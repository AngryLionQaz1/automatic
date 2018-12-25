package com.snow.automatic.module.shell;

import com.snow.automatic.common.bean.Result;
import com.snow.automatic.common.bean.Tips;
import com.snow.automatic.common.pojo.Project;
import com.snow.automatic.common.repository.ProjectRepository;
import com.snow.automatic.common.util.ShellUtils;
import com.snow.automatic.config.annotation.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShellService {


    @Autowired
    private ProjectRepository projectRepository;


    @Author
    public Result addProject(String projectName, String jarName, String gitPath,String projectContent) {
        if (projectRepository.findByProjectName(projectName).isPresent())return Result.fail(Tips.PROJECT_HAD.msg);
        //项目文件初始化
        String str=projectInit(projectName,jarName,gitPath);
        projectRepository.save(Project.builder().projectName(projectName).jarName(jarName).gitPath(gitPath).projectContent(projectContent).createTime(LocalDateTime.now()).build());
        return Result.success(str);
    }
    @Author
    public Result projectInit(Long id){
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        String str=ShellUtils.getSehllPath(optionalProject.get().getProjectName());
        return Result.success(ShellUtils.execShell(str,"init"));
    }

    private String projectInit(String projectName, String jarName, String gitPath) {
        try {
            ShellUtils.createFile(projectName,projectName+".sh",ShellUtils.shellStr(projectName,jarName,gitPath));
           return ShellUtils.execShell(ShellUtils.getSehllPath(projectName),"init");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Author
    public Result start(Long id) {
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        String str=ShellUtils.getSehllPath(optionalProject.get().getProjectName());
        return Result.success(ShellUtils.execShell(str,"start"));
    }


    @Author
    public Result status(Long id) {
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        String str=ShellUtils.getSehllPath(optionalProject.get().getProjectName());
        return Result.success(ShellUtils.execShell(str,"status"));
    }


    @Author
    public Result stop(Long id) {
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        String str=ShellUtils.getSehllPath(optionalProject.get().getProjectName());
        return Result.success(ShellUtils.execShell(str,"stop"));
    }

    @Author
    public Result jenkins(Long id) {
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        String str=ShellUtils.getSehllPath(optionalProject.get().getProjectName());
        return Result.success(ShellUtils.execShell(str,"jenkins"));
    }

    @Author("god")
    public Result delete(Long id) {
        Optional<Project> optionalProject=projectRepository.findById(id);
        if (!optionalProject.isPresent())return Result.fail();
        projectRepository.delete(optionalProject.get());
        ShellUtils.execShell(ShellUtils.getSehllPath(optionalProject.get().getProjectName()),"stop");
        ShellUtils.removeProject(optionalProject.get().getProjectName());
        return Result.success();
    }
    @Author
    public Result projects(Integer page, Integer pagesize) {
        if (page>0)page--;
        return Result.success(projectRepository.findAll(PageRequest.of(page,pagesize)));
    }





}
