package com.igt.poc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.igt.poc.service.TaskService;
import com.igt.poc.service.UserService;
import com.igt.poc.vo.TaskVO;
import com.igt.poc.vo.UserVo;

@RestController
public class TestController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	private final Logger logger = Logger.getLogger(TestController.class);
	
	
	@RequestMapping(value="inserTask",method=RequestMethod.POST)
	public @ResponseBody List<TaskVO> inserTask(@RequestBody TaskVO data){
		List<TaskVO> taskVOLst = null;
		TaskVO taskVO;
		try {
			taskVOLst = new ArrayList<TaskVO>();
			logger.info("in TestController.inserTask()::");	
			data.setCreateDate(new Date());
			data.setRecordStatus("1");
			taskVO = taskService.insertTask(data);
			taskVOLst.add(taskVO);
			taskVOLst = GetAllTasks();
		} catch (Exception e) {
			e.printStackTrace();
			taskVOLst.add(new TaskVO());
		}
		return taskVOLst;
	}
	@RequestMapping(value="inserTask",method=RequestMethod.GET)
	public @ResponseBody List<TaskVO> inserTaskOnload(){
		List<TaskVO> taskVOLst = null;
		TaskVO taskVO;
		try {
			taskVOLst = new ArrayList<TaskVO>();
			taskVOLst = GetAllTasks();
		} catch (Exception e) {
			e.printStackTrace();
			taskVOLst.add(new TaskVO());
		}
		return taskVOLst;
	}
	
	
	
	@RequestMapping(value = "getAllTasks", method = RequestMethod.GET)
	public List<TaskVO> GetAllTasks(){
		
		logger.info("in TestController.GetAllTasks()::");
		List<TaskVO> clients = taskService.getAllTasks();
		return clients;
	}
	
	
	@RequestMapping(value = "updateTask", method = RequestMethod.POST)
	public List<TaskVO> updateTask(@RequestBody TaskVO data ){
		List<TaskVO> taskVOLst = new ArrayList<TaskVO>();		
		taskService.updateTask(data);
		taskVOLst = GetAllTasks();
		return taskVOLst;
	}
	
	@RequestMapping(value = "/deleteTaskByName", method = RequestMethod.POST)
	public List<TaskVO> deleteClientsByNumber( @RequestBody String taskName ){
		List<TaskVO> taskVOLst = new ArrayList<TaskVO>();
		taskService.deleteTasksByName(taskName);
		taskVOLst = GetAllTasks();
		return taskVOLst;
	}
	
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public TaskVO findById( @RequestBody String id ){
		TaskVO taskVO = taskService.findById(id);
		return taskVO;
	}
	
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody UserVo userVo ){
		logger.info("in TestController.updateTask()::");
		Boolean flag = userService.findByUserNamePassword(userVo.getUserName(),userVo.getPassword());
		
		String message = "";
		if(flag){
			message="{\"message\":\""+userVo.getUserName()+"\"}";
			return message;
			
		}
		else{
			message="{\"message\":\"Invalid Credentials\"}";
			return message;
		}
		
	}

	
      

}
