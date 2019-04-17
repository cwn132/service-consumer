package com.spingcloud.serviceconsumer.service;

import com.spingcloud.serviceconsumer.serviceimpl.IOrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "product-service", fallback = IOrderServiceFallback.class)
public   interface IOrderService {

    @RequestMapping(value = "/saveIorder",method = RequestMethod.GET)
    Object saveIorder(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId);
}
