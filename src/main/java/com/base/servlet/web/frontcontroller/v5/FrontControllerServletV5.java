package com.base.servlet.web.frontcontroller.v5;

import com.base.servlet.web.frontcontroller.ModelView;
import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.base.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.base.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.base.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// FrontController -> DispatcherServlet
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // Springboot의 HandlerMapping
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberFormControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberFormControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 요청에 맞는 핸들러 찾기
        Object handler = getHandler(request);

        // 핸들러가 없는 경우 404 응답
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 어댑터 찾기
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 핸들러 어댑터로 핸들러 실행
        ModelView mv = adapter.handle(request, response, handler);

        // 핸들러에서 return 받은 ModelView를 view로 변환
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        // model에 있는 데이터를 모두 request에 담아서 view에 전달
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        // Springboot의 ViewResolver
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        return handlerMappingMap.get(request.getRequestURI());
    }
}
