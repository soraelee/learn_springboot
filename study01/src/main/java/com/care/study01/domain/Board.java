package com.care.study01.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Board extends BaseEntity{

    @Id
    @SequenceGenerator(
            name = "ENTITY_ID_GENERATOR",
            sequenceName = "BOARD_SEQUENCES",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bno;

    @Column(length = 500, nullable=false) //칼럼의 길이와 null 허용 여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;


}
