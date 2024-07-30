package mvc.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping")
public class MappingClassController {

    /**
     * 회원 목록 조회 : GET /users
     * 회원 등록 : POST /users
     * 회원 조회 : GET /users/{userId}
     * 회원 수정 : PATCH /users/{userId}
     * 회원 삭제 : DELETE /users/{userId}
     */

    @GetMapping("/users")
    public String users() {
        return "get users";
    }

    @PostMapping("/users")
    public String addUser() {
        return "post user";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId = " + userId;
    }

    @PatchMapping("/users/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId = " + userId;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId = " + userId;
    }
}
