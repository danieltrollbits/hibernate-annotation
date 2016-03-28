package com.training.hibernate.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CONTACT")
public class Contact {

	private int id;
	private Type type;
	private String value;
	private Person person;

	public Contact(){};

	public Contact(Type type, String value){
		this.type = type;
		this.value = value;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	public Type getType(){
		return this.type;
	}

	public void setType(Type type){
		this.type = type;
	}

	@Column(name = "value")
	public String getValue(){
		return this.value;
	}

	public void setValue(String value){
		this.value = value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	public Person getPerson(){
		return this.person;
	}

	public void setPerson(Person person){
		this.person = person;
	}

	public String toString(){
		return this.type + ": " + this.value;
	}
}