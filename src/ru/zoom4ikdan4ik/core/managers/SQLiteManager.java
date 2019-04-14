package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.ICore;
import ru.zoom4ikdan4ik.core.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.managers.base.SQLBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteManager extends SQLBase implements ISQLManager {
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
            loggerUtils.info(corePlugin, "[SQLite] " + error.getMessage());
        }

        return null;
    }
}
