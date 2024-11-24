package com.jdfs.problem_solving.db_queue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface QueueRepository extends CrudRepository<QueueItem, Integer> {
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
    Iterable<QueueItem> readQueue(Integer limit);
}
