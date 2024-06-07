package com.care.study01.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString // Board 타입의 객체 참조를 board 변수를 이용해서 참조 - @ManyToOne의 다대일 관계로 구성
@SequenceGenerator(name = "reply_seq_generator",
        sequenceName = "reply_seq",
        initialValue = 1, allocationSize = 1)
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_generator")
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }
}
