package com.luc.demo.services;

import com.luc.demo.model.Student;
import com.luc.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByName = studentRepository.findByName(student.getName());

        if (studentByName.isPresent())
            throw new IllegalStateException("student with name: " + student.getName() + " already exists");

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean existsById = studentRepository.existsById(studentId);
        if (!existsById) throw new IllegalStateException("Student with id:" + studentId + " does not exists");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String lastName) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id:" + studentId + " does not exists"));

        if (name != null && name.length() != 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (lastName != null && lastName.length() != 0 && !Objects.equals(student.getName(), lastName)) {
            student.setLastName(lastName);
        }
    }
}
