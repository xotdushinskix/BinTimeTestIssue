package com.bintime.hatch.model;

import javax.persistence.*;

/**
 * Created by FromxSoul on 08.10.2016.
 */
@Entity
@Table(name = "text_file")
public class TextFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private int word_id;

    @Column(name = "value")
    private String value;

    @Column(name = "count")
    private int count;

    public TextFile(){

    }

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
