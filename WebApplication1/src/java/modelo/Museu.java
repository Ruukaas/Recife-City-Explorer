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
public class Museu extends touristSpot{
    
    private String descricao;
    private String bairro;
    private String logradouro;
    private double latitude;
    private double longitude;
    private String telefone;
    private String site;

    // Construtor com todos os atributos
    public Museu(int _id,String nome, String descricao, String bairro, String logradouro, double latitude,
                 double longitude, String telefone, String site) {
        super(_id, nome);
        this.descricao = descricao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telefone = telefone;
        this.site = site;
    }

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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    // Método toString para imprimir informações do objeto
    @Override
    public String toString() {
        return "Museu{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", telefone='" + telefone + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
