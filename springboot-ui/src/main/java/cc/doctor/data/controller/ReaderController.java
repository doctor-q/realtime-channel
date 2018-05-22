package cc.doctor.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reader")
public class ReaderController {
    @RequestMapping("list")
    public String readerList() {
        return "readerlist";
    }
}
