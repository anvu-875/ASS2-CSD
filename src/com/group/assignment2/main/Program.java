/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.assignment2.main;

import com.group.assignment2.core.AVLTree;
import com.group.assignment2.core.Node;

/**
 *
 * @author hoang
 */
public class Program {

    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree();
        avl.insert(5);
        avl.insert(60);
        avl.insert(10);
        avl.insert(25);
        avl.insert(30);
        avl.insert(48);
        avl.inorderTraversal();
        int tmp = 0;
        for (Integer node : avl) {
            System.out.println(node);
        }
    }
}
