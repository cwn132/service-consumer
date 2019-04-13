package com.spingcloud.serviceconsumer.service;

import com.spingcloud.serviceconsumer.dao.ProductOrder;

/**
 * 订单业务类
 */
public interface ProductOrderService {
    //下单接口
    ProductOrder save(int userId, int productId);
}