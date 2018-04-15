package me.espere.feelings.web.config;

import me.espere.feelings.impl.dictionary.InMemoryCsvVadDictionary;
import me.espere.feelings.impl.lemmatizer.NlpCoreBasedLemmatizer;
import me.espere.feelings.spec.aggregator.MeanValueVadAggregator;
import me.espere.feelings.spec.aggregator.VadAggregator;
import me.espere.feelings.spec.analyzer.SimpleVadSentenceAnalyzer;
import me.espere.feelings.spec.analyzer.VadSentenceAnalyzer;
import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.lemmatizer.Lemmatizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SentimentConfiguration {
    @Bean
    public Lemmatizer lemmatizer() {
        return new NlpCoreBasedLemmatizer();
    }

    @Bean
    public VadDictionary dictionary() throws IOException {
        return new InMemoryCsvVadDictionary("vad-dictionary.csv");
    }

    @Bean
    public VadAggregator aggregator() {
        return new MeanValueVadAggregator();
    }

    @Bean
    public VadSentenceAnalyzer analyzer(Lemmatizer lemmatizer, VadDictionary dictionary, VadAggregator aggregator) {
        return new SimpleVadSentenceAnalyzer(lemmatizer, dictionary, aggregator);
    }
}
