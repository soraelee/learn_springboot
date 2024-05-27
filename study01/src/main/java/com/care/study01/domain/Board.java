package com.care.study01.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SequenceGenerator(
        name = "board_id_generator",
        sequenceName = "board_seq",
        allocationSize = 1)
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_generator")
    private Long bno;

    @Column(length = 500, nullable=false) //칼럼의 길이와 null 허용 여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    //수정 기능 추가
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
