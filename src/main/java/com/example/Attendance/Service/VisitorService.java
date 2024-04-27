package com.example.Attendance.Service;

import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    // Метод для создания посещения
    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    // Метод для получения всех посещений
    public List<Visitor> getAllVisitor() {
        return visitorRepository.findAll();
    }

    // Метод для получения посещения по его идентификатору
    public Optional<Visitor> getVisitorById(UUID id) {
        return visitorRepository.findById(id);
    }

    // Метод для обновления информации о посещении
    public Visitor updateVisitor(UUID id, Visitor updatedVisitor) {
        Visitor visitor = visitorRepository.findById(id).orElse(null);
        if (visitor != null) {
            visitor.setMark(updatedVisitor.getMark());
            visitor.setComment(updatedVisitor.getComment());
            visitor.setStatus(updatedVisitor.getStatus());
            return visitorRepository.save(visitor);
        }
        return null;
    }

    // Метод для удаления записи о посещении по его идентификатору
    public void deleteVisitor(UUID id) {
        visitorRepository.deleteById(id);
    }

    // Метод для поиска участника и его посещений по почте
//    public List<Visitor> findByEmail(String email) {
//        return visitorRepository.findByEmail(email);
//    }

    // Метод для поиска участника и его посещений по почте
    public List<Object[]> findByMark(int mark) {
        return visitorRepository.findByMark(mark);
    }

    // Метод для поиска участника и его посещений по почте
    public  List<Object[]>  findByComment(String comment) {
        return visitorRepository.findByComment(comment);
    }

    // Метод для поиска участника и его посещений по почте
    public List<Object[]>   findByStatus(String status) {
        return visitorRepository.findByStatus(status);
    }

    public Visitor change(UUID id, String newStatus, int mark, String comment) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись посещения  с id: " + id+" не найдена"));
        visitor.setStatus(newStatus);
        visitor.setMark(mark);
        visitor.setComment(comment);
        return visitorRepository.save(visitor);
    }

    // Метод для изменения статуса посещения
    public Visitor changeStatus(UUID id, String newStatus) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись посещения  с id: " + id+" не найдена"));
        visitor.setStatus(newStatus);
        return visitorRepository.save(visitor);
    }
    // Метод для изменения комментария о посещении
    public Visitor changeComment(UUID id, String newComment) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись посещения   с id: " + id+" не найдена"));
        visitor.setComment(newComment);
        return visitorRepository.save(visitor);
    }
    // Метод для изменения оценки посещения
    public Visitor changeMark(UUID id, int newMark) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запись посещения   с id: " + id+" не найдена"));
        visitor.setMark(newMark);
        return visitorRepository.save(visitor);
    }
}
