package net.oceandepth.journalApp.journalEntry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheck {

    @GetMapping("health-check")
    public String check() {
        return "ok!";
    }
}
