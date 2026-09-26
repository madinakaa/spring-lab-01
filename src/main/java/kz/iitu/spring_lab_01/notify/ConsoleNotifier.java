package kz.iitu.spring_lab_01.notify;

import org.springframework.stereotype.Component;

@Component("console")
public class ConsoleNotifier implements Notifier {

    @Override
    public String send(String message) {
        return "Console: " + message;
    }

    @Override
    public String channel() {
        return "console";
    }
}