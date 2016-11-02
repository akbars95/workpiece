package com.mtsmda.spring.helper;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.spring.helper.response.CommonResponse;
import com.mtsmda.spring.helper.service.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dminzat on 11/2/2016.
 */
public class Run {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-*.xml");
        MailService mailService = applicationContext.getBean("mailServiceImpl", MailService.class);
        List<String> emailAddressList = applicationContext.getBean("emailAddressList", ArrayList.class);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("souvenir.buy.site@gmail.com");
        simpleMailMessage.setTo(emailAddressList.toArray(new String[emailAddressList.size()]));
        simpleMailMessage.setCc("souvenir.buy.site@gmail.com");
        simpleMailMessage.setBcc("souvenir.buy.site@gmail.com");
        simpleMailMessage.setSentDate(LocalDateTimeHelper.convertLocalDateTimeToDate(LocalDateTime.now()));
        simpleMailMessage.setSubject("Simple Test");
        simpleMailMessage.setText("This is test message!");
        CommonResponse<Boolean> booleanCommonResponse = null;//mailService.sendEMail(simpleMailMessage);
//        booleanCommonResponse = mailService.sendEMail(simpleMailMessage);

        Map<String, File> stringFileMap = new LinkedHashMap<>();
        stringFileMap.put("oracle java image", new ClassPathResource("images/oracle_java-100026145-large.jpg").getFile());
        stringFileMap.put("spring framework", new ClassPathResource("images/springframework.png").getFile());
        stringFileMap.put("goreniya instruction", new ClassPathResource("docs/312794.pdf").getFile());
        /*booleanCommonResponse = mailService.sendEMailWithAttachment(simpleMailMessage, stringFileMap);*/

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("currentUser", "Ivanov Ivan Ivanovic");
//        booleanCommonResponse = mailService.sendEMailViaFreemarkerTemplate(simpleMailMessage, params, "forTest.ftl");
//        booleanCommonResponse = mailService.sendEMailViaFreemarkerTemplateWithAttachment(simpleMailMessage, params, stringFileMap, "forTest.ftl");

        Map<String, File> stringFileMapInline = new LinkedHashMap<>();
        stringFileMapInline.put("javaImage", new ClassPathResource("images/oracle_java-100026145-large.jpg").getFile());
        stringFileMapInline.put("springImage", new ClassPathResource("images/springframework.png").getFile());
        stringFileMapInline.put("docInstruction", new ClassPathResource("docs/312794.pdf").getFile());
//        booleanCommonResponse = mailService.sendEMailViaFreemarkerTemplateWithInlineAttachment(simpleMailMessage, params, "forTestInlineData.ftl", stringFileMapInline);

        booleanCommonResponse = mailService.sendEMailViaFreemarkerTemplateWithInlineAttachment(simpleMailMessage, params, "forTestInlineData.ftl", stringFileMap, stringFileMapInline);
        System.out.println(booleanCommonResponse);
    }

}