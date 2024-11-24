package com.jdfs.problem_solving.db_queue.producer;

import de.svenjacobs.loremipsum.LoremIpsum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile({"producer", "all"})
@Slf4j
@Component
@RequiredArgsConstructor
class ProducerQueueRunner {

    private final ProducerQueueRepository repository;
    private final LoremIpsum loremIpsum = new LoremIpsum();
    private final Random random = new Random();

    @Scheduled(fixedRate = 100)
    public void run() {
        try {
            final ProducerQueueItem item = new ProducerQueueItem();
            item.setContent(loremIpsum.getWords(random.nextInt(49), random.nextInt(50)));
            repository.save(item);
        } catch (RuntimeException e) {
            log.error("failed to insert in the queue", e);
            throw e;
        }
    }
}
