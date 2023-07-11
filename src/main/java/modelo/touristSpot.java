/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author euluc
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spot_type")
public abstract class touristSpot implements Serializable {

    //TO DO - Quando o método de converter endereço em lat/long estiver pronto, colocar o lat e long aqui
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int codigo;
    
    protected int _id;
    @Transient
    protected String nome;
    protected String tipoDeAtracao;
    @Transient
    protected double latitude;
    @Transient
    protected double longitude;
    @Transient
    protected double currentDistanciaParaLoc;
    @ManyToMany(mappedBy = "favoritos")
    private List<Usuario> usuarios;

    public touristSpot() {
    }

    public touristSpot(int _id, String nome, double latitude, double longitude) {
        this._id = _id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.setTipoDeAtracao("");
        this.currentDistanciaParaLoc = 0.0;
    }

    public touristSpot(int _id, String nome) {
        this._id = _id;
        this.nome = nome;
        this.currentDistanciaParaLoc = 0.0;
        this.setTipoDeAtracao("");
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTipoDeAtracao() {
        return tipoDeAtracao;
    }

    public void setTipoDeAtracao(String tipoDeAtracao) {
        String currentString = this.getClass().getSimpleName();
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < currentString.length(); i++) { //Começa do 1 para n contar a primeira letra.
            char c = currentString.charAt(i);

            if (Character.isUpperCase(c) && i > 1) {
                resultado.append(' ');
            }

            resultado.append(c);
        }

        this.tipoDeAtracao = resultado.toString();
    }

    public double getCurrentDistanciaParaLoc() {
        return currentDistanciaParaLoc;
    }

    public void setCurrentDistanciaParaLoc(double currentDistanciaParaLoc) {
        this.currentDistanciaParaLoc = currentDistanciaParaLoc;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

@Override
public int hashCode() {
    int hash = 0;
    hash += (codigo != 0 ? Integer.valueOf(codigo).hashCode() : 0);
    return hash;
}

@Override
public boolean equals(Object object) {
    if (!(object instanceof touristSpot)) {
        return false;
    }
    touristSpot other = (touristSpot) object;
    return !((codigo == 0 && other.codigo != 0)
            || (codigo != 0 && !Integer.valueOf(codigo).equals(other.codigo)));
}

}
