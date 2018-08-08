package pers.xiaoming.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

@Mapper
public interface IStudentDao {
    Student create(Student student);

    Student selectById(int id);

    List<Student> selectAll();
}
