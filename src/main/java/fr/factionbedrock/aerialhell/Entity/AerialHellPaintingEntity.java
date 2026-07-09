package fr.factionbedrock.aerialhell.Entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AerialHellPaintingEntity extends HangingEntity implements VariantHolder<Holder<PaintingVariant>>
{
    private static final EntityDataAccessor<Holder<PaintingVariant>> DATA_PAINTING_VARIANT_ID = SynchedEntityData.defineId(AerialHellPaintingEntity.class, EntityDataSerializers.PAINTING_VARIANT);
    public static final MapCodec<Holder<PaintingVariant>> VARIANT_MAP_CODEC = PaintingVariant.CODEC.fieldOf("variant");
    public static final Codec<Holder<PaintingVariant>> VARIANT_CODEC = VARIANT_MAP_CODEC.codec();
    public static final float DEPTH = 0.0625F;

    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> entityType, Level world) {super(entityType, world);}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        builder.define(DATA_PAINTING_VARIANT_ID, this.registryAccess().registryOrThrow(Registries.PAINTING_VARIANT).getAny().orElseThrow());
    }

    @Override public void onSyncedDataUpdated(EntityDataAccessor<?> data)
    {
        if (DATA_PAINTING_VARIANT_ID.equals(data)) {this.recalculateBoundingBox();}
    }

    public void setVariant(Holder<PaintingVariant> variant) {this.entityData.set(DATA_PAINTING_VARIANT_ID, variant);}
    public Holder<PaintingVariant> getVariant() {return this.entityData.get(DATA_PAINTING_VARIANT_ID);}

    public static Optional<AerialHellPaintingEntity> create(Level world, BlockPos pos, Direction facing)
    {
        AerialHellPaintingEntity paintingEntity = new AerialHellPaintingEntity(world, pos);
        List<Holder<PaintingVariant>> list = new ArrayList();
        world.registryAccess().registryOrThrow(Registries.PAINTING_VARIANT).getTagOrEmpty(AerialHellTags.PaintingVariants.PLACEABLE).forEach(list::add);
        if (list.isEmpty()) {return Optional.empty();}
        else
        {
            paintingEntity.setDirection(facing);
            list.removeIf(variant ->
            {
                paintingEntity.setVariant(variant);
                return !paintingEntity.survives();
            });
            if (list.isEmpty()) {return Optional.empty();}
            else
            {
                int i = list.stream().mapToInt(AerialHellPaintingEntity::variantArea).max().orElse(0);
                list.removeIf(variant -> variantArea(variant) < i);
                Optional<Holder<PaintingVariant>> optional = Util.getRandomSafe(list, paintingEntity.random);
                if (optional.isEmpty()) {return Optional.empty();}
                else
                {
                    paintingEntity.setVariant((Holder<PaintingVariant>)optional.get());
                    paintingEntity.setDirection(facing);
                    return Optional.of(paintingEntity);
                }
            }
        }
    }

    private static int variantArea(Holder<PaintingVariant> variant) {return variant.value().area();}
    private AerialHellPaintingEntity(Level world, BlockPos pos) {super(EntityType.PAINTING, world, pos);}

    public AerialHellPaintingEntity(Level world, BlockPos pos, Direction direction, Holder<PaintingVariant> variant)
    {
        this(world, pos);
        this.setVariant(variant);
        this.setDirection(direction);
    }

    @Override public void addAdditionalSaveData(CompoundTag nbt)
    {
        VARIANT_CODEC.encodeStart(this.registryAccess().createSerializationContext(NbtOps.INSTANCE), this.getVariant())
                .ifSuccess(nbtElement -> nbt.merge((CompoundTag)nbtElement));
        nbt.putByte("facing", (byte)this.direction.get2DDataValue());
        super.addAdditionalSaveData(nbt);
    }

    @Override public void readAdditionalSaveData(CompoundTag nbt)
    {
        VARIANT_CODEC.parse(this.registryAccess().createSerializationContext(NbtOps.INSTANCE), nbt).ifSuccess(this::setVariant);
        this.direction = Direction.from2DDataValue(nbt.getByte("facing"));
        super.readAdditionalSaveData(nbt);
        this.setDirection(this.direction);
    }

    @Override protected AABB calculateBoundingBox(BlockPos pos, Direction side)
    {
        float f = 0.46875F;
        Vec3 vec3d = Vec3.atCenterOf(pos).relative(side, -0.46875);
        PaintingVariant paintingVariant = this.getVariant().value();
        double d = this.offsetForPaintingSize(paintingVariant.width());
        double e = this.offsetForPaintingSize(paintingVariant.height());
        Direction direction = side.getCounterClockWise();
        Vec3 vec3d2 = vec3d.relative(direction, d).relative(Direction.UP, e);
        Direction.Axis axis = side.getAxis();
        double g = axis == Direction.Axis.X ? 0.0625 : (double)paintingVariant.width();
        double h = paintingVariant.height();
        double i = axis == Direction.Axis.Z ? 0.0625 : (double)paintingVariant.width();
        return AABB.ofSize(vec3d2, g, h, i);
    }

    private double offsetForPaintingSize(int dimension) {return dimension % 2 == 0 ? 0.5 : 0.0;}

    @Override public void dropItem(@Nullable Entity breaker)
    {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
        {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (breaker instanceof Player playerEntity && playerEntity.hasInfiniteMaterials()) {return;}
            this.spawnAtLocation(AerialHellItems.AERIAL_HELL_PAINTING);
        }
    }

    @Override public void playPlacementSound() {this.playSound(SoundEvents.PAINTING_PLACE, 1.0F, 1.0F);}

    @Override public void moveTo(double x, double y, double z, float yaw, float pitch) {this.setPos(x, y, z);}

    @Override public void lerpTo(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {this.setPos(x, y, z);}

    @Override public Vec3 trackingPosition() {return Vec3.atLowerCornerOf(this.pos);}

    @Override public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityTrackerEntry) {return new ClientboundAddEntityPacket(this, this.direction.get3DDataValue(), this.getPos());}

    @Override public void recreateFromPacket(ClientboundAddEntityPacket packet)
    {
        super.recreateFromPacket(packet);
        this.setDirection(Direction.from3DDataValue(packet.getData()));
    }

    @Override public ItemStack getPickResult() {return new ItemStack(AerialHellItems.AERIAL_HELL_PAINTING);}
}
