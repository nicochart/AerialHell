package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class VenomousSnakeEntity extends AbstractSnakeEntity
{
    public VenomousSnakeEntity(EntityType<? extends VenomousSnakeEntity> type, Level world) {super(type, world);}

    @Override protected BodyPartDeathReaction getBodyPartDeathReaction() {return BodyPartDeathReaction.LOOSE_TAIL;}

    @Override public boolean doHurtTarget(Entity attackedEntity)
    {
        boolean flag = super.doHurtTarget(attackedEntity);
        if (flag && attackedEntity instanceof LivingEntity livingEntity && !EntityHelper.isLivingEntityShadowImmune(livingEntity))
        {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 500, 0));
        }
        return flag;
    }
}
