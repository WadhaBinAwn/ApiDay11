package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.services.ApplicationService;
import org.example.services.RequestService;
import org.example.services.SessionService;

@Path("scopes")
public class ScopesController {


    @Inject RequestService reqServ;
    @Inject SessionService sessServ;
    @Inject ApplicationService appServ;
    @GET
    public String getIt() {
        return "Got it: App: " + appServ.getCount() + ", Session: " + sessServ.getCount() + ", Request: " + reqServ.getCount();
}}
