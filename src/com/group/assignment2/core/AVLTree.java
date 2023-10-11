/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.assignment2.core;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author hoang
 */
public class AVLTree<T extends Comparable<T>> implements Iterable<T> {

    public Node<T> root;

    public AVLTree() {
        root = null;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private int balanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        updateHeight(node);
        updateHeight(tmp);
        return tmp;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        updateHeight(node);
        updateHeight(tmp);
        return tmp;
    }

    private Node<T> balance(Node<T> node) {
        if (node == null) {
            return node;
        }

        int bf = balanceFactor(node);

        if (bf > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (bf < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(T value) {
        if (value == null) {
            return;
        }
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (value.compareTo(node.data) < 0) {
            node.left = insert(node.left, value);
        } else if (value.compareTo(node.data) > 0) {
            node.right = insert(node.right, value);
        } else {
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return node;
        }
        if (value.compareTo(node.data) < 0) {
            node.left = delete(node.left, value);
        } else if (value.compareTo(node.data) > 0) {
            node.right = delete(node.right, value);
        } else {
            if ((node.left == null) || (node.right == null)) {
                Node<T> temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node<T> temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }
        if (node == null) {
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<T> minValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public T search(T value) {
        return search(root, value);
    }

    private T search(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.equals(node.data)) {
            return node.data;
        }
        if (value.compareTo(node.data) < 0) {
            return search(node.left, value);
        }
        return search(node.right, value);
    }

//    public String findPathBetweenNodes(T value1, T value2) {
//        List<T> path = findPath(root, value1, value2);
//        if (!path.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < path.size(); i++) {
//                sb.append(path.get(i));
//                if (i < path.size() - 1) {
//                    sb.append(" -> ");
//                }
//            }
//            return sb.toString();
//        } else {
//            return "Path not found";
//        }
//    }
//
//    private List<T> findPath(Node<T> node, T key1, T key2) {
//        List<T> path = new ArrayList<>();
//        if (node == null) {
//            return path;
//        }
//        if (key1.compareTo(node.data) < 0 && key2.compareTo(node.data) < 0) {
//            path.add(node.data);
//            path.addAll(findPath(node.left, key1, key2));
//        } else if (key1.compareTo(node.data) > 0 && key2.compareTo(node.data) > 0) {
//            path.add(node.data);
//            path.addAll(findPath(node.right, key1, key2));
//        } else {
//            path.add(node.data);
//        }
//        return path;
//    }
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node<T> node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    public void preorderTraversal() {
        preorderTraversal(root);
        System.out.println();
    }

    private void preorderTraversal(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    private ArrayList<Node<T>> getAllNodeRef() {
        ArrayList<Node<T>> nodeList = new ArrayList();
        addAllNodeRef(nodeList);
        return nodeList;
    }

    private void addAllNodeRef(ArrayList<Node<T>> nodeList) {
        addAllNodeRef(root, nodeList);
    }

    private void addAllNodeRef(Node<T> node, ArrayList<Node<T>> nodeList) {
        if (node != null) {
            addAllNodeRef(node.left, nodeList);
            nodeList.add(node);
            addAllNodeRef(node.right, nodeList);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            ArrayList<Node<T>> dataList = getAllNodeRef();

            @Override
            public boolean hasNext() {
                return !dataList.isEmpty();
            }

            @Override
            public T next() {
                return dataList.remove(0).data;
            }
        };
    }
}
