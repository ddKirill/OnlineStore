package com.ddkirill.strore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Table (value = "users")
public class UserEntity {

    @Id
    private Long chatId;
    private Long userId;
    private String userName;
    private Integer phoneNumber;
    private Timestamp registeredAt;
    @Column(value = "chat_id")
    private Set<OrderReferences> orderReferences = new HashSet<>();
    @Version
    private Integer version;

    public UserEntity(Long chatId, String userName, Integer phoneNumber, Timestamp registeredAt, Long userId, Set<OrderReferences> orderReferences) {
        this.chatId = chatId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.registeredAt = registeredAt;
        this.userId = userId;
        this.orderReferences = orderReferences;
    }

    public void addOrderReferences(OrderEntity order) {
        orderReferences.add(new OrderReferences(order.getOrderNumber()));
    }

    public UserEntity() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<OrderReferences> getOrderReferences() {
        return orderReferences;
    }
}
