package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSoldierEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ThornyWebBlock extends WebBlock
{
	public ThornyWebBlock(Properties properties)
	{
		super(properties);
	}
	
	private boolean isEntityImmune(Entity entityIn)
	{
		return ((entityIn instanceof SpiderEntity) || (entityIn instanceof HellSpiderEntity) || (entityIn instanceof MudSoldierEntity) || (entityIn instanceof MudGolemEntity) || (entityIn instanceof MudCycleMageEntity));
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		boolean isImmune = isEntityImmune(entityIn);
		
		if (!isImmune) {entityIn.setMotionMultiplier(state, new Vector3d(0.45D, 0.25D, 0.45D));}
		if (entityIn instanceof LivingEntity && !isImmune)
		{
			
			((LivingEntity) entityIn).attackEntityFrom(new DamageSource("web_thorns"), 2.0F);
		}
	}
}
