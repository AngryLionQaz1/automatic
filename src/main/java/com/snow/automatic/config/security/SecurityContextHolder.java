package com.snow.automatic.config.security;


import com.snow.automatic.common.pojo.User;
import org.springframework.stereotype.Component;

@Component
public  class SecurityContextHolder {

    private static ThreadLocal<User> securityContext=new ThreadLocal<>();
    private static ThreadLocal<String> localId=new ThreadLocal<>();

    public void setId(String id){
        localId.set(id);
    }
    public void removeId(){
        localId.remove();
    }
    public String getId(){
        return localId.get();
    }

    public void setUser(User user){
        securityContext.set(user);
    }

    public void removeUser(){
        securityContext.remove();
    }


    public User getSecurityContext(){
     return securityContext.get();
    }






}
