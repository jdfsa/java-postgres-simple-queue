package com.jdfs.problem_solving.db_queue.publisher;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile({"publisher", "producer-all", "all"})
@Slf4j
@Component
@RequiredArgsConstructor
class PublisherQueueRunnner {

    private final PublisherQueueProcessor processor;

    @Scheduled(fixedDelay = 10)
    public void run() {
        try {
            processor.readAndProcessDbQueue();
        }
        catch (RuntimeException e) {
            log.error("unexpected exception, the messages read will be kept in the 'queue'", e);
        }
    }
}
