package com.example.Attendance.Controller;
import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Контроллер посещений мероприятий
 *
 * @version 1.0
 * @author Artemova
 */

@RestController
@Data
public class VisitorController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @Autowired
    /**
     * Получение участника мероприятия по идентификатору и номеру записи.
     *
     * @param id идентификатор  участника
     *   @param id_ номер посещения участника
     * @return участник
     */
    private VisitorService visitorService;


    // Endpoint для получения всех посещений
    /**
     * Получение  всех посещений всех мероприятий всеми участниками
     *
     * @return список посещений
     */
    @GetMapping("/all")
    public ResponseEntity<List<Visitor>> getAllVisitor() {
        List<Visitor> allVisitor = visitorService.getAllVisitor();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    /**
     * Получение  всех мероприятий участника по его идентификатору
     *
     * @param id идентификатор  участника

     * @return список мероприятий
     */
    @GetMapping("/get/id/{id}")
    public ResponseEntity<List<Visitor>> getVisitorByUUID( @PathVariable("id") UUID id) {
     try{   List<Visitor> visitor = visitorService.getVisitorById(id);
        return ResponseEntity.ok(visitor);
    } catch (Exception e) {

         System.out.println("Лох");
        throw new RuntimeException("Error fetching visitors", e);

    }
    }
    /**
     * Получение текущего мероприятия для участника по идентификатору участника.
     *
     * @param id идентификатор  участника
     *
     * @return текущее мероприятие
     */
    @GetMapping("/get/currentevent/{id}")
    public ResponseEntity<List<Visitor>> getCurrentEvent( @PathVariable("id") UUID id) {
    try{    List<Visitor> visitor = visitorService.getCurrentEvent(id);
        return ResponseEntity.ok(visitor);
    } catch (Exception e) {


        throw new RuntimeException("Error fetching visitors", e);

    }
    }
    /**
     * Получение участника мероприятия по идентификатору и номеру записи.
     *
     * @param id идентификатор  участника
     *   @param id_ номер посещения участника
     * @return участник
     */
    @GetMapping("/get/uuidid/{id}/{id_}")
    public ResponseEntity<List<Visitor>> getVisitorByUUID(@PathVariable ("id") UUID id, @PathVariable ("id_") int id_) {
        List<Visitor> visitor = visitorService.getVisitorById(id, id_);
        return ResponseEntity.ok(visitor);
    }
    /**
     * Получение мероприятий для участников по почте и ФИО участников.
     *
     * @param email почта  участника
     *            @param surname фамилия  участника
     *                       @param name имя  участника
     *                                  @param patronymic отчество участника
     * @return список мероприятий
     */
    @GetMapping("/getFIO/{surname}/{name}/{patronymic}/{email}")
        public ResponseEntity<List<Visitor>>  getByFIO(@PathVariable("surname") String surname,@PathVariable("name") String name,
                                                       @PathVariable("patronymic")        String patronymic,@PathVariable("email")  String email) {
        List<Visitor> visitor = visitorService.getByFIO(surname, name, patronymic,email);
        return ResponseEntity.ok(visitor);
    }

    /**
     * Получение мероприятий для участников по почте и ФИО участников в ближайшую дату.
     *
     * @param email почта  участника
     *            @param surname фамилия  участника
     *                       @param name имя  участника
     *                                  @param patronymic отчество участника
     * @return список мероприятий
     */
    @GetMapping("/getDate/{surname}/{name}/{patronymic}/{email}/{date}")
    public ResponseEntity<List<Visitor>> getByDate (@PathVariable("surname") String surname,@PathVariable("name") String name,
                                                   @PathVariable("patronymic")        String patronymic,@PathVariable("email")  String email,
                                                    @PathVariable("date") Timestamp date) {
        List<Visitor> visitor = visitorService.getByDate(surname, name, patronymic,email,date);
        return ResponseEntity.ok(visitor);
    }

    /**
     * Получение участников мероприятия по идентификатору и ФИО.
     *
     * @param id идентификатор  участника
     *            @param surname фамилия  участника
     *                       @param name имя  участника
     *                                  @param patronymic отчество участника
     * @return участник
     */
    @GetMapping("/getFIOUUID/{surname}/{name}/{patronymic}/{id}")
    public ResponseEntity<List<Visitor>>  getByFIOUUID(@PathVariable("surname") String surname,@PathVariable("name") String name,
                                                   @PathVariable("patronymic")        String patronymic,@PathVariable("id")  UUID id) {
        List<Visitor> visitor = visitorService.getByFIOUUID(surname, name, patronymic,id);
        return ResponseEntity.ok(visitor);

    }
    /**
     * Получение посещений мероприятия по статусу и почте.
     *
     * @param email почта  участника
     * @param status статус посещения
     * @return посещение
     */
    @GetMapping("/get/emailstatus/{email}/{status}")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatus(@PathVariable ("email") String email, @PathVariable ("status") String status) {
        List<Visitor> visitor = visitorService.findByEmailStatus(email, status);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение посещений мероприятия по почте и статусу, посетивших это мероприятие.
     *
     * @param email почта  участника
     * @return посещение
     */
    @GetMapping("/get/emailtrue/{email}/true")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatusTrue(@PathVariable ("email") String email) {
        List<Visitor> visitor = visitorService.findByEmailStatusTrue(email);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение посещений мероприятия по идентификатору и статусу.
     *
     * @param uuid идентификатор  участника
     * @param status статус посещения
     * @return посещение
     */
    @GetMapping("/get/uuidstatus/{uuid}/{status}")
    public ResponseEntity<List<Visitor>> getVisitorByEmailStatus(@PathVariable("uuid") UUID uuid, @PathVariable("status") String status) {
        List<Visitor> visitor = visitorService.findByUUIDStatus(uuid, status);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение посещений мероприятия по идентификатору, посетивших это мероприятие.
     *
     * @param uuid идентификатор  участника
     * @return посещение
     */
    @GetMapping("/get/uuidtrue/{uuid}/true")
    public ResponseEntity<List<Visitor>> getVisitorByUUIDStatusTrue(@PathVariable("uuid") UUID uuid) {
        List<Visitor> visitor = visitorService.findByUUIDStatusTrue(uuid);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение участников мероприятия по мероприятию, посетивших это мероприятие
     *
     * @param event идентификатор посещения
     * @return список посещений
     */
    @GetMapping("/get/eventtrue/{event}/true")
    public ResponseEntity<List<Visitor>> getVisitorByEventTrue(@PathVariable("event") String event) {
        List<Visitor> visitor = visitorService.findByEventTrue(event);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение участников мероприятия по мероприятию.
     *
     * @param event идентификатор посещения
     * @return список посещений
     */
    @GetMapping("/get/event/{event}")
    public ResponseEntity<List<Visitor>> getVisitorByEvent(@PathVariable("event") String event) {
        List<Visitor> visitor = visitorService.findByEvent(event);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Получение участника мероприятия по мероприятию и оценке.
     *
     * @param event мероприятие
     * @param mark оценка
     * @return список посещений
     */
    @GetMapping("/get/eventmark/{event}/{mark}")
    public ResponseEntity<List<Visitor>> getVisitorByEventMark(@PathVariable("event") String event, @PathVariable("mark") int mark) {
        List<Visitor> visitor = visitorService.findByEventMark(event, mark);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }
    /**
     * Смена комментария, оценки и статуса посещении по идентификатору.
     *
     * @param id идентификатор посещения
     * @param updatedVisitor обновленное значение посещения
     * @return посещение
     */
    @PostMapping("/{id}/update")
    public ResponseEntity<Visitor> updateVisitor(@PathVariable("id") int id, @RequestBody Visitor updatedVisitor) {
        Visitor visitor = visitorService.updateVisitor(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Получение участника мероприятия по почте.
     *
     * @param email почта
     * @return список посещений
     */
    @GetMapping("/getemail/{email}")
    public List<Visitor> getVisitorByEmail(@PathVariable("email") String email) {
        return visitorService.findByEmail(email);
    }
    /**
     * Получение количества отметившихся о присутствии участников мероприятия.
     *
     * @param event мероприятие
     * @return количество участников мероприятия
     */
    @GetMapping("/getcount/{event}")
    public int getVisitorCountForEvent(@PathVariable("event") String event) {
        return visitorService.getVisitorCountForEvent(event);
    }


    /**
     * Получение всех участников мероприятия по оценке.
     *
     * @param mark оценка
     * @return список посещений
     */
    @GetMapping("/getmark/{mark}")
    public  List<Visitor>  getVisitorByMark(@PathVariable("mark") int mark) {
        return visitorService.findByMark(mark);
    }

    /**
     * Получение всех участников мероприятия по статусу.
     *
     * @param status статус
     * @return список посещений
     */
    @GetMapping("/getstatus/{status}")
    public  List<Visitor>   getVisitorByStatus(@PathVariable("status") String status) {
        return visitorService.findByStatus(status);
    }

    /**
     * Получение всех участников мероприятия по комментарию.
     *
     * @param comment комментарий
      * @return список посещений
     */
    @GetMapping("/getcomment/{comment}")
    public List<Visitor>  getVisitorByComment(@PathVariable("comment") String comment) {
        return visitorService.findByComment(comment);
    }

    /**
     * Смена статуса на посещении по идентификатору.
     *
     * @param id идентификатор посещения
   @param changedVisitor обновленное значение посещения
     * @return посещение
     */
    @PostMapping("/{id}/chstatus")
    public Visitor changeStatus(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
        return visitorService.changeStatus(id, changedVisitor);

    }
    /**
     * Смена комментария, оценки и статуса посещении по идентификатору.
     *
     * @param id идентификатор посещения
     * @param changedVisitor обновленное значение посещения
     * @return посещение
     */
    @PostMapping("/{id}/change")
    public  ResponseEntity< Visitor> change(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
     //   return visitorService.change(id, changedVisitor);
        Visitor visitor = visitorService.change(id, changedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Смена оценки на посещении по идентификатору.
     *
     * @param id идентификатор посещения
     * @param changedVisitor обновленное значение посещения
     * @return посещение
     */
    @PostMapping("/{id}/chmark")
    public Visitor changeMark(@PathVariable("id") int id, @RequestBody Visitor changedVisitor) {
        return visitorService.changeMark(id, changedVisitor);
    }

    /**
     * Смена комментария на посещении по идентификатору.
     *
     * @param id идентификатор посещения
     * @param changedVisitor обновленное значение посещения
     * @return посещение

     */
    @PostMapping("/{id}/chcomment")
    public Visitor changeComment(@PathVariable("id") int id,  @RequestBody Visitor changedVisitor) {
        return visitorService.changeComment(id, changedVisitor);
    }
}