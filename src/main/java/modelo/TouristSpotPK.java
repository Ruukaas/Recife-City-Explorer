/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author euluc
 */
public class TouristSpotPK implements Serializable {

    private int id;
    private String tipoDeAtracao;

    public TouristSpotPK() {
    }

    public TouristSpotPK(int id, String tipoDeAtracao) {
        this.id = id;
        this.tipoDeAtracao = tipoDeAtracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDeAtracao() {
        return tipoDeAtracao;
    }

    public void setTipoDeAtracao(String tipoDeAtracao) {
        this.tipoDeAtracao = tipoDeAtracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristSpotPK that = (TouristSpotPK) o;
        return id == that.id && Objects.equals(tipoDeAtracao, that.tipoDeAtracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoDeAtracao);
    }
}

