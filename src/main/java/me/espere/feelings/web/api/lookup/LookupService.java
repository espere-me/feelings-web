package me.espere.feelings.web.api.lookup;

import me.espere.feelings.spec.dictionary.Dictionary;
import me.espere.feelings.spec.dictionary.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LookupService {
    private Dictionary dictionary;

    @Autowired
    public LookupService(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Collection<Entry> lookupWords(Collection<String> words) {
        return words
                .stream()
                .map(dictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}
