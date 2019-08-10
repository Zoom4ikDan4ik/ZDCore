package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.ICore;
import ru.zoom4ikdan4ik.core.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.managers.base.SQLBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.function.Function;

public class SQLiteManager extends SQLBase implements ISQLManager {
    //TODO Did it...
    private Connection sqliteConnection;

    @Override
    public void createTables() {

    }

    public Connection getConnection(ICore plugin) {
        try {
            this.sqliteConnection = DriverManager.getConnection(
                    "jdbc:sqlite://" + plugin.getConfigManager().getFileConfig().getAbsolutePath());

            if (this.isConnection(this.sqliteConnection))
                return this.sqliteConnection;
        } catch (Exception error) {
            this.loggerUtils.info(this.corePlugin, "[SQLite] " + error.getMessage());
        }

        return null;
    }

    public void executeUpdate(String query) {
        super.executeUpdate(this.sqliteConnection, query);
    }

    public <R> R executeResultSet(String query, Function<ResultSet, R> func) {
        return super.executeResultSet(this.sqliteConnection, query, func);
    }
}
