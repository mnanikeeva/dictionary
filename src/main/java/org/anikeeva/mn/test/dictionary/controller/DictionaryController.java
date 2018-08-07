package org.anikeeva.mn.test.dictionary.controller;

import org.anikeeva.mn.test.dictionary.model.Dictionary;
import org.anikeeva.mn.test.dictionary.model.DictionaryRepository;
import org.anikeeva.mn.test.dictionary.model.Word;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    DictionaryRepository dictionaries = new DictionaryRepository();

    @RequestMapping(value = "/{name}", method = GET)
    @ResponseBody
    public ResponseEntity<Dictionary> get(@PathVariable String name) {
        Dictionary dictionary = dictionaries.find(name);
        if (dictionary != null) {
            return new ResponseEntity<>(dictionary, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{name}/{text}", method = GET)
    @ResponseBody
    public ResponseEntity<Word> get(@PathVariable String name, @PathVariable String text) {
        Dictionary dictionary = dictionaries.find(name);
        if (dictionary != null) {
            if (dictionary.getWord(text) != null) {
                return new ResponseEntity<>(dictionary.getWord(text), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{name}/{text}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name, @PathVariable String text) {
        Dictionary dictionary = dictionaries.find(name);
        if (dictionary != null) {
            dictionary.removeWord(text);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{name}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name) {
        dictionaries.deleteByName(name);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{name}", method = POST)
    @ResponseBody
    public Dictionary post(@PathVariable String name) {
        return dictionaries.create(name);
    }

    @RequestMapping(value = "/{name}", method = PUT)
    @ResponseBody
    public ResponseEntity put(@PathVariable String name, @RequestBody Word word) {
        Dictionary dictionary = dictionaries.find(name);
        if (dictionary != null) {
            dictionary.addWord(word);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}



