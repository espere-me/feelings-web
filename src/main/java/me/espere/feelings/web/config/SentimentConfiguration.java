package me.espere.feelings.web.config;

import me.espere.feelings.impl.dictionary.InMemoryCsvVadDictionary;
import me.espere.feelings.impl.lemmatizer.NlpCoreBasedLemmatizer;
import me.espere.feelings.spec.aggregator.Aggregator;
import me.espere.feelings.spec.aggregator.MeanValueAggregator;
import me.espere.feelings.spec.analyzer.SimpleTextAnalyzer;
import me.espere.feelings.spec.analyzer.TextAnalyzer;
import me.espere.feelings.spec.dictionary.Dictionary;
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
    public Dictionary dictionary() throws IOException {
        return new InMemoryCsvVadDictionary();
    }

    @Bean
    public Aggregator aggregator() {
        return new MeanValueAggregator();
    }

    @Bean
    public TextAnalyzer analyzer(Lemmatizer lemmatizer, Dictionary dictionary, Aggregator aggregator) {
        return new SimpleTextAnalyzer(lemmatizer, dictionary, aggregator);
    }
}
