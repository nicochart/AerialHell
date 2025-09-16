package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeadRootsBlock extends AerialHellTwistingVinesBlock
{
    public DeadRootsBlock(AbstractBlock.Settings settings) {super(settings);}

    private boolean isEntityImmuneToDamage(Entity entity)
    {
        return ((entity instanceof SpiderEntity) || EntityHelper.isShadowEntity(entity) || EntityHelper.isMudEntity(entity)) || EntityHelper.isFeatheryEntity(entity) || EntityHelper.isImmuneToBramblesDamage(entity);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler)
    {
        if (world instanceof ServerWorld serverWorld && entity instanceof LivingEntity && !isEntityImmuneToDamage(entity))
        {
            entity.damage(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.ROOT_THORNS), 2.0F);
        }
    }
}
