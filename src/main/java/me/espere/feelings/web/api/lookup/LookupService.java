package me.espere.feelings.web.api.lookup;

import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.dictionary.VadEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LookupService {
    private VadDictionary vadDictionary;

    @Autowired
    public LookupService(VadDictionary vadDictionary) {
        this.vadDictionary = vadDictionary;
    }

    public Collection<VadEntry> lookupWords(Collection<String> words) {
        return words
                .stream()
                .map(vadDictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}
