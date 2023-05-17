/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author euluc
 */
public abstract class touristSpot {
    //TO DO - Quando o método de converter endereço em lat/long estiver pronto, colocar o lat e long aqui
        protected int _id;
        protected String nome;
        
    public touristSpot(int _id, String nome) {
        this._id = _id;
        this.nome = nome;
    }
    
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
