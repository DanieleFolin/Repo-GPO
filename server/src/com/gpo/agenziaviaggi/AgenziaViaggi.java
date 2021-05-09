package com.gpo.agenziaviaggi;

import java.sql.SQLException;

public class AgenziaViaggi {

    public static void main(String[] args) {

        int n = 20;
        try {
            DatabaseConnector dbC = new DatabaseConnector();
            for(int i = 0; i < n; i++){
                ServerThread st = new ServerThread(1300 + i, dbC);
                st.start();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
