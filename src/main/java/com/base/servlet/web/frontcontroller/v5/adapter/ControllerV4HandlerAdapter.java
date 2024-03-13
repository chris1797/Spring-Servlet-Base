package com.base.servlet.web.frontcontroller.v5.adapter;

import com.base.servlet.web.frontcontroller.ModelView;
import com.base.servlet.web.frontcontroller.v4.ControllerV4;
import com.base.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4HandlerAdapter);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // handler가 ControllerV4 타입이므로 다운캐스팅
        ControllerV4 controller = (ControllerV4) handler;

        // paramMap 세팅
        Map<String, String> paramMap = createParamMap(request);

        // ControllerV4의 process 메서드 호출
        HashMap<String, Object> model = new HashMap<>();

        // ModelView 생성
        ModelView mv = new ModelView(controller.process(paramMap, model));
        mv.setModel(model);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
