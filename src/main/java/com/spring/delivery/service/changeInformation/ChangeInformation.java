package com.spring.delivery.service.changeInformation;

import com.spring.delivery.domain.request.UpdateInformation;
import com.spring.delivery.model.User;

public interface ChangeInformation {
    public User changeInformation(UpdateInformation updateInformation);
}
