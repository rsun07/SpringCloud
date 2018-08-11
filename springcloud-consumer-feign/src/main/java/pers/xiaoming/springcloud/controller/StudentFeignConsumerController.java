package pers.xiaoming.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pers.xiaoming.springcloud.entity.Student;
import pers.xiaoming.springcloud.service.StudentConsumerService;

import java.util.List;

@RestController
@RequestMapping("/student_consumer/feign")
public class StudentFeignConsumerController {
    @Autowired
    private StudentConsumerService service;

    @RequestMapping
    @PostMapping
    public Student post(@RequestBody Student student) {
        return this.service.create(student);
    }

    @RequestMapping(value = "/id/{id}")
    @GetMapping
    public Student get(@PathVariable("id") int id) {
        return this.service.select(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    @GetMapping
    public List<Student> getAll() {
        return this.service.selectAll();
    }

    @RequestMapping(value = "/discovery")
    public Object discovery() {
        return this.service.discovery();
    }
}
