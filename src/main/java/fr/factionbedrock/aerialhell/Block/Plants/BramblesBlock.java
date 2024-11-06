package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;

public class BramblesBlock extends AerialHellTallGrassBlock
{
	public BramblesBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
	{
		entityIn.makeStuckInBlock(state, new Vec3((double)0.8F, 0.75D, (double)0.8F));
		if (!level.isClientSide() && entityIn instanceof LivingEntity livingEntity)
    	{
			if (!EntityHelper.isImmuneToBramblesDamage(livingEntity))
			{
				int poisonDuration = this == AerialHellBlocksAndItems.SHADOW_BRAMBLES.get() ? 60 : 40;
				livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, 0));
				livingEntity.hurt(AerialHellDamageTypes.getDamageSource(level, AerialHellDamageTypes.BRAMBLES_THORNS), 1.0F);
			}
    	}
	}
}
