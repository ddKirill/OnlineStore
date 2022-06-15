package com.ddkirill.strore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class StroreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StroreApplication.class, args);
	}

}
