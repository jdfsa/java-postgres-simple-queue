package com.jdfs.problem_solving.db_queue.consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile({"consumer", "all"})
@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerQueueRunner {

    private final AmazonSQS sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    @Scheduled(fixedDelay = 10)
    public void run() {
        try {
            final var result = sqsClient.receiveMessage(queueUrl);
            for (final Message msg : result.getMessages()) {
                sqsClient.deleteMessage(queueUrl, msg.getReceiptHandle());
                log.info("sqs received message: {}", msg.toString());
            }
        }
        catch (RuntimeException e) {
            log.error("unexpected exception, the messages read will be kept in the 'queue'", e);
        }
    }
}
