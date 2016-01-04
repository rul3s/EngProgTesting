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
 * Represents an Activation Code
 * @author raul
 */
public class ActivationCode {
    private final String code;
    
    public ActivationCode(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    @Override
    public String toString() {
        return "ActivationCode{" + "code='" + code + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationCode activationCode = (ActivationCode) o;
        
        return code.equals(activationCode.code);
    }
}
