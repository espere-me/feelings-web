package me.espere.feelings.web.evaluate;

import lombok.Value;
import me.espere.feelings.spec.dictionary.VadEntry;
import me.espere.feelings.spec.dictionary.VadValue;

import java.util.Collection;

@Value
public class Evaluation {
    private VadValue feeling;
    private Collection<VadEntry> entries;
}
