package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.CorruptionProtectorBlock;
import fr.factionbedrock.aerialhell.Inventory.Menu.ProtectorMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CorruptionProtectorBlockEntity extends BaseContainerBlockEntity
{
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static int MAX_PROTECTION_DISTANCE = 100;
    private int active_timer;
    private int protection_distance;

    public CorruptionProtectorBlockEntity(BlockPos pos, BlockState blockState, int protectionDistance)
    {
        super(AerialHellBlockEntities.CORRUPTION_PROTECTOR_BLOCK.get(), pos, blockState);
        this.protection_distance = protectionDistance;
        this.active_timer = 0;
    }

    @Override @NotNull protected Component getDefaultName()
    {
        return Component.translatable("container." + AerialHell.MODID + ".protector");
    }

    @Override protected AbstractContainerMenu createMenu(int id, @NotNull Inventory inv) {return new ProtectorMenu(id, inv, this);}

    public static void tick(Level level, BlockPos pos, BlockState state, CorruptionProtectorBlockEntity blockEntity)
    {
        boolean isActive = blockEntity.active_timer > 0;
        if (isActive) {blockEntity.active_timer--;}
        else if (!blockEntity.items.isEmpty())
        {
            ItemStack stack = blockEntity.items.get(0);
            if (OscillatorBlockEntity.getOscillatingMap().containsKey(stack.getItem()))
            {
                blockEntity.active_timer += 10 * OscillatorBlockEntity.getOscillatingMap().get(stack.getItem());
                stack.shrink(1);
            }
        }

        int protection_distance = (int) ((isActive ? 1.0F : 0.25F) * blockEntity.getProtectionDistance()); RandomSource rand = level.random;
        int try_number = (int) (protection_distance * protection_distance * protection_distance * 1.0F/8.0F);
        for (int i=0; i<try_number; i++)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(-protection_distance, protection_distance), rand.nextInt(-protection_distance, protection_distance), rand.nextInt(-protection_distance, protection_distance));
            if (blockEntity.isValidProtectorForPos(level, blockpos, pos) && BlockHelper.isCorrupted(level, blockpos) && level instanceof ServerLevel serverlevel)
            {
                BlockHelper.uncorrupt(serverlevel, blockpos);
            }
        }
    }

    public boolean isValidProtectorForPos(LevelReader level, BlockPos posToProtect, BlockPos protectorPos)
    {
        return level.getBlockState(protectorPos).getBlock() instanceof CorruptionProtectorBlock && protectorPos.distSqr(posToProtect) < getProtectionDistance() * getProtectionDistance();
    }

    public int getProtectionDistance() {return Math.min(Math.max(0, protection_distance), MAX_PROTECTION_DISTANCE);}

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putInt("protection_distance", protection_distance);
        tag.putInt("active_timer", active_timer);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.protection_distance = tag.getInt("protection_distance");
        this.active_timer = tag.getInt("active_timer");
    }

    @Override public NonNullList<ItemStack> getItems() {return this.items;}
    @Override protected void setItems(NonNullList<ItemStack> items) {this.items = items;}
    @Override public int getContainerSize() {return this.items.size();}
}
