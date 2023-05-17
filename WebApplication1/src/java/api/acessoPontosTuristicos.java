package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import modelo.BarERestaurante;
import modelo.CentroDeCompras;
import modelo.FeiraLivre;
import modelo.Hotel;
import modelo.MercadoPublico;
import modelo.Museu;
import modelo.Ponte;
import modelo.Teatro;
import modelo.touristSpot;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author euluc
 */
public class acessoPontosTuristicos {

    
    //TO DO - A comparação != null ta servindo de nada, trocar ela
    //Usar o mesmo mecanismo da conversão de endereço para latitude e longitude para descobrir a latitude e longitude dos baresERestaurantes ou apagar eles da lista
    //Testar em todos os "gets" se a latitude ou longitude ta retornando nulo
    String currentAPIURL;
    URL url;
    HttpURLConnection conn;
    int responseCode;
    BufferedReader reader;
    StringBuilder response;

    public acessoPontosTuristicos() {

    }

    public void connectURL() {
        try {
            url = new URL(currentAPIURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSON() {
        JSONObject jsonObject = null;
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                jsonObject = new JSONObject(response.toString()).getJSONObject("result");
            } else {
                System.out.println("API Request Failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonObject;
    }

    public JSONArray JSONToList(JSONObject currentJSON, String key) throws JSONException {
        JSONArray currentJSONArray = null;
        currentJSONArray = currentJSON.getJSONArray(key);
        return currentJSONArray;
    }
    
    //Usado no getBaresERestaurantes
    public Double[] getLatELongBaseadoNoEndereco(String endereco) {
        Double[] currentLatLong = new Double[2];
         //Atribuir retorno da chamada da função do que converte o endereço em lat e long no currentLatLong
        return currentLatLong;
    }

    public List<Museu> getMuseus() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=97ab18da-f940-43b1-b0d4-a9e93e90bed5";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayMuseus = null;
        List<Museu> museus = new ArrayList<>();
        if (currentResponse != null) {
            arrayMuseus = JSONToList(currentResponse, "records");
        }
        if (arrayMuseus != null) {
            for (int i = 0; i < arrayMuseus.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentMuseu = arrayMuseus.getJSONObject(i);

                if(currentMuseu.getString("latitude").equals("null") || currentMuseu.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto Museu
                int _id = currentMuseu.getInt("_id");
                String nome = currentMuseu.getString("nome");
                String descricao = currentMuseu.getString("descricao");
                String bairro = currentMuseu.getString("bairro");
                String logradouro = currentMuseu.getString("logradouro");
                Double latitude = Double.parseDouble(currentMuseu.getString("latitude"));
                Double longitude = Double.parseDouble(currentMuseu.getString("longitude"));
                String telefone = currentMuseu.getString("telefone");
                String site = currentMuseu.getString("site");

                Museu museu = new Museu(_id, nome, descricao, bairro, logradouro, latitude, longitude, telefone, site);

                // Adicionar o objeto à lista
                museus.add(museu);
            }
        }
        return museus;
    }

    public List<Teatro> getTeatros() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=16d45f07-1fab-4b8c-95d1-dbf555b6f913";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayTeatro = null;
        List<Teatro> teatros = new ArrayList<>();
        if (currentResponse != null) {
            arrayTeatro = JSONToList(currentResponse, "records");
        }
        if (arrayTeatro != null) {
            for (int i = 0; i < arrayTeatro.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentTeatro = arrayTeatro.getJSONObject(i);
                if(currentTeatro.getString("latitude").equals("null") || currentTeatro.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto Teatro
                int _id = currentTeatro.getInt("_id");
                String nome = currentTeatro.getString("nome");
                String descricao = currentTeatro.getString("descricao");
                String bairro = currentTeatro.getString("bairro");
                String logradouro = currentTeatro.getString("logradouro");
                String telefone = currentTeatro.getString("Telefone");
               Double latitude = Double.parseDouble(currentTeatro.getString("latitude"));
                Double longitude = Double.parseDouble(currentTeatro.getString("longitude"));
                Teatro teatro = new Teatro(_id, nome, descricao, bairro, logradouro, telefone, latitude, longitude);

                // Adicionar o objeto à lista de Teatro
                teatros.add(teatro);
            }
        }

        return teatros;
    }

    public List<MercadoPublico> getMercadosPublicos() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=40d97dcb-4a14-4365-bced-8555998a498d";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayMercadoPublico = null;
        List<MercadoPublico> mercadosPublicos = new ArrayList<>();
        if (currentResponse != null) {
            arrayMercadoPublico = JSONToList(currentResponse, "records");
        }
        if (arrayMercadoPublico != null) {
            for (int i = 0; i < arrayMercadoPublico.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentMercadoPublico = arrayMercadoPublico.getJSONObject(i);
                
                if(currentMercadoPublico.getString("latitude").equals("null") || currentMercadoPublico.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto MercadoPublico
                int _id = currentMercadoPublico.getInt("_id");
                String nome = currentMercadoPublico.getString("nome");
                String descricao = currentMercadoPublico.getString("descricao");
                String bairro = currentMercadoPublico.getString("bairro");
                Double latitude = Double.parseDouble(currentMercadoPublico.getString("latitude"));
                Double longitude = Double.parseDouble(currentMercadoPublico.getString("longitude"));
                MercadoPublico mercadoPublico = new MercadoPublico(_id, nome, descricao, bairro, latitude, longitude);

                // Adicionar o objeto à lista de MercadoPublico
                mercadosPublicos.add(mercadoPublico);
            }
        }
        return mercadosPublicos;
    }

    public List<FeiraLivre> getFeirasLivres() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=dc6b3d07-3124-453d-b11e-72364cced7aa";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayFeiraLivre = null;
        List<FeiraLivre> feirasLivres = new ArrayList<>();
        if (currentResponse != null) {
            arrayFeiraLivre = JSONToList(currentResponse, "records");
        }
        if (arrayFeiraLivre != null) {
            for (int i = 0; i < arrayFeiraLivre.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentFeiraLivre = arrayFeiraLivre.getJSONObject(i);

                if(currentFeiraLivre.getString("Latitude").equals("null") || currentFeiraLivre.getString("Longitude").equals("null")) {
                    continue;
                }
                
                // Extrair os valores do registro e criar um objeto FeiraLivre
                int _id = currentFeiraLivre.getInt("_id");
                String nome = currentFeiraLivre.getString("Nome");
                String localizacao = currentFeiraLivre.getString("Localização");
                String dias = currentFeiraLivre.getString("Dias");
                String horario = currentFeiraLivre.getString("Horário");
                String observacao = currentFeiraLivre.getString("Observação");
                Double latitude = Double.parseDouble(currentFeiraLivre.getString("Latitude"));
                Double longitude = Double.parseDouble(currentFeiraLivre.getString("Longitude"));
                
                FeiraLivre feiraLivre = new FeiraLivre(_id, nome, localizacao, dias, horario, observacao, latitude, longitude);

                // Adicionar o objeto à lista de FeiraLivre
                feirasLivres.add(feiraLivre);
            }
        }
        return feirasLivres;
    }

    public List<Ponte> getPontes() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=61fcd098-058b-4bb1-9918-f46cfbac3261";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayPontes = null;
        List<Ponte> pontes = new ArrayList<>();
        if (currentResponse != null) {
            arrayPontes = JSONToList(currentResponse, "records");
        }
        if (arrayPontes != null) {
            for (int i = 0; i < arrayPontes.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentPonte = arrayPontes.getJSONObject(i);

                if(currentPonte.getString("latitude").equals("null") || currentPonte.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto Ponte
                int _id = currentPonte.getInt("_id");
                String nome = currentPonte.getString("Nome");
                String descricao = currentPonte.getString("descricao");
                String bairro = currentPonte.getString("bairro");
                Double latitude = Double.parseDouble(currentPonte.getString("latitude"));
                Double longitude = Double.parseDouble(currentPonte.getString("longitude"));

                Ponte ponte = new Ponte(_id, nome, descricao, bairro, latitude, longitude);

                // Adicionar o objeto à lista
                pontes.add(ponte);
            }
        }
        return pontes;

    }

    public List<CentroDeCompras> getCentrosDeCompras() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=81f406de-8468-4bb9-b038-0956d6684acd";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayCentroDeCompras = null;
        List<CentroDeCompras> centrosDeCompras = new ArrayList<>();
        if (currentResponse != null) {
            arrayCentroDeCompras = JSONToList(currentResponse, "records");
        }
        if (arrayCentroDeCompras != null) {
            for (int i = 0; i < arrayCentroDeCompras.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentCentroDeCompras = arrayCentroDeCompras.getJSONObject(i);

                if(currentCentroDeCompras.getString("latitude").equals("null") || currentCentroDeCompras.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto CentroDeCompras
                int _id = currentCentroDeCompras.getInt("_id");
                String nome = currentCentroDeCompras.getString("nome");
                String descricao = currentCentroDeCompras.getString("descricao");
                String bairro = currentCentroDeCompras.getString("bairro");
                String logradouro = currentCentroDeCompras.getString("logradouro");
               Double latitude = Double.parseDouble(currentCentroDeCompras.getString("latitude"));
                Double longitude = Double.parseDouble(currentCentroDeCompras.getString("longitude"));
                String telefone = currentCentroDeCompras.getString("telefone");
                String site = currentCentroDeCompras.getString("site");
                String funcionamento = currentCentroDeCompras.getString("funcionamento");
                String funcionamentoDomingo = currentCentroDeCompras.getString("funcionamentoDomingo");

                CentroDeCompras centroDeCompras = new CentroDeCompras(_id, nome, descricao, bairro, logradouro, latitude, longitude, telefone, site, funcionamento, funcionamentoDomingo);

                // Adicionar o objeto à lista
                centrosDeCompras.add(centroDeCompras);
            }
        }
        return centrosDeCompras;

    }

    public List<Hotel> getHoteis() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=0d8fb090-2863-4d51-9b21-baae4bae5a11";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayHoteis = null;
        List<Hotel> hoteis = new ArrayList<>();
        if (currentResponse != null) {
            arrayHoteis = JSONToList(currentResponse, "records");
        }
        if (arrayHoteis != null) {
            for (int i = 0; i < arrayHoteis.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentHotel = arrayHoteis.getJSONObject(i);
                if(currentHotel.getString("latitude").equals("null") || currentHotel.getString("longitude").equals("null")) {
                    continue;
                }
                // Extrair os valores do registro e criar um objeto Hoteis
                int _id = currentHotel.getInt("_id");
                String nome = currentHotel.getString("nome");
                String endereco = currentHotel.getString("endereco");
                String telefone = currentHotel.getString("telefone");
                String site = currentHotel.getString("site");
                Double latitude = Double.parseDouble(currentHotel.getString("latitude"));
                Double longitude = Double.parseDouble(currentHotel.getString("longitude"));
                    
                Hotel hotel = new Hotel(_id, nome, endereco, telefone, site, latitude, longitude);

                // Adicionar o objeto à lista
                hoteis.add(hotel);
            }
        }
        return hoteis;

    }

    public List<BarERestaurante> getBaresERestaurantes() throws JSONException {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=d85bf4e3-637e-4e1b-9b03-970dca4403c7";
        
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayBaresERestaurantes = null;
        List<BarERestaurante> baresERestaurantes = new ArrayList<>();
        if (currentResponse != null) {
            arrayBaresERestaurantes = JSONToList(currentResponse, "records");
        }
        if (arrayBaresERestaurantes != null) {
            for (int i = 0; i < arrayBaresERestaurantes.length(); i++) {
                // Obter o objeto JSON correspondente ao registro atual
                JSONObject currentBarERestaurante = arrayBaresERestaurantes.getJSONObject(i);

//                if(currentBarERestaurante.getString("latitude").equals("null") || currentBarERestaurante.getString("longitude").equals("null")) {
//                    continue;
//                }
                
                // Extrair os valores do registro e criar um objeto BaresERestaurantes
                int _id = currentBarERestaurante.getInt("_id");
                String nome = currentBarERestaurante.getString("nome");
                String endereco = currentBarERestaurante.getString("endereco");
                String telefone = currentBarERestaurante.getString("telefone");
                String especialidade = currentBarERestaurante.getString("especialidade");
                String site = currentBarERestaurante.getString("site");
                String email = currentBarERestaurante.getString("email");
                
                //Chamar a função getLatELongBaseadoNoEndereco passando o endereco como parâmetro e criar dois doubles latitude e longitude e passar eles na chamada do construtor

                BarERestaurante barERestaurante = new BarERestaurante(_id, nome, endereco, telefone, especialidade, site, email);

                // Adicionar o objeto à lista
                baresERestaurantes.add(barERestaurante);
            }
        }
        return baresERestaurantes;

    }

    public List<touristSpot> getAllPontosTuristicos() throws JSONException {
        List<touristSpot> pontosTuristicos = new ArrayList<>();
        pontosTuristicos.addAll(getMuseus());
        pontosTuristicos.addAll(getTeatros());
        pontosTuristicos.addAll(getMercadosPublicos());
        pontosTuristicos.addAll(getFeirasLivres());
        pontosTuristicos.addAll(getPontes());
        pontosTuristicos.addAll(getCentrosDeCompras());
        pontosTuristicos.addAll(getHoteis());
        pontosTuristicos.addAll(getBaresERestaurantes());

        return pontosTuristicos;
    }

    public static void main(String[] args) throws JSONException {
        acessoPontosTuristicos acesso = new acessoPontosTuristicos();
        for (touristSpot ts : acesso.getAllPontosTuristicos()) {
            System.out.println(ts.getNome());
        }

    }

}
