package com.care.study01.repository.search;

import com.care.study01.domain.Board;
import com.care.study01.domain.QBoard;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; //Q도메인 객체
        JPQLQuery<Board> query = from(board); //select.. from board
        query.where(board.title.contains("1")); //where title like..
        List<Board> list = query.fetch();
        long count = query.fetchCount();

        return null;
    }
}
