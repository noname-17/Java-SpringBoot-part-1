package com.codingshuttle.youtube.LearningRESTAPIs.service.impl;

import com.codingshuttle.youtube.LearningRESTAPIs.DTO.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.DTO.StudentDto;
import com.codingshuttle.youtube.LearningRESTAPIs.entity.student;
import com.codingshuttle.youtube.LearningRESTAPIs.repository.StudentRepository;
import com.codingshuttle.youtube.LearningRESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<StudentDto> getAllStudents() {

        List<student> students=studentRepository.findAll();
//        List<StudentDto> studentDtoList=students
//                .stream()
//                .map(student -> new StudentDto(student.getId(),student.getName(),student.getEmail()))
//                .toList();

        return students
                .stream()
                .map(student -> new StudentDto(student.getId(),student.getName(),student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getAllStudentById(long id) {
        student student= studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("student not found with"+id));
        StudentDto studentDto=modelMapper.map(student,StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        student newstudent = modelMapper.map(addStudentRequestDto, student.class);
        student student= studentRepository.save(newstudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist by id: "+id);

        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(long id, AddStudentRequestDto addStudentRequestDto) {
        student student= studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("student not found with"+id));
        modelMapper.map(addStudentRequestDto,student);
        student=studentRepository.save(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(long id, Map<String, Object> updates) {
        student student= studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("student not found with"+id));
        updates.forEach((field,value)->{
            switch (field){
                case "name":student.setName((String) value);
                break;
                case "email":student.setEmail((String) value);
                break;
                default:throw new IllegalArgumentException("field is not supported");
            }
        });
        student savestudent=studentRepository.save(student);
        return modelMapper.map(savestudent,StudentDto.class);
    }


}
