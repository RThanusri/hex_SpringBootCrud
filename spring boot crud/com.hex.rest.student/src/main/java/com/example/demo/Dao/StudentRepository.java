package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	List<Student> findByNameContaining(String name);
	List<Student> findByAgeBetween(int a1 ,int a2);
	
}
