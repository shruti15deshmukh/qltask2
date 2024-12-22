package stu.com.springboot.studentMgmt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name= "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="roll_no", unique= true)
    private String roll;
    @Column(name="phone_number")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")


    private Section section;

    public Student() {
    }
    public Student(String name, String roll, String phone, Section section) {
        this.name = name;
        this.roll = roll;
        this.phone = phone;
        this.section = section;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
