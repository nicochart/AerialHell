package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VerdigrisZombieEntity extends AerialHellHostileEntity
{
    public VerdigrisZombieEntity(EntityType<? extends AerialHellHostileEntity> type, World world)
    {
        super(type, world);
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
        		.createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D);
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 0.5F);
    }
}
