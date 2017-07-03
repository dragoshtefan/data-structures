package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntBiFunction;

import com.sun.istack.internal.NotNull;
import exceptions.DuplicateValueException;
import exceptions.NullValueException;

public class BinarySearchTree <T> {

    private Node root;
    private ToIntBiFunction<T, T> keyGen;
    private int size;

    public BinarySearchTree(@NotNull ToIntBiFunction<T, T> keyGen) {
        root = null;
        this.keyGen = keyGen;
        size = 0;
    }


    public void add(T item) throws NullValueException, DuplicateValueException {
        if (item == null) {
            throw new NullValueException();
        }
        if (root == null) {
            root = new Node(item);
            size ++;
        }
        else {
            addInSubTree(item, root, keyGen.applyAsInt(item, root.value));
        }
    }

    private void addInSubTree(T item, Node subTree, int compareFactor) throws DuplicateValueException {
        if (compareFactor == 0) {
            throw new DuplicateValueException(item);
        }
        if (compareFactor > 0) {
            if (subTree.right == null) {
                subTree.right = new Node(item);
                size++;
                return;
            } else {
                addInSubTree(item, subTree.right, keyGen.applyAsInt(item, subTree.right.value));
            }
        } else {
            if (subTree.left == null) {
                subTree.left = new Node(item);
                size++;
                return;
            } else {
                addInSubTree(item, subTree.left, keyGen.applyAsInt(item, subTree.left.value));
            }
        }
    }



    public List<T> getAsList() {
        List<T> values = new ArrayList<T>();
        createValuesList(values, root);
        return values;
    }

    private void createValuesList(List<T> valuesList, Node tree){
        if (tree == null)
            return;
        createValuesList(valuesList, tree.left);
        valuesList.add(tree.value);
        createValuesList(valuesList, tree.right);
    }

    ToIntBiFunction setKeyGen(@NotNull ToIntBiFunction<T, T> keyGen) {
        this.keyGen = keyGen;
        return keyGen;
    }

    public boolean elementExists(T element) {
        return element != null && searchTree(element, root);
    }

    private boolean searchTree(T element, Node tree) {
        if (tree == null)
            return false;
        int diffFactor = keyGen.applyAsInt(element, tree.value);
        if (diffFactor == 0)
            return true;
        if (diffFactor < 0) {
            return (searchTree(element, tree.left));
        } else {
            return (searchTree(element, tree.right));
        }
    }

    public void removeItem(T item){
        if (item == null) {
            return;
        }
        if (elementExists(item)){
            size --;
        }
        root = deleteNode(root, item);
    }

    private Node deleteNode(Node root, T item) {
        if (root == null) {
            return null;
        }
        int diffFactor = keyGen.applyAsInt(item, root.value);
        if (diffFactor > 0) {
            root.right = deleteNode(root.right, item);
        }else if (diffFactor < 0) {
            root.left = deleteNode(root.left, item);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                 return root.left;
            }
            root.value = findMin(root.right);
            root.right = deleteNode(root.right, root.value);
        }
        return root;
    }


    private T findMin(Node root) {
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


    private class  Node  {

        private Node left;
        private Node right;
        private T value;

        Node(T value){
            this.value = value;
            left = null;
            right = null;
        }
    }
}
