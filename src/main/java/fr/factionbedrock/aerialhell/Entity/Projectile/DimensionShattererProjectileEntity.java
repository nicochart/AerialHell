package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.IntangibleTemporaryBlock;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DimensionShattererProjectileEntity extends FireballEntity
{
	public DimensionShattererProjectileEntity(EntityType<? extends DimensionShattererProjectileEntity> type, World world) {super(type, world);}
	public DimensionShattererProjectileEntity(World world, LivingEntity shooter) {this(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, world); this.setOwner(shooter);}

	public DimensionShattererProjectileEntity(World world, double x, double y, double z, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, x, y, z, new Vec3d(accX, accY, accZ), world);
	}

	public DimensionShattererProjectileEntity(World world, LivingEntity shooter, double accX, double accY, double accZ)
	{
		super(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, shooter, new Vec3d(accX, accY, accZ), world);
	}

	@Override public boolean isFireImmune() {return true;}
	@Override protected boolean isBurning() {return false;}
	@Override public float getBrightnessAtEyes() {return 0.0F;}

	@Override protected void onCollision(HitResult result) {}

	@Override protected void initDataTracker(DataTracker.Builder builder) {super.initDataTracker(builder);}

	@Override public void tick()
	{
		if (this.getWorld().isClient || (this.getOwner() == null || !this.getOwner().isRemoved()) && this.getWorld().isChunkLoaded(this.getBlockPos()))
		{
			Vec3d vec3 = this.getVelocity();
			double d0 = this.getX() + vec3.x;
			double d1 = this.getY() + vec3.y;
			double d2 = this.getZ() + vec3.z;
			ProjectileUtil.setRotationFromVelocity(this, 0.2F);
			ParticleEffect particleoptions = this.getParticleType();
			if (particleoptions != null) {this.getWorld().addParticle(particleoptions, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);}
			this.setPos(d0, d1, d2);
		}

		if (this.age < 100)
		{
			if (!this.getWorld().isClient)
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
								pos = new BlockPos((int) (this.getPos().x - 0.5F + x), (int) (this.getPos().y + 0.5F + y), (int) (this.getPos().z - 0.5F + z));
								BlockState beforeState = this.getWorld().getBlockState(pos);
								if (!beforeState.isAir())
								{
									if (beforeState.getBlock() != AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK)
									{
										IntangibleTemporaryBlock intangibleBlock = ((IntangibleTemporaryBlock) AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK);
										this.getWorld().setBlockState(pos, intangibleBlock.getDefaultState(), 2);
										setIntangibleTemporaryBlockEntityBeforeState(this.getWorld(), pos, beforeState);
									}
									else
									{
										BlockEntity blockentity = this.getWorld().getBlockEntity(pos);
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

	public void shootStraightForward(Entity shooter, float xRot, float yRot, float zRot, float velocity, float inaccuracy)
	{
		float x = -MathHelper.sin(yRot * (float) (Math.PI / 180.0)) * MathHelper.cos(xRot * (float) (Math.PI / 180.0));
		float y = -MathHelper.sin((xRot + zRot) * (float) (Math.PI / 180.0));
		float z = MathHelper.cos(yRot * (float) (Math.PI / 180.0)) * MathHelper.cos(xRot * (float) (Math.PI / 180.0));
		this.setVelocity(x, y, z, velocity, inaccuracy);
	}

	@Nullable @Override protected ParticleEffect getParticleType() {return null;}

	public static void setIntangibleTemporaryBlockEntityBeforeState(WorldView level, BlockPos pos, @Nullable BlockState state)
	{
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity) {intangibleblockentity.setBeforeState(state);}
	}
}
