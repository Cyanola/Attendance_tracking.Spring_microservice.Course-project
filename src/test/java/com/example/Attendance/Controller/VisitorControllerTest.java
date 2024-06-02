package com.example.Attendance.Controller;

import com.example.Attendance.Model.Visitor;
import com.example.Attendance.Service.VisitorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class VisitorControllerTest {
    @Mock
    private VisitorService visitorService;

    @InjectMocks
    private VisitorController visitorController;

    private Visitor testVisitor;
    private Visitor updatedVisitor;

    @BeforeEach
    public void setup() {
        testVisitor = new Visitor(1, UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172"),"Данилов","Сергей","Васильевич", "sdftgrytuyiu@mail.ru",
                "Зачет", Timestamp.valueOf("2024-09-01 05:00:00"), Timestamp.valueOf("2024-09-01 15:00:00"),"Не отмечено","Не отмечено",0 );
        testVisitor.setId(1);

        updatedVisitor = new Visitor(1, UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172"),"Данилов","Сергей","Васильевич", "sdftgrytuyiu@mail.ru",
                "Зачет", Timestamp.valueOf("2024-09-01 05:00:00"), Timestamp.valueOf("2024-09-01 15:00:00"),"Присутствовал","Супер!",5 );

    }

    @Test
    void testGetAllVisitor() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getAllVisitor()).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getAllVisitor();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(visitorList, response.getBody());
    }

    @Test
    void testGetVisitorByUUID() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getVisitorById(eq(UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172")))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getVisitorByUUID(UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    @Test
    void testGetCurrentEvent() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getCurrentEvent(eq(UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172")))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getCurrentEvent(UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }
    /**Тест выбора даты ближайших мероприятий участником
     * @return список ближайших мероприятий участника
     */
    @Test
    void testGetByDate() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getByDate(eq("Данилов"),eq("Сергей"),eq("Васильевич"),eq("sdftgrytuyiu@mail.ru"),
                eq(Timestamp.valueOf("2024-06-05 12:00:00")))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getByDate("Данилов","Сергей","Васильевич",
                "sdftgrytuyiu@mail.ru",Timestamp.valueOf("2024-06-05 12:00:00"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    /**Тест получения всех мероприятий участником
     * @return список мероприятий участника
     */
    @Test
    void testGetByFIO() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getByFIO(eq("Данилов"),eq("Сергей"),eq("Васильевич"),eq("sdftgrytuyiu@mail.ru"))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getByFIO("Данилов","Сергей","Васильевич","sdftgrytuyiu@mail.ru");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    @Test
    void testGetByFIOUUID() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.getByFIOUUID(eq("Данилов"),eq("Сергей"),eq("Васильевич"),eq(UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172")))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getByFIOUUID("Данилов","Сергей","Васильевич",UUID.fromString("d7dca61a-8bbb-4735-ae34-15b63d275172"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    @Test
    void testGetVisitorByEventTrue() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.findByEventTrue(eq("Зачет"))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getVisitorByEventTrue("Зачет");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    @Test
    void testGetVisitorByEvent() {
        List<Visitor> visitorList = new ArrayList<>();
        visitorList.add(testVisitor);
        when(visitorService.findByEvent(eq("Зачет"))).thenReturn(visitorList);
        ResponseEntity<List<Visitor>> response = visitorController.getVisitorByEvent("Зачет");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, Objects.requireNonNull(response.getBody()).get(0));
    }

    /**Тест выбора мероприятия и изменении информации о посещении участником
     * @return обновление записи о посещении мероприятия участника
     */
    @Test
    void testChange() {
        when(visitorService.change(eq(1), any(Visitor.class))).thenReturn(testVisitor);
        ResponseEntity<Visitor> response = visitorController.change(1, updatedVisitor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testVisitor, response.getBody());
    }
}