package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.managers.base.SQLBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.function.Function;

public class MySQLManager extends SQLBase implements ISQLManager {
    private Connection mysqlConnection;

    @Override
    public void createTables() {

    }

    public Connection getConnection(String host, int port, String database, String user, String password) {
        try {
            this.mysqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8",
                    user, password);

            if (this.isConnection(this.mysqlConnection))
                return this.mysqlConnection;
        } catch (Exception error) {
            loggerUtils.info(corePlugin, "[MySQL] " + error.getMessage());
        }

        return null;
    }

    public void executeUpdate(String query) {
        super.executeUpdate(this.mysqlConnection, query);
    }

    public <R> R executeResultSet(String query, Function<ResultSet, R> func) {
        return super.executeResultSet(this.mysqlConnection, query, func);
    }
}
