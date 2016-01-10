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
import local.VoteProcessor;
import services.*;

/**
 * Implements a simplification of Use Case: Emit Vote
 * @author raul
 */
public class VotingMachine {
    private final ValidationService validationService;
    private final VotePrinter votePrinter;
    private final VotesDB votesDB;
    private final VoteProcessor voteProcessor;
    public HashSet<Party> validParties;
    private boolean canVote = false;
    
    public VotingMachine(ValidationService validationService,
            VotePrinter votePrinter,VotesDB votesDB) {
        this.validationService = validationService;
        this.votePrinter = votePrinter;
        this.votesDB = votesDB;
        voteProcessor = new VoteProcessor(votesDB, validParties);
    }
    
    public void activateEmission(ActivationCode code)
            throws IllegalStateException {
        validationService.validate(code);
        canVote = validationService.check(code);
        validationService.deactivate(code);
    }
    
    public boolean canVote(){
        return canVote;
    }
    
    public void vote(Vote vote) throws IllegalStateException {
        try{
            if(canVote){
                votesDB.registerVote(vote);
                canVote=false;
            }
            else{
                Exception e = new IllegalStateException();
                throw e;
            }
        }catch(Exception e){
            
        }
    }
}
