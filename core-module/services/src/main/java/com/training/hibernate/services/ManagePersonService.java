package com.training.hibernate.services;

import com.training.hibernate.model.*;
import com.training.hibernate.dao.*;
import java.util.*;

public class ManagePersonService {
	private PersonDao p = new PersonDao();
	private AddressDao a = new AddressDao();
	private ContactDao c = new ContactDao();

	public List<Person> getPersons(String command, Object... args){
		return p.getPersons(command, args);
	}

	public void execute(String command, Object... args){
		p.execute(command, args);
	}

	public Person getPersonById(int id) {
		return p.getPersonById(id);
	}

	public Address getAddressById(int id){
		return a.getAddressById(id);
	}

	public void updateAddress(Address address){
		a.updateAddress(address);
	}

	public Set<Contact> getContactsByPersonId(int id){
		return c.getContactsByPersonId(id);
	}

	public void updatePersonContact(int id, Contact contact){
		p.updatePersonContact(id, contact);
	}

	public void deleteContactById(int id){
		c.deleteContactById(id);
	}

	public List<Role> getRoles(){
		return p.getRoles();
	}

	public Set<Role> getPersonRoles(int id){
		return p.getPersonRoles(id);
	}

}