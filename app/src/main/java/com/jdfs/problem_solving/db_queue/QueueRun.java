package com.jdfs.problem_solving.db_queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class QueueRun {

    private final QueueProcessor processor;

    @Scheduled(fixedRate = 100)
    public void run() {
        try {
            processor.readAndProcess();
        }
        catch (RuntimeException e) {
            log.error("exceção não esperada, retomando processamento", e);
        }
    }

    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            try {
                processor.readAndProcess();
            }
            catch (RuntimeException e) {
                log.error("exceção não esperada, retomando processamento", e);
            }
        }
    }
}
