package com.care.study01.repository;

import com.care.study01.domain.Board;
import com.care.study01.domain.Reply;
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


@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert(){
        // 실제 DB에 있는 bno
        Long bno = 100L;

        //Board 객체 생성
        Board board = Board.builder().bno(bno).build();
        log.info(board);
        Reply reply = Reply.builder()
                .board(board) //board_bno 참조
                .replyText("댓글..")
                .replyer("replyer1")
                .build();
        log.info(reply);

        replyRepository.save(reply);
    }
    @Transactional
    @Test
    public void testBoardReplies() {
        Long bno = 100L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }

}
