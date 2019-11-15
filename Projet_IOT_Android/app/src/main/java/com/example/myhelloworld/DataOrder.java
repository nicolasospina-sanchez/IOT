package com.example.myhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class DataOrder extends AppCompatActivity {
    String value1,value2,value3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent(); // gets the previously created intent
        final String ip = myIntent.getStringExtra("id");
        final int port = Integer.parseInt(myIntent.getStringExtra("port"));

        setContentView(R.layout.activity_data_order);
        Spinner spinner1 = (Spinner) findViewById (R.id.firstletter);
        Spinner spinner2 = (Spinner) findViewById (R.id.secondletter);
        Spinner spinner3 = (Spinner) findViewById (R.id.thirdletter);
        Button boutonConfirmer = (Button) findViewById (R.id.confirmer);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.defaultValue, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                permuteValues(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                permuteValues(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                permuteValues(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        boutonConfirmer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (value1 != "Choisir..." && value2 != "Choisir..." && value3 != "Choisir..."){
                    String message = "";
                    if (value1.charAt(0) == 'T')
                        message = "T";
                    else if (value1.charAt(0) == 'L')
                        message = "L";
                    else if (value1.charAt(0) == 'H')
                        message = "H";
                    if (value2.charAt(0) == 'T')
                        message = message + "T";
                    else if (value2.charAt(0) == 'L')
                        message = message + "L";
                    else if (value2.charAt(0) == 'H')
                        message = message + "H";
                    if (value3.charAt(0) == 'T')
                        message = message + "T";
                    else if (value3.charAt(0) == 'L')
                        message = message + "L";
                    else if (value3.charAt(0) == 'H')
                        message = message + "H";


                    final String finalMessage = message;
                    (new Thread() {
                        public void run() {
                            try{
                                DatagramSocket UDPSocket = new DatagramSocket();
                                InetAddress address = InetAddress.getByName("192.168.1.14");
                                byte[] data = finalMessage.getBytes();

                                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                                UDPSocket.send(packet);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    /*
                    //ATTENDRE PACKET
                    while(run){
                        byte[] recu = new byte[8192];
                        DatagramPacket packetRecu = new DatagramPacket(recu, recu.length);
                        socket.setSoTimeout(timout);
                        socket.receive(packetRecu);
                        final String messageRecu = new String(recu, 0, packetRecu.getLength());
                        msgRecu = messageRecu;
                        recuTV.setText(msgRecu);

                        socket.close();
                        run = false;
                    }
                    */
                } else {
                    //message d'erreur de formulaire
                }
            }
        });
    }

    public void permuteValues(int index){
        Spinner spinner1 = (Spinner) findViewById (R.id.firstletter);
        Spinner spinner2 = (Spinner) findViewById (R.id.secondletter);
        Spinner spinner3 = (Spinner) findViewById (R.id.thirdletter);
        String valeur1 = spinner1.getSelectedItem().toString();
        String valeur2 = spinner2.getSelectedItem().toString();
        String valeur3 = spinner3.getSelectedItem().toString();

        switch (index){
            case 0:
                break;
            case 1:
                if (valeur1 == valeur2){
                    spinner2.setSelection(0);
                }
                if (valeur1 == valeur3){
                    spinner3.setSelection(0);
                }
            case 2:
                if (valeur2 == valeur1){
                    spinner1.setSelection(0);
                }
                if (valeur2 == valeur3){
                    spinner3.setSelection(0);
                }
            case 3:
                if (valeur3 == valeur1){
                    spinner1.setSelection(0);
                }
                if (valeur3 == valeur2){
                    spinner2.setSelection(0);
                }
        }
        value1 = spinner1.getSelectedItem().toString();
        value2 = spinner2.getSelectedItem().toString();
        value3 = spinner3.getSelectedItem().toString();
    }

  /*  public void send(final int joueur){

        (new Thread() {
            public void run() {
                try {
                    String Joueur = (joueur == 1) ? "(1)" : "(2)";
                    int joueur_length = Joueur.length();
                    byte[] data = Joueur.getBytes();
                    DatagramPacket packet = new DatagramPacket(data, joueur_length, local, port);
                    s.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }*/
}
