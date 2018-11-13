package io.ctsa.companymanagement.stream.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StreamProducer<T> {

    private static final boolean SENT = true;
    private static final boolean FAILED = false;

    private final MessageChannel createRecruitmentDirectChannel;

    @Autowired
    public StreamProducer(ProducerChannels channels) {
        this.createRecruitmentDirectChannel = channels.createRecruitmentDirectOutput();
    }

    public void sendMessage(T payload) {
        try {
            createRecruitmentDirectChannel.send(MessageBuilder.withPayload(payload).build());
            log.info("Sent direct message for creating recruitment.");
        } catch (Exception e) {
            log.info("Failed to send direct message for creating recruitment.");
        }
    }
}
