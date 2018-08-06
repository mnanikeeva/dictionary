package org.anikeeva.mn.test.dictionary.model;

/**
 * Created by sbt-novozhilova-mn on 06.08.2018.
 */
public class Word {
    private String text;
    private String description;

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
