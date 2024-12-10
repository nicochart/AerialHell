package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.util.SpriteIdentifier;

import static net.minecraft.client.render.TexturedRenderLayers.CHEST_ATLAS_TEXTURE;

public class AerialHellChestMaterials
{
    public static final SpriteIdentifier AERIAL_TREE_SINGLE = makeChestSpriteIdentifier("aerial_tree", ChestType.SINGLE);
    public static final SpriteIdentifier AERIAL_TREE_LEFT = makeChestSpriteIdentifier("aerial_tree", ChestType.LEFT);
    public static final SpriteIdentifier AERIAL_TREE_RIGHT = makeChestSpriteIdentifier("aerial_tree", ChestType.RIGHT);
    public static final SpriteIdentifier COPPER_PINE_SINGLE = makeChestSpriteIdentifier("copper_pine", ChestType.SINGLE);
    public static final SpriteIdentifier COPPER_PINE_LEFT = makeChestSpriteIdentifier("copper_pine", ChestType.LEFT);
    public static final SpriteIdentifier COPPER_PINE_RIGHT = makeChestSpriteIdentifier("copper_pine", ChestType.RIGHT);
    public static final SpriteIdentifier LAPIS_ROBINIA_SINGLE = makeChestSpriteIdentifier("lapis_robinia", ChestType.SINGLE);
    public static final SpriteIdentifier LAPIS_ROBINIA_LEFT = makeChestSpriteIdentifier("lapis_robinia", ChestType.LEFT);
    public static final SpriteIdentifier LAPIS_ROBINIA_RIGHT = makeChestSpriteIdentifier("lapis_robinia", ChestType.RIGHT);
    public static final SpriteIdentifier STELLAR_JUNGLE_TREE_SINGLE = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.SINGLE);
    public static final SpriteIdentifier STELLAR_JUNGLE_TREE_LEFT = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.LEFT);
    public static final SpriteIdentifier STELLAR_JUNGLE_TREE_RIGHT = makeChestSpriteIdentifier("stellar_jungle_tree", ChestType.RIGHT);
    public static final SpriteIdentifier GOLDEN_BEECH_SINGLE = makeChestSpriteIdentifier("golden_beech", ChestType.SINGLE);
    public static final SpriteIdentifier GOLDEN_BEECH_LEFT = makeChestSpriteIdentifier("golden_beech", ChestType.LEFT);
    public static final SpriteIdentifier GOLDEN_BEECH_RIGHT = makeChestSpriteIdentifier("golden_beech", ChestType.RIGHT);
    public static final SpriteIdentifier SHADOW_PINE_SINGLE = makeChestSpriteIdentifier("shadow_pine", ChestType.SINGLE);
    public static final SpriteIdentifier SHADOW_PINE_LEFT = makeChestSpriteIdentifier("shadow_pine", ChestType.LEFT);
    public static final SpriteIdentifier SHADOW_PINE_RIGHT = makeChestSpriteIdentifier("shadow_pine", ChestType.RIGHT);
    public static final SpriteIdentifier GRAY_SHROOM_SINGLE = makeChestSpriteIdentifier("gray_shroom", ChestType.SINGLE);
    public static final SpriteIdentifier GRAY_SHROOM_LEFT = makeChestSpriteIdentifier("gray_shroom", ChestType.LEFT);
    public static final SpriteIdentifier GRAY_SHROOM_RIGHT = makeChestSpriteIdentifier("gray_shroom", ChestType.RIGHT);
    public static final SpriteIdentifier SKY_CACTUS_FIBER_SINGLE = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.SINGLE);
    public static final SpriteIdentifier SKY_CACTUS_FIBER_LEFT = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.LEFT);
    public static final SpriteIdentifier SKY_CACTUS_FIBER_RIGHT = makeChestSpriteIdentifier("sky_cactus_fiber", ChestType.RIGHT);
    public static final SpriteIdentifier GHOST_BOAT_SINGLE = makeChestSpriteIdentifier("ghost_boat", ChestType.SINGLE);
    public static final SpriteIdentifier GHOST_BOAT_LEFT = makeChestSpriteIdentifier("ghost_boat", ChestType.LEFT);
    public static final SpriteIdentifier GHOST_BOAT_RIGHT = makeChestSpriteIdentifier("ghost_boat", ChestType.RIGHT);
    public static final SpriteIdentifier MUD_SINGLE = makeChestSpriteIdentifier("mud", ChestType.SINGLE);
    public static final SpriteIdentifier MUD_LEFT = makeChestSpriteIdentifier("mud", ChestType.LEFT);
    public static final SpriteIdentifier MUD_RIGHT = makeChestSpriteIdentifier("mud", ChestType.RIGHT);
    public static final SpriteIdentifier LUNATIC_SINGLE = makeChestSpriteIdentifier("lunatic", ChestType.SINGLE);
    public static final SpriteIdentifier LUNATIC_LEFT = makeChestSpriteIdentifier("lunatic", ChestType.LEFT);
    public static final SpriteIdentifier LUNATIC_RIGHT = makeChestSpriteIdentifier("lunatic", ChestType.RIGHT);
    public static final SpriteIdentifier GOLDEN_NETHER_SINGLE = makeChestSpriteIdentifier("golden_nether", ChestType.SINGLE);
    public static final SpriteIdentifier GOLDEN_NETHER_LEFT = makeChestSpriteIdentifier("golden_nether", ChestType.LEFT);
    public static final SpriteIdentifier GOLDEN_NETHER_RIGHT = makeChestSpriteIdentifier("golden_nether", ChestType.RIGHT);
    public static final SpriteIdentifier SHADOW_CATACOMBS_SINGLE = makeChestSpriteIdentifier("shadow_catacombs", ChestType.SINGLE);
    public static final SpriteIdentifier SHADOW_CATACOMBS_LEFT = makeChestSpriteIdentifier("shadow_catacombs", ChestType.LEFT);
    public static final SpriteIdentifier SHADOW_CATACOMBS_RIGHT = makeChestSpriteIdentifier("shadow_catacombs", ChestType.RIGHT);
    public static final SpriteIdentifier VOLUCITE_SINGLE = makeChestSpriteIdentifier("volucite", ChestType.SINGLE);
    public static final SpriteIdentifier VOLUCITE_LEFT = makeChestSpriteIdentifier("volucite", ChestType.LEFT);
    public static final SpriteIdentifier VOLUCITE_RIGHT = makeChestSpriteIdentifier("volucite", ChestType.RIGHT);

    private static SpriteIdentifier makeChestSpriteIdentifier(String name, ChestType type)
    {
        return switch (type)
        {
            case LEFT -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, AerialHell.id("entity/chest/" + name + "/" + name + "_left"));
            case RIGHT -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, AerialHell.id("entity/chest/" + name + "/" + name + "_right"));
            default -> new SpriteIdentifier(CHEST_ATLAS_TEXTURE, AerialHell.id("entity/chest/" + name + "/" + name));
        };
    }
}