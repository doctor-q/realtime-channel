package cc.doctor.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trigger")
public class TriggerController {
    @RequestMapping("list")
    public String triggerList() {
        return "triggerlist";
    }
}
