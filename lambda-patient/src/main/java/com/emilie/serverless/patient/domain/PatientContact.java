package com.emilie.serverless.patient.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@DynamoDBTable(tableName = "Utilize DynamoDBMapperConfig")
@JsonIgnoreProperties(ignoreUnknown = true) 
public class PatientContact {

	private String id;

	 @DynamoDBHashKey(attributeName = "id")
	    @DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 @DynamoDBAttribute(attributeName = "firstname")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	 @DynamoDBAttribute(attributeName = "lastname")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 @DynamoDBAttribute(attributeName = "emailaddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	 @DynamoDBAttribute(attributeName = "zipcode")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	 @DynamoDBAttribute(attributeName = "contact")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String zipcode;
	private String contact;
	private String contactPreference;
	 @DynamoDBAttribute(attributeName = "contactPreference")
	public String getContactPreference() {
		return contactPreference;
	}
	public void setContactPreference(String contactPreference) {
		this.contactPreference = contactPreference;
	}
	 @DynamoDBAttribute(attributeName = "termCond")
	public String getTermCond() {
		return termCond;
	}
	public void setTermCond(String termCond) {
		this.termCond = termCond;
	}
	private String termCond;
	
}
