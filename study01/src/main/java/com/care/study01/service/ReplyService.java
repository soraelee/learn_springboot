package com.care.study01.service;

import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO); //create
    ReplyDTO read(Long rno); //read
    void modify(ReplyDTO replyDTO); //update
    void remove(Long rno); //delete

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
