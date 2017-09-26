package com.yogesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yogesh.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory 
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try{
			//use the session object to save the Java Object
			
			//create the 3 student objects
			System.out.println("Creating the student object : ");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");
			
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the Student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			// find out the student's id : primary key
			System.out.println("Saved student. Generated id :" + tempStudent.getId());
			
			//now get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting stduent with id : "+ tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete :"+ myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");			
		}
		
		finally{
			factory.close();
		} 

	}

}
