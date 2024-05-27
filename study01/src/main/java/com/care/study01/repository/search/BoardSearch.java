package com.care.study01.repository.search;

import com.care.study01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    //페이지 처리 기능
    Page<Board> search1(Pageable pageable);

}
