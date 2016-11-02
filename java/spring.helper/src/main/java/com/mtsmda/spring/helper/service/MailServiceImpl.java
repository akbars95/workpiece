package com.mtsmda.spring.helper.service;

import com.mtsmda.generator.GenerateRandom;
import com.mtsmda.helper.*;
import com.mtsmda.spring.helper.configuration.FreeMarkerFacade;
import com.mtsmda.spring.helper.response.CommonResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by dminzat on 9/22/2016.
 */
@Service("mailServiceImpl")
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class);

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSenderImpl mailSender;

    @Autowired
    @Qualifier("freeMarkerFacade")
    private FreeMarkerFacade freeMarkerFacade;

    /**
     * This method will send compose and send the message
     */
    /*@Override
    public CommonResponse<Boolean> sendEMail(String to, String subject, String body) {
        return sendEmailMessage(to, subject, body, null, null, null);
    }

    @Override
    public CommonResponse<Boolean> sendEMailViaFreemarkerTemplate(String to, String subject, Map<String, Object> input, String templateName) {
        return sendEmailMessage(to, subject, null, input, null, templateName);
    }

    @Override
    public CommonResponse<Boolean> sendEmail(String to, String subject, String body, String[] attachFiles) {
        return sendEmailMessage(to, subject, body, null, attachFiles, null);
    }

    @Override
    public CommonResponse<Boolean> sendEmailViaFreemarkerTemplate(String to, String subject, Map<String, Object> input, String[] attachFiles, String templateName) {
        return sendEmailMessage(to, subject, null, input, attachFiles, templateName);
    }*/
    private CommonResponse<Boolean> sendEmailMessage(String to, String subject, String body, Map<String, Object> input, String[] attachFiles, String templateName) {
        String innerMessageForLog = null;
        try {
            if (ObjectHelper.objectIsNull(to)) {
                innerMessageForLog = "param 'to' is null or empty!";
                LOGGER.error(innerMessageForLog);
                return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, innerMessageForLog);
            }

            if (ObjectHelper.objectIsNull(subject)) {
                innerMessageForLog = "param 'subject' is null or empty!";
                LOGGER.error(innerMessageForLog);
                return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, innerMessageForLog);
            }

            if (ObjectHelper.objectIsNull(body) && ObjectHelper.objectIsNull(input)) {
                innerMessageForLog = "param 'body' or 'input' is null or empty!";
                LOGGER.error(innerMessageForLog);
                return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, innerMessageForLog);
            }

            Session session = Session.getInstance(this.mailSender.getJavaMailProperties(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSender.getUsername(),
                            mailSender.getPassword());
                }
            });
            Writer writer = new StringWriter();
            try {
                freeMarkerFacade.writeContent(templateName, writer, input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            MimeMessage messageMail = new MimeMessage(session);
            messageMail.setFrom(new InternetAddress(mailSender.getUsername()));
            messageMail.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            messageMail.setSubject(subject);
            messageMail.setText(writer.toString(), "UTF-8", "html");
            messageMail.setSentDate(LocalDateTimeHelper.convertCurrentLocalDateTimeToDate());

            BodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(writer.toString(), "text/html");

            writer.close();

            Multipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(mimeBodyPart);

            if (ObjectHelper.objectIsNotNull(attachFiles) && attachFiles.length > 0) {
                for (String filePath : attachFiles) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    try {
                        attachPart.attachFile(filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mimeMultipart.addBodyPart(attachPart);
                }
            }

            messageMail.setContent(mimeMultipart);
            Transport.send(messageMail);
            return new CommonResponse<>(true, CommonResponse.EMAIL_SERVICE_SUCCESS, null);
        } catch (Exception e) {
            innerMessageForLog = ExceptionMessageHelper.exceptionDescription(e);
            LOGGER.error(innerMessageForLog);
            return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, innerMessageForLog);
        }
    }

    public MimeMessagePreparator getContentWithAttachments(String from, String[] to, String subject, String content, Map<String, ClassPathResource> fileNameFileMapInlineImages, Map<String, ClassPathResource> fileNameFileMap) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            if (ObjectHelper.objectIsNotNull(fileNameFileMapInlineImages) && !fileNameFileMapInlineImages.isEmpty()) {
                fileNameFileMapInlineImages.forEach((name, classPathResource) -> {
                    try {
                        mimeMessageHelper.addInline(name, classPathResource);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
            if (ObjectHelper.objectIsNotNull(fileNameFileMap) && !fileNameFileMap.isEmpty())
                fileNameFileMap.forEach((fileName, classPathResource) -> {
                    try {
                        mimeMessageHelper.addAttachment(fileName, classPathResource);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
        };
    }

    public void sendEmailWithAttachment() {
        Writer writer = new StringWriter();
        ClassPathResource classPathResource = new ClassPathResource("images/email/Screenshot.png");
        Map<String, Object> stringObjectMap = null;
        try {
            stringObjectMap = MapHelper.getMap(
                    ListHelper.getListWithData("currentUser", "requestDateTime",
                            "tempUrl", "tempPassword", "imageSrc"),
                    ListHelper.getListWithData("Ivanov Ivan", LocalDateTimeHelper.convertLocalDateTimeToString(LocalDateTime.now(), LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT),
                            "http://yandex.ru", new GenerateRandom(true).generate(19), "firstImage"));

            System.out.println(classPathResource.getPath());
            this.freeMarkerFacade.writeContent("emailSendWithImage.ftl", writer, stringObjectMap);
            Map<String, ClassPathResource> stringClassPathResourceMapInlineImages = MapHelper.getMap(
                    ListHelper.getListWithData("firstImage"), ListHelper.getListWithData(classPathResource)
            );
            Map<String, ClassPathResource> stringClassPathResourceMap = MapHelper.getMap(
                    ListHelper.getListWithData("description"), ListHelper.getListWithData(classPathResource)
            );
            this.mailSender.send(getContentWithAttachments("souvenir.buy.site@gmail.com", new String[]{"artem.borisov0922@yandex.ru"},
                    "testing", writer.toString(), stringClassPathResourceMapInlineImages, stringClassPathResourceMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommonResponse<Boolean> sendEMail(SimpleMailMessage simpleMailMessage) {
        try {
            this.mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, ExceptionMessageHelper.exceptionDescription(e));
        }
        return new CommonResponse<>(true, CommonResponse.EMAIL_SERVICE_SUCCESS, "Email Success Send!");
    }

    @Override
    public CommonResponse<Boolean> sendEMailWithAttachment(SimpleMailMessage simpleMailMessage, Map<String, File> fileNameAndFile, boolean isHTML) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            convertSimpleMailMessageToMimeMessageHelper(simpleMailMessage, mimeMessageHelper, isHTML);
            if (ObjectHelper.objectIsNotNull(fileNameAndFile)) {
                fileNameAndFile.forEach((fileName, file) -> {
                    try {
                        mimeMessageHelper.addAttachment(fileName, file);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, ExceptionMessageHelper.exceptionDescription(e));
        }
        return new CommonResponse<>(true, CommonResponse.EMAIL_SERVICE_SUCCESS, "Email Success Send!");
    }

    @Override
    public CommonResponse<Boolean> sendEMailViaFreemarkerTemplate(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName) {
        return sendEMailViaFreemarkerTemplateWithAttachment(simpleMailMessage, input, null, templateName);
    }

    @Override
    public CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input, Map<String, File> fileNameAndFile, String templateName) {
        try {
            Writer writer = new StringWriter();
            this.freeMarkerFacade.writeContent(templateName, writer, input);
            simpleMailMessage.setText(writer.toString());
            return sendEMailWithAttachment(simpleMailMessage, fileNameAndFile, true);
        } catch (Exception e) {
            return new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, ExceptionMessageHelper.exceptionDescription(e));
        }
    }

    @Override
    public CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithInlineAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName, Map<String, File> fileNameAndFileInline) {
        return sendEMailWithAttachmentProcess(simpleMailMessage, input, templateName, null, fileNameAndFileInline, true);
    }

    @Override
    public CommonResponse<Boolean> sendEMailViaFreemarkerTemplateWithInlineAttachment(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName, Map<String, File> fileNameAndFile, Map<String, File> fileNameAndFileInline) {
        return sendEMailWithAttachmentProcess(simpleMailMessage, input, templateName, fileNameAndFile, fileNameAndFileInline, true);
    }

    private void convertSimpleMailMessageToMimeMessageHelper(SimpleMailMessage simpleMailMessage, MimeMessageHelper mimeMessageHelper, boolean isHTML) throws MessagingException, UnsupportedEncodingException {
        mimeMessageHelper.setSentDate(ObjectHelper.objectIsNull(simpleMailMessage.getSentDate()) ? LocalDateTimeHelper.convertCurrentLocalDateTimeToDate() : simpleMailMessage.getSentDate());
        mimeMessageHelper.setFrom(simpleMailMessage.getFrom());
        mimeMessageHelper.setTo(simpleMailMessage.getTo());
        if(!ObjectHelper.objectIsNull(simpleMailMessage.getCc())){
            mimeMessageHelper.setCc(simpleMailMessage.getCc());
        }

        if(!ObjectHelper.objectIsNull(simpleMailMessage.getBcc())){
            mimeMessageHelper.setBcc(simpleMailMessage.getBcc());
        }

        mimeMessageHelper.setSubject(simpleMailMessage.getSubject());
        mimeMessageHelper.setText(simpleMailMessage.getText(), isHTML);
    }

    private CommonResponse<Boolean> sendEMailWithAttachmentProcess(SimpleMailMessage simpleMailMessage, Map<String, Object> input, String templateName, Map<String, File> fileNameAndFile, Map<String, File> fileNameAndFileInline, boolean isHTML) {
        try {
            Writer writer = new StringWriter();
            this.freeMarkerFacade.writeContent(templateName, writer, input);
            simpleMailMessage.setText(writer.toString());
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            convertSimpleMailMessageToMimeMessageHelper(simpleMailMessage, mimeMessageHelper, isHTML);
            if (ObjectHelper.objectIsNotNull(fileNameAndFile) && !fileNameAndFile.isEmpty()) {
                fileNameAndFile.forEach((fileName, file) -> {
                    try {
                        mimeMessageHelper.addAttachment(fileName, file);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            if (ObjectHelper.objectIsNotNull(fileNameAndFileInline) && !fileNameAndFileInline.isEmpty()) {
                fileNameAndFileInline.forEach((contentId, file) -> {
                    try {
                        mimeMessageHelper.addInline(contentId, file);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            new CommonResponse<>(false, CommonResponse.EMAIL_SERVICE_ERROR, ExceptionMessageHelper.exceptionDescription(e));
        }
        return new CommonResponse<>(true, CommonResponse.EMAIL_SERVICE_SUCCESS, "Email Success Send!");
    }
}