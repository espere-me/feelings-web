package me.espere.feelings.web.api.evaluate;

import me.espere.feelings.spec.analyzer.VadSentenceAnalysis;
import me.espere.feelings.spec.analyzer.VadSentenceAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateService {
    private VadSentenceAnalyzer analyzer;

    @Autowired
    public EvaluateService(VadSentenceAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public VadSentenceAnalysis evaluateSentence(String sentence) {
        return analyzer.analyzeSentence(sentence);
    }
}
