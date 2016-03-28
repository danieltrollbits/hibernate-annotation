package com.training.hibernate.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.*;

@Entity
@Table(name = "PERSON")
public class Person {
	
	private int id;
	private String lastName;
	private String firstName;
	private String middleName;
	private String fullName;
	private Gender gender;
	private Date birthdate;
	private Address address;
	private boolean employed;
	private float gwa;
	private Set<Contact> contacts;
	private Set<Role> roles = new HashSet<Role>(0);

	public Person(){};

	public Person(String lastName, String firstName, String middleName, Gender gender, Date birthdate, boolean employed, float gwa){
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.employed = employed;
		this.gwa = gwa;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	@Column(name = "last_name")
	public String getLastName(){
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	@Column(name = "first_name")
	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	@Column(name = "middle_name")
	public String getMiddleName(){
		return this.middleName;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	@Transient
	public String getFullName(){
		return this.fullName = this.firstName + " " + this.middleName + " " + this.lastName;
	}

	public void setFullName(String firstName, String middleName, String lastName){
		this.fullName = firstName + " " + middleName + " " + lastName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	public Gender getGender(){
		return this.gender;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}

	@Column(name = "birthdate")
	public Date getBirthdate(){
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	}

	@OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Address getAddress(){
		return this.address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	@Column(name = "employed")
	public boolean isEmployed(){
		return this.employed;
	}

	public void setEmployed(boolean employed){
		this.employed = employed;
	}

	@Column(name = "gwa")
	public float getGwa(){
		return this.gwa;
	}

	public void setGwa(float gwa){
		this.gwa = gwa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	public Set<Contact> getContacts(){
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts){
		this.contacts = contacts;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "PERSON_ROLE", joinColumns = { 
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
	public Set<Role> getRoles(){
		return this.roles;
	}

	public void setRoles(Set<Role> roles){
		this.roles = roles;
	}

	public String toString(){
		return "name: " + getFullName() + "gender: " + this.gender + "birthdate: " + this.birthdate;
	}
}