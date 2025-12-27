package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlockEntity
{
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = createCodec(IntangibleTemporaryBlock::new);

    public IntangibleTemporaryBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected MapCodec<? extends IntangibleTemporaryBlock> getCodec() {return CODEC;}

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new IntangibleTemporaryBlockEntity(pos, state);}

    @Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_living_entity_xz_delta_movement_factor, 1.0F);
        if (world.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
        if (world.getBlockEntity(pos.up()) instanceof IntangibleTemporaryBlockEntity blockentity) {blockentity.resetTickCount();}
    }

    @Override public void nonLivingEntityInside(BlockState state, World world, BlockPos pos, Entity entity)
    {
        EntityHelper.multiplyDeltaMovement(entity, default_non_living_entity_xz_delta_movement_factor, 0.05);
    }

    @Override public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        List<ItemStack> drops = getBeforeStateDrops(state, world, pos, player);

        for (ItemStack stack : drops) {world.spawnEntity(createItemEntitySupplier(world, pos, stack).get());}
        return super.onBreak(world, pos, state, player);
    }

    private static Supplier<ItemEntity> createItemEntitySupplier(World world, BlockPos pos, ItemStack stack)
    {
        double dx = MathHelper.nextDouble(world.random, -0.1, 0.1);
        double dy = MathHelper.nextDouble(world.random, 0.0, 0.1);
        double dz = MathHelper.nextDouble(world.random, -0.1, 0.1);
        return () -> new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack, dx, dy, dz);
    }

    protected List<ItemStack> getBeforeStateDrops(BlockState state, World world, BlockPos pos, PlayerEntity player)
    {
        if (world instanceof ServerWorld serverworld && world.getBlockEntity(pos) instanceof IntangibleTemporaryBlockEntity intangibleBlockEntity && intangibleBlockEntity.getBeforeState() != null)
        {
            //copy of net.minecraft.block.AbstractBlock.getLootTableKey()
            Identifier identifier = Registries.BLOCK.getId(intangibleBlockEntity.getBeforeState().getBlock());
            RegistryKey<LootTable> lootTable = RegistryKey.of(RegistryKeys.LOOT_TABLE, identifier.withPrefixedPath("blocks/"));
            LootWorldContext lootparams = new LootWorldContext.Builder(serverworld).build(LootContextTypes.EMPTY);
            ServerWorld lootparamserverlevel = lootparams.getWorld();
            LootTable loottable = lootparamserverlevel.getServer().getReloadableRegistries().getLootTable(lootTable);
            return loottable.generateLoot(lootparams);
        }
        return Collections.emptyList();
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient() ? null : validateTicker(type, AerialHellBlockEntities.INTANGIBLE_TEMPORARY_BLOCK, IntangibleTemporaryBlockEntity::tick);
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return CollisionConditionHalfTransparentBlock.FULL_COLLISION_SHAPE;}

    @Override protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {return 1.0F;}

    @Override protected boolean isTransparent(BlockState state) {return true;}
}
