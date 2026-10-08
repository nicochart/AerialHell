package fr.factionbedrock.aerialhell.Client.Util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.ClientTickEvent;

//Dev tool (registered only in dev environment, see AerialHellClientSetup)
//"/aerialhell panorama" captures the 6 faces of a title screen panorama (cube map) from the player eyes, using vanilla panorama screenshot
//pictures are saved in <game directory>/screenshots/panorama_0.png ... panorama_5.png
public class PanoramaCaptureHandler
{
    private static final int DELAY_SECONDS = 4; //lets the chat screen close before capture
    private static int ticksBeforeCapture = -1;

    public static int scheduleCapture(CommandSourceStack source) {return scheduleCapture(source, DELAY_SECONDS);}

    private static int scheduleCapture(CommandSourceStack source, int delaySeconds)
    {
        ticksBeforeCapture = Math.max(1, delaySeconds * 20);
        source.sendSystemMessage(Component.literal("Aerial Hell panorama capture in " + delaySeconds + "s, make sure to have FOV set to 90, and don't move !").withStyle(ChatFormatting.DARK_GREEN));
        return 1;
    }

    public static void panoramaClientTick(ClientTickEvent.Post event)
    {
        if (ticksBeforeCapture < 0 || --ticksBeforeCapture > 0) {return;}
        ticksBeforeCapture = -1;

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) {return;}
        minecraft.player.sendSystemMessage(Component.literal("Aerial Hell panorama capture saved in screenshots folder").withStyle(ChatFormatting.DARK_GREEN));
    }
}