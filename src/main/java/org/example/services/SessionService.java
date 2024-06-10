package org.example.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import org.example.dao.JobDAO;

import java.io.Serializable;

@SessionScoped
public class SessionService implements Serializable {
    @Inject
    JobDAO dao;
    private int count;

    public int getCount() {
        return ++count;
    }
    @PostConstruct
    public void init(){
        System.out.println("session object created ");
    }


    @PreDestroy
    public  void  Kill(){

        System.out.println("session object killed");
    }
}
