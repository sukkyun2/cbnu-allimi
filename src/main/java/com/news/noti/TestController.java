package com.news.noti;

import com.news.noti.collector.CBNUSoftwareArticleCollectRequest;
import com.news.noti.collector.CBNUSoftwareArticleCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class TestController {

    @RequestMapping("/home")
    public String test(Model model){
        return "test1";
    }
}
