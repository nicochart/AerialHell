package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.IntangibleTemporaryBlock;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class DimensionShattererProjectileEntity extends Fireball
{
	public DimensionShattererProjectileEntity(EntityType<? extends DimensionShattererProjectileEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public DimensionShattererProjectileEntity(Level worldIn, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE.get(), x, y, z, new Vec3(accX, accY, accZ), worldIn);
	}

	public DimensionShattererProjectileEntity(Level worldIn, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE.get(), shooter, new Vec3(accX, accY, accZ), worldIn);
	}

	@Override public boolean fireImmune() {return true;}
	@Override protected boolean shouldBurn() {return false;}
	@Override public float getLightLevelDependentMagicValue() {return 0.0F;}

	@Override protected void onHit(HitResult result) {}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder) {super.defineSynchedData(builder);}

	@Override public void tick()
	{
		if (this.tickCount < 200)
		{
			if (!this.level().isClientSide)
			{
				BlockPos pos;
				for (int x=-2; x<=2; x++)
				{
					for (int y=-2; y<=2; y++)
					{
						for (int z=-2; z<=2; z++)
						{
							pos = this.blockPosition().offset(x,y,z);
							BlockState beforeState = this.level().getBlockState(pos);
							if (!beforeState.isAir() && beforeState.getBlock() != AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get())
							{
								IntangibleTemporaryBlock intangibleBlock = ((IntangibleTemporaryBlock) AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get());
								this.level().setBlock(pos, intangibleBlock.defaultBlockState(), 2);
								setIntangibleTemporaryBlockEntityBeforeState(this.level(), pos, beforeState);
							}
						}
					}
				}
			}
		}
		else {this.discard();}
	}

	public static void setIntangibleTemporaryBlockEntityBeforeState(LevelAccessor level, BlockPos pos, @Nullable BlockState state)
	{
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity) {intangibleblockentity.setBeforeState(state);}
	}
}
