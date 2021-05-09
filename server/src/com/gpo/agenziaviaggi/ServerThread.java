package com.gpo.agenziaviaggi;

import java.io.*;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {

    private final int port;
    private DatabaseConnector dbConnector;

    public ServerThread(int port, DatabaseConnector dbC){
        super();
        this.port = port;
        this.dbConnector = dbC;
    }

    @Override
    public void run(){
        try (ServerSocket ss = new ServerSocket(port)){
            Socket clientSocket = ss.accept();

            //Lettura
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            List<Message> parametriList = (List<Message>) objectInputStream.readObject();

            //Metodo lettura database con parametri da parametriList
            Object risorsaRichiesta;
            switch(parametriList.get(0).getText()){
                case "VerificaUtente":
                    risorsaRichiesta = dbConnector.verificaUtente(parametriList.get(1).getText(), parametriList.get(2).getText());
                    break;
                case "OttieniVoliViaggio":
                    risorsaRichiesta = dbConnector.ottieniVoli(Integer.parseInt(parametriList.get(1).getText()));
                    break;
                case "OttieniUtente":
                    risorsaRichiesta = dbConnector.ottieniUtente(Integer.parseInt(parametriList.get(1).getText()));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + parametriList.get(0).getText());
            }

            //Scrittura
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(risorsaRichiesta);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException e){
            System.out.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}