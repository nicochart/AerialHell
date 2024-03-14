package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EntEntity extends AerialHellHostileEntity
{
    public EntEntity(EntityType<? extends AerialHellHostileEntity> type, Level world)
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
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.WOOD_HIT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.WOOD_BREAK;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.WOOD_FALL;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.WOOD_STEP, 0.15F, 0.5F);}
}
