package com.jdfs.problem_solving.db_queue.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile({"consumer", "all"})
@Slf4j
@Component
@RequiredArgsConstructor
class ConsumerQueueRunnner {

    private final ConsumerQueueProcessor processor;

    @Scheduled(fixedRate = 100)
    public void run() {
        try {
            processor.readAndProcess();
        }
        catch (RuntimeException e) {
            log.error("unexpected exception, the messages read will be kept in the 'queue'", e);
        }
    }
}
