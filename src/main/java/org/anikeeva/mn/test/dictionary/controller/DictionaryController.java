package org.anikeeva.mn.test.dictionary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    List<Dictionary> dict = new ArrayList<>();

    @RequestMapping(value = "/{name}/{text}", method = GET)
    @ResponseBody
    public Word get(@PathVariable String name, @PathVariable String text) {
        Word result = new Word();
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).name.equals(name)) {
                result = dict.get(i).words.get(text);
            }
        }
        return result;
    }

    @RequestMapping(value = "/{name}", method = GET)
    @ResponseBody
    public Dictionary get(@PathVariable String name) {
        Dictionary result = null;
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).name.equals(name)) {
                result = dict.get(i);
            }
        }
        return result;
    }

    @RequestMapping(value = "/{name}/{text}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name, @PathVariable String text) {
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).name.equals(name)) {
                if (dict.get(i).words.containsKey(text)) {
                    dict.get(i).words.remove(text);
                }
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name) {
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).name.equals(name)) {
                dict.remove(dict.get(i));
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = POST)
    @ResponseBody
    public Dictionary post(@PathVariable String name) {
        Dictionary dictionary = new Dictionary(name);
        dict.add(dictionary);
        return dictionary;
    }

    @RequestMapping(value = "/{name}", method = PUT)
    @ResponseBody
    public ResponseEntity put(@PathVariable String name, @RequestBody Word word) {

        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).name.equals(name)) {
                dict.get(i).words.put(word.text, word);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public static class Dictionary {
        String name;
        Map<String, Word> words = new HashMap<>();

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
    }

    public static class Word {
        String text;
        String description;

        public Word() {

        }

        public Word(String text, String description) {
            this.text = text;
            this.description = description;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}



