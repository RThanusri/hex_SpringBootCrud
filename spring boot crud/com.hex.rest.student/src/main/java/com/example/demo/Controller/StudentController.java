package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Exception.IdNotFoundException;
import com.example.demo.Service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	
	@Autowired
	StudentService ser;
	
	@PostMapping("/saveData")
	public ResponseEntity<Student> saveData(@Valid @RequestBody Student s)
	{
		
		Student s2=ser.saveData(s);
		if(s2==null)
		{
			return new ResponseEntity<>(s2,HttpStatus.NOT_FOUND);
		}
		else
			
		{
			return new ResponseEntity<>(s2,HttpStatus.CREATED);
			
		}
		
		
	}
	@GetMapping("/getDatas")
	public ResponseEntity<List<Student> >getDatas()
	{
		List<Student> list=ser.getDatas();
		if( list.isEmpty()
				)
		{
			return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		
	}
	@GetMapping("/getDataByName/{name}")
	public ResponseEntity<List<Student> >getDataByName(@PathVariable  String name )
	{
		List<Student> list=ser.FindDataByName(name);
		if( list.isEmpty()
				)
		{
			return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getDataByAgeBetween/{age1}/{age2}")
	public ResponseEntity<List<Student> >getDataByAgeBetween(@PathVariable  int age1 ,@PathVariable int age2)
	{
		List<Student> list=ser.FindByAgeBetween(age1, age2);
		if( list.isEmpty()
				)
		{
			return new ResponseEntity<>(list,HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
	}
	@DeleteMapping("/removeData/{rollNo}")
	public Student removeData(@PathVariable int rollNo)
	{
		Student s=ser.removedata(rollNo);
		return s;
	}
	@PutMapping("/updateData/{rollNo}/{name}")
	public ResponseEntity<String> updateData(@PathVariable int rollNo, @PathVariable String name)
	{
		
		String r= ser.updateData(rollNo,name);
		if(r==null)
		{
			return new ResponseEntity<>(r,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			
			return new ResponseEntity<>(r,HttpStatus.OK);
		}
	}
	@GetMapping("/getData/{rollNo}")
	 public ResponseEntity<Student> getStudent(@PathVariable int rollNo) {
	        try {
	            Student student = ser.getData(rollNo);
	            return new ResponseEntity<>(student, HttpStatus.OK);
	        } catch (IdNotFoundException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
}
}
