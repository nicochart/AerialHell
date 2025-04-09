package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.core.BlockPos;

public class CalculateTintContextInfo
{
    public final BlockPos pos;
    public final boolean shiftedRender;

    public CalculateTintContextInfo(BlockPos pos) {this(pos, BlocksAndItemsColorHandler.isShadowBindEnabled());}
    public CalculateTintContextInfo(BlockPos pos, boolean shiftedRender) {this.pos = pos; this.shiftedRender = shiftedRender;}
}
