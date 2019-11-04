package net.mguenther.todo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Fault", description = "Represents an error that has occurred while trying to process a business transaction.")
public class FaultRepr {

    @JsonProperty(value = "description", required = true)
    @ApiModelProperty(value = "This is a short client-facing message that describes the error.", required = true)
    private String description;
}
