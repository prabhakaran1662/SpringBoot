package prabha.samplePOC.main.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import prabha.samplePOC.main.interfaceclass.StudentInterface;

@RestController
public class StudentController {
	
	@Autowired
	private StudentInterface studentInterface;
	
	  @Autowired
	    private JdbcTemplate jdbcTemplateTwo;
	
	@RequestMapping(value="/student/", method=RequestMethod.POST)	
	public ResponseEntity<Void> insertStudentDetails(@RequestBody JsonNode data) {
		if (this.studentInterface.isStudentExist(data.get("id").asInt())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else {
		//	this.studentInterface.insertStudentDetails(data);
			String sql="INSERT INTO `school`.`student` (`id`,`name`,`age`,`mark`,`totalmark`) VALUES("+data.get("id").asInt()+",'"+data.get("name").asText()+"',"+data.get("age").asInt()+","+data.get("mark").asInt()+","+data.get("total").asInt()+")";
			jdbcTemplateTwo.execute(sql);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}		 
	}
	
	@RequestMapping(value="/student/", method=RequestMethod.PUT)	
	public ResponseEntity<Void> updateStudentDetails(@RequestBody JsonNode data) {
		if (this.studentInterface.isStudentExist(data.get("id").asInt())) {
			this.studentInterface.updateStudentDetails(data);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		 
	}
	
	@RequestMapping(value="/student/", method=RequestMethod.GET)	
	public ResponseEntity<List<Map<String, Object>>> getStudentDetails() {
		List<Map<String, Object>> result = this.studentInterface.getStudentDetails();
		if(result.size() == 0) {
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);	
		}
	}

	@RequestMapping(value="/student/{studentid}", method=RequestMethod.DELETE)	
	public ResponseEntity<Void> deleteStudentDetails(@PathVariable("studentid") Integer studentId) {
		if (this.studentInterface.isStudentExist(studentId)) {
			this.studentInterface.deleteStudentDetails(studentId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}		 
	}
	
}
