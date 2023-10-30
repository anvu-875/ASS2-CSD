/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.assignment2.core;

/**
 *
 * @author hoang
 */
public class Node<T extends Comparable<T>> {

    T data;
    Node<T> left, right;
    int height;

    Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
