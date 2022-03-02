package fr.factionbedrock.aerialhell.Registry;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Commands.TpAerialHell;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class AerialHellCommands
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
        LiteralCommandNode<CommandSource> cmdAerialHell = dispatcher.register(
                Commands.literal(AerialHell.MODID)
                        .then(TpAerialHell.register(dispatcher))
        );

        dispatcher.register(Commands.literal("aerialhell").redirect(cmdAerialHell));
    }
}
