package org.anikeeva.mn.test.dictionary.model;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sbt-novozhilova-mn on 06.08.2018.
 */
public class Dictionary {
    private String name;
    private Map<String, Word> words = new HashMap<>();

    public Dictionary() {
    }

    public Dictionary(String name) {
        this.name = name;
    }

    public Set<Word> getWords() {
        return new HashSet<>(words.values());
    }

    public void setWords(Set<Word> words) {
        this.words = words != null ? toMap(words) : new HashMap<>();
    }

    public Dictionary(String name, Set<Word> words) {
        this.name = name;
        this.words = words != null ? toMap(words) : new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary that = (Dictionary) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Word getWord(String text) {
        return words.get(text);
    }

    public void removeWord(String text) {
        words.remove(text);
    }

    public void addWord(Word word) {
        words.put(word.getText(), word);
    }

    private Map<String, Word> toMap(Set<Word> words){
        return words.stream().collect(Collectors.toMap(Word::getText, Function.identity()));
    }
}
