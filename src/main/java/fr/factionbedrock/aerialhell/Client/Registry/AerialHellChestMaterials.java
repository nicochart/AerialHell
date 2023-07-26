package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    /*@SubscribeEvent TODO : Use atlas info JSON files instead
    public static void addMaterialSprites(TextureStitchEvent.Pre event)
    {
        if (event.getAtlas().location().equals(Sheets.CHEST_SHEET))
        {
            event.addSprite(AERIAL_TREE_SINGLE.texture());
            event.addSprite(AERIAL_TREE_LEFT.texture());
            event.addSprite(AERIAL_TREE_RIGHT.texture());
            event.addSprite(COPPER_PINE_SINGLE.texture());
            event.addSprite(COPPER_PINE_LEFT.texture());
            event.addSprite(COPPER_PINE_RIGHT.texture());
            event.addSprite(LAPIS_ROBINIA_SINGLE.texture());
            event.addSprite(LAPIS_ROBINIA_LEFT.texture());
            event.addSprite(LAPIS_ROBINIA_RIGHT.texture());
            event.addSprite(GOLDEN_BEECH_SINGLE.texture());
            event.addSprite(GOLDEN_BEECH_LEFT.texture());
            event.addSprite(GOLDEN_BEECH_RIGHT.texture());
            event.addSprite(SHADOW_PINE_SINGLE.texture());
            event.addSprite(SHADOW_PINE_LEFT.texture());
            event.addSprite(SHADOW_PINE_RIGHT.texture());
            event.addSprite(GRAY_SHROOM_SINGLE.texture());
            event.addSprite(GRAY_SHROOM_LEFT.texture());
            event.addSprite(GRAY_SHROOM_RIGHT.texture());
            event.addSprite(SKY_CACTUS_FIBER_SINGLE.texture());
            event.addSprite(SKY_CACTUS_FIBER_LEFT.texture());
            event.addSprite(SKY_CACTUS_FIBER_RIGHT.texture());
            event.addSprite(MUD_SINGLE.texture());
            event.addSprite(MUD_LEFT.texture());
            event.addSprite(MUD_RIGHT.texture());
            event.addSprite(LUNATIC_SINGLE.texture());
            event.addSprite(LUNATIC_LEFT.texture());
            event.addSprite(LUNATIC_RIGHT.texture());
            event.addSprite(GOLDEN_NETHER_SINGLE.texture());
            event.addSprite(GOLDEN_NETHER_LEFT.texture());
            event.addSprite(GOLDEN_NETHER_RIGHT.texture());
            event.addSprite(SHADOW_CATACOMBS_SINGLE.texture());
            event.addSprite(SHADOW_CATACOMBS_LEFT.texture());
            event.addSprite(SHADOW_CATACOMBS_RIGHT.texture());
            event.addSprite(VOLUCITE_SINGLE.texture());
            event.addSprite(VOLUCITE_LEFT.texture());
            event.addSprite(VOLUCITE_RIGHT.texture());
        }
    }*/
}