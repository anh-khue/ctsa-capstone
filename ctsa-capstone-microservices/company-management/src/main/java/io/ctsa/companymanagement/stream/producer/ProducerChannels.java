package io.ctsa.companymanagement.stream.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {

    String CREATE_RECRUITMENT_DIRECT_OUTPUT = "createRecruitmentDirectOutput";

    @Output(CREATE_RECRUITMENT_DIRECT_OUTPUT)
    MessageChannel createRecruitmentDirectOutput();
}
