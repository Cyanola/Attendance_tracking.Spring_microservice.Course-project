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
/**
 * Класс Visitor представляет собой запись о посещении мероприятия участником, который
 * был зарегистрирован на мероприятие и подтвердил явку через ответ на уведомление
 *
 * @version 1.0
 * @author Artemova
 */
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

    /**
     * Получить статус
     * @return статус
     */
    public String getStatus() {
        return status;
    }
    /**
     * Установить статус.
     *
     * @param status статус
     */

    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Получить комментарий
     * @return комментарий
     */

    public String getComment() {
        return comment;
    }
    /**
     * Установить комментарий.
     *
     * @param comment комментарий
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Получить оценку
     * @return оценка
     */
    public int getMark() {
        return mark;
    }
    /**
     * Установить оценку.
     *
     * @param mark оценка
     */

    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * Получить время начала мероприятия
     * @return время начала
     */
    @Transient
    public Timestamp startedAt() {

        return started_at;
    }
    /**
     * Получить время окончания мероприятия
     * @return время окончания
     */
    @Transient
    public Timestamp endedAt() {

        return ended_at;
    }
    /**
     * Получить идентификатор участника мероприятия
     * @return идентификатор участника мероприятия
     */
    @Transient
    public UUID getUUID() {

        return id_visit;
    }

}