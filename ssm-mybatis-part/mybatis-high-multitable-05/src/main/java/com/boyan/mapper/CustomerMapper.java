package com.boyan.mapper;

import com.boyan.pojo.Customer;

public interface CustomerMapper {
    Customer selectCustomerWithOrderList(Integer customerId);

}
