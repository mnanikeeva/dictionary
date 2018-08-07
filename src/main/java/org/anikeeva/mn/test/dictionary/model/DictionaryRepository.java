package org.anikeeva.mn.test.dictionary.model;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-novozhilova-mn on 06.08.2018.
 */
public class DictionaryRepository {

    List<Dictionary> dictionaries = new ArrayList<>();

    public Dictionary find(String name) {
        for (Dictionary dict : dictionaries) {
            if (dict.getName().equals(name)) {
                return dict;
            }
        }
        return null;
    }

    public void deleteByName(String name) {
        dictionaries.remove(find(name));
    }

    public Dictionary create(String name) {
        Dictionary dictionary = new Dictionary(name);
        dictionaries.add(dictionary);
        return dictionary;
    }
}
