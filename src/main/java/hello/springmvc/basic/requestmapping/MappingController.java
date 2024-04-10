package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RestController
public class MappingController {

    // RequestMapping은 어떤 HTTP Method를 사용할지 지정하지 않으면 모든 HTTP Method에 대해 매핑된다.
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v2")
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @RequestMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    // @PathVariable과 파라미터 이름이 같다면 생략 가능
    @RequestMapping("/mapping/{userId}")
    public String mappingPath2(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    // path에 params를 추가하여 특정 파라미터가 있을 때만 매핑되도록 할 수 있다.
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    // 특정 헤더가 있을 때만 매핑되도록 할 수 있다.
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // Content-Type이 application/json인 경우에만 매핑되도록 할 수 있다.
    // application/json 외에도 대표적으로 다음과 같은 값들이 있다.
    // - text/html
    // - application/xml
    // - application/* (application 하위의 모든 타입)
    // - */* (모든 타입)
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // Accept라는 헤더가 있을 때만 매핑되도록 할 수 있다.
    // Accept header는 클라이언트가 서버에게 받고 싶은 타입을 알려주는 것이다.
    // Accept header에 application/json이 있을 때만 매핑된다.
    // MediaType.APPLICATION_JSON_VALUE는 application/json을 나타낸다.
    @PostMapping(value = "/mapping-produce", produces = MediaType.APPLICATION_JSON_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
