package net.mguenther.todo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "itemId")
@ToString(exclude = "id")
@Table(name = "ITEM")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ITEM_ID", nullable = false, updatable = false)
    private String itemId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CREATED", nullable = false)
    private Long created;

    @Column(name = "CLOSED")
    private Boolean closed;

    @Column(name = "CLOSED_AT")
    private Long closedAt;

    public Item(final String description) {
        this.itemId = UUID.randomUUID().toString();
        this.description = description;
        this.created = Instant.now(Clock.systemUTC()).toEpochMilli();
        this.closed = false;
        this.closedAt = null;
    }

    public void conclude() {
        this.closed = true;
        this.closedAt = Instant.now(Clock.systemUTC()).toEpochMilli();
    }
}
