package br.crud.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:h2:mem:embarcacoes_db;DB_CLOSE_DELAY=-1;"
            + "INIT=CREATE TABLE IF NOT EXISTS embarcacoes ("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "nome VARCHAR(255),"
            + "tipo VARCHAR(100),"
            + "ativa BOOLEAN"
            + ")";

    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}