package com.snow.automatic.module.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "前端项目")
@RestController
@RequestMapping("web")
public class WebController {



    @Autowired
    private WebService webService;

    @GetMapping("projects")
    @ApiOperation(value = "获取项目列表")
    public ResponseEntity projects(@ApiParam(value = "第几页",required = true)@RequestParam Integer page,
                                   @ApiParam(value = "多少条",required = true)@RequestParam Integer pagesize){
        return ResponseEntity.ok(webService.projects(page,pagesize));
    }


    @PostMapping("delete")
    @ApiOperation(value = "删除项目")
    public ResponseEntity delete(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(webService.delete(id));
    }

    @PostMapping("project")
    @ApiOperation(value = "添加项目")
    public ResponseEntity project(@ApiParam(value = "项目名", required = true) @RequestParam String projectName,
                                  @ApiParam(value = "创库地址", required = true) @RequestParam String gitPath,
                                  @ApiParam(value = "项目描述", required = true) @RequestParam String projectContent) {
        return ResponseEntity.ok(webService.addProject(projectName, gitPath, projectContent));
    }
    @GetMapping("jenkins")
    @ApiOperation(value = "更新项目")
    public ResponseEntity jenkins(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(webService.jenkins(id));
    }




}
