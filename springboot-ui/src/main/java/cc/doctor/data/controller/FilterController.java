package cc.doctor.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("filter")
public class FilterController {
    @RequestMapping("list")
    public String filterList() {
        return "filterlist";
    }
}
