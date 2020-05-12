package prabha.samplePOC.main.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import prabha.samplePOC.main.dao.StudentDao;
import prabha.samplePOC.main.interfaceclass.StudentInterface;

@Component
public class StudentServices implements StudentInterface{
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public  void insertStudentDetails(JsonNode data) {		
		 this.studentDao.insertStudentDetails(data);
	}

	@Override
	public boolean isStudentExist(int id) {
		return this.studentDao.isStudentExist(id);		
	}
	
	@Override
	public void updateStudentDetails(JsonNode data) {
		this.studentDao.updateStudentDetails(data);
	}

	@Override
	public List<Map<String, Object>> getStudentDetails() {		
		return this.studentDao.getStudentDetails();
	}

	@Override
	public void deleteStudentDetails(int id) {
		this.studentDao.deleteStudentDetails(id);		
	}
	

}
