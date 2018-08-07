package org.anikeeva.mn.test.dictionary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-novozhilova-mn on 06.08.2018.
 */
public class DictionaryRepository {

    List<Dictionary> list = new ArrayList<>();

    public Dictionary find(String name) {
        for (Dictionary dict : list) {
            if (dict.getName().equals(name)) {
                return dict;
            }
        }
        return null;
    }

    public void deleteByName(String name) {
        list.remove(find(name));
    }

    public Dictionary create(String name) {
        Dictionary dictionary = new Dictionary(name);
        list.add(dictionary);
        return dictionary;
    }

}
