package pers.xiaoming.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pers.xiaoming.springcloud.entity.Student;
import pers.xiaoming.springcloud.hystrix.http.GetStudentById;

import java.util.List;

@RestController
@RequestMapping("/student_consumer/hystrix")
public class StudentConsumerController {

    private static final String REST_URL_HOST = "http://localhost:8001";
    private static final String STUDENT_RESOURCE = "/student";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    @PostMapping
    @HystrixCommand(fallbackMethod = "processHystrixPost")
    public ResponseEntity<Student> post(@RequestBody Student student) {
        if (student == null || student.getName() == null) {
            throw new RuntimeException("Invalid student input");
        }
        restTemplate.postForObject(REST_URL_HOST + STUDENT_RESOURCE, student, Student.class);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Fall back method must receive the same signature (return type, parameters) as the trigger method
    // It shouldn't be a exception handler, here is just an example
    public ResponseEntity<Student> processHystrixPost(@RequestBody Student student) {
        return new ResponseEntity<>(student, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/id/{id}")
    @GetMapping
    public Student get(@PathVariable("id") int id) {
        return new GetStudentById(restTemplate, id).execute();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    @GetMapping
    public List<Student> getAll() {
        return restTemplate.getForObject(REST_URL_HOST + STUDENT_RESOURCE + "/all", List.class);
    }

    @RequestMapping(value = "/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_HOST + STUDENT_RESOURCE + "/discovery", Object.class);
    }
}
