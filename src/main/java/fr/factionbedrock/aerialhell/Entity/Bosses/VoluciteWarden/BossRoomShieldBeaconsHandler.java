package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//anchored warden boss room shield beacons : 4 pedestals around the room center (on the diagonals, PEDESTAL_DISTANCE from the center on both x & z)
//when a shield transition starts, shield beacons are placed on the pedestals : as many as the boss difficulty (number of nearby players), between 2 and 4
//the chosen pedestals are the closest to the players (a lone player gets the 2 pedestals of its side). a pedestal already holding a beacon gets no other one
//blocks placed by players where a beacon appears are destroyed with their drops (same as the stairs, see BossRoomStairsHandler)
public class BossRoomShieldBeaconsHandler
{
    private static final double PEDESTAL_DISTANCE = 15.5D; //pedestal center, from the room center (on x & z)
    private static final int BEACON_Y = 15; //beacon feet (pedestal top), relative to the boss room piece min y
    private static final int MIN_BEACONS = 2, MAX_BEACONS = 4;
    private static final double PLAYERS_SEARCH_RANGE = 32.0D; //players choosing the pedestals, around the pedestals level center

    private final VoluciteWardenEntity warden;
    @Nullable private BoundingBox room; //boss room piece (the one the warden stands in), null outside a volucite castle

    public BossRoomShieldBeaconsHandler(VoluciteWardenEntity warden) {this.warden = warden;}

    public void setRoom(@Nullable BoundingBox room) {this.room = room;}

    //called when a shield transition starts (server side)
    public void placeShieldBeacons()
    {
        if (this.room == null || !(this.warden.level() instanceof ServerLevel level)) {return;}

        int beaconCount = Mth.clamp(this.warden.getDifficulty(), MIN_BEACONS, MAX_BEACONS);
        List<Vec3> pedestals = this.getPedestalPositions();
        List<Player> players = this.getPlayersInRoom(level);
        Util.shuffle(pedestals, this.warden.getRandom()); //random choice between equally close pedestals (or without any player)
        pedestals.sort(Comparator.comparingDouble(pedestal -> getDistanceToClosestPlayerSqr(pedestal, players)));

        for (Vec3 pedestal : pedestals.subList(0, beaconCount))
        {
            if (!hasShieldBeacon(level, pedestal)) {placeShieldBeacon(level, pedestal);}
        }
    }

    //beacon positions (feet, pedestal top center) : the 4 diagonals of the room center
    private List<Vec3> getPedestalPositions()
    {
        List<Vec3> pedestals = new ArrayList<>();
        if (this.room == null) {return pedestals;}

        double centerX = (this.room.minX() + this.room.maxX() + 1) / 2.0D, centerZ = (this.room.minZ() + this.room.maxZ() + 1) / 2.0D; //same as the warden anchor
        for (int xSide : new int[] {-1, 1})
        {
            for (int zSide : new int[] {-1, 1}) {pedestals.add(new Vec3(centerX + xSide * PEDESTAL_DISTANCE, this.room.minY() + BEACON_Y, centerZ + zSide * PEDESTAL_DISTANCE));}
        }
        return pedestals;
    }

    //creative players are included : they never change the beacon count (not counted in the boss difficulty), but they can check where beacons appear
    private List<Player> getPlayersInRoom(ServerLevel level)
    {
        if (this.room == null) {return List.of();}
        Vec3 center = new Vec3((this.room.minX() + this.room.maxX() + 1) / 2.0D, this.room.minY() + BEACON_Y, (this.room.minZ() + this.room.maxZ() + 1) / 2.0D);
        return level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(PLAYERS_SEARCH_RANGE), player -> player.isAlive() && !player.isSpectator());
    }

    private static double getDistanceToClosestPlayerSqr(Vec3 pos, List<Player> players)
    {
        double minDistanceSqr = Double.MAX_VALUE;
        for (Player player : players) {minDistanceSqr = Math.min(minDistanceSqr, player.distanceToSqr(pos));}
        return minDistanceSqr;
    }

    private static boolean hasShieldBeacon(ServerLevel level, Vec3 pedestal)
    {
        return !level.getEntitiesOfClass(VoluciteWardenShieldBeaconEntity.class, getShieldBeaconBox(pedestal), LivingEntity::isAlive).isEmpty();
    }

    private static AABB getShieldBeaconBox(Vec3 pos) {return AerialHellEntities.VOLUCITE_WARDEN_SHIELD_BEACON.get().getDimensions().makeBoundingBox(pos);}

    private void placeShieldBeacon(ServerLevel level, Vec3 pos)
    {
        VoluciteWardenShieldBeaconEntity beacon = AerialHellEntities.VOLUCITE_WARDEN_SHIELD_BEACON.get().create(level, EntitySpawnReason.MOB_SUMMONED);
        if (beacon == null) {return;}

        this.destroyBlocksInTheWay(level, getShieldBeaconBox(pos));
        beacon.snapTo(pos.x, pos.y, pos.z, 0.0F, 0.0F);
        level.addFreshEntity(beacon);

        Vec3 beaconCenter = pos.add(0.0D, beacon.getBbHeight() / 2.0F, 0.0D);
        VoluciteGlyphParticles.sendInward(level, beaconCenter, 1.5F, 3.5F, 12); //glyphs gathering into the beacon
        level.playSound(null, beaconCenter.x, beaconCenter.y, beaconCenter.z, SoundEvents.STONE_PLACE, this.warden.getSoundSource(), 2.0F, 0.6F);
    }

    //blocks placed by players inside the beacon box are destroyed with their drops. core protected blocks, fluids & unbreakable blocks are kept
    private void destroyBlocksInTheWay(ServerLevel level, AABB box)
    {
        for (BlockPos pos : BlockPos.betweenClosed(BlockPos.containing(box.minX, box.minY, box.minZ), BlockPos.containing(box.maxX - 1.0E-4D, box.maxY - 1.0E-4D, box.maxZ - 1.0E-4D)))
        {
            BlockState state = level.getBlockState(pos);
            if (state.isAir() || state.getBlock() instanceof LiquidBlock || BlockHelper.isCoreProtected(state) || state.getDestroySpeed(level, pos) < 0.0F) {continue;}
            level.destroyBlock(pos, true, this.warden);
        }
    }
}
