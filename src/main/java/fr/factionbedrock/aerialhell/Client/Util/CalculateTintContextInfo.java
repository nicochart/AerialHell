package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

public class CalculateTintContextInfo
{
    public final BlockAndTintGetter level;
    public final BlockPos pos;
    public final boolean shiftedRender;
    @Nullable public final Holder<Biome> biome;

    public CalculateTintContextInfo(BlockAndTintGetter level, BlockPos pos) {this(level, pos, BlocksAndItemsColorHandler.isShadowBindEnabled());}
    public CalculateTintContextInfo(BlockAndTintGetter level, BlockPos pos, boolean shiftedRender) {this(level, pos, shiftedRender, null);}
    public CalculateTintContextInfo(BlockAndTintGetter level, BlockPos pos, boolean shiftedRender, @Nullable Holder<Biome> biome) {this.level = level; this.pos = pos; this.shiftedRender = shiftedRender; this.biome = biome;}
}
