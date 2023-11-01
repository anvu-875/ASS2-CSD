/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.assignment2.model;

/**
 *
 * @author hoang
 */
public class Vocabulary implements Comparable<Vocabulary>{
    private String englishWord;
    private String vietnameseMeaning;

    public Vocabulary(String englishWord, String vietnameseMeaning) {
        this.englishWord = englishWord;
        this.vietnameseMeaning = vietnameseMeaning;
    }
    
    public Vocabulary(String englistWord) {
        this.englishWord = englistWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getVietnameseMeaning() {
        return vietnameseMeaning;
    }

    public void setVietnameseMeaning(String vietnameseMeaning) {
        this.vietnameseMeaning = vietnameseMeaning;
    }

    @Override
    public int compareTo(Vocabulary o) {
        return this.englishWord.compareTo(o.englishWord);
    }

    @Override
    public String toString() {
        return  englishWord + ": " + vietnameseMeaning;
    }
}
