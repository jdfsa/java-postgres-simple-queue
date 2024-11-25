package com.jdfs.problem_solving.db_queue.publisher;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
class PublisherQueueProcessor {

    private final PublisherQueueRepository repository;
    private final AmazonSQS sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    @Transactional()
    public void readAndProcessDbQueue() {
        var items = repository.readQueue(5);
        items.forEach(q -> {
            log.info("queue item: {}", q);
            final SendMessageRequest request = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(q.toString());
            sqsClient.sendMessage(request);
        });
        hasException();
    }


    private final Random r = new Random();

    private void hasException() {
        if (r.nextBoolean()) {
            throw new RuntimeException("random exception generated");
        }
    }
}
