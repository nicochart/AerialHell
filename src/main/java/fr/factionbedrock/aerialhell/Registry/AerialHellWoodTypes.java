package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
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
    public static WoodType STELLAR_JUNGLE_TREE = createDefault(new ResourceLocation(MODID, "stellar_jungle_tree").toString());
    public static WoodType SHADOW_PINE = createDefault(new ResourceLocation(MODID, "shadow_pine").toString());
    public static WoodType SKY_CACTUS_FIBER = createDefault(new ResourceLocation(MODID, "sky_cactus_fiber").toString());
    public static WoodType GRAY_SHROOM = createComplete(new ResourceLocation(MODID, "gray_shroom").toString(), BlockSetType.OAK, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN);

    private static WoodType createDefault(String name) {return new WoodType(name, BlockSetType.OAK);}

    private static WoodType createComplete(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen)
    {
        return new WoodType(name, setType, soundType, hangingSignSoundType, fenceGateClose, fenceGateOpen);
    }

    public static void registerWoodTypes(final FMLClientSetupEvent event) //Client side
    {
        WoodType.register(AERIAL_TREE);
        WoodType.register(COPPER_PINE);
        WoodType.register(LAPIS_ROBINIA);
        WoodType.register(GOLDEN_BEECH);
        WoodType.register(STELLAR_JUNGLE_TREE);
        WoodType.register(SHADOW_PINE);
        WoodType.register(SKY_CACTUS_FIBER);
        WoodType.register(GRAY_SHROOM);
    }

    public static void addWoodTypesToSheets(final FMLClientSetupEvent event) //Client side too
    {
        event.enqueueWork(() -> {
            Sheets.addWoodType(AERIAL_TREE);
            Sheets.addWoodType(COPPER_PINE);
            Sheets.addWoodType(LAPIS_ROBINIA);
            Sheets.addWoodType(GOLDEN_BEECH);
            Sheets.addWoodType(STELLAR_JUNGLE_TREE);
            Sheets.addWoodType(SHADOW_PINE);
            Sheets.addWoodType(SKY_CACTUS_FIBER);
            Sheets.addWoodType(GRAY_SHROOM);
        });
    }
}
