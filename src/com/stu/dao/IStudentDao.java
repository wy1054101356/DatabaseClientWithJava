package com.stu.dao;


import java.util.List;

import com.stu.model.Student;

public interface IStudentDao {
	public List<Student> getAllStudent();

	public Student getStudent(String sno);

	public boolean findStudent(String sno);

	public boolean insertStudent(Student stu);

	public boolean updateStudent(Student stu);

	public boolean deleteStudent(String sno);
}
