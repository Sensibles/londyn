package pl.arturkufa.londyncommon.kafka.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private long id;
    private String message;

    @JsonCreator
    public TestMessage(@JsonProperty("id") long id,
                       @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
