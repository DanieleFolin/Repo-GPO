package com.example.agenziaviaggiapp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {

    private final String hostname;
    private final int port;
    private int number;
    private int initNumber;

    public ClientThread(String hostname, int port){
        super();
        this.hostname = hostname;
        this.port = port;
        number = 0;
    }

    public void setNumber(int numberToSend) {
        this.number = numberToSend;
        this.initNumber = numberToSend;
    }

    public int getNumber(){ return number; }

    public boolean isReady(){
        if(number != initNumber){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void run(){
        try (Socket socket = new Socket(hostname, port)){
            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // make a bunch of messages to send.
            List<Message> messages = new ArrayList<>();
            messages.add(new Message("Hello from the other side!"));    //Database o Server
            messages.add(new Message("How are you doing?"));    //Object Type
            messages.add(new Message("What time is it?"));  //Filtro1
            messages.add(new Message("Hi hi hi hi."));  //Filtro2

            objectOutputStream.writeObject(messages);

            while(isReady()){
                number = socket.getInputStream().read();    //Legge la risorsa ricevuta
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}