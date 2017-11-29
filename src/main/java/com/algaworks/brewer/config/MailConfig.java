package com.algaworks.brewer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.algaworks.brewer.mail.Mailer;

@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
@PropertySources({ 
	@PropertySource("classpath:env/mail-${ambiente:local}.properties"), //informa qual o arquivo de senha na hora que sobe o server senao informar vai ser o local definido como padrão
	@PropertySource(value = "file:\\E:\\_Programação\\Algaworks\\brewer-mail.properties", ignoreResourceNotFound = true) //subscreve sempre as propriedades que existe no ultimo arquivo e ignora caso nao exista o arquivo
})
public class MailConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		//mailSender.setHost("mail.conceptautomacao.com.br");
		mailSender.setPort(587);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setPassword(env.getProperty("SENDGRID_PASSWORD"));
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", false);
		props.put("mail.smtp.connectiontimeout", 10000); //miliseconds
		
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}

}
