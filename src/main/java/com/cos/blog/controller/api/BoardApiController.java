package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board/save")
    public ResponseDto<Integer> save(@RequestBody Board board,
    @AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("BoardApiController:save호출됨");

        boardService.글쓰기(board
            , principal.getUser());
        
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }



    @DeleteMapping("/api/board/delete/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        
        System.out.println("삭제:"  + id);
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/update/{id}")
    public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board board){
        System.out.println("수정:" + id );
        boardService.글수정하기(id, board);


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    
}
