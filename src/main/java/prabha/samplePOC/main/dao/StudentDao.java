package prabha.samplePOC.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class StudentDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplateOne;

    @Autowired
    private JdbcTemplate jdbcTemplateTwo;
    
    public void insertStudentDetails(JsonNode data) {
		try {
			String sql="INSERT INTO `school`.`student` (`id`,`name`,`age`,`mark`,`totalmark`) VALUES("+data.get("id").asInt()+",'"+data.get("name").asText()+"',"+data.get("age").asInt()+","+data.get("mark").asInt()+","+data.get("total").asInt()+")";
			this.jdbcTemplateOne.execute(sql);
			insertEmployeeDetails(data);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertEmployeeDetails(JsonNode data) {
		try {
			String sql="INSERT INTO `office`.`employee` (`id`,`name`,`age`,`mark`,`totalmark`) VALUES("+data.get("id").asInt()+",'"+data.get("name").asText()+"',"+data.get("age").asInt()+","+data.get("mark").asInt()+","+data.get("total").asInt()+")";
			this.jdbcTemplateTwo.execute(sql);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public boolean isStudentExist(int id) {
		try {
			String sql ="SELECT COUNT(*) FROM school.student where id="+id+"";
			int count = this.jdbcTemplateOne.queryForObject(sql, new Object[] {}, Integer.class);
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateStudentDetails(JsonNode data) {
		try {
			String sql="UPDATE `school`.`student` SET `name` = '"+data.get("name").asText()+"',`age` = "+data.get("age").asInt()+",`mark` = "+data.get("mark").asInt()+",`totalmark` = "+data.get("totalmark").asInt()+"	WHERE `id` = "+data.get("id").asInt()+"";
			this.jdbcTemplateOne.update(sql);
		}catch (DataAccessException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public List<Map<String, Object>> getStudentDetails() {
		try {
			String sql ="SELECT * FROM school.student";
			List<Map<String, Object>> studentList = this.jdbcTemplateOne.queryForList(sql);
			return studentList;
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteStudentDetails(int id) {
		try {
			String sql="Delete from school.student where id="+id+"";
			this.jdbcTemplateOne.execute(sql);
		}catch (DataAccessException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
