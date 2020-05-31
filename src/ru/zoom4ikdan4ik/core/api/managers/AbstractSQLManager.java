package ru.zoom4ikdan4ik.core.api.managers;

import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public abstract class AbstractSQLManager implements IBase {
    public boolean isConnection(Connection connection) throws SQLException {
        if (connection == null) {
            this.loggerUtils.info(this.corePlugin, "Could not connect to the database");

            throw new SQLException();
        } else if (!connection.isValid(5)) {
            this.loggerUtils.info(this.corePlugin, "Could not connect to the database.");

            throw new SQLException();
        } else
            return true;
    }

    public void executeUpdate(Connection connection, String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException error) {
            this.loggerUtils.info(this.corePlugin, "[executeUpdate] " + error.getMessage());
        } catch (NullPointerException error) {
            this.loggerUtils.info(this.corePlugin, error.getMessage());
        }
    }

    public <R> R executeResultSet(Connection connection, String query, Function<ResultSet, R> func) {
        try (Statement statement = connection.createStatement()) {
            return func.apply(statement.executeQuery(query));
        } catch (SQLException error) {
            this.loggerUtils.info(this.corePlugin, "[getResultSet] " + error.getMessage());
        } catch (NullPointerException error) {
            this.loggerUtils.info(this.corePlugin, error.getMessage());
        }

        return null;
    }
}
