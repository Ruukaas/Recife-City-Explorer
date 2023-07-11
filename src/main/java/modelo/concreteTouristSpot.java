/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author euluc
 */

@Entity
public class concreteTouristSpot extends touristSpot implements Serializable{

    public concreteTouristSpot() {
    }

    public concreteTouristSpot(int _id, String nome, double latitude, double longitude) {
        super(_id, nome, latitude, longitude);
    }

    public concreteTouristSpot(int _id, String nome) {
        super(_id, nome);
    }
    
    
    
}
