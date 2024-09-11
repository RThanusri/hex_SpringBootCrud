package com.example.demo.Service;




import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentRepository;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.IdNotFoundException;

@Service
public class StudentService {
	@Autowired
	StudentRepository repo;

	public Student saveData(Student s) {
		
		int roll=s.getRollNo();
		Student stud=repo.findById(roll).orElse(null);
		if(stud!=null)
		{
		     return null;
		}
		else {
			repo.save(stud);
			 return stud;
	}
		
	}

	public List<Student> getDatas() {
		List<Student> l =(List<Student>) repo.findAll();
		return l;
	}

	public Student removedata(int rollNo) {
		Student s=repo.findById(rollNo).orElse(null);
		if(s==null)
		{
			System.out.println("NOT FOUND");
		}
		else
		{
			repo.delete(s);
		}
		return s;
		
	}

	public String updateData(int r, String n) {
		Student s1=repo.findById(r).orElse(null);
		if(s1==null)
		{
			return null;
		}
		else
			
			
		{
			
			s1.setName(n);
			
			repo.save(s1);
			return "Name Updated";
		}
	}

		
	
	    
	    
		public Student getData(int rollNo) {
		    return repo.findById(rollNo)
		               .orElseThrow(() -> new IdNotFoundException("Student not found with rollNo: " + rollNo));
		}
		
		public List<Student> FindDataByName(String name)
		{
			List<Student>lis= repo.findByNameContaining(name);
			if(lis.isEmpty())
			{
				 throw new IdNotFoundException("Student(s) not found with name: " + name);
				
			}
			return lis;
		}
		public List<Student>FindByAgeBetween(int a1 , int a2)
		{
			List<Student>lis=repo.findByAgeBetween(a1, a2);
			if(lis.isEmpty())
			{
				 throw new IdNotFoundException("Students not between the given age " + a1 + a2);
				
			}
			return lis;
		}
	}
	

	   
		
	
