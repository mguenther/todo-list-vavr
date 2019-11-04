package net.mguenther.todo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "ItemDetails", description = "Shows all details of a todo list item.")
public class ShowItemRepr {

    @JsonProperty(value = "itemId", required = true)
    @ApiModelProperty(value = "This is the ID of the todo list item.", required = true)
    private String itemId;

    @JsonProperty(value = "description", required = true)
    @ApiModelProperty(value = "Describes what needs to be done.", required = true, example = "Buy groceries")
    private String description;

    @JsonProperty(value = "created", required = true)
    @ApiModelProperty(value = "This timestamp refers to the point in time when this item has been created (UTC, milliseconds).", required = true, example = "1571300370779")
    private Long created;

    @JsonProperty(value = "closed", required = true)
    @ApiModelProperty(value = "Determines whether this item has been closed.", required = true, example = "true")
    private Boolean closed;

    @JsonProperty(value = "closedAt")
    @ApiModelProperty(value = "This timestamp refers to the point in time when this item has been concluded (UTC, milliseconds).", example = "1571300370779")
    private Long closedAt;
}
