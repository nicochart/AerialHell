package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractLightProjectileEntity extends ThrownEntity
{
    private int ticksInAir = 0;
    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, World world) {super(type, world);}

    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, LivingEntity shooter, World world)
    {
        super(type, shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), world);
        this.setOwner(shooter);
    }
    @Override public void setVelocity(double x, double y, double z, float velocity, float inaccuracy)
    {
    	super.setVelocity(x, y, z, velocity, inaccuracy);
    	this.playSound(this.getShootSound(), 3, 0.875F + 0.25F * random.nextFloat());
    }

    //@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}
    @Override protected void initDataTracker(DataTracker.Builder builder) {}
    @Override public boolean hasNoGravity() {return true;}

    @Override public void tick()
    {
    	double d1,d2,d3; d1 = 0.5D - random.nextFloat(); d2 = 0.5D - random.nextFloat(); d3 = 0.5D - random.nextFloat();
        this.getWorld().addParticleClient(this.getFlyParticle(), this.getX() + d1, this.getY() + 0.3D + d2, this.getZ() + d3, d1, d2, d3);
        super.tick();
        if (!this.isOnGround()) {++this.ticksInAir;}
        if (this.ticksInAir > 300) {this.discard();}
        if (this.getWorld().getBlockState(this.getBlockPos()).isIn(AerialHellTags.Blocks.SOLID_ETHER)) {this.playHitEffect(); this.discard();}
        if (this.getWorld() instanceof ServerWorld serverWorld)
        {
            transformBlocks(serverWorld, this, this.getShiftType());
        }

    }

    static void transformBlocks(ServerWorld world, AbstractLightProjectileEntity projectile, BiomeShifter.ShiftType shiftType)
    {
        BlockPos pos;
        for (int x=-2; x<=2; x++)
        {
            for (int y = 2; y >= -2; y--)
            {
                for (int z = -2; z <= 2; z++)
                {
                    if (!((Math.abs(x) == 2 && Math.abs(y) == 2) || (Math.abs(x) == 2 && Math.abs(z) == 2) || (Math.abs(y) == 2 && Math.abs(z) == 2)))
                    {
                        pos = new BlockPos((int) (projectile.getPos().x - 0.5F + x), (int) (projectile.getPos().y + 0.5F + y), (int) (projectile.getPos().z - 0.5F + z));
                        if (shiftType == BiomeShifter.ShiftType.UNCORRUPT && BlockHelper.isCorrupted(world, pos))
                        {
                            BlockHelper.uncorrupt(world, pos);
                        }
                        else if (shiftType == BiomeShifter.ShiftType.CORRUPT && !BlockHelper.isCorrupted(world, pos) && BlockHelper.canBeCorrupted(world, pos, BlockHelper.CorruptionType.ANY))
                        {
                            BlockHelper.corrupt(world, pos, BlockHelper.CorruptionType.ANY);
                        }
                    }
                }
            }
        }
    }

    protected abstract BiomeShifter.ShiftType getShiftType();

    @Override protected void onCollision(HitResult result)
    {
        this.playHitEffect();
        super.onCollision(result);
        if (result.getType() != HitResult.Type.ENTITY && !this.getWorld().isClient()) {this.discard();}
    }

    public void playHitEffect()
    {
        double d1,d2,d3,d4,d5,d6;
        d1 = 0.5D - random.nextFloat(); d2 = 0.5D - random.nextFloat(); d3 = 0.5D - random.nextFloat(); d4 = 0.5D - random.nextFloat(); d5 = 0.5D - random.nextFloat(); d6 = 0.5D - random.nextFloat();
        this.getWorld().addParticleClient(this.getImpactParticle(), this.getX() - d1, this.getY() - d2, this.getZ() - d3, -d1, -d2, -d3);
        this.getWorld().addParticleClient(this.getImpactParticle(), this.getX() - d4, this.getY() - d5, this.getZ() - d6, -d4, -d5, -d6);
        this.getWorld().addParticleClient(this.getFlyParticle(), this.getX() + d1, this.getY() + d2, this.getZ() + d3, d1, d2, d3);
        this.getWorld().addParticleClient(this.getFlyParticle(), this.getX() + d4, this.getY() + d5, this.getZ() + d6, d4, d5, d6);
        this.playDisappearSound(1, 0.75F + 0.5F * random.nextFloat());
    }

    @Override protected void onEntityHit(EntityHitResult result)
    {
        this.playDisappearSound(1, 0.25F + 0.25F * random.nextFloat());
    }

    protected abstract SimpleParticleType getImpactParticle();
    protected abstract SimpleParticleType getFlyParticle();
    protected abstract SoundEvent getShootSound();
    protected abstract void playDisappearSound(float volume, float pitch);
}
