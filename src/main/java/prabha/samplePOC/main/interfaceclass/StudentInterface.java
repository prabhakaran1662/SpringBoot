package prabha.samplePOC.main.interfaceclass;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public interface StudentInterface {

	public void insertStudentDetails(JsonNode data);
	public void updateStudentDetails(JsonNode data);
	public List<Map<String, Object>> getStudentDetails();
	public void deleteStudentDetails(int id);
	public boolean isStudentExist(int id);


}
