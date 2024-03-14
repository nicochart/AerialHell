package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class VerdigrisZombieEntity extends AerialHellHostileEntity
{
    public VerdigrisZombieEntity(EntityType<? extends AerialHellHostileEntity> type, Level world)
    {
        super(type, world);
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
        		.add(Attributes.FOLLOW_RANGE, 35.0D);
    }
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.ZOMBIE_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.ZOMBIE_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ZOMBIE_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 0.5F);}
}
