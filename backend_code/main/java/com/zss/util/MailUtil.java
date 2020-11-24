package com.zss.util;

import com.zss.util.randomCode;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    private static String myEmailAccount="1250913438@qq.com";//发件人
    private static String myEmailPassword="hjwhrxfcunsggfda";//保护码
    private static String myEmailSMTPHost="smtp.qq.com";
    private static String receiveMailAccount="";//收件人
    private static String smtpPort="465";

    private static String randomStr ;


    public static String sendMail(String email,String username){
        try{
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);

            Session session=Session.getDefaultInstance(props);
            session.setDebug(true);

            receiveMailAccount = email;//更改邮件接收人为注册用户
            MimeMessage message= createMimeMessage(session,myEmailAccount,receiveMailAccount,username);

            Transport transport=session.getTransport();

            transport.connect(myEmailAccount,myEmailPassword);

            transport.sendMessage(message,message.getAllRecipients());

            transport.close();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return randomStr;
    }

    private static MimeMessage createMimeMessage(Session session,String sendMail,String receiveMail,String recivename){
        //1. 创建邮件
        MimeMessage message = new MimeMessage(session);
        try{
            //1. 创建邮件
//            MimeMessage message = new MimeMessage(session);

            //2.绑定发件人
            message.setFrom(new InternetAddress(sendMail,"巴蜀超市","UTF-8"));

            //3.绑定收件人
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail,recivename,"UTF-8"));


            //4.邮件主题
            message.setSubject("您此次注册的验证码：","UTF-8");

            //5. 邮件正文
            randomStr = new randomCode().randomcode();
            message.setContent(randomStr,"text/html;charset=UTF-8");

            //6.发送时间
            message.setSentDate(new Date());

            //7.保存设置
            message.saveChanges();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return message;
    }
}
