package com.care.study01.controller;

import com.care.study01.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Reply", description = "Reply 관련 API 입니다.")
@RestController
@RequestMapping("replies")
@Log4j2
public class ReplyController {
        @Operation(
                summary = "reply POST", description = "POST 형식으로 답글 달기11"
        )
        @ApiResponse(responseCode = "200", description = "답글 등록 완료")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO, //검증을 완료 했으니 실제 실행 토드로 변경
                                      BindingResult bindingResult) throws BindException {
            //검증을 완료 했으니 실제 실행 코드로 변경
            log.info(replyDTO);

            if(bindingResult.hasErrors()){
                throw new BindException(bindingResult);
            }

            Map<String, Long> resultMap = new HashMap<>();
//            resultMap.put("rno", 111L);

            return resultMap;
        }
}
