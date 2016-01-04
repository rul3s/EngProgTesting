/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * Represents a party
 * @author raul
 */
public class Party {
    private final String name;
    
    public Party(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    @Override
    public String toString() {
        return "Party{" + "name='" + name + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        
        return name.equals(party.name);
    }
}
