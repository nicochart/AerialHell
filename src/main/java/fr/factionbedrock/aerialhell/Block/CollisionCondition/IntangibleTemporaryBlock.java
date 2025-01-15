package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlockEntity
{
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = simpleCodec(IntangibleTemporaryBlock::new);

    public IntangibleTemporaryBlock(Properties properties) {super(properties);}

    @Override protected MapCodec<? extends IntangibleTemporaryBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new IntangibleTemporaryBlockEntity(pos, state);}

    @Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, 1.0F);
        if (level.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
        if (level.getBlockEntity(pos.above()) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
    }

    @Override public void nonLivingEntityInside(BlockState state, Level level, BlockPos pos, Entity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, 0.05);
    }

    @Override public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player)
    {
        List<ItemStack> drops = getBeforeStateDrops(state, level, pos, player);

        for (ItemStack stack : drops) {level.addFreshEntity(createItemEntitySupplier(level, pos, stack).get());}
        return super.playerWillDestroy(level, pos, state, player);
    }

    private static Supplier<ItemEntity> createItemEntitySupplier(Level level, BlockPos pos, ItemStack stack)
    {
        double dx = Mth.nextDouble(level.random, -0.1, 0.1);
        double dy = Mth.nextDouble(level.random, 0.0, 0.1);
        double dz = Mth.nextDouble(level.random, -0.1, 0.1);
        return () -> new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack, dx, dy, dz);
    }

    protected List<ItemStack> getBeforeStateDrops(BlockState state, Level level, BlockPos pos, Player player)
    {
        if (level instanceof ServerLevel serverlevel && level.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity intangibleBlockEntity && intangibleBlockEntity.getBeforeState() != null)
        {
            ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(intangibleBlockEntity.getBeforeState().getBlock());
            ResourceKey<LootTable> lootTable = ResourceKey.create(Registries.LOOT_TABLE, resourcelocation.withPrefix("blocks/"));
            LootParams lootparams = new LootParams.Builder(serverlevel).create(LootContextParamSets.EMPTY);
            ServerLevel lootparamserverlevel = lootparams.getLevel();
            LootTable loottable = lootparamserverlevel.getServer().reloadableRegistries().getLootTable(lootTable);
            return loottable.getRandomItems(lootparams);
        }
        return Collections.emptyList();
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK.get(), IntangibleTemporaryBlockEntity::tick);
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return CollisionConditionHalfTransparentBlock.FULL_COLLISION_SHAPE;}

    @Override protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {return 1.0F;}

    @Override protected boolean propagatesSkylightDown(BlockState state) {return true;}
}
