/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author euluc
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "spot_type")
@IdClass(TouristSpotPK.class)
public abstract class touristSpot implements Serializable {

    //TO DO - Quando o método de converter endereço em lat/long estiver pronto, colocar o lat e long aqui
    @Id
    protected int id;

    @Id
    @Column(name = "tipo_de_atracao")
    protected String tipoDeAtracao;

    @Transient
    protected String nome;
    @Transient
    protected double latitude;
    @Transient
    protected double longitude;

    @ManyToMany(mappedBy = "favoritos")
    private List<Usuario> usuarios;

    //Comparar distância
    @Transient
    protected double currentDistanciaParaLoc;

    @Transient
    protected boolean isFavorite;

    public touristSpot() {
    }

    public touristSpot(int id, String nome, double latitude, double longitude) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.setTipoDeAtracao("");
        this.currentDistanciaParaLoc = 0.0;
    }

    public touristSpot(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.currentDistanciaParaLoc = 0.0;
        this.setTipoDeAtracao("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tipoDeAtracao != null ? tipoDeAtracao.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        touristSpot other = (touristSpot) object;
        return id == other.id && Objects.equals(tipoDeAtracao, other.tipoDeAtracao);
    }

}
