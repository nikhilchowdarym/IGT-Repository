package com.igt.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igt.poc.dao.TaskDao;
import com.igt.poc.vo.TaskVO;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao testDao;
	
	public List<TaskVO> getAllTasks() {
		
		return testDao.getAllTasks();
	}
 
	public TaskVO insertTask(TaskVO testPojo) {
		return testDao.insertTask(testPojo);
	}
 
	public boolean updateTask(TaskVO testPojo) {
		return testDao.updateTask(testPojo);
	}
  
	public boolean deleteTasksByName(String taskName){
		return testDao.deleteTaskByName(taskName);
	}

	@Override
	public TaskVO findById(String id) {
		
		return testDao.findById(id);
	}
}
