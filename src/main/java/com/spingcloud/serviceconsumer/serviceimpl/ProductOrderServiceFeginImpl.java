package com.spingcloud.serviceconsumer.serviceimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.spingcloud.serviceconsumer.controller.ProductClient;
import com.spingcloud.serviceconsumer.dao.ProductOrder;
import com.spingcloud.serviceconsumer.service.ProductOrderFeginService;
import com.spingcloud.serviceconsumer.service.ProductOrderService;
import com.spingcloud.serviceconsumer.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceFeginImpl implements ProductOrderFeginService {

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder saveorder(int userId, int productId) {

        //获取json格式的字符串数据
        String response = productClient.findById(productId);
        //Json字符串转换成JsonNode对象
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        //将数据封装到订单实体中
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        //获取商品名称和商品价格
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));

        //因为在商品微服务配置了集群，所以这里打印看下调用了是哪个集群节点，输出端口号。
        System.out.println(jsonNode.get("name").toString());
        return productOrder;
    }


}
