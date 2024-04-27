package com.example.Attendance.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
@Table(name="Visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visit", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @NotNull
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "status")
    private String status;

    @NotNull
    @NotBlank
    @Column(name = "event")
    private String event;
    @NotNull
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "comment")
    private String comment;

    @Column(name = "mark")
    private int mark;

    public Visitor() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "started_at", updatable = false)
    private LocalDateTime started_At;

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
}