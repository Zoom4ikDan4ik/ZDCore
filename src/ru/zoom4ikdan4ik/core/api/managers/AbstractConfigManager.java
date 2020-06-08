package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.IConfigManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.ICommands;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IMessages;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.ISQLs;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractConfigManager implements IConfigManager, IBase {
    private final Plugin plugin;

    private File file;
    private FileConfiguration config;

    public AbstractConfigManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return this.getPlugin().getName();
    }

    public final Plugin getPlugin() {
        return this.plugin;
    }

    public final FileConfiguration getConfig() {
        return this.config;
    }

    public final void setConfig(FileConfiguration fileConfiguration) {
        this.config = fileConfiguration;
    }

    public final File getFileConfig() {
        return this.file;
    }

    public final void setFileConfig(File file) {
        this.file = file;
    }

    public final void save() {
        try {
            this.getConfig().save(this.getFileConfig());
        } catch (IOException error) {
            this.loggerUtils.info(this.plugin, error.getMessage());
        }
    }

    public final void registerMessages(Object[] objects) {
        for (Object object : objects)
            if (object instanceof IMessages)
                this.registerMessage((IMessages) object);
    }

    public final void registerCommands(Object[] objects) {
        for (Object object : objects)
            if (object instanceof ICommands)
                this.registerCommand((ICommands) object);
    }

    public final void registerPermissions(Object[] objects) {
        for (Object object : objects)
            if (object instanceof IPermissions)
                this.registerPermission((IPermissions) object);
    }

    public final void registerQueries(Object[] objects) {
        for (Object object : objects)
            if (object instanceof ISQLs)
                this.registerQuery((ISQLs) object);
    }

    public final void registerMessage(IMessages message) {
        this.setPath(CategoriesEnum.MESSAGES, message.getName(), message.getMessage());

        message.setMessage(this.getMessage(message.getName()));
    }

    public final void registerCommand(ICommands command) {
        this.setPath(CategoriesEnum.COMMANDS, command.getName(), command.getSubCommand());

        command.setSubCommand(this.getCommand(command.getName()));
    }

    public final void registerPermission(IPermissions permissions) {
        this.setPath(CategoriesEnum.PERMISSIONS, permissions.getName(), permissions.getPermission());

        permissions.setPermission(this.getPermssion(permissions.getName()));
    }

    public final void registerQuery(ISQLs query) {
        this.setPath(CategoriesEnum.QUERIES, query.getName(), query.getSQL());

        query.setSQL(this.getQuery(query.getName()));
    }

    public final String getMessage(String path) {
        return this.getString(this.getPath(CategoriesEnum.MESSAGES, path));
    }

    public final String getCommand(String path) {
        return this.getString(this.getPath(CategoriesEnum.COMMANDS, path));
    }

    public final String getPermssion(String path) {
        return this.getString(this.getPath(CategoriesEnum.PERMISSIONS, path));
    }

    public final String getQuery(String path) {
        return this.getString(this.getPath(CategoriesEnum.QUERIES, path));
    }

    public final String getString(String path) {
        return this.getConfig().getString(path);
    }

    public final String getString(String path, String option) {
        this.setPath(path, option);

        return this.getConfig().getString(path);
    }

    public final int getInt(String path) {
        return this.getConfig().getInt(path);
    }

    public final int getInt(String path, int option) {
        this.setPath(path, option);

        return this.getConfig().getInt(path);
    }

    public final boolean getBoolean(String path) {
        return this.getConfig().getBoolean(path);
    }

    public final boolean getBoolean(String path, boolean option) {
        this.setPath(path, option);

        return this.getConfig().getBoolean(path);
    }

    public final double getDouble(String path) {
        return this.getConfig().getDouble(path);
    }

    public final double getDouble(String path, double option) {
        this.setPath(path, option);

        return this.getConfig().getDouble(path);
    }

    public final long getLong(String path) {
        return this.getConfig().getLong(path);
    }

    public final long getLong(String path, long option) {
        this.setPath(path, option);

        return this.getConfig().getLong(path);
    }

    public final List<String> getStringList(String path) {
        return this.getConfig().getStringList(path);
    }

    public final List<String> getStringList(String path, List<String> option) {
        this.setPath(path, option);

        return this.getConfig().getStringList(path);
    }

    public final List<?> getList(String path) {
        return this.getConfig().getList(path);
    }

    public final List<?> getList(String path, List<?> option) {
        this.setPath(path, option);

        return this.getConfig().getList(path);
    }

    public final World getWorld(String path) {
        return Bukkit.getWorld(this.getConfig().getString(path));
    }

    public final World getWorld(String path, String option) {
        this.setPath(path, option);

        String world = this.getConfig().getString(path);

        if (world != null)
            return Bukkit.getWorld(world);
        else return Bukkit.getWorlds().get(0);
    }

    public final ConfigurationSection getConfigurationSection(String path) {
        return this.getConfig().getConfigurationSection(path);
    }

    public final void setPath(String path, Object option) {
        this.setPath(CategoriesEnum.CONFIG, path, option);
    }

    public final void setPath(CategoriesEnum categoriesEnum, String path, Object option) {
        if (!this.isSet(categoriesEnum, path))
            this.getConfig().set(this.getPath(categoriesEnum.getCategory(), path), option);
    }

    public final void setValue(String path, Object option) {
        this.getConfig().set(path, option);
    }

    public final boolean isSet(CategoriesEnum categoriesEnum, String path) {
        return this.isSet(this.getPath(categoriesEnum.getCategory(), path));
    }

    public final boolean isSet(String path) {
        return this.getConfig().isSet(path);
    }

    public final String getPath(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < objects.length; i++) {
            stringBuilder.append(objects[i]);

            if (i < objects.length - 1)
                stringBuilder.append(".");
        }

        return stringBuilder.toString();
    }

    public enum CategoriesEnum {
        CONFIG("config"), MESSAGES("messages"), PERMISSIONS("permissions"), COMMANDS("commands"), QUERIES("queries"), CUSTOMS("customs");

        private String category;

        CategoriesEnum(String category) {
            this.category = category;
        }

        public final String getCategory() {
            return this.category.toUpperCase();
        }

        public final void setCategory(String category) {
            this.category = category;
        }
    }
}
