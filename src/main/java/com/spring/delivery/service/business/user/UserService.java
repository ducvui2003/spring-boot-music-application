package com.spring.delivery.service.business.user;

import com.spring.delivery.domain.request.RequestUpdateInformation;
import com.spring.delivery.domain.response.ResponseUpdateInformation;

public interface UserService {
     ResponseUpdateInformation changeInformation(RequestUpdateInformation updateInformation);

    void buyPremium();
}
