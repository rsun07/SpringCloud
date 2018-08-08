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

import java.util.List;

@RestController
@RequestMapping("/student_consumer")
public class StudentConsumerController {

    private static final String REST_URL_HOST = "http://localhost:8001";
    private static final String STUDENT_RESOURCE = "/student";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    @PostMapping
    public Student post(@RequestBody Student student) {
        return restTemplate.postForObject(REST_URL_HOST + STUDENT_RESOURCE, student, Student.class);
    }

    @RequestMapping(value = "/id/{id}")
    @GetMapping
    public Student get(@PathVariable("id") int id) {
        return restTemplate.getForObject(REST_URL_HOST + STUDENT_RESOURCE + "/id/" + id, Student.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    @GetMapping
    public List<Student> getAll() {
        return restTemplate.getForObject(REST_URL_HOST + STUDENT_RESOURCE + "/all", List.class);
    }
}
