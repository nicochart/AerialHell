package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractSlimePirateEntity extends AbstractHumanoidMonster
{
    public AbstractSlimePirateEntity(EntityType<? extends AbstractSlimePirateEntity> type, World world) {super(type, world, 1.0F, 0.33F);}

    @Override public void remove(Entity.RemovalReason removalReason)
    {
        if (!this.getWorld().isClient && !this.isBaby() && this.isDead())
        {
            int number = 1 + this.random.nextInt(2);
            if (random.nextInt(5) == 0) {number++;}
            for (int l = 0; l < number; ++l)
            {
                float x = ((float) (l % 2) - 0.5F) * 0.5F;
                float z = ((float) (l / 2) - 0.5F) * 0.5F;
                AbstractSlimePirateEntity littlePirate = this.getDieOffspringType().create(this.getWorld(), SpawnReason.TRIGGERED);
                if (littlePirate != null)
                {
                    if (this.isPersistent()) {littlePirate.setPersistent();}
                    littlePirate.setCustomName(this.getCustomName());
                    littlePirate.setAiDisabled(this.isAiDisabled());
                    littlePirate.setInvulnerable(this.isInvulnerable());
                    littlePirate.setBaby(true);
                    littlePirate.refreshPositionAndAngles(this.getX() + (double) x, this.getY() + 0.5D, this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
                    this.getWorld().spawnEntity(littlePirate);
                    //No weapon
                    //littlePirate.initEquipment(this.getRandom(), this.getWorld().getCurrentDifficultyAt(this.getBlockPos()));
                }
            }
        }
        super.remove(removalReason);
    }

    @Override public boolean damage(ServerWorld serverWorld, DamageSource damageSource, float amount)
    {
        return super.damage(serverWorld, damageSource, this.isBaby() ? amount * 1.5F : amount);
    }

    public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return this.getType();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType()
    {
        return (EntityType<? extends AbstractSlimePirateEntity>) super.getType();
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AbstractHumanoidMonster.registerAttributes(20.0D, 4.0D, 0.2D, 35.0D);
    }

    @Override public int getMinAmbientSoundDelay() {return 300;}
    @Override protected SoundEvent getAmbientSound(){return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SLIME_PIRATE_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(AerialHellSoundEvents.ENTITY_SLIME_PIRATE_STEP, 0.15F, 0.5F);}
}
