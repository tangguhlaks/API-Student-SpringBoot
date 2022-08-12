package com.laksdev.training.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
       if (studentByEmail.isPresent()){
           throw new IllegalStateException("email taken");
       }
       studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("student with id "+id+" does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalStateException("Student not found"));
        if (name != null && name.length() > 0 && !name.equals(student.getName())){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !email.equals(student.getEmail())){
            Optional<Student> studentEmail = studentRepository.findStudentByEmail(email);
            if (!studentEmail.isPresent()){
                student.setEmail(email);
            }else{
                throw new IllegalStateException("email taken");
            }
        }
    }
}
