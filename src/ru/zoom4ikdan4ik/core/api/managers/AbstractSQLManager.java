package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.sql.*;
import java.util.function.Function;

public abstract class AbstractSQLManager implements ISQLManager, IBase {
    private final Plugin plugin;
    private Connection mysqlConnection;

    public AbstractSQLManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public final Connection getMySQLConnection() {
        return this.mysqlConnection;
    }

    public final boolean isConnection() throws SQLException {
        if (this.getMySQLConnection() == null) {
            this.loggerUtils.info(this.plugin, "Could not connect to the database");

            throw new SQLException();
        } else if (!this.getMySQLConnection().isValid(5)) {
            this.loggerUtils.info(this.plugin, "Could not connect to the database.");

            throw new SQLException();
        } else
            return true;
    }

    public final void setConnection(DatabaseConnection databaseConnection) {
        try {
            this.mysqlConnection = DriverManager.getConnection(
                    String.format(
                            "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8",
                            databaseConnection.host,
                            databaseConnection.port,
                            databaseConnection.database
                    ),
                    databaseConnection.user,
                    databaseConnection.password
            );

            this.mysqlConnection.setAutoCommit(false);
        } catch (Exception error) {
            this.loggerUtils.info(this.plugin, "[MySQL] " + error.getMessage());
        }
    }

    public final void executeUpdate(String query) {
        try (Statement statement = this.getMySQLConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException error) {
            this.loggerUtils.info(this.plugin, "[executeUpdate] " + error.getMessage());
        } catch (NullPointerException error) {
            this.loggerUtils.info(this.plugin, error.getMessage());
        }
    }

    public final <R> R executeResultSet(String query, Function<ResultSet, R> func) {
        try (Statement statement = this.getMySQLConnection().createStatement()) {
            return func.apply(statement.executeQuery(query));
        } catch (SQLException error) {
            this.loggerUtils.info(this.plugin, "[getResultSet] " + error.getMessage());
        } catch (NullPointerException error) {
            this.loggerUtils.info(this.plugin, error.getMessage());
        }

        return null;
    }

    public final void commit() {
        try {
            if (this.getMySQLConnection() != null && !this.getMySQLConnection().isClosed())
                this.getMySQLConnection().commit();
        } catch (SQLException error) {
            this.loggerUtils.info(this.plugin, "[MySQL] " + error.getMessage());
        }
    }

    public static final class DatabaseConnection {
        private String host;
        private int port;
        private String database;
        private String user;
        private String password;

        public DatabaseConnection() {
            this.host = null;
            this.port = 0;
            this.database = null;
            this.user = null;
            this.password = null;
        }

        public DatabaseConnection(final String host, final int port, final String database, final String user, final String password) {
            this.host = host;
            this.port = port;
            this.database = database;
            this.user = user;
            this.password = password;
        }

        public final void setHost(final String host) {
            this.host = host;
        }

        public final void setPort(final int port) {
            this.port = port;
        }

        public final void setDatabase(final String database) {
            this.database = database;
        }

        public final void setUser(final String user) {
            this.user = user;
        }

        public final void setPassword(final String password) {
            this.password = password;
        }
    }
}
