package com.amirkenesbay.service.impl;

import com.amirkenesbay.dao.AppUserDAO;
import com.amirkenesbay.dao.RawDataDAO;
import com.amirkenesbay.entity.AppUser;
import com.amirkenesbay.entity.RawData;
import com.amirkenesbay.service.MainService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.amirkenesbay.entity.enums.UserState.BASIC_STATE;

@Service
public class MainServiceImpl implements MainService {
    private final RawDataDAO rawDataDAO;
    private final ProducerServiceImpl producerServiceImpl;
    private final AppUserDAO appUserDAO;

    public MainServiceImpl(RawDataDAO rawDataDAO, ProducerServiceImpl producerServiceImpl, AppUserDAO appUserDAO) {
        this.rawDataDAO = rawDataDAO;
        this.producerServiceImpl = producerServiceImpl;
        this.appUserDAO = appUserDAO;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
        var textMessage = update.getMessage();
        var telegramUser = textMessage.getFrom();
        var appUser = findOrSaveAppUser(telegramUser);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from NODE");
        producerServiceImpl.produceAnswer(sendMessage);
    }

    private AppUser findOrSaveAppUser(User telegramUser){
        AppUser persistenAppUser = appUserDAO.findAppUserByTelegramUserId(telegramUser.getId());
        if(persistenAppUser == null){
            AppUser transeitAppUser = AppUser.builder()
                    .telegramUserId(telegramUser.getId())
                    .userName(telegramUser.getUserName())
                    .lastName(telegramUser.getLastName())
                    // TODO изменить значения по умолчанию после добавления регистрации
                    .isActive(true)
                    .state(BASIC_STATE)
                    .build();
            return appUserDAO.save(transeitAppUser);
        }
        return persistenAppUser;
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                          .event(update)
                          .build();
        rawDataDAO.save(rawData);
    }
}
