package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

//beacon sustaining the anchored volucite warden shield, standing on a boss room pedestal. looks like a volucite pillar (arm segment tail, see VoluciteWardenShieldBeaconModel)
//placed by the warden when a shield transition starts : 2 to 4 beacons depending on the boss difficulty (see BossRoomShieldBeaconsHandler)
//not linked to the warden yet : no shield loss when destroyed, never removed by the warden, no particles toward it
public class VoluciteWardenShieldBeaconEntity extends Mob
{
    public VoluciteWardenShieldBeaconEntity(EntityType<? extends VoluciteWardenShieldBeaconEntity> type, Level level) {super(type, level);}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        if (source.getEntity() != null && source.getEntity().is(AerialHellTags.Entities.VOLUCITE)) {return false;} //not hurt by the warden or its allies
        return super.hurtServer(level, source, amount);
    }

    /* ------------------------------------------------ */
    /* ---------- Block-like (static) entity ---------- */
    /* ------------------------------------------------ */

    @Override public boolean canBeCollidedWith(@Nullable Entity entity) {return true;} //block-like collision
    @Override public boolean isNoGravity() {return true;}
    @Override public boolean isPushable() {return false;}
    @Override public boolean isPushedByFluid() {return false;}
    @Override public boolean isInWall() {return false;}
    @Override public boolean canBreatheUnderwater() {return true;}
    @Override public boolean canBeLeashed() {return false;}
    @Override public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
    @Override public void knockback(double strength, double x, double z, DamageSource damageSource, float damage) {}
    @Override public void knockback(double strength, double x, double z, DamageSource damageSource, float damage, boolean comesFromEffect) {}

    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.STONE_HIT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.STONE_BREAK;}
}
