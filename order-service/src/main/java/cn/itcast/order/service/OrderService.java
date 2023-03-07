package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //发送http请求到user-service (注意端口和/别写错了)
        // TODO: 2023/3/6 将这里的硬编码转换为服务名 + 端口 取消硬编码到url的部分
        //String url = "http://localhost:8081/user/" + order.getUserId();
        String url = "http://userservice/user/" + order.getUserId();
        //RestTemplate默认返回的是json格式的数据但是第二个参数是responseType来指定返回值类型
        User user = restTemplate.getForObject(url, User.class);
        //将user填入order中
        order.setUser(user);
        return order;
    }
}
