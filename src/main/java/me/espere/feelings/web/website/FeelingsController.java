package me.espere.feelings.web.website;

import me.espere.feelings.spec.analyzer.VadSentenceAnalysis;
import me.espere.feelings.web.api.evaluate.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class FeelingsController {
    private EvaluateService evaluateService;

    @Autowired
    public FeelingsController(EvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String feelingsForm(Model model) {
        model.addAttribute("feelingsForm", new FeelingsForm());
        return "feelings-input";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String feelings(@ModelAttribute FeelingsForm feelingsForm, Model model) {
        String sentence = feelingsForm.getSentence();

        VadSentenceAnalysis sentenceAnalysis = evaluateService.evaluateSentence(sentence);

        model.addAttribute("sentence", sentence);

        model.addAttribute("valence", sentenceAnalysis.getVadValue().getValence());
        model.addAttribute("arousal", sentenceAnalysis.getVadValue().getArousal());
        model.addAttribute("dominance", sentenceAnalysis.getVadValue().getDominance());

        model.addAttribute("wordAnalyses", sentenceAnalysis.getWordAnalyses());

        return "feelings-result";
    }
}
