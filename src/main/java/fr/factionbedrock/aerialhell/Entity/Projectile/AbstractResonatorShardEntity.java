package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public abstract class AbstractResonatorShardEntity extends AbstractArrow
{
    public AbstractResonatorShardEntity(EntityType<? extends AbstractResonatorShardEntity> type, Level level)
    {
        super(type, level);
        this.setBaseDamage(this.getShardDamage());
    }

    @Override protected void onHitBlock(BlockHitResult result)
    {
        super.onHitBlock(result);
        this.setSoundEvent(AerialHellSoundEvents.HEAVY_CORE_HIT.get());
    }

    @Override protected SoundEvent getDefaultHitGroundSoundEvent() {return AerialHellSoundEvents.HEAVY_CORE_HIT.get();}

    protected abstract float getShardDamage();
}