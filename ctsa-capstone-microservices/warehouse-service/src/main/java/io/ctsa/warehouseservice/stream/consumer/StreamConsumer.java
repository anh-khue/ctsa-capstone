package io.ctsa.warehouseservice.stream.consumer;

import io.ctsa.warehouseservice.model.Recruitment;
import io.ctsa.warehouseservice.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StreamConsumer {

    private final RecruitmentService recruitmentService;

    public StreamConsumer(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @StreamListener(ConsumerChannels.CREATE_RECRUITMENT_DIRECT_INPUT)
    public void createRecruitment(@Payload Recruitment recruitment) {
        log.info("Received message for creating new recruitment.");
        log.info(recruitment.getSource());
        try {
//            recruitment = recruitmentService.saveRecruitment(recruitment);
//            log.info("New recruitment saved with ID = " + recruitment.getId());
        } catch (Exception e) {
            log.info("Failed to save new recruitment.");
        }
    }
}
