package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VenomousSnakeEntity extends AbstractSnakeEntity
{
    public VenomousSnakeEntity(EntityType<? extends VenomousSnakeEntity> type, Level world) {super(type, world);}

    @Override protected BodyPartDeathReaction getBodyPartDeathReaction() {return BodyPartDeathReaction.LOOSE_TAIL;}
    @Override protected IntProvider getLength() {return UniformInt.of(14,20);}//{return ConstantInt.of(16);}

    @Override public boolean doHurtTarget(Entity attackedEntity)
    {
        boolean flag = super.doHurtTarget(attackedEntity);
        if (flag && attackedEntity instanceof LivingEntity livingEntity && !EntityHelper.isLivingEntityShadowImmune(livingEntity))
        {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 500, 0));
        }
        return flag;
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }
}
