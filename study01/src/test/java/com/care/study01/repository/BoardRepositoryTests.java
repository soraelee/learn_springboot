package com.care.study01.repository;

import com.care.study01.domain.Board;
import com.care.study01.dto.BoardListReplyCountDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;
    //insert 기능 테스트 - save()
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();

            Board result = boardRepository.save(board);
            System.out.println(result);
        });
    }

    //select 기능 테스트
    @Test
    public void testSelect(){
        long bno = 100;

        Optional<Board> result = boardRepository.findById(bno);
        //findById() : 특정한 번호의 게시물을 조회하는 기능
//        log.info(result);

    }

    // update 기능 테스트
    @Test
    public void testUpdate(){
        long bno = 100;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow(); // Optional의 인자가 null일 경우 예외처리
        board.change("update..title 100", "update content 100");
        boardRepository.save(board);
    }

    //delete 기능 테스트
    @Test
    public void testDelete() {
        long bno = 1;
        boardRepository.deleteById(bno);
    }

    //페이징 처리
    @Test
    public void testPaging(){
        // 1 Page order by bno desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count : " + result.getTotalElements());
        log.info("total pages : " + result.getTotalPages());
        log.info("page number : " + result.getNumber());
        log.info("page size : " + result.getSize());

        List<Board> todoList = result.getContent(); //조회된 데이터
        todoList.forEach(board -> log.info(String.valueOf(board)));
    }
    //쿼리 메소드
//    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);
//    @Query("select b from Board b where b.title like concat('%', :keyword, '%'")
//    Page<Board> findKeyword(String keyword, Pageable pageable);
//    @Query(value = "select now()", nativeQuery = true)
//    String getTime();
    @Test
    public void testSearch1(){
        //2 page order by bno desc
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }


    @Test
    public void testSearchAll(){
        String[] types = {"t", "c", "w"}; //title, content, write
        String keyword = "1"; //contains 1 = Like = 1
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        // total pages
        log.info("getTotalPages : " + result.getTotalPages());

        //page size
        log.info("Page size : " + result.getSize());

        //pageNumber
        log.info("page Number : " + result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ":" +result.hasNext());

        result.getContent().forEach(board -> log.info(board));

    }
    @Test
    public void testSearchReplyCount() {
        String[] types = {"t", "c", "w"};

        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());
        //page size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious() + ":" + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }

}
