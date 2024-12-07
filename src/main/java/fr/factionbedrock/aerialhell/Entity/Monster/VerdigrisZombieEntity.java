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

public class VerdigrisZombieEntity extends AerialHellHostileEntity
{
    public VerdigrisZombieEntity(EntityType<? extends AerialHellHostileEntity> type, World world) {super(type, world);}
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
        		.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D);
    }
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.ENTITY_ZOMBIE_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.ENTITY_ZOMBIE_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_ZOMBIE_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 0.5F);}
}
