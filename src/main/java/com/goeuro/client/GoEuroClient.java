package com.goeuro.client;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

public class GoEuroClient {

    private static String connectUrl = "http://api.goeuro.com/api/v2/position/suggest/en/";

    public static List<HashMap> getInfo(String requestString) throws GoEuroServerException {

        Client client = ClientBuilder.newBuilder().register(GoEuroListBodyReader.class).build();
        System.out.println("Connecting to endpoint " + connectUrl);
        System.out.println("Request: " + requestString);
        WebTarget webTarget = client.target(connectUrl + requestString);
        try {
            Response response = webTarget.request().get();
            if (response.getStatus() != 200) {
                System.out.println("Error connecting to endpoint");
                throw new GoEuroServerException();
            }
            System.out.println("Data received ftom the endpoint");
            List responseProcessed = response.readEntity(List.class);
            System.out.println("Data parsed, records number: " + responseProcessed.size());
            return responseProcessed;
        } catch (ProcessingException ex) {
            System.out.println("Error while retrieving data from endpoint");
            return null;
        }

    }

}
