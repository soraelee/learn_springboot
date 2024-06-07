package com.care.study01.controller;

import com.care.study01.dto.PageRequestDTO;
import com.care.study01.dto.PageResponseDTO;
import com.care.study01.dto.ReplyDTO;
import com.care.study01.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor //의존성 주입을 위함
public class ReplyController {

    private final ReplyService replyService;

    //답글 등록
    @Operation(
                summary = "reply POST", description = "POST 형식으로 답글 달기"
    )
    @ApiResponse(responseCode = "200", description = "답글 등록 완료")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException {

            log.info(replyDTO);

            if(bindingResult.hasErrors()){
                throw new BindException(bindingResult);
            }

            Map<String, Long> resultMap = new HashMap<>();
            Long rno = replyService.register(replyDTO);

            resultMap.put("rno", rno);

            return resultMap;
        }
    @Operation(
                summary = "Replies of Board", description = "GET 방식으로 특정 게시물의 댓글 목록"
        )
    @ApiResponse(responseCode = "200", description = "특정 게시물 댓글 목록 확인 완료")
    @GetMapping(value="/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                                 PageRequestDTO pageRequestDTO){
            PageResponseDTO<ReplyDTO> pageResponseDTO = replyService.getListOfBoard(bno, pageRequestDTO );
            return pageResponseDTO;
        }

    @Operation(
                summary = "Read Reply", description = "Get 방식으로 특정 댓글 조회"
        )
    @ApiResponse(responseCode = "200", description = "특정 댓글 조회 완료")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable Long rno){

            ReplyDTO replyDTO = replyService.read(rno);

            return replyDTO;
        }
    @Operation(summary = "Delete Reply", description = "delete 방식으로 특정 댓글 삭제")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 완료")
    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable Long rno){
        replyService.remove(rno);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);

        return resultMap;
    }

    @Operation(summary = "Modify Reply", description = "Put 방식으로 댓글 수정")
    @ApiResponse(responseCode = "200", description = "댓글 수정 완료")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable Long rno, @RequestBody ReplyDTO replyDTO){
        replyDTO.setRno(rno); // 번호를 일치 시킴
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno", rno);
        return resultMap;
    }
}
