package me.espere.feelings.web.api.evaluate;

import me.espere.feelings.spec.analyzer.TextAnalysis;
import me.espere.feelings.spec.analyzer.TextAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateService {
    private TextAnalyzer analyzer;

    @Autowired
    public EvaluateService(TextAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public TextAnalysis evaluateText(String text) {
        return analyzer.analyzeText(text);
    }
}
