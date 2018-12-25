package com.snow.automatic.module.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员")
@RestController
@RequestMapping("admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @PostMapping("add")
    @ApiOperation(value = "添加管理员")
    public ResponseEntity add(@RequestParam String username,
                              @RequestParam String password){
        return ResponseEntity.ok(adminService.add(username,password));
    }

    @PostMapping("delete")
    @ApiOperation(value = "删除管理员")
    public ResponseEntity delete(@RequestParam Long id){
        return ResponseEntity.ok(adminService.delete(id));
    }



}
