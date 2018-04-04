package com.igt.poc.dao;

import java.util.List;

import com.igt.poc.vo.TaskVO;

public interface TaskDao {
	
	public List<TaskVO> getAllTasks();
		
	public TaskVO insertTask(TaskVO testPojo);
	
	public boolean updateTask(TaskVO testPojo);
		
	public boolean deleteTaskByName(String name);
	public TaskVO findById(String id);

}
