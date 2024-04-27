package com.example.Attendance.Repository;
import com.example.Attendance.Model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
        // Метод для поиска участника по почте
//        @Transactional
//        @Modifying
//        @Query("SELECT * FROM Visitor v WHERE v.email = :email")
//             List<Visitor> findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("SELECT v.event,v.email, v.status,v.comment, v.mark FROM Visitor v WHERE v.status = :status")
    List<Object[]> findByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query("SELECT  v.event,v.email, v.status,v.comment, v.mark FROM Visitor v WHERE v.mark = :mark")
    List<Object[]>  findByMark(@Param("mark") int mark);

    @Transactional
    @Modifying
    @Query("SELECT  v.event,v.email, v.status,v.comment, v.mark FROM Visitor v WHERE v.comment = :comment")
    List<Object[]> findByComment(@Param("comment") String comment);
}
