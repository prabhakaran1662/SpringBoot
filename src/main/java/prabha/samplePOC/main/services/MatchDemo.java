package prabha.samplePOC.main.services;

import java.util.List;
import java.util.function.Predicate;

public class MatchDemo {

	 public static void main(String[] args) {
	    
	     List<Employee> list = Employee.getEmpList();
	     //using allMatch
	     boolean b1 = list.stream().anyMatch(e -> !e.ststus);
	     System.out.println(b1);
	  
	  }    
}
