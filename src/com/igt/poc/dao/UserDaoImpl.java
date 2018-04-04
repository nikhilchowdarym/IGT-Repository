package com.igt.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.igt.poc.util.IGTMongoClient;
import com.igt.poc.vo.TaskVO;
import com.igt.poc.vo.UserVo;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository("UserDao")
public class UserDaoImpl implements UserDao{

	@Override
	public Boolean findByUserNamePassword(String userName, String password) {
		List<UserVo> listUsers = new ArrayList<UserVo>();
		
		MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
		  MongoCollection<Document> collectionName = mongoDBConnection.getCollection("USERS_MASTER");
		  
		  BasicDBObject criteria = new BasicDBObject();
		  criteria.append("userName", userName);
		  criteria.append("password", password);
		  FindIterable<Document> taskVOs = collectionName.find(criteria);
		  
		  for (Document document : taskVOs) {
				 UserVo userVo = new UserVo();
				 userVo.setUserName(document.get("userName").toString());
				 userVo.setPassword(document.get("password").toString());
				 listUsers.add(userVo);
			}
		  	  
		 if(listUsers!= null && listUsers.size()>0){
			  return true;
		  }else{
			  return false;
		  }
		  
	}

}
