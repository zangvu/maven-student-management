package com.giangvt.unittest.services;

import static com.giangvt.unittest.model.Race.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import com.giangvt.unittest.model.Movie;
import com.giangvt.unittest.model.Ring;
import com.giangvt.unittest.model.TolkienCharacter;
import com.giangvt.unittest.services.DataService;


class DataServiceTest {
    DataService dataService;

    @BeforeEach
    void setUp() throws Exception {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        assertAll("frodo info",
                () -> assertEquals(33, frodo.age),
                () -> assertEquals("Frodo", frodo.getName()),
                () -> assertNotEquals("Frodon", frodo.getName()));
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        assertEquals(sameJake, jake);
        assertNotEquals(jakeClone, jake);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        assertNotEquals(tolkienCharacter.getClass(), Movie.class);

//        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
//        assertFalse(Movie.class.isAssignableFrom(tolkienCharacter.getClass()));
//        assertTrue(TolkienCharacter.class.isAssignableFrom(tolkienCharacter.getClass()));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        TolkienCharacter fellow = dataService.getFellowshipCharacter("Lars");
        System.out.println(fellow);
        assertNull(fellow);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        assertNotNull(dataService.getFellowshipCharacter("Frodo"));
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        assertAll("Frodo and Gandalf in fellowship",
                () -> assertNotNull(fellowship.stream().filter(e -> e.getName().equals("Frodo")).findFirst().get()),
                () -> assertNotNull(fellowship.stream().filter(e -> e.getName().equals("Frodo")).findFirst().get()));


//        List<TolkienCharacter> fellowship = dataService.getFellowship();
//        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
//        TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, MAIA);
//
//        assertTrue(fellowship.contains(frodo));
//        assertTrue(fellowship.contains(gandalf));
    }

    @Test
    void ensureThatOneRingBearerIsPartOfTheFellowship() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        Map<Ring, TolkienCharacter> ringBearers = dataService.getRingBearers();

        assertTrue(ringBearers.values().stream().anyMatch(ringBearer -> fellowship.contains(ringBearer)));

    }

    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        String[] flag = new String[]{"Frodo", "Sam", "Merry", "Pippin", "Gandalf", "Legolas", "Gimli", "Aragorn", "Boromir"};
        int i = 0;
        for (TolkienCharacter fellow :
                fellowship) {
            assertEquals(fellow, dataService.getFellowshipCharacter(flag[i]));
            i++;
        }
//        assertEquals(dataService.getFellowshipCharacter("Frodo"), fellowship.get(0));
//        assertEquals(dataService.getFellowshipCharacter("Sam"), fellowship.get(1));
//        assertEquals(dataService.getFellowshipCharacter("Merry"), fellowship.get(2));
//        assertEquals(dataService.getFellowshipCharacter("Pippin"), fellowship.get(3));
//        assertEquals(dataService.getFellowshipCharacter("Gandalf"), fellowship.get(4));
//        assertEquals(dataService.getFellowshipCharacter("Legolas"), fellowship.get(5));
//        assertEquals(dataService.getFellowshipCharacter("Gimli"), fellowship.get(6));
//        assertEquals(dataService.getFellowshipCharacter("Aragorn"), fellowship.get(7));
//        assertEquals(dataService.getFellowshipCharacter("Boromir"), fellowship.get(8));
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
//        assertTrue(fellowship.stream().allMatch(member -> {
//            if ((member.getRace().equals(HOBBIT) || member.getRace().equals(MAN)) && member.age >= 100) {
//                return false;
//            }
//            if ((member.getRace().equals(ELF) || member.getRace().equals(DWARF)) && member.age <= 100) {
//                return false;
//            }
//            return true;
//        }));

        assertTrue(fellowship
                .stream()
                .filter(member -> member.getRace().equals(HOBBIT) || member.getRace().equals(MAN))
                .allMatch(member -> member.age < 100));

        assertTrue(fellowship
                .stream()
                .filter(member -> member.getRace().equals(ELF) || member.getRace().equals(MAIA))
                .allMatch(member -> member.age > 100));

    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
    }


    @Test
    @DisplayName("Ensure that access to the fellowship throws exception outside the valid range")
    void exceptionTesting() {
        DataService dataService = new DataService();
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> dataService.getFellowship().get(20));
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
//    @Disabled("Please fix and enable")
    public void ensureThatAgeMustBeLargerThanZeroViaSetter() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use assertThrows() rule to check that the message is:
        // Age is not allowed to be smaller than zero
//        frodo.setAge(-1);
        assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1));

    }

    @Test
//    @Disabled("Please fix and enable")
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"

//        TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> new TolkienCharacter("Frodo", -1, HOBBIT));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }
}
