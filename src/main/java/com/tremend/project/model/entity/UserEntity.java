package com.tremend.project.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "USERS")
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
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime onTime;
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "off_time")
    private LocalTime offTime;

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

    public LocalTime getOnTime() {
        return onTime;
    }

    public void setOnTime(LocalTime onTime) {
        this.onTime = onTime;
    }

    public LocalTime getOffTime() {
        return offTime;
    }

    public void setOffTime(LocalTime offTime) {
        this.offTime = offTime;
    }
}
