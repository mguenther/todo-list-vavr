package net.mguenther.todo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(value = "CreateItem", description = "Contains the information to create a new todo list item.")
public class CreateItemRepr {

    @NotNull
    @NotEmpty
    @JsonProperty(value = "description", required = true)
    @ApiModelProperty(value = "Describes what needs to be done.", required = true, example = "Buy groceries")
    private String description;
}
