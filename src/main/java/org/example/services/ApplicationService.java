package org.example.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationService {
    private int count;

    public int getCount() {
        return ++count;
}


    @PostConstruct
    public void init(){
        System.out.println("App object created ");
    }


    @PreDestroy
    public  void  Kill(){

        System.out.println("App object killed");
    }
}
