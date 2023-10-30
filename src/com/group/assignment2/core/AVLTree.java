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

    //I.Handle Height
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
    
    //II.Balance Tree
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

    //III.Main functions
    
    //1.Insert
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
    
    //2.Delete
    public void delete(T value) {
        if (value == null) {
            return;
        }
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

    //3.Search
    public T search(T value) {
        if (value == null) {
            return null;
        }
        Node<T> currentNode = root;
        while (currentNode != null) {
            if (value.compareTo(currentNode.data) == 0) {
                return currentNode.data;
            } else if (value.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return null;
    }
    
    //4.FindPath
    public String findPath(T value1, T value2) {
        if (value1 == null || value2 == null) {
            return "This feature doesn't support null reference";
        }
        boolean isValue1Exist = false;
        boolean isValue2Exist = false;
        for (T data : this) {
            if (!isValue1Exist && data.compareTo(value1) == 0) {
                isValue1Exist = true;
            }
            if (!isValue2Exist && data.compareTo(value2) == 0) {
                isValue2Exist = true;
            }
            if (isValue1Exist && isValue2Exist) {
                break;
            }
        }
        if (!isValue1Exist || !isValue2Exist) {
            return "Can't find any path!";
        }
        if (value1.compareTo(value2) == 0) {
            return value1.toString();
        }
        return findPath(root, value1, value2);
    }

    private String findPath(Node<T> mainRoot, T val1, T val2) {
        if (mainRoot == null) {
            return "Can't find any path!";
        }
        if (mainRoot.data.compareTo(val1) > 0 && mainRoot.data.compareTo(val2) > 0) {
            return findPath(mainRoot.left, val1, val2);
        }
        if (mainRoot.data.compareTo(val1) < 0 && mainRoot.data.compareTo(val2) < 0) {
            return findPath(mainRoot.right, val1, val2);
        }
        if (val1.compareTo(val2) > 0) {
            T tmp = val1;
            val1 = val2;
            val2 = tmp;
        }
        String result = "";
        if(val1.compareTo(mainRoot.data) != 0) {
            Node<T> currLeft = mainRoot.left;
            while (val1.compareTo(currLeft.data) != 0) {
                result = " -> " + currLeft.data.toString();
                if (val1.compareTo(currLeft.data) > 0) {
                    currLeft = currLeft.right;
                }
                if (val1.compareTo(currLeft.data) < 0) {
                    currLeft = currLeft.left;
                }
            }
            result = currLeft.data.toString() + result;
        }
        result += result.isEmpty() ? mainRoot.data.toString() : " -> " + mainRoot.data.toString();
        if(val2.compareTo(mainRoot.data) != 0) {
            result += " -> ";
            Node<T> currRight = mainRoot.right;
            while (val2.compareTo(currRight.data) != 0) {
                result += currRight.data.toString() + " -> ";
                if (val2.compareTo(currRight.data) > 0) {
                    currRight = currRight.right;
                }
                if (val2.compareTo(currRight.data) < 0) {
                    currRight = currRight.left;
                }
            }
            result += currRight.data;
        }
        return result;
    }
    
    //IV.Iteration
    
    //1.Design
    public void inorderTraversal(DIterable<T> ite) {
        inorderTraversal(root, ite);
    }

    private void inorderTraversal(Node<T> node, DIterable<T> ite) {
        if (node != null) {
            inorderTraversal(node.left, ite);
            ite.iterable(node.data);
            inorderTraversal(node.right, ite);
        }
    }

    public void preorderTraversal(DIterable<T> ite) {
        preorderTraversal(root, ite);
    }

    private void preorderTraversal(Node<T> node, DIterable<T> ite) {
        if (node != null) {
            ite.iterable(node.data);
            preorderTraversal(node.left, ite);
            preorderTraversal(node.right, ite);
        }
    }
    
    public void postorderTraversal(DIterable<T> ite) {
        postorderTraversal(root, ite);
    }

    private void postorderTraversal(Node<T> node, DIterable<T> ite) {
        if (node != null) {
            postorderTraversal(node.left, ite);
            postorderTraversal(node.right, ite);
            ite.iterable(node.data);
        }
    }

    //2.ForLoop
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
    
    //V.Test
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree();
        avl.insert(5);
        avl.insert(60);
        avl.insert(10);
        avl.insert(25);
        avl.insert(30);
        avl.insert(48);
        avl.insert(70);
//        avl.inorderTraversal((data) -> {
//            System.out.print(data + " ");
//        });
//        System.out.println();
        avl.preorderTraversal((data) -> {
            System.out.print(data + " ");
        });
//        System.out.println();
//        avl.postorderTraversal((data ) -> {
//            System.out.print(data + " ");
//        });
//        System.out.println("");
//        avl.preorderTraversal();
//        System.out.println(avl.findPath(5, 70));
//        for (Integer node : avl) {
//            System.out.println(node);
//        }
    }
}
