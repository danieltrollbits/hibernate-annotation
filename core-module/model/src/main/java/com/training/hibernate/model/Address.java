package com.training.hibernate.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ADDRESS")
public class Address implements java.io.Serializable {

	private int personId;
	private Person person;
	private String street;
	private int houseNo;
	private String barangay;
	private String subdivision;
	private String city;
	private String zipCode;

	public Address(){};

	public Address(String street, int houseNo, String barangay, String subdivision, String city, String zipCode){
		this.street = street;
		this.houseNo = houseNo;
		this.barangay = barangay;
		this.subdivision = subdivision;
		this.city = city;
		this.zipCode = zipCode;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "person"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "person_id", unique = true, nullable = false)
	public int getPersonId(){
		return this.personId;
	}

	public void setPersonId(int personId){
		this.personId = personId;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
	public Person getPerson(){
		return this.person;
	}

	public void setPerson(Person person){
		this.person = person;
	}

	@Column(name = "street")
	public String getStreet(){
		return this.street;
	}

	public void setStreet(String street){
		this.street = street;
	}

	@Column(name = "house_no")
	public int getHouseNo(){
		return this.houseNo;
	}

	public void setHouseNo(int houseNo){
		this.houseNo = houseNo;
	}

	@Column(name = "barangay")
	public String getBarangay(){
		return this.barangay;
	}

	public void setBarangay(String barangay){
		this.barangay = barangay;
	}

	@Column(name = "subdivision")
	public String getSubdivision(){
		return this.subdivision;
	}

	public void setSubdivision(String subdivision){
		this.subdivision = subdivision;
	}

	@Column(name = "city")
	public String getCity(){
		return this.city;
	}

	public void setCity(String city){
		this.city = city;
	}

	@Column(name = "zip_code")
	public String getZipCode(){
		return this.zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String toString(){
		return this.houseNo + " " + this.street + " " + this.subdivision + " " + this.city; 
	}
}