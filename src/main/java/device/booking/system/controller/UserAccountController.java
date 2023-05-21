package device.booking.system.controller;

import device.booking.system.model.LoginUser;
import device.booking.system.model.request.UserRequest;
import device.booking.system.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;


@RestController
@RequestMapping("/api/user")
public class UserAccountController {
    @Autowired
    private Validator validator;

    @Autowired
    private UserAccountService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserRequest userRequest) {
        if (service.registerUser(userRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUser loginUser) {
        return ResponseEntity.ok(service.authenticateUser(loginUser));
    }
}
