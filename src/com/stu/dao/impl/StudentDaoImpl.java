package com.stu.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stu.dao.IStudentDao;
import com.stu.model.Student;
import com.stu.util.DatabaseBean;

public class StudentDaoImpl implements IStudentDao {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<Student>();
		try {
			conn = DatabaseBean.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select sno,sname,ssex,sage,sdept from student");
			while (rs.next()) {
				Student stu = new Student();
				stu.setSno(rs.getString("sno"));
				stu.setSname(rs.getString("sname"));
				stu.setSsex(rs.getString("ssex"));
				stu.setSage(rs.getInt("sage"));
				stu.setSdept(rs.getString("sdept"));
				students.add(stu);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseBean.close(rs, stmt, conn);
		}
		return students;
	}

	public Student getStudent(String sno) {
		Student stu = new Student();
		try {
			conn = DatabaseBean.getConnection();
			psmt = conn.prepareStatement("select sno,sname,ssex,sage,sdept from student where sno=?");
			psmt.setString(1, sno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				stu.setSno(rs.getString("sno"));
				stu.setSname(rs.getString("sname"));
				stu.setSsex(rs.getString("ssex"));
				stu.setSage(rs.getInt("sage"));
				stu.setSdept(rs.getString("sdept"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseBean.close(rs, psmt, conn);
		}
		return stu;
	}

	public boolean findStudent(String sno) {
		Student isFindStudent = getStudent(sno);
		if(isFindStudent.getSno() == null)
			return false;
		else
			return true;
	}

	public boolean insertStudent(Student stu) {
		try {
			String sql = "insert into student(sno,sname,ssex,sage,sdept) values(?,?,?,?,?)";
			conn = DatabaseBean.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stu.getSno());
			psmt.setString(2, stu.getSname());
			psmt.setString(3, stu.getSsex());
			psmt.setInt(4, stu.getSage());
			psmt.setString(5, stu.getSdept());
			psmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DatabaseBean.close(rs, stmt, conn);
		}
		return false;
	}

	public boolean updateStudent(Student stu) {
		boolean isUpdate = false;

		Connection conn = null;
		Statement stmt = null;
		
		String sql = "update " + "student" + " set " 
				+ "Sname='" + stu.getSname()+  "',"
				+ "SSex='"+ stu.getSsex() +    "'," 
				+ "Sage='" + stu.getSage() +   "'," 
				+ "Sdept='" + stu.getSdept() + "' " 
				+ "where SNO='" + stu.getSno() + "'";
		try {
			conn = DatabaseBean.getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			// 将操作数据影响的行数输出
			System.out.println("修改数据的行数为：" + count);
			isUpdate = true;
		} catch (SQLException ex) {
			isUpdate = false;
			ex.printStackTrace();
		} finally {
			DatabaseBean.close(null, stmt, conn);
		}
		return isUpdate;
	}

	@Override
	public boolean deleteStudent(String sno) {
		boolean isDelete = false;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "delete from sc where sno='" + sno + "'";
		
		try {
			conn = DatabaseBean.getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			isDelete = true;
			// 将操作数据影响的行数输出
			System.out.println("删除数据的行数为：" + count);
		} catch (SQLException ex) {
			ex.printStackTrace();
			isDelete = false;
		} finally {
			DatabaseBean.close(null, stmt, conn);
		}

		return isDelete;

	}

}
