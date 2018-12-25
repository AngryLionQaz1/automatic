package com.snow.automatic.module.web;

import com.snow.automatic.common.bean.Config;
import com.snow.automatic.common.bean.Result;
import com.snow.automatic.common.bean.Tips;
import com.snow.automatic.common.pojo.Web;
import com.snow.automatic.common.repository.WebRepository;
import com.snow.automatic.common.util.ShellUtils;
import com.snow.automatic.config.annotation.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WebService {

    @Autowired
    private WebRepository webRepository;
    @Autowired
    private Config config;

    @Author
    public Result addProject(String projectName, String gitPath, String projectContent) {
        if (webRepository.findByProjectName(projectName).isPresent())return Result.fail(Tips.PROJECT_HAD.msg);
        webRepository.save(Web.builder().projectName(projectName).gitPath(gitPath).projectContent(projectContent).createTime(LocalDateTime.now()).build());
        try {
            ShellUtils.createFile(config.getTomcatWebapp(),projectName+".sh",ShellUtils.webShell(config.getTomcatWebapp(),projectName,gitPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(ShellUtils.execShell(config.getTomcatWebapp()+"/"+projectName+".sh"));
    }
    @Author("god")
    public Result delete(Long id) {
        Optional<Web> o=webRepository.findById(id);
        if (!o.isPresent())return Result.fail();
        webRepository.delete(o.get());
        ShellUtils.removeWebProject(config.getTomcatWebapp(),o.get().getProjectName());
        ShellUtils.removeWebProject(config.getTomcatWebapp(),o.get().getProjectName()+".sh");
        return Result.success();
    }
    @Author
    public Result jenkins(Long id) {
        Optional<Web> o=webRepository.findById(id);
        if (!o.isPresent())return Result.fail();
        return Result.success(ShellUtils.execShell(config.getTomcatWebapp()+"/"+o.get().getProjectName()+".sh"));
    }

    @Author
    public Result projects(Integer page, Integer pagesize) {
        if (page>0)page--;
        return Result.success(webRepository.findAll(PageRequest.of(page,pagesize)));
    }

}
