/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import geolocalizacao.pontosProximos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.URIUtils;
import modelo.BarERestaurante;
import modelo.Museu;
import modelo.touristSpot;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author ALUNO
 */
//TO DO - Melhorar o valid structure
@ManagedBean
@ViewScoped
public class acessoAPIGeocoding implements Serializable {

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
        System.out.println("Chamou");
        if (hasValidStructure()) {
            Double[] latlong = getLatLong(currentSearch);

            pontosProximos currentPP = new pontosProximos(latlong[0], latlong[1]);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot viewRoot = facesContext.getViewRoot();
            List<touristSpot> elemento = (List<touristSpot>) viewRoot.getViewMap().get("lista");

            //Ordenando
            Collections.sort(elemento, new Comparator<touristSpot>() {

                @Override
                public int compare(touristSpot e1, touristSpot e2) {
                    // Calcula a distância entre o elemento e a latitude/longitude fixa
                    double distanciaE1 = currentPP.calcularDistancia(e1.getLatitude(), e1.getLongitude());
                    double distanciaE2 = currentPP.calcularDistancia(e2.getLatitude(), e2.getLongitude());

                    // Compara as distâncias
                    return Double.compare(distanciaE1, distanciaE2);
                }
            });

            acessoPontosTuristicos meuBean1 = FacesContext.getCurrentInstance().getApplication()
                    .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{pontosTuristicos}", acessoPontosTuristicos.class);
            meuBean1.setAllPontosTuristicos(elemento);
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Endereço incorreto",
                            "O endereço deve estar no formato \'Rua,Cidade/Estado,País\'"));
        }
    }

    public static void main(String[] args) throws JSONException, MalformedURLException, URISyntaxException, UnsupportedEncodingException {
//        System.out.println(new acessoAPIGeocoding().getLatLong("https://api.opencagedata.com/geocode/v1/json?key=6a6de70f376e4015b6f857e263e4a383&q=Rua Cafelândia, Carapicuíba, Brasil&limit=1")[0]);
        System.out.println(new acessoAPIGeocoding().getLatLong("Avenida Professor Luiz Freire 500, Pernambuco, Brasil")[0]);//        URL ur = new URL("https://api.opencagedata.com/geocode/v1/json?key=6a6de70f376e4015b6f857e263e4a383&q=Rua Vale do Siriji");;
    }
}
