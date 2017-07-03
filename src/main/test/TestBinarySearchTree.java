import static org.junit.Assert.*;

import collections.BinarySearchTree;
import exceptions.DuplicateValueException;
import exceptions.NullValueException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by Dragoshescu on 01.07.2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBinarySearchTree {

    BinarySearchTree<String> tree;


    @Before
    public void setUp() throws DuplicateValueException, NullValueException {
        tree = new BinarySearchTree<>(String::compareTo);
        tree.add("string3");
        tree.add("string2");
        tree.add("string4");
        tree.add("string6");
        tree.add("string7");
    }

    @Test
    public void testACreation() {
        BinarySearchTree<String> tree2 = new BinarySearchTree<>(String::compareTo);
        assertNotNull(tree2);
        assertEquals(0, tree2.getSize());
    }

    @Test
    public void testBGetSet() {
        assertNotNull(tree);
        assertFalse(tree.elementExists("string1"));
        assertTrue(tree.elementExists("string2"));
        assertTrue(tree.elementExists("string3"));
        assertTrue(tree.elementExists("string4"));
        assertFalse(tree.elementExists("stringasdad"));
        assertEquals(5,tree.getSize());
    }

    @Test
    public void testCDeletion() {
        int size = tree.getSize();
        tree.removeItem(null);
        assertEquals(size, tree.getSize());
        tree.removeItem("string5");
        assertEquals(size, tree.getSize());
        System.out.println(tree.getAsList());
        tree.removeItem("string2");
        System.out.println(tree.getAsList());
        assertFalse(tree.elementExists("string2"));
        assertEquals(--size , tree.getSize());
        tree.removeItem("string4");
        assertEquals(--size, tree.getSize());
        assertFalse(tree.elementExists("string4"));
        System.out.println(tree.getAsList());
        tree.removeItem("string3");
        assertEquals(--size, tree.getSize());
        System.out.println(tree.getAsList());
        assertFalse(tree.elementExists("string3"));
    }
}
