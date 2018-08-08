package pers.xiaoming.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xiaoming.springcloud.dao.IStudentDao;
import pers.xiaoming.springcloud.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private IStudentDao dao;

    @Override
    public Student create(Student student) {
        return dao.create(student);
    }

    @Override
    public Student get(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Student> getAll() {
        return dao.selectAll();
    }
}
