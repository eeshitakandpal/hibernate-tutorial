package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		// use session object to save Java object
		try {
			
			// start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list(); 
		
			//display the students
			displayStudents(theStudents);
			
			
			
			//query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList(); 
			
			//display the students
			System.out.println("\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList(); 
			
			//display the students
			System.out.println("\nStudents who have last name of Doe  OR first name ofDaffy");
			displayStudents(theStudents);
			
			
			
			
			//query students: email LIKE '%luv2code.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList(); 
					
			//display the students
			System.out.println("\nStudents who have email ending with '%luv2code.com'");
			displayStudents(theStudents);

			
			
			//query students: email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList(); 
					
			//display the students
			System.out.println("\nStudents who have email ending with '%gmail.com'");
			displayStudents(theStudents);

			
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
