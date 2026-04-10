package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class DeadRootsPlantBlock extends AerialHellTwistingVinesPlantBlock
{
    public DeadRootsPlantBlock(BlockBehaviour.Properties settings) {super(settings);}

    private boolean isEntityImmuneToDamage(Entity entity)
    {
        return ((entity instanceof Spider) || EntityHelper.isShadowEntity(entity) || EntityHelper.isMudEntity(entity)) || EntityHelper.isFeatheryEntity(entity) || EntityHelper.isImmuneToBramblesDamage(entity);
    }

    @Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
    {
        if (world instanceof ServerLevel serverWorld && entity instanceof LivingEntity && !isEntityImmuneToDamage(entity))
        {
            entity.hurtServer(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.ROOT_THORNS), 2.0F);
        }
    }
}
