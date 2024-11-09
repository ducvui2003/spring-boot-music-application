package com.spring.delivery.service.business.user;

import com.spring.delivery.model.User;
import com.spring.delivery.repository.UserRepository;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

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
}
