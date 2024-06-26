package com.care.study01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private long bno;

    @NotEmpty
    @Size(min=3, max=100) // 비어있지 않고 무조건 내용을 작성하도록
    private String title;

    @NotEmpty
    private String content, writer;
    private LocalDateTime regDate, modDate;
}
