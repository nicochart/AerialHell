package fr.factionbedrock.aerialhell.Entity.Monster.Snake;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WormEntity extends AbstractSnakeEntity
{
    public WormEntity(EntityType<? extends WormEntity> type, World world) {super(type, world);}

    @Override protected BodyPartDeathReaction getBodyPartDeathReaction() {return BodyPartDeathReaction.SPLIT_IF_NOT_HEAD;}
    @Override protected UniformIntProvider getLength() {return UniformIntProvider.create(10,24);}
    @Override protected int getMinLength() {return 2;}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D);
    }

    @Nullable @Override protected SoundEvent getAmbientSound(){return this.isHead() ? AerialHellSoundEvents.ENTITY_WORM_AMBIENT : null;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_WORM_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WORM_DEATH;}
}
