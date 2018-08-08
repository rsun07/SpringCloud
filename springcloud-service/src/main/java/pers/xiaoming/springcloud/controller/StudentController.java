package pers.xiaoming.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xiaoming.springcloud.entity.Student;
import pers.xiaoming.springcloud.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @RequestMapping
    @PostMapping
    public Student post(@RequestBody Student student) {
        service.create(student);
        return student;
    }

    @RequestMapping("/id/{id}")
    @GetMapping
    public Student get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @RequestMapping("/all")
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
}
