package com.example.user.webservices;

import com.example.user.entities.MenuRestaurant;
import com.example.user.entities.Restaurant;
import com.example.user.entities.Spectacle;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
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
    private static String URL = "http://192.168.0.12:8080/PuyDuFouWS/PuyDuFouWS?WSDL";
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

    public static Restaurant invokeRestaurant(int id, String webMethName) {
        Restaurant resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo PI = new PropertyInfo();
        // Set Name
        PI.setName("ID");
        // Set Value
        PI.setValue(id);
        // Set dataType
        PI.setType(int.class);
        // Add the property to request object
        request.addProperty(PI);
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
            //SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            SoapObject response = (SoapObject) envelope.bodyIn;
            SoapObject object = (SoapObject) response.getProperty(0);
            resTxt = new Restaurant(Integer.parseInt(object.getProperty("id").toString()),object.getProperty("nom").toString(),object.getProperty("description").toString(),Double.parseDouble(object.getProperty("latitude").toString()),Double.parseDouble(object.getProperty("longitude").toString()));
        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            //resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return resTxt;
    }

    public static void invokeEnregistreNoteRestaurant(int id,int note, String webMethName) {
        Spectacle resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo PI = new PropertyInfo();
        // Set Name
        PI.setName("id");
        // Set Value
        PI.setValue(id);
        // Set dataType
        PI.setType(int.class);
        // Add the property to request object
        request.addProperty(PI);
        PropertyInfo PI2 = new PropertyInfo();
        PI2.setName("note");
        PI2.setValue(note);
        PI2.setType(int.class);
        request.addProperty(PI2);
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
        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            //resTxt = "Error occured";
        }
        //Return resTxt to calling object
        //return resTxt;
    }

    public static float invokeGetNoteRestaurant(int id, String webMethName) {
        float resTxt = 0;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo PI = new PropertyInfo();
        // Set Name
        PI.setName("id");
        // Set Value
        PI.setValue(id);
        // Set dataType
        PI.setType(int.class);
        // Add the property to request object
        request.addProperty(PI);
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
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = Float.parseFloat(response.toString());
        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            //resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return resTxt;
    }

    public static List<MenuRestaurant> invokeGetMenuRestaurant(int id, String webMethName) {
        List<MenuRestaurant> resTxt = new ArrayList<>();
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo PI = new PropertyInfo();
        // Set Name
        PI.setName("id");
        // Set Value
        PI.setValue(id);
        // Set dataType
        PI.setType(int.class);
        // Add the property to request object
        request.addProperty(PI);
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
            SoapObject response = (SoapObject) envelope.bodyIn;
            System.out.println("Liste menu : " + response.toString());
            for(int i = 0;i<response.getPropertyCount();i++){
                SoapObject object = (SoapObject)response.getProperty(i);
                MenuRestaurant menu = new MenuRestaurant(Integer.parseInt(object.getProperty("id").toString()),object.getProperty("titre").toString(),object.getProperty("description").toString(),Integer.parseInt(object.getProperty("idRestaurant").toString()),Float.parseFloat(object.getProperty("prix").toString()));
                resTxt.add(menu);
                System.out.println(menu.getTitre());
            }
        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            //resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return resTxt;
    }
}
