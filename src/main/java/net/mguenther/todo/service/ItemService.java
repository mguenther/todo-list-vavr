package net.mguenther.todo.service;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mguenther.todo.domain.Fault;
import net.mguenther.todo.domain.Item;
import net.mguenther.todo.domain.ItemRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Either<Fault, Item> createItem(final String description) {
        return Try.of(() -> createItemInternal(description))
                .map(Either::<Fault, Item>right)
                .onSuccess(e -> log.info("The item with ID '{}' has been created.", e.get().getItemId()))
                .recover(cause -> Either.left(Fault.of("Unable to create the given item.", cause)))
                .get();
    }

    private Item createItemInternal(final String description) {
        final Item item = new Item(description);
        return repository.save(item);
    }

    public Either<Fault, Item> findItem(final String itemId) {
        return repository.findByItemId(itemId)
                .toTry(() -> new IllegalArgumentException("An item with ID '" + itemId + "' does not exist."))
                .map(Either::<Fault, Item>right)
                .recover(cause -> Either.left(Fault.of("Unable to find item with ID '" + itemId + "'.", cause)))
                .get();
    }

    public Either<Fault, Item> closeItem(final String itemId) {
        return repository.findByItemId(itemId)
                .toTry(() -> new IllegalArgumentException("An item with ID '" + itemId + "' does not exist."))
                .map(this::closeItemInternal)
                .map(Either::<Fault, Item>right)
                .onSuccess(e -> log.info("The item with ID '{}' has been closed.", e.get().getItemId()))
                .recover(cause -> Either.left(Fault.of("Unable to close item with ID '" + itemId + "'.", cause)))
                .get();
    }

    private Item closeItemInternal(final Item item) {
        item.conclude();
        return repository.save(item);
    }
}
