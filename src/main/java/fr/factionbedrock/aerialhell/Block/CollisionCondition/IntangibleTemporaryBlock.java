package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class IntangibleTemporaryBlock extends CollisionConditionHalfTransparentBlockEntity
{
    public static final MapCodec<IntangibleTemporaryBlock> CODEC = simpleCodec(IntangibleTemporaryBlock::new);
    private final int lifetime = 1;
    private int tickcount;

    public IntangibleTemporaryBlock(Properties properties)
    {
        super(properties);
        this.tickcount = 0;
    }

    @Override protected MapCodec<? extends BaseEntityBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new IntangibleTemporaryBlockEntity(pos, state);}

    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        this.tickcount++;
        if (this.tickcount >= this.lifetime) {level.setBlock(pos, this.getNextBlockState(level, pos), 2);}
    }

    @Override protected boolean canEntityCollide(Entity entity) {return false;}

    @Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}

    public void resetTickCount() {this.tickcount = 0;}

    private BlockState getNextBlockState(LevelAccessor level, BlockPos pos)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity)
        {
            BlockState beforeState = intangibleblockentity.getBeforeState();
            return beforeState == null || beforeState.getBlock() == AerialHellBlocksAndItems.INTANGIBLE_TEMPORARY_BLOCK.get() ? Blocks.AIR.defaultBlockState() : beforeState;
        }
        else {return Blocks.AIR.defaultBlockState();}
    }
}
