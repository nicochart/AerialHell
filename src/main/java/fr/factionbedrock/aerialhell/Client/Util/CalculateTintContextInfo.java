package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class CalculateTintContextInfo
{
    public final BlockPos pos;
    public final boolean shiftedRender;
    @Nullable public final Holder<Biome> biome;

    public CalculateTintContextInfo(BlockPos pos) {this(pos, BlocksAndItemsColorHandler.isShadowBindEnabled());}
    public CalculateTintContextInfo(BlockPos pos, boolean shiftedRender) {this(pos, shiftedRender, null);}
    public CalculateTintContextInfo(BlockPos pos, boolean shiftedRender, @Nullable Holder<Biome> biome) {this.pos = pos; this.shiftedRender = shiftedRender; this.biome = biome;}
}
