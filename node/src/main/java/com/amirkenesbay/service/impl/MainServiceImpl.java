package com.amirkenesbay.service.impl;

import com.amirkenesbay.dao.RawDataDAO;
import com.amirkenesbay.entity.RawData;
import com.amirkenesbay.service.MainService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MainServiceImpl implements MainService {
    private final RawDataDAO rawDataDAO;
    private final ProducerServiceImpl producerServiceImpl;

    public MainServiceImpl(RawDataDAO rawDataDAO, ProducerServiceImpl producerServiceImpl) {
        this.rawDataDAO = rawDataDAO;
        this.producerServiceImpl = producerServiceImpl;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from NODE");
        producerServiceImpl.produceAnswer(sendMessage);
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                          .event(update)
                          .build();
        rawDataDAO.save(rawData);
    }
}
