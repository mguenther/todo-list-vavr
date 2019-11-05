package net.mguenther.todo.api;

import io.swagger.annotations.*;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mguenther.todo.domain.Fault;
import net.mguenther.todo.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "Todo List Item Controller", description = "Provides the means to manage todo list items.")
public class ItemController {

    private final ItemService service;

    private final ItemReprConverter converter;

    @ApiOperation(value = "Creates a new todo list item.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The todo list item has been successfully created.", response = ShowItemRepr.class),
            @ApiResponse(code = 400, message = "The data describing the new todo list item is invalid."),
            @ApiResponse(code = 500, message = "An internal error occurred while processing the create item request.")
    })
    @PostMapping(path = "/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@ApiParam(value = "CreateItem", required = true) @RequestBody @Valid final CreateItemRepr createItemRepr) {
        final Either<ResponseEntity<FaultRepr>, ResponseEntity<ShowItemRepr>> response = service
                .createItem(createItemRepr.getDescription())
                .peekLeft(this::logWarningIfAny)
                .bimap(converter::toFaultRepr, converter::toItemCreatedRepr);
        return response.isRight() ? response.get() : response.getLeft();
    }

    @ApiOperation(value = "Shows the todo list item with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The response contains a detailed representation of the todo list item with the given ID.", response = ShowItemRepr.class),
            @ApiResponse(code = 500, message = "An internal error occurred while retrieving the detailed representation of the requested todo list item.")
    })
    @GetMapping(path = "/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showItem(@PathVariable("itemId") final String itemId) {
        final Either<ResponseEntity<FaultRepr>, ResponseEntity<ShowItemRepr>> response = service
                .findItem(itemId)
                .peekLeft(this::logWarningIfAny)
                .bimap(converter::toFaultRepr, converter::toItemRepr);
        return response.isRight() ? response.get() : response.getLeft();
    }

    @ApiOperation(value = "Closes the todo list item with the given (business) ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The todo list item with the given ID has been successfully closed."),
            @ApiResponse(code = 500, message = "An internal error occurred while processing the close item request.")
    })
    @DeleteMapping(path = "/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> closeItem(@ApiParam(value = "itemId", required = true) @PathVariable("itemId") final String itemId) {
        final Either<ResponseEntity<FaultRepr>, ResponseEntity<Void>> response = service
                .closeItem(itemId)
                .peekLeft(this::logWarningIfAny)
                .bimap(converter::toFaultRepr, converter::toDeletedItemRepr);
        return response.isRight() ? response.get() : response.getLeft();
    }

    private void logWarningIfAny(final Fault fault) {
        log.warn("Request failed due to: '{}'", fault.getDescription(), fault.getCause());
    }
}
