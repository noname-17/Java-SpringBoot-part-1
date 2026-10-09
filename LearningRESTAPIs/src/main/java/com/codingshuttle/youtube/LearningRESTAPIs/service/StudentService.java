package com.codingshuttle.youtube.LearningRESTAPIs.service;

import com.codingshuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.DTO.StudentDto;


import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getAllStudentById(long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(long id);

    StudentDto updateStudent(long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(long id, Map<String, Object> updates);
}
