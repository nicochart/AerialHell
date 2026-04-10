package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.Mixin.ChestVariantMixin;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;

public class AerialHellChestVariants
{
    public static final ChestRenderState.ChestMaterialType AERIAL_TREE = create(8, "aerial_tree");
    public static final ChestRenderState.ChestMaterialType COPPER_PINE = create(9, "copper_pine");
    public static final ChestRenderState.ChestMaterialType LAPIS_ROBINIA = create(10, "lapis_robinia");
    public static final ChestRenderState.ChestMaterialType STELLAR_JUNGLE_TREE = create(11, "stellar_jungle_tree");
    public static final ChestRenderState.ChestMaterialType GOLDEN_BEECH = create(12, "golden_beech");
    public static final ChestRenderState.ChestMaterialType SHADOW_PINE = create(13, "shadow_pine");
    public static final ChestRenderState.ChestMaterialType GRAY_SHROOM = create(14, "gray_shroom");
    public static final ChestRenderState.ChestMaterialType SKY_CACTUS_FIBER = create(15, "sky_cactus_fiber");
    public static final ChestRenderState.ChestMaterialType GHOST_BOAT = create(16, "ghost_boat");
    public static final ChestRenderState.ChestMaterialType MUD = create(17, "mud");
    public static final ChestRenderState.ChestMaterialType LUNATIC = create(18, "lunatic");
    public static final ChestRenderState.ChestMaterialType GOLDEN_NETHER = create(19, "golden_nether");
    public static final ChestRenderState.ChestMaterialType SHADOW_CATACOMBS = create(20, "shadow_catacombs");
    public static final ChestRenderState.ChestMaterialType VOLUCITE = create(21, "volucite");

    private static ChestRenderState.ChestMaterialType create(int index, String name)
    {
        return ChestVariantMixin.invokeConstructor(name, index);
    }
}
