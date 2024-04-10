package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WormEntity extends AbstractSnakeEntity
{
    public WormEntity(EntityType<? extends WormEntity> type, Level world) {super(type, world);}

    @Override protected BodyPartDeathReaction getBodyPartDeathReaction() {return BodyPartDeathReaction.SPLIT_IF_NOT_HEAD;}
    @Override protected IntProvider getLength() {return UniformInt.of(10,24);}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }
}
