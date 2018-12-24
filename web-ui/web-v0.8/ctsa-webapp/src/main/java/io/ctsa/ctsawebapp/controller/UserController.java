package io.ctsa.ctsawebapp.controller;

import io.ctsa.ctsawebapp.service.UserService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-in")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest) {
        try {
            return ResponseEntity.status(OK).
                    body(userService.signIn(signInRequest.getUsername(), signInRequest.getPassword())
                                    .orElseThrow(NotFound::new));
        } catch (NotFound notFound) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }
}


class SignInRequest {

    private String username;
    private String password;

    public SignInRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
