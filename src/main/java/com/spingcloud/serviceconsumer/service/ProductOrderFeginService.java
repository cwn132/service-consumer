package com.spingcloud.serviceconsumer.service;

import com.spingcloud.serviceconsumer.dao.ProductOrder;

public interface ProductOrderFeginService {

    //下单接口
    ProductOrder saveorder(int userId, int productId);
}
