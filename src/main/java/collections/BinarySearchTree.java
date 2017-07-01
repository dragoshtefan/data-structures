package collections;

import com.sun.istack.internal.NotNull;
import exceptions.DuplicateValueException;
import exceptions.NullValueException;

import java.util.Objects;
import java.util.function.ToIntBiFunction;

public class BinarySearchTree <T> {

    private Node root;
    private ToIntBiFunction<T, T> keyGen;

    public BinarySearchTree() {

    }

    public void add(T item) throws NullValueException, DuplicateValueException {
        if (item == null) {
            throw new NullValueException();
        }
        Node newNode = new Node(item);
        if (root == null) {
            root = newNode;
        }
        else {
            addInSubTree(newNode, root);
        }
    }

    private void addInSubTree(Node newNode, @NotNull Node subTree) throws DuplicateValueException {
        int compareFactor = keyGen.applyAsInt(newNode.value, subTree.value);
        if (compareFactor == 0) {
            throw new DuplicateValueException(newNode.value);
        }
        if (compareFactor > 0) {
            if (subTree.right == null) {
                subTree.right = newNode;
            }
            else {
                addInSubTree(newNode, subTree.right);
            }
        } else if (compareFactor < 0) {
            if (subTree.left == null){
                subTree.left = newNode;
            } else {
                addInSubTree(newNode, subTree.right);
            }
        }
    }

    ToIntBiFunction setKeyGen(ToIntBiFunction<T, T> keyGen){
        this.keyGen = keyGen;
        return keyGen;
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
