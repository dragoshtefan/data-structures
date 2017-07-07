import collections.HashTable;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHashTable {

    HashTable<Integer, Integer> table;
    @Before
    public void setUp() {
        table = new HashTable<>(2);
    }
}
