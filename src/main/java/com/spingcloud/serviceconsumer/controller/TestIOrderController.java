package com.spingcloud.serviceconsumer.controller;

import com.spingcloud.serviceconsumer.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api")
@Api("OrderFeginController相关的api")
public class TestIOrderController {

    @Autowired
    private IOrderService iorderService;

    @ApiOperation(value = "根据id查询商品信息(测试)", notes = "查询数据库中某个的商品信息(测试)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id",paramType = "query", value = "用户ID", required = true),
            @ApiImplicitParam(name = "product_id",value = "商品ID",required = true,paramType = "query",dataType = "string")
    })
    @RequestMapping(value = "/saveIorder",method = RequestMethod.GET)
    public Object saveIorder(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId) throws Exception{
        return iorderService.saveIorder(userId,productId);
    }


}
