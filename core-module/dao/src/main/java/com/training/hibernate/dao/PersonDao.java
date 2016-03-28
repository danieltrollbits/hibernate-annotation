package com.training.hibernate.dao;

import com.training.hibernate.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.training.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.*;
import java.io.*;

public class PersonDao {

	public List<Person> getPersons(String str, Object... args){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
		List<Person> persons = new ArrayList<>(); 
		try{
			tx = session.beginTransaction();
			Criteria cr = null;
			switch(str){
				case "all":
					cr = session.createCriteria(Person.class);
					persons = cr.list();
				break;
				case "byLastName":
					cr = session.createCriteria(Person.class);
					cr.add(Restrictions.eq("lastName",(String)args[0]));
					persons = cr.list();
				break;
				default: break;
			}
			
		}catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return persons;
	}

	public void execute(String command, Object... args){
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			switch(command){
				case "add":
					session.save((Person)args[0]);
				break;
				case "update":
					session.update((Person)args[0]);
				case "deleteById":
					Integer id = (Integer) args[0];
					Person person = (Person)session.get(Person.class, id.intValue());
					session.delete(person);
				break;
				default: break;		
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			if(transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public Person getPersonById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Person person = null;
		try{
			tx = session.beginTransaction();
			String hql = "from Person where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id",id);
			person = (Person) query.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return person;
	}

	public void updatePersonContact(int id, Contact contact){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Person person = (Person)session.get(Person.class, id);
			contact.setPerson(person);
			person.getContacts().add(contact);
			session.update(person);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}	
	}

	public List<Role> getRoles(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
		List<Role> roles = new ArrayList<>();
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Role.class);
			roles = cr.list();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return roles;
	}

	public Set<Role> getPersonRoles(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
		Set<Role> roles = new HashSet<>();
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Person.class);
			cr.add(Restrictions.eq("id",id));
			Person person = (Person)cr.uniqueResult();
			roles = person.getRoles();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return roles;	
	}
}