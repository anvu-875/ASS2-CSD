/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.assignment2.handler;

import com.group.assignment2.core.AVLTree;
import com.group.assignment2.model.Dictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author hoang
 */
public class DictionaryList {

    private static AVLTree<Dictionary> dictionary = new AVLTree();

    public DictionaryList() {
    }

    public void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" \\| ");

                if (fields.length >= 2) {
                    String englistWord = fields[0].trim();
                    String vietnameseWord = fields[1].trim();

                    Dictionary item = new Dictionary(englistWord, vietnameseWord);
                    this.dictionary.insert(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DictionaryList ls = new DictionaryList();
        ls.loadDataFromFile("dictionary.txt");
    }
}
