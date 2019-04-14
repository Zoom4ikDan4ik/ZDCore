package ru.zoom4ikdan4ik.core.managers.base;

import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public class SQLBase implements IBase {

    public boolean isConnection(Connection connection) throws SQLException {
        if (connection == null) {
            loggerUtils.info(corePlugin, "Could not connect to the database");

            throw new SQLException();
        } else if (!connection.isValid(5)) {
            loggerUtils.info(corePlugin, "Could not connect to the database.");

            throw new SQLException();
        } else
            return true;
    }

    public void executeUpdate(Connection connection, String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException error) {
            loggerUtils.info(corePlugin, "[executeUpdate] " + error.getMessage());
        } catch (NullPointerException error) {
            loggerUtils.info(corePlugin, error.getMessage());
        }
    }

    public <R> R executeResultSet(Connection connection, String query, Function<ResultSet, R> func) {
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
