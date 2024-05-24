package com.care.study01.repository;

import com.care.study01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    //insert 기능 테스트 - save()
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..."+ i)
                    .content("content..." + i)
                    .writer("user"+(i%10))
                    .build();

            Board result = boardRepository.save(board);
        });
    }
}
