package com.jdfs.problem_solving.db_queue.producer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProducerQueueRepository extends CrudRepository<ProducerQueueItem, Integer> {
}