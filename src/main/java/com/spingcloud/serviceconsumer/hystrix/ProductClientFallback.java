package com.spingcloud.serviceconsumer.hystrix;

import com.spingcloud.serviceconsumer.controller.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务，错降级处理
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {

        System.out.println("ProductClientFallback中的降级方法");

        //这对gai该接口进行一些逻辑降级处理........
        return null;
    }
}