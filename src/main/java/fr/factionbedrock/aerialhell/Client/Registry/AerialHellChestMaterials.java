package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

public class AerialHellChestMaterials
{
    public static final Material AERIAL_TREE_SINGLE = makeChestSpriteResourceLocation("aerial_tree", ChestType.SINGLE);
    public static final Material AERIAL_TREE_LEFT = makeChestSpriteResourceLocation("aerial_tree", ChestType.LEFT);
    public static final Material AERIAL_TREE_RIGHT = makeChestSpriteResourceLocation("aerial_tree", ChestType.RIGHT);
    public static final Material COPPER_PINE_SINGLE = makeChestSpriteResourceLocation("copper_pine", ChestType.SINGLE);
    public static final Material COPPER_PINE_LEFT = makeChestSpriteResourceLocation("copper_pine", ChestType.LEFT);
    public static final Material COPPER_PINE_RIGHT = makeChestSpriteResourceLocation("copper_pine", ChestType.RIGHT);
    public static final Material LAPIS_ROBINIA_SINGLE = makeChestSpriteResourceLocation("lapis_robinia", ChestType.SINGLE);
    public static final Material LAPIS_ROBINIA_LEFT = makeChestSpriteResourceLocation("lapis_robinia", ChestType.LEFT);
    public static final Material LAPIS_ROBINIA_RIGHT = makeChestSpriteResourceLocation("lapis_robinia", ChestType.RIGHT);
    public static final Material STELLAR_JUNGLE_TREE_SINGLE = makeChestSpriteResourceLocation("stellar_jungle_tree", ChestType.SINGLE);
    public static final Material STELLAR_JUNGLE_TREE_LEFT = makeChestSpriteResourceLocation("stellar_jungle_tree", ChestType.LEFT);
    public static final Material STELLAR_JUNGLE_TREE_RIGHT = makeChestSpriteResourceLocation("stellar_jungle_tree", ChestType.RIGHT);
    public static final Material GOLDEN_BEECH_SINGLE = makeChestSpriteResourceLocation("golden_beech", ChestType.SINGLE);
    public static final Material GOLDEN_BEECH_LEFT = makeChestSpriteResourceLocation("golden_beech", ChestType.LEFT);
    public static final Material GOLDEN_BEECH_RIGHT = makeChestSpriteResourceLocation("golden_beech", ChestType.RIGHT);
    public static final Material SHADOW_PINE_SINGLE = makeChestSpriteResourceLocation("shadow_pine", ChestType.SINGLE);
    public static final Material SHADOW_PINE_LEFT = makeChestSpriteResourceLocation("shadow_pine", ChestType.LEFT);
    public static final Material SHADOW_PINE_RIGHT = makeChestSpriteResourceLocation("shadow_pine", ChestType.RIGHT);
    public static final Material GRAY_SHROOM_SINGLE = makeChestSpriteResourceLocation("gray_shroom", ChestType.SINGLE);
    public static final Material GRAY_SHROOM_LEFT = makeChestSpriteResourceLocation("gray_shroom", ChestType.LEFT);
    public static final Material GRAY_SHROOM_RIGHT = makeChestSpriteResourceLocation("gray_shroom", ChestType.RIGHT);
    public static final Material SKY_CACTUS_FIBER_SINGLE = makeChestSpriteResourceLocation("sky_cactus_fiber", ChestType.SINGLE);
    public static final Material SKY_CACTUS_FIBER_LEFT = makeChestSpriteResourceLocation("sky_cactus_fiber", ChestType.LEFT);
    public static final Material SKY_CACTUS_FIBER_RIGHT = makeChestSpriteResourceLocation("sky_cactus_fiber", ChestType.RIGHT);
    public static final Material GHOST_BOAT_SINGLE = makeChestSpriteResourceLocation("ghost_boat", ChestType.SINGLE);
    public static final Material GHOST_BOAT_LEFT = makeChestSpriteResourceLocation("ghost_boat", ChestType.LEFT);
    public static final Material GHOST_BOAT_RIGHT = makeChestSpriteResourceLocation("ghost_boat", ChestType.RIGHT);
    public static final Material MUD_SINGLE = makeChestSpriteResourceLocation("mud", ChestType.SINGLE);
    public static final Material MUD_LEFT = makeChestSpriteResourceLocation("mud", ChestType.LEFT);
    public static final Material MUD_RIGHT = makeChestSpriteResourceLocation("mud", ChestType.RIGHT);
    public static final Material LUNATIC_SINGLE = makeChestSpriteResourceLocation("lunatic", ChestType.SINGLE);
    public static final Material LUNATIC_LEFT = makeChestSpriteResourceLocation("lunatic", ChestType.LEFT);
    public static final Material LUNATIC_RIGHT = makeChestSpriteResourceLocation("lunatic", ChestType.RIGHT);
    public static final Material GOLDEN_NETHER_SINGLE = makeChestSpriteResourceLocation("golden_nether", ChestType.SINGLE);
    public static final Material GOLDEN_NETHER_LEFT = makeChestSpriteResourceLocation("golden_nether", ChestType.LEFT);
    public static final Material GOLDEN_NETHER_RIGHT = makeChestSpriteResourceLocation("golden_nether", ChestType.RIGHT);
    public static final Material SHADOW_CATACOMBS_SINGLE = makeChestSpriteResourceLocation("shadow_catacombs", ChestType.SINGLE);
    public static final Material SHADOW_CATACOMBS_LEFT = makeChestSpriteResourceLocation("shadow_catacombs", ChestType.LEFT);
    public static final Material SHADOW_CATACOMBS_RIGHT = makeChestSpriteResourceLocation("shadow_catacombs", ChestType.RIGHT);
    public static final Material VOLUCITE_SINGLE = makeChestSpriteResourceLocation("volucite", ChestType.SINGLE);
    public static final Material VOLUCITE_LEFT = makeChestSpriteResourceLocation("volucite", ChestType.LEFT);
    public static final Material VOLUCITE_RIGHT = makeChestSpriteResourceLocation("volucite", ChestType.RIGHT);

    private static Material makeChestSpriteResourceLocation(String name, ChestType type)
    {
        return switch (type)
        {
            case LEFT -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name + "_left"));
            case RIGHT -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name + "_right"));
            default -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name));
        };
    }
}