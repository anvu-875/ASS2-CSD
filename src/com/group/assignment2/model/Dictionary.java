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

    private AVLTree<Vocabulary> dictionary = new AVLTree();
    
    private static Dictionary instance = null;

    private Dictionary() {
    }
    
    public static Dictionary getInstance() {
        if (Dictionary.instance == null) {
            Dictionary.instance = new Dictionary();
            Dictionary.instance.loadDataFromFile("dictionary.txt");
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
    
    public boolean addWord(Vocabulary newWord) {
        return this.dictionary.insert(newWord);
    }
    
    public boolean removeWord(Vocabulary word) {
        return this.dictionary.delete(word);
    }

    public Vocabulary searchWord(String englishWord) {
        Vocabulary word = new Vocabulary(englishWord);
        return this.dictionary.search(word);
    }
    
    public String getPath(String englishWord1, String englishWord2) {
        Vocabulary word1 = new Vocabulary(englishWord1);
        Vocabulary word2 = new Vocabulary(englishWord2);
        return this.dictionary.findPath(word1, word2);
    }
    
    public static void main(String[] args) {
        Dictionary ls = Dictionary.getInstance();
        System.out.println(ls.getPath("accountable", "website"));
    }
}
