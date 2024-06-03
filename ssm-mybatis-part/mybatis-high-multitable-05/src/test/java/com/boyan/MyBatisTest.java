package com.boyan;


import com.boyan.mapper.CustomerMapper;
import com.boyan.mapper.OrderMapper;
import com.boyan.pojo.Customer;
import com.boyan.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class MyBatisTest {

    private SqlSession session;

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(
                        Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession(true);
    }
    @AfterEach
    public void clear() {
        // session.commit();
        session.close();
    }

    @Test
    public void testRelationshipToOne()
    {
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        // 查询Order对象，检查是否同时查询了关联的Customer对象
        Order order = orderMapper.selectOrderWithCustomer(2);
        System.out.println("order = " + order);
    }

    @Test
    public void testRelationshipToMulti()
    {
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        // 查询Customer对象同时将关联的Order集合查询出来
        Customer customer = customerMapper.selectCustomerWithOrderList(1);
        System.out.println("customer.getCustomerId() = " + customer.getCustomerId());
        System.out.println("customer.getCustomerName() = " + customer.getCustomerName());
        List<Order> orderList = customer.getOrderList();
        for (Order order : orderList) {
            System.out.println("order = " + order);
        }
    }
}