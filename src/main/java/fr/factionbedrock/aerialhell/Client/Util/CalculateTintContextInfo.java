package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.util.math.BlockPos;

public class CalculateTintContextInfo
{
    public final BlockPos pos;
    public final boolean shiftedRender;

    public CalculateTintContextInfo(BlockPos pos) {this(pos, BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind());}
    public CalculateTintContextInfo(BlockPos pos, boolean shiftedRender) {this.pos = pos; this.shiftedRender = shiftedRender;}
}
