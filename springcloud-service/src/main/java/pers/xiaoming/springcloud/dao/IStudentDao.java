package pers.xiaoming.springcloud.dao;

import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

public interface IStudentDao {
    Student create(Student student);

    Student selectById(int id);

    List<Student> selectAll();
}
