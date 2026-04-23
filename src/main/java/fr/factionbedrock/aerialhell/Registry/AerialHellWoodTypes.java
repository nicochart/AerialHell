package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.client.renderer.Sheets.*;

public class AerialHellWoodTypes
{
    public static WoodType AERIAL_TREE = createDefault("aerial_tree"); //TODO does it work ?
    public static WoodType COPPER_PINE = createDefault("copper_pine");
    public static WoodType LAPIS_ROBINIA = createDefault("lapis_robinia");
    public static WoodType GOLDEN_BEECH = createDefault("golden_beech");
    public static WoodType STELLAR_JUNGLE_TREE = createDefault("stellar_jungle_tree");
    public static WoodType SHADOW_PINE = createDefault("shadow_pine");
    public static WoodType SKY_CACTUS_FIBER = createDefault("sky_cactus_fiber");
    public static WoodType GRAY_SHROOM = createComplete("gray_shroom", BlockSetType.OAK, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN);

    private static WoodType createDefault(String name) {return new WoodType(name, BlockSetType.OAK);}

    private static WoodType createComplete(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen)
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
        SIGN_SPRITES.put(woodType, createSignSprite(woodType));
        HANGING_SIGN_SPRITES.put(woodType, createHangingSignSprite(woodType));
    }

    //copies of methods of same name from net.minecraft.client.renderer.Sheets
    private static SpriteId createSignSprite(final WoodType type) {return SIGN_MAPPER.apply(AerialHell.id(type.name()));}
    private static SpriteId createHangingSignSprite(final WoodType type) {return HANGING_SIGN_MAPPER.apply(AerialHell.id(type.name()));}
}
