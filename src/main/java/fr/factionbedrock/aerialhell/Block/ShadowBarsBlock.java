package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShadowBarsBlock extends IronBarsBlock
{
    protected final static VoxelShape EMPTY_SHAPE = Shapes.empty();
    public ShadowBarsBlock(BlockBehaviour.Properties settings) {super(settings.isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false));}

    @Override public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity instanceof ShadowTrollEntity || entity instanceof ShadowAutomatonEntity || entity instanceof LilithEntity) {return EMPTY_SHAPE;}
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity instanceof ShadowTrollEntity || entity instanceof ShadowAutomatonEntity || entity instanceof LilithEntity) {return EMPTY_SHAPE;}
        }
        return super.getShape(state, world, pos, context);
    }

    @Override public boolean isPathfindable(BlockState state, PathComputationType type) {return true;}
}
