package stu.com.springboot.studentMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.com.springboot.studentMgmt.entity.Section;
import stu.com.springboot.studentMgmt.entity.Student;

import java.util.List;

public interface StuR extends JpaRepository <Student, Integer> {
    List<Student> findBySection(Section section);
}
