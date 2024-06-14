package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellPaintingVariants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class AerialHellPaintingEntity extends HangingEntity implements VariantHolder<Holder<PaintingVariant>>
{
    public static final EntityDataAccessor<Holder<PaintingVariant>> DATA_PAINTING_VARIANT_ID = SynchedEntityData.defineId(AerialHellPaintingEntity.class, EntityDataSerializers.PAINTING_VARIANT);
    private static final ResourceKey<PaintingVariant> DEFAULT_VARIANT = PaintingVariants.KEBAB;
    public static final String VARIANT_TAG = "variant";

    public AerialHellPaintingEntity(EntityType<? extends AerialHellPaintingEntity> type, Level worldIn) {super(type, worldIn);}
    private AerialHellPaintingEntity(Level level, BlockPos pos) {super(AerialHellEntities.AERIAL_HELL_PAINTING.get(), level, pos);}
    public AerialHellPaintingEntity(Level level, BlockPos pos, Direction direction, Holder<PaintingVariant> variant) {this(level, pos); this.setVariant(variant); this.setDirection(direction);}

    private static Holder<PaintingVariant> getDefaultVariant() {return AerialHellPaintingVariants.SPOOKY_ISLANDS.getHolder().get();}

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {builder.define(DATA_PAINTING_VARIANT_ID, getDefaultVariant());}

    @Override public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor)
    {
        if (DATA_PAINTING_VARIANT_ID.equals(dataAccessor)) {this.recalculateBoundingBox();}
    }

    @Override public Holder<PaintingVariant> getVariant() {return this.entityData.get(DATA_PAINTING_VARIANT_ID);}
    @Override public void setVariant(Holder<PaintingVariant> variant) {this.entityData.set(DATA_PAINTING_VARIANT_ID, variant);}

    public static Optional<AerialHellPaintingEntity> create(Level level, BlockPos pos, Direction direction)
    {
        AerialHellPaintingEntity painting = new AerialHellPaintingEntity(level, pos);
        List<Holder<PaintingVariant>> list = AerialHellPaintingVariants.getAerialHellPaintingVariantList();
        if (list.isEmpty()) {return Optional.empty();}
        else
        {
            painting.setDirection(direction);
            list.removeIf((variant) -> {painting.setVariant(variant); return !painting.survives();});
            if (list.isEmpty()) {return Optional.empty();}
            else
            {
                int i = list.stream().mapToInt(AerialHellPaintingEntity::variantArea).max().orElse(0);
                list.removeIf((variant) -> {return variantArea(variant) < i;});
                Optional<Holder<PaintingVariant>> optional = Util.getRandomSafe(list, painting.random);
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

    private static int variantArea(Holder<PaintingVariant> variant) {return variant.value().getWidth() * variant.value().getHeight();}

    public void addAdditionalSaveData(CompoundTag tag)
    {
        storeVariant(tag, this.getVariant());
        tag.putByte("facing", (byte)this.direction.get2DDataValue());
        super.addAdditionalSaveData(tag);
    }

    public void readAdditionalSaveData(CompoundTag tag)
    {
        Holder<PaintingVariant> holder = loadVariant(tag).orElseGet(AerialHellPaintingEntity::getDefaultVariant);
        this.setVariant(holder);
        this.direction = Direction.from2DDataValue(tag.getByte("facing"));
        super.readAdditionalSaveData(tag);
        this.setDirection(this.direction);
    }

    public static void storeVariant(CompoundTag tag, Holder<PaintingVariant> variant) {tag.putString("variant", variant.unwrapKey().orElse(DEFAULT_VARIANT).location().toString());}

    public static Optional<Holder<PaintingVariant>> loadVariant(CompoundTag tag)
    {
        return Optional.ofNullable(ResourceLocation.tryParse(tag.getString("variant"))).map((resourceLocation) -> {return ResourceKey.create(Registries.PAINTING_VARIANT, resourceLocation);}).flatMap(BuiltInRegistries.PAINTING_VARIANT::getHolder);
    }

    @Override public int getWidth() {return this.getVariant().value().getWidth();}
    @Override public int getHeight() {return this.getVariant().value().getHeight();}

    @Override
    public void dropItem(@Nullable Entity entity)
    {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (entity instanceof Player)
            {
                Player player = (Player)entity;
                if (player.getAbilities().instabuild) {return;}
            }
            this.spawnAtLocation(AerialHellBlocksAndItems.AERIAL_HELL_PAINTING.get());
        }
    }

    @Override public void playPlacementSound() {this.playSound(SoundEvents.PAINTING_PLACE, 1.0F, 1.0F);}
    @Override public void moveTo(double x, double y, double z, float yrot, float xrot) {this.setPos(x, y, z);}
    @Override public void lerpTo(double x, double y, double z, float yrot, float xrot, int lerpSteps) {this.setPos(x, y, z);}
    @Override public Vec3 trackingPosition() {return Vec3.atLowerCornerOf(this.pos);}
    @Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return new ClientboundAddEntityPacket(this, this.direction.get3DDataValue(), this.getPos());}

    @Override public void recreateFromPacket(ClientboundAddEntityPacket packet)
    {
        super.recreateFromPacket(packet);
        this.setDirection(Direction.from3DDataValue(packet.getData()));
    }

    @Override public ItemStack getPickResult() {return new ItemStack(AerialHellBlocksAndItems.AERIAL_HELL_PAINTING.get());}
}
