package me.espere.feelings.web.lookup;

import me.espere.feelings.spec.dictionary.VadEntry;
import me.espere.feelings.web.lookup.LookupResponse.LookupResponseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/api/lookup")
public class LookupController {
    private LookupService lookupService;

    @Autowired
    public LookupController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public LookupResponse lookup(@RequestBody LookupRequest lookupRequest) {
        Collection<String> words = lookupRequest.getWords();
        Collection<VadEntry> lookupEntries = lookupService.lookupWords(words);
        return buildLookupResponse(lookupEntries);
    }

    private LookupResponse buildLookupResponse(Collection<VadEntry> lookupEntries) {
        Collection<LookupResponseEntry> responseEntries = lookupEntries
                .stream()
                .map(entry -> new LookupResponseEntry(
                        entry.getWord(),
                        entry.getVadValue().getValence(),
                        entry.getVadValue().getArousal(),
                        entry.getVadValue().getDominance()
                ))
                .collect(toList());

        return new LookupResponse(responseEntries);
    }
}
