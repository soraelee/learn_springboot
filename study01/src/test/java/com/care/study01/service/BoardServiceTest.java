package com.care.study01.service;

import com.care.study01.domain.Board;
import com.care.study01.dto.BoardDTO;
import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        //등록 처리
        BoardDTO boardDTO = BoardDTO.builder()
                .title("sample title...")
                .content("sample content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);
        log.info("bno : " + bno);

    }

    @Test
    public void testModify(){
        //변경에 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(201L)
                .title("Updated,,, 201")
                .content("Updated content --- 201")
                .build();
        boardService.modify(boardDTO); //엔터티 및 데이터베이스 수정
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }

}
