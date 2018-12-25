package com.snow.automatic;

import org.junit.Test;

import java.io.*;

public class ExecTest {


    @Test
    public void test() throws Exception {

        String cmd = "cmd /c dir c:\\windows";
               cmd = "npm.cmd -version";
               cmd = "node C:\\Users\\win\\WebstormProjects\\untitled\\build\\build.js";
        final Process process = Runtime.getRuntime().exec(cmd);
        printMessage(process.getInputStream());
        int value = process.waitFor();
        System.out.println(value);

    }


    private static void printMessage(final InputStream input) {
        new Thread(new Runnable() {

            public void run() {
                Reader reader = null;
                try {
                    reader = new InputStreamReader(input,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                BufferedReader bf = new BufferedReader(reader);
                String line = null;
                try {
                    while ((line = bf.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
