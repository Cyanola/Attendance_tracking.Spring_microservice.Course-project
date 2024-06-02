package com.example.Attendance.Repository;
import com.example.Attendance.Model.Visitor;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
        // Метод для поиска участника по почте(всех его мероприятий)

        @Query("SELECT v FROM Visitor v WHERE v.email = :email")
             List<Visitor> findByEmail(@Param("email") String email);

    // Метод для поиска участника по почте(мероприятий, где он присутствовал, или нет)

    @Query("SELECT v FROM Visitor v WHERE v.email = :email AND v.status=:status")
    List<Visitor> findByEmailStatus(@Param("email") String email, @Param("status")String status);

    // Метод для поиска участника по почте(мероприятий, где он присутствовал)

    @Query("SELECT v FROM Visitor v WHERE v.email = :email AND v.status='Присутствовал'")
    List<Visitor> findByEmailStatusTrue(@Param("email") String email);

    //все  участники мероприятия

    @Query("SELECT v FROM Visitor v WHERE v.event = :event")
    List<Visitor> findByEvent(@Param("event") String event);

    //все  участники всех мероприятий со статусом

    @Query("SELECT v FROM Visitor v WHERE v.status = :status")
    List<Visitor> findByStatus(@Param("status") String status);

    //все  участники всех мероприятий с оценкой

    @Query("SELECT v FROM Visitor v WHERE v.mark = :mark")
    List<Visitor>  findByMark(@Param("mark") int mark);

    //все  участники всех мероприятий с комментарием

    @Query("SELECT v FROM Visitor v WHERE v.comment = :comment")
    List<Visitor> findByComment(@Param("comment") String comment);

    // Метод для поиска участника по uuid(одного его посещения)


    @Query("SELECT v FROM Visitor v WHERE v.id_visit = :uuid" +
            " AND v.id=:id_")
    List<Visitor> findByUUIDID(@Param("uuid") UUID uuid, @Param("id_") int id);

    // Метод для поиска участника по uuid(всех его мероприятий)

    @Query("SELECT v FROM Visitor v WHERE v.id_visit = :uuid")
    List<Visitor> findByUUID(@Param("uuid") UUID uuid);



    @Query("SELECT v FROM Visitor v WHERE v.id_visit = :uuid AND CURRENT_TIMESTAMP BETWEEN v.started_at AND v.ended_at")
    List<Visitor> findCurrentEvent(@Param("uuid") UUID uuid);

    // Метод для поиска участника по uuid(мероприятий, где он присутствовал, или нет)


    @Query("SELECT v FROM Visitor v WHERE v.id_visit = :uuid AND v.status=:status")
    List<Visitor> findByUUIDStatus(@Param("uuid") UUID uuid, @Param("status")String status);

    // Метод для поиска участника по uuid(мероприятий, где он присутствовал)

    @Query("SELECT v FROM Visitor v WHERE v.id_visit = :uuid AND v.status='Присутствовал'")
    List<Visitor> findByUUIDStatusTrue(@Param("uuid") UUID  uuid);


    //все  присутствовашие участники мероприятия

    @Query("SELECT v FROM Visitor v WHERE v.event=:event and v.status = 'Присутствовал'")
    List<Visitor> findByEventTrue(@Param("event") String event);
    //все  присутствовашие участники мероприятия с оценкой

    @Query("SELECT v FROM Visitor v WHERE v.event=:event and v.mark = :mark and v.status = 'Присутствовал'")
    List<Visitor> findByEventMark(@Param("event") String event,@Param("mark") int mark);

    @Query(value="SELECT v.* FROM Visitor v " +
            "WHERE v.surname = :surname " +
            "AND v.name = :name " +
            "AND v.patronymic = :patronymic " +
            "AND v.email = :email " +
            "AND v.started_at BETWEEN CURRENT_TIMESTAMP AND :endDate",nativeQuery = true)
    List<Visitor> getByDate(@Param("surname") String surname, @Param("name") String name,@Param("patronymic") String patr,@Param("email") String email,
                            @Param("endDate") Timestamp endDate);

    @Query("SELECT v  FROM Visitor v " +
            "WHERE v.surname=:surname and v.name = :name and v.patronymic = :patronymic and v.email=:email")
   List<Visitor> getByFIO(@Param("surname") String surname, @Param("name") String name,@Param("patronymic") String patr,@Param("email") String email );

    @Query("SELECT v  FROM Visitor v " +
            "WHERE v.surname=:surname and v.name = :name and v.patronymic = :patronymic and v.id_visit=:id")
    List<Visitor> getByFIOUUID(@Param("surname") String surname, @Param("name") String name,@Param("patronymic") String patr,@Param("id") UUID id );

    @Query("SELECT v FROM Visitor v WHERE v.id =:id")

    Visitor findById(@Param("id") int id);


    @Query("SELECT COUNT(*) FROM Visitor WHERE status='Присутствовал' and  event=:event")

    int countOfVisitors(@Param("event") String event);
}
