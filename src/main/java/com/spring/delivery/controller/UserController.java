package com.spring.delivery.controller;

import com.spring.delivery.domain.request.RequestUpdateInformation;
import com.spring.delivery.domain.response.ResponseUpdateInformation;
import com.spring.delivery.service.changeInformation.ChangeInformationImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private ChangeInformationImplement changeInformationImplement;

    @PutMapping("/change-information")
    public ResponseEntity<ResponseUpdateInformation> changeInformation(@Valid  @RequestBody RequestUpdateInformation updateInformation){
    return ResponseEntity.ok(changeInformationImplement.changeInformation(updateInformation));
    }
}
