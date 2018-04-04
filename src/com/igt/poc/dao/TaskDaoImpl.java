package com.igt.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.igt.poc.util.IGTMongoClient;
import com.igt.poc.vo.TaskVO;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Repository("TaskDao")
public class TaskDaoImpl implements TaskDao{
	
//	@Autowired
//	@Qualifier("mongoTemplate")
//	private MongoTemplate mongoTemplate;
	
	public TaskDaoImpl(){
	}
			
	public List<TaskVO> getAllTasks() {
		List<TaskVO> listTasks = null;
		try{
			 listTasks =  new ArrayList<TaskVO>();
			 MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
			 MongoCollection<Document> collectionName = mongoDBConnection.getCollection("IGT_TASK_DETAILS");
			 FindIterable<Document> taskVOs= collectionName.find();
			 
			 for (Document document : taskVOs) {
				 TaskVO taskVO = new TaskVO();
				 taskVO.setTaskName(document.get("taskName").toString());
				 taskVO.setTaskStatus(document.get("taskStatus").toString());
				 taskVO.setPersonName(document.get("personName").toString());
				 taskVO.setId(document.get("_id").toString());
				 listTasks.add(taskVO);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listTasks;
	}
 
	
 
	public TaskVO insertTask(TaskVO taskVO) {
		
		try{
//			mongoTemplate.save(testPojo, "Task");
			
			 MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
			  MongoCollection<Document> collectionName = mongoDBConnection.getCollection("IGT_TASK_DETAILS");
			  Document document = null;
			  				
 			  Gson gson = new Gson();
 			  document = new Document();
 			  document.putAll( Document.parse(gson.toJson(taskVO)));		  			  
 			  collectionName.insertOne(document);
		}
		catch(Exception ex){
			ex.printStackTrace();
			//return false;
		}
		
		return taskVO;
	}
 
	public boolean updateTask(TaskVO taskPojo) {
		try{
			
			MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
			  MongoCollection<Document> collectionName = mongoDBConnection.getCollection("IGT_TASK_DETAILS");
			  /*collectionName.updateOne(Filters.eq("id", taskPojo.getId()), Updates.set("taskName", taskPojo.getTaskName()));
			  collectionName.updateOne(Filters.eq("id", taskPojo.getId()), Updates.set("taskStatus", taskPojo.getTaskStatus())); 		  
			*/
			  
			  Bson filter = Filters.eq("_id", new ObjectId(taskPojo.getId()));
			  BasicDBObject updateQuery = new BasicDBObject();
			  updateQuery.append("$set", new BasicDBObject().append("taskName", taskPojo.getTaskName()));
//			  updateQuery.append("$set", new BasicDBObject().append("taskStatus", taskPojo.getTaskStatus()));
			  collectionName.findOneAndUpdate(filter, updateQuery);
			  
			  Bson filter3 = Filters.eq("_id", new ObjectId(taskPojo.getId()));
			  BasicDBObject updateQuery3 = new BasicDBObject();
			  updateQuery3.append("$set", new BasicDBObject().append("personName", taskPojo.getPersonName()));
//			  updateQuery.append("$set", new BasicDBObject().append("taskStatus", taskPojo.getTaskStatus()));
			  collectionName.findOneAndUpdate(filter3, updateQuery3);
			  
			  Bson filter1 = Filters.eq("_id", new ObjectId(taskPojo.getId()));
			  BasicDBObject updateQuery1 = new BasicDBObject();
//			  updateQuery.append("$set", new BasicDBObject().append("taskName", taskPojo.getTaskName()));
			  updateQuery1.append("$set", new BasicDBObject().append("taskStatus", taskPojo.getTaskStatus()));
			  collectionName.findOneAndUpdate(filter1, updateQuery1);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean deleteTaskByName(String taskName) {
		
		
		MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
		  MongoCollection<Document> collectionName = mongoDBConnection.getCollection("IGT_TASK_DETAILS");
		  collectionName.deleteOne(new Document("_id", new ObjectId(taskName)));
		
//		Criteria criteria = Criteria.where("TaskName").in(taskName);
		
//		Query query = new Query(criteria);
		
		return true;
	}

	@Override
	public TaskVO findById(String id) {
		TaskVO taskVO = null;
		List<TaskVO> listTasks = null;
//		List<TestPojo> listTasks = mongoTemplate.findAll(TestPojo.class);
		try{
			 listTasks =  new ArrayList<TaskVO>();
		MongoDatabase mongoDBConnection = IGTMongoClient.getMongoDB();
		  MongoCollection<Document> collectionName = mongoDBConnection.getCollection("IGT_TASK_DETAILS");
		  FindIterable<Document> taskVOs = collectionName.find(new Document("_id", new ObjectId(id)));
		  for (Document document : taskVOs) {
				 TaskVO taskVO1 = new TaskVO();
				 taskVO1.setTaskName(document.get("taskName").toString());
				 taskVO1.setTaskStatus(document.get("taskStatus").toString());
				 taskVO1.setPersonName(document.get("personName").toString());
				 taskVO1.setId(document.get("_id").toString());
				 listTasks.add(taskVO1);
			}
		  if(listTasks!= null && listTasks.size()>0){
			  taskVO = listTasks.get(0);
		  }else{
			  taskVO = new TaskVO();
		  }
		  
		}catch(Exception e){
			e.printStackTrace();
		}
		  return taskVO;
	}

}
