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
package data;

/**
 * Represents a vote.
 * @author raul
 */
final public class Vote {
    private final String option;
    
    public Vote(String option) {
        this.option = option;
    }
    
    public String getOption() {
        return option;
    }
    
    @Override
    public String toString() {
        return "Vote{" + "option='" + option + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        
        return option.equals(vote.option);
    }
    
    @Override
    public int hashCode() {
        return option.hashCode();
    }
}