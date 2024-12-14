package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BramblesBlock extends AerialHellTallGrassBlock
{
	public BramblesBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, false));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn)
	{
		entityIn.slowMovement(state, new Vec3d((double)0.8F, 0.75D, (double)0.8F));
		if (!world.isClient() && entityIn instanceof LivingEntity livingEntity)
    	{
			if (!EntityHelper.isImmuneToBramblesDamage(livingEntity))
			{
				int poisonDuration = this == AerialHellBlocks.SHADOW_BRAMBLES ? 60 : 40;
				livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, poisonDuration, 0));
				livingEntity.damage(AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.BRAMBLES_THORNS), 1.0F);
			}
    	}
	}
}
