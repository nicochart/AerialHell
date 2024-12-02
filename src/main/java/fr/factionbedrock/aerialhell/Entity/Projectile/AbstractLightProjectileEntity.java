package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public abstract class AbstractLightProjectileEntity extends ThrownEntity
{
    private int ticksInAir = 0;
    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, Level world) {super(type, world);}

    public AbstractLightProjectileEntity(EntityType<? extends AbstractLightProjectileEntity> type, LivingEntity shooter, Level world)
    {
        super(type, shooter, world);
        this.setOwner(shooter);
    }
    @Override public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
    	super.shoot(x, y, z, velocity, inaccuracy);
    	this.playSound(this.getShootSound(), 3, 0.875F + 0.25F * random.nextFloat());
    }

    //@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {}
    @Override public boolean isNoGravity() {return true;}

    @Override public void tick()
    {
    	double d1,d2,d3; d1 = 0.5D - random.nextFloat(); d2 = 0.5D - random.nextFloat(); d3 = 0.5D - random.nextFloat();
        this.level().addParticle(this.getFlyParticle(), this.getX() + d1, this.getY() + 0.3D + d2, this.getZ() + d3, d1, d2, d3);
        super.tick();
        if (!this.onGround()) {++this.ticksInAir;}
        if (this.ticksInAir > 300) {this.discard();}
        if (this.level().getBlockState(this.getBlockPos()).is(AerialHellTags.Blocks.SOLID_ETHER)) {this.playHitEffect(); this.discard();}
        if (this.level() instanceof  ServerLevel serverLevel)
        {
            transformBlocks(serverLevel, this, this.getShiftType());
        }

    }

    static void transformBlocks(ServerLevel level, AbstractLightProjectileEntity projectile, BiomeShifter.ShiftType shiftType)
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
                        pos = new BlockPos((int) (projectile.position().x - 0.5F + x), (int) (projectile.position().y + 0.5F + y), (int) (projectile.position().z - 0.5F + z));
                        if (shiftType == BiomeShifter.ShiftType.UNCORRUPT && BlockHelper.isCorrupted(level, pos))
                        {
                            BlockHelper.uncorrupt(level, pos);
                        }
                        else if (shiftType == BiomeShifter.ShiftType.CORRUPT && !BlockHelper.isCorrupted(level, pos) && BlockHelper.canBeCorrupted(level, pos, BlockHelper.CorruptionType.ANY))
                        {
                            BlockHelper.corrupt(level, pos, BlockHelper.CorruptionType.ANY);
                        }
                    }
                }
            }
        }
    }

    protected abstract BiomeShifter.ShiftType getShiftType();

    @Override protected void onHit(HitResult result)
    {
        this.playHitEffect();
        super.onHit(result);
        if (result.getType() != HitResult.Type.ENTITY && !this.level().isClientSide()) {this.discard();}
    }

    public void playHitEffect()
    {
        double d1,d2,d3,d4,d5,d6;
        d1 = 0.5D - random.nextFloat(); d2 = 0.5D - random.nextFloat(); d3 = 0.5D - random.nextFloat(); d4 = 0.5D - random.nextFloat(); d5 = 0.5D - random.nextFloat(); d6 = 0.5D - random.nextFloat();
        this.level().addParticle(this.getImpactParticle(), this.getX() - d1, this.getY() - d2, this.getZ() - d3, -d1, -d2, -d3);
        this.level().addParticle(this.getImpactParticle(), this.getX() - d4, this.getY() - d5, this.getZ() - d6, -d4, -d5, -d6);
        this.level().addParticle(this.getFlyParticle(), this.getX() + d1, this.getY() + d2, this.getZ() + d3, d1, d2, d3);
        this.level().addParticle(this.getFlyParticle(), this.getX() + d4, this.getY() + d5, this.getZ() + d6, d4, d5, d6);
        this.playDisappearSound(1, 0.75F + 0.5F * random.nextFloat());
    }

    @Override protected void onHitEntity(EntityHitResult result)
    {
        this.playDisappearSound(1, 0.25F + 0.25F * random.nextFloat());
    }

    protected abstract SimpleParticleType getImpactParticle();
    protected abstract SimpleParticleType getFlyParticle();
    protected abstract SoundEvent getShootSound();
    protected abstract void playDisappearSound(float volume, float pitch);
}
