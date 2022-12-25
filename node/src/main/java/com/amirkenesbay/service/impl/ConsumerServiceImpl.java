package com.amirkenesbay.service.impl;

import com.amirkenesbay.service.ConsumeService;
import com.amirkenesbay.service.ProduceService;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.amirkenesbay.model.RabbitQueue.*;

@Service
@Log4j
public class ConsumerServiceImpl implements ConsumeService {
    private final ProduceService produceService;

    public ConsumerServiceImpl(ProduceService produceService) {
        this.produceService = produceService;
    }

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdates(Update update) {
        log.debug("NODE: TEXT MESSAGE IS RECEIVED");

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from NODE");
        produceService.produceAnswer(sendMessage);
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeDocMessageUpdates(Update update) {
        log.debug("NODE: DOC MESSAGE IS RECEIVED");
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumePhotoMessageUpdated(Update update) {
        log.debug("NODE: PHOTO MESSAGE IS RECEIVED");
    }
}
