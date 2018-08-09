package pers.xiaoming.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
@Slf4j
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private DiscoveryClient client;

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

    @RequestMapping(value = "/discovery")
    @GetMapping
    public Object discovery()
    {
        List<String> list = client.getServices();
        log.info("Discover Service list : {}", list);

        List<ServiceInstance> srvList = client.getInstances("SPRINGCLOUD-WEBSERVICE");
        for (ServiceInstance element : srvList) {
            log.info(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
}
