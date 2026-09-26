package kz.iitu.spring_lab_01.web;

import kz.iitu.spring_lab_01.notify.NotificationService;
import kz.iitu.spring_lab_01.notify.Notifier;
import kz.iitu.spring_lab_01.scope.TicketOffice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lab2")
public class Lab2Controller {

    private final NotificationService notifications;
    private final TicketOffice ticketOffice;
    private final Notifier html;

    public Lab2Controller(
            NotificationService notifications,
            TicketOffice ticketOffice,
            @Qualifier("html") Notifier html) {

        this.notifications = notifications;
        this.ticketOffice = ticketOffice;
        this.html = html;
    }

    @GetMapping("/notify")
    public Map<String, Object> notify(
            @RequestParam(defaultValue = "Hello") String text) {

        return Map.of(
                "primary", notifications.viaPrimary(text),
                "console", notifications.viaConsole(text),
                "all", notifications.viaAll(text),
                "beanNames", notifications.names()
        );
    }

    @GetMapping("/scopes")
    public Map<String, Object> scopes() {
        return ticketOffice.demo();
    }

    @GetMapping(value = "/custom", produces = "text/html")
    public String custom(
            @RequestParam(defaultValue = "Hello HTML") String text) {

        return html.send(text);
    }
}