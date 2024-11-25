package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ShadowBarsBlock extends PaneBlock
{
    protected final static VoxelShape EMPTY_SHAPE = VoxelShapes.empty();
    public ShadowBarsBlock(AbstractBlock.Settings settings) {super(settings.solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false));}

    @Override public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (context instanceof EntityShapeContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity instanceof ShadowTrollEntity || entity instanceof ShadowAutomatonEntity || entity instanceof LilithEntity) {return EMPTY_SHAPE;}
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (context instanceof EntityShapeContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity instanceof ShadowTrollEntity || entity instanceof ShadowAutomatonEntity || entity instanceof LilithEntity) {return EMPTY_SHAPE;}
        }
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override public boolean canPathfindThrough(BlockState state, NavigationType type) {return true;}
}
