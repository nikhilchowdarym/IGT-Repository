package com.igt.poc.service;

import java.util.List;

import com.igt.poc.vo.TaskVO;

public interface TaskService {
	
	public List<TaskVO> getAllTasks();
	
	public TaskVO insertTask(TaskVO testPojo);
	
	public boolean updateTask(TaskVO testPojo);
		
	public boolean deleteTasksByName(String name);
	
	public TaskVO findById(String id);

}
