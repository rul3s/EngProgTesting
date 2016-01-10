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
import java.util.HashMap;

/**
 * A class that represents the result in an election site
 * results are saved in a HashMap "Party,Int"
 * @author raul
 */
public class VoteCounter {
    private int counter;
    private final Party NULL, BLANK;
    public HashMap<Party, Integer> voteMap;
    
    public VoteCounter(){
        counter = 0;
        NULL = new Party("NULL");
        BLANK = new Party("");
        voteMap = new HashMap<>();
        voteMap.clear();
    }
    
    public void countValid(Party party){
        counter++;
        voteMap.put(party, voteMap.get(party)+1);
    }
    
    public void countNull(){
        counter++;
        voteMap.put(NULL, (voteMap.get(NULL))+1);
    }
    
    public void countBlank(){
        counter++;
        voteMap.put(BLANK, (voteMap.get(BLANK))+1);
    }
    
    public int getTotal(){
        return this.counter;
    }
}

/*
Aquesta classe compta els vots que se li notifiquen amb els mètodes:
countValid(Party party) : Incrementa el comptador de vots que actualment té el
partit party .
countNull() : Incrementa el comptador de vots nuls.countBlank() : Incrementa el comptador de vots en blanc.
Per accedir als resultats tenim els mètodes:
getTotal() : retorna el nombre total de vots recomptats (tant vàlids com nuls i en
blanc).
getVotesFor(Party party) : retorna el nombre de vots que ha obtingut el partit,
per tant, 0 si no se li ha comptabilitzat cap vot.
getNulls() : retorna el nombre de vots nulls.
getBlanks() : retorna el nombre de vots en blanc.
Fixeu-vos que aquesta classe la podem provar de forma independent a les altres. Les
classes que representen valors, com Party , no es consideren classes col·laboradores del
SUT, sinó que son tan bàsiques com String , BigDecimal , etc.
El primer que haureu de fer és fer tests per aquesta classe i implementar-la de manera
que satisfaci els tests
*/

/*
package local;

public class VoteCounter {
    ????
    public VoteCounter() { ??? }
    public void countValid(Party party) { ??? }
    public void countNull() { ??? }
    public void countBlank() { ??? }
    public int getTotal() { ??? }
    public int getVotesFor(Party party) { ??? }
    public int getNulls() { ??? }
    public int getBlanks() { ??? }
}
*/
