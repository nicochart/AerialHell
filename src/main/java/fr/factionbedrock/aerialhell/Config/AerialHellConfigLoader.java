package fr.factionbedrock.aerialhell.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.factionbedrock.aerialhell.AerialHell;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;

public class AerialHellConfigLoader
{
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FOLDER = new File(FMLPaths.CONFIGDIR.get().toFile(), AerialHell.MODID);
    private static final File CONFIG_FILE = new File(CONFIG_FOLDER, "config.json");

    public static void loadAndStoreConfigParams()
    {
        AerialHellConfig config = loadConfig();
        LoadedConfigParams.loadConfigParams(config);
    }

    public static AerialHellConfig loadConfig()
    {
        if (!CONFIG_FOLDER.exists()) {CONFIG_FOLDER.mkdirs();}

        if (!CONFIG_FILE.exists())
        {
            AerialHellConfig defaultconfig = new AerialHellConfig();
            saveConfig(defaultconfig);
            return defaultconfig;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {return GSON.fromJson(reader, AerialHellConfig.class);}
        catch (IOException e) {e.printStackTrace(); return new AerialHellConfig();}
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
