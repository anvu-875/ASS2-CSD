/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.assignment2.main;

import com.group.assignment2.model.Dictionary;
import com.group.assignment2.model.Vocabulary;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Program {

    private static final Dictionary DICT = Dictionary.getInstance();
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("*****************MENU*****************");
            System.out.println();
            System.out.println("1.Add one word to the dictionary");
            System.out.println("2.Remove one word from the dictionary");
            System.out.println("3.Find a word in the dictionary");
            System.out.println("4.Print a path between two words");
            System.out.println("5.Print all vocabulary");
            System.out.println("6.Exit the program");
            System.out.println();
            System.out.println("**************************************");
            System.out.print("Please enter your option (1 - 6): ");
            int choice = 0;
            while (true) {
                try {
                    choice = Integer.parseInt(SC.nextLine());
                    if (choice < 1 || choice > 6) {
                        System.out.print("Invalid! Please enter again: ");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid! Please enter again: ");
                }
            }
            System.out.println();
            System.out.println("--------------------------------------");
            switch (choice) {
                case 1: {
                    System.out.println("1.Add one word to the dictionary");
                    System.out.println();
                    String englishWord = getEnglishWord(true);
                    String vietnameseMeaning = getVietnameseMeaning();

                    boolean checkAdd = DICT.addWord(englishWord, vietnameseMeaning);
                    if (checkAdd) {
                        System.out.println();
                        System.out.println("The job was completed successfully!!");
                    } else {
                        System.out.println();
                        System.out.println("The job has failed!!");
                    }
                    break;
                }

                case 2: {
                    System.out.println("2.Remove one word from the dictionary");
                    System.out.println();
                    String englishWord = getEnglishWord(false);

                    boolean checkDel = DICT.removeWord(englishWord);
                    if (checkDel) {
                        System.out.println();
                        System.out.println("The job was completed successfully!!");
                    } else {
                        System.out.println();
                        System.out.println("The job has failed! Maybe your input word doesn't exist in the dictionary.");
                    }
                    break;
                }

                case 3: {
                    System.out.println("3.Find a word in the dictionary");
                    System.out.println();
                    String englishWord = getEnglishWord(false);
                    Vocabulary foundWord = DICT.searchWord(englishWord);
                    if (foundWord != null) {
                        System.out.println();
                        System.out.println("Word found successfully!!");
                        System.out.println(foundWord);
                    } else {
                        System.out.println();
                        System.out.println("Word not found!!");
                    }
                    break;
                }

                case 4: {
                    System.out.println("4.Print a path between two words");
                    System.out.println();
                    System.out.println("FIRST WORD:");
                    String englishWord1 = getEnglishWord(false);
                    System.out.println("SECOND WORD:");
                    String englishWord2 = getEnglishWord(false);
                    String foundPath = DICT.getPath(englishWord1, englishWord2);
                    System.out.println();
                    System.out.println(foundPath);
                    break;
                }

                case 5: {
                    System.out.println("5.Print all vocabulary");
                    System.out.println();
                    DICT.printAll();
                    System.out.println();
                    break;
                }

                case 6: {
                    System.out.println("6.Exit the program");
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println();
                    return;
                }
            }
            System.out.println("--------------------------------------");
            System.out.println();
        }
    }

    private static String getEnglishWord(boolean checkDuplicated) {
        String englishWord = "";
        System.out.print("Please enter English word: ");
        while (true) {
            englishWord = SC.nextLine();
            if (englishWord == null || englishWord.isEmpty()) {
                System.out.print("Invalid! Please enter English word again: ");
            } else if (!englishWord.matches("([A-Za-z]+\\s)*[A-Za-z]+")) {
                System.out.print("Invalid! Please enter English word again: ");
            } else {
                if (checkDuplicated) {
                    Vocabulary voca = DICT.searchWord(englishWord);
                    if (voca != null) {
                        System.out.print("Duplicated word! Please enter English word again: ");
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return englishWord;
    }

    private static String getVietnameseMeaning() {
        String vietnameseMeaning = "";
        System.out.print("Please enter Vietnamese meaning: ");
        while (true) {
            vietnameseMeaning = SC.nextLine();
            if (vietnameseMeaning == null || vietnameseMeaning.isEmpty()) {
                System.out.print("Invalid! Please enter Vietnamese meaning again: ");
            } else {
                break;
            }
        }
        return vietnameseMeaning;
    }
}
