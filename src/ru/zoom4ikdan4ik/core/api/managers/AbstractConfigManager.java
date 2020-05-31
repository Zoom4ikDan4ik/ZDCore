package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractConfigManager implements IConfigManager, IBase {
    private final ICore plugin;

    private File file;
    private FileConfiguration config;

    public AbstractConfigManager(ICore plugin) {
        this.plugin = plugin;
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
            this.loggerUtils.info((Plugin) this.plugin, error.getMessage());
        }
    }

    public final void registerMessage(String path, String message) {
        this.setPath(CategoriesEnum.MESSAGES, path, message);
    }

    public final void registerCommand(String path, String command) {
        this.setPath(CategoriesEnum.COMMANDS, path, command);
    }

    public final void registerPermission(String path, String permissions) {
        this.setPath(CategoriesEnum.PERMISSIONS, path, permissions);
    }

    public final void registerQuery(String path, String query) {
        this.setPath(CategoriesEnum.QUERIES, path, query);
    }

    public final String getMessage(String path) {
        return this.getPath(CategoriesEnum.MESSAGES, path);
    }

    public final String getCommand(String path) {
        return this.getPath(CategoriesEnum.COMMANDS, path);
    }

    public final String getPermssion(String path) {
        return this.getPath(CategoriesEnum.PERMISSIONS, path);
    }

    public final String getQuery(String path) {
        return this.getPath(CategoriesEnum.QUERIES, path);
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

        return Bukkit.getWorld(this.getConfig().getString(path));
    }

    public final ConfigurationSection getConfigurationSection(String path) {
        return this.getConfig().getConfigurationSection(path);
    }

    public final void setPath(CategoriesEnum categoriesEnum, String path, Object option) {
        if (!this.isSet(categoriesEnum, path))
            this.getConfig().set(this.getPath(categoriesEnum.getCategory(), path), option);
    }

    public final void setPath(String path, Object option) {
        if (!this.isSet(path))
            this.getConfig().set(path, option);
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
        MESSAGES("messages"), PERMISSIONS("permissions"), COMMANDS("commands"), QUERIES("queries");

        private final String category;

        CategoriesEnum(String category) {
            this.category = category;
        }

        public String getCategory() {
            return this.category;
        }
    }
}
