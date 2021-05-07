package com.gpo.agenziaviaggi;

import com.gpo.agenziaviaggi.classes.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/agenzia_viaggi";
    private static final String user = "root";
    private static final String password = "";

    public DatabaseConnector() {
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            Statement stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery("show tables;");

            boolean isAllCreated = true;
            boolean[] existingTables = new boolean[6];

            while (resultSet.next()){

                switch (resultSet.getString(1)) {
                    case "Aeroporto" -> existingTables[0] = true;
                    case "Utente" -> existingTables[1] = true;
                    case "Compagnia_Aerea" -> existingTables[2] = true;
                    case "Lista_Voli" -> existingTables[3] = true;
                    case "Viaggio" -> existingTables[4] = true;
                    case "Volo" -> existingTables[5] = true;
                    default -> isAllCreated = false;
                }
            }

            stmt.close();
            if(!isAllCreated){
                if(existingTables[0]) createAeroportoTable(connection);
                if(existingTables[1]) createUtenteTable(connection);
                if(existingTables[2]) createCompagniaAereaTable(connection);
                if(existingTables[4]) createViaggioTable(connection);
                if(existingTables[3]) createListaVoliTable(connection);
                if(existingTables[5]) createVoloTable(connection);
            }

        } catch (SQLException throwables){
            System.out.println("Ops, error!");
            throwables.printStackTrace();
        }
    }

    private void createAeroportoTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "create table Aeroporto (ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(100) not null," +
                        "Luogo varchar(150) not null" +
                        ");";

        statement.executeQuery(createTable); //TODO controllare tipo di execute
        statement.close();
    }

    private void createUtenteTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "create table Utente (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(40) not null," +
                        "Cognome varchar(40) not null" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    private void createCompagniaAereaTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "create table Compagnia_Aerea (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(60) not null" +
                        "Nazione varchar(60)" +
                        "TipoCompagnia int not null" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    private void createListaVoliTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //TODO controllare foreing key e costruzione tabella
        String createTable =
                "create table Lista_Voli (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Compagnia_Aerea int not null," +
                        "Aereoporto_Partenza int not null," +
                        "Aereoporto_Arrivo int not null," +
                        "Data_Ora_Partenza datetime not null," +
                        "Data_Ora_Arrivo datetime not null," +
                        "N_Posti_Totali int not null," +
                        "N_Posti_Disponibili int not null," +
                        "FOREIGN KEY (Compagnia_Aerea) REFERENCES Compagnia_Aerea(ID)," +
                        "FOREIGN KEY (Aereoporto_Partenza) REFERENCES Aereoporto(ID)," +
                        "FOREIGN KEY (Aereoporto_Arrivo) REFERENCES Aereoporto(ID)," +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    private void createViaggioTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createTable =
                "create table Viaggio (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Prezzo float" +
                        "Valutazione float" +
                        "Data_Partenza date not null," +
                        "Data_Ritorno date not null" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    private void createVoloTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        //TODO controllare foreing key e costruzione tabella
        String createTable =
                "create table Volo (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "N_Posti int not null," +
                        "Viaggio int not null," +
                        "ID_Volo_Lista int not null," +
                        "FOREIGN KEY (Viaggio) REFERENCES Viaggio(ID)," +
                        "FOREIGN KEY (ID_Volo_Lista) REFERENCES Lista_Voli(ID)" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    public boolean insertNewUtente(Utente utente){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Utente VALUES ('?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setString(1, utente.getNome());
            insert.setString(2, utente.getCognome());
            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean insertNewAereoporto(Aeroporto aeroporto){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Aeroporto VALUES ('?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setString(1, aeroporto.getNome());
            insert.setString(2, aeroporto.getLuogo());
            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean insertNewCompagniaAerea(CompagniaAerea compagniaAerea){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            PreparedStatement insert;
            if(compagniaAerea.getNazione() == null){
                String createUser = "INSERT INTO Compagnia_Aerea (Nome, TipoCompagnia) VALUES ('?', '?');";
                insert = connection.prepareStatement(createUser);
                insert.setString(1, compagniaAerea.getNome() );
                insert.setInt(2, compagniaAerea.getTipoDiCompagnia());
            } else {
                String createUser = "INSERT INTO Compagnia_Aerea VALUES ('?', '?', '?');";
                insert = connection.prepareStatement(createUser);
                insert.setString(1, compagniaAerea.getNome());
                insert.setString(2, compagniaAerea.getNazione());
                insert.setInt(3, compagniaAerea.getTipoDiCompagnia());
            }
            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    //TODO controllare funzione
    public boolean insertNewVoliInListaVoli(VoloDaLista voloDaLista){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Lista_Voli VALUES ('?', '?', '?', '?', '?', '?', '?',);";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setInt(1, voloDaLista.getCompagniaAerea().getId());
            insert.setInt(2, voloDaLista.getAeroportoDiPartenza().getId());
            insert.setInt(3, voloDaLista.getAeroportoDiArrivo().getId());
            insert.setString(4, voloDaLista.getDataOraPartenza().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            insert.setString(5, voloDaLista.getDataOraArrivo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            insert.setInt(6, voloDaLista.getPostiTotali());
            insert.setInt(7, voloDaLista.getPostiDisponibili());

            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    //TODO controllare funzione
    public boolean insertNewViaggio(Viaggio viaggio){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Viaggio (Prezzo, Data_Partenza, Data_Ritorno) VALUES ('?','?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setFloat(1, viaggio.getPrezzo());
            insert.setString(2, viaggio.getDataPartenza().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            insert.setString(3, viaggio.getDataRitorno().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean insertNewVolo(Viaggio viaggio, Volo volo){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Volo VALUES ('?','?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setInt(1, volo.getnPosti());
            insert.setInt(2, viaggio.getId());
            insert.setInt(3, volo.getVoloDaLista().getId());

            insert.executeUpdate();

            insert.close();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }
}
