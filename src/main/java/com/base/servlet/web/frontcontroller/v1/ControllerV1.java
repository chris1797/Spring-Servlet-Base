package com.base.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

public interface ControllerV1 {

    void proceess(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException, ServletException;
}
