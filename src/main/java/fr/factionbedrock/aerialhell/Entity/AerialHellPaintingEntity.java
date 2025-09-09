package fr.factionbedrock.aerialhell.Entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AerialHellPaintingEntity extends HangingEntity
{
    private static final EntityDataAccessor<Holder<PaintingVariant>> DATA_PAINTING_VARIANT_ID = SynchedEntityData.defineId(AerialHellPaintingEntity.class, EntityDataSerializers.PAINTING_VARIANT);
    public static final float DEPTH = 0.0625F;

    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> entityType, Level level) {super(entityType, level);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        builder.define(DATA_PAINTING_VARIANT_ID, this.registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT).getAny().orElseThrow());
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key)
    {
        if (DATA_PAINTING_VARIANT_ID.equals(key)) {this.recalculateBoundingBox();}
    }

    public void setVariant(Holder<PaintingVariant> variant) {this.entityData.set(DATA_PAINTING_VARIANT_ID, variant);}

    public Holder<PaintingVariant> getVariant() {return this.entityData.get(DATA_PAINTING_VARIANT_ID);}

    @Override @Nullable
    public <T> T get(DataComponentType<? extends T> dataComponent)
    {
        return (T)(dataComponent == DataComponents.PAINTING_VARIANT ? castComponentValue(dataComponent, this.getVariant()) : super.get(dataComponent));
    }

    @Override protected void applyImplicitComponents(DataComponentGetter dataComponentGetter)
    {
        this.applyImplicitComponentIfPresent(dataComponentGetter, DataComponents.PAINTING_VARIANT);
        super.applyImplicitComponents(dataComponentGetter);
    }

    @Override protected <T> boolean applyImplicitComponent(DataComponentType<T> dataComponentType, T value)
    {
        if (dataComponentType == DataComponents.PAINTING_VARIANT)
        {
            this.setVariant((Holder)castComponentValue(DataComponents.PAINTING_VARIANT, value));
            return true;
        }
        else
        {
            return super.applyImplicitComponent(dataComponentType, value);
        }
    }

    public static Optional<AerialHellPaintingEntity> create(Level level, BlockPos pos, Direction direction)
    {
        AerialHellPaintingEntity painting = new AerialHellPaintingEntity(level, pos);
        List<Holder<PaintingVariant>> list = new ArrayList<>();
        level.registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT).getTagOrEmpty(AerialHellTags.PaintingVariants.PLACEABLE).forEach(list::add);
        if (list.isEmpty()) {return Optional.empty();}
        else
        {
            painting.setDirection(direction);
            list.removeIf(p_344343_ ->
            {
                painting.setVariant((Holder<PaintingVariant>)p_344343_);
                return !painting.survives();
            });
            if (list.isEmpty()) {return Optional.empty();}
            else
            {
                int i = list.stream().mapToInt(AerialHellPaintingEntity::variantArea).max().orElse(0);
                list.removeIf(p_218883_ -> variantArea((Holder<PaintingVariant>)p_218883_) < i);
                Optional<Holder<PaintingVariant>> optional = Util.getRandomSafe(list, painting.getRandom());
                if (optional.isEmpty()) {return Optional.empty();}
                else
                {
                    painting.setVariant(optional.get());
                    painting.setDirection(direction);
                    return Optional.of(painting);
                }
            }
        }
    }

    private static int variantArea(Holder<PaintingVariant> variant) {return variant.value().area();}
    private AerialHellPaintingEntity(Level level, BlockPos pos) {super(EntityType.PAINTING, level, pos);}

    public AerialHellPaintingEntity(Level level, BlockPos pos, Direction direction, Holder<PaintingVariant> variant)
    {
        this(level, pos);
        this.setVariant(variant);
        this.setDirection(direction);
    }

    @Override protected void addAdditionalSaveData(ValueOutput valueOutput)
    {
        valueOutput.store("facing", Direction.LEGACY_ID_CODEC_2D, this.getDirection());
        super.addAdditionalSaveData(valueOutput);
        VariantUtils.writeVariant(valueOutput, this.getVariant());
    }

    @Override protected void readAdditionalSaveData(ValueInput valueInput)
    {
        Direction direction = (Direction)valueInput.read("facing", Direction.LEGACY_ID_CODEC_2D).orElse(Direction.SOUTH);
        super.readAdditionalSaveData(valueInput);
        this.setDirection(direction);
        VariantUtils.readVariant(valueInput, Registries.PAINTING_VARIANT).ifPresent(this::setVariant);
    }

    @Override protected AABB calculateBoundingBox(BlockPos pos, Direction direction)
    {
        Vec3 vec3 = Vec3.atCenterOf(pos).relative(direction, -0.46875);
        PaintingVariant paintingvariant = this.getVariant().value();
        double d0 = this.offsetForPaintingSize(paintingvariant.width());
        double d1 = this.offsetForPaintingSize(paintingvariant.height());
        Direction counterClockWiseDirection = direction.getCounterClockWise();
        Vec3 vec31 = vec3.relative(counterClockWiseDirection, d0).relative(Direction.UP, d1);
        Direction.Axis direction$axis = direction.getAxis();
        double d2 = direction$axis == Direction.Axis.X ? 0.0625 : (double)paintingvariant.width();
        double d3 = (double)paintingvariant.height();
        double d4 = direction$axis == Direction.Axis.Z ? 0.0625 : (double)paintingvariant.width();
        return AABB.ofSize(vec31, d2, d3, d4);
    }

    private double offsetForPaintingSize(int dimension) {
        return dimension % 2 == 0 ? 0.5 : 0.0;
    }

    @Override public void dropItem(ServerLevel serverLevel, @Nullable Entity brokenEntity)
    {
        if (serverLevel.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
        {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (brokenEntity instanceof Player player && player.hasInfiniteMaterials()) {return;}
            this.spawnAtLocation(serverLevel, AerialHellItems.AERIAL_HELL_PAINTING.get());
        }
    }

    @Override public void playPlacementSound() {this.playSound(SoundEvents.PAINTING_PLACE, 1.0F, 1.0F);}
    @Override public void snapTo(double pX, double pY, double pZ, float pYaw, float pPitch) {this.setPos(pX, pY, pZ);}

    @Override public Vec3 trackingPosition() {return Vec3.atLowerCornerOf(this.pos);}

    @Override public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_352062_) {return new ClientboundAddEntityPacket(this, this.getDirection().get3DDataValue(), this.getPos());}

    @Override public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket)
    {
        super.recreateFromPacket(clientboundAddEntityPacket);
        this.setDirection(Direction.from3DDataValue(clientboundAddEntityPacket.getData()));
    }

    @Override public ItemStack getPickResult() {return new ItemStack(AerialHellItems.AERIAL_HELL_PAINTING.get());}
}
