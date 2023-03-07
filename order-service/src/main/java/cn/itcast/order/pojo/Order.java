package cn.itcast.order.pojo;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
    public Long getUserId() {
        return this.userId;
    }
}