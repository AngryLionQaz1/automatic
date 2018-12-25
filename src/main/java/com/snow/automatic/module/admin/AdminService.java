package com.snow.automatic.module.admin;

import com.snow.automatic.common.bean.Result;
import com.snow.automatic.common.pojo.Role;
import com.snow.automatic.common.pojo.User;
import com.snow.automatic.common.repository.RoleRepository;
import com.snow.automatic.common.repository.UserRepository;
import com.snow.automatic.common.util.PasswordEncoderUtils;
import com.snow.automatic.config.annotation.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Author("god")
    public Result add(String username, String password) {
        Optional<Role> optionalRole=roleRepository.findById(1L);
        if (!optionalRole.isPresent())roleRepository.save(Role.builder().name("admin").build());
        Role role=roleRepository.findById(1L).get();
        return Result.success(userRepository.save(User.builder().username(username).password(PasswordEncoderUtils.encode(password)).roles(Arrays.asList(role)).createTime(LocalDateTime.now()).build()));
    }

    @Author("god")
    public Result delete(Long id) {
        Optional<User> o=userRepository.findById(id);
        if (!o.isPresent())return Result.fail();
        userRepository.delete(o.get());
        return Result.success();
    }
}
