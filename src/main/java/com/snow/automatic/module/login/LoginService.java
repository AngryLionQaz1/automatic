package com.snow.automatic.module.login;

import com.snow.automatic.common.bean.Result;
import com.snow.automatic.common.bean.Tips;
import com.snow.automatic.common.pojo.User;
import com.snow.automatic.common.repository.UserRepository;
import com.snow.automatic.common.util.PasswordEncoderUtils;
import com.snow.automatic.config.token.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTToken jwtToken;

    public Result login(String username, String password) {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if (!optionalUser.isPresent())return Result.fail(Tips.PASSWORD_FALSE.msg);
        if (!PasswordEncoderUtils.decode(password,optionalUser.get().getPassword()))return Result.fail(Tips.PASSWORD_FALSE.msg);
        User user=optionalUser.get();
        user.setToken(jwtToken.createToken(String.valueOf(user.getId())));
        return Result.success(user);
    }






}
