package com.amirkenesbay.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ConsumeService {
    void consumeTextMessageUpdates(Update update);
    void consumeDocMessageUpdates(Update update);
    void consumePhotoMessageUpdated(Update update);
}
