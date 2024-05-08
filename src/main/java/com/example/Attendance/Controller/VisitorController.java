package com.example.Attendance.Controller;
import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Data
public class VisitorController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
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

    // Endpoint для получения  всех мероприятий участника по его идентификатору
    @GetMapping("/get/id/{id}")
    public ResponseEntity<List<Visitor>> getVisitorByUUID( @PathVariable("id") UUID id) {
     try{   List<Visitor> visitor = visitorService.getVisitorById(id);
        return ResponseEntity.ok(visitor);
    } catch (Exception e) {

         System.out.println("Лох");
        throw new RuntimeException("Error fetching visitors", e);

    }
    }
    @GetMapping("/get/currentevent/{id}")
    public ResponseEntity<List<Visitor>> getCurrentEvent( @PathVariable("id") UUID id) {
    try{    List<Visitor> visitor = visitorService.getCurrentEvent(id);
        return ResponseEntity.ok(visitor);
    } catch (Exception e) {

        System.out.println("Лох");
        throw new RuntimeException("Error fetching visitors", e);

    }
    }

    @GetMapping("/get/uuidid/{id}/{id_}")
    public ResponseEntity<List<Visitor>> getVisitorByUUID(@PathVariable ("id") UUID id, @PathVariable ("id_") int id_) {
        List<Visitor> visitor = visitorService.getVisitorById(id, id_);
        return ResponseEntity.ok(visitor);
    }

    @GetMapping("/getFIO/{surname}/{name}/{patronymic}/{email}")
        public ResponseEntity<List<Visitor>>  getByFIO(@PathVariable("surname") String surname,@PathVariable("name") String name,
                                                       @PathVariable("patronymic")        String patronymic,@PathVariable("email")  String email) {
        List<Visitor> visitor = visitorService.getByFIO(surname, name, patronymic,email);
        return ResponseEntity.ok(visitor);

    }
    @GetMapping("/getFIOUUID/{surname}/{name}/{patronymic}/{id}")
    public ResponseEntity<List<Visitor>>  getByFIOUUID(@PathVariable("surname") String surname,@PathVariable("name") String name,
                                                   @PathVariable("patronymic")        String patronymic,@PathVariable("id")  UUID id) {
        List<Visitor> visitor = visitorService.getByFIOUUID(surname, name, patronymic,id);
        return ResponseEntity.ok(visitor);

    }
    @GetMapping("/get/emailstatus/{email}/{status}")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatus(@PathVariable ("email") String email, @PathVariable ("status") String status) {
        List<Visitor> visitor = visitorService.findByEmailStatus(email, status);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/emailtrue/{email}/true")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatusTrue(@PathVariable ("email") String email) {
        List<Visitor> visitor = visitorService.findByEmailStatusTrue(email);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/uuidstatus/{uuid}/{status}")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatus(@PathVariable("uuid") UUID uuid, @PathVariable("status") String status) {
        List<Visitor> visitor = visitorService.findByUUIDStatus(uuid, status);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/uuidtrue/{uuid}/true")
    public ResponseEntity<List<Visitor>> getVisitorByUUIDStatusTrue(@PathVariable("uuid") UUID uuid) {
        List<Visitor> visitor = visitorService.findByUUIDStatusTrue(uuid);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/eventtrue/{event}/true")
    public ResponseEntity<List<Visitor>> getVisitorByEventTrue(@PathVariable("event") String event) {
        List<Visitor> visitor = visitorService.findByEventTrue(event);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/event/{event}")
    public ResponseEntity<List<Visitor>> getVisitorByEvent(@PathVariable("event") String event) {
        List<Visitor> visitor = visitorService.findByEvent(event);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    @GetMapping("/get/eventmark/{event}/{mark}")
    public ResponseEntity<List<Visitor>> getVisitorByEventMark(@PathVariable("event") String event, @PathVariable("mark") int mark) {
        List<Visitor> visitor = visitorService.findByEventMark(event, mark);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    // Endpoint для обновления информации о посещении участника
    @PostMapping("/{id}/update")
    public ResponseEntity<Visitor> updateVisitor(@PathVariable("id") int id, @RequestBody Visitor updatedVisitor) {
        Visitor visitor = visitorService.updateVisitor(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint для удаления посещения по его идентификатору
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteVisitor(@PathVariable UUID id) {
//        visitorService.deleteVisitor(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    // Отображение посещения участника по его почте
    @GetMapping("/getemail/{email}")
    public List<Visitor> getVisitorByEmail(@PathVariable("email") String email) {
        return visitorService.findByEmail(email);
    }

    @GetMapping("/getcount/{event}")
    public int getVisitorCountForEvent(@PathVariable("event") String event) {
        return visitorService.getVisitorCountForEvent(event);
    }


    //все  участники всех мероприятий с оценкой
    @GetMapping("/getmark/{mark}")
    public  List<Visitor>  getVisitorByMark(@PathVariable("mark") int mark) {
        return visitorService.findByMark(mark);
    }

    //все  участники всех мероприятий со статусом
    @GetMapping("/getstatus/{status}")
    public  List<Visitor>   getVisitorByStatus(@PathVariable("status") String status) {
        return visitorService.findByStatus(status);
    }

    //все  участники всех мероприятий с комментарием
    @GetMapping("/getcomment/{comment}")
    public List<Visitor>  getVisitorByComment(@PathVariable("comment") String comment) {
        return visitorService.findByComment(comment);
    }

    //Смена статуса посещения
    @PostMapping("/{id}/chstatus")
    public Visitor changeStatus(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
        return visitorService.changeStatus(id, changedVisitor);
    }
    @PostMapping("/{id}/change")
    public Visitor change(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
        return visitorService.change(id, changedVisitor);
    }

    // Смена оценки посещения участника
    @PostMapping("/{id}/chmark")
    public Visitor changeMark(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
        return visitorService.changeMark(id, changedVisitor);
    }
    // Смена комментария посещения участника
    @PostMapping("/{id}/chcomment")
    public Visitor changeComment(@PathVariable("id") int id,  @RequestBody Visitor changedVisitor) {
        return visitorService.changeComment(id, changedVisitor);
    }
}