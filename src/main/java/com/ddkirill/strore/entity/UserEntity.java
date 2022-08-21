package com.ddkirill.strore.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;
import java.sql.Timestamp;


@Table (value = "users")
public class UserEntity {

    @Id
    private Long chatId;
    private String userName;
    private Integer phoneNumber;
    private Integer[] orderNumber;
    private Timestamp registeredAt;
    @Version
    private Integer version;

    public UserEntity(Long chatId, String userName, Integer phoneNumber, Integer[] orderNumber, Timestamp registeredAt) {
        this.chatId = chatId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.orderNumber = orderNumber;
        this.registeredAt = registeredAt;
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

    public Integer[] getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer[] orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }


}
