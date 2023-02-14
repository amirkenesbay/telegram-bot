package com.amirkenesbay.service;

import com.amirkenesbay.entity.AppDocument;
import com.amirkenesbay.entity.AppPhoto;
import com.amirkenesbay.service.enums.LinkType;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface FileService {
    AppDocument processDoc(Message externalMessage);
    AppPhoto processPhoto(Message telegramMessage);
    String generateLink(Long docId, LinkType linkType);
}
