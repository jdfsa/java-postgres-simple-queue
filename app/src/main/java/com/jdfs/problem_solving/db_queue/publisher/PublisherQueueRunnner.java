package com.jdfs.problem_solving.db_queue.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile({"publisher", "all"})
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
