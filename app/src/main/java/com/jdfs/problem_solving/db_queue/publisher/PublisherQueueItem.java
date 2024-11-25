package com.jdfs.problem_solving.db_queue.publisher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "queue")
class PublisherQueueItem {

    @Id
    private Integer id;

    private String content;

    @Override
    public String toString() {
        return String.format("%d | %s", id, content);
    }
}
