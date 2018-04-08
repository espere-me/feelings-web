package me.espere.feelings.web.api.evaluate;

import me.espere.feelings.web.api.evaluate.EvaluateResponse.EvaluateResponseEntry;
import me.espere.feelings.web.api.evaluate.EvaluateResponse.EvaluateResponseFeeling;
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
        Evaluation evaluation = evaluateService.evaluateSentence(sentence);
        return buildEvaluateResponse(evaluation);
    }

    private EvaluateResponse buildEvaluateResponse(Evaluation evaluation) {
        EvaluateResponseFeeling responseFeeling = new EvaluateResponseFeeling(
                evaluation.getFeeling().getValence(),
                evaluation.getFeeling().getArousal(),
                evaluation.getFeeling().getDominance()
        );

        Collection<EvaluateResponseEntry> responseEntries = evaluation.getEntries()
                .stream()
                .map(entry -> new EvaluateResponseEntry(
                        entry.getWord(),
                        entry.getVadValue().getValence(),
                        entry.getVadValue().getArousal(),
                        entry.getVadValue().getDominance()
                ))
                .collect(Collectors.toList());

        return new EvaluateResponse(responseFeeling, responseEntries);
    }
}
