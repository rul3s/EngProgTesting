/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
