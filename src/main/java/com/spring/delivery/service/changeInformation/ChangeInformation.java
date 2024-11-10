package com.spring.delivery.service.changeInformation;

import com.spring.delivery.domain.request.RequestUpdateInformation;
import com.spring.delivery.domain.response.ResponseUpdateInformation;
import com.spring.delivery.model.User;

public interface ChangeInformation {
    public ResponseUpdateInformation changeInformation(RequestUpdateInformation updateInformation);
}
