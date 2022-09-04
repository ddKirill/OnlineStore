package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.UserEntity;
import com.ddkirill.strore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserManagerService {

    private final UserRepository userRepository;

    public UserManagerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(Message message, OrderEntity firstOrder) {

        var chatId = message.getChatId();
        var userName = message.getChat().getFirstName();
        var userId = message.getFrom().getId();

        if (userRepository.existsById(chatId)) {
            System.out.println("Пользователь уже существует");
        } else {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            UserEntity newUser = new UserEntity();
            newUser.setUserId(userId);
            newUser.setUserName(userName);
            newUser.setChatId(chatId);
            newUser.setRegisteredAt(timestamp);
            //Create order and add in ref table
            newUser.addOrderReferences(firstOrder);
            userRepository.save(newUser);
            System.out.println("Новый пользователь создан");
        }
    }

    public UserEntity getUserById(Long chatId) {
        Optional<UserEntity> optionalUser = userRepository.findById(chatId);
        UserEntity userEntity;
        if (optionalUser.isPresent()) {
            userEntity = optionalUser.get();
        } else {
            return null;
        }
        return userEntity;
    }

}
