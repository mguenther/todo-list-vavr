package net.mguenther.todo.api;

import net.mguenther.todo.domain.Fault;
import net.mguenther.todo.domain.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ItemReprConverter {

    ResponseEntity<FaultRepr> toFaultRepr(final Fault fault) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(convertToFaultRepr(fault));
    }

    ResponseEntity<ShowItemRepr> toItemCreatedRepr(final Item item) {
        return ResponseEntity.created(URI.create("/items/" + item.getItemId())).body(convertToShowItemRepr(item));
    }

    ResponseEntity<ShowItemRepr> toItemRepr(final Item item) {
        return ResponseEntity.ok(convertToShowItemRepr(item));
    }

    ResponseEntity<Void> toDeletedItemRepr(final Item item) {
        return ResponseEntity.noContent().build();
    }

    private FaultRepr convertToFaultRepr(final Fault fault) {
        final FaultRepr faultRepr = new FaultRepr();
        faultRepr.setDescription(fault.getDescription());
        return faultRepr;
    }

    private ShowItemRepr convertToShowItemRepr(final Item item) {
        final ShowItemRepr showItemRepr = new ShowItemRepr();
        showItemRepr.setItemId(item.getItemId());
        showItemRepr.setDescription(item.getDescription());
        showItemRepr.setCreated(item.getCreated());
        showItemRepr.setClosed(item.getClosed());
        showItemRepr.setClosedAt(item.getClosedAt());
        return showItemRepr;
    }
}
