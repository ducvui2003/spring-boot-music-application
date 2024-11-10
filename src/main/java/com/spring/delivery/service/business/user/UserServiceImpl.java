package com.spring.delivery.service.business.user;

import com.spring.delivery.domain.request.RequestUpdateInformation;
import com.spring.delivery.domain.response.ResponseUpdateInformation;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper = UserMapper.INSTANCE;

    @Override

    public void buyPremium() {
        String email = SecurityUtil.getCurrentUserLogin().get();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (user.isPremium()) {
            throw new AppException(AppErrorCode.USER_ALREADY_PREMIUM);
        }
        user.setPremium(true);
        userRepository.save(user);
    }

    @Override
    public ResponseUpdateInformation changeInformation(RequestUpdateInformation updateInformation) {
        String email = SecurityUtil.getCurrentUserLogin().get();
        User user = userRepository.getUserByEmail(email);
        user.setFullName(updateInformation.getFullName());
        user.setPhoneNumber(updateInformation.getPhoneNumber());
        user.setSex(updateInformation.isSex());
        user.setBirthDay(updateInformation.getBirthDay());
        userRepository.save(user);
        return userMapper.toResponseUpdateInformation(user);
    }
}
