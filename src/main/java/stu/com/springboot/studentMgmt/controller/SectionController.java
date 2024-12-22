package stu.com.springboot.studentMgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import stu.com.springboot.studentMgmt.entity.Section;
import stu.com.springboot.studentMgmt.repository.SecR;

@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SecR secR;
    @PostMapping
    public ResponseEntity<Section> addSection(@RequestBody Section section) {
        try {
            if (section == null) {
                throw new NullPointerException("Section details cannot be null");
            }
            return ResponseEntity.ok(secR.save(section));
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }



}
