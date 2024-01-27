package com.base.servlet.web.frontcontroller.v3;

import com.base.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, Object> paramMap);
}
