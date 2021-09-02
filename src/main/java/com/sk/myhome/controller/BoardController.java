package com.sk.myhome.controller;

import com.sk.myhome.model.Board;
import com.sk.myhome.repository.BoardRepository;
import com.sk.myhome.service.BoardService;
import com.sk.myhome.service.UserService;
import com.sk.myhome.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final BoardValidator boardValidator;


    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size=2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){
        //Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
        //Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id ){
        if(id==null){
            model.addAttribute("board", new Board());
        }else{
            Optional<Board> board = boardRepository.findById(id);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        //Authentiation 가져오는 방법 2
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication1.getName();
        boardService.save(username, board);
        //boardRepository.save(board);
        return "redirect:/board/list";
    }

}
