package fr.factionbedrock.aerialhell.Entity.Projectile.ResonatorShard;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractResonatorShardEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RubyShardEntity extends AbstractResonatorShardEntity
{
    public RubyShardEntity(EntityType<RubyShardEntity> type, Level level) {super(type, level);}

    @Override protected float getShardDamage() {return 4.5F;}

    @Override protected ItemStack getDefaultPickupItem() {return new ItemStack(AerialHellItems.RUBY_SHARD.get());}
}