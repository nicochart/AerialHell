package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractSlimePirateEntity extends AbstractHumanoidMonster
{
    public AbstractSlimePirateEntity(EntityType<? extends AbstractSlimePirateEntity> type, Level world) {super(type, world, 1.0F, 0.33F);}

    @Override public void remove(Entity.RemovalReason removalReason)
    {
        if (!this.level().isClientSide && !this.isBaby() && this.isDeadOrDying())
        {
            int number = 1 + this.random.nextInt(2);
            if (random.nextInt(5) == 0) {number++;}
            for (int l = 0; l < number; ++l)
            {
                float x = ((float) (l % 2) - 0.5F) * 0.5F;
                float z = ((float) (l / 2) - 0.5F) * 0.5F;
                AbstractSlimePirateEntity littlePirate = this.getDieOffspringType().create(this.level());
                if (littlePirate != null)
                {
                    if (this.isPersistenceRequired()) {littlePirate.setPersistenceRequired();}
                    littlePirate.setCustomName(this.getCustomName());
                    littlePirate.setNoAi(this.isNoAi());
                    littlePirate.setInvulnerable(this.isInvulnerable());
                    littlePirate.setBaby(true);
                    littlePirate.moveTo(this.getX() + (double) x, this.getY() + 0.5D, this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
                    this.level().addFreshEntity(littlePirate);
                    //No weapon
                    //littlePirate.populateDefaultEquipmentSlots(this.getRandom(), this.level().getCurrentDifficultyAt(this.blockPosition()));
                }
            }
        }
        super.remove(removalReason);
    }

    @Override public boolean hurt(DamageSource damageSource, float amount)
    {
        return super.hurt(damageSource, this.isBaby() ? amount * 1.5F : amount);
    }

    public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return this.getType();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType()
    {
        return (EntityType<? extends AbstractSlimePirateEntity>) super.getType();
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(20.0D, 4.0D, 0.2D, 35.0D);
    }

    @Override public int getAmbientSoundInterval() {return 300;}
    @Override protected SoundEvent getAmbientSound(){return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_DEATH.get();}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(AerialHellSoundEvents.ENTITY_SLIME_PIRATE_STEP.get(), 0.15F, 0.5F);}
}
