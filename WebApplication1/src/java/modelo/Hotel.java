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
public class Hotel extends touristSpot{
  
    private String endereco;
    private String telefone;
    private String site;
    private double latitude;
    private double longitude;



    // Construtor com todos os atributos
    public Hotel(int _id, String nome, String endereco, String telefone, String site,
                  double latitude, double longitude) {
        super(_id, nome);
        this.endereco = endereco;
        this.telefone = telefone;
        this.site = site;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters e Setters para todos os atributos

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
        return "Hoteis{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", site='" + site + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

