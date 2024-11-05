package com.spring.delivery.controller;

import com.spring.delivery.domain.request.UpdateInformation;
import com.spring.delivery.model.User;
import com.spring.delivery.service.changeInformation.ChangeInformationImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private ChangeInformationImplement changeInformationImplement;

    @PutMapping("/change-information")
    public User changeInformation(@RequestBody UpdateInformation updateInformation){
    return   changeInformationImplement.changeInformation(updateInformation);
    }
}
