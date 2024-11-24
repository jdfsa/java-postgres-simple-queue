package com.jdfs.problem_solving.db_queue.consumer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "queue")
class ConsumerQueueItem {

    @Id
    private Integer id;

    private String content;
}
