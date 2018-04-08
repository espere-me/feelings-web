package me.espere.feelings.web.api.lookup;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class LookupRequest {
    private Collection<String> words;
}
