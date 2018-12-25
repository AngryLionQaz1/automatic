package com.snow.automatic.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="config" )
public class Config {
    /**
     * 请求头
     */
    private String authorization = "authorization";
    /**
     * 存储当前登录token
     */
    private String token = "authorization";
    /**
     * JWT字段名
     */
    private String jwtKey = "AUTHORITIES_KEY";
    /**
     * JWT签名密钥
     */
    private String jwtSecretKey = "secretKey";
    /**
     * JWT有效期
     */
    private Long jwtTokenValidity = 7L;

    /**拦截uri*/
    private String addPath="/token/**";
    /**不拦截uri*/
    private String excludePath="/test/**";

    /**Tomcat webapp 地址*/
    private String tomcatWebapp="/usr/local/tomcat/webapps";


    /**aes 密码*/
    private String aesPassword="qazwsxed";
    /**端口号*/
    private Integer filePort=8080;
    /**本地文件地址*/
    private String  filePath;
    /**IP地址*/
    private String  fileHost;
    /**请求地址*/
    private String  fileUrl="/upload/**";
    /**设置不能上传的文件类型*/
    private String  fileType;





}
