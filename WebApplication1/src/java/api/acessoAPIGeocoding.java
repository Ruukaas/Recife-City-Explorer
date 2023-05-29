/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import jdk.nashorn.internal.runtime.URIUtils;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author ALUNO
 */

//TO DO - Melhorar o valid structure
@ManagedBean
public class acessoAPIGeocoding {

    String currentSearch;

    String currentAPIURL = "https://api.opencagedata.com/geocode/v1/json?key=6a6de70f376e4015b6f857e263e4a383&q=";
    HttpURLConnection conn;

    public String getCurrentSearch() {
        return currentSearch;
    }

    public void setCurrentSearch(String currentSearch) {
        this.currentSearch = currentSearch;
    }
    int responseCode;
    BufferedReader reader;
    StringBuilder response;

    public Double[] getLatLong(String endereco) {
        Double[] currentLatLong = new Double[2];
        try {

            endereco = URLEncoder.encode(endereco);
            try {
                URL url = new URL(currentAPIURL.concat(endereco).concat("&limit=1"));
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                responseCode = conn.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Não foi possível encontrar a localização",
                                "Tente novamente mais tarde"));
            }
            //JSON
            JSONArray jsonArray = null;

            try {
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    jsonArray = new JSONObject(response.toString()).getJSONArray("results");
                } else {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "Não foi possível encontrar a localização",
                                    "Tente novamente mais tarde"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Não foi possível encontrar a localização",
                                "Tente novamente mais tarde"));
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Não foi possível encontrar a localização",
                                "Tente novamente mais tarde"));
            }

            JSONObject current = jsonArray.getJSONObject(0).getJSONObject("geometry");

            currentLatLong[0] = Double.parseDouble(current.getString("lat"));
            currentLatLong[1] = Double.parseDouble(current.getString("lng"));
//        System.out.println(current.toStrisng());;

        } catch (JSONException ex) {
            Logger.getLogger(acessoAPIGeocoding.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Não foi possível encontrar a localização",
                            "Tente novamente mais tarde"));
        }
        return currentLatLong;
    }

    public boolean hasValidStructure() {
        int count = 0;
        int index = -1;

        // Procurar pela primeira vírgula
        index = currentSearch.indexOf(",");
        if (index != -1) {
            count++;
            // Verificar se há algum caractere antes da primeira vírgula
            if (index > 0) {
                // Existe um caractere antes da primeira vírgula
            } else {
                // Não há nenhum caractere antes da primeira vírgula
                return false;
            }
        } else {
            // Não foi encontrada a primeira vírgula
            return false;
        }

        // Procurar pela segunda vírgula
        index = currentSearch.indexOf(",", index + 1);
        if (index != -1) {
            count++;
            // Verificar se há algum caractere entre a primeira e a segunda vírgula
            if (index - (currentSearch.indexOf(",") + 1) > 0) {
                // Existe um caractere entre a primeira e a segunda vírgula
            } else {
                // Não há nenhum caractere entre a primeira e a segunda vírgula
                return false;
            }
        } else {
            // Não foi encontrada a segunda vírgula
            return false;
        }

        // Verificar se há algum caractere depois da segunda vírgula
        if (currentSearch.length() - (currentSearch.lastIndexOf(",") + 1) > 0) {
            // Existe um caractere depois da segunda vírgula
        } else {
            // Não há nenhum caractere depois da segunda vírgula
            return false;
        }

        // Se foram encontradas pelo menos duas vírgulas com a estrutura correta, retornar true
        return count >= 2;
    }

    public void atualizarAtracoesProximas() {
        if (hasValidStructure()) {
            Double[] latlong = getLatLong(currentSearch);
            //Organizar listas, vê se listas do managedBean são estaticas, se sim organizar elas de acordo com essa latLong
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Endereço incorreto",
                            "O endereço deve estar no formato \'Rua,Cidade/Estado,País\'"));
        }
    }

    public static void main(String[] args) throws JSONException, MalformedURLException, URISyntaxException, UnsupportedEncodingException {
//        System.out.println(new acessoAPIGeocoding().getLatLong("https://api.opencagedata.com/geocode/v1/json?key=6a6de70f376e4015b6f857e263e4a383&q=Rua Cafelândia, Carapicuíba, Brasil&limit=1")[0]);
        System.out.println(new acessoAPIGeocoding().getLatLong("Rua Cafelândia, São Paulo, Brasil")[1]);//        URL ur = new URL("https://api.opencagedata.com/geocode/v1/json?key=6a6de70f376e4015b6f857e263e4a383&q=Rua Vale do Siriji");;
    }
}
