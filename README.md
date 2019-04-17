# service-consumer
Service Consumer服务消费方


1.调用：
http://localhost:9001/api/v1/order/save?user_id=1&product_id=1

2.用Fegin调用：
http://localhost:9001/api/v1/order/saveorder?user_id=1&product_id=1

3.整合Hystrix熔断

4.添加AOP日志：http://localhost:9001/api/saveIorder?product_id=2&user_id=2

5.整合Swagger:http://localhost:9001/swagger-ui.html
