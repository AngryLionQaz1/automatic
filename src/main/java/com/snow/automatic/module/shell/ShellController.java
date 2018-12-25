package com.snow.automatic.module.shell;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "后端项目")
@RestController
@RequestMapping("shell")
public class ShellController {


    @Autowired
    private ShellService shellService;

    @GetMapping("projects")
    @ApiOperation(value = "获取项目列表")
    public ResponseEntity projects(@ApiParam(value = "第几页",required = true)@RequestParam Integer page,
                                   @ApiParam(value = "多少条",required = true)@RequestParam Integer pagesize){
        return ResponseEntity.ok(shellService.projects(page,pagesize));
    }

    @GetMapping("start")
    @ApiOperation(value = "启动")
    public ResponseEntity start(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.start(id));
    }


    @GetMapping("status")
    @ApiOperation(value = "运行状态")
    public ResponseEntity status(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.status(id));
    }

    @GetMapping("stop")
    @ApiOperation(value = "停止")
    public ResponseEntity stop(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.stop(id));
    }

    @GetMapping("jenkins")
    @ApiOperation(value = "更新项目")
    public ResponseEntity jenkins(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.jenkins(id));
    }

    @GetMapping("init")
    @ApiOperation(value = "初始化项目")
    public ResponseEntity init(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.projectInit(id));
    }


    @PostMapping("project")
    @ApiOperation(value = "添加项目")
    public ResponseEntity project(@ApiParam(value = "项目名", required = true) @RequestParam String projectName,
                                  @ApiParam(value = "jar包名", required = true) @RequestParam String jarName,
                                  @ApiParam(value = "创库地址", required = true) @RequestParam String gitPath,
                                  @ApiParam(value = "项目描述", required = true) @RequestParam String projectContent) {
        return ResponseEntity.ok(shellService.addProject(projectName, jarName, gitPath, projectContent));
    }

    @PostMapping("delete")
    @ApiOperation(value = "删除项目")
     public ResponseEntity delete(@ApiParam(value = "项目ID",required = true)@RequestParam Long id){
        return ResponseEntity.ok(shellService.delete(id));
    }





}
