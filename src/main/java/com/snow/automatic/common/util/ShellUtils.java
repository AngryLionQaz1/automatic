package com.snow.automatic.common.util;


import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.EnumSet;

public class ShellUtils {



    /**shell 文件*/
    public static String shellStr(String projectName,String jarName,String gitPath){
        StringBuffer sb=new StringBuffer();
        sb.append("PROJECT_PATH="+ path(projectName));
        sb.append("\n");
        sb.append("APP_NAME="+projectName);
        sb.append("\n");
        sb.append("JAR_NAME="+jarName);
        sb.append("\n");
        sb.append("APP_PATH="+gitPath);
        sb.append("\n");
        sb.append("PID=$APP_NAME\\.pid");
        sb.append("\n");
        sb.append("cd $PROJECT_PATH");
        sb.append("\n");
        sb.append("init(){\n" +
                "git clone $APP_PATH\n" +
                "if [ $? -eq \"0\" ];then\n" +
                "    cd $APP_NAME\n" +
                "    mvn clean package\n" +
                "    cd target\n" +
                "    mv -f $JAR_NAME $PROJECT_PATH\n" +
                "else\n" +
                "    stop\n" +
                "    cd $APP_NAME\n" +
                "    git pull\n" +
                "    mvn clean package\n" +
                "    cd target\n" +
                "    mv -f $JAR_NAME $PROJECT_PATH\n" +
                "fi\n" +
                "}");
        sb.append("\n");
        sb.append("usage() {\n" +
                "    echo \"Usage: sh 执行脚本.sh [start|stop|restart|status|init|jenkins]\"\n" +
                "    exit 1\n" +
                "}");
        sb.append("\n");
        sb.append("is_exist(){\n" +
                "  pid=`ps -ef|grep $JAR_NAME|grep -v grep|awk '{print $2}' `\n" +
                "  #如果不存在返回1，存在返回0\n" +
                "  if [ -z \"${pid}\" ]; then\n" +
                "   return 1\n" +
                "  else\n" +
                "    return 0\n" +
                "  fi\n" +
                "}");
        sb.append("\n");
        sb.append("start(){\n" +
                "  is_exist\n" +
                "  if [ $? -eq \"0\" ]; then\n" +
                "    echo \">>> ${JAR_NAME} is already running PID=${pid} <<<\"\n" +
                "  else\n" +
                "    nohup java -Xms256m -Xmx512m -jar $JAR_NAME >/dev/null 2>&1 &\n" +
                "    echo $! > $PID\n" +
                "    echo \">>> start $JAR_NAME successed PID=$! <<<\"\n" +
                "   fi\n" +
                "  }");
        sb.append("\n");
        sb.append("start_jenkins(){\n" +
                "   cd $PROJECT_PATH\n" +
                "  is_exist\n" +
                "  if [ $? -eq \"0\" ]; then\n" +
                "    echo \">>> ${JAR_NAME} is already running PID=${pid} <<<\"\n" +
                "  else\n" +
                "    nohup java -Xms256m -Xmx512m -jar $JAR_NAME >/dev/null 2>&1 &\n" +
                "    echo $! > $PID\n" +
                "    echo \">>> start $JAR_NAME successed PID=$! <<<\"\n" +
                "   fi\n" +
                "  }\n");
        sb.append("\n");
        sb.append("stop(){\n" +
                "  #is_exist\n" +
                "  pidf=$(cat $PID)\n" +
                "  #echo \"$pidf\"\n" +
                "  echo \">>> api PID = $pidf begin kill $pidf <<<\"\n" +
                "  kill $pidf\n" +
                "  rm -rf $PID\n" +
                "  sleep 2\n" +
                "  is_exist\n" +
                "  if [ $? -eq \"0\" ]; then\n" +
                "    echo \">>> api 2 PID = $pid begin kill -9 $pid  <<<\"\n" +
                "    kill -9  $pid\n" +
                "    sleep 2\n" +
                "    echo \">>> $JAR_NAME process stopped <<<\"\n" +
                "  else\n" +
                "    echo \">>> ${JAR_NAME} is not running <<<\"\n" +
                "  fi\n" +
                "}");
        sb.append("\n");
        sb.append("status(){\n" +
                "  is_exist\n" +
                "  if [ $? -eq \"0\" ]; then\n" +
                "    echo \">>> ${JAR_NAME} is running PID is ${pid} <<<\"\n" +
                "  else\n" +
                "    echo \">>> ${JAR_NAME} is not running <<<\"\n" +
                "  fi\n" +
                "}");
        sb.append("\n");
        sb.append("restart(){\n" +
                "  stop\n" +
                "  start\n" +
                "}");
        sb.append("\n");
        sb.append("jenkins(){\n" +
                "  init\n" +
                "  start_jenkins\n" +
                "}");
        sb.append("\n");
        sb.append("case \"$1\" in\n" +
                "  \"start\")\n" +
                "    start\n" +
                "    ;;\n" +
                "  \"stop\")\n" +
                "    stop\n" +
                "    ;;\n" +
                "  \"status\")\n" +
                "    status\n" +
                "    ;;\n" +
                "  \"init\")\n" +
                "    init\n" +
                "    ;;\n" +
                "  \"jenkins\")\n" +
                "    jenkins\n" +
                "    ;;\n" +
                "  \"restart\")\n" +
                "    restart\n" +
                "    ;;\n" +
                "  *)\n" +
                "    usage\n" +
                "    ;;\n" +
                "esac\n" +
                "exit 0");
        sb.append("\n");
        return sb.toString();
    }



    /**web*/
    public static String webShell(String tomcatWebapp,String projectName,String gitPath){
        StringBuffer sb=new StringBuffer();
        sb.append("TOMCAT_WEBAPP=").append(tomcatWebapp);
        sb.append("\n");
        sb.append("APP_PATH=").append(gitPath);
        sb.append("\n");
        sb.append("APP_NAME=").append(projectName);
        sb.append("\n");
        sb.append("cd $TOMCAT_WEBAPP");
        sb.append("\n");
        sb.append("git clone $APP_PATH");
        sb.append("\n");
        sb.append("if [ $? -eq \"0\" ];then\n" +
                "   echo $(cd `dirname $0`; pwd)\n" +
                "else\n" +
                "    cd $APP_NAME\n" +
                "    echo $(cd `dirname $0`; pwd)\n" +
                "    git pull\n" +
                "fi");
        sb.append("\n");
        return sb.toString();
    }



    /**删除项目*/
    public static void removeWebProject(String tomcatWebapp,String projectName){


        Path directory = Paths.get(tomcatWebapp).resolve(projectName);
        DeleteDirectory walk = new DeleteDirectory();
        EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        try {
            Files.walkFileTree(directory, opts, Integer.MAX_VALUE, walk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**删除项目*/
    public static void removeProject(String projectName){
        Path directory = Paths.get(ShellUtils.path(projectName));
        DeleteDirectory walk = new DeleteDirectory();
        EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        try {
            Files.walkFileTree(directory, opts, Integer.MAX_VALUE, walk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**详情项目地*/
    public static  String getSehllPath(String projectName){


        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
           return Paths.get(courseFile).resolve(projectName).resolve(projectName+".sh").toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
       return courseFile;
    }
    public static String path(String projectName) {
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
            return Paths.get(courseFile).resolve(projectName).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseFile;
    }



    /**生成文件*/
    public static void createFile(String path,String name,String str) throws IOException {

        Path p=Paths.get(path).resolve(name);
        if(!Files.exists(FileSystems.getDefault().getPath(path))) Files.createDirectories(FileSystems.getDefault().getPath(path));
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(new File(p.toUri()));
            FileChannel channel=fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode(str);
//            ByteBuffer src = Charset.forName("ISO-8859-1").encode(str);
            int length = 0;
            while ((length = channel.write(src)) != 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**生成文件*/
    public static  void createFile (String path, String name, InputStream input){
        try {
            Files.copy(input, Paths.get(path).resolve(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 执行shell
     * @param execCmd 传入脚本或者命令
     * @param para 传入参数
     */
    private static void execShell(boolean execCmd, String... para) {
        StringBuffer paras = new StringBuffer();
        Arrays.stream(para).forEach(x -> paras.append(x).append(" "));
        try {
            String cmd = "", shpath = "";
            if (execCmd) {
                // 命令模式
                shpath = "echo";
            } else {
                shpath = "/Users/yangyibo/Desktop/callShell.sh";

            }
            cmd = shpath + " " + paras.toString();
            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解决了 参数中包含 空格和脚本没有执行权限的问题
     * @param scriptPath 脚本路径
     * @param para 参数数组
     */
    public static String execShell(String scriptPath, String ... para) {
        try {
            String[] cmd = new String[]{scriptPath};
            //为了解决参数中包含空格
            cmd= addAll(cmd,para);

            //解决脚本没有执行权限
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755",scriptPath);
            Process process = builder.start();
            process.waitFor();

            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            //执行结果
           return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static <T> T[] addAll(T[] array1, T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            Class<?> type1 = array1.getClass().getComponentType();
            T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);

            try {
                System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
                return joinedArray;
            } catch (ArrayStoreException var6) {
                Class<?> type2 = array2.getClass().getComponentType();
                if (!type1.isAssignableFrom(type2)) {
                    throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), var6);
                } else {
                    throw var6;
                }
            }
        }
    }

    public static <T> T[] clone(T[] array) {
        return array == null ? null : (T[]) array.clone();
    }



}
