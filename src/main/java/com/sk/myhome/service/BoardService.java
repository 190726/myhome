package com.sk.myhome.service;

import com.sk.myhome.model.Board;
import com.sk.myhome.model.User;
import com.sk.myhome.repository.BoardRepository;
import com.sk.myhome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Board save(String username, Board board){
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    };

}
