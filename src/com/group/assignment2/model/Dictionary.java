/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.assignment2.model;

import com.group.assignment2.core.AVLTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author hoang
 */
public class Dictionary {

    private final AVLTree<Vocabulary> dictionary = new AVLTree();

    private static final String databaseFile = "dictionary.txt";

    private static Dictionary instance = null;

    private Dictionary() {
    }

    public static Dictionary getInstance() {
        if (Dictionary.instance == null) {
            Dictionary.instance = new Dictionary();
            Dictionary.instance.loadDataFromFile(databaseFile);
        }
        return Dictionary.instance;
    }

    private void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" \\| ");

                if (fields.length >= 2) {
                    String englistWord = fields[0].trim();
                    String vietnameseWord = fields[1].trim();

                    Vocabulary word = new Vocabulary(englistWord, vietnameseWord);
                    this.dictionary.insert(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addWord(String englishWord, String vietnameseWord) {
        if (englishWord == null || vietnameseWord == null || englishWord.isEmpty() || vietnameseWord.isEmpty()) {
            return false;
        }
        Vocabulary newWord = new Vocabulary(englishWord, vietnameseWord);
        return this.dictionary.insert(newWord);
    }

    public boolean removeWord(String englishWord) {
        if (englishWord == null || englishWord.isEmpty()) {
            return false;
        }
        Vocabulary word = new Vocabulary(englishWord);
        return this.dictionary.delete(word);
    }

    public Vocabulary searchWord(String englishWord) {
        if (englishWord == null || englishWord.isEmpty()) {
            return null;
        }
        Vocabulary word = new Vocabulary(englishWord);
        return this.dictionary.search(word);
    }

    public String getPath(String englishWord1, String englishWord2) {
        Vocabulary word1 = (englishWord1 != null && englishWord1.length() > 0) ? new Vocabulary(englishWord1) : null;
        Vocabulary word2 = (englishWord2 != null && englishWord2.length() > 0) ? new Vocabulary(englishWord2) : null;
        return this.dictionary.findPath(word1, word2);
    }

    public void printAll() {
        if (!this.dictionary.isEmpty()) {
            for (Vocabulary vocab : this.dictionary) {
                System.out.println(vocab);
            }
        }
    }

//    public static void main(String[] args) {
//        Dictionary ls = Dictionary.getInstance();
////        for (Vocabulary voca : ls.dictionary) {
////            System.out.println(voca);
////        }
//        System.out.println(ls.getPath("versatile", null));
//        ls.printAll();
//    }
}
