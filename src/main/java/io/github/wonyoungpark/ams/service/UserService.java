package io.github.wonyoungpark.ams.service;

import io.github.wonyoungpark.ams.common.Exception.UserDuplicatedException;
import io.github.wonyoungpark.ams.common.Exception.UserNotFoundException;
import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.dto.UserDTO;
import io.github.wonyoungpark.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by one0 on 2017. 7. 15..
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * username으로 사용자 정보 조회하기 - Spring Security에서 사용
     * @param name
     * @return
     */
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    /**
     * 유저 정보 조회
     * @param id
     * @return
     */
    public User getUser(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    /**
     * 유저 생성
     * @param create
     * @return
     */
    @Transactional
    public User createUser(UserDTO.Create create) {
        User user = modelMapper.map(create, User.class);

        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            log.error("user duplicated exception. {}", username);
            throw new UserDuplicatedException(username);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Date now = new Date();
        user.setJoined(now);

        return userRepository.save(user);
    }

    /**
     * 유저 정보 수정
     * @param id
     * @param update
     * @return
     */
    @Transactional
    public User updateUser(Long id, UserDTO.Update update) {
        User user = getUser(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(update.getEmail());

        return userRepository.save(user);
    }

    /** 유저 삭제
     *
     * @param id
     */
    @Transactional
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
