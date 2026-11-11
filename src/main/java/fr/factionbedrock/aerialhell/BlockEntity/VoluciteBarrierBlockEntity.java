package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import org.jetbrains.annotations.Nullable;

//no data
//only used for rendering (see VoluciteBarrierRenderer)
public class VoluciteBarrierBlockEntity extends BlockEntity
{
    public static final int ALL_FACES = (1 << 6) - 1;
    private static final int UNKNOWN_FACES = -1;
    //client side cache of the faces to render : one bit per direction (1 << Direction.get3DDataValue()). computed when needed, forgotten when a neighbor changes
    private int visibleFaces = UNKNOWN_FACES;

    public VoluciteBarrierBlockEntity(BlockPos pos, BlockState state) {super(AerialHellBlockEntities.VOLUCITE_BARRIER.get(), pos, state);}

    public static boolean hasFace(int faces, Direction direction) {return (faces & (1 << direction.get3DDataValue())) != 0;}

    //faces to render (bit mask, see hasFace)
    public int getVisibleFaces()
    {
        if (this.visibleFaces != UNKNOWN_FACES) {return this.visibleFaces;}
        int faces = this.computeVisibleFaces();
        if (this.areNeighborsLoaded()) {this.visibleFaces = faces;} //not cached while a neighbor chunk is not loaded : its blocks would look like air
        return faces;
    }

    //same rule as the block model faces : no face against another barrier (or against an opaque block), a wall of barriers shows only its outer faces
    private int computeVisibleFaces()
    {
        Level level = this.getLevel();
        if (level == null) {return ALL_FACES;}
        int faces = 0;
        for (Direction direction : Direction.values())
        {
            BlockState neighborState = level.getBlockState(this.getBlockPos().relative(direction));
            if (Block.shouldRenderFace(level, this.getBlockPos(), this.getBlockState(), neighborState, direction)) {faces |= 1 << direction.get3DDataValue();}
        }
        return faces;
    }

    private boolean areNeighborsLoaded()
    {
        Level level = this.getLevel();
        if (level == null) {return false;}
        for (Direction direction : Direction.values())
        {
            BlockPos neighborPos = this.getBlockPos().relative(direction);
            if (!level.isOutsideBuildHeight(neighborPos) && !level.isLoaded(neighborPos)) {return false;}
        }
        return true;
    }

    //a neighbor changed (see VoluciteBarrierBlock.updateShape & neighborChanged). server side : the client is told to compute its visible faces again (see onDataPacket)
    public void onNeighborUpdate()
    {
        this.visibleFaces = UNKNOWN_FACES;
        if (this.level != null && !this.level.isClientSide()) {this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);}
    }

    @Nullable @Override public Packet<ClientGamePacketListener> getUpdatePacket() {return ClientboundBlockEntityDataPacket.create(this);}

    //no data in the packet : it only tells the client that a neighbor changed
    @Override public void onDataPacket(Connection connection, ValueInput valueInput) {this.visibleFaces = UNKNOWN_FACES;}
}
