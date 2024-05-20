package com.example.Attendance.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @NotNull
    @NotBlank
    @Column(name = "id_visit", updatable = false, nullable = false)
    private UUID id_visit;
    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;


    @NotNull
    @Column(name = "patronymic")
    private String patronymic;
    @NotNull
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;


    @NotNull
    @NotBlank
    @Column(name = "event")
    private String event;
    @NotNull
    @NotBlank
    @Column(name = "started_at", updatable = false)
    private Timestamp started_at;
    @NotNull
    @NotBlank
    @Column(name = "ended_at", updatable = false)
    private Timestamp ended_at;

    @Column(name = "status")
    private String status;
    @Column(name = "comment")
    private String comment;

    @Column(name = "mark")
    private int mark;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    @Transient
    public Timestamp startedAt() {

        return started_at;
    }
    @Transient
    public Timestamp endedAt() {

        return ended_at;
    }
    @Transient
    public UUID getUUID() {

        return id_visit;
    }

}