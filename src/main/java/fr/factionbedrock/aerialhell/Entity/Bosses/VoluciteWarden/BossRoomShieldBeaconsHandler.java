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
//when a shield transition starts, shield beacons are placed on the pedestals
//if one is already placed, the beacon is updated and his life is restored
//the chosen pedestals are the closest to the warden target
//each beacon deals a fraction of his own taken damage to the VW
//beacons disappear by themselves when the shield breaks
//blocks placed by players where a beacon appears are destroyed with their drops
public class BossRoomShieldBeaconsHandler
{
    public static final double PEDESTAL_DISTANCE = 15.5D; //pedestal center, from the room center (on x & z)
    private static final int BEACON_Y = 15; //beacon feet (pedestal top), relative to the boss room piece min y

    private final VoluciteWardenEntity warden;
    @Nullable private BoundingBox room; //boss room piece (the one the warden stands in), null outside a volucite castle

    public BossRoomShieldBeaconsHandler(VoluciteWardenEntity warden) {this.warden = warden;}

    public void setRoom(@Nullable BoundingBox room) {this.room = room;}

    //called when a shield transition starts (server side). shieldLevel : 1 to 3, depends on the warden real health (see VoluciteWardenEntity.startShieldTransition)
    public void placeShieldBeacons(int bossDifficulty, int shieldLevel)
    {
        if (this.room == null || !(this.warden.level() instanceof ServerLevel level)) {return;}

        //0 or 1 player : depends on shield level, 1 -> 1 or 2 (random) -> 2 (this.getSoloBeaconCount(shieldLevel))
        //2 or 3 players : 2
        //4 or 5 players : 3
        //more : 4
        int beaconCount = bossDifficulty <= 1 ? this.getSoloBeaconCount(shieldLevel) : Mth.clamp((int) (bossDifficulty * 0.75F), 2, 4);
        List<Vec3> pedestals = this.getPedestalPositions();
        Util.shuffle(pedestals, this.warden.getRandom()); //random choice between equally close pedestals (or without target)
        @Nullable LivingEntity target = this.warden.getTarget();
        if (target != null) {pedestals.sort(Comparator.comparingDouble(pedestal -> target.distanceToSqr(pedestal)));}

        //beacons still standing count in the beacons number. missing beacons : on the free pedestals closest to the target
        int missingBeacons = beaconCount;
        List<VoluciteWardenShieldBeaconEntity> standingBeacons = new ArrayList<>();
        List<Vec3> freePedestals = new ArrayList<>();
        for (Vec3 pedestal : pedestals)
        {
            List<VoluciteWardenShieldBeaconEntity> beacons = getShieldBeacons(level, pedestal);
            if (beacons.isEmpty()) {freePedestals.add(pedestal);}
            else {standingBeacons.addAll(beacons); missingBeacons--;}
        }
        int newBeacons = Mth.clamp(missingBeacons, 0, freePedestals.size());

        //n beacons : each one deals 1/n of the beacons damage to the warden
        float wardenDamageMultiplier = 1.0F / (standingBeacons.size() + newBeacons);
        standingBeacons.forEach(beacon -> this.restoreShieldBeacon(level, beacon, wardenDamageMultiplier));
        for (int i = 0; i < newBeacons; i++) {this.placeShieldBeacon(level, freePedestals.get(i), wardenDamageMultiplier);}
    }

    private int getSoloBeaconCount(int shieldLevel) {return shieldLevel <= 1 ? 1 : shieldLevel == 2 ? Mth.nextInt(this.warden.getRandom(), 1, 2) : 2;}

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

    private static List<VoluciteWardenShieldBeaconEntity> getShieldBeacons(ServerLevel level, Vec3 pedestal)
    {
        return level.getEntitiesOfClass(VoluciteWardenShieldBeaconEntity.class, getShieldBeaconBox(pedestal), LivingEntity::isAlive);
    }

    private static AABB getShieldBeaconBox(Vec3 pos) {return AerialHellEntities.VOLUCITE_WARDEN_SHIELD_BEACON.get().getDimensions().makeBoundingBox(pos);}

    private static Vec3 getShieldBeaconCenter(VoluciteWardenShieldBeaconEntity beacon) {return beacon.position().add(0.0D, beacon.getBbHeight() / 2.0F, 0.0D);}

    //beacon still standing when a shield transition starts
    private void restoreShieldBeacon(ServerLevel level, VoluciteWardenShieldBeaconEntity beacon, float wardenDamageMultiplier)
    {
        beacon.setWarden(this.warden, wardenDamageMultiplier); //updating multiplier
        if (beacon.getHealth() < beacon.getMaxHealth()) {beacon.setHealth(beacon.getMaxHealth());} //restoring health
        VoluciteGlyphParticles.sendInward(level, getShieldBeaconCenter(beacon), 1.5F, 3.5F, 12);
    }

    private void placeShieldBeacon(ServerLevel level, Vec3 pos, float wardenDamageMultiplier)
    {
        VoluciteWardenShieldBeaconEntity beacon = AerialHellEntities.VOLUCITE_WARDEN_SHIELD_BEACON.get().create(level, EntitySpawnReason.MOB_SUMMONED);
        if (beacon == null) {return;}

        this.destroyBlocksInTheWay(level, getShieldBeaconBox(pos));
        beacon.snapTo(pos.x, pos.y, pos.z, 0.0F, 0.0F);
        beacon.setWarden(this.warden, wardenDamageMultiplier);
        level.addFreshEntity(beacon);

        Vec3 beaconCenter = getShieldBeaconCenter(beacon);
        VoluciteGlyphParticles.sendInward(level, beaconCenter, 1.5F, 3.5F, 12);
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
