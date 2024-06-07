package com.care.study01.controller;

import com.care.study01.dto.BoardDTO;
import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor // 자동으로 생성자 주입에 대한 코드 생성
public class BoardController {
    private final BoardService bs;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO responseDTO = bs.listWithReplyCount(pageRequestDTO); //생성자 생성
        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO); //모델로 전달
    }

    @GetMapping("/register")
    public void registerGET(){

    }
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO dto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info("board Post register...");

        if(bindingResult.hasErrors()){
            log.info("hasErrors.....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        log.info(dto);

        Long bno = bs.register(dto);
        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = bs.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("board modify post ..............."+boardDTO);

        if(bindingResult.hasErrors()){
            log.info("has errors...");
            String link = pageRequestDTO.getLink();
            //url에 기존의 모든 조건을 원래대로 붙여 /board/link로 이동하게 함
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+link;
        }
        bs.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("remove post ...." + bno);
        bs.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }
}

