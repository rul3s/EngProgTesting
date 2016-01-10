/*
 * Copyright (C) 2016 raul
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package local;

import data.Party;
import data.Vote;
import java.util.HashSet;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import services.VotesDB;

/**
 *
 * @author raul
 */
public class VoteProcessorTest {
    private VoteDB_SUT voteDB;
    private VoteProcessor voteProcessor;
    private VoteCounter voteCounter;
    private HashSet<Party> validParties;
    private Party a,b,c,NULL,BLANK;
    
    public void resetCounters(){
        voteCounter.voteMap.put(a, 0);
        voteCounter.voteMap.put(b, 0);
        voteCounter.voteMap.put(c, 0);
        voteCounter.voteMap.put(NULL, 0);
        voteCounter.voteMap.put(BLANK, 0);
    }
    
    @Before
    public void initVoteProcessorTest() {
        voteDB = new VoteDB_SUT();
        validParties = new HashSet<>();
        voteProcessor = new VoteProcessor(voteDB, validParties);
        
        a = new Party("PartyA");
        b = new Party("PartyB");
        c = new Party("PartyC");
        NULL = new Party("NULL");
        BLANK = new Party("");
        
    }

    /**
     * Test of count method, of class VoteProcessor.
     */
    @Test
    public void testCountSimple() {
        validParties.clear();
        validParties.add(a);
        
        voteDB.registerVote(new Vote("PartyA"));
        voteCounter = voteProcessor.count();
        assertTrue(voteCounter.voteMap.get(a) == 1);
        assertTrue(voteCounter.getTotal() == 1);
    }
    
    
    @Test
    public void testCountMultiple() {
        validParties.clear();
        validParties.add(a);
        
        for(int x=0;x<100;x++)voteDB.registerVote(new Vote("PartyA"));
        voteCounter = voteProcessor.count();
        assertTrue(voteCounter.voteMap.get(a) == 100);
        assertTrue(voteCounter.getTotal() == 100);
    }
    
    
    @Test
    public void testCountMixedMultiple() {
        validParties.clear();
        validParties.add(a);
        validParties.add(b);
        validParties.add(c);
        validParties.add(NULL);
        validParties.add(BLANK);
        
        for(int x=0;x<200;x++)voteDB.registerVote(new Vote("PartyA"));
        for(int x=0;x<300;x++)voteDB.registerVote(new Vote("PartyB"));
        for(int x=0;x<400;x++)voteDB.registerVote(new Vote("PartyC"));
        for(int x=0;x<500;x++)voteDB.registerVote(new Vote("Party A"));
        for(int x=0;x<500;x++)voteDB.registerVote(new Vote("FHAKSDH"));
        for(int x=0;x<500;x++)voteDB.registerVote(new Vote("fsdfasdf"));
        for(int x=0;x<600;x++)voteDB.registerVote(new Vote(""));
        voteCounter = voteProcessor.count();
        assertTrue(voteCounter.voteMap.get(a) == 200);
        assertTrue(voteCounter.voteMap.get(b) == 300);
        assertTrue(voteCounter.voteMap.get(c) == 400);
        assertTrue(voteCounter.voteMap.get(NULL) == 1500);
        assertTrue(voteCounter.voteMap.get(BLANK) == 600);
        assertTrue(voteCounter.getTotal() == 3000);
    }
    
    
    private class VoteDB_SUT implements VotesDB{
        private LinkedList<Vote> votes;
        
        public VoteDB_SUT(){
            votes = new LinkedList<>();
        }
        
        @Override
        public void registerVote(Vote vote) {
            votes.add(vote);
        }

        @Override
        public LinkedList<Vote> getVotes() {
            return votes;
        }
    }
    
}
