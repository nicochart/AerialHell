package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DeadRootsPlantBlock extends AerialHellTwistingVinesPlantBlock
{
    public DeadRootsPlantBlock(Properties properties) {super(properties);}

    private boolean isEntityImmuneToDamage(Entity entityIn)
    {
        return ((entityIn instanceof Spider) || EntityHelper.isShadowEntity(entityIn) || EntityHelper.isMudEntity(entityIn)) || EntityHelper.isFeatheryEntity(entityIn) || EntityHelper.isImmuneToBramblesDamage(entityIn);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity && !isEntityImmuneToDamage(entityIn))
        {
            entityIn.hurt(AerialHellDamageTypes.getDamageSource(level, AerialHellDamageTypes.ROOT_THORNS), 2.0F);
        }
    }
}
