package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.managers.base.SQLBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            this.mysqlConnection.setAutoCommit(false);

            if (this.isConnection(this.mysqlConnection))
                return this.mysqlConnection;
        } catch (Exception error) {
            this.loggerUtils.info(this.corePlugin, "[MySQL] " + error.getMessage());
        }

        return null;
    }

    public void executeUpdate(String query) {
        try {
            if (this.mysqlConnection == null || (this.mysqlConnection != null && this.mysqlConnection.isClosed()))
                configManager.setConnection();

            super.executeUpdate(this.mysqlConnection, query);
        } catch (SQLException error) {
            this.loggerUtils.info(this.corePlugin, "[executeUpdate] " + error.getMessage());
        }
    }

    public <R> R executeResultSet(String query, Function<ResultSet, R> func) {
        try {
            if (this.mysqlConnection == null || (this.mysqlConnection != null && this.mysqlConnection.isClosed()))
                configManager.setConnection();

            return super.executeResultSet(this.mysqlConnection, query, func);
        } catch (SQLException error) {
            this.loggerUtils.info(this.corePlugin, "[executeResultSet] " + error.getMessage());
        }

        return null;
    }

    public void commit() {
        try {
            this.mysqlConnection.commit();
        } catch (SQLException error) {
            this.loggerUtils.info(this.corePlugin, "[MySQL] " + error.getMessage());
        }
    }
}
