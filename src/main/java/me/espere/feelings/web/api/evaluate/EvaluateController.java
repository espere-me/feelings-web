package me.espere.feelings.web.api.evaluate;

import me.espere.feelings.spec.analyzer.TextAnalysis;
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
        String text = evaluateRequest.getText();
        TextAnalysis textAnalysis = evaluateService.evaluateText(text);
        return buildEvaluateResponse(textAnalysis);
    }

    private EvaluateResponse buildEvaluateResponse(TextAnalysis textAnalysis) {
        EvaluateResponseFeeling responseFeeling = new EvaluateResponseFeeling(
                textAnalysis.getVadValue().getValence(),
                textAnalysis.getVadValue().getArousal(),
                textAnalysis.getVadValue().getDominance()
        );

        Collection<EvaluateResponseWordAnalysis> responseWordAnalyses = textAnalysis.getWordAnalyses()
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
