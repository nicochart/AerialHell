package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellWoodTypes
{
    public static WoodType AERIAL_TREE = createDefault(new ResourceLocation(MODID, "aerial_tree").toString());
    public static WoodType COPPER_PINE = createDefault(new ResourceLocation(MODID, "copper_pine").toString());
    public static WoodType LAPIS_ROBINIA = createDefault(new ResourceLocation(MODID, "lapis_robinia").toString());
    public static WoodType GOLDEN_BEECH = createDefault(new ResourceLocation(MODID, "golden_beech").toString());
    public static WoodType SHADOW_PINE = createDefault(new ResourceLocation(MODID, "shadow_pine").toString());
    public static WoodType SKY_CACTUS_FIBER = createDefault(new ResourceLocation(MODID, "sky_cactus_fiber").toString());
    public static WoodType GRAY_SHROOM = createDefault(new ResourceLocation(MODID, "gray_shroom").toString());

    private static WoodType createDefault(String name)
    {
        return new WoodType(name, BlockSetType.OAK);
    }

    public static void registerWoodTypes(final FMLClientSetupEvent event) //Client side
    {
        WoodType.register(AERIAL_TREE);
        WoodType.register(COPPER_PINE);
        WoodType.register(LAPIS_ROBINIA);
        WoodType.register(GOLDEN_BEECH);
        WoodType.register(SHADOW_PINE);
        WoodType.register(SKY_CACTUS_FIBER);
        WoodType.register(GRAY_SHROOM);
    }

    public static void addWoodTypesToSheets() //Client & Server side
    {
        Sheets.addWoodType(AERIAL_TREE);
        Sheets.addWoodType(COPPER_PINE);
        Sheets.addWoodType(LAPIS_ROBINIA);
        Sheets.addWoodType(GOLDEN_BEECH);
        Sheets.addWoodType(SHADOW_PINE);
        Sheets.addWoodType(SKY_CACTUS_FIBER);
        Sheets.addWoodType(GRAY_SHROOM);
    }
}
