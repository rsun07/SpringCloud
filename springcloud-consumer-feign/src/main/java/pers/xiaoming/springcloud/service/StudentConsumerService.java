package pers.xiaoming.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

// Generate http client to call the service
// Just like mybatis ORM create queries based on annotation
@FeignClient(value = "SPRINGCLOUD-WEBSERVICE")
@RequestMapping("/student")
public interface StudentConsumerService {

    @RequestMapping(method = RequestMethod.POST)
    Student create(Student student);

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    Student select(@PathVariable("id") int id);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Student> selectAll();

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    Object discovery();
}
