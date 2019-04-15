package com.spingcloud.serviceconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spingcloud.serviceconsumer.service.ProductOrderFeginService;
import com.spingcloud.serviceconsumer.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1/order")
@RequestMapping(value = "api")
@Api("OrderFeginController相关的api")
public class OrderFeginController {

    @Autowired
    private ProductOrderFeginService productOrderFeginService;

    //限流：对外提供一个服务接口，允许最大并发数为10
//    private final Semaphore permit = new Semaphore(4, true);

    //当调用微服务出现异常会降级到saveOrderFail方法中
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    @RequestMapping(value = "/saveorder",method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询商品信息", notes = "查询数据库中某个的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id",paramType = "query", value = "用户ID", required = true),
            @ApiImplicitParam(name = "product_id",value = "商品ID",required = true,paramType = "query",dataType = "string")
    })
    public Object saveorder(@RequestParam("user_id")int userId, @RequestParam("product_id") int productId) throws InterruptedException {



        return productOrderFeginService.saveorder(userId, productId);


    }


    //注意，方法签名一定要要和api方法一致
    private Object saveOrderFail(int userId, int productId){

        System.out.println("controller中的降级方法");

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
        return msg;
    }
}
