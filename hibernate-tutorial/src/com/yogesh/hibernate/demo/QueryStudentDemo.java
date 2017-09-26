package com.yogesh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yogesh.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory 
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try{
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			
			//display the students
			displayStudents(theStudents);
			
			
			//query students: lastname="Doe"
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			System.out.println("Student who have last name as Doe");
			displayStudents(theStudents);
			
			
			//query students : lastname='Doe' OR firstName='Daffy
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("Student who have last name as Doe or firstName as Daffy");
			displayStudents(theStudents);
			
			
			//query students : where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("Student who have email of gmail");
			displayStudents(theStudents);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");			
		}
		
		finally{
			factory.close();
		} 

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student temStudent: theStudents){
			System.out.println(temStudent);
		}
	}

}
