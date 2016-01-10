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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raul
 */
public class VoteClassifierTest {
    private VoteClassifier voteClassifier;
    public HashSet<Party> validParties;
    private Party a,b,c;
    
    @Before
    public void initVoteClassifier(){
        a = new Party("PartyA");
        b = new Party("PartyB");
        c = new Party("PartyC");
        validParties = new HashSet<>();
        validParties.add(a);
        validParties.add(b);
        validParties.add(c);
        voteClassifier = new VoteClassifier(validParties);
    }

    /**
     * Test of isBlank method, of class VoteClassifier.
     */
    @Test
    public void testIsBlank() {
        assertTrue(voteClassifier.isBlank(new Vote("")));
    }

    /**
     * Test of isNull method, of class VoteClassifier.
     */
    @Test
    public void testIsNull() {
        assertTrue(voteClassifier.isNull(new Vote("asddfasd")));
    }

    /**
     * Test of isValid method, of class VoteClassifier.
     */
    @Test
    public void testIsValid() {
        voteClassifier.validParties.add(a);
        voteClassifier.validParties.add(b);
        voteClassifier.validParties.add(c);
        assertTrue(voteClassifier.isValid(new Vote("PartyA")));
        assertTrue(voteClassifier.isValid(new Vote("PartyB")));
        assertTrue(voteClassifier.isValid(new Vote("PartyC")));
    }
}