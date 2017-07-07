package collections;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree <K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }


    public void add(K key, V value) {
        if (key == null) {
            return;
        }
        if (root == null) {
            root = new Node(new Entry<K, V>(key, value));
            size ++;
        }
        else {
            addInSubTree(key, value, root, key.compareTo(root.value.getKey()));
        }
    }

    private void addInSubTree(K key, V value, Node subTree, int compareFactor) {
        if (compareFactor == 0) {
            return;
        }
        if (compareFactor > 0) {
            if (subTree.right == null) {
                subTree.right = new Node(key, value);
                size++;
            } else {
                addInSubTree(key, value, subTree.right, key.compareTo(subTree.right.value.getKey()));
            }
        } else {
            if (subTree.left == null) {
                subTree.left = new Node(key, value);
                size++;
            } else {
                addInSubTree(key, value, subTree.left, key.compareTo(subTree.left.value.getKey()));
            }
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }


    public List<Entry<K, V>> getAsList() {
        List<Entry<K, V>> values = new ArrayList<>();
        createValuesList(values, root);
        return values;
    }

    private void createValuesList(List<Entry<K, V>> valuesList, Node tree){
        if (tree == null)
            return;
        createValuesList(valuesList, tree.left);
        valuesList.add(tree.value);
        createValuesList(valuesList, tree.right);
    }

    public boolean keyExists(K key) {
        return key != null && searchTree(key, root);
    }

    private boolean searchTree(K key, Node tree) {
        if (tree == null)
            return false;
        int diffFactor = key.compareTo(tree.value.getKey());
        if (diffFactor == 0)
            return true;
        if (diffFactor < 0) {
            return (searchTree(key, tree.left));
        } else {
            return (searchTree(key, tree.right));
        }
    }

    public void removeItem(K key){
        if (key == null) {
            return;
        }
        if (keyExists(key)){
            size --;
        }
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, K key) {
        if (root == null) {
            return null;
        }
        int diffFactor = key.compareTo(root.value.getKey());
        if (diffFactor > 0) {
            root.right = deleteNode(root.right, key);
        }else if (diffFactor < 0) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                 return root.left;
            }
            root.value = findMin(root.right);
            root.right = deleteNode(root.right, root.value.getKey());
        }
        return root;
    }


    private Entry<K,V> findMin(Node root) {
        if (root.left != null) {
            return findMin(root.left);
        }
        else {
            return root.value;
        }
    }

    public int getSize(){
        return size;
    }

    public V getOfKey(K key){
        Node node = searchInTree(key, root);
        if (node == null || node.value == null)
            return null;
        return node.value.getValue();
    }

    private Node searchInTree(K key, Node tree){
        if (tree == null || key == null) {
            return null;
        }
        int diffFactor = key.compareTo(tree.value.getKey());
        if (diffFactor == 0) {
            return tree;
        } else if (diffFactor < 0){
            return searchInTree(key, tree.left);
        } else {
            return searchInTree(key, tree.right);
        }

    }

    private class  Node  {

        private Node left;
        private Node right;
        private Entry<K,V> value;

        Node(Entry<K,V> value){
            this.value = value;
            left = null;
            right = null;
        }

        Node(K key, V value) {
            this.value = new Entry<K, V>(key, value);
        }
    }
}
