package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

public class AerialHellChestMaterials
{
    public static final Material AERIAL_TREE_SINGLE = makeChestSpriteIdentifier("aerial_tree", ChestType.SINGLE);
    public static final Material AERIAL_TREE_LEFT = makeChestSpriteIdentifier("aerial_tree", ChestType.LEFT);
    public static final Material AERIAL_TREE_RIGHT = makeChestSpriteIdentifier("aerial_tree", ChestType.RIGHT);
    public static final Material COPPER_PINE_SINGLE = makeChestSpriteIdentifier("copper_pine", ChestType.SINGLE);
    public static final Material COPPER_PINE_LEFT = makeChestSpriteIdentifier("copper_pine", ChestType.LEFT);
    public static final Material COPPER_PINE_RIGHT = makeChestSpriteIdentifier("copper_pine", ChestType.RIGHT);
    public static final Material LAPIS_ROBINIA_SINGLE = makeChestSpriteIdentifier("lapis_robinia", ChestType.SINGLE);
    public static final Material LAPIS_ROBINIA_LEFT = makeChestSpriteIdentifier("lapis_robinia", ChestType.LEFT);
    public static final Material LAPIS_ROBINIA_RIGHT = makeChestSpriteIdentifier("lapis_robinia", ChestType.RIGHT);
    public static final Material STELLAR_JUNGLE_TREE_SINGLE = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.SINGLE);
    public static final Material STELLAR_JUNGLE_TREE_LEFT = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.LEFT);
    public static final Material STELLAR_JUNGLE_TREE_RIGHT = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.RIGHT);
    public static final Material GOLDEN_BEECH_SINGLE = makeChestSpriteIdentifier("golden_beech", ChestType.SINGLE);
    public static final Material GOLDEN_BEECH_LEFT = makeChestSpriteIdentifier("golden_beech", ChestType.LEFT);
    public static final Material GOLDEN_BEECH_RIGHT = makeChestSpriteIdentifier("golden_beech", ChestType.RIGHT);
    public static final Material SHADOW_PINE_SINGLE = makeChestSpriteIdentifier("shadow_pine", ChestType.SINGLE);
    public static final Material SHADOW_PINE_LEFT = makeChestSpriteIdentifier("shadow_pine", ChestType.LEFT);
    public static final Material SHADOW_PINE_RIGHT = makeChestSpriteIdentifier("shadow_pine", ChestType.RIGHT);
    public static final Material GRAY_SHROOM_SINGLE = makeChestSpriteIdentifier("gray_shroom", ChestType.SINGLE);
    public static final Material GRAY_SHROOM_LEFT = makeChestSpriteIdentifier("gray_shroom", ChestType.LEFT);
    public static final Material GRAY_SHROOM_RIGHT = makeChestSpriteIdentifier("gray_shroom", ChestType.RIGHT);
    public static final Material SKY_CACTUS_FIBER_SINGLE = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.SINGLE);
    public static final Material SKY_CACTUS_FIBER_LEFT = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.LEFT);
    public static final Material SKY_CACTUS_FIBER_RIGHT = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.RIGHT);
    public static final Material GHOST_BOAT_SINGLE = makeChestSpriteIdentifier("ghost_boat", ChestType.SINGLE);
    public static final Material GHOST_BOAT_LEFT = makeChestSpriteIdentifier("ghost_boat", ChestType.LEFT);
    public static final Material GHOST_BOAT_RIGHT = makeChestSpriteIdentifier("ghost_boat", ChestType.RIGHT);
    public static final Material MUD_SINGLE = makeChestSpriteIdentifier("mud", ChestType.SINGLE);
    public static final Material MUD_LEFT = makeChestSpriteIdentifier("mud", ChestType.LEFT);
    public static final Material MUD_RIGHT = makeChestSpriteIdentifier("mud", ChestType.RIGHT);
    public static final Material LUNATIC_SINGLE = makeChestSpriteIdentifier("lunatic", ChestType.SINGLE);
    public static final Material LUNATIC_LEFT = makeChestSpriteIdentifier("lunatic", ChestType.LEFT);
    public static final Material LUNATIC_RIGHT = makeChestSpriteIdentifier("lunatic", ChestType.RIGHT);
    public static final Material GOLDEN_NETHER_SINGLE = makeChestSpriteIdentifier("golden_nether", ChestType.SINGLE);
    public static final Material GOLDEN_NETHER_LEFT = makeChestSpriteIdentifier("golden_nether", ChestType.LEFT);
    public static final Material GOLDEN_NETHER_RIGHT = makeChestSpriteIdentifier("golden_nether", ChestType.RIGHT);
    public static final Material SHADOW_CATACOMBS_SINGLE = makeChestSpriteIdentifier("shadow_catacombs", ChestType.SINGLE);
    public static final Material SHADOW_CATACOMBS_LEFT = makeChestSpriteIdentifier("shadow_catacombs", ChestType.LEFT);
    public static final Material SHADOW_CATACOMBS_RIGHT = makeChestSpriteIdentifier("shadow_catacombs", ChestType.RIGHT);
    public static final Material VOLUCITE_SINGLE = makeChestSpriteIdentifier("volucite", ChestType.SINGLE);
    public static final Material VOLUCITE_LEFT = makeChestSpriteIdentifier("volucite", ChestType.LEFT);
    public static final Material VOLUCITE_RIGHT = makeChestSpriteIdentifier("volucite", ChestType.RIGHT);

    private static Material makeChestSpriteIdentifier(String name, ChestType type)
    {
        return switch (type)
        {
            case LEFT -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name + "_left"));
            case RIGHT -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name + "_right"));
            default -> new Material(CHEST_SHEET, AerialHell.id("entity/chest/" + name + "/" + name));
        };
    }
}