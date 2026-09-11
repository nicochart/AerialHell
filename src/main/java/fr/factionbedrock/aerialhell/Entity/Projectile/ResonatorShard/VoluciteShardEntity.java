package fr.factionbedrock.aerialhell.Entity.Projectile.ResonatorShard;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractResonatorShardEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VoluciteShardEntity extends AbstractResonatorShardEntity
{
    public int ticksLiving = 0;
    public VoluciteShardEntity(EntityType<VoluciteShardEntity> type, Level level)
    {
        super(type, level);
    }

    @Override public void tick()
    {
        super.tick();
        this.ticksLiving++;

        if (!this.inGround)
        {
            double customGravity = getCustomGravity();
            double gravityCorrection = 0.05D - customGravity;

            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y + gravityCorrection, motion.z);
        }
    }

    protected double getCustomGravity()
    {
        if (this.ticksLiving >= 100) {return 0.05F;}
        else if (this.ticksLiving > 90) {return 0.04F;}
        else if (this.ticksLiving > 80) {return 0.03F;}
        else if (this.ticksLiving > 65) {return 0.02F;}
        else if (this.ticksLiving > 50) {return 0.01F;}
        else {return 0.0F;}
    }

    @Override public void addAdditionalSaveData(CompoundTag output)
    {
        super.addAdditionalSaveData(output);
        output.putInt("ticksLiving", this.ticksLiving);
    }

    @Override public void readAdditionalSaveData(CompoundTag input)
    {
        super.readAdditionalSaveData(input);
        this.ticksLiving = input.contains("ticksLiving") ? input.getInt("ticksLiving") :0;
    }

    @Override protected float getShardDamage() {return 7.5F;}

    @Override protected ItemStack getPickupItem() {return new ItemStack(AerialHellBlocksAndItems.VOLUCITE_SHARD.get());}
}