import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testSortByDate() {
        // Create sessions out of chronological order
        Session sEarly = new Session(7, "Early", "Alice", "2026-01-01", "Room A", 5, 10);
        Session sMid = new Session(8, "Mid", "Bob", "2026-06-01", "Room B", 3, 10);
        Session sLate = new Session(9, "Late", "Charlie", "2026-12-31", "Room C", 2, 10);

        // Build list in mixed order: Mid -> Late -> Early
        SessionList list = new SessionList(sMid, new SessionList(sLate, new SessionList(sEarly, null)));

        // Sort the list
        SessionList sorted = SessionList.sortByDate(list);

        // Verify the order is now: Early -> Mid -> Late
        assertEquals(sEarly, sorted.get(0).session(), "First element should be the earliest date");
        assertEquals(sMid, sorted.get(1).session(), "Second element should be the middle date");
        assertEquals(sLate, sorted.get(2).session(), "Third element should be the latest date");
    }

    @Test
    void testSortByDateEdgeCases() {
        // Null list check
        assertNull(SessionList.sortByDate(null));

        // Single element list check
        Session s1 = new Session(10, "Only One", "Alice", "2026-06-01", "Room A", 5, 10);
        SessionList singleList = new SessionList(s1, null);
        SessionList sortedSingle = SessionList.sortByDate(singleList);

        assertNotNull(sortedSingle);
        assertEquals(s1, sortedSingle.session());
        assertNull(sortedSingle.rest());
    }
}
