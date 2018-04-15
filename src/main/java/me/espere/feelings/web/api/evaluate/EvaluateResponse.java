package me.espere.feelings.web.api.evaluate;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Collection;

@Value
public class EvaluateResponse {
    private EvaluateResponseFeeling feeling;

    private Collection<EvaluateResponseWordAnalysis> wordAnalyses;

    @Value
    public static class EvaluateResponseFeeling {
        private BigDecimal valence;
        private BigDecimal arousal;
        private BigDecimal dominance;
    }

    @Value
    public static class EvaluateResponseWordAnalysis {
        private String word;
        private String lemma;
        private BigDecimal valence;
        private BigDecimal arousal;
        private BigDecimal dominance;
    }
}

