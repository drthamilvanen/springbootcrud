package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public void registerStudent(Student student) {
        Optional<Student> byEmail = studentRepo.findStudentByEmail(student.getEmail());
        if(byEmail.isPresent())
            throw new IllegalStateException("email taken");
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean isThere = studentRepo.existsById(studentId);
        if(!isThere)
                throw new IllegalStateException("Student ID : " + studentId +" is incorrect");
        else
            studentRepo.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long stud,String name,String email) {
        Student student = studentRepo.findById(stud)
                .orElseThrow(()->new IllegalStateException("Student with Id" + stud + "not exist"));

        if(name !=null &&
                name.length()>0 &&
        !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email !=null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email)){
            Optional<Student> student1 = studentRepo.findStudentByEmail(email);
            if(student1.isPresent()){
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);

        }
    }
}
