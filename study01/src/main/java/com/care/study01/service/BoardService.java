package com.care.study01.service;

import com.care.study01.dto.BoardDTO;
import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;


public interface BoardService {
    Long register(BoardDTO boardDTO); //register 선언
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
