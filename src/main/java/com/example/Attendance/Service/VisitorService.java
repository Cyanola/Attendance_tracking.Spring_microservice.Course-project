package com.example.Attendance.Service;

import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

//    // Метод для создания посещения
//    public Visitor createVisitor(Visitor visitor) {
//        return visitorRepository.save(visitor);
//    }



    // Метод для получения всех посещений всех мероприятий
    public List<Visitor> getAllVisitor() {
        return visitorRepository.findAll();
    }

    // Метод для получения  всех мероприятий участника
    public List<Visitor> getVisitorById(UUID id) {
        return visitorRepository.findByUUID(id);
    }
    public List<Visitor> getCurrentEvent(UUID id) {
        return visitorRepository.findCurrentEvent(id);
    }
    public List<Visitor> getVisitorById(UUID id,int id_) {
        return visitorRepository.findByUUIDID(id,id_);
    }
    public int getVisitorCountForEvent(String event ) {
        return visitorRepository.countOfVisitors(event);
    }


    // Метод для обновления информации о посещении
    public Visitor updateVisitor(int id, Visitor updatedVisitor) {
        Visitor visitor = visitorRepository.findById(id);
        if (visitor != null) {
            visitor.setMark(updatedVisitor.getMark());
            visitor.setComment(updatedVisitor.getComment());
            visitor.setStatus(updatedVisitor.getStatus());
            return visitorRepository.save(visitor);
        }
        return null;
    }

//    // Метод для удаления записи о посещении по его идентификатору
//    public void deleteVisitor(UUID id) {
//        visitorRepository.deleteById(id);
//    }

     //Метод для поиска  всех мероприятий и их посещений участника по почте
    public List<Visitor> findByEmail(String email) {
        return visitorRepository.findByEmail(email);
    }

    // Метод для поиска всех участников всех мероприятий и их посещений по оценке
    public List<Visitor> findByMark(int mark) {
        return visitorRepository.findByMark(mark);
    }

    // Метод для поиска  всех участников всех мероприятий и их посещений по комментарию
    public  List<Visitor>  findByComment(String comment) {
        return visitorRepository.findByComment(comment);
    }

    // Метод для поиска всех участников всех мероприятий и их посещений по статусу
    public List<Visitor>  findByStatus(String status) {
        return visitorRepository.findByStatus(status);
    }


    public List<Visitor>  findByEmailStatus(String email, String status) {
        return visitorRepository.findByEmailStatus(email, status);
    }
    public List<Visitor>  findByEmailStatusTrue(String email) {
        return visitorRepository.findByEmailStatusTrue(email);
    }
    public List<Visitor>  findByUUIDStatus(UUID uuid, String status) {
        return visitorRepository.findByUUIDStatus(uuid,status);
    }
    public List<Visitor>  findByUUIDStatusTrue(UUID uuid) {
        return visitorRepository.findByUUIDStatusTrue(uuid);
    }

    public List<Visitor>  findByEventTrue(String event) {
        return visitorRepository.findByEventTrue(event);
    }
    public List<Visitor>  findByEvent(String event) {
        return visitorRepository.findByEvent(event);
    }
    public List<Visitor>  findByEventMark(String event, int mark) {
        return visitorRepository.findByEventMark(event, mark);
    }
    public List<Visitor> getByFIO(
            String sur,String name,String patr, String email) {
        return visitorRepository.getByFIO(sur, name, patr, email);
    }
    public List<Visitor> getByFIOUUID(
            String sur,String name,String patr, UUID id) {
        return visitorRepository.getByFIOUUID(sur, name, patr, id);
    }
    public Visitor change(int id, @RequestBody Visitor changedVisitor ){
        Visitor visitor = visitorRepository.findById(id);

        visitor.setStatus(changedVisitor.getStatus());
        visitor.setMark(changedVisitor.getMark());
        visitor.setComment(changedVisitor.getComment());
        return visitorRepository.save(visitor);
    }

    // Метод для изменения статуса посещения
    public Visitor changeStatus(int id,  @RequestBody Visitor changedVisitor ) {
        Visitor visitor = visitorRepository.findById(id);

        visitor.setStatus(changedVisitor.getStatus());
        return visitorRepository.save(visitor);
    }
    // Метод для изменения комментария о посещении
    public Visitor changeComment(int id,  @RequestBody Visitor changedVisitor ) {
        Visitor visitor = visitorRepository.findById(id);

        visitor.setComment(changedVisitor.getComment());
        return visitorRepository.save(visitor);
    }
    // Метод для изменения оценки посещения
    public Visitor changeMark(int id,  @RequestBody Visitor changedVisitor ) {
        Visitor visitor = visitorRepository.findById(id);

        visitor.setMark(changedVisitor.getMark());
        return visitorRepository.save(visitor);
    }


}
