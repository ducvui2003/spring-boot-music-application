package com.spring.delivery.service.changeInformation;

import com.spring.delivery.domain.request.RequestUpdateInformation;
import com.spring.delivery.domain.response.ResponseUpdateInformation;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeInformationImplement implements ChangeInformation{
    UserRepository userRepository;
    UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public ResponseUpdateInformation changeInformation(RequestUpdateInformation updateInformation) {
     String email =   SecurityUtil.getCurrentUserLogin().get();
     User user = userRepository.getUserByEmail(email);
     user.setFullName(updateInformation.getFullName());
        user.setPhoneNumber(updateInformation.getPhoneNumber());
        user.setSex(updateInformation.isSex());
        user.setBirthDay(updateInformation.getBirthDay());
        userRepository.save(user);
        return userMapper.toResponseUpdateInformation(user);
    }
}
