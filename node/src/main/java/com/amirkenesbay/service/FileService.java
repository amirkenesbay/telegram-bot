package com.amirkenesbay.service;

import com.amirkenesbay.entity.AppDocument;
import com.amirkenesbay.entity.AppPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface FileService {
    AppDocument processDoc(Message externalMessage);
    AppPhoto processPhoto(Message telegramMessage);
}
