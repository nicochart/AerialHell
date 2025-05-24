package fr.factionbedrock.aerialhell.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.factionbedrock.aerialhell.AerialHell;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AerialHellConfigLoader
{
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FOLDER = new File(FMLPaths.CONFIGDIR.get().toFile(), AerialHell.MODID);
    private static final File CONFIG_FILE = new File(CONFIG_FOLDER, "config.json");
    public static final String CONFIG_VERSION = "0.7.3.1";

    public static void loadAndStoreConfigParams()
    {
        AerialHellConfig config = loadConfig();
        LoadedConfigParams.loadConfigParams(config);
    }

    public static AerialHellConfig loadConfig()
    {
        if (!CONFIG_FOLDER.exists()) {CONFIG_FOLDER.mkdirs();}

        AerialHellConfig config;
        if (!CONFIG_FILE.exists())
        {
            config = new AerialHellDefaultConfig();
            saveConfig(config);
        }
        else
        {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {config = GSON.fromJson(reader, AerialHellConfig.class);}
            catch (IOException e) {e.printStackTrace(); config = new AerialHellDefaultConfig();}

            if (!config.configVersion.equals(CONFIG_VERSION))
            {
                AerialHell.LOGGER.warn("Aerial Hell : Outdated config -> backup created, new config file generated.");
                backupOldConfig();
                config = new AerialHellDefaultConfig();
                saveConfig(config);
            }
        }

        return config;
    }

    private static void backupOldConfig()
    {
        try
        {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
            Path backupPath = new File(CONFIG_FOLDER, "config-outdated_" + timestamp + ".json").toPath();
            Files.move(CONFIG_FILE.toPath(), backupPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public static void saveConfig(AerialHellConfig config)
    {
        try
        {
            if (!CONFIG_FOLDER.exists()) {CONFIG_FOLDER.mkdirs();}
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {GSON.toJson(config, writer);}
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
