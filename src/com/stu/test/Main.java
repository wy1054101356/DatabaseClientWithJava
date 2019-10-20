package com.stu.test;

import java.util.List;

import com.stu.model.Student;
import com.stu.util.DaoFactory;

public class Main {

	public static void main(String[] args) {
		// ��ʾ���
		displayStudents("ԭʼ");

		Student stu = new Student("9512104", "����", "��", 20, "�����ϵ");
		// ��������֮ǰ��Ҫ���ж�ѧ���Ƿ��ظ�
		if (DaoFactory.getStudentDao().findStudent(stu.getSno())) {
			System.out.println("ϵͳ��Ϣ��ѧ��Ϊ " + stu.getSno() + " ��ѧ����Ϣ�Ѿ����ڣ�");
			return;
		}

		// ����ѧ������
		if (DaoFactory.getStudentDao().insertStudent(stu))
			System.out.println("ϵͳ��Ϣ�����ݲ���ɹ���");

		// ��ʾ���
		displayStudents("����");

		// �޸�ѧ������
		stu.setSage(18);
		if (DaoFactory.getStudentDao().updateStudent(stu))
			System.out.println("ϵͳ��Ϣ�������޸ĳɹ���");

		// ��ʾ���
		displayStudents("�޸�");

		// ɾ��ѧ������
		if (DaoFactory.getStudentDao().deleteStudent("9512104"))
			System.out.println("ϵͳ��Ϣ������ɾ���ɹ���");

		// ��ʾ���
		displayStudents("ɾ��");
	}

	private static void displayStudents(String state) {
		List<Student> students = DaoFactory.getStudentDao().getAllStudent();
		System.out.println("**************************************");
		System.out.println(state + "ѧ����¼����Ϊ��");
		for (Student s : students) {
			System.out.print("ѧ�ţ�" + s.getSno());
			System.out.print(" ����:" + s.getSname());
			System.out.print(" �Ա� " + s.getSsex());
			System.out.print(" ���䣺" + s.getSage());
			System.out.println(" ϵ����" + s.getSdept());
		}
	}

}
