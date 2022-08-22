package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.StatusEnum;
import com.ddkirill.strore.entity.UserEntity;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public void addNewUser(Message message) {

        var chatId = message.getChatId();
        var userName = message.getChat().getFirstName();

        if (userRepository.findById(message.getChatId()).isEmpty()) {
            //Create new user
            UserEntity newUser = new UserEntity(chatId, userName, null, null, new Timestamp(System.currentTimeMillis()) );
            userRepository.save(newUser);
            //Add first order for new user
            OrderEntity createOrder = new OrderEntity(null, chatId, null, null, StatusEnum.WAIT.getStatus(), null, null);
            orderRepository.save(createOrder);
        }
    }
}
