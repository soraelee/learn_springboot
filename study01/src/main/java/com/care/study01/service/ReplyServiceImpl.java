package com.care.study01.service;


import com.care.study01.domain.Reply;
import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.dto.ReplyDTO;
import com.care.study01.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    //ReplyRepository와 ModelMapper을 주입 받아 구현
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    //final - 참조형 변수: 가리키는 객체를 변경하지 못하는 것

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();

        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);

        Reply reply = replyOptional.orElseThrow();

        return modelMapper.map(reply, ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());

        Reply reply = replyOptional.orElseThrow();

        //댓글의 Text만 수정 가능
        reply.changeText(replyDTO.getReplyText());

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(), Sort.by("rno").ascending());
                //Pageable 타입의 객체를 구성하여 파라미터 전달, 페이징 처리 진행 (페이지 번호, 사이즈, Sort)
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable); //board의 리스트 불러오기

        result.getContent().forEach(dto -> log.info(dto)); //Reply로 가져옴

        List<ReplyDTO> dtoList = result.getContent().stream()
                .map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        dtoList.forEach(list -> log.info(list)); //댓글 - 데이터베이스 스타일로 가져옴

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
