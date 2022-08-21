package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.UserEntity;
import com.ddkirill.strore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Timestamp;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(Message message) {

        var chatId = message.getChatId();
        var userName = message.getChat().getFirstName();

        if (userRepository.findById(message.getChatId()).isEmpty()) {

            UserEntity newUser = new UserEntity(chatId, userName, null, null, new Timestamp(System.currentTimeMillis()) );

            userRepository.save(newUser);
        }
    }
}
