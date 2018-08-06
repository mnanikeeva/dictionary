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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    @RequestMapping(value = "/{name}", method = GET)
    @ResponseBody
    public ResponseEntity<Dictionary> get(@PathVariable String name) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        Dictionary dictionary = dictionaryRepository.find(name);
        if (dictionary != null) {
            return new ResponseEntity<>(dictionary, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{name}/{text}", method = GET)
    @ResponseBody
    public ResponseEntity<Word> get(@PathVariable String name, @PathVariable String text) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        Dictionary dictionary = dictionaryRepository.find(name);
        if (dictionary != null) {
            if (dictionary.getWords().get(text) != null) {
                return new ResponseEntity<>(dictionary.getWords().get(text), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{name}/{text}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name, @PathVariable String text) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        Dictionary dictionary = dictionaryRepository.find(name);
        if (dictionary != null) {
            if (dictionary.getWords().containsKey(text)) {
                dictionary.getWords().remove(text);
            }
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/{name}", method = DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable String name) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        dictionaryRepository.deleteByName(name);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/{name}", method = POST)
    @ResponseBody
    public Dictionary post(@PathVariable String name) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        return dictionaryRepository.create(name);
    }

    @RequestMapping(value = "/{name}", method = PUT)
    @ResponseBody
    public ResponseEntity put(@PathVariable String name, @RequestBody Word word) {
        DictionaryRepository dictionaryRepository = new DictionaryRepository();
        Dictionary dictionary = dictionaryRepository.find(name);
        if (dictionary != null) {
            dictionary.getWords().put(word.getText(), word);
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}



