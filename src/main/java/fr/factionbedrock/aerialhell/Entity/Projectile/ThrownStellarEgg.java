package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ThrownStellarEgg extends ThrownItemEntity
{
    public ThrownStellarEgg(EntityType<? extends ThrownStellarEgg> type, World world) {super(type, world);}
    public ThrownStellarEgg(World world, LivingEntity shooter) {super(AerialHellEntities.THROWN_STELLAR_EGG, shooter, world, AerialHellItems.STELLAR_EGG.getDefaultStack());}
    public ThrownStellarEgg(World world, double x, double y, double z) {super(AerialHellEntities.THROWN_STELLAR_EGG, x, y, z, world, AerialHellItems.STELLAR_EGG.getDefaultStack());}

    @Override public void handleStatus(byte p_37484_) //copied from EggEntity
    {
        if (p_37484_ == 3)
        {
            for(int i = 0; i < 8; ++i)
            {
                this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getDefaultItem().getDefaultStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    @Override protected void onEntityHit(EntityHitResult entityHitResult) //copied from EggEntity
    {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().serverDamage(this.getDamageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override protected void onCollision(HitResult hitResult) //copied from EggEntity, replacing Chicken with StellarChicken
    {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient)
        {
            if (this.random.nextInt(8) == 0)
            {
                int i = 1;
                if (this.random.nextInt(32) == 0) {i = 4;}

                for(int j = 0; j < i; ++j)
                {
                    StellarChickenEntity chicken = AerialHellEntities.STELLAR_CHICKEN.create(this.getWorld(), SpawnReason.TRIGGERED);
                    if (chicken != null)
                    {
                        chicken.setBreedingAge(-24000);
                        chicken.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        chicken.setColor(this.getWorld().getColor(this.getBlockPos(), Biome::getGrassColorAt));

                        this.getWorld().spawnEntity(chicken);
                    }
                }
            }
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

    @Override protected Item getDefaultItem() {return AerialHellItems.STELLAR_EGG;}
}
