package com.stu.util;

import com.stu.dao.IStudentDao;

import com.stu.dao.impl.StudentDaoImpl;

public class DaoFactory {
	public static IStudentDao getStudentDao() {
		return new StudentDaoImpl();
	}

}
