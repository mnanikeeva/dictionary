package org.anikeeva.mn.test.dictionary.model;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Word> getWords() {
        return words;
    }

    public void setWords(Map<String, Word> words) {
        this.words = words;
    }

    public Dictionary(String name, Map<String, Word> words) {
        this.name = name;
        this.words = words;
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
        return getWords().get(text);
    }

    public void removeWord(String text) {
        getWords().remove(text);
    }

    public void addWord(Word word) {
        getWords().put(word.getText(), word);
    }
}
