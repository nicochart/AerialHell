package fr.factionbedrock.aerialhell.Entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.PaintingVariantTags;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AerialHellPaintingEntity extends AbstractDecorationEntity implements VariantHolder<RegistryEntry<PaintingVariant>>
{
    private static final TrackedData<RegistryEntry<PaintingVariant>> DATA_PAINTING_VARIANT_ID = DataTracker.registerData(AerialHellPaintingEntity.class, TrackedDataHandlerRegistry.PAINTING_VARIANT);
    public static final MapCodec<RegistryEntry<PaintingVariant>> VARIANT_MAP_CODEC = PaintingVariant.ENTRY_CODEC.fieldOf("variant");
    public static final Codec<RegistryEntry<PaintingVariant>> VARIANT_CODEC = VARIANT_MAP_CODEC.codec();
    public static final float DEPTH = 0.0625F;

    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> entityType, World world) {super(entityType, world);}

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        builder.add(DATA_PAINTING_VARIANT_ID, this.getRegistryManager().get(RegistryKeys.PAINTING_VARIANT).getDefaultEntry().orElseThrow());
    }

    @Override public void onTrackedDataSet(TrackedData<?> data)
    {
        if (DATA_PAINTING_VARIANT_ID.equals(data)) {this.updateAttachmentPosition();}
    }

    public void setVariant(RegistryEntry<PaintingVariant> variant) {this.dataTracker.set(DATA_PAINTING_VARIANT_ID, variant);}
    public RegistryEntry<PaintingVariant> getVariant() {return this.dataTracker.get(DATA_PAINTING_VARIANT_ID);}

    public static Optional<AerialHellPaintingEntity> create(World world, BlockPos pos, Direction facing)
    {
        AerialHellPaintingEntity paintingEntity = new AerialHellPaintingEntity(world, pos);
        List<RegistryEntry<PaintingVariant>> list = new ArrayList();
        world.getRegistryManager().get(RegistryKeys.PAINTING_VARIANT).iterateEntries(AerialHellTags.PaintingVariants.PLACEABLE).forEach(list::add);
        if (list.isEmpty()) {return Optional.empty();}
        else
        {
            paintingEntity.setFacing(facing);
            list.removeIf(variant ->
            {
                paintingEntity.setVariant(variant);
                return !paintingEntity.canStayAttached();
            });
            if (list.isEmpty()) {return Optional.empty();}
            else
            {
                int i = list.stream().mapToInt(AerialHellPaintingEntity::variantArea).max().orElse(0);
                list.removeIf(variant -> variantArea(variant) < i);
                Optional<RegistryEntry<PaintingVariant>> optional = Util.getRandomOrEmpty(list, paintingEntity.random);
                if (optional.isEmpty()) {return Optional.empty();}
                else
                {
                    paintingEntity.setVariant((RegistryEntry<PaintingVariant>)optional.get());
                    paintingEntity.setFacing(facing);
                    return Optional.of(paintingEntity);
                }
            }
        }
    }

    private static int variantArea(RegistryEntry<PaintingVariant> variant) {return variant.value().getArea();}
    private AerialHellPaintingEntity(World world, BlockPos pos) {super(EntityType.PAINTING, world, pos);}

    public AerialHellPaintingEntity(World world, BlockPos pos, Direction direction, RegistryEntry<PaintingVariant> variant)
    {
        this(world, pos);
        this.setVariant(variant);
        this.setFacing(direction);
    }

    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        VARIANT_CODEC.encodeStart(this.getRegistryManager().getOps(NbtOps.INSTANCE), this.getVariant())
                .ifSuccess(nbtElement -> nbt.copyFrom((NbtCompound)nbtElement));
        nbt.putByte("facing", (byte)this.facing.getHorizontal());
        super.writeCustomDataToNbt(nbt);
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        VARIANT_CODEC.parse(this.getRegistryManager().getOps(NbtOps.INSTANCE), nbt).ifSuccess(this::setVariant);
        this.facing = Direction.fromHorizontal(nbt.getByte("facing"));
        super.readCustomDataFromNbt(nbt);
        this.setFacing(this.facing);
    }

    @Override protected Box calculateBoundingBox(BlockPos pos, Direction side)
    {
        float f = 0.46875F;
        Vec3d vec3d = Vec3d.ofCenter(pos).offset(side, -0.46875);
        PaintingVariant paintingVariant = this.getVariant().value();
        double d = this.offsetForPaintingSize(paintingVariant.width());
        double e = this.offsetForPaintingSize(paintingVariant.height());
        Direction direction = side.rotateYCounterclockwise();
        Vec3d vec3d2 = vec3d.offset(direction, d).offset(Direction.UP, e);
        Direction.Axis axis = side.getAxis();
        double g = axis == Direction.Axis.X ? 0.0625 : (double)paintingVariant.width();
        double h = paintingVariant.height();
        double i = axis == Direction.Axis.Z ? 0.0625 : (double)paintingVariant.width();
        return Box.of(vec3d2, g, h, i);
    }

    private double offsetForPaintingSize(int dimension) {return dimension % 2 == 0 ? 0.5 : 0.0;}

    @Override public void onBreak(@Nullable Entity breaker)
    {
        if (this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
        {
            this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);
            if (breaker instanceof PlayerEntity playerEntity && playerEntity.isInCreativeMode()) {return;}
            this.dropItem(AerialHellItems.AERIAL_HELL_PAINTING);
        }
    }

    @Override public void onPlace() {this.playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);}

    @Override public void refreshPositionAndAngles(double x, double y, double z, float yaw, float pitch) {this.setPosition(x, y, z);}

    @Override public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {this.setPosition(x, y, z);}

    @Override public Vec3d getSyncedPos() {return Vec3d.of(this.attachedBlockPos);}

    @Override public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {return new EntitySpawnS2CPacket(this, this.facing.getId(), this.getAttachedBlockPos());}

    @Override public void onSpawnPacket(EntitySpawnS2CPacket packet)
    {
        super.onSpawnPacket(packet);
        this.setFacing(Direction.byId(packet.getEntityData()));
    }

    @Override public ItemStack getPickBlockStack() {return new ItemStack(AerialHellItems.AERIAL_HELL_PAINTING);}
}
