package com.care.study01.service;

import com.care.study01.domain.Board;
import com.care.study01.dto.BoardDTO;
import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);//insert

        Long bno = boardRepository.save(board).getBno(); //save in database

        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        //Optional<> 처리
        Optional<Board> result = boardRepository.findById(bno);
        //findById() : 특정한 번호의 게시물을 조회하는 기능
        Board board = result.orElseThrow();
        //orElseThrow : Optional의 인자가 null일 경우 예외처리

        //Entity -> DTO
        BoardDTO  boardDTO = modelMapper.map(board, BoardDTO.class); //select 이므로

        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());// 기존 데이터 찾음
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        // 가져온 엔티티 값에서 DTO 값으로 수정

        boardRepository.save(board); //update in database
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        //BoardRepository 호출 기능
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //result로 나온 값을 list<dto>형태로 매핑
        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
