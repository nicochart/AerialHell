package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.world.entity.Entity;

//implemented on Player by PlayerSneakEdgeBypassMixin (both sides)
//vanilla sneaking stops the player at block edges, and at the edges of any walkable hitbox (see Player.maybeBackOffFromEdge)
//while the bypass is active, sneaking does not stop the player at edges : used when a boss drags or repulses players (see AbstractBossEntity.dragOrRepulseBypassesSneak)
//the client moves its own player, then the server replays the movement : the bypass must be active on both sides (boss drag and repulse are called on both sides)
public interface SneakEdgeBypassPlayer
{
    int DEFAULT_BYPASS_DURATION = 10; //ticks after the last push, also covering the remaining momentum

    void aerialhell$bypassSneakEdgeFor(int ticks);
    boolean aerialhell$isBypassingSneakEdge();

    static void bypassSneakEdge(Entity entity, int ticks) {if (entity instanceof SneakEdgeBypassPlayer player) {player.aerialhell$bypassSneakEdgeFor(ticks);}}
}
