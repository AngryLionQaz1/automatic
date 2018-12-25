package com.snow.automatic.common.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.EnumSet;

import static org.junit.Assert.*;

public class ShellUtilsTest {



    @Test
    public void test6(){

        ShellUtils.removeWebProject("F:\\project\\IdeaProjects\\snow\\automatic\\automatic","automatic.sh");

    }
    @Test
    public void test5() throws IOException {

        String ss="/usr/local/tomcat/webapps";
        String str=ShellUtils.webShell(ss,"webH","ssh://admin@47.92.213.93:29418/~admin/webH.git");
        System.out.println(str);
        ShellUtils.createFile(Paths.get(ss).resolve("webH").toString(),"webH.sh",str);

    }

    @Test
    public void test4() throws IOException {


        Path directory = Paths.get(ShellUtils.path("docker"));
        DeleteDirectory walk = new DeleteDirectory();
        EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(directory, opts, Integer.MAX_VALUE, walk);



    }


    @Test
    public void test3(){

        System.out.println(ShellUtils.getSehllPath("docker"));

    }

    @Test
    public void test2() throws IOException {

        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);

    }

    @Test
    public void test() throws IOException {

        String str=ShellUtils.shellStr("automatic","automatic-0.0.1-SNAPSHOT.jar","ssh://admin@47.92.213.93:29418/~admin/automatic.git");
        System.out.println(str);

        ShellUtils.createFile("automatic","automatic.sh",str);





    }




}