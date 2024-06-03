package com.boyan.mapper;

import com.boyan.pojo.Order;

public interface OrderMapper {
    Order selectOrderWithCustomer(Integer orderId);
}
