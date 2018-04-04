package com.igt.poc.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class IGTMongoClient {
	
	

	@SuppressWarnings("resource")
	public static MongoDatabase getMongoDB(){
		MongoDatabase mongoDB= null;
		try {			
			Resource resource = new ClassPathResource("/MongoDB.properties");
			Properties p;
			MongoClient mongoClient;
			String ip="",dataBaseName="";
			
			int port = 0;
			try {
				p = PropertiesLoaderUtils.loadProperties(resource);
				ip=p.getProperty("ipaddress");
				port=Integer.parseInt(p.getProperty("port"));
				dataBaseName=p.getProperty("database");
			} catch (IOException e) {
				e.printStackTrace();
			}
			mongoClient = new MongoClient(ip,port);
			mongoDB = mongoClient.getDatabase(dataBaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mongoDB;
		
	}
}
