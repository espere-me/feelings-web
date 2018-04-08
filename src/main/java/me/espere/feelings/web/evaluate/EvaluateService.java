package me.espere.feelings.web.evaluate;

import me.espere.feelings.spec.aggregator.VadAggregator;
import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.dictionary.VadEntry;
import me.espere.feelings.spec.dictionary.VadValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Service
public class EvaluateService {
    private VadDictionary vadDictionary;
    private VadAggregator vadAggregator;

    @Autowired
    public EvaluateService(VadDictionary vadDictionary, VadAggregator vadAggregator) {
        this.vadDictionary = vadDictionary;
        this.vadAggregator = vadAggregator;
    }

    public Evaluation evaluateSentence(String sentence) {
        Collection<VadEntry> entries = stream(sentence.split(" "))
                .map(vadDictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        VadValue feeling = vadAggregator.aggregate(sentence, entries);

        return new Evaluation(feeling, entries);
    }
}
