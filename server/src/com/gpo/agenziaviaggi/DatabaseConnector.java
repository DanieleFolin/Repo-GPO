package com.gpo.agenziaviaggi;

import com.gpo.agenziaviaggi.classes.*;

import java.sql.*;

import java.util.ArrayList;

/**
 * Questa classe serve a stabilire la connessione con il database.
 * Bisogna sempre richiamare il costruttore altrimenti si potrebbe incorrere in errori.
 */
public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/agenzia_viaggi";
    private static final String user = "root";
    private static final String password = "";

    /**
     * Costruttore del Database
     */
    public DatabaseConnector() throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement stmt = connection.createStatement();

        ResultSet resultSet = stmt.executeQuery("SHOW tables;");

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
    }

    /**
     * Serve a creare la tabella "Utente" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createUtenteTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "CREATE TABLE Utente (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(40) not null," +
                        "Cognome varchar(40) not null," +
                        "Username varchar(40) not null UNIQUE," +
                        "Password varchar(40) not null" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    /**
     * Serve a creare la tabella "Aeroporto" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createAeroportoTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "CREATE TABLE Aeroporto (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(100) not null," +
                        "Luogo varchar(150) not null" +
                        ");";

        statement.executeQuery(createTable);
        statement.close();
    }

    /**
     * Serve a creare la tabella "CompagniaAerea" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createCompagniaAereaTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "CREATE TABLE Compagnia_Aerea (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varchar(60) not null," +
                        "Nazione varchar(60)," +
                        "TipoCompagnia int not null" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    /**
     * Serve a creare la tabella "ListaVoli" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createListaVoliTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createTable =
                "CREATE TABLE Lista_Voli (" +
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
                        "FOREIGN KEY (Aereoporto_Arrivo) REFERENCES Aereoporto(ID)" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    /**
     * Serve a creare la tabella "Viaggio" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createViaggioTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createTable =
                "CREATE TABLE Viaggio (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "Nome varcar(50) not null," +
                        "Prezzo float," +
                        "Valutazione float," +
                        "Data_Partenza date not null," +
                        "Data_Ritorno date not null," +
                        "ID_Utente int not null," +
                        "FOREIGN KEY (ID_Utente) REFERENCES Utente(ID)" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    /**
     * Serve a creare la tabella "Volo" nel caso non esista.
     * @param connection connesione creata dal costruttore
     * @throws SQLException errore richiamato se si incorre in un errore durante la creazione della tabella
     */
    private void createVoloTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTable =
                "CREATE TABLE Volo (" +
                        "ID int not null AUTO_INCREMENT PRIMARY KEY," +
                        "N_Posti int not null," +
                        "ID_Viaggio int not null," +
                        "ID_Volo_Lista int not null," +
                        "FOREIGN KEY (Viaggio) REFERENCES Viaggio(ID)," +
                        "FOREIGN KEY (ID_Volo_Lista) REFERENCES Lista_Voli(ID)" +
                        ");";

        statement.executeUpdate(createTable);
        statement.close();
    }

    /**
     * permette di inserire un nuovo utente nel database
     * @param utente l'utente da inserire
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoUtente(Utente utente){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Utente VALUES ('?','?','?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setString(1, utente.getNome());
            insert.setString(2, utente.getCognome());
            insert.setString(3, utente.getUsername());
            insert.setString(4, utente.getPassword());
            insert.executeUpdate();

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * permette di inserire un nuovo aeroporto nel database
     * @param aeroporto l'aeroporto da inserire
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoAereoporto(Aeroporto aeroporto){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Aeroporto VALUES ('?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setString(1, aeroporto.getNome());
            insert.setString(2, aeroporto.getLuogo());
            insert.executeUpdate();

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * permette di inserire una nuova compagnia aerea nel database
     * @param compagniaAerea la compagnia aerea da inserire
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoCompagniaAerea(CompagniaAerea compagniaAerea){
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

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * permette di inserire un nuovo volo nella lista dei voli disponibili nel database
     * @param voloDaLista il volo da inserire
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoVoloInListaVoli(VoloDaLista voloDaLista){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Lista_Voli VALUES ('?', '?', '?', '?', '?', '?', '?',);";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setInt(1, voloDaLista.getCompagniaAerea().getId());
            insert.setInt(2, voloDaLista.getAeroportoDiPartenza().getId());
            insert.setInt(3, voloDaLista.getAeroportoDiArrivo().getId());
            insert.setTimestamp(4, new Timestamp(voloDaLista.getDataOraPartenza().getTime()));
            insert.setTimestamp(5, new Timestamp(voloDaLista.getDataOraArrivo().getTime()));
            insert.setInt(6, voloDaLista.getPostiTotali());
            insert.setInt(7, voloDaLista.getPostiDisponibili());

            insert.executeUpdate();

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * permette di inserire un nuovo viaggio di un utente nel database
     * @param viaggio il viaggio da inserire
     * @param userId l'ID del utente a cui appartiene il viaggio
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoViaggio(Viaggio viaggio, int userId){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Viaggio (Prezzo, Nome, Data_Partenza, Data_Ritorno) VALUES ('?','?','?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setFloat(1, viaggio.getPrezzo());
            insert.setString(2, viaggio.getNome());
            insert.setDate(3, new Date(viaggio.getDataPartenza().getTime()));
            insert.setDate(4, new Date(viaggio.getDataRitorno().getTime()));
            insert.setInt(5, userId);

            insert.executeUpdate();

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * permette di inserire un nuovo volo di un viaggio nel database
     * @param viaggio il viaggio che comprende il volo da inserire
     * @param volo il volo da inserire
     * @return true se l'inserimento è avvenuto con successo, false se invece c'è
     * stato un problema ed è fallito
     */
    public boolean inserisciNuovoVolo(Viaggio viaggio, Volo volo){
        try (Connection connection = DriverManager.getConnection(url, user, password))
        {
            String createUser = "INSERT INTO Volo VALUES ('?','?','?');";
            PreparedStatement insert = connection.prepareStatement(createUser);
            insert.setInt(1, volo.getnPosti());
            insert.setInt(2, viaggio.getId());
            insert.setInt(3, volo.getVoloDaLista().getId());

            insert.executeUpdate();

            return true;

        } catch (SQLException throwables) {
            System.out.println("Ops, error!");
            throwables.printStackTrace();
            return false;
        }
    }

    private Aeroporto ottieniAeroporto(int id) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String getAirport =
                "SELECT * FROM Aeroporto WHERE ID='" + id + "';";

        ResultSet resultSet = statement.executeQuery(getAirport);

        Aeroporto tempAirport = null;

        while (resultSet.next()){
            tempAirport = new Aeroporto(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));

        }

        return tempAirport;
    }

    private CompagniaAerea ottieniCompagniaAerea(int id) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String getAirline =
                "SELECT * FROM CompagniaAerea WHERE ID='" + id + "';";

        ResultSet resultSet = statement.executeQuery(getAirline);

        CompagniaAerea tempAirline= null;

        while (resultSet.next()){
            tempAirline = new CompagniaAerea(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4));
        }

        return tempAirline;
    }

    private VoloDaLista ottieniVoloDaLista(int id) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String getFlight =
                "SELECT * FROM Lista_Voli WHERE ID='" + id + "';";

        ResultSet resultSet = statement.executeQuery(getFlight);

        boolean flightExist = false;
        VoloDaLista tempFLight = null;

        while (resultSet.next()){
            flightExist = true;
            tempFLight = new VoloDaLista(resultSet.getInt(1),
                    new Date(resultSet.getDate(5).getTime()),
                    new Date(resultSet.getDate(6).getTime()),
                    resultSet.getInt(7),
                    resultSet.getInt(8));
        }

        if(flightExist) {
            int tempCompagniaID = resultSet.getInt(2);
            int tempAeroportoPartenzaID = resultSet.getInt(3);
            int tempAeroportoArrivoID = resultSet.getInt(4);

            statement.close();

            tempFLight.setCompagniaAerea(ottieniCompagniaAerea(tempCompagniaID));
            tempFLight.setAeroportoDiPartenza(ottieniAeroporto(tempAeroportoPartenzaID));
            tempFLight.setAeroportoDiArrivo(ottieniAeroporto(tempAeroportoArrivoID));
        }

        return tempFLight;
    }

    public ArrayList<Volo> ottieniVoli(int viaggioId) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String getFlight =
                "SELECT * FROM Volo WHERE ID_Viaggio='" + viaggioId + "';";

        ResultSet resultSet = statement.executeQuery(getFlight);

        ArrayList<Volo> tempFlights = new ArrayList<>();
        ArrayList<Integer> tempIDs = new ArrayList<>();
        boolean flightExist = false;


        while (resultSet.next()){
            flightExist = true;
            tempFlights.add(new Volo(resultSet.getInt(1),
                                    resultSet.getInt(2)));
            tempIDs.add(resultSet.getInt(3));
        }

        statement.close();

        if(flightExist) {
            for (int i = 0; i < tempIDs.size(); i++)
                tempFlights.get(i).setVoloDaLista(ottieniVoloDaLista(tempIDs.get(i)));
        }

        return tempFlights;
    }

    /**
     * verifica se esiste un utente e se la sua password è corretta
     * @param username lo username dell'utente da verificare
     * @param password la password dell'utente da verificare
     * @return -2 se non esistono utenti con quello username, -1 se la password è sbagliata,
     * l'ID dell'utente se lo username e la password sono giusti
     * @throws SQLException errore richiamato se si incorre in un errore durante la verifica del utente
     */
    public int verificaUtente(String username, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String verificaUtente =
                "SELECT ID, Password FROM Utente WHERE Username='" + username + "';";

        ResultSet resultSet = statement.executeQuery(verificaUtente);

        int risultato = -2;

        while (resultSet.next()){
            if(resultSet.getString(2).equals(password)) risultato = resultSet.getInt(1);
            else risultato = -1;
        }

        return risultato;
    }

    public Utente ottieniUtente(int id) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String getUser =
                "SELECT * FROM Utente WHERE ID='" + id + "';";

        ResultSet resultSet = statement.executeQuery(getUser);

        Utente tempUser = null;

        boolean userExist = false;

        while(resultSet.next()) {
            userExist = true;
            tempUser = new Utente(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5));
        }

        int tempUserID = resultSet.getInt(1);

        statement.close();

        if(userExist) tempUser.setViaggi(getViaggiByUserID(tempUserID));

        return tempUser;
    }

    private ArrayList<Viaggio> getViaggiByUserID(int userId) throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        String getUser =
                "SELECT * FROM Viaggio WHERE ID_Utente='" + userId + "';";

        ArrayList<Viaggio> tempViaggi = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(getUser);

        boolean viaggiExist = false;

        while (resultSet.next()){
            viaggiExist = true;
            tempViaggi.add(new Viaggio(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getFloat(3),
                                        resultSet.getFloat(4),
                                        new Date(resultSet.getDate(5).getTime()),
                                        new Date(resultSet.getDate(6).getTime())));
        }

        statement.close();

        if(viaggiExist) return tempViaggi;
        else return null;
    }

}