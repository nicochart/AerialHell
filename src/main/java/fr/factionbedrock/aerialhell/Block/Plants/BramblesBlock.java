package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;

public class BramblesBlock extends AerialHellTallGrassBlock
{
	public BramblesBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}

	@Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entityIn, InsideBlockEffectApplier handler, boolean intersects)
	{
		entityIn.makeStuckInBlock(state, new Vec3((double)0.8F, 0.75D, (double)0.8F));
		if (world instanceof ServerLevel serverWorld && entityIn instanceof LivingEntity livingEntity)
    	{
			if (!EntityHelper.isImmuneToBramblesDamage(livingEntity))
			{
				int poisonDuration = this == AerialHellBlocks.SHADOW_BRAMBLES ? 60 : 40;
				livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, 0));
				livingEntity.hurtServer(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.BRAMBLES_THORNS), 1.0F);
			}
    	}
	}
}
