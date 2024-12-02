package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownStellarEgg extends ThrownItemEntity
{
    public ThrownStellarEgg(EntityType<? extends ThrownStellarEgg> type, Level level) {super(type, level);}
    public ThrownStellarEgg(Level level, LivingEntity shooter) {super(AerialHellEntities.THROWN_STELLAR_EGG.get(), shooter, level);}
    public ThrownStellarEgg(Level level, double x, double y, double z) {super(AerialHellEntities.THROWN_STELLAR_EGG.get(), x, y, z, level);}

    @Override public void handleEntityEvent(byte p_37484_) //copied from ThrownEgg
    {
        if (p_37484_ == 3)
        {
            for(int i = 0; i < 8; ++i)
            {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    protected void onHitEntity(EntityHitResult entityHitResult) //copied from ThrownEgg
    {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override protected void onHit(HitResult hitResult) //copied from ThrownEgg, replacing Chicken with StellarChicken
    {
        super.onHit(hitResult);
        if (!this.level().isClientSide)
        {
            if (this.random.nextInt(8) == 0)
            {
                int i = 1;
                if (this.random.nextInt(32) == 0) {i = 4;}

                for(int j = 0; j < i; ++j)
                {
                    StellarChickenEntity chicken = AerialHellEntities.STELLAR_CHICKEN.get().create(this.level());
                    if (chicken != null)
                    {
                        chicken.setAge(-24000);
                        chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        chicken.setColor(this.level().getBlockTint(this.getBlockPos(), Biome::getGrassColor));

                        this.level().spawnEntity(chicken);
                    }
                }
            }
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override protected Item getDefaultItem() {return AerialHellBlocksAndItems.STELLAR_EGG.get();}
}
