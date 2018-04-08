package me.espere.feelings.web.config;

import me.espere.feelings.impl.dictionary.InMemoryCsvVadDictionary;
import me.espere.feelings.spec.dictionary.VadDictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SentimentConfiguration {
    @Bean
    public VadDictionary defaultDictionary() throws IOException {
        return new InMemoryCsvVadDictionary("vad-dictionary.csv");
    }
}
