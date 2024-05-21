package hello.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        // .addAttribute()를 사용하려면 Model을 파라미터로 받아야 한다.
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        /**
         * void로 반환하는 경우 요청 URL을 참고해서 논리 뷰 이름으로 사용한다.
         * 요청 URL이 논리 뷰 이름이 된다.
         */
        model.addAttribute("data", "hello!");
    }
}
