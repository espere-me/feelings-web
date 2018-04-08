package me.espere.feelings.web.evaluate;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Collection;

@Value
public class EvaluateResponse {
    private EvaluateResponseFeeling feeling;

    private Collection<EvaluateResponseEntry> entries;

    @Value
    public static class EvaluateResponseFeeling {
        private BigDecimal valence;
        private BigDecimal arousal;
        private BigDecimal dominance;
    }

    @Value
    public static class EvaluateResponseEntry {
        private String word;
        private BigDecimal valence;
        private BigDecimal arousal;
        private BigDecimal dominance;
    }
}

