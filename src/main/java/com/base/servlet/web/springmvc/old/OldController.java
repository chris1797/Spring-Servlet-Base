package com.base.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 * Bean의 이름이 /springmvc/old-controller, URL로 매핑
 * 이전 버전의 스프링 MVC 컨트롤러
 * 클라이언트에서 해당 Bean의 이름("/springmvc/old-controller")으로 요청
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
