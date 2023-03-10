package cn.itcast.order.service;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2、使用feign调用findById方法即可
        User user = userClient.findById(order.getUserId());
        //3、封装user
        order.setUser(user);
        //4、返回
        return order;
    }

    /*@Resource
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
    }*/
}
