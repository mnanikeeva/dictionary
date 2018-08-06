package org.anikeeva.mn.test.dictionary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-novozhilova-mn on 06.08.2018.
 */
public class DictionaryRepository {

    List<Dictionary> dict = new ArrayList<>();

    public Dictionary find(String name) {
        for (Dictionary aDict : dict) {
            if (aDict.getName().equals(name)) {
                return aDict;
            }
        }
        return null;
    }

    public void deleteByName(String name) {
        dict.remove(find(name));
    }

    public Dictionary create(String name) {
        Dictionary dictionary = new Dictionary(name);
        dict.add(dictionary);
        return dictionary;
    }

}
