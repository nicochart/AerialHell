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
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AerialHellConfigLoader
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FOLDER = FabricLoader.getInstance().getConfigDir().resolve(AerialHell.MODID);
    private static final Path CONFIG_PATH = CONFIG_FOLDER.resolve("config.json");
    public static final String CONFIG_VERSION = "0.7.8";

    public static void loadAndStoreConfigParams()
    {
        AerialHellConfig config = loadConfig();
        LoadedConfigParams.loadConfigParams(config);
    }

    public static AerialHellConfig loadConfig()
    {
        AerialHellConfig config;
        if (!Files.exists(CONFIG_PATH))
        {
            config = new AerialHellDefaultConfig();
            saveConfig(config);
        }
        else
        {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {config = GSON.fromJson(reader, AerialHellConfig.class);}
            catch (IOException e) {e.printStackTrace(); config = new AerialHellDefaultConfig();}

            if (!config.configVersion.equals(CONFIG_VERSION))
            {
                AerialHell.LOGGER.warn("Aerial Hell : Outdated config -> backup created, new config file generated.");

                AerialHellConfig newConfig = new AerialHellDefaultConfig();
                if (config.configVersion.equals("0.7.3.1")) //"0.7.3.1" -> "0.7.8" introduced new parameter "customMainMenuBackground". No other change.
                {
                    //newConfig.configVersion = CONFIG_VERSION; //new config version
                    newConfig.shadowSpreadSpeedMultiplier = config.shadowSpreadSpeedMultiplier; //unchanged
                    newConfig.enableShadowBindReloadTexture = config.enableShadowBindReloadTexture; //unchanged
                    newConfig.enableShadowBindTextureShift = config.enableShadowBindTextureShift; //unchanged
                    newConfig.doBossGriefing = config.doBossGriefing; //unchanged
                    //newConfig.customMainMenuBackground = true; //missing parameter in 0.7.3.1
                    newConfig.overworldAbandonnedPortalSpacingOverride = config.overworldAbandonnedPortalSpacingOverride; //unchanged
                }

                backupOldConfig();
                config = newConfig;
                saveConfig(newConfig);
            }
        }

        return config;
    }

    private static void backupOldConfig()
    {
        try
        {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
            Path backupPath = CONFIG_FOLDER.resolve("config-outdated_" + timestamp + ".json");
            Files.move(CONFIG_PATH, backupPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {e.printStackTrace();}
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
