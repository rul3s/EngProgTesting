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

import data.Vote;
import java.util.LinkedList;
import services.VotesDB;

/**
 *
 * @author raul
 */
public class VoteDB_SUT implements VotesDB{
        private final LinkedList<Vote> votes;
        
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
