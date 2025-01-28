package passoff.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class EqualsTestingUtility<T> {
    private String itemsPlural;
    private T original;
    private T equal;
    private Collection<T> allDifferent;

    protected abstract String getItemsPlural();
    protected abstract T buildOriginal();
    protected abstract Collection<T> buildAllDifferent();


    @BeforeEach
    public void setUp() {
        itemsPlural = getItemsPlural();
        original = buildOriginal();
        equal = buildOriginal(); // For a second time
        allDifferent = buildAllDifferent();
    }

    @Test
    @DisplayName("Equals Testing")
    public void equalsTest() {
        Assertions.assertEquals(original, equal, "equals returned false for equal " + itemsPlural);
        for (var different : allDifferent) {
            Assertions.assertNotEquals(original, different, "equals returned true for different " + itemsPlural);
        }
    }

    @Test
    @DisplayName("HashCode Testing")
    public void hashTest() {
        Assertions.assertEquals(original.hashCode(), equal.hashCode(),
                "hashCode returned different values for equal " + itemsPlural);
        for (var different : allDifferent) {
            Assertions.assertNotEquals(original.hashCode(), different.hashCode(),
                    "hashCode returned the same value for different " + itemsPlural);
        }
    }

    @Test
    @DisplayName("Equals & HashCode Testing")
    public void hashSetTest() {
        Set<T> set = new HashSet<>();
        set.add(original);

        // Manually test insertion of original & equal items
        Assertions.assertTrue(set.contains(original));
        Assertions.assertTrue(set.contains(equal));
        Assertions.assertEquals(1, set.size());
        set.add(equal);
        Assertions.assertEquals(1, set.size());

        // Programmatically test insertion of all different items
        int expectedSetSize = 1;
        for (var different : allDifferent) {
            Assertions.assertFalse(set.contains(different),
                    "Different item should not be present in set before insertion");
            set.add(different);
            expectedSetSize++;
            Assertions.assertEquals(expectedSetSize, set.size(),
                    "New item was counted as different during insertion");
        }

    }

}
