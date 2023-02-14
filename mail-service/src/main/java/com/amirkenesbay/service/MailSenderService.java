package com.amirkenesbay.service;


import com.amirkenesbay.dto.MailParams;

public interface MailSenderService {
    void send(MailParams mailParams);
}
