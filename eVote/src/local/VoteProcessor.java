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
import services.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;


/**
 *
 * @author raul
 */
public class VoteProcessor {
    private final VotesDB votesDB;
    private final HashSet<Party> validParties;
    private VoteCounter vCount;
    private VoteClassifier vClass;   
    
    public VoteProcessor(VotesDB votesDB, HashSet<Party> validParties) {
        this.votesDB = votesDB;
        this.validParties = validParties;
    }
    
    public VoteCounter count() {
        Vote vote;
        vCount = new VoteCounter();
        vClass = new VoteClassifier(validParties);
        
        initValidParties();
        
        ListIterator<Vote> itVotes = votesDB.getVotes().listIterator();
        while(itVotes.hasNext()){
            vote = (Vote) itVotes.next();
            if(vClass.isBlank(vote)) vCount.countBlank();
            else if(vClass.isNull(vote)) vCount.countNull();
            else if(vClass.isValid(vote))vCount.countValid(new Party(vote.getOption()));
        }
        
        return vCount;
    }
    
    private void initValidParties(){
        Iterator itParties = validParties.iterator();
        while(itParties.hasNext()){
            vCount.voteMap.put((Party)itParties.next(), 0);
        }
        vCount.voteMap.put(new Party(""),0);
        vCount.voteMap.put(new Party("NULL"),0);
    }
}
