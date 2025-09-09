package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownStellarEgg extends ThrowableItemProjectile
{
    public ThrownStellarEgg(EntityType<? extends ThrownStellarEgg> type, Level level) {super(type, level);}
    public ThrownStellarEgg(Level level, LivingEntity shooter) {super(AerialHellEntities.THROWN_STELLAR_EGG.get(), shooter, level, AerialHellItems.STELLAR_EGG.toStack());}
    public ThrownStellarEgg(Level level, double x, double y, double z) {super(AerialHellEntities.THROWN_STELLAR_EGG.get(), x, y, z, level, AerialHellItems.STELLAR_EGG.toStack());}

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

    @Override protected void onHitEntity(EntityHitResult entityHitResult) //copied from ThrownEgg
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
                    StellarChickenEntity chicken = AerialHellEntities.STELLAR_CHICKEN.get().create(this.level(), EntitySpawnReason.TRIGGERED);
                    if (chicken != null)
                    {
                        chicken.setAge(-24000);
                        chicken.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        chicken.setColor(this.level().getBlockTint(this.blockPosition(), Biome::getGrassColor));

                        this.level().addFreshEntity(chicken);
                    }
                }
            }
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override protected Item getDefaultItem() {return AerialHellItems.STELLAR_EGG.get();}
}
