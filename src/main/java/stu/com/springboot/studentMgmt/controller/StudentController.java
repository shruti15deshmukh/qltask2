package stu.com.springboot.studentMgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stu.com.springboot.studentMgmt.entity.Section;
import stu.com.springboot.studentMgmt.entity.Student;
import stu.com.springboot.studentMgmt.repository.SecR;
import stu.com.springboot.studentMgmt.repository.StuR;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StuR stuR;
    @Autowired
    private SecR secR;


    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            if (student == null) {
                throw new NullPointerException("Student details cannot be null");
            }
            return ResponseEntity.ok(stuR.save(student));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @GetMapping
    public ResponseEntity<List<Student>> getStudents()

    {
        return ResponseEntity.ok(stuR.findAll());
    }
//    @GetMapping("/by-section")
//    public ResponseEntity<List<Student>> getStudentsBySection(@RequestParam String sec)
//    {
//
//        Section section = secR.findBySec(sec);
//        if (section != null) {
//
//            return ResponseEntity.ok(stuR.findBySection(section));
//        }
//        return ResponseEntity.notFound().build();
//    }
    @GetMapping("/by-section")
    public ResponseEntity<List<Student>> getStudentsBySection(@RequestParam String sec) {
        try {
            if (sec == null) {
                throw new NullPointerException("Section cannot be null");
            }
            Section section = secR.findBySec(sec);
            if (section == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found");
            }
            return ResponseEntity.ok(stuR.findBySection(section));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        try {
            if (!stuR.existsById(id)) {
                throw new IllegalArgumentException("Student ID not found: " + id);
            }
            stuR.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student theStudent) {
        try {
            if (!stuR.existsById(id)) {
                throw new IllegalArgumentException("Student ID not found: " + id);
            }
            return stuR.findById(id)
                    .map(student -> {
                        student.setName(theStudent.getName());
                        student.setRoll(theStudent.getRoll());
                        student.setPhone(theStudent.getPhone());
                        student.setSection(theStudent.getSection());
                        return ResponseEntity.ok(stuR.save(student));
                    })
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }



}
