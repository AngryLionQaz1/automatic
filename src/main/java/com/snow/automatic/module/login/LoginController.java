package com.snow.automatic.module.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录")
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping
    @ApiOperation(value = "登录")
    public ResponseEntity login(@ApiParam(value = "账号",required = true)@RequestParam String username,
                                @ApiParam(value = "密码",required = true)@RequestParam String password){
        return ResponseEntity.ok(loginService.login(username,password));
    }






}
