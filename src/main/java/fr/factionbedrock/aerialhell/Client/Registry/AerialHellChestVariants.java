package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.Mixin.ChestVariantMixin;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;

public class AerialHellChestVariants
{
    public static final ChestBlockEntityRenderState.Variant AERIAL_TREE = create(8, "aerial_tree");
    public static final ChestBlockEntityRenderState.Variant COPPER_PINE = create(9, "copper_pine");
    public static final ChestBlockEntityRenderState.Variant LAPIS_ROBINIA = create(10, "lapis_robinia");
    public static final ChestBlockEntityRenderState.Variant STELLAR_JUNGLE_TREE = create(11, "stellar_jungle_tree");
    public static final ChestBlockEntityRenderState.Variant GOLDEN_BEECH = create(12, "golden_beech");
    public static final ChestBlockEntityRenderState.Variant SHADOW_PINE = create(13, "shadow_pine");
    public static final ChestBlockEntityRenderState.Variant GRAY_SHROOM = create(14, "gray_shroom");
    public static final ChestBlockEntityRenderState.Variant SKY_CACTUS_FIBER = create(15, "sky_cactus_fiber");
    public static final ChestBlockEntityRenderState.Variant GHOST_BOAT = create(16, "ghost_boat");
    public static final ChestBlockEntityRenderState.Variant MUD = create(17, "mud");
    public static final ChestBlockEntityRenderState.Variant LUNATIC = create(18, "lunatic");
    public static final ChestBlockEntityRenderState.Variant GOLDEN_NETHER = create(19, "golden_nether");
    public static final ChestBlockEntityRenderState.Variant SHADOW_CATACOMBS = create(20, "shadow_catacombs");
    public static final ChestBlockEntityRenderState.Variant VOLUCITE = create(21, "volucite");

    private static ChestBlockEntityRenderState.Variant create(int index, String name)
    {
        return ChestVariantMixin.invokeConstructor(name, index);
    }
}
