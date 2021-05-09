package com.example.agenziaviaggiapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
    private String ActionType;
    private String ID;
    private String Username;
    private String Password;
    private Object Risorsa = null;

    public ClientThread(String hostname, int port){
        super();
        this.hostname = hostname;
        this.port = port;
    }

    public void aggiungiParametri(String actionType, int id){
        this.ActionType = actionType;
        this.ID = Integer.toString(id);
    }

    public void aggiungiParametri(String username, String password){
        this.ActionType = "VerificaUtente";
        this.Username = username;
        this.Password = password;
    }

    public boolean isReady(){
        if(Risorsa == null){
            return true;
        }else{
            return false;
        }
    }

    //IsReady

    @Override
    public void run(){
        try (Socket socket = new Socket(hostname, port)){

            //Scrittura
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            List<Message> messages = new ArrayList<>();
            messages.add(new Message(ActionType));    //Object Type
            if(ActionType.equals("VerificaUtente")){
                messages.add(new Message(Username));
                messages.add(new Message(Password));
            }else{
                messages.add(new Message(ID));
            }

            objectOutputStream.writeObject(messages);

            while(isReady()){
                //Lettura
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                Risorsa = objectInputStream.readObject();
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException e){
            System.out.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}