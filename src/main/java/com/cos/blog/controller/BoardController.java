package com.cos.blog.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    @GetMapping({ "", "/" })
    public String index(Model model,
    @PageableDefault(size=4, sort="id", direction = Sort.Direction.DESC) Pageable pageable)
        // @AuthenticationPrincipal PrincipalDetails principal
         { //컨트롤러에서 세션을 어떻게 찾는가?
        // System.out.println("로그인 정보 : " + principal.getUsername());
        Page<Board> boards = boardService.글목록(pageable);
        model.addAttribute("boards", boards);


        ArrayList<String> pages = new ArrayList<String>();
        int totalPage = boards.getTotalPages();
        int currentPage = pageable.getPageNumber() + 0;
        int perCount = 4;
        int start = currentPage - (perCount)/2;
        int end = currentPage + (perCount+1)/2;
        
        start = start<0 ? 0 : start;
        end = end<totalPage ? end : totalPage;

        start = end==totalPage ? totalPage - perCount : start;
        end = start==0 ? perCount : end;

        
        String tag0 = "<li class='page-item disabled'> <a class='page-link' href='?page=";
        String tag1 = "<li class='page-item'> <a class='page-link' href='?page=";
        String tag2 = "'>";
        String tag3 = "</a><li/>";
        

        for(int i =start; i<end; i++){
            
            if(i==currentPage){
                pages.add(
                tag0 + (i) + tag2 + (i+1) + tag3
            );
            }else{
                pages.add(
                tag1 + (i) + tag2 + (i+1) + tag3
            );
            }

            
        }

        
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pages", pages);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());


        return "index"; // viewResolver 작동!!
    }

    @GetMapping("/dummy")
    public @ResponseBody Page<Board> dummy(Model model,
    @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable)
        // @AuthenticationPrincipal PrincipalDetails principal
         { 
             Page<Board> pageBoard = boardRepository.findAll(pageable);
             List<Board> boards = pageBoard.getContent();
        return pageBoard; // viewResolver 작동!!
    }

    // USER

    @GetMapping("/board/saveForm")
    public String write(@AuthenticationPrincipal PrincipalDetails principal){
        System.out.println("로그인 정보 : " + principal.getUsername());
        return "/board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String findById(@AuthenticationPrincipal PrincipalDetails principal,@PathVariable int id, Model model){

        Board board = boardService.글상세보기(id);
        model.addAttribute("board", board);
        model.addAttribute("writer", principal.getUsername().equals(board.getUser().getUsername()));
        return "/board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "/board/updateForm";
    }

}
