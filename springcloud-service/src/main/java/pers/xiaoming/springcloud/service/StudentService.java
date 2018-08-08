package pers.xiaoming.springcloud.service;

import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

public interface StudentService {
    Student create(Student student);

    Student get(int id);

    List<Student> getAll();
}
