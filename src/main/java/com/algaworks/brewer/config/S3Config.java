package com.algaworks.brewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

@Configuration
@PropertySource(value = "file:\\E:\\_Programação\\Algaworks\\brewer-s3.properties", ignoreResourceNotFound = true) //subscreve sempre as propriedades que existe no ultimo arquivo e ignora caso nao exista o arquivo
public class S3Config {

	@Autowired
	private Environment env;
	
	@Bean
	public AmazonS3 amazonS3() {
		AWSCredentials credenciais = new BasicAWSCredentials(env.getProperty("AWS_ACCESS_KEY_ID"), env.getProperty("AWS_SECRET_ACCESS_KEY"));
		AmazonS3 amazonS3 = new AmazonS3Client(credenciais, new ClientConfiguration());
		amazonS3.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
		amazonS3.setEndpoint("http://localhost:9444/s3");
		return amazonS3;
//		Quando usar o S3 verdadeiro
//		Region regiao = Region.getRegion(Regions.US_EAST_1);
//		amazonS3.setRegion(regiao);
	}
}
