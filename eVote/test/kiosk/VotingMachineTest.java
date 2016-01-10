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
package kiosk;

import data.*;
import java.util.HashSet;
import local.ValidationServiceSUT;
import local.VoteCounter;
import local.VoteDB_SUT;
import local.VotePrinterSUT;
import local.VoteProcessor;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
/**
 *
 * @author raul
 */
public class VotingMachineTest {
    private VoteDB_SUT voteDB;
    private VoteProcessor voteProcessor;
    private VoteCounter voteCounter;
    private VotingMachine votingMachine;
    private ValidationServiceSUT validationService;
    private VotePrinterSUT votePrinter;
    private HashSet<Party> validParties;
    private Party a,b,c,NULL,BLANK;
    
    @Before
    public void initVotingMachineTest() {
        voteDB = new VoteDB_SUT();
        validParties = new HashSet<>();
        validationService = new ValidationServiceSUT();
        votePrinter = new VotePrinterSUT();
        voteProcessor = new VoteProcessor(voteDB, validParties);
        votingMachine = new VotingMachine(validationService, votePrinter, voteDB);
        votingMachine.validParties = this.validParties;
        
        
        a = new Party("PartyA");
        b = new Party("PartyB");
        c = new Party("PartyC");
        NULL = new Party("NULL");
        BLANK = new Party("");
        
        validParties.add(a);
        validParties.add(b);
        validParties.add(c);
        validParties.add(NULL);
        validParties.add(BLANK);
        
        validationService.validCodes.clear();
    }

    @Test
    public void emitVoteSimpleOK(){
        Vote vote = new Vote("PartyA");
        ActivationCode code = new ActivationCode("0001");
        votingMachine.activateEmission(code);
        assertTrue(votingMachine.canVote());
        votingMachine.vote(vote);
        votePrinter.print(vote);
        voteCounter = voteProcessor.count();
        assertEquals(voteCounter.getTotal(),1);
        assertTrue(voteCounter.voteMap.get(new Party("PartyA")).equals(1));
    }
    
    @Test
    public void emitVoteSimpleIncorrect(){
        Vote vote = new Vote("Party A");
        ActivationCode code = new ActivationCode("0001");
        votingMachine.activateEmission(code);
        assertTrue(votingMachine.canVote());
        votingMachine.vote(vote);
        votePrinter.print(vote);
        voteCounter = voteProcessor.count();
        assertEquals(voteCounter.getTotal(),1);
        assertTrue(voteCounter.voteMap.get(new Party("PartyA")).equals(0));
    }
    
    @Test
    public void emitVoteMultiple(){
        ActivationCode code;
        Vote voteA = new Vote("PartyA");
        Vote voteB = new Vote("PartyB");
        Vote voteC = new Vote("PartyC");
        Vote voteNULL = new Vote("dskjfas");
        Vote voteBLANK = new Vote("");
        
        code = new ActivationCode("0001");
        votingMachine.activateEmission(code);
        if(votingMachine.canVote()){
            votingMachine.vote(voteA);
            votePrinter.print(voteA);
        }
        
        code = new ActivationCode("0002");
        votingMachine.activateEmission(code);
        if(votingMachine.canVote()){
            votingMachine.vote(voteB);
            votePrinter.print(voteB);
        }
        
        code = new ActivationCode("0003");
        votingMachine.activateEmission(code);
        if(votingMachine.canVote()){
            votingMachine.vote(voteC);
            votePrinter.print(voteC);
        }
        
        code = new ActivationCode("0004");
        votingMachine.activateEmission(code);
        if(votingMachine.canVote()){
            votingMachine.vote(voteBLANK);
            votePrinter.print(voteBLANK);
        }
        
        code = new ActivationCode("0005");
        votingMachine.activateEmission(code);
        if(votingMachine.canVote()){
            votingMachine.vote(voteNULL);
            votePrinter.print(voteNULL);
        }
        
        voteCounter = voteProcessor.count();
        assertEquals(voteCounter.getTotal(),5);
        assertTrue(voteCounter.voteMap.get(new Party("PartyA")).equals(1));
        assertTrue(voteCounter.voteMap.get(new Party("PartyB")).equals(1));
        assertTrue(voteCounter.voteMap.get(new Party("PartyC")).equals(1));
        assertTrue(voteCounter.voteMap.get(new Party("NULL")).equals(1));
        assertTrue(voteCounter.voteMap.get(new Party("")).equals(1));
    }
    
    @Test
    public void emitVoteTwoTimes(){
        Vote vote = new Vote("PartyA");
        ActivationCode code = new ActivationCode("0001");
        votingMachine.activateEmission(code);
        votingMachine.vote(vote);
        votingMachine.vote(vote);
        votePrinter.print(vote);
        voteCounter = voteProcessor.count();
        assertEquals(voteCounter.getTotal(),1);
    }
    
}
