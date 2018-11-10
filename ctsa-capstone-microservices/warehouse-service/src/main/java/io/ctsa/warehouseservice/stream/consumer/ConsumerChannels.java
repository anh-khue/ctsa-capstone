package io.ctsa.warehouseservice.stream.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {

    String CREATE_RECRUITMENT_DIRECT_INPUT = "createRecruitmentDirectInput";

    @Input(CREATE_RECRUITMENT_DIRECT_INPUT)
    SubscribableChannel createRecruitmentDirectInput();
}
