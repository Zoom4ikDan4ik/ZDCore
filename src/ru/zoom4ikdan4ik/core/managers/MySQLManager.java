package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.IMySQLManager;

import java.sql.*;
import java.util.function.Function;

public class MySQLManager implements IMySQLManager {

    private Connection connection;

    public Connection getConnection(String host, int port, String database, String user, String password) {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8",
                    user, password);
            checkConnection();
        } catch (Exception error) {
            loggerUtils.info(corePlugin, "[getConnection] " + error.getMessage());
        }

        return connection;
    }

    @Override
    public void createTables() {
    }

    private boolean checkConnection() throws SQLException {
        if (connection == null) {
            loggerUtils.info(corePlugin, "Could not connect to the database");

            throw new SQLException();
        } else if (!connection.isValid(5)) {
            loggerUtils.info(corePlugin, "Could not connect to the database.");

            throw new SQLException();
        } else
            return true;
    }

    public void executeUpdateMySQL(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException error) {
            loggerUtils.info(corePlugin, "[executeUpdate] " + error.getMessage());
        } catch (NullPointerException error) {
            loggerUtils.info(corePlugin, error.getMessage());
        }
    }

    public <R> R runSqlWith(String query, Function<ResultSet, R> func) {
        try (Statement statement = connection.createStatement()) {
            return func.apply(statement.executeQuery(query));
        } catch (SQLException error) {
            loggerUtils.info(corePlugin, "[getResultSet] " + error.getMessage());
        } catch (NullPointerException error) {
            loggerUtils.info(corePlugin, error.getMessage());
        }

        return null;
    }
}
