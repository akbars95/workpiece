package com.mtsmda.spring.helper.service;

import com.mtsmda.spring.helper.response.CommonResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;

import java.io.File;
import java.util.Map;

/**
 * Created by dminzat on 9/22/2016.
 */
public interface MailService {

    CommonResponse<Boolean> sendEMail(SimpleMailMessage simpleMailMessage);
    CommonResponse<Boolean> sendEMailWithAttachment(SimpleMailMessage simpleMailMessage, Map<String, File> fileNameAndFile, boolean isHTML);
    CommonResponse<Boolean> sendEMailViaFreemarkerTemplate(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName);
    CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input,  Map<String, File> fileNameAndFile, String templateName);
    CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithInlineAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName, Map<String, File> fileNameAndFileInline);
    CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithInlineAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName, Map<String, File> fileNameAndFile, Map<String, File> fileNameAndFileInline);

}