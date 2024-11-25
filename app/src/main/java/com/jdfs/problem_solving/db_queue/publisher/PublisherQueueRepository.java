package com.jdfs.problem_solving.db_queue.publisher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface PublisherQueueRepository extends CrudRepository<PublisherQueueItem, Integer> {
    @Query(value = """
            delete from queue
            where id in (
            	select id
            	from queue
            	order by id
            	for update skip locked
            	limit :limit
            )
            returning *
            """, nativeQuery = true)
    Iterable<PublisherQueueItem> readQueue(Integer limit);
}
