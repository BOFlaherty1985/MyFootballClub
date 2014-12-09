package uk.co.myfootballclub.persistence.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * User Domain Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/12/2014
 * @project MyFootballClub
 */
@Entity
@Validated
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Length(min = 3)
    private String firstName;
    @NotNull
    @Length(min = 2)
    private String lastName;
    @NotNull
    @Length(min = 6, max = 20)
    private String username;
    @Column(name = "User_Email")
    @Email
    private String email;
    @Column(name = "Club_ID")
    private int myFootballClub;

    public Long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMyFootballClub() {
        return myFootballClub;
    }

    public void setMyFootballClub(int myFootballClub) {
        this.myFootballClub = myFootballClub;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", myFootballClub=" + myFootballClub +
                '}';
    }

}