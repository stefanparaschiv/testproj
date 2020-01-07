package com.tremend.project.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "pref_temp")
    private int prefTemp;
    @Column(name = "on_time")
    private Time onTime;
    @Column(name = "off_time")
    private Time offTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getPrefTemp() {
        return prefTemp;
    }

    public void setPrefTemp(int prefTemp) {
        this.prefTemp = prefTemp;
    }

    public Time getOnTime() {
        return onTime;
    }

    public void setOnTime(Time onTime) {
        this.onTime = onTime;
    }

    public Time getOffTime() {
        return offTime;
    }

    public void setOffTime(Time offTime) {
        this.offTime = offTime;
    }
}
