package me.espere.feelings.web.lookup;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Collection;

@Value
public class LookupResponse {
    private Collection<LookupResponseEntry> entries;

    @Value
    public static class LookupResponseEntry {
        private String word;
        private BigDecimal valence;
        private BigDecimal arousal;
        private BigDecimal dominance;
    }
}
