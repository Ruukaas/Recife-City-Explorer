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
public class FeiraLivre extends touristSpot {
    private String localizacao;
    private String dias;
    private String horario;
    private String observacao;
    private double latitude;
    private double longitude;

  

    // Construtor com todos os atributos
    public FeiraLivre(int _id, String nome, String localizacao, String dias, String horario,
                      String observacao, double latitude, double longitude) {
        super(_id, nome);
        this.localizacao = localizacao;
        this.dias = dias;
        this.horario = horario;
        this.observacao = observacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters e Setters para todos os atributos

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    // Método toString para imprimir informações do objeto
    @Override
    public String toString() {
        return "FeiraLivre{" +
                "_id=" + this._id +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", dias='" + dias + '\'' +
                ", horario='" + horario + '\'' +
                ", observacao='" + observacao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
