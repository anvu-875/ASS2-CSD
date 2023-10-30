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
    
    public void addWord(Vocabulary newWord) {
        this.dictionary.insert(newWord);
    }
    
    public void removeWord(Vocabulary word) {
        this.dictionary.delete(word);
    }

    public static void main(String[] args) {
        Dictionary ls = Dictionary.getInstance();
        int count = 0;
        for (Vocabulary data : ls.dictionary) {
            System.out.println(data);
            count++;
        }
        System.out.println(count);
    }
}
