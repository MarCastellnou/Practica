package kioskTests;
import data.Party;
import kiosk.VoteCounter;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoteCounterTests {
    HashSet<Party> parties;
    VoteCounter count;

    @BeforeEach
    public void BeforeEach(){
        parties = new HashSet<>();
        Party partido = new Party("p");
        Party partido1 = new Party("p1");
        Party partido2 = new Party("p2");
        Party partido3 = new Party("p3");
        Party partido4 = new Party("p4");
        parties.add(partido);
        parties.add(partido1);
        parties.add(partido2);
        parties.add(partido3);
        parties.add(partido4);
        count = new VoteCounter(parties);
    }

    @Test
    public void countPartyTest() throws Exception {
        Party part = new Party("part");
        Party part1 = new Party("part1");
        Party part2 = new Party("part2");
        Party part3 = new Party("p1");
        Party part4 = new Party("p4");
        assertThrows(Exception.class, () -> count.countParty(part));
        assertThrows(Exception.class, () -> count.countParty(part1));
        assertThrows(Exception.class, () -> count.countParty(part2));
        count.countParty(part3);
        count.countParty(part4);
        assertEquals(0,count.getBlanks());
        assertEquals(0,count.getNulls());
        assertEquals(2,count.getTotal());
    }
    @Test
    public void scrutinizeTest(){
        Party partido = new Party(" ");
        Party partido1 = new Party(" ");
        Party partido2 = new Party(" ");
        Party partido3 = new Party("null");
        Party partido4 = new Party("null");
        Party part3 = new Party("p1");
        Party part4 = new Party("p4");

        count.scrutinize(partido);
        count.scrutinize(partido1);
        count.scrutinize(partido2);
        count.scrutinize(partido3);
        count.scrutinize(partido4);
        count.scrutinize(part3);
        count.scrutinize(part4);

        assertEquals(3,count.getBlanks());
        assertEquals(2,count.getNulls());
        assertEquals(7,count.getTotal());

    }
    @Test
    public void getVoterForTest(){
        Party partido = new Party ("p1");
        Party partido2 = new Party ("p2");
        count.scrutinize(partido);
        count.scrutinize(partido);
        count.scrutinize(partido2);
        assertEquals(2,count.getVotesFor(partido));
        assertEquals(1,count.getVotesFor(partido2));
    }
    @Test
    public void getBlanksTest(){
        Party partido = new Party ("p1");
        Party partido2 = new Party (" ");
        count.scrutinize(partido);
        count.scrutinize(partido);
        count.scrutinize(partido2);
        assertEquals(1,count.getBlanks());
    }
    @Test
    public void getNullsTest(){
        Party partido = new Party ("null");
        Party partido2 = new Party (" ");
        count.scrutinize(partido);
        count.scrutinize(partido2);
        assertEquals(1,count.getNulls());
    }
    @Test
    public void getTotalTest(){
        Party partido = new Party ("null");
        Party partido2 = new Party (" ");
        Party partido3 = new Party ("p1");
        count.scrutinize(partido);
        count.scrutinize(partido2);
        count.scrutinize(partido3);
        count.scrutinize(partido3);
        count.scrutinize(partido);
        assertEquals(5,count.getTotal());
    }
}
