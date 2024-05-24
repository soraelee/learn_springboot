package com.care.study01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model){
        model.addAttribute("msg", "HelloWorld");
    }

    @GetMapping("/ex/ex01")
    public void ex01(Model model){
        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDD");
        System.out.println("list : "+list);
        model.addAttribute("list", list);

    }
    class SampleDTO{
        private String p1, p2, p3;

        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
    }
    @GetMapping("/ex/ex02")
    public void ex02(Model model){
        List<String> strlist = IntStream.range(1, 10)
                .mapToObj(i -> "Data"+i)
                .collect(Collectors.toList());
        model.addAttribute("list", strlist);

        Map<String, String> map = new HashMap<>();
        map.put("A", "AAAA");
        map.put("B", "BBBB");

        model.addAttribute("map", map);

        SampleDTO dto = new SampleDTO();

        dto.p1 = "Value -- p1";
        dto.p2 = "Value -- p2";
        dto.p3 = "Value -- p3";

        model.addAttribute("dto", dto);
    }
    @GetMapping("/ex/ex03")
    public void ex03(Model model){
        model.addAttribute("arr", new String[]{"AAA", "BBB", "CCC"});
    }
}
