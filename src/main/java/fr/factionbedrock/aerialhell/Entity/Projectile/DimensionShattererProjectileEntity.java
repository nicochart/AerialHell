package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.IntangibleTemporaryBlock;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class DimensionShattererProjectileEntity extends Fireball
{
	public DimensionShattererProjectileEntity(EntityType<? extends DimensionShattererProjectileEntity> type, Level level) {super(type, level);}
	public DimensionShattererProjectileEntity(Level level, LivingEntity shooter) {this(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE.get(), level); this.setOwner(shooter);}

	public DimensionShattererProjectileEntity(Level level, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE.get(), x, y, z, new Vec3(accX, accY, accZ), level);
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
		if (this.level().isClientSide || (this.getOwner() == null || !this.getOwner().isRemoved()) && this.level().hasChunkAt(this.blockPosition()))
		{
			Vec3 vec3 = this.getDeltaMovement();
			double d0 = this.getX() + vec3.x;
			double d1 = this.getY() + vec3.y;
			double d2 = this.getZ() + vec3.z;
			ProjectileUtil.rotateTowardsMovement(this, 0.2F);
			ParticleOptions particleoptions = this.getTrailParticle();
			if (particleoptions != null) {this.level().addParticle(particleoptions, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);}
			this.setPos(d0, d1, d2);
		}

		if (this.tickCount < 100)
		{
			if (!this.level().isClientSide)
			{
				BlockPos pos;
				for (int x=-2; x<=2; x++)
				{
					for (int y=2; y>=-2; y--)
					{
						for (int z=-2; z<=2; z++)
						{
							if (!((Math.abs(x) == 2 && Math.abs(y) == 2) || (Math.abs(x) == 2 && Math.abs(z) == 2) || (Math.abs(y) == 2 && Math.abs(z) == 2)))
							{
								pos = new BlockPos((int) (this.position().x - 0.5F + x), (int) (this.position().y + 0.5F + y), (int) (this.position().z - 0.5F + z));
								BlockState beforeState = this.level().getBlockState(pos);
								if (!beforeState.isAir())
								{
									if (beforeState.getBlock() != AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get())
									{
										IntangibleTemporaryBlock intangibleBlock = ((IntangibleTemporaryBlock) AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get());
										this.level().setBlockState(pos, intangibleBlock.getDefaultState(), 2);
										setIntangibleTemporaryBlockEntityBeforeState(this.level(), pos, beforeState);
									}
									else
									{
										BlockEntity blockentity = this.level().getBlockEntity(pos);
										if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleBlockEntity) {intangibleBlockEntity.resetTickCount();}
									}
								}
							}
						}
					}
				}
			}
		}
		else {this.discard();}
	}

	public void shootStraightForwars(Entity shooter, float xRot, float yRot, float zRot, float velocity, float inaccuracy)
	{
		float x = -MathHelper.sin(yRot * (float) (Math.PI / 180.0)) * MathHelper.cos(xRot * (float) (Math.PI / 180.0));
		float y = -MathHelper.sin((xRot + zRot) * (float) (Math.PI / 180.0));
		float z = MathHelper.cos(yRot * (float) (Math.PI / 180.0)) * MathHelper.cos(xRot * (float) (Math.PI / 180.0));
		this.shoot(x, y, z, velocity, inaccuracy);
	}

	@Nullable @Override protected ParticleOptions getTrailParticle() {return null;}

	public static void setIntangibleTemporaryBlockEntityBeforeState(LevelAccessor level, BlockPos pos, @Nullable BlockState state)
	{
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity) {intangibleblockentity.setBeforeState(state);}
	}
}
