package com.aledma.hackathonBEfinal.controller;

import com.aledma.hackathonBEfinal.domain.User;
import com.aledma.hackathonBEfinal.domain.WebScraping;
import com.aledma.hackathonBEfinal.dto.UserDto;
import com.aledma.hackathonBEfinal.exception.DataNotFoundException;
import com.aledma.hackathonBEfinal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User", description = "User관련 로직 작성")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 400, message = "회원가입 실패")
    })
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto){
        try {
            this.userService.signUp(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "로그인", notes = "로그인 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "로그인 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody UserDto userDto){
        try {
            Long id = this.userService.login(userDto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "WebScraping list", notes = "WebScraping list api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 가져오기 성공")
    })
    @GetMapping("/{userId}/all")
    public ResponseEntity<List<WebScraping>> getUserQuestions(@PathVariable Long userId) {
        List<WebScraping> list = userService.getUserwebscrapingList(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "WebScraping list 최근파일 5개", notes = "WebScraping Recent list api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 가져오기 성공")
    })
    @GetMapping("/{userId}/home")
    public ResponseEntity<List<WebScraping>> getRecentUserQuestions(@PathVariable Long userId) {
        List<WebScraping> list = userService.getUserwebscrapingList(userId);

        // 최근 파일 5개 불러오기
        List<WebScraping> recentList = list.subList(list.size() - 4, list.size());
        return new ResponseEntity<>(recentList, HttpStatus.OK);
    }

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    Long userId = ((UserDetails) authentication.getPrincipal()).getId();
//
//    List<WebScraping> list = userService.getUserwebscrapingList(userId);
//        return new RsssesponseEntity<>(list, HttpStatus.OK);
//





    //아래 두개 메소드 추가
//    @ApiOperation(value = "즐겨찾기 추가", notes = "즐겨찾기 추가 api")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "즐겨찾기 추가 성공"),
//            @ApiResponse(code = 400, message = "즐겨찾기 추가 실패")
//    })
//    @PostMapping("/{userId}/favorites/add")
//    public ResponseEntity<String> addWebScrapingToFavorites(@PathVariable Long userId, @RequestParam Long webScrapingId) {
//        try {
//            userService.addWebScrapingToFavorites(userId, webScrapingId);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @ApiOperation(value = "즐겨찾기 리스트", notes = "즐겨찾기 리스트 api")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "즐겨찾기 리스트 보여주기 성공"),
//            @ApiResponse(code = 400, message = "즐겨찾기 리스트 추출 실패")
//    })
//    @GetMapping("/{userId}/favorites")
//    public ResponseEntity<List<WebScraping>> getFavoriteWebScrapings(@PathVariable Long userId) {
//        try {
//            List<WebScraping> favoriteWebScrapings = userService.getFavoriteWebScrapings(userId);
//            return new ResponseEntity<>(favoriteWebScrapings, HttpStatus.OK);
//        }catch (DataNotFoundException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }




}
