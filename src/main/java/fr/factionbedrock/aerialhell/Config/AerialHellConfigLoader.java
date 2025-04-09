package fr.factionbedrock.aerialhell.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.factionbedrock.aerialhell.AerialHell;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class AerialHellConfigLoader
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FOLDER = FabricLoader.getInstance().getConfigDir().resolve(AerialHell.MODID);
    private static final Path CONFIG_PATH = CONFIG_FOLDER.resolve("config.json");

    public static void loadAndStoreConfigParams()
    {
        AerialHellConfig config = loadConfig();
        LoadedConfigParams.loadConfigParams(config);
    }

    public static AerialHellConfig loadConfig()
    {
        if (!Files.exists(CONFIG_PATH))
        {
            AerialHellConfig defaultConfig = new AerialHellConfig();
            saveConfig(defaultConfig);
            return defaultConfig;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {return GSON.fromJson(reader, AerialHellConfig.class);}
        catch (IOException e) {e.printStackTrace(); return new AerialHellConfig();}
    }

    public static void saveConfig(AerialHellConfig config)
    {
        try
        {
            Files.createDirectories(CONFIG_FOLDER);
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {GSON.toJson(config, writer);}
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
