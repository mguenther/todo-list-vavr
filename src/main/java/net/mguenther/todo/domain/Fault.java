package net.mguenther.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString(of = "description")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Fault {

    @JsonProperty("description")
    private final String description;

    @JsonIgnore
    private final Throwable cause;

    public static Fault of(final Throwable cause) {
        return new Fault(cause.getMessage(), cause);
    }

    public static Fault of(final String description) {
        return new Fault(description, null);
    }

    public static Fault of(final String description, final Throwable cause) {
        return new Fault(description, cause);
    }
}
