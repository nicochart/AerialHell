package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
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
        this.setSoundEvent(SoundEvents.HEAVY_CORE_HIT);
    }

    @Override protected SoundEvent getDefaultHitGroundSoundEvent() {return SoundEvents.HEAVY_CORE_HIT;}

    protected abstract float getShardDamage();
}