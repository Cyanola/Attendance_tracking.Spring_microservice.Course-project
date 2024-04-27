package com.example.Attendance.Controller;
import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    // Endpoint для создания нового посещения
//    @PostMapping("/add")
//    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
//        Visitor createdVisitor = visitorService.createVisitor(visitor);
//        return new ResponseEntity<>(createdVisitor, HttpStatus.CREATED);
//    }

    // Endpoint для получения всех посещений
    @GetMapping("/all")
    public ResponseEntity<List<Visitor>> getAllVisitor() {
        List<Visitor> allVisitor = visitorService.getAllVisitor();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }

    // Endpoint для получения  посещения по его идентификатору
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable UUID id) {
        Optional<Visitor> visitor = visitorService.getVisitorById(id);
        return visitor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint для обновления информации о посещении участника
//    @PostMapping("/update/{id}")
//    public ResponseEntity<Visitor> updateVisitor(@PathVariable UUID id, @RequestBody Visitor updatedVisitor) {
//        Visitor visitor = visitorService.updateVisitor(id, updatedVisitor);
//        if (visitor != null) {
//            return new ResponseEntity<>(visitor, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Endpoint для удаления посещения по его идентификатору
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable UUID id) {
        visitorService.deleteVisitor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    // Отображение посещения участника по его почте
//    @GetMapping("/getemail/email")
//    public List<Visitor> getVisitorByEmail(@RequestParam String email) {
//        return visitorService.findByEmail(email);
//    }

    // Отображение посещения  по его оценке
    @GetMapping("/getmark/{mark}")
    public  List<Object[]>  getVisitorByMark(@PathVariable int mark) {
        return visitorService.findByMark(mark);
    }

    // Отображение посещения  по его статусу
    @GetMapping("/getstatus/{status}")
    public  List<Object[]>   getVisitorByStatus(@PathVariable String status) {
        return visitorService.findByStatus(status);
    }

    // Отображение посещения  по его комментарию
    @GetMapping("/getcomment/{comment}")
    public List<Object[]>  getVisitorByComment(@PathVariable String comment) {
        return visitorService.findByComment(comment);
    }

    //Смена статуса посещения
    @PostMapping("/chstatus/{status}/{id}")
    public Visitor changeStatus(@PathVariable UUID id, @PathVariable String status) {
        return visitorService.changeStatus(id, status);
    }
    @PostMapping("/change/{id}/{status}/{mark}/{comment}")
//    public Visitor[] change(@PathVariable UUID id, @PathVariable String status,@PathVariable int mark,@PathVariable String comment) {
//    return new Visitor[]{visitorService.changeStatus(id, status),
//        visitorService.changeMark(id, mark),
//        visitorService.changeComment(id, comment)};
//    }
    public Visitor change(@PathVariable UUID id, @PathVariable String status,@PathVariable int mark,@PathVariable String comment) {
        return visitorService.change(id, status, mark, comment);
    }

    // Смена оценки посещения участника
    @PostMapping("/chmark/{id}/{mark}")
    public Visitor changeMark(@PathVariable UUID id, @PathVariable int mark) {
        return visitorService.changeMark(id, mark);
    }
    // Смена комментария посещения участника
    @PostMapping("/chcomment/{id}/{comment}")
    public Visitor changeComment(@PathVariable UUID id, @PathVariable String comment) {
        return visitorService.changeComment(id, comment);
    }
}