/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author farah
 */
public class Type {
    private int id;
    private String nomType, descriptionType;
     private List<Activite> activites;
    
    public Type(){
    }
    
    public Type(int id, String nomType, String descriptionType){
        this.id=id;
        this.nomType=nomType;
        this.descriptionType=descriptionType;
    }
    
    public Type(int id, String nomType){
        this.id=id;
        this.nomType=nomType;
    }
    public Type(String nomType, String descriptionType){
        this.nomType=nomType;
        this.descriptionType=descriptionType;
    }
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    
    public String getNomType(){
        return nomType;
    }
    public void setNomType(String nomType){
        this.nomType=nomType;
    }
    public String getDescriptionType(){
        return descriptionType;
    }
    
    public void setDescriptionType(String descriptionType)
    {
        this.descriptionType=descriptionType;
    }
    
    @Override
    public String toString(){
        return "Type: "+"nom type= "+nomType+"description="+descriptionType;
    }
}
