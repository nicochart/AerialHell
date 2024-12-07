package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntEntity extends AerialHellHostileEntity
{
    public EntEntity(EntityType<? extends AerialHellHostileEntity> type, World world) {super(type, world);}
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
        		.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D);
    }
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.BLOCK_WOOD_HIT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.BLOCK_WOOD_BREAK;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.BLOCK_WOOD_FALL;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.BLOCK_WOOD_STEP, 0.15F, 0.5F);}
}
