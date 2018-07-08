package com.mamoru.memberservice;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * user: alekseyb
 * date: 03.07.18
 */
@Document(collection = "members")
public class Member implements Serializable {

    @Id
    String id;
    String firstName;
    String lastName;
    Date birthDate;
    String postalCode;
    ObjectId picture;

    public Member() {
    }

    public Member(String firstName, String lastName, Date birthDate, String postalCode, ObjectId picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
        this.picture = picture;
    }

    public Member(String firstName, String lastName, Date birthDate, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ObjectId getPicture() {
        return picture;
    }

    public void setPicture(ObjectId picture) {
        this.picture = picture;
    }

}
