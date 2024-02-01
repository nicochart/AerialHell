package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

@OnlyIn(Dist.CLIENT)
public class AerialHellChestMaterials
{
    public static final Material AERIAL_TREE_SINGLE = makeChestMaterial("aerial_tree", ChestType.SINGLE);
    public static final Material AERIAL_TREE_LEFT = makeChestMaterial("aerial_tree", ChestType.LEFT);
    public static final Material AERIAL_TREE_RIGHT = makeChestMaterial("aerial_tree", ChestType.RIGHT);
    public static final Material COPPER_PINE_SINGLE = makeChestMaterial("copper_pine", ChestType.SINGLE);
    public static final Material COPPER_PINE_LEFT = makeChestMaterial("copper_pine", ChestType.LEFT);
    public static final Material COPPER_PINE_RIGHT = makeChestMaterial("copper_pine", ChestType.RIGHT);
    public static final Material LAPIS_ROBINIA_SINGLE = makeChestMaterial("lapis_robinia", ChestType.SINGLE);
    public static final Material LAPIS_ROBINIA_LEFT = makeChestMaterial("lapis_robinia", ChestType.LEFT);
    public static final Material LAPIS_ROBINIA_RIGHT = makeChestMaterial("lapis_robinia", ChestType.RIGHT);
    public static final Material STELLAR_JUNGLE_TREE_SINGLE = makeChestMaterial("stellar_jungle_tree", ChestType.SINGLE);
    public static final Material STELLAR_JUNGLE_TREE_LEFT = makeChestMaterial("stellar_jungle_tree", ChestType.LEFT);
    public static final Material STELLAR_JUNGLE_TREE_RIGHT = makeChestMaterial("stellar_jungle_tree", ChestType.RIGHT);
    public static final Material GOLDEN_BEECH_SINGLE = makeChestMaterial("golden_beech", ChestType.SINGLE);
    public static final Material GOLDEN_BEECH_LEFT = makeChestMaterial("golden_beech", ChestType.LEFT);
    public static final Material GOLDEN_BEECH_RIGHT = makeChestMaterial("golden_beech", ChestType.RIGHT);
    public static final Material SHADOW_PINE_SINGLE = makeChestMaterial("shadow_pine", ChestType.SINGLE);
    public static final Material SHADOW_PINE_LEFT = makeChestMaterial("shadow_pine", ChestType.LEFT);
    public static final Material SHADOW_PINE_RIGHT = makeChestMaterial("shadow_pine", ChestType.RIGHT);
    public static final Material GRAY_SHROOM_SINGLE = makeChestMaterial("gray_shroom", ChestType.SINGLE);
    public static final Material GRAY_SHROOM_LEFT = makeChestMaterial("gray_shroom", ChestType.LEFT);
    public static final Material GRAY_SHROOM_RIGHT = makeChestMaterial("gray_shroom", ChestType.RIGHT);
    public static final Material SKY_CACTUS_FIBER_SINGLE = makeChestMaterial("sky_cactus_fiber", ChestType.SINGLE);
    public static final Material SKY_CACTUS_FIBER_LEFT = makeChestMaterial("sky_cactus_fiber", ChestType.LEFT);
    public static final Material SKY_CACTUS_FIBER_RIGHT = makeChestMaterial("sky_cactus_fiber", ChestType.RIGHT);
    public static final Material GHOST_BOAT_SINGLE = makeChestMaterial("ghost_boat", ChestType.SINGLE);
    public static final Material GHOST_BOAT_LEFT = makeChestMaterial("ghost_boat", ChestType.LEFT);
    public static final Material GHOST_BOAT_RIGHT = makeChestMaterial("ghost_boat", ChestType.RIGHT);
    public static final Material MUD_SINGLE = makeChestMaterial("mud", ChestType.SINGLE);
    public static final Material MUD_LEFT = makeChestMaterial("mud", ChestType.LEFT);
    public static final Material MUD_RIGHT = makeChestMaterial("mud", ChestType.RIGHT);
    public static final Material LUNATIC_SINGLE = makeChestMaterial("lunatic", ChestType.SINGLE);
    public static final Material LUNATIC_LEFT = makeChestMaterial("lunatic", ChestType.LEFT);
    public static final Material LUNATIC_RIGHT = makeChestMaterial("lunatic", ChestType.RIGHT);
    public static final Material GOLDEN_NETHER_SINGLE = makeChestMaterial("golden_nether", ChestType.SINGLE);
    public static final Material GOLDEN_NETHER_LEFT = makeChestMaterial("golden_nether", ChestType.LEFT);
    public static final Material GOLDEN_NETHER_RIGHT = makeChestMaterial("golden_nether", ChestType.RIGHT);
    public static final Material SHADOW_CATACOMBS_SINGLE = makeChestMaterial("shadow_catacombs", ChestType.SINGLE);
    public static final Material SHADOW_CATACOMBS_LEFT = makeChestMaterial("shadow_catacombs", ChestType.LEFT);
    public static final Material SHADOW_CATACOMBS_RIGHT = makeChestMaterial("shadow_catacombs", ChestType.RIGHT);
    public static final Material VOLUCITE_SINGLE = makeChestMaterial("volucite", ChestType.SINGLE);
    public static final Material VOLUCITE_LEFT = makeChestMaterial("volucite", ChestType.LEFT);
    public static final Material VOLUCITE_RIGHT = makeChestMaterial("volucite", ChestType.RIGHT);

    private static Material makeChestMaterial(String name, ChestType type)
    {
        return switch (type)
        {
            case LEFT -> new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/chest/" + name + "/" + name + "_left"));
            case RIGHT -> new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/chest/" + name + "/" + name + "_right"));
            default -> new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/chest/" + name + "/" + name));
        };
    }
}