package me.espere.feelings.web.api.evaluate;

import me.espere.feelings.spec.analyzer.VadSentenceAnalysis;
import me.espere.feelings.web.api.evaluate.EvaluateResponse.EvaluateResponseFeeling;
import me.espere.feelings.web.api.evaluate.EvaluateResponse.EvaluateResponseWordAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/evaluate")
public class EvaluateController {
    private EvaluateService evaluateService;

    @Autowired
    public EvaluateController(EvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public EvaluateResponse evaluate(@RequestBody EvaluateRequest evaluateRequest) {
        String sentence = evaluateRequest.getSentence();
        VadSentenceAnalysis sentenceAnalysis = evaluateService.evaluateSentence(sentence);
        return buildEvaluateResponse(sentenceAnalysis);
    }

    private EvaluateResponse buildEvaluateResponse(VadSentenceAnalysis sentenceAnalysis) {
        EvaluateResponseFeeling responseFeeling = new EvaluateResponseFeeling(
                sentenceAnalysis.getVadValue().getValence(),
                sentenceAnalysis.getVadValue().getArousal(),
                sentenceAnalysis.getVadValue().getDominance()
        );

        Collection<EvaluateResponseWordAnalysis> responseWordAnalyses = sentenceAnalysis.getWordAnalyses()
                .stream()
                .map(wordAnalysis -> new EvaluateResponseWordAnalysis(
                        wordAnalysis.getWord(),
                        wordAnalysis.getLemma(),
                        wordAnalysis.getVadValue().getValence(),
                        wordAnalysis.getVadValue().getArousal(),
                        wordAnalysis.getVadValue().getDominance()
                ))
                .collect(Collectors.toList());

        return new EvaluateResponse(responseFeeling, responseWordAnalyses);
    }
}
