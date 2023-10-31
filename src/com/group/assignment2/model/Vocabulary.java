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
    private String vietnameseWord;

    public Vocabulary(String englishWord, String vietnameseWord) {
        this.englishWord = englishWord;
        this.vietnameseWord = vietnameseWord;
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

    public String getVietnameseWord() {
        return vietnameseWord;
    }

    public void setVietnameseWord(String vietnameseWord) {
        this.vietnameseWord = vietnameseWord;
    }

    @Override
    public int compareTo(Vocabulary o) {
        return this.englishWord.compareTo(o.englishWord);
    }

    @Override
    public String toString() {
        return  englishWord + ": " + vietnameseWord;
    }
}
