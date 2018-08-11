package pers.xiaoming.springcloud.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

@Component
@Slf4j
public class StudentConsumerServiceFallbackFactory implements FallbackFactory<StudentConsumerService> {
    @Override
    public StudentConsumerService create(Throwable throwable) {

        return new StudentConsumerService() {
            @Override
            public Student create(Student student) {
                log.error("Fail fast with 500 and return anonymous user");
                return new Student("", 0.0);
            }

            @Override
            public Student select(int id) {
                log.error("Fall back and return blank student");
                return new Student("", 0.0);            }

            @Override
            public List<Student> selectAll() {
                log.error("Fall back and return blank student");
                return null;            }

            @Override
            public Object discovery() {
                return null;
            }
        };
    }

}
