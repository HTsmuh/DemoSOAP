package com.example.user.usingsoap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    String TAG = "Response";
    TextView resulttext;
    Object resultString;

    String SOAP_ACTION = "http://medicarehospital.pk//Get_Speciality";
    String METHOD_NAME = "Get_Speciality";
    String NAMESPACE = "http://medicarehospital.pk//";
    String URL = "http://medicarehospital.pk/WebService.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttext= (TextView) findViewById(R.id.result);

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            //Request.addProperty("Id", getCel);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = soapEnvelope.getResponse();
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
        Log.i(TAG, "onPostExecute");
        Toast.makeText(MainActivity.this, "Response" + resultString, Toast.LENGTH_LONG).show();
        //resulttext.setText(resultString.toString());
    }
}