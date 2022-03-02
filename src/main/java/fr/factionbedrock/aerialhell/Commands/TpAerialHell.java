package fr.factionbedrock.aerialhell.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import fr.factionbedrock.aerialhell.Registry.AerialHellDimensions;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import java.util.function.Function;

public class TpAerialHell implements Command<CommandSource>
{
    private static final TpAerialHell CMD = new TpAerialHell();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
    {
        return Commands.literal("dim")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        ServerPlayerEntity player = context.getSource().asPlayer();
        int x = player.getPosition().getX();
        int z = player.getPosition().getZ();
        if (player.getEntityWorld().getDimensionKey().equals(AerialHellDimensions.AERIAL_HELL_DIMENSION))
        {
            ServerWorld world = player.getServer().getWorld(World.OVERWORLD);
            TeleportationTools.teleport(player, world, new BlockPos(x, 200, z));
        }
        else
        {
            ServerWorld world = player.getServer().getWorld(AerialHellDimensions.AERIAL_HELL_DIMENSION);
            TeleportationTools.teleport(player, world, new BlockPos(x, 200, z));
        }
        return 0;
    }
    
    private static class TeleportationTools
    {
        public static void teleport(ServerPlayerEntity entity, ServerWorld destination, BlockPos pos)
        {
            entity.changeDimension(destination, new ITeleporter()
            {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity)
                {
                    entity = repositionEntity.apply(false);
                    entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    return entity;
                }
            });
        }
    }

}

