package uk.co.myfootballclub.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User Domain Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/12/2014
 * @project MyFootballClub
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @Column(name = "User_Email")
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
