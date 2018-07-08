package com.mamoru.memberservice;

import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * user: alekseyb
 * date: 04.07.18
 */
@XmlRootElement
public class MemberDTO implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String postalCode;
    private String imageId;

    public MemberDTO() {
    }

    public MemberDTO(String firstName, String lastName, Date birthDate, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
    }

    public MemberDTO(String id,String firstName, String lastName, Date birthDate, String postalCode, String image) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
        this.imageId = image;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
