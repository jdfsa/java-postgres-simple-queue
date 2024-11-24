package com.jdfs.problem_solving.db_queue.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
class ConsumerQueueProcessor {

    private final ConsumerQueueRepository repository;

    @Transactional()
    public void readAndProcess() {
        var items = repository.readQueue(5);
        items.forEach(q -> log.info("queue item: {}", q));
        hasException();
    }


    private final Random r = new Random();

    private void hasException() {
        if (r.nextBoolean()) {
            throw new RuntimeException("random exception generated");
        }
    }
}
