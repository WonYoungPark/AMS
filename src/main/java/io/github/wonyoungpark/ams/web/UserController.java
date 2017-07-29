package io.github.wonyoungpark.ams.web;

import io.github.wonyoungpark.ams.common.ErrorResponse;
import io.github.wonyoungpark.ams.common.Exception.UserDuplicatedException;
import io.github.wonyoungpark.ams.common.Exception.UserNotFoundException;
import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.dto.UserDTO;
import io.github.wonyoungpark.ams.repository.UserRepository;
import io.github.wonyoungpark.ams.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by one0 on 2017. 7. 1.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 유저 생성
     * @param create
     * @param result
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity createUser(@RequestBody @Valid UserDTO.Create create, BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다.");
            errorResponse.setCode("bad.request");
            // TODO BindingResult 안에 들어있는 에러 정보 사용하기.
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(create);

        return new ResponseEntity<>(modelMapper.map(user, UserDTO.Response.class), HttpStatus.CREATED);

    }

    /**
     * 유저 목록 가져오기(페이징 처리)
     * @param pageable
     * @return
     */
    @GetMapping()
    public PageImpl<UserDTO.Response> getUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        //TODO stream() vs parallelStream() 알아보
        List<UserDTO.Response> content = page.getContent().parallelStream().map(user -> modelMapper.map(user, UserDTO.Response.class)).collect(Collectors.toList());
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    /**
     * 유저 정보 가져오기
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO.Response getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return modelMapper.map(user, UserDTO.Response.class);
    }

    /**
     * 유저 정보 업데이트
     * @param id
     * @param update
     * @param result
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO.Update update, BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("");
            errorResponse.setCode("");

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        User user = userService.updateUser(id, update);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.Response.class), HttpStatus.OK);

    }

    /**
     * 유저 정보 부분 수정
     * @param id
     * @param update
     * @param result
     * @return
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity patchUser(@PathVariable Long id, @RequestBody @Valid UserDTO.Update update, BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("");
            errorResponse.setCode("");

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        User user = userService.updateUser(id, update);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.Response.class), HttpStatus.OK);
    }

    /**
     * 유저 삭제
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * 중복된 유저가 있을 경우 Exception 처리 선언
     * @param e
     * @return
     */
    @ExceptionHandler(UserDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleUserDuplicatedException(UserDuplicatedException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getUsername() + "] 중복된 username 입니다.");
        errorResponse.setCode("duplicated.username.exception");
        return errorResponse;
    }

    /**
     * 조회된 유저가 없을 경우 Exception 처리 선언
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getId() + "]에 해당하는 계정이 없습니다.");
        errorResponse.setCode("account.not.found.exception");
        return errorResponse;
    }
}





