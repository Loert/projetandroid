package com.example.user.webservices;

import com.example.user.entities.HoraireSpectacle;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 17/06/2015.
 */
public class WebServicePlanning {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://puydufou.ws.com/";
    //Webservice URL - WSDL File location
    private static String URL = "http://192.168.0.12:8080/PuyDuFouWS/PuyDuFouWS?WSDL";
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://puydufou.ws.com/";

    public static List<HoraireSpectacle> invokePlannningOptimiseHoraireSpectacle(String webMethName){
        List<HoraireSpectacle> ListeHoraire = new ArrayList<HoraireSpectacle>();
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        System.out.println("Recupération du planning optimise");
        try {
            androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
            SoapObject response = (SoapObject) envelope.bodyIn;
            System.out.println(response.toString());
            for(int i= 0; i< response.getPropertyCount(); i++){
                SoapObject object = (SoapObject)response.getProperty(i);
                HoraireSpectacle Horaire = new HoraireSpectacle(Integer.parseInt(object.getProperty("id").toString()),object.getProperty("heureDebut").toString());
                Horaire.setIdSpectacle(Integer.parseInt(object.getProperty("spectaclesId").toString()));
                System.out.println(Horaire.getHoraire());
                ListeHoraire.add(Horaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListeHoraire;
    }

    public static List<HoraireSpectacle> invokeSpectacleAVenir(String webMethName){
        List<HoraireSpectacle> ListeHoraire = new ArrayList<HoraireSpectacle>();
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
            SoapObject response = (SoapObject) envelope.bodyIn;
            System.out.println(response.toString());
            for(int i= 0; i< response.getPropertyCount(); i++){
                SoapObject object = (SoapObject)response.getProperty(i);
                HoraireSpectacle Horaire = new HoraireSpectacle(Integer.parseInt(object.getProperty("id").toString()),object.getProperty("heureDebut").toString());
                Horaire.setIdSpectacle(Integer.parseInt(object.getProperty("spectaclesId").toString()));
                System.out.println(Horaire.getHoraire());
                ListeHoraire.add(Horaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListeHoraire;
    }
}
