package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static net.minecraft.client.render.TexturedRenderLayers.*;

public class AerialHellWoodTypes
{
    public static WoodType AERIAL_TREE = createDefault("aerial_tree"); //TODO does it work ?
    public static WoodType COPPER_PINE = createDefault("copper_pine");
    public static WoodType LAPIS_ROBINIA = createDefault("lapis_robinia");
    public static WoodType GOLDEN_BEECH = createDefault("golden_beech");
    public static WoodType STELLAR_JUNGLE_TREE = createDefault("stellar_jungle_tree");
    public static WoodType SHADOW_PINE = createDefault("shadow_pine");
    public static WoodType SKY_CACTUS_FIBER = createDefault("sky_cactus_fiber");
    public static WoodType GRAY_SHROOM = createComplete("gray_shroom", BlockSetType.OAK, BlockSoundGroup.NETHER_WOOD, BlockSoundGroup.NETHER_WOOD_HANGING_SIGN, SoundEvents.BLOCK_NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.BLOCK_NETHER_WOOD_FENCE_GATE_OPEN);

    private static WoodType createDefault(String name) {return new WoodType(name, BlockSetType.OAK);}

    private static WoodType createComplete(String name, BlockSetType setType, BlockSoundGroup soundType, BlockSoundGroup hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen)
    {
        return new WoodType(name, setType, soundType, hangingSignSoundType, fenceGateClose, fenceGateOpen);
    }

    public static void registerWoodTypes()
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

    public static void addWoodTypesToSheets()
    {
        addWoodTypeToSheets(AERIAL_TREE);
        addWoodTypeToSheets(COPPER_PINE);
        addWoodTypeToSheets(LAPIS_ROBINIA);
        addWoodTypeToSheets(GOLDEN_BEECH);
        addWoodTypeToSheets(STELLAR_JUNGLE_TREE);
        addWoodTypeToSheets(SHADOW_PINE);
        addWoodTypeToSheets(SKY_CACTUS_FIBER);
        addWoodTypeToSheets(GRAY_SHROOM);
    }

    public static void addWoodTypeToSheets(WoodType woodType)
    {
        SIGN_TYPE_TEXTURES.put(woodType, createSignTextureId(woodType));
        HANGING_SIGN_TYPE_TEXTURES.put(woodType, createHangingSignTextureId(woodType));
    }

    //copies of methods of same name from net.minecraft.client.render.TexturedRenderLayers
    private static SpriteIdentifier createSignTextureId(WoodType type) {return new SpriteIdentifier(SIGNS_ATLAS_TEXTURE, AerialHell.id("entity/signs/" + type.name()));}
    private static SpriteIdentifier createHangingSignTextureId(WoodType type) {return new SpriteIdentifier(SIGNS_ATLAS_TEXTURE, AerialHell.id("entity/signs/hanging/" + type.name()));}
}
