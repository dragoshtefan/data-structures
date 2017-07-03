package collections;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.regexp.internal.RE;
import exceptions.DuplicateValueException;
import exceptions.NullValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntBiFunction;

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

    public boolean removeItem2(T element) {
        if (root == null || element == null) {
            return false;
        }
        Node child = root;
        Node parent = child;
        int diffFac = keyGen.applyAsInt(element, child.value);
        while ((diffFac != 0) && (child != null)) {
            if (diffFac < 0) {
                parent = child;
                child = child.left;
            }
            else {
                parent = child;
                child = child.right;
            }
            if (child != null)
                diffFac = keyGen.applyAsInt(element, child.value);
        }
        if (diffFac == 0) {
            Node replacementParent = parent;
            Node replacement = child;
            if (replacement.right != null) {
                replacement = replacement.right;
                while (replacement.left != null) {
                    replacementParent = replacement;
                    replacement = replacement.left;
                }
            }
            replacementParent.left = replacement.right;
            if (keyGen.applyAsInt(element, root.value) == 0) {
                root.value = replacement.value;
            } else if ((parent.left != null) && (keyGen.applyAsInt(parent.left.value, element) == 0)) {
                parent.left.value = replacement.value;
            } else if ((parent.right != null) && (keyGen.applyAsInt(parent.right.value, element) == 0)) {
                parent.right.value = replacement.value;
            }
            size --;
            return true;
        }
        return false;
    }

    public boolean removeItem(T item) {
        if (item == null || root == null) {
            return false;
        }
        /*
        we're looking for the node containing the searched value and its parent
         */
        Node val = root;
        Node valParent = root;
        int diffFactor = keyGen.applyAsInt(item, root.value);
        while ((diffFactor != 0) && (val != null)) {
            diffFactor = keyGen.applyAsInt(item, val.value);
            if (diffFactor < 0) {
                valParent = val;
                val = val.left;
            }
            if (diffFactor > 0) {
                valParent = val;
                val = val.right;
            }
        }
        /*
        if we haven't found a node then the value doesn't exist in the tree
         */
        if (val == null) {
            return false;
        }
        /*
        we're looking for the node whose value will replace the value node
        this will be the leftmost node in the right subtree of the val node,
        or, if the right subtree is empty, the left node one level under
         */
        Node replace = val;
        Node replaceParent = valParent;
        if (replace.right == null) {
            replace = replace.left;
        } else {
            replace = replace.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
        }
        /*
        if we haven't found a replace, the node val must be a leaf
         */
        if (replace == null) {
            /*
            it might be that its the only node, therefore node to delete is root
             */
            if (root == val) {
                root = null;
                size --;
                return true;
            }
            if (valParent.left == val) {
                valParent.left = null;
            }
            if (valParent.right == val) {
                valParent.right = null;
            }
            return true;
        }
        val.value = replace.value;
        replaceParent =
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
