package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.IntangibleTemporaryBlock;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class DimensionShattererProjectileEntity extends Fireball
{
	public DimensionShattererProjectileEntity(EntityType<? extends DimensionShattererProjectileEntity> type, Level world) {super(type, world);}
	public DimensionShattererProjectileEntity(Level world, LivingEntity shooter) {this(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, world); this.setOwner(shooter);}

	public DimensionShattererProjectileEntity(Level world, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, x, y, z, new Vec3(accX, accY, accZ), world);
	}

	public DimensionShattererProjectileEntity(Level world, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, shooter, new Vec3(accX, accY, accZ), world);
	}

	@Override public boolean fireImmune() {return true;}
	@Override protected boolean shouldBurn() {return false;}
	@Override public float getLightLevelDependentMagicValue() {return 0.0F;}

	@Override protected void onHit(HitResult result) {}

	@Override protected void defineSynchedData(SynchedEntityData.Builder builder) {super.defineSynchedData(builder);}

	@Override public void tick()
	{
		if (this.level().isClientSide() || (this.getOwner() == null || !this.getOwner().isRemoved()) && this.level().hasChunkAt(this.blockPosition()))
		{
			Vec3 vec3 = this.getDeltaMovement();
			double d0 = this.getX() + vec3.x;
			double d1 = this.getY() + vec3.y;
			double d2 = this.getZ() + vec3.z;
			ProjectileUtil.rotateTowardsMovement(this, 0.2F);
			ParticleOptions particleoptions = this.getTrailParticle();
			if (particleoptions != null) {this.level().addParticle(particleoptions, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);}
			this.setPosRaw(d0, d1, d2);
		}

		if (this.tickCount < 100)
		{
			if (!this.level().isClientSide())
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
								pos = new BlockPos((int) (this.getX() - 0.5F + x), (int) (this.getY() + 0.5F + y), (int) (this.getZ() - 0.5F + z));
								BlockState beforeState = this.level().getBlockState(pos);
								if (!beforeState.isAir())
								{
									if (beforeState.getBlock() != AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)
									{
										IntangibleTemporaryBlock intangibleBlock = ((IntangibleTemporaryBlock) AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK);
										this.level().setBlock(pos, intangibleBlock.defaultBlockState(), 2);
										BlockHelper.setIntangibleTemporaryBlockEntityBeforeState(this.level(), pos, beforeState);
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

	public void shootStraightForward(Entity shooter, float pitch, float yaw, float roll, float velocity, float inaccuracy)
	{
		float x = -Mth.sin(yaw * (float) (Math.PI / 180.0)) * Mth.cos(pitch * (float) (Math.PI / 180.0));
		float y = -Mth.sin((pitch + roll) * (float) (Math.PI / 180.0));
		float z = Mth.cos(yaw * (float) (Math.PI / 180.0)) * Mth.cos(pitch * (float) (Math.PI / 180.0));
		this.shoot(x, y, z, velocity, inaccuracy);
	}

	@Nullable @Override protected ParticleOptions getTrailParticle() {return null;}
}
