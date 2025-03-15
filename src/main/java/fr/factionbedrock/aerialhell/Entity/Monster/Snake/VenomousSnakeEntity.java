package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

public class VenomousSnakeEntity extends AbstractSnakeEntity
{
    public VenomousSnakeEntity(EntityType<? extends VenomousSnakeEntity> type, World world) {super(type, world);}

    @Override protected BodyPartDeathReaction getBodyPartDeathReaction() {return BodyPartDeathReaction.LOOSE_TAIL;}
    @Override protected UniformIntProvider getLength() {return UniformIntProvider.create(14,20);}//{return ConstantInt.of(16);}
    @Override protected int getMinLength() {return 3;}

    @Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
        boolean flag = super.tryAttack(serverWorld, attackedEntity);
        if (flag && attackedEntity instanceof LivingEntity livingEntity && !EntityHelper.isLivingEntityShadowImmune(livingEntity))
        {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 500, 0));
        }
        return flag;
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 3.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.FOLLOW_RANGE, 35.0D);
    }
}
