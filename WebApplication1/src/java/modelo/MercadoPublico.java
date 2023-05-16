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
public class MercadoPublico extends touristSpot{
    
    private String descricao;
    private String bairro;
    private double latitude;
    private double longitude;

    // Construtor com todos os atributos
    public MercadoPublico(int _id, String nome, String descricao, String bairro, double latitude, double longitude) {
        super(_id, nome);
        this.descricao = descricao;
        this.bairro = bairro;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters e Setters para todos os atributos
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
        return "MercadoPublico{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

