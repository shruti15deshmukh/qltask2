package stu.com.springboot.studentMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stu.com.springboot.studentMgmt.entity.Section;

public interface SecR extends JpaRepository<Section, Integer> {
    Section findBySec(String sec);



}
