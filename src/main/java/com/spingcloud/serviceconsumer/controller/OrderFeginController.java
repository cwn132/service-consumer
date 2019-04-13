package com.spingcloud.serviceconsumer.controller;

import com.spingcloud.serviceconsumer.service.ProductOrderFeginService;
import com.spingcloud.serviceconsumer.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderFeginController {

    @Autowired
    private ProductOrderFeginService productOrderFeginService;

    @RequestMapping("saveorder")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId){
        return productOrderFeginService.saveorder(userId, productId);
    }
}
