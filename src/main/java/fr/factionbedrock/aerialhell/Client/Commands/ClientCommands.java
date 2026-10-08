package fr.factionbedrock.aerialhell.Client.Commands;

import fr.factionbedrock.aerialhell.Client.Util.PanoramaCaptureHandler;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

public class ClientCommands
{
    //only in dev env
    //see PanoramaCaptureHandler
    public static void registerPanoramaCommand(RegisterClientCommandsEvent event)
    {
        event.getDispatcher().register(Commands.literal("aerialhell")
            .then(Commands.literal("panorama")
            .executes(context -> PanoramaCaptureHandler.scheduleCapture(context.getSource())))
        );
    }
}