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

import data.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test class VoteCounter
 * @author raul
 */

public class VoteCounterTest {
    private Party a,b,c,NULL,BLANK;
    private VoteCounter voteCounter;
    
    public void resetCounters(){
        voteCounter.voteMap.put(a, 0);
        voteCounter.voteMap.put(b, 0);
        voteCounter.voteMap.put(c, 0);
        voteCounter.voteMap.put(NULL, 0);
        voteCounter.voteMap.put(BLANK, 0);
    }
    
    @Before
    public void initVoteCounter() {
        voteCounter = new VoteCounter();
        a = new Party("PartyA");
        b = new Party("PartyB");
        c = new Party("PartyC");
        NULL = new Party("NULL");
        BLANK = new Party("");
        resetCounters();
    }
    
    @Test
    public void testMap() {
        voteCounter.voteMap.clear();
        resetCounters();
        voteCounter.voteMap.put(a, 50);
        voteCounter.voteMap.put(b, 50);
        voteCounter.voteMap.put(c, 50);
        voteCounter.voteMap.put(NULL, 50);
        voteCounter.voteMap.put(BLANK, 50);
        
        assertTrue(voteCounter.voteMap.get(a) == 50);
        assertTrue(voteCounter.voteMap.get(b) == 50);
        assertTrue(voteCounter.voteMap.get(c) == 50);
        assertTrue(voteCounter.voteMap.get(NULL) == 50);
        assertTrue(voteCounter.voteMap.get(BLANK) == 50);
    }
    
    @Test
    public void testAddingVotes() {
        //Test individual votes
        voteCounter.voteMap.clear();
        resetCounters();
        voteCounter.countValid(a);
        voteCounter.countValid(b);
        voteCounter.countValid(c);
        voteCounter.countValid(NULL);
        voteCounter.countValid(BLANK);
        
        assertTrue(voteCounter.voteMap.get(a) == 1);
        assertTrue(voteCounter.voteMap.get(b) == 1);
        assertTrue(voteCounter.voteMap.get(c) == 1);
        assertTrue(voteCounter.voteMap.get(NULL) == 1);
        assertTrue(voteCounter.voteMap.get(BLANK) == 1);
        
        //Test multiple votes
        voteCounter.voteMap.clear();
        resetCounters();
        for(int x = 0; x<500; x++) voteCounter.countValid(a);
        for(int x = 0; x<500; x++) voteCounter.countValid(b);
        for(int x = 0; x<500; x++) voteCounter.countValid(c);
        for(int x = 0; x<500; x++) voteCounter.countValid(NULL);
        for(int x = 0; x<500; x++) voteCounter.countValid(BLANK);
        
        assertTrue(voteCounter.voteMap.get(a) == 500);
        assertTrue(voteCounter.voteMap.get(b) == 500);
        assertTrue(voteCounter.voteMap.get(c) == 500);
        assertTrue(voteCounter.voteMap.get(NULL) == 500);
        assertTrue(voteCounter.voteMap.get(BLANK) == 500);
        
        //Test mixed and multiple votes
        voteCounter.voteMap.clear();
        resetCounters();
        for(int x = 0; x<1000; x++){
            voteCounter.countValid(a);
            voteCounter.countValid(b);
            voteCounter.countValid(c);
            voteCounter.countValid(NULL);
            voteCounter.countValid(BLANK);
        }
        
        assertTrue(voteCounter.voteMap.get(a) == 1000);
        assertTrue(voteCounter.voteMap.get(b) == 1000);
        assertTrue(voteCounter.voteMap.get(c) == 1000);
        assertTrue(voteCounter.voteMap.get(BLANK) == 1000);
        assertTrue(voteCounter.voteMap.get(NULL) == 1000);
    }
    
    @Test
    public void testAddingBlank() {
        voteCounter.voteMap.clear();
        resetCounters();
        voteCounter.countBlank();
        assertTrue(voteCounter.voteMap.get(BLANK) == 1);
        
        voteCounter.voteMap.clear();
        resetCounters();
        for(int x = 0; x<500; x++) voteCounter.countBlank();
        assertTrue(voteCounter.voteMap.get(BLANK) == 500);
    }
    
    @Test
    public void testAddingNull() {
        voteCounter.voteMap.clear();
        resetCounters();
        voteCounter.countNull();
        assertTrue(voteCounter.voteMap.get(NULL) == 1);
        
        voteCounter.voteMap.clear();
        resetCounters();
        for(int x = 0; x<500; x++) voteCounter.countNull();
        assertTrue(voteCounter.voteMap.get(NULL) == 500);
    }
    
    @Test
    public void testGetTotal(){
        voteCounter.voteMap.clear();
        resetCounters();
        for(int x = 0; x<1000; x++){
            voteCounter.countValid(a);
            voteCounter.countValid(b);
            voteCounter.countValid(c);
            voteCounter.countValid(NULL);
            voteCounter.countValid(BLANK);
        }
        
        assertTrue(voteCounter.getTotal() == 5000);
    }
}