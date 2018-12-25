package com.snow.automatic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.Test;

public class ssx {


    @Test
    public  void test() {

        try {
            Process p = null;
            String line = null;
            BufferedReader stdout = null;
            String command = "npm";
            p = Runtime.getRuntime().exec(command);
            stdout = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            stdout.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




