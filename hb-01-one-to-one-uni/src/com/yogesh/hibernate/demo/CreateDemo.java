package com.yogesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.yogesh.hibernate.demo.entity.Instructor;
import com.yogesh.hibernate.demo.entity.InstructorDetail;
import com.yogesh.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create a session factory 
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try{
			//use the session object to save the Java Object
			
			//create the object 
//	Instructor tempInstructor = new Instructor("Yogesh", "Darji", "yogesh.darji@gmail.com");
//	
//	InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","Love to code");
	
	Instructor tempInstructor = new Instructor("Kalyani", "Nirmal", "yogesh.darji@gmail.com");
	
	InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","Programming");
	
	
			//associate object
	tempInstructor.setInstructorDetail(tempInstructorDetail);		
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will ALSO save the details object 
			//because of CascadeType.ALL
			
			System.out.println("Saving instructor : "+ tempInstructor);
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");			
		}
		
		finally{
			factory.close();
		}
		
		
	}

}
