package com.example.user.webservices;

import com.example.user.entities.Restaurant;
import com.example.user.entities.Spectacle;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 15/06/2015.
 */
public class WebServiceRestaurants {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://puydufou.ws.com/";
    //Webservice URL - WSDL File location
    private static String URL = "http://10.151.128.10:8080/PuyDuFouWS/PuyDuFouWS?WSDL";
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://puydufou.ws.com/";

    public static List<Restaurant> invokeListeRestaurant(String webMethName) {
        List<Restaurant> listeRestaurant = new ArrayList<Restaurant>();
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
            // Get the response
            SoapObject response = (SoapObject) envelope.bodyIn;
            System.out.println(response.toString());
            // Assign it to resTxt variable static variable
            for(int i= 0; i< response.getPropertyCount(); i++){
                SoapObject object = (SoapObject)response.getProperty(i);
                Restaurant restaurant = new Restaurant(Integer.parseInt(object.getProperty("id").toString()),object.getProperty("nom").toString(),object.getProperty("description").toString(),Double.parseDouble(object.getProperty("latitude").toString()),Double.parseDouble(object.getProperty("longitude").toString()));
                listeRestaurant.add(restaurant);
            }
            //listeNoms.add(response.getProperty("nom").toString());

        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            //listeSpectacles.add("Error occured");
        }
        //Return resTxt to calling object
        return listeRestaurant;
    }
}
