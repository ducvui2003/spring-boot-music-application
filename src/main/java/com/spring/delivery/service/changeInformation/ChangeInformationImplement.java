package com.spring.delivery.service.changeInformation;

import com.spring.delivery.domain.request.UpdateInformation;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeInformationImplement implements ChangeInformation{
    @Autowired
    UserRepository userRepository;

    @Override
    public User changeInformation(UpdateInformation updateInformation) {
     String email =   SecurityUtil.getCurrentUserLogin().get();
     User user = userRepository.getUserByEmail(email);
     user.setFullName(updateInformation.getFullName());
        user.setPhoneNumber(updateInformation.getPhoneNumber());
        user.setSex(updateInformation.isSex());
        user.setBirthDay(updateInformation.getBirthDay());
        return user;
    }
}
