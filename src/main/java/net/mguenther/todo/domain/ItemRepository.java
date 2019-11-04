package net.mguenther.todo.domain;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Option<Item> findByItemId(String itemId);
}
