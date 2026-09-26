package kz.iitu.spring_lab_01.web;

import kz.iitu.spring_lab_01.scope.TicketOffice;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScopeController {

    private final TicketOffice ticketOffice;

    public ScopeController(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    @GetMapping("/scope")
    public Map<String, Object> scope() {
        return ticketOffice.demo();
    }
}