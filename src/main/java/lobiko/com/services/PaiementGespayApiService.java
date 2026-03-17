package lobiko.com.services;

import lobiko.com.entities.MobileMoneyObject;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
  Cette classe permet de faire le paiement par mobil money
 */

public class PaiementGespayApiService {

    Client client = ClientBuilder.newClient();
    public void PaiementGespayApiService(MobileMoneyObject mobileMoneyObject){
        Entity payload = Entity.json("{  \"amount\": 2,  \"currency\": \"USD\",  \"provider\": \"mpesa_cd\",  \"country_code\": \"CD\",  \"phone_number\": \"243810000000\",  \"order_id\": \"Facture_082\",  \"callback_url\": \"https://site.com\"}");
        Response response = client.target("https://api.genesyspay.solutions/v1/payment/mobile/")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer {GPPRIV-dc45ce55cbee3606068ed54a4be3bb71c950ac70}")
                .post(payload);

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));
    }

}
