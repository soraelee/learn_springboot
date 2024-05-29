package com.care.study01.controller;

import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor // 자동으로 생성자 주입에 대한 코드 생성
public class BoardController {
    private final BoardService bs;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO responseDTO = bs.list(pageRequestDTO); //생성자 생성
        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO); //모델로 전달
    }
}

