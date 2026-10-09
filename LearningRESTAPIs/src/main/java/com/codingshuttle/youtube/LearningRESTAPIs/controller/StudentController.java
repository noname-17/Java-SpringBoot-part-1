package com.codingshuttle.youtube.LearningRESTAPIs.controller;

import com.codingshuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.DTO.StudentDto;
import com.codingshuttle.youtube.LearningRESTAPIs.entity.student;
import com.codingshuttle.youtube.LearningRESTAPIs.repository.StudentRepository;
import com.codingshuttle.youtube.LearningRESTAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent() {

//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") long id) {
        return ResponseEntity.ok(studentService.getAllStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable long id ,
                                                    @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable long id ,
                                                           @RequestBody Map<String , Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id,updates));
    }

}
