package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlockEntity
{
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = simpleCodec(IntangibleTemporaryBlock::new);

    public IntangibleTemporaryBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override protected MapCodec<? extends IntangibleTemporaryBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new IntangibleTemporaryBlockEntity(pos, state);}

    @Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, 1.0F);
        if (world.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
        if (world.getBlockEntity(pos.above()) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
    }

    @Override public void nonLivingEntityInside(BlockState state, Level world, BlockPos pos, Entity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, 0.05);
    }

    @Override public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        List<ItemStack> drops = getBeforeStateDrops(state, world, pos, player);

        for (ItemStack stack : drops) {world.addFreshEntity(createItemEntitySupplier(world, pos, stack).get());}
        return super.playerWillDestroy(world, pos, state, player);
    }

    private static Supplier<ItemEntity> createItemEntitySupplier(Level world, BlockPos pos, ItemStack stack)
    {
        double dx = Mth.nextDouble(world.getRandom(), -0.1, 0.1);
        double dy = Mth.nextDouble(world.getRandom(), 0.0, 0.1);
        double dz = Mth.nextDouble(world.getRandom(), -0.1, 0.1);
        return () -> new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack, dx, dy, dz);
    }

    protected List<ItemStack> getBeforeStateDrops(BlockState state, Level world, BlockPos pos, Player player)
    {
        if (world instanceof ServerLevel serverworld && world.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity intangibleBlockEntity && intangibleBlockEntity.getBeforeState() != null)
        {
            //copy of net.minecraft.block.AbstractBlock.getLootTableKey()
            Identifier identifier = BuiltInRegistries.BLOCK.getKey(intangibleBlockEntity.getBeforeState().getBlock());
            ResourceKey<LootTable> lootTable = ResourceKey.create(Registries.LOOT_TABLE, identifier.withPrefix("blocks/"));
            LootParams lootparams = new LootParams.Builder(serverworld).create(LootContextParamSets.EMPTY);
            ServerLevel lootparamserverlevel = lootparams.getLevel();
            LootTable loottable = lootparamserverlevel.getServer().reloadableRegistries().getLootTable(lootTable);
            return loottable.getRandomItems(lootparams);
        }
        return Collections.emptyList();
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide() ? null : createTickerHelper(type, AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK, IntangibleTemporaryBlockEntity::tick);
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return CollisionConditionHalfTransparentBlock.FULL_COLLISION_SHAPE;}

    @Override protected float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {return 1.0F;}

    @Override protected boolean propagatesSkylightDown(BlockState state) {return true;}
}
