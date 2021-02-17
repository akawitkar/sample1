package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Student;
import com.example.repository.StudentRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository ;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

	@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) throws ResourceNotFoundException{
		Optional<Student> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new ResourceNotFoundException("id-" + id);

		return student.get();
	}
    
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }


}