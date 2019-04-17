package com.spingcloud.serviceconsumer.serviceimpl;

import com.spingcloud.serviceconsumer.controller.ProductClient;
import com.spingcloud.serviceconsumer.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@Component
public class IOrderServiceFallback implements IOrderService {


    /**
     * 出错则调用该方法返回友好错误
     * @param userId
     * @param productId
     * @return
     */
    public Object saveIorder(int userId,int productId){
        System.out.println("saveIorder中的降级方法");

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "下单人数太多，您被挤出来了，稍等重试...");
        return msg;
    }
}
