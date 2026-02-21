package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Item.*;
import fr.factionbedrock.aerialhell.Item.Armor.ShadowArmorItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyBucketItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyLiquidOfGodsBucketItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyWaterBucketItem;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterials;
import fr.factionbedrock.aerialhell.Item.Material.ToolMaterials;
import fr.factionbedrock.aerialhell.Item.Shuriken.*;
import fr.factionbedrock.aerialhell.Item.Tools.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellJukeboxSongs;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTrimMaterials;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.block.ComposterBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

public class AerialHellItems
{
    public static void registerCompostableItems()
    {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_GRASS, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_TALL_GRASS, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_GRASS_BALL, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_DEAD_BUSH, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLUE_FLOWER, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLACK_ROSE, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BELLFLOWER, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_BERRY, 0.5F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_AERIAL_BERRY, 0.85F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(FROZEN_AERIAL_BERRY, 0.85F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_BERRY_SEEDS, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_AERIAL_BERRY_SEEDS, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_CONE, 0.5F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_TREE_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_TREE_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GOLDEN_BEECH_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GOLDEN_BEECH_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(LAPIS_ROBINIA_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(LAPIS_ROBINIA_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SHADOW_PINE_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SHADOW_PINE_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_JUNGLE_TREE_SAPLING, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_JUNGLE_TREE_LEAVES, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CORTINARIUS_VIOLACEUS, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SKY_CACTUS_FIBER, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SKY_CACTUS, 0.4F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_SKY_CACTUS_FIBER, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_SKY_CACTUS, 0.8F);
    }

    //portal
    public static final Item STELLAR_PORTAL_FRAME_BLOCK = register(Keys.STELLAR_PORTAL_FRAME_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK, new Item.Settings().registryKey(Keys.STELLAR_PORTAL_FRAME_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_PORTAL_FRAME_ORE = register(Keys.STELLAR_PORTAL_FRAME_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_ORE, new Item.Settings().registryKey(Keys.STELLAR_PORTAL_FRAME_ORE).useBlockPrefixedTranslationKey()));
    public static final Item DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = register(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE, new Item.Settings().registryKey(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_PORTAL_FRAME_BRICK = register(Keys.STELLAR_PORTAL_FRAME_BRICK.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.STELLAR_PORTAL_FRAME_BRICK)));
    public static final Item STELLAR_LIGHTER = register(Keys.STELLAR_LIGHTER.getValue().getPath(), new StellarLighterItem(new Item.Settings().registryKey(Keys.STELLAR_LIGHTER).maxCount(1).maxDamage(4)));

    //torch
    public static final Item CRYSTALLIZED_TORCH = register(Keys.CRYSTALLIZED_TORCH.getValue().getPath(), new VerticallyAttachableBlockItem(AerialHellBlocks.CRYSTALLIZED_TORCH, AerialHellBlocks.CRYSTALLIZED_WALL_TORCH, Direction.DOWN, new Item.Settings().registryKey(Keys.CRYSTALLIZED_TORCH).useBlockPrefixedTranslationKey()));
    public static final Item FLUORITE_TORCH = register(Keys.FLUORITE_TORCH.getValue().getPath(), new VerticallyAttachableBlockItem(AerialHellBlocks.FLUORITE_TORCH, AerialHellBlocks.FLUORITE_WALL_TORCH, Direction.DOWN, new Item.Settings().registryKey(Keys.FLUORITE_TORCH).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_TORCH = register(Keys.VOLUCITE_TORCH.getValue().getPath(), new VerticallyAttachableBlockItem(AerialHellBlocks.VOLUCITE_TORCH, AerialHellBlocks.VOLUCITE_WALL_TORCH, Direction.DOWN, new Item.Settings().registryKey(Keys.VOLUCITE_TORCH).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_TORCH = register(Keys.SHADOW_TORCH.getValue().getPath(), new VerticallyAttachableBlockItem(AerialHellBlocks.SHADOW_TORCH, AerialHellBlocks.SHADOW_WALL_TORCH, Direction.DOWN, new Item.Settings().registryKey(Keys.SHADOW_TORCH).useBlockPrefixedTranslationKey()));

    //lanterns
    public static final Item CRYSTALLIZED_LANTERN = register(Keys.CRYSTALLIZED_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTALLIZED_LANTERN, new Item.Settings().registryKey(Keys.CRYSTALLIZED_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item FLUORITE_LANTERN = register(Keys.FLUORITE_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.FLUORITE_LANTERN, new Item.Settings().registryKey(Keys.FLUORITE_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_LANTERN = register(Keys.RUBY_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_LANTERN, new Item.Settings().registryKey(Keys.RUBY_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_CRYSTALLIZED_LANTERN = register(Keys.RUBY_CRYSTALLIZED_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_CRYSTALLIZED_LANTERN, new Item.Settings().registryKey(Keys.RUBY_CRYSTALLIZED_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_FLUORITE_LANTERN = register(Keys.RUBY_FLUORITE_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_FLUORITE_LANTERN, new Item.Settings().registryKey(Keys.RUBY_FLUORITE_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_LANTERN = register(Keys.VOLUCITE_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_LANTERN, new Item.Settings().registryKey(Keys.VOLUCITE_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_CRYSTALLIZED_LANTERN = register(Keys.VOLUCITE_CRYSTALLIZED_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_CRYSTALLIZED_LANTERN, new Item.Settings().registryKey(Keys.VOLUCITE_CRYSTALLIZED_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_FLUORITE_LANTERN = register(Keys.VOLUCITE_FLUORITE_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_FLUORITE_LANTERN, new Item.Settings().registryKey(Keys.VOLUCITE_FLUORITE_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_LANTERN = register(Keys.LUNATIC_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_LANTERN, new Item.Settings().registryKey(Keys.LUNATIC_LANTERN).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_LANTERN = register(Keys.SHADOW_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_LANTERN, new Item.Settings().registryKey(Keys.SHADOW_LANTERN).useBlockPrefixedTranslationKey()));

    //chains
    public static final Item RUBY_CHAIN = register(Keys.RUBY_CHAIN.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_CHAIN, new Item.Settings().registryKey(Keys.RUBY_CHAIN).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_CHAIN = register(Keys.VOLUCITE_CHAIN.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_CHAIN, new Item.Settings().registryKey(Keys.VOLUCITE_CHAIN).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_CHAIN = register(Keys.LUNATIC_CHAIN.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_CHAIN, new Item.Settings().registryKey(Keys.LUNATIC_CHAIN).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CHAIN = register(Keys.SHADOW_CHAIN.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CHAIN, new Item.Settings().registryKey(Keys.SHADOW_CHAIN).useBlockPrefixedTranslationKey()));

    //grass & dirt
    public static final Item STELLAR_GRASS_BLOCK = register(Keys.STELLAR_GRASS_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_GRASS_BLOCK, new Item.Settings().registryKey(Keys.STELLAR_GRASS_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_STELLAR_GRASS_BLOCK = register(Keys.CHISELED_STELLAR_GRASS_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, new Item.Settings().registryKey(Keys.CHISELED_STELLAR_GRASS_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_DIRT = register(Keys.STELLAR_DIRT.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_DIRT, new Item.Settings().registryKey(Keys.STELLAR_DIRT).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COARSE_DIRT = register(Keys.STELLAR_COARSE_DIRT.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COARSE_DIRT, new Item.Settings().registryKey(Keys.STELLAR_COARSE_DIRT).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_FARMLAND = register(Keys.STELLAR_FARMLAND.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_FARMLAND, new Item.Settings().registryKey(Keys.STELLAR_FARMLAND).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_DIRT_PATH = register(Keys.STELLAR_DIRT_PATH.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_DIRT_PATH, new Item.Settings().registryKey(Keys.STELLAR_DIRT_PATH).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_PODZOL = register(Keys.STELLAR_PODZOL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_PODZOL, new Item.Settings().registryKey(Keys.STELLAR_PODZOL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_CRYSTAL_PODZOL = register(Keys.STELLAR_CRYSTAL_PODZOL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_CRYSTAL_PODZOL, new Item.Settings().registryKey(Keys.STELLAR_CRYSTAL_PODZOL).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_STELLAR_DIRT = register(Keys.CHISELED_STELLAR_DIRT.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_STELLAR_DIRT, new Item.Settings().registryKey(Keys.CHISELED_STELLAR_DIRT).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_GRASS_BLOCK = register(Keys.SHADOW_GRASS_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_GRASS_BLOCK, new Item.Settings().registryKey(Keys.SHADOW_GRASS_BLOCK).useBlockPrefixedTranslationKey()));

    //slippery sand
    public static final Item SLIPPERY_SAND = register(Keys.SLIPPERY_SAND.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND, new Item.Settings().registryKey(Keys.SLIPPERY_SAND).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE = register(Keys.SLIPPERY_SAND_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS = register(Keys.SLIPPERY_SAND_STONE_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CUT_SLIPPERY_SAND_STONE = register(Keys.CUT_SLIPPERY_SAND_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CUT_SLIPPERY_SAND_STONE, new Item.Settings().registryKey(Keys.CUT_SLIPPERY_SAND_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS).useBlockPrefixedTranslationKey()));

    //giant root
    public static final Item GIANT_ROOT = register(Keys.GIANT_ROOT.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GIANT_ROOT, new Item.Settings().registryKey(Keys.GIANT_ROOT).useBlockPrefixedTranslationKey(), 300));

    //aerial_tree
    public static final Item AERIAL_TREE_LOG = register(Keys.AERIAL_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_LOG, new Item.Settings().registryKey(Keys.AERIAL_TREE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_AERIAL_TREE_LOG = register(Keys.STRIPPED_AERIAL_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG, new Item.Settings().registryKey(Keys.STRIPPED_AERIAL_TREE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item AERIAL_TREE_WOOD = register(Keys.AERIAL_TREE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_WOOD, new Item.Settings().registryKey(Keys.AERIAL_TREE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item STRIPPED_AERIAL_TREE_WOOD = register(Keys.STRIPPED_AERIAL_TREE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_AERIAL_TREE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item AERIAL_TREE_LEAVES = register(Keys.AERIAL_TREE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_LEAVES, new Item.Settings().registryKey(Keys.AERIAL_TREE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_TREE_PLANKS = register(Keys.AERIAL_TREE_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PLANKS, new Item.Settings().registryKey(Keys.AERIAL_TREE_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item CHISELED_AERIAL_TREE_PLANKS = register(Keys.CHISELED_AERIAL_TREE_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS, new Item.Settings().registryKey(Keys.CHISELED_AERIAL_TREE_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item AERIAL_TREE_BOOKSHELF = register(Keys.AERIAL_TREE_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_BOOKSHELF, new Item.Settings().registryKey(Keys.AERIAL_TREE_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_TREE_SAPLING = register(Keys.AERIAL_TREE_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_SAPLING, new Item.Settings().registryKey(Keys.AERIAL_TREE_SAPLING).useBlockPrefixedTranslationKey()));

    //petrified aerial tree
    public static final Item PETRIFIED_AERIAL_TREE_LOG = register(Keys.PETRIFIED_AERIAL_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.PETRIFIED_AERIAL_TREE_LOG, new Item.Settings().registryKey(Keys.PETRIFIED_AERIAL_TREE_LOG).useBlockPrefixedTranslationKey(), 600));

    //golden beech
    public static final Item GOLDEN_BEECH_LOG = register(Keys.GOLDEN_BEECH_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_LOG, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_GOLDEN_BEECH_LOG = register(Keys.STRIPPED_GOLDEN_BEECH_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG, new Item.Settings().registryKey(Keys.STRIPPED_GOLDEN_BEECH_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_WOOD = register(Keys.GOLDEN_BEECH_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_WOOD, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item STRIPPED_GOLDEN_BEECH_WOOD = register(Keys.STRIPPED_GOLDEN_BEECH_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_GOLDEN_BEECH_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item GOLDEN_BEECH_PLANKS = register(Keys.GOLDEN_BEECH_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PLANKS, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item CHISELED_GOLDEN_BEECH_PLANKS = register(Keys.CHISELED_GOLDEN_BEECH_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS, new Item.Settings().registryKey(Keys.CHISELED_GOLDEN_BEECH_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_LEAVES = register(Keys.GOLDEN_BEECH_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_LEAVES, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_BOOKSHELF = register(Keys.GOLDEN_BEECH_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_BOOKSHELF, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_SAPLING = register(Keys.GOLDEN_BEECH_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_SAPLING, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_SAPLING).useBlockPrefixedTranslationKey()));

    //cropper pine
    public static final Item COPPER_PINE_LOG = register(Keys.COPPER_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_LOG, new Item.Settings().registryKey(Keys.COPPER_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_COPPER_PINE_LOG = register(Keys.STRIPPED_COPPER_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_LOG, new Item.Settings().registryKey(Keys.STRIPPED_COPPER_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_WOOD = register(Keys.COPPER_PINE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_WOOD, new Item.Settings().registryKey(Keys.COPPER_PINE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item STRIPPED_COPPER_PINE_WOOD = register(Keys.STRIPPED_COPPER_PINE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_COPPER_PINE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item COPPER_PINE_PLANKS = register(Keys.COPPER_PINE_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PLANKS, new Item.Settings().registryKey(Keys.COPPER_PINE_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_LEAVES = register(Keys.COPPER_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_LEAVES, new Item.Settings().registryKey(Keys.COPPER_PINE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_BOOKSHELF = register(Keys.COPPER_PINE_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_BOOKSHELF, new Item.Settings().registryKey(Keys.COPPER_PINE_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_SAPLING = register(Keys.COPPER_PINE_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_SAPLING, new Item.Settings().registryKey(Keys.COPPER_PINE_SAPLING).useBlockPrefixedTranslationKey()));

    //lapis robinia
    public static final Item LAPIS_ROBINIA_LOG = register(Keys.LAPIS_ROBINIA_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_LOG, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_LOG).useBlockPrefixedTranslationKey(), 400));
    public static final Item ENCHANTED_LAPIS_ROBINIA_LOG = register(Keys.ENCHANTED_LAPIS_ROBINIA_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG, new Item.Settings().registryKey(Keys.ENCHANTED_LAPIS_ROBINIA_LOG).useBlockPrefixedTranslationKey(), 400));
    public static final Item STRIPPED_LAPIS_ROBINIA_LOG = register(Keys.STRIPPED_LAPIS_ROBINIA_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG, new Item.Settings().registryKey(Keys.STRIPPED_LAPIS_ROBINIA_LOG).useBlockPrefixedTranslationKey(), 400));
    public static final Item LAPIS_ROBINIA_WOOD = register(Keys.LAPIS_ROBINIA_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_WOOD, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_WOOD).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_LAPIS_ROBINIA_WOOD = register(Keys.STRIPPED_LAPIS_ROBINIA_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_LAPIS_ROBINIA_WOOD).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_LEAVES = register(Keys.LAPIS_ROBINIA_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_LEAVES, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_PLANKS = register(Keys.LAPIS_ROBINIA_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PLANKS, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_BOOKSHELF = register(Keys.LAPIS_ROBINIA_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_SAPLING = register(Keys.LAPIS_ROBINIA_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_SAPLING, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_SAPLING).useBlockPrefixedTranslationKey()));

    //shadow_pine
    public static final Item SHADOW_PINE_LOG = register(Keys.SHADOW_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_LOG, new Item.Settings().registryKey(Keys.SHADOW_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item EYE_SHADOW_PINE_LOG = register(Keys.EYE_SHADOW_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.EYE_SHADOW_PINE_LOG, new Item.Settings().registryKey(Keys.EYE_SHADOW_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_SHADOW_PINE_LOG = register(Keys.STRIPPED_SHADOW_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG, new Item.Settings().registryKey(Keys.STRIPPED_SHADOW_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_WOOD = register(Keys.SHADOW_PINE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_WOOD, new Item.Settings().registryKey(Keys.SHADOW_PINE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item STRIPPED_SHADOW_PINE_WOOD = register(Keys.STRIPPED_SHADOW_PINE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_SHADOW_PINE_WOOD).useBlockPrefixedTranslationKey(), 210));
    public static final Item SHADOW_PINE_LEAVES = register(Keys.SHADOW_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_PINE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item PURPLE_SHADOW_PINE_LEAVES = register(Keys.PURPLE_SHADOW_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, new Item.Settings().registryKey(Keys.PURPLE_SHADOW_PINE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_PLANKS = register(Keys.SHADOW_PINE_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PLANKS, new Item.Settings().registryKey(Keys.SHADOW_PINE_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_BOOKSHELF = register(Keys.SHADOW_PINE_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_BOOKSHELF, new Item.Settings().registryKey(Keys.SHADOW_PINE_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_SAPLING = register(Keys.SHADOW_PINE_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_SAPLING, new Item.Settings().registryKey(Keys.SHADOW_PINE_SAPLING).useBlockPrefixedTranslationKey()));
    public static final Item PURPLE_SHADOW_PINE_SAPLING = register(Keys.PURPLE_SHADOW_PINE_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_SAPLING, new Item.Settings().registryKey(Keys.PURPLE_SHADOW_PINE_SAPLING).useBlockPrefixedTranslationKey()));

    //stellar jungle tree
    public static final Item STELLAR_JUNGLE_TREE_LOG = register(Keys.STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_LOG).useBlockPrefixedTranslationKey(), 400));
    public static final Item STRIPPED_STELLAR_JUNGLE_TREE_LOG = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_LOG, new Item.Settings().registryKey(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG).useBlockPrefixedTranslationKey(), 400));
    public static final Item STELLAR_JUNGLE_TREE_WOOD = register(Keys.STELLAR_JUNGLE_TREE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_WOOD, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_WOOD).useBlockPrefixedTranslationKey(), 300));
    public static final Item STRIPPED_STELLAR_JUNGLE_TREE_WOOD = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_WOOD, new Item.Settings().registryKey(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_LEAVES = register(Keys.STELLAR_JUNGLE_TREE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_PLANKS = register(Keys.STELLAR_JUNGLE_TREE_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PLANKS, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_PLANKS).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_BOOKSHELF = register(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BOOKSHELF, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_SAPLING = register(Keys.STELLAR_JUNGLE_TREE_SAPLING.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_SAPLING).useBlockPrefixedTranslationKey()));
    public static final Item DEAD_STELLAR_JUNGLE_TREE_LOG = register(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.DEAD_STELLAR_JUNGLE_TREE_LOG, new Item.Settings().registryKey(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG).useBlockPrefixedTranslationKey(), 300));

    //shroom
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_STEM, new Item.Settings().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM, new Item.Settings().registryKey(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM, new Item.Settings().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM, new Item.Settings().registryKey(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK, new Item.Settings().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_LIGHT = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.getValue().getPath(), new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT, new Item.Settings().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT).useBlockPrefixedTranslationKey()));
    public static final Item CORTINARIUS_VIOLACEUS = register(Keys.CORTINARIUS_VIOLACEUS.getValue().getPath(), new BlockItem(AerialHellBlocks.CORTINARIUS_VIOLACEUS, new Item.Settings().registryKey(Keys.CORTINARIUS_VIOLACEUS).useBlockPrefixedTranslationKey()));

    public static final Item GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM, new Item.Settings().registryKey(Keys.GIANT_VERDIGRIS_AGARIC_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM, new Item.Settings().registryKey(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM).useBlockPrefixedTranslationKey(), 100));
    public static final Item GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_BARK_STEM, new Item.Settings().registryKey(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM).useBlockPrefixedTranslationKey(), 75));
    public static final Item STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM, new Item.Settings().registryKey(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM).useBlockPrefixedTranslationKey(), 75));
    public static final Item GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = register(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK, new Item.Settings().registryKey(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item VERDIGRIS_AGARIC = register(Keys.VERDIGRIS_AGARIC.getValue().getPath(), new BlockItem(AerialHellBlocks.VERDIGRIS_AGARIC, new Item.Settings().registryKey(Keys.VERDIGRIS_AGARIC).useBlockPrefixedTranslationKey()));
    public static final Item GLOWING_BOLETUS = register(Keys.GLOWING_BOLETUS.getValue().getPath(), new BlockItem(AerialHellBlocks.GLOWING_BOLETUS, new Item.Settings().registryKey(Keys.GLOWING_BOLETUS).useBlockPrefixedTranslationKey()));
    public static final Item TALL_GLOWING_BOLETUS = register(Keys.TALL_GLOWING_BOLETUS.getValue().getPath(), new BlockItem(AerialHellBlocks.TALL_GLOWING_BOLETUS, new Item.Settings().registryKey(Keys.TALL_GLOWING_BOLETUS).useBlockPrefixedTranslationKey()));
    public static final Item BLUE_MEANIE_CLUSTER = register(Keys.BLUE_MEANIE_CLUSTER.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_MEANIE_CLUSTER, new Item.Settings().registryKey(Keys.BLUE_MEANIE_CLUSTER).useBlockPrefixedTranslationKey()));
    public static final Item GIANT_ROOT_SHROOM = register(Keys.GIANT_ROOT_SHROOM.getValue().getPath(), new BlockItem(AerialHellBlocks.GIANT_ROOT_SHROOM, new Item.Settings().registryKey(Keys.GIANT_ROOT_SHROOM).useBlockPrefixedTranslationKey()));

    public static final Item GIANT_GANODERMA_APPLANATUM_BLOCK = register(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK, new Item.Settings().registryKey(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK).useBlockPrefixedTranslationKey()));

    public static final Item GRAY_SHROOM_PLANKS = register(Keys.GRAY_SHROOM_PLANKS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PLANKS, new Item.Settings().registryKey(Keys.GRAY_SHROOM_PLANKS).useBlockPrefixedTranslationKey(), 100));
    public static final Item GRAY_SHROOM_BOOKSHELF = register(Keys.GRAY_SHROOM_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.GRAY_SHROOM_BOOKSHELF, new Item.Settings().registryKey(Keys.GRAY_SHROOM_BOOKSHELF).useBlockPrefixedTranslationKey()));

    //shadow corrupted / uncorrupted variants
    public static final Item SHADOW_AERIAL_TREE_LOG = register(Keys.SHADOW_AERIAL_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, new Item.Settings().registryKey(Keys.SHADOW_AERIAL_TREE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_GOLDEN_BEECH_LOG = register(Keys.SHADOW_GOLDEN_BEECH_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, new Item.Settings().registryKey(Keys.SHADOW_GOLDEN_BEECH_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_COPPER_PINE_LOG = register(Keys.SHADOW_COPPER_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LOG, new Item.Settings().registryKey(Keys.SHADOW_COPPER_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_LAPIS_ROBINIA_LOG = register(Keys.SHADOW_LAPIS_ROBINIA_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, new Item.Settings().registryKey(Keys.SHADOW_LAPIS_ROBINIA_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_STELLAR_JUNGLE_TREE_LOG = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, new Item.Settings().registryKey(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item HOLLOW_SHADOW_PINE_LOG = register(Keys.HOLLOW_SHADOW_PINE_LOG.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, new Item.Settings().registryKey(Keys.HOLLOW_SHADOW_PINE_LOG).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_AERIAL_TREE_LEAVES = register(Keys.SHADOW_AERIAL_TREE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_AERIAL_TREE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_GOLDEN_BEECH_LEAVES = register(Keys.SHADOW_GOLDEN_BEECH_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_GOLDEN_BEECH_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_COPPER_PINE_LEAVES = register(Keys.SHADOW_COPPER_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_COPPER_PINE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_LAPIS_ROBINIA_LEAVES = register(Keys.SHADOW_LAPIS_ROBINIA_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_LAPIS_ROBINIA_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_STELLAR_JUNGLE_TREE_LEAVES = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, new Item.Settings().registryKey(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item HOLLOW_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_SHADOW_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, new Item.Settings().registryKey(Keys.HOLLOW_SHADOW_PINE_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item HOLLOW_PURPLE_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, new Item.Settings().registryKey(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES).useBlockPrefixedTranslationKey()));

    //ladder
    public static final Item SKY_LADDER = register(Keys.SKY_LADDER.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_LADDER, new Item.Settings().registryKey(Keys.SKY_LADDER).useBlockPrefixedTranslationKey()));

    //natural blocks and items
    public static final Item STELLAR_STONE = register(Keys.STELLAR_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE, new Item.Settings().registryKey(Keys.STELLAR_STONE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE = register(Keys.STELLAR_COBBLESTONE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_STONE = register(Keys.MOSSY_STELLAR_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_STONE).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_COBBLESTONE = register(Keys.MOSSY_STELLAR_COBBLESTONE.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_COBBLESTONE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_CLAY = register(Keys.STELLAR_CLAY.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_CLAY, new Item.Settings().registryKey(Keys.STELLAR_CLAY).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS = register(Keys.STELLAR_STONE_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item GLAUCOPHANITE = register(Keys.GLAUCOPHANITE.getValue().getPath(), new BlockItem(AerialHellBlocks.GLAUCOPHANITE, new Item.Settings().registryKey(Keys.GLAUCOPHANITE).useBlockPrefixedTranslationKey()));
    public static final Item POLISHED_GLAUCOPHANITE = register(Keys.POLISHED_GLAUCOPHANITE.getValue().getPath(), new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE, new Item.Settings().registryKey(Keys.POLISHED_GLAUCOPHANITE).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_STONE = register(Keys.SHADOW_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_STONE, new Item.Settings().registryKey(Keys.SHADOW_STONE).useBlockPrefixedTranslationKey()));

    //crystal
    public static final Item CRYSTAL_BLOCK = register(Keys.CRYSTAL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTAL_BLOCK, new Item.Settings().registryKey(Keys.CRYSTAL_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTAL_BRICKS = register(Keys.CRYSTAL_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS, new Item.Settings().registryKey(Keys.CRYSTAL_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTAL_BRICKS_SLAB = register(Keys.CRYSTAL_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_SLAB, new Item.Settings().registryKey(Keys.CRYSTAL_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTAL_BRICKS_STAIRS = register(Keys.CRYSTAL_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.CRYSTAL_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTAL_BRICKS_WALL = register(Keys.CRYSTAL_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_WALL, new Item.Settings().registryKey(Keys.CRYSTAL_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_CRYSTAL_BLOCK = register(Keys.STELLAR_STONE_CRYSTAL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK, new Item.Settings().registryKey(Keys.STELLAR_STONE_CRYSTAL_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CRYSTAL_BLOCK = register(Keys.SHADOW_CRYSTAL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, new Item.Settings().registryKey(Keys.SHADOW_CRYSTAL_BLOCK).rarity(AerialHellRarities.CORRUPTED).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTALLIZED_LEAVES = register(Keys.CRYSTALLIZED_LEAVES.getValue().getPath(), new BlockItem(AerialHellBlocks.CRYSTALLIZED_LEAVES, new Item.Settings().registryKey(Keys.CRYSTALLIZED_LEAVES).useBlockPrefixedTranslationKey()));
    public static final Item CRYSTAL = register(Keys.CRYSTAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.CRYSTAL)));
    public static final Item STELLAR_STONE_CRYSTAL = register(Keys.STELLAR_STONE_CRYSTAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.STELLAR_STONE_CRYSTAL)));
    public static final Item SHADOW_CRYSTAL = register(Keys.SHADOW_CRYSTAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.SHADOW_CRYSTAL).rarity(AerialHellRarities.CORRUPTED)));

    //glass and glass pane
    public static final Item SLIPPERY_SAND_GLASS = register(Keys.SLIPPERY_SAND_GLASS.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_GLASS).useBlockPrefixedTranslationKey()));
    public static final Item RED_SLIPPERY_SAND_GLASS = register(Keys.RED_SLIPPERY_SAND_GLASS.getValue().getPath(), new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS, new Item.Settings().registryKey(Keys.RED_SLIPPERY_SAND_GLASS).useBlockPrefixedTranslationKey()));
    public static final Item BLACK_SLIPPERY_SAND_GLASS = register(Keys.BLACK_SLIPPERY_SAND_GLASS.getValue().getPath(), new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS, new Item.Settings().registryKey(Keys.BLACK_SLIPPERY_SAND_GLASS).useBlockPrefixedTranslationKey()));
    public static final Item BLUE_SLIPPERY_SAND_GLASS = register(Keys.BLUE_SLIPPERY_SAND_GLASS.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS, new Item.Settings().registryKey(Keys.BLUE_SLIPPERY_SAND_GLASS).useBlockPrefixedTranslationKey()));
    public static final Item GREEN_SLIPPERY_SAND_GLASS = register(Keys.GREEN_SLIPPERY_SAND_GLASS.getValue().getPath(), new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS, new Item.Settings().registryKey(Keys.GREEN_SLIPPERY_SAND_GLASS).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_GLASS_PANE = register(Keys.SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS_PANE, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_GLASS_PANE).useBlockPrefixedTranslationKey()));
    public static final Item RED_SLIPPERY_SAND_GLASS_PANE = register(Keys.RED_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS_PANE, new Item.Settings().registryKey(Keys.RED_SLIPPERY_SAND_GLASS_PANE).useBlockPrefixedTranslationKey()));
    public static final Item BLACK_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS_PANE, new Item.Settings().registryKey(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE).useBlockPrefixedTranslationKey()));
    public static final Item BLUE_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS_PANE, new Item.Settings().registryKey(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE).useBlockPrefixedTranslationKey()));
    public static final Item GREEN_SLIPPERY_SAND_GLASS_PANE = register(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS_PANE, new Item.Settings().registryKey(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE).useBlockPrefixedTranslationKey()));

    //ghost boat
    public static final Item GHOST_BOAT_PLANKS = register(Keys.GHOST_BOAT_PLANKS.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_PLANKS, new Item.Settings().registryKey(Keys.GHOST_BOAT_PLANKS).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_LOG = register(Keys.GHOST_BOAT_LOG.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_LOG, new Item.Settings().registryKey(Keys.GHOST_BOAT_LOG).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_WOOD = register(Keys.GHOST_BOAT_WOOD.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOD, new Item.Settings().registryKey(Keys.GHOST_BOAT_WOOD).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_SLAB = register(Keys.GHOST_BOAT_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_SLAB, new Item.Settings().registryKey(Keys.GHOST_BOAT_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_STAIRS = register(Keys.GHOST_BOAT_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_STAIRS, new Item.Settings().registryKey(Keys.GHOST_BOAT_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_FENCE = register(Keys.GHOST_BOAT_FENCE.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_FENCE, new Item.Settings().registryKey(Keys.GHOST_BOAT_FENCE).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_GATE = register(Keys.GHOST_BOAT_GATE.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_GATE, new Item.Settings().registryKey(Keys.GHOST_BOAT_GATE).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_DOOR = register(Keys.GHOST_BOAT_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_DOOR, new Item.Settings().registryKey(Keys.GHOST_BOAT_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_TRAPDOOR = register(Keys.GHOST_BOAT_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_TRAPDOOR, new Item.Settings().registryKey(Keys.GHOST_BOAT_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_CHEST = register(Keys.GHOST_BOAT_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_CHEST, new Item.Settings().registryKey(Keys.GHOST_BOAT_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_WOOL = register(Keys.GHOST_BOAT_WOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOL, new Item.Settings().registryKey(Keys.GHOST_BOAT_WOOL).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_STELLAR_COBBLESTONE = register(Keys.GHOST_STELLAR_COBBLESTONE.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE, new Item.Settings().registryKey(Keys.GHOST_STELLAR_COBBLESTONE).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_RUBY_BLOCK = register(Keys.GHOST_RUBY_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_RUBY_BLOCK, new Item.Settings().registryKey(Keys.GHOST_RUBY_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_FLUORITE_BLOCK = register(Keys.GHOST_FLUORITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_FLUORITE_BLOCK, new Item.Settings().registryKey(Keys.GHOST_FLUORITE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_AZURITE_BLOCK = register(Keys.GHOST_AZURITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_AZURITE_BLOCK, new Item.Settings().registryKey(Keys.GHOST_AZURITE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_GOLD_BLOCK = register(Keys.GHOST_GOLD_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_GOLD_BLOCK, new Item.Settings().registryKey(Keys.GHOST_GOLD_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_BARREL = register(Keys.GHOST_BOAT_BARREL.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_BARREL, new Item.Settings().registryKey(Keys.GHOST_BOAT_BARREL).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_CRAFTING_TABLE = register(Keys.GHOST_BOAT_CRAFTING_TABLE.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.GHOST_BOAT_CRAFTING_TABLE).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_BOAT_VINE_ROPE_SPOOL = register(Keys.GHOST_BOAT_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_BOAT_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.GHOST_BOAT_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_LANTERN = register(Keys.GHOST_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_LANTERN, new Item.Settings().registryKey(Keys.GHOST_LANTERN).useBlockPrefixedTranslationKey()));

    //reactors
    public static final Item WEAK_LIGHT_REACTOR = register(Keys.WEAK_LIGHT_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.WEAK_LIGHT_REACTOR, new Item.Settings().registryKey(Keys.WEAK_LIGHT_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));
    public static final Item HIGH_POWER_LIGHT_REACTOR = register(Keys.HIGH_POWER_LIGHT_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR, new Item.Settings().registryKey(Keys.HIGH_POWER_LIGHT_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));
    public static final Item WEAK_SHADOW_REACTOR = register(Keys.WEAK_SHADOW_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.WEAK_SHADOW_REACTOR, new Item.Settings().registryKey(Keys.WEAK_SHADOW_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item HIGH_POWER_SHADOW_REACTOR = register(Keys.HIGH_POWER_SHADOW_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR, new Item.Settings().registryKey(Keys.HIGH_POWER_SHADOW_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.CORRUPTED)));

    public static final Item BROKEN_WEAK_LIGHT_REACTOR = register(Keys.BROKEN_WEAK_LIGHT_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR, new Item.Settings().registryKey(Keys.BROKEN_WEAK_LIGHT_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));
    public static final Item BROKEN_HIGH_POWER_LIGHT_REACTOR = register(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR, new Item.Settings().registryKey(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));
    public static final Item BROKEN_WEAK_SHADOW_REACTOR = register(Keys.BROKEN_WEAK_SHADOW_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR, new Item.Settings().registryKey(Keys.BROKEN_WEAK_SHADOW_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item BROKEN_HIGH_POWER_SHADOW_REACTOR = register(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR.getValue().getPath(), new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR, new Item.Settings().registryKey(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.CORRUPTED)));

    //solid_ethers
    public static final Item WHITE_SOLID_ETHER = register(Keys.WHITE_SOLID_ETHER.getValue().getPath(), new BlockItem(AerialHellBlocks.WHITE_SOLID_ETHER, new Item.Settings().registryKey(Keys.WHITE_SOLID_ETHER).useBlockPrefixedTranslationKey()));
    public static final Item BLUE_SOLID_ETHER = register(Keys.BLUE_SOLID_ETHER.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_SOLID_ETHER, new Item.Settings().registryKey(Keys.BLUE_SOLID_ETHER).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_SOLID_ETHER = register(Keys.GOLDEN_SOLID_ETHER.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_SOLID_ETHER, new Item.Settings().registryKey(Keys.GOLDEN_SOLID_ETHER).useBlockPrefixedTranslationKey()));
    public static final Item GREEN_SOLID_ETHER = register(Keys.GREEN_SOLID_ETHER.getValue().getPath(), new BlockItem(AerialHellBlocks.GREEN_SOLID_ETHER, new Item.Settings().registryKey(Keys.GREEN_SOLID_ETHER).useBlockPrefixedTranslationKey()));
    public static final Item PURPLE_SOLID_ETHER = register(Keys.PURPLE_SOLID_ETHER.getValue().getPath(), new BlockItem(AerialHellBlocks.PURPLE_SOLID_ETHER, new Item.Settings().registryKey(Keys.PURPLE_SOLID_ETHER).useBlockPrefixedTranslationKey()));

    //dungeons blocks
    public static final Item MUD_BRICKS = register(Keys.MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BRICKS, new Item.Settings().registryKey(Keys.MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_MUD_BRICKS = register(Keys.CRACKED_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_MUD_BRICKS = register(Keys.MOSSY_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS, new Item.Settings().registryKey(Keys.MOSSY_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_MUD_BRICKS = register(Keys.CHISELED_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_MUD_BRICKS, new Item.Settings().registryKey(Keys.CHISELED_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item LIGHT_MUD_BRICKS = register(Keys.LIGHT_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.LIGHT_MUD_BRICKS, new Item.Settings().registryKey(Keys.LIGHT_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LIGHT_MUD_BRICKS = register(Keys.CRACKED_LIGHT_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_LIGHT_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_STONE = register(Keys.LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_STONE, new Item.Settings().registryKey(Keys.LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item DARK_LUNATIC_STONE = register(Keys.DARK_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE, new Item.Settings().registryKey(Keys.DARK_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item ROOF_LUNATIC_STONE = register(Keys.ROOF_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.ROOF_LUNATIC_STONE, new Item.Settings().registryKey(Keys.ROOF_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LUNATIC_STONE = register(Keys.CRACKED_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE, new Item.Settings().registryKey(Keys.CRACKED_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_LUNATIC_STONE = register(Keys.CHISELED_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_LUNATIC_STONE, new Item.Settings().registryKey(Keys.CHISELED_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item LIGHT_LUNATIC_STONE = register(Keys.LIGHT_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.LIGHT_LUNATIC_STONE, new Item.Settings().registryKey(Keys.LIGHT_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item ROOF_LIGHT_LUNATIC_STONE = register(Keys.ROOF_LIGHT_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.ROOF_LIGHT_LUNATIC_STONE, new Item.Settings().registryKey(Keys.ROOF_LIGHT_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LIGHT_LUNATIC_STONE = register(Keys.CRACKED_LIGHT_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE, new Item.Settings().registryKey(Keys.CRACKED_LIGHT_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_BRICKS = register(Keys.SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_SHADOW_CATACOMBS_BRICKS = register(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item BONE_SHADOW_CATACOMBS_BRICKS = register(Keys.BONE_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.BONE_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.BONE_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item SKULL_SHADOW_CATACOMBS_BRICKS = register(Keys.SKULL_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.SKULL_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.SKULL_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_BRICKS = register(Keys.GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_GOLDEN_NETHER_BRICKS = register(Keys.CHISELED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.CHISELED_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.LIGHT_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_PILLAR = register(Keys.LUNATIC_PILLAR.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_PILLAR, new Item.Settings().registryKey(Keys.LUNATIC_PILLAR).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_PILLAR_TOP = register(Keys.LUNATIC_PILLAR_TOP.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_PILLAR_TOP, new Item.Settings().registryKey(Keys.LUNATIC_PILLAR_TOP).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_STONE = register(Keys.VOLUCITE_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_STONE, new Item.Settings().registryKey(Keys.VOLUCITE_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_VOLUCITE_STONE = register(Keys.CRACKED_VOLUCITE_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE, new Item.Settings().registryKey(Keys.CRACKED_VOLUCITE_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_VOLUCITE_STONE = register(Keys.CHISELED_VOLUCITE_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_VOLUCITE_STONE, new Item.Settings().registryKey(Keys.CHISELED_VOLUCITE_STONE).useBlockPrefixedTranslationKey()));
    public static final Item LIGHT_VOLUCITE_STONE = register(Keys.LIGHT_VOLUCITE_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.LIGHT_VOLUCITE_STONE, new Item.Settings().registryKey(Keys.LIGHT_VOLUCITE_STONE).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LIGHT_VOLUCITE_STONE = register(Keys.CRACKED_LIGHT_VOLUCITE_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE, new Item.Settings().registryKey(Keys.CRACKED_LIGHT_VOLUCITE_STONE).useBlockPrefixedTranslationKey()));

    //dungeon cores
    public static final Item MUD_DUNGEON_CORE = register(Keys.MUD_DUNGEON_CORE.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_DUNGEON_CORE, new Item.Settings().registryKey(Keys.MUD_DUNGEON_CORE).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_DUNGEON_CORE = register(Keys.LUNATIC_DUNGEON_CORE.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_DUNGEON_CORE, new Item.Settings().registryKey(Keys.LUNATIC_DUNGEON_CORE).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_DUNGEON_CORE = register(Keys.SHADOW_CATACOMBS_DUNGEON_CORE.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_DUNGEON_CORE).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_DUNGEON_CORE = register(Keys.GOLDEN_NETHER_DUNGEON_CORE.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_DUNGEON_CORE).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_DUNGEON_CORE = register(Keys.VOLUCITE_DUNGEON_CORE.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_DUNGEON_CORE, new Item.Settings().registryKey(Keys.VOLUCITE_DUNGEON_CORE).useBlockPrefixedTranslationKey()));

    //dungeons slabs, stairs & walls
    public static final Item MUD_BRICKS_SLAB = register(Keys.MUD_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BRICKS_SLAB, new Item.Settings().registryKey(Keys.MUD_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MUD_BRICKS_STAIRS = register(Keys.MUD_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.MUD_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MUD_BRICKS_WALL = register(Keys.MUD_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BRICKS_WALL, new Item.Settings().registryKey(Keys.MUD_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_MUD_BRICKS_SLAB = register(Keys.CRACKED_MUD_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_SLAB, new Item.Settings().registryKey(Keys.CRACKED_MUD_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_MUD_BRICKS_STAIRS = register(Keys.CRACKED_MUD_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_MUD_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_MUD_BRICKS_WALL = register(Keys.CRACKED_MUD_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_WALL, new Item.Settings().registryKey(Keys.CRACKED_MUD_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_MUD_BRICKS_SLAB = register(Keys.MOSSY_MUD_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB, new Item.Settings().registryKey(Keys.MOSSY_MUD_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_MUD_BRICKS_STAIRS = register(Keys.MOSSY_MUD_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.MOSSY_MUD_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_MUD_BRICKS_WALL = register(Keys.MOSSY_MUD_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL, new Item.Settings().registryKey(Keys.MOSSY_MUD_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_STONE_SLAB = register(Keys.VOLUCITE_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_STONE_SLAB, new Item.Settings().registryKey(Keys.VOLUCITE_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_STONE_STAIRS = register(Keys.VOLUCITE_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_STONE_STAIRS, new Item.Settings().registryKey(Keys.VOLUCITE_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_STONE_WALL = register(Keys.VOLUCITE_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_STONE_WALL, new Item.Settings().registryKey(Keys.VOLUCITE_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_VOLUCITE_STONE_SLAB = register(Keys.CRACKED_VOLUCITE_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_SLAB, new Item.Settings().registryKey(Keys.CRACKED_VOLUCITE_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_VOLUCITE_STONE_STAIRS = register(Keys.CRACKED_VOLUCITE_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_VOLUCITE_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_VOLUCITE_STONE_WALL = register(Keys.CRACKED_VOLUCITE_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL, new Item.Settings().registryKey(Keys.CRACKED_VOLUCITE_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_STONE_SLAB = register(Keys.LUNATIC_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_STONE_SLAB, new Item.Settings().registryKey(Keys.LUNATIC_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_STONE_STAIRS = register(Keys.LUNATIC_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_STONE_STAIRS, new Item.Settings().registryKey(Keys.LUNATIC_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_STONE_WALL = register(Keys.LUNATIC_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_STONE_WALL, new Item.Settings().registryKey(Keys.LUNATIC_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item DARK_LUNATIC_STONE_SLAB = register(Keys.DARK_LUNATIC_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_SLAB, new Item.Settings().registryKey(Keys.DARK_LUNATIC_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item DARK_LUNATIC_STONE_STAIRS = register(Keys.DARK_LUNATIC_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_STAIRS, new Item.Settings().registryKey(Keys.DARK_LUNATIC_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item DARK_LUNATIC_STONE_WALL = register(Keys.DARK_LUNATIC_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_WALL, new Item.Settings().registryKey(Keys.DARK_LUNATIC_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LUNATIC_STONE_SLAB = register(Keys.CRACKED_LUNATIC_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_SLAB, new Item.Settings().registryKey(Keys.CRACKED_LUNATIC_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LUNATIC_STONE_STAIRS = register(Keys.CRACKED_LUNATIC_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_LUNATIC_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_LUNATIC_STONE_WALL = register(Keys.CRACKED_LUNATIC_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL, new Item.Settings().registryKey(Keys.CRACKED_LUNATIC_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings().registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings().registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings().registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings().registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_BARS = register(Keys.SHADOW_BARS.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_BARS, new Item.Settings().registryKey(Keys.SHADOW_BARS).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_BRICKS_SLAB = register(Keys.GOLDEN_NETHER_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_SLAB, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.GOLDEN_NETHER_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_BRICKS_WALL = register(Keys.GOLDEN_NETHER_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_SLAB = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_SLAB, new Item.Settings().registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_WALL = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL, new Item.Settings().registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL).useBlockPrefixedTranslationKey()));

    //smoky quartz
    public static final Item SMOKY_QUARTZ = register(Keys.SMOKY_QUARTZ.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.SMOKY_QUARTZ)));
    public static final Item SMOKY_QUARTZ_BLOCK = register(Keys.SMOKY_QUARTZ_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BLOCK, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item SMOOTH_SMOKY_QUARTZ = register(Keys.SMOOTH_SMOKY_QUARTZ.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ, new Item.Settings().registryKey(Keys.SMOOTH_SMOKY_QUARTZ).useBlockPrefixedTranslationKey()));
    public static final Item CHISELED_SMOKY_QUARTZ_BLOCK = register(Keys.CHISELED_SMOKY_QUARTZ_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, new Item.Settings().registryKey(Keys.CHISELED_SMOKY_QUARTZ_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item SMOKY_QUARTZ_BRICKS = register(Keys.SMOKY_QUARTZ_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BRICKS, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item SMOKY_QUARTZ_PILLAR = register(Keys.SMOKY_QUARTZ_PILLAR.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_PILLAR, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_PILLAR).useBlockPrefixedTranslationKey()));
    public static final Item SMOKY_QUARTZ_SLAB = register(Keys.SMOKY_QUARTZ_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_SLAB, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item SMOOTH_SMOKY_QUARTZ_SLAB = register(Keys.SMOOTH_SMOKY_QUARTZ_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB, new Item.Settings().registryKey(Keys.SMOOTH_SMOKY_QUARTZ_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item SMOKY_QUARTZ_STAIRS = register(Keys.SMOKY_QUARTZ_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_STAIRS, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item SMOOTH_SMOKY_QUARTZ_STAIRS = register(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS, new Item.Settings().registryKey(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS).useBlockPrefixedTranslationKey()));

    //dungeon trapped blocks
    public static final Item TRAPPED_MUD_BRICKS = register(Keys.TRAPPED_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_MUD_BRICKS, new Item.Settings().registryKey(Keys.TRAPPED_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item TRAPPED_LIGHT_MUD_BRICKS = register(Keys.TRAPPED_LIGHT_MUD_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS, new Item.Settings().registryKey(Keys.TRAPPED_LIGHT_MUD_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item TRAPPED_LUNATIC_STONE = register(Keys.TRAPPED_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_LUNATIC_STONE, new Item.Settings().registryKey(Keys.TRAPPED_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item TRAPPED_LIGHT_LUNATIC_STONE = register(Keys.TRAPPED_LIGHT_LUNATIC_STONE.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE, new Item.Settings().registryKey(Keys.TRAPPED_LIGHT_LUNATIC_STONE).useBlockPrefixedTranslationKey()));
    public static final Item TRAPPED_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.TRAPPED_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));
    public static final Item TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings().registryKey(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS).useBlockPrefixedTranslationKey()));

    //dungeon other blocks, loots
    public static final Item MUD_BONE_BLOCK = register(Keys.MUD_BONE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BONE_BLOCK, new Item.Settings().registryKey(Keys.MUD_BONE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item MUD_BONE_PILE_BLOCK = register(Keys.MUD_BONE_PILE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BONE_PILE_BLOCK, new Item.Settings().registryKey(Keys.MUD_BONE_PILE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item MUD_BONE = register(Keys.MUD_BONE.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.MUD_BONE)));
    public static final Item THORNY_COBWEB = register(Keys.THORNY_COBWEB.getValue().getPath(), new BlockItem(AerialHellBlocks.THORNY_COBWEB, new Item.Settings().registryKey(Keys.THORNY_COBWEB).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_NETHERRACK = register(Keys.AERIAL_NETHERRACK.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK, new Item.Settings().registryKey(Keys.AERIAL_NETHERRACK).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_NETHERRACK_SLAB = register(Keys.AERIAL_NETHERRACK_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_SLAB, new Item.Settings().registryKey(Keys.AERIAL_NETHERRACK_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_NETHERRACK_STAIRS = register(Keys.AERIAL_NETHERRACK_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_STAIRS, new Item.Settings().registryKey(Keys.AERIAL_NETHERRACK_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_NETHERRACK_WALL = register(Keys.AERIAL_NETHERRACK_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_WALL, new Item.Settings().registryKey(Keys.AERIAL_NETHERRACK_WALL).useBlockPrefixedTranslationKey()));

    //dungeon bookshelfs
    public static final Item MUD_BOOKSHELF = register(Keys.MUD_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_BOOKSHELF, new Item.Settings().registryKey(Keys.MUD_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_BOOKSHELF = register(Keys.LUNATIC_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_BOOKSHELF, new Item.Settings().registryKey(Keys.LUNATIC_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_BOOKSHELF = register(Keys.GOLDEN_NETHER_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BOOKSHELF, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_BOOKSHELF = register(Keys.SHADOW_CATACOMBS_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BOOKSHELF, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_BOOKSHELF = register(Keys.VOLUCITE_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_BOOKSHELF, new Item.Settings().registryKey(Keys.VOLUCITE_BOOKSHELF).useBlockPrefixedTranslationKey()));

    //glyph blocks
    public static final Item MUD_GLYPH_BLOCK = register(Keys.MUD_GLYPH_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_GLYPH_BLOCK, new Item.Settings().registryKey(Keys.MUD_GLYPH_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_GLYPH_BLOCK = register(Keys.LUNATIC_GLYPH_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_GLYPH_BLOCK, new Item.Settings().registryKey(Keys.LUNATIC_GLYPH_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_PRISON_GLYPH_BLOCK = register(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_GLYPH_BLOCK = register(Keys.VOLUCITE_GLYPH_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_GLYPH_BLOCK, new Item.Settings().registryKey(Keys.VOLUCITE_GLYPH_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_GLYPH_BLOCK = register(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK).useBlockPrefixedTranslationKey()));

    //trophies
    public static final Item MUD_CYCLE_MAGE_TROPHY = register(Keys.MUD_CYCLE_MAGE_TROPHY.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_CYCLE_MAGE_TROPHY, new Item.Settings().registryKey(Keys.MUD_CYCLE_MAGE_TROPHY).useBlockPrefixedTranslationKey()));
    public static final Item LUNAR_PRIEST_TROPHY = register(Keys.LUNAR_PRIEST_TROPHY.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNAR_PRIEST_TROPHY, new Item.Settings().registryKey(Keys.LUNAR_PRIEST_TROPHY).useBlockPrefixedTranslationKey()));
    public static final Item LILITH_TROPHY = register(Keys.LILITH_TROPHY.getValue().getPath(), new BlockItem(AerialHellBlocks.LILITH_TROPHY, new Item.Settings().registryKey(Keys.LILITH_TROPHY).useBlockPrefixedTranslationKey()));
    public static final Item CHAINED_GOD_TROPHY = register(Keys.CHAINED_GOD_TROPHY.getValue().getPath(), new BlockItem(AerialHellBlocks.CHAINED_GOD_TROPHY, new Item.Settings().registryKey(Keys.CHAINED_GOD_TROPHY).useBlockPrefixedTranslationKey()));

    //ores
    public static final Item IRON_STELLAR_ORE = register(Keys.IRON_STELLAR_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.IRON_STELLAR_ORE, new Item.Settings().registryKey(Keys.IRON_STELLAR_ORE).useBlockPrefixedTranslationKey()));
    public static final Item GOLD_STELLAR_ORE = register(Keys.GOLD_STELLAR_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLD_STELLAR_ORE, new Item.Settings().registryKey(Keys.GOLD_STELLAR_ORE).useBlockPrefixedTranslationKey()));
    public static final Item DIAMOND_STELLAR_ORE = register(Keys.DIAMOND_STELLAR_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.DIAMOND_STELLAR_ORE, new Item.Settings().registryKey(Keys.DIAMOND_STELLAR_ORE).useBlockPrefixedTranslationKey()));
    public static final Item FLUORITE_ORE = register(Keys.FLUORITE_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.FLUORITE_ORE, new Item.Settings().registryKey(Keys.FLUORITE_ORE).useBlockPrefixedTranslationKey()));
    public static final Item MAGMATIC_GEL_ORE = register(Keys.MAGMATIC_GEL_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.MAGMATIC_GEL_ORE, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_ORE).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_ORE = register(Keys.RUBY_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_ORE, new Item.Settings().registryKey(Keys.RUBY_ORE).useBlockPrefixedTranslationKey()));
    public static final Item AZURITE_ORE = register(Keys.AZURITE_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.AZURITE_ORE, new Item.Settings().registryKey(Keys.AZURITE_ORE).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_ORE = register(Keys.VOLUCITE_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_ORE, new Item.Settings().registryKey(Keys.VOLUCITE_ORE).rarity(AerialHellRarities.VIBRANT).useBlockPrefixedTranslationKey()));
    public static final Item OBSIDIAN_ORE = register(Keys.OBSIDIAN_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.OBSIDIAN_ORE, new Item.Settings().registryKey(Keys.OBSIDIAN_ORE).rarity(Rarity.EPIC).useBlockPrefixedTranslationKey()));
    public static final Item SMOKY_QUARTZ_ORE = register(Keys.SMOKY_QUARTZ_ORE.getValue().getPath(), new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_ORE, new Item.Settings().registryKey(Keys.SMOKY_QUARTZ_ORE).rarity(Rarity.EPIC).useBlockPrefixedTranslationKey()));

    public static final Item RAW_RUBY_BLOCK = register(Keys.RAW_RUBY_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.RAW_RUBY_BLOCK, new Item.Settings().registryKey(Keys.RAW_RUBY_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item RAW_AZURITE_BLOCK = register(Keys.RAW_AZURITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.RAW_AZURITE_BLOCK, new Item.Settings().registryKey(Keys.RAW_AZURITE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item RAW_VOLUCITE_BLOCK = register(Keys.RAW_VOLUCITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.RAW_VOLUCITE_BLOCK, new Item.Settings().registryKey(Keys.RAW_VOLUCITE_BLOCK).useBlockPrefixedTranslationKey()));

    public static final Item RAW_RUBY = register(Keys.RAW_RUBY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RAW_RUBY)));
    public static final Item RAW_AZURITE = register(Keys.RAW_AZURITE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RAW_AZURITE)));
    public static final Item RAW_VOLUCITE = register(Keys.RAW_VOLUCITE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RAW_VOLUCITE)));

    public static final Item FLUORITE_BLOCK = register(Keys.FLUORITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.FLUORITE_BLOCK, new Item.Settings().registryKey(Keys.FLUORITE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item MAGMATIC_GEL_BLOCK = register(Keys.MAGMATIC_GEL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.MAGMATIC_GEL_BLOCK, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_BLOCK = register(Keys.RUBY_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_BLOCK, new Item.Settings().registryKey(Keys.RUBY_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item AZURITE_BLOCK = register(Keys.AZURITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.AZURITE_BLOCK, new Item.Settings().registryKey(Keys.AZURITE_BLOCK).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_BLOCK = register(Keys.VOLUCITE_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_BLOCK, new Item.Settings().registryKey(Keys.VOLUCITE_BLOCK).useBlockPrefixedTranslationKey()));

    public static final Item FLUORITE = register(Keys.FLUORITE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FLUORITE).trimMaterial(AerialHellTrimMaterials.FLUORITE)));
    public static final Item MAGMATIC_GEL = register(Keys.MAGMATIC_GEL.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL).trimMaterial(AerialHellTrimMaterials.MAGMATIC_GEL)));
    public static final Item RUBY = register(Keys.RUBY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY).trimMaterial(AerialHellTrimMaterials.RUBY)));
    public static final Item AZURITE_CRYSTAL = register(Keys.AZURITE_CRYSTAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_CRYSTAL)));
    public static final Item VOLUCITE_VIBRANT = register(Keys.VOLUCITE_VIBRANT.getValue().getPath(), new VoluciteVibrantItem(new Item.Settings().registryKey(Keys.VOLUCITE_VIBRANT).trimMaterial(AerialHellTrimMaterials.VOLUCITE_VIBRANT).rarity(AerialHellRarities.VIBRANT)));

    public static final Item OVERHEATED_RUBY = register(Keys.OVERHEATED_RUBY.getValue().getPath(), new WithInformationItem(new Item.Settings().registryKey(Keys.OVERHEATED_RUBY)));
    public static final Item OVERHEATED_VOLUCITE = register(Keys.OVERHEATED_VOLUCITE.getValue().getPath(), new WithInformationItem(new Item.Settings().registryKey(Keys.OVERHEATED_VOLUCITE)));

    //legendary ores
    public static final Item ARSONIST_INGOT = register(Keys.ARSONIST_INGOT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_INGOT).trimMaterial(AerialHellTrimMaterials.ARSONIST_INGOT).rarity(AerialHellRarities.LEGENDARY).fireproof()));
    public static final Item LUNATIC_CRYSTAL = register(Keys.LUNATIC_CRYSTAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_CRYSTAL).trimMaterial(AerialHellTrimMaterials.LUNATIC_CRYSTAL).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item OBSIDIAN_SHARD = register(Keys.OBSIDIAN_SHARD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_SHARD).trimMaterial(AerialHellTrimMaterials.OBSIDIAN_SHARD).rarity(Rarity.EPIC)));
    public static final Item CURSED_CRYSAL = register(Keys.CURSED_CRYSAL.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.CURSED_CRYSAL).trimMaterial(AerialHellTrimMaterials.CURSED_CRYSTAL).rarity(AerialHellRarities.CORRUPTED)));

    public static final Item ARSONIST_BLOCK = register(Keys.ARSONIST_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.ARSONIST_BLOCK, new Item.Settings().registryKey(Keys.ARSONIST_BLOCK).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.LEGENDARY).fireproof()));
    public static final Item LUNATIC_CRYSTAL_BLOCK = register(Keys.LUNATIC_CRYSTAL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_CRYSTAL_BLOCK, new Item.Settings().registryKey(Keys.LUNATIC_CRYSTAL_BLOCK).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item CURSED_CRYSAL_BLOCK = register(Keys.CURSED_CRYSAL_BLOCK.getValue().getPath(), new BlockItem(AerialHellBlocks.CURSED_CRYSAL_BLOCK, new Item.Settings().registryKey(Keys.CURSED_CRYSAL_BLOCK).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.CORRUPTED)));

    //cactus
    public static final Item SKY_CACTUS = register(Keys.SKY_CACTUS.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS, new Item.Settings().registryKey(Keys.SKY_CACTUS).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_PLANKS = register(Keys.SKY_CACTUS_FIBER_PLANKS.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PLANKS, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_PLANKS).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_BOOKSHELF = register(Keys.SKY_CACTUS_FIBER_BOOKSHELF.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BOOKSHELF, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_BOOKSHELF).useBlockPrefixedTranslationKey()));
    public static final Item VIBRANT_SKY_CACTUS = register(Keys.VIBRANT_SKY_CACTUS.getValue().getPath(), new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS, new Item.Settings().registryKey(Keys.VIBRANT_SKY_CACTUS).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));
    public static final Item VIBRANT_SKY_CACTUS_FIBER_LANTERN = register(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN.getValue().getPath(), new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN, new Item.Settings().registryKey(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN).useBlockPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));

    //bushes
    public static final Item AERIAL_BERRY_SEEDS = register(Keys.AERIAL_BERRY_SEEDS.getValue().getPath(),new BlockItem(AerialHellBlocks.AERIAL_BERRY_BUSH, new Item.Settings().registryKey(Keys.AERIAL_BERRY_SEEDS).useItemPrefixedTranslationKey()));
    public static final Item VIBRANT_AERIAL_BERRY_SEEDS = register(Keys.VIBRANT_AERIAL_BERRY_SEEDS.getValue().getPath(),new BlockItem(AerialHellBlocks.VIBRANT_AERIAL_BERRY_BUSH, new Item.Settings().registryKey(Keys.VIBRANT_AERIAL_BERRY_SEEDS).useItemPrefixedTranslationKey().rarity(AerialHellRarities.VIBRANT)));

    //crops
    public static final Item STELLAR_WHEAT_SEEDS = register(Keys.STELLAR_WHEAT_SEEDS.getValue().getPath(),new BlockItem(AerialHellBlocks.STELLAR_WHEAT, new Item.Settings().registryKey(Keys.STELLAR_WHEAT_SEEDS).useItemPrefixedTranslationKey()));
    public static final Item STELLAR_WHEAT = register(Keys.STELLAR_WHEAT.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.STELLAR_WHEAT)));
    public static final Item BLUE_MEANIE_SPORES = register(Keys.BLUE_MEANIE_SPORES.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_MEANIE_CROP, new Item.Settings().registryKey(Keys.BLUE_MEANIE_SPORES).useItemPrefixedTranslationKey()));
    public static final Item BLUE_MEANIE_CAP = register(Keys.BLUE_MEANIE_CAP.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.BLUE_MEANIE_CAP)));

    //vertical growing plants
    public static final Item CLIMBING_VINE = register(Keys.CLIMBING_VINE.getValue().getPath(),new BlockItem(AerialHellBlocks.CLIMBING_VINE, new Item.Settings().registryKey(Keys.CLIMBING_VINE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_SUGAR_CANE = register(Keys.STELLAR_SUGAR_CANE.getValue().getPath(),new BlockItem(AerialHellBlocks.STELLAR_SUGAR_CANE, new Item.Settings().registryKey(Keys.STELLAR_SUGAR_CANE).useBlockPrefixedTranslationKey()));

    //chorus like
    public static final Item FULL_MOON_FLOWER = register(Keys.FULL_MOON_FLOWER.getValue().getPath(),new BlockItem(AerialHellBlocks.FULL_MOON_FLOWER, new Item.Settings().registryKey(Keys.FULL_MOON_FLOWER).useBlockPrefixedTranslationKey()));

    //vines
    public static final Item VINE_BLOSSOM = register(Keys.VINE_BLOSSOM.getValue().getPath(),new BlockItem(AerialHellBlocks.BLOSSOMING_VINES, new Item.Settings().registryKey(Keys.VINE_BLOSSOM).useItemPrefixedTranslationKey()));
    public static final Item LAZULI_ROOTS = register(Keys.LAZULI_ROOTS.getValue().getPath(), new BlockItem(AerialHellBlocks.LAZULI_ROOTS, new Item.Settings().registryKey(Keys.LAZULI_ROOTS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_ROOTS = register(Keys.STELLAR_ROOTS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_ROOTS, new Item.Settings().registryKey(Keys.STELLAR_ROOTS).useBlockPrefixedTranslationKey()));
    public static final Item DEAD_ROOTS = register(Keys.DEAD_ROOTS.getValue().getPath(), new BlockItem(AerialHellBlocks.DEAD_ROOTS, new Item.Settings().registryKey(Keys.DEAD_ROOTS).useBlockPrefixedTranslationKey()));
    public static final Item GLOWING_ROOTS = register(Keys.GLOWING_ROOTS.getValue().getPath(), new BlockItem(AerialHellBlocks.GLOWING_ROOTS, new Item.Settings().registryKey(Keys.GLOWING_ROOTS).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_GLOWING_ROOTS = register(Keys.SHADOW_GLOWING_ROOTS.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_GLOWING_ROOTS, new Item.Settings().registryKey(Keys.SHADOW_GLOWING_ROOTS).useBlockPrefixedTranslationKey()));

    //grass
    public static final Item STELLAR_GRASS = register(Keys.STELLAR_GRASS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_GRASS, new Item.Settings().registryKey(Keys.STELLAR_GRASS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_GRASS_BALL = register(Keys.STELLAR_GRASS_BALL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_GRASS_BALL, new Item.Settings().registryKey(Keys.STELLAR_GRASS_BALL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_FERN = register(Keys.STELLAR_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_FERN, new Item.Settings().registryKey(Keys.STELLAR_FERN).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_TALL_GRASS = register(Keys.STELLAR_TALL_GRASS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_TALL_GRASS, new Item.Settings().registryKey(Keys.STELLAR_TALL_GRASS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_TALL_FERN = register(Keys.STELLAR_TALL_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_TALL_FERN, new Item.Settings().registryKey(Keys.STELLAR_TALL_FERN).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_VERY_TALL_GRASS = register(Keys.STELLAR_VERY_TALL_GRASS.getValue().getPath(),new BlockItem(AerialHellBlocks.STELLAR_VERY_TALL_GRASS, new Item.Settings().registryKey(Keys.STELLAR_VERY_TALL_GRASS).useBlockPrefixedTranslationKey()));
    public static final Item BLUISH_FERN = register(Keys.BLUISH_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUISH_FERN, new Item.Settings().registryKey(Keys.BLUISH_FERN).useBlockPrefixedTranslationKey()));
    public static final Item TALL_BLUISH_FERN = register(Keys.TALL_BLUISH_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.TALL_BLUISH_FERN, new Item.Settings().registryKey(Keys.TALL_BLUISH_FERN).useBlockPrefixedTranslationKey()));
    public static final Item POLYCHROME_FERN = register(Keys.POLYCHROME_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.POLYCHROME_FERN, new Item.Settings().registryKey(Keys.POLYCHROME_FERN).useBlockPrefixedTranslationKey()));
    public static final Item TALL_POLYCHROME_FERN = register(Keys.TALL_POLYCHROME_FERN.getValue().getPath(), new BlockItem(AerialHellBlocks.TALL_POLYCHROME_FERN, new Item.Settings().registryKey(Keys.TALL_POLYCHROME_FERN).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_DEAD_BUSH = register(Keys.STELLAR_DEAD_BUSH.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_DEAD_BUSH, new Item.Settings().registryKey(Keys.STELLAR_DEAD_BUSH).useBlockPrefixedTranslationKey()));
    public static final Item BRAMBLES = register(Keys.BRAMBLES.getValue().getPath(), new BlockItem(AerialHellBlocks.BRAMBLES, new Item.Settings().registryKey(Keys.BRAMBLES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_BRAMBLES = register(Keys.SHADOW_BRAMBLES.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_BRAMBLES, new Item.Settings().registryKey(Keys.SHADOW_BRAMBLES).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_GRASS = register(Keys.SHADOW_GRASS.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_GRASS, new Item.Settings().registryKey(Keys.SHADOW_GRASS).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_GRASS_BALL = register(Keys.SHADOW_GRASS_BALL.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_GRASS_BALL, new Item.Settings().registryKey(Keys.SHADOW_GRASS_BALL).useBlockPrefixedTranslationKey()));
    public static final Item PURPLISH_STELLAR_GRASS = register(Keys.PURPLISH_STELLAR_GRASS.getValue().getPath(), new BlockItem(AerialHellBlocks.PURPLISH_STELLAR_GRASS, new Item.Settings().registryKey(Keys.PURPLISH_STELLAR_GRASS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_CLOVERS = register(Keys.STELLAR_CLOVERS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_CLOVERS, new Item.Settings().registryKey(Keys.STELLAR_CLOVERS).useBlockPrefixedTranslationKey()));
    public static final Item GLOWING_STELLAR_GRASS = register(Keys.GLOWING_STELLAR_GRASS.getValue().getPath(), new BlockItem(AerialHellBlocks.GLOWING_STELLAR_GRASS, new Item.Settings().registryKey(Keys.GLOWING_STELLAR_GRASS).useBlockPrefixedTranslationKey()));

    //flowers
    public static final Item BLUE_FLOWER = register(Keys.BLUE_FLOWER.getValue().getPath(), new BlockItem(AerialHellBlocks.BLUE_FLOWER, new Item.Settings().registryKey(Keys.BLUE_FLOWER).useBlockPrefixedTranslationKey()));
    public static final Item BLACK_ROSE = register(Keys.BLACK_ROSE.getValue().getPath(), new BlockItem(AerialHellBlocks.BLACK_ROSE, new Item.Settings().registryKey(Keys.BLACK_ROSE).useBlockPrefixedTranslationKey()));
    public static final Item BELLFLOWER = register(Keys.BELLFLOWER.getValue().getPath(), new BlockItem(AerialHellBlocks.BELLFLOWER, new Item.Settings().registryKey(Keys.BELLFLOWER).useBlockPrefixedTranslationKey()));

    //with gui
    public static final Item OSCILLATOR = register(Keys.OSCILLATOR.getValue().getPath(), new BlockItem(AerialHellBlocks.OSCILLATOR, new Item.Settings().registryKey(Keys.OSCILLATOR).useBlockPrefixedTranslationKey()));
    public static final Item FREEZER = register(Keys.FREEZER.getValue().getPath(), new BlockItem(AerialHellBlocks.FREEZER, new Item.Settings().registryKey(Keys.FREEZER).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_FURNACE = register(Keys.STELLAR_FURNACE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_FURNACE, new Item.Settings().registryKey(Keys.STELLAR_FURNACE).useBlockPrefixedTranslationKey()));
    public static final Item GHOST_STELLAR_FURNACE = register(Keys.GHOST_STELLAR_FURNACE.getValue().getPath(), new BlockItem(AerialHellBlocks.GHOST_STELLAR_FURNACE, new Item.Settings().registryKey(Keys.GHOST_STELLAR_FURNACE).useBlockPrefixedTranslationKey()));

    //chests
    public static final Item AERIAL_TREE_CHEST = register(Keys.AERIAL_TREE_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST, new Item.Settings().registryKey(Keys.AERIAL_TREE_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_CHEST = register(Keys.GOLDEN_BEECH_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_CHEST = register(Keys.COPPER_PINE_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST, new Item.Settings().registryKey(Keys.COPPER_PINE_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_CHEST = register(Keys.LAPIS_ROBINIA_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_CHEST, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_CHEST = register(Keys.SHADOW_PINE_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_CHEST, new Item.Settings().registryKey(Keys.SHADOW_PINE_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_CHEST = register(Keys.STELLAR_JUNGLE_TREE_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_CHEST = register(Keys.SKY_CACTUS_FIBER_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_CHEST = register(Keys.GRAY_SHROOM_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.GRAY_SHROOM_CHEST, new Item.Settings().registryKey(Keys.GRAY_SHROOM_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item MUD_CHEST = register(Keys.MUD_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.MUD_CHEST, new Item.Settings().registryKey(Keys.MUD_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item LUNATIC_CHEST = register(Keys.LUNATIC_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.LUNATIC_CHEST, new Item.Settings().registryKey(Keys.LUNATIC_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item VOLUCITE_CHEST = register(Keys.VOLUCITE_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.VOLUCITE_CHEST, new Item.Settings().registryKey(Keys.VOLUCITE_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_CATACOMBS_CHEST = register(Keys.SHADOW_CATACOMBS_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_CHEST, new Item.Settings().registryKey(Keys.SHADOW_CATACOMBS_CHEST).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_NETHER_CHEST = register(Keys.GOLDEN_NETHER_CHEST.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_NETHER_CHEST, new Item.Settings().registryKey(Keys.GOLDEN_NETHER_CHEST).useBlockPrefixedTranslationKey()));

    //chest mimics
    public static final Item AERIAL_TREE_CHEST_MIMIC = register(Keys.AERIAL_TREE_CHEST_MIMIC.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC, new Item.Settings().registryKey(Keys.AERIAL_TREE_CHEST_MIMIC).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_CHEST_MIMIC = register(Keys.GOLDEN_BEECH_CHEST_MIMIC.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_CHEST_MIMIC).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_CHEST_MIMIC = register(Keys.COPPER_PINE_CHEST_MIMIC.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST_MIMIC, new Item.Settings().registryKey(Keys.COPPER_PINE_CHEST_MIMIC).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_CHEST_MIMIC = register(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC).useBlockPrefixedTranslationKey()));

    //barrel mimics
    public static final Item SHADOW_PINE_BARREL_MIMIC = register(Keys.SHADOW_PINE_BARREL_MIMIC.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_BARREL_MIMIC, new Item.Settings().registryKey(Keys.SHADOW_PINE_BARREL_MIMIC).useBlockPrefixedTranslationKey()));

    //fences, bars or walls
    public static final Item AERIAL_TREE_FENCE = register(Keys.AERIAL_TREE_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_FENCE, new Item.Settings().registryKey(Keys.AERIAL_TREE_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_FENCE = register(Keys.GOLDEN_BEECH_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_FENCE, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_FENCE = register(Keys.COPPER_PINE_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_FENCE, new Item.Settings().registryKey(Keys.COPPER_PINE_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_FENCE = register(Keys.LAPIS_ROBINIA_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_FENCE, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_FENCE = register(Keys.SHADOW_PINE_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_FENCE, new Item.Settings().registryKey(Keys.SHADOW_PINE_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_FENCE = register(Keys.STELLAR_JUNGLE_TREE_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_FENCE, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_FENCE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_FENCE = register(Keys.SKY_CACTUS_FIBER_FENCE.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_FENCE, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_FENCE).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_FENCE = register(Keys.GRAY_SHROOM_FENCE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_FENCE, new Item.Settings().registryKey(Keys.GRAY_SHROOM_FENCE).useBlockPrefixedTranslationKey(), 100));
    public static final Item RUBY_BARS = register(Keys.RUBY_BARS.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_BARS, new Item.Settings().registryKey(Keys.RUBY_BARS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_WALL = register(Keys.STELLAR_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_WALL, new Item.Settings().registryKey(Keys.STELLAR_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE_WALL = register(Keys.STELLAR_COBBLESTONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_WALL, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS_WALL = register(Keys.STELLAR_STONE_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_WALL, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_STONE_WALL = register(Keys.MOSSY_STELLAR_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_WALL = register(Keys.MOSSY_STELLAR_COBBLESTONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_WALL = register(Keys.SLIPPERY_SAND_STONE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_WALL, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL, new Item.Settings().registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL).useBlockPrefixedTranslationKey()));
    public static final Item GLAUCOPHANITE_WALL = register(Keys.GLAUCOPHANITE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.GLAUCOPHANITE_WALL, new Item.Settings().registryKey(Keys.GLAUCOPHANITE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item POLISHED_GLAUCOPHANITE_WALL = register(Keys.POLISHED_GLAUCOPHANITE_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_WALL, new Item.Settings().registryKey(Keys.POLISHED_GLAUCOPHANITE_WALL).useBlockPrefixedTranslationKey()));
    public static final Item MAGMATIC_GEL_WALL = register(Keys.MAGMATIC_GEL_WALL.getValue().getPath(), new BlockItem(AerialHellBlocks.MAGMATIC_GEL_WALL, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_WALL).useBlockPrefixedTranslationKey()));

    //gates
    public static final Item AERIAL_TREE_GATE = register(Keys.AERIAL_TREE_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_GATE, new Item.Settings().registryKey(Keys.AERIAL_TREE_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_GATE = register(Keys.GOLDEN_BEECH_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_GATE, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_GATE = register(Keys.COPPER_PINE_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_GATE, new Item.Settings().registryKey(Keys.COPPER_PINE_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_GATE = register(Keys.LAPIS_ROBINIA_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_GATE, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_GATE = register(Keys.SHADOW_PINE_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_GATE, new Item.Settings().registryKey(Keys.SHADOW_PINE_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_GATE = register(Keys.STELLAR_JUNGLE_TREE_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_GATE, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_GATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_GATE = register(Keys.SKY_CACTUS_FIBER_GATE.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_GATE, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_GATE).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_GATE = register(Keys.GRAY_SHROOM_GATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_GATE, new Item.Settings().registryKey(Keys.GRAY_SHROOM_GATE).useBlockPrefixedTranslationKey(), 100));

    //doors
    public static final Item AERIAL_TREE_DOOR = register(Keys.AERIAL_TREE_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_DOOR, new Item.Settings().registryKey(Keys.AERIAL_TREE_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_DOOR = register(Keys.GOLDEN_BEECH_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_DOOR, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_DOOR = register(Keys.COPPER_PINE_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_DOOR, new Item.Settings().registryKey(Keys.COPPER_PINE_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_DOOR = register(Keys.LAPIS_ROBINIA_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_DOOR, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_DOOR = register(Keys.SHADOW_PINE_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_DOOR, new Item.Settings().registryKey(Keys.SHADOW_PINE_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_DOOR = register(Keys.STELLAR_JUNGLE_TREE_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_DOOR, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_DOOR = register(Keys.SKY_CACTUS_FIBER_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_DOOR, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_DOOR = register(Keys.GRAY_SHROOM_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GRAY_SHROOM_DOOR, new Item.Settings().registryKey(Keys.GRAY_SHROOM_DOOR).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_DOOR = register(Keys.RUBY_DOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_DOOR, new Item.Settings().registryKey(Keys.RUBY_DOOR).useBlockPrefixedTranslationKey()));

    //trapdoors
    public static final Item AERIAL_TREE_TRAPDOOR = register(Keys.AERIAL_TREE_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_TRAPDOOR, new Item.Settings().registryKey(Keys.AERIAL_TREE_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_TRAPDOOR = register(Keys.GOLDEN_BEECH_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_TRAPDOOR, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_TRAPDOOR = register(Keys.COPPER_PINE_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_TRAPDOOR, new Item.Settings().registryKey(Keys.COPPER_PINE_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_TRAPDOOR = register(Keys.LAPIS_ROBINIA_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_TRAPDOOR = register(Keys.SHADOW_PINE_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_TRAPDOOR, new Item.Settings().registryKey(Keys.SHADOW_PINE_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_TRAPDOOR = register(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_TRAPDOOR, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_TRAPDOOR = register(Keys.SKY_CACTUS_FIBER_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks. SKY_CACTUS_FIBER_TRAPDOOR, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_TRAPDOOR = register(Keys.GRAY_SHROOM_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.GRAY_SHROOM_TRAPDOOR, new Item.Settings().registryKey(Keys.GRAY_SHROOM_TRAPDOOR).useBlockPrefixedTranslationKey()));
    public static final Item RUBY_TRAPDOOR = register(Keys.RUBY_TRAPDOOR.getValue().getPath(), new BlockItem(AerialHellBlocks.RUBY_TRAPDOOR, new Item.Settings().registryKey(Keys.RUBY_TRAPDOOR).useBlockPrefixedTranslationKey()));

    //buttons
    public static final Item STELLAR_STONE_BUTTON = register(Keys.STELLAR_STONE_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BUTTON, new Item.Settings().registryKey(Keys.STELLAR_STONE_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE_BUTTON = register(Keys.STELLAR_COBBLESTONE_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_BUTTON, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS_BUTTON = register(Keys.STELLAR_STONE_BRICKS_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_BUTTON, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_TREE_BUTTON = register(Keys.AERIAL_TREE_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BUTTON, new Item.Settings().registryKey(Keys.AERIAL_TREE_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item GOLDEN_BEECH_BUTTON = register(Keys.GOLDEN_BEECH_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BUTTON, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item COPPER_PINE_BUTTON = register(Keys.COPPER_PINE_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BUTTON, new Item.Settings().registryKey(Keys.COPPER_PINE_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item LAPIS_ROBINIA_BUTTON = register(Keys.LAPIS_ROBINIA_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BUTTON, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item SHADOW_PINE_BUTTON = register(Keys.SHADOW_PINE_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BUTTON, new Item.Settings().registryKey(Keys.SHADOW_PINE_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item STELLAR_JUNGLE_TREE_BUTTON = register(Keys.STELLAR_JUNGLE_TREE_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BUTTON, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_BUTTON).useBlockPrefixedTranslationKey(), 100));
    public static final Item SKY_CACTUS_FIBER_BUTTON = register(Keys.SKY_CACTUS_FIBER_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BUTTON, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_BUTTON).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_BUTTON = register(Keys.GRAY_SHROOM_BUTTON.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BUTTON, new Item.Settings().registryKey(Keys.GRAY_SHROOM_BUTTON).useBlockPrefixedTranslationKey(), 30));
    public static final Item GLAUCOPHANITE_BUTTON = register(Keys.GLAUCOPHANITE_BUTTON.getValue().getPath(), new BlockItem(AerialHellBlocks.GLAUCOPHANITE_BUTTON, new Item.Settings().registryKey(Keys.GLAUCOPHANITE_BUTTON).useBlockPrefixedTranslationKey()));

    //pressure plates
    public static final Item STELLAR_STONE_PRESSURE_PLATE = register(Keys.STELLAR_STONE_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.STELLAR_STONE_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE_PRESSURE_PLATE = register(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS_PRESSURE_PLATE = register(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item AERIAL_TREE_PRESSURE_PLATE = register(Keys.AERIAL_TREE_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.AERIAL_TREE_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_PRESSURE_PLATE = register(Keys.GOLDEN_BEECH_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_PRESSURE_PLATE = register(Keys.COPPER_PINE_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.COPPER_PINE_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_PRESSURE_PLATE = register(Keys.LAPIS_ROBINIA_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_PRESSURE_PLATE = register(Keys.SHADOW_PINE_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.SHADOW_PINE_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_PRESSURE_PLATE = register(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_PRESSURE_PLATE = register(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_PRESSURE_PLATE = register(Keys.GRAY_SHROOM_PRESSURE_PLATE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.GRAY_SHROOM_PRESSURE_PLATE).useBlockPrefixedTranslationKey(), 100));
    public static final Item GLAUCOPHANITE_PRESSURE_PLATE = register(Keys.GLAUCOPHANITE_PRESSURE_PLATE.getValue().getPath(), new BlockItem(AerialHellBlocks.GLAUCOPHANITE_PRESSURE_PLATE, new Item.Settings().registryKey(Keys.GLAUCOPHANITE_PRESSURE_PLATE).useBlockPrefixedTranslationKey()));

    //slabs
    public static final Item AERIAL_TREE_SLAB = register(Keys.AERIAL_TREE_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_SLAB, new Item.Settings().registryKey(Keys.AERIAL_TREE_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item GOLDEN_BEECH_SLAB = register(Keys.GOLDEN_BEECH_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_SLAB, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item COPPER_PINE_SLAB = register(Keys.COPPER_PINE_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_SLAB, new Item.Settings().registryKey(Keys.COPPER_PINE_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item LAPIS_ROBINIA_SLAB = register(Keys.LAPIS_ROBINIA_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_SLAB, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item SHADOW_PINE_SLAB = register(Keys.SHADOW_PINE_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_SLAB, new Item.Settings().registryKey(Keys.SHADOW_PINE_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item STELLAR_JUNGLE_TREE_SLAB = register(Keys.STELLAR_JUNGLE_TREE_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SLAB, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_SLAB).useBlockPrefixedTranslationKey(), 150));
    public static final Item SKY_CACTUS_FIBER_SLAB = register(Keys.SKY_CACTUS_FIBER_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_SLAB, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_SLAB = register(Keys.GRAY_SHROOM_SLAB.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_SLAB, new Item.Settings().registryKey(Keys.GRAY_SHROOM_SLAB).useBlockPrefixedTranslationKey(), 50));
    public static final Item STELLAR_STONE_SLAB = register(Keys.STELLAR_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_SLAB, new Item.Settings().registryKey(Keys.STELLAR_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE_SLAB = register(Keys.STELLAR_COBBLESTONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_SLAB, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS_SLAB = register(Keys.STELLAR_STONE_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_SLAB, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_STONE_SLAB = register(Keys.MOSSY_STELLAR_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_SLAB = register(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_SLAB = register(Keys.SLIPPERY_SAND_STONE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB, new Item.Settings().registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item POLISHED_GLAUCOPHANITE_SLAB = register(Keys.POLISHED_GLAUCOPHANITE_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_SLAB, new Item.Settings().registryKey(Keys.POLISHED_GLAUCOPHANITE_SLAB).useBlockPrefixedTranslationKey()));
    public static final Item MAGMATIC_GEL_SLAB = register(Keys.MAGMATIC_GEL_SLAB.getValue().getPath(), new BlockItem(AerialHellBlocks.MAGMATIC_GEL_SLAB, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_SLAB).useBlockPrefixedTranslationKey()));

    //stairs
    public static final Item AERIAL_TREE_STAIRS = register(Keys.AERIAL_TREE_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_STAIRS, new Item.Settings().registryKey(Keys.AERIAL_TREE_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_STAIRS = register(Keys.GOLDEN_BEECH_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_STAIRS, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_STAIRS = register(Keys.COPPER_PINE_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_STAIRS, new Item.Settings().registryKey(Keys.COPPER_PINE_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_STAIRS = register(Keys.LAPIS_ROBINIA_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_STAIRS, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_STAIRS = register(Keys.SHADOW_PINE_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_STAIRS, new Item.Settings().registryKey(Keys.SHADOW_PINE_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_STAIRS = register(Keys.STELLAR_JUNGLE_TREE_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STAIRS, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_STAIRS).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_STAIRS = register(Keys.SKY_CACTUS_FIBER_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_STAIRS, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_STAIRS = register(Keys.GRAY_SHROOM_STAIRS.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_STAIRS, new Item.Settings().registryKey(Keys.GRAY_SHROOM_STAIRS).useBlockPrefixedTranslationKey(), 100));
    public static final Item STELLAR_STONE_STAIRS = register(Keys.STELLAR_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_STAIRS, new Item.Settings().registryKey(Keys.STELLAR_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_COBBLESTONE_STAIRS = register(Keys.STELLAR_COBBLESTONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_STAIRS, new Item.Settings().registryKey(Keys.STELLAR_COBBLESTONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_STONE_BRICKS_STAIRS = register(Keys.STELLAR_STONE_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.STELLAR_STONE_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_STONE_STAIRS = register(Keys.MOSSY_STELLAR_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_STAIRS = register(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS, new Item.Settings().registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_STAIRS = register(Keys.SLIPPERY_SAND_STONE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS, new Item.Settings().registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item POLISHED_GLAUCOPHANITE_STAIRS = register(Keys.POLISHED_GLAUCOPHANITE_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_STAIRS, new Item.Settings().registryKey(Keys.POLISHED_GLAUCOPHANITE_STAIRS).useBlockPrefixedTranslationKey()));
    public static final Item MAGMATIC_GEL_STAIRS = register(Keys.MAGMATIC_GEL_STAIRS.getValue().getPath(), new BlockItem(AerialHellBlocks.MAGMATIC_GEL_STAIRS, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_STAIRS).useBlockPrefixedTranslationKey()));

    //signs
    public static final Item AERIAL_TREE_SIGN = register(Keys.AERIAL_TREE_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.AERIAL_TREE_STANDING_SIGN, AerialHellBlocks.AERIAL_TREE_WALL_SIGN, new Item.Settings().registryKey(Keys.AERIAL_TREE_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_SIGN = register(Keys.GOLDEN_BEECH_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN, AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_SIGN = register(Keys.COPPER_PINE_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.COPPER_PINE_STANDING_SIGN, AerialHellBlocks.COPPER_PINE_WALL_SIGN, new Item.Settings().registryKey(Keys.COPPER_PINE_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_SIGN = register(Keys.LAPIS_ROBINIA_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN, AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_SIGN = register(Keys.SHADOW_PINE_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.SHADOW_PINE_STANDING_SIGN, AerialHellBlocks.SHADOW_PINE_WALL_SIGN, new Item.Settings().registryKey(Keys.SHADOW_PINE_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_SIGN = register(Keys.STELLAR_JUNGLE_TREE_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN, AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_SIGN = register(Keys.SKY_CACTUS_FIBER_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN, AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_SIGN).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_SIGN = register(Keys.GRAY_SHROOM_SIGN.getValue().getPath(), new SignItem(AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN, AerialHellBlocks.GRAY_SHROOM_WALL_SIGN, new Item.Settings().registryKey(Keys.GRAY_SHROOM_SIGN).useBlockPrefixedTranslationKey()));

    //hanging signs
    public static final Item AERIAL_TREE_HANGING_SIGN = register(Keys.AERIAL_TREE_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.AERIAL_TREE_HANGING_SIGN, AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.AERIAL_TREE_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item GOLDEN_BEECH_HANGING_SIGN = register(Keys.GOLDEN_BEECH_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN, AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item COPPER_PINE_HANGING_SIGN = register(Keys.COPPER_PINE_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.COPPER_PINE_HANGING_SIGN, AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.COPPER_PINE_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item LAPIS_ROBINIA_HANGING_SIGN = register(Keys.LAPIS_ROBINIA_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN, AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item SHADOW_PINE_HANGING_SIGN = register(Keys.SHADOW_PINE_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.SHADOW_PINE_HANGING_SIGN, AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.SHADOW_PINE_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item STELLAR_JUNGLE_TREE_HANGING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN, AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item SKY_CACTUS_FIBER_HANGING_SIGN = register(Keys.SKY_CACTUS_FIBER_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN, AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));
    public static final Item GRAY_SHROOM_HANGING_SIGN = register(Keys.GRAY_SHROOM_HANGING_SIGN.getValue().getPath(), new HangingSignItem(AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN, AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN, new Item.Settings().registryKey(Keys.GRAY_SHROOM_HANGING_SIGN).useBlockPrefixedTranslationKey().maxCount(16)));

    //crafting tables
    public static final Item AERIAL_TREE_CRAFTING_TABLE = register(Keys.AERIAL_TREE_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.AERIAL_TREE_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_CRAFTING_TABLE = register(Keys.GOLDEN_BEECH_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_CRAFTING_TABLE = register(Keys.COPPER_PINE_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.COPPER_PINE_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_CRAFTING_TABLE = register(Keys.LAPIS_ROBINIA_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_CRAFTING_TABLE = register(Keys.SHADOW_PINE_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.SHADOW_PINE_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_CRAFTING_TABLE = register(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_CRAFTING_TABLE = register(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_CRAFTING_TABLE = register(Keys.GRAY_SHROOM_CRAFTING_TABLE.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE, new Item.Settings().registryKey(Keys.GRAY_SHROOM_CRAFTING_TABLE).useBlockPrefixedTranslationKey(), 100));

    //barrels
    public static final Item AERIAL_TREE_BARREL = register(Keys.AERIAL_TREE_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BARREL, new Item.Settings().registryKey(Keys.AERIAL_TREE_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_BARREL = register(Keys.GOLDEN_BEECH_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BARREL, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_BARREL = register(Keys.COPPER_PINE_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BARREL, new Item.Settings().registryKey(Keys.COPPER_PINE_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_BARREL = register(Keys.LAPIS_ROBINIA_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BARREL, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_BARREL = register(Keys.SHADOW_PINE_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BARREL, new Item.Settings().registryKey(Keys.SHADOW_PINE_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_BARREL = register(Keys.STELLAR_JUNGLE_TREE_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_BARREL).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_BARREL = register(Keys.SKY_CACTUS_FIBER_BARREL.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BARREL, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_BARREL).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_BARREL = register(Keys.GRAY_SHROOM_BARREL.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BARREL, new Item.Settings().registryKey(Keys.GRAY_SHROOM_BARREL).useBlockPrefixedTranslationKey(), 100));

    //composters
    public static final Item AERIAL_TREE_COMPOSTER = register(Keys.AERIAL_TREE_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_COMPOSTER, new Item.Settings().registryKey(Keys.AERIAL_TREE_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item GOLDEN_BEECH_COMPOSTER = register(Keys.GOLDEN_BEECH_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_COMPOSTER, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item COPPER_PINE_COMPOSTER = register(Keys.COPPER_PINE_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_COMPOSTER, new Item.Settings().registryKey(Keys.COPPER_PINE_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item LAPIS_ROBINIA_COMPOSTER = register(Keys.LAPIS_ROBINIA_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item SHADOW_PINE_COMPOSTER = register(Keys.SHADOW_PINE_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_COMPOSTER, new Item.Settings().registryKey(Keys.SHADOW_PINE_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item STELLAR_JUNGLE_TREE_COMPOSTER = register(Keys.STELLAR_JUNGLE_TREE_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_COMPOSTER, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_COMPOSTER).useBlockPrefixedTranslationKey(), 300));
    public static final Item SKY_CACTUS_FIBER_COMPOSTER = register(Keys.SKY_CACTUS_FIBER_COMPOSTER.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_COMPOSTER, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_COMPOSTER).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_COMPOSTER = register(Keys.GRAY_SHROOM_COMPOSTER.getValue().getPath(), new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_COMPOSTER, new Item.Settings().registryKey(Keys.GRAY_SHROOM_COMPOSTER).useBlockPrefixedTranslationKey(), 100));

    //decorative
    public static final Item AERIAL_TREE_VINE_ROPE_SPOOL = register(Keys.AERIAL_TREE_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.AERIAL_TREE_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.AERIAL_TREE_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item GOLDEN_BEECH_VINE_ROPE_SPOOL = register(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.GOLDEN_BEECH_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item COPPER_PINE_VINE_ROPE_SPOOL = register(Keys.COPPER_PINE_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.COPPER_PINE_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.COPPER_PINE_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item LAPIS_ROBINIA_VINE_ROPE_SPOOL = register(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item SHADOW_PINE_VINE_ROPE_SPOOL = register(Keys.SHADOW_PINE_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.SHADOW_PINE_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.SHADOW_PINE_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = register(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = register(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));
    public static final Item GRAY_SHROOM_VINE_ROPE_SPOOL = register(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL.getValue().getPath(), new BlockItem(AerialHellBlocks.GRAY_SHROOM_VINE_ROPE_SPOOL, new Item.Settings().registryKey(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL).useBlockPrefixedTranslationKey()));

    //item for crafts
    public static final Item SKY_STICK = register(Keys.SKY_STICK.getValue().getPath(),new BurnableItem(new Item.Settings().registryKey(Keys.SKY_STICK), 100));
    public static final Item SKY_BOWL = register(Keys.SKY_BOWL.getValue().getPath(),new BurnableItem(new Item.Settings().registryKey(Keys.SKY_BOWL), 200));
    public static final Item SHADOW_SHARD = register(Keys.SHADOW_SHARD.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.SHADOW_SHARD).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item ROTTEN_LEATHER = register(Keys.ROTTEN_LEATHER.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.ROTTEN_LEATHER)));
    public static final Item VENOMOUS_SNAKE_SKIN = register(Keys.VENOMOUS_SNAKE_SKIN.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.VENOMOUS_SNAKE_SKIN)));
    public static final Item ARSONIST_UPGRADE_SMITHING_TEMPLATE = register(Keys.ARSONIST_UPGRADE_SMITHING_TEMPLATE.getValue().getPath(), ItemHelper.SmithingTemplate.createUpgradeTemplate("arsonist", new Item.Settings().registryKey(Keys.ARSONIST_UPGRADE_SMITHING_TEMPLATE)));

    //projectile item
    public static final Item STELLAR_EGG = register(Keys.STELLAR_EGG.getValue().getPath(),new StellarEggItem(new Item.Settings().registryKey(Keys.STELLAR_EGG).maxCount(16)));
    public static final Item DIMENSION_SHATTERER_PROJECTILE = register(Keys.DIMENSION_SHATTERER_PROJECTILE.getValue().getPath(),new DimensionShattererProjectileItem(new Item.Settings().registryKey(Keys.DIMENSION_SHATTERER_PROJECTILE).maxCount(16)));

    //shurikens
    public static final Item IRON_SHURIKEN = register(Keys.IRON_SHURIKEN.getValue().getPath(), new IronShurikenItem(new Item.Settings().rarity(Rarity.COMMON).registryKey(Keys.IRON_SHURIKEN)));
    public static final Item GOLD_SHURIKEN = register(Keys.GOLD_SHURIKEN.getValue().getPath(), new GoldShurikenItem(new Item.Settings().rarity(Rarity.COMMON).registryKey(Keys.GOLD_SHURIKEN)));
    public static final Item DIAMOND_SHURIKEN = register(Keys.DIAMOND_SHURIKEN.getValue().getPath(), new DiamondShurikenItem(new Item.Settings().rarity(Rarity.COMMON).registryKey(Keys.DIAMOND_SHURIKEN)));
    public static final Item NETHERITE_SHURIKEN = register(Keys.NETHERITE_SHURIKEN.getValue().getPath(), new NetheriteShurikenItem(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(Keys.NETHERITE_SHURIKEN)));
    public static final Item RUBY_SHURIKEN = register(Keys.RUBY_SHURIKEN.getValue().getPath(), new RubyShurikenItem(new Item.Settings().rarity(Rarity.COMMON).registryKey(Keys.RUBY_SHURIKEN)));
    public static final Item AZURITE_SHURIKEN = register(Keys.AZURITE_SHURIKEN.getValue().getPath(), new AzuriteShurikenItem(new Item.Settings().rarity(Rarity.COMMON).registryKey(Keys.AZURITE_SHURIKEN)));
    public static final Item MAGMATIC_GEL_SHURIKEN = register(Keys.MAGMATIC_GEL_SHURIKEN.getValue().getPath(), new MagmaticGelShurikenItem(new Item.Settings().rarity(AerialHellRarities.FROZEN).registryKey(Keys.MAGMATIC_GEL_SHURIKEN)));
    public static final Item VOLUCITE_SHURIKEN = register(Keys.VOLUCITE_SHURIKEN.getValue().getPath(), new VoluciteShurikenItem(new Item.Settings().rarity(AerialHellRarities.VIBRANT).registryKey(Keys.VOLUCITE_SHURIKEN)));
    public static final Item OBSIDIAN_SHURIKEN = register(Keys.OBSIDIAN_SHURIKEN.getValue().getPath(), new ObsidianShurikenItem(new Item.Settings().rarity(Rarity.EPIC).registryKey(Keys.OBSIDIAN_SHURIKEN)));
    public static final Item LUNATIC_CRYSTAL_SHURIKEN = register(Keys.LUNATIC_CRYSTAL_SHURIKEN.getValue().getPath(), new LunaticCrystalShurikenItem(new Item.Settings().rarity(AerialHellRarities.LEGENDARY).registryKey(Keys.LUNATIC_CRYSTAL_SHURIKEN)));
    public static final Item ARSONIST_SHURIKEN = register(Keys.ARSONIST_SHURIKEN.getValue().getPath(), new ArsonistShurikenItem(new Item.Settings().rarity(AerialHellRarities.MYTHICAL).registryKey(Keys.ARSONIST_SHURIKEN)));
    public static final Item LIGHTNING_SHURIKEN = register(Keys.LIGHTNING_SHURIKEN.getValue().getPath(), new LightningShurikenItem(new Item.Settings().rarity(Rarity.UNCOMMON).registryKey(Keys.LIGHTNING_SHURIKEN)));


    //food
    public static final Item AERIAL_BERRY = register(Keys.AERIAL_BERRY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AERIAL_BERRY).food(AerialHellFoods.Properties.AERIAL_BERRY)));
    public static final Item ROASTED_AERIAL_BERRY = register(Keys.ROASTED_AERIAL_BERRY.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.ROASTED_AERIAL_BERRY).food(AerialHellFoods.Properties.ROASTED_AERIAL_BERRY)));
    public static final Item VIBRANT_AERIAL_BERRY = register(Keys.VIBRANT_AERIAL_BERRY.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.VIBRANT_AERIAL_BERRY).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_AERIAL_BERRY)));
    public static final Item FROZEN_AERIAL_BERRY = register(Keys.FROZEN_AERIAL_BERRY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_AERIAL_BERRY).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_AERIAL_BERRY, AerialHellFoods.Consumables.FROZEN_AERIAL_BERRY)));
    public static final Item STELLAR_BREAD = register(Keys.STELLAR_BREAD.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.STELLAR_BREAD).food(AerialHellFoods.Properties.STELLAR_BREAD)));
    public static final Item FROZEN_MUTTON = register(Keys.FROZEN_MUTTON.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_MUTTON).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_MUTTON, AerialHellFoods.Consumables.FROZEN_MUTTON)));
    public static final Item VIBRANT_CHICKEN = register(Keys.VIBRANT_CHICKEN.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VIBRANT_CHICKEN).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_CHICKEN, AerialHellFoods.Consumables.VIBRANT_CHICKEN)));
    public static final Item FROZEN_CHICKEN = register(Keys.FROZEN_CHICKEN.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_CHICKEN).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_CHICKEN, AerialHellFoods.Consumables.FROZEN_CHICKEN)));
    public static final Item RUBY_AERIAL_BERRY = register(Keys.RUBY_AERIAL_BERRY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_AERIAL_BERRY).rarity(Rarity.RARE).food(AerialHellFoods.Properties.RUBY_AERIAL_BERRY, AerialHellFoods.Consumables.RUBY_AERIAL_BERRY)));
    public static final Item VOLUCITE_AERIAL_BERRY = register(Keys.VOLUCITE_AERIAL_BERRY.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VOLUCITE_AERIAL_BERRY).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.VOLUCITE_AERIAL_BERRY)));
    public static final Item GLOWING_STICK_FRUIT = register(Keys.GLOWING_STICK_FRUIT.getValue().getPath(), new BlockItem(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES, new Item.Settings().registryKey(Keys.GLOWING_STICK_FRUIT).food(AerialHellFoods.Properties.GLOWING_STICK_FRUIT)));
    public static final Item VIBRANT_GLOWING_STICK_FRUIT = register(Keys.VIBRANT_GLOWING_STICK_FRUIT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VIBRANT_GLOWING_STICK_FRUIT).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_GLOWING_STICK_FRUIT)));
    public static final Item FROZEN_GLOWING_STICK_FRUIT = register(Keys.FROZEN_GLOWING_STICK_FRUIT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_GLOWING_STICK_FRUIT).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_GLOWING_STICK_FRUIT, AerialHellFoods.Consumables.FROZEN_GLOWING_STICK_FRUIT)));
    public static final Item CORTINARIUS_VIOLACEUS_PIECE = register(Keys.CORTINARIUS_VIOLACEUS_PIECE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.CORTINARIUS_VIOLACEUS_PIECE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.CORTINARIUS_VIOLACEUS_PIECE, AerialHellFoods.Consumables.CORTINARIUS_VIOLACEUS_PIECE)));
    public static final Item GANODERMA_APPLANATUM_PIECE = register(Keys.GANODERMA_APPLANATUM_PIECE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.GANODERMA_APPLANATUM_PIECE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.GANODERMA_APPLANATUM_PIECE, AerialHellFoods.Consumables.GANODERMA_APPLANATUM_PIECE)));
    public static final Item DARK_SHADOW_FRUIT = register(Keys.DARK_SHADOW_FRUIT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.DARK_SHADOW_FRUIT).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.DARK_SHADOW_FRUIT, AerialHellFoods.Consumables.DARK_SHADOW_FRUIT)));
    public static final Item PURPLE_SHADOW_FRUIT = register(Keys.PURPLE_SHADOW_FRUIT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.PURPLE_SHADOW_FRUIT).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.PURPLE_SHADOW_FRUIT, AerialHellFoods.Consumables.PURPLE_SHADOW_FRUIT)));
    public static final Item SHADOW_FRUIT_STEW = register(Keys.SHADOW_FRUIT_STEW.getValue().getPath(), new SkySoupItem(new Item.Settings().registryKey(Keys.SHADOW_FRUIT_STEW).rarity(AerialHellRarities.CORRUPTED).food(AerialHellFoods.Properties.SHADOW_FRUIT_STEW, AerialHellFoods.Consumables.SHADOW_FRUIT_STEW)));
    public static final Item SOLID_ETHER_SOUP = register(Keys.SOLID_ETHER_SOUP.getValue().getPath(), new SkySoupItem(new Item.Settings().registryKey(Keys.SOLID_ETHER_SOUP).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.SOLID_ETHER_SOUP, AerialHellFoods.Consumables.SOLID_ETHER_SOUP)));
    public static final Item VIBRANT_SOLID_ETHER_SOUP = register(Keys.VIBRANT_SOLID_ETHER_SOUP.getValue().getPath(), new SkySoupItem(new Item.Settings().registryKey(Keys.VIBRANT_SOLID_ETHER_SOUP).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.VIBRANT_SOLID_ETHER_SOUP)));
    public static final Item FROZEN_SOLID_ETHER_SOUP = register(Keys.FROZEN_SOLID_ETHER_SOUP.getValue().getPath(), new SkySoupItem(new Item.Settings().registryKey(Keys.FROZEN_SOLID_ETHER_SOUP).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.FROZEN_SOLID_ETHER_SOUP)));
    public static final Item SHADOW_SPIDER_EYE = register(Keys.SHADOW_SPIDER_EYE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.SHADOW_SPIDER_EYE).rarity(AerialHellRarities.CORRUPTED).food(AerialHellFoods.Properties.SHADOW_SPIDER_EYE, AerialHellFoods.Consumables.SHADOW_SPIDER_EYE)));
    public static final Item PHANTOM_MEAT = register(Keys.PHANTOM_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.PHANTOM_MEAT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.PHANTOM_MEAT, AerialHellFoods.Consumables.PHANTOM_MEAT)));
    public static final Item VIBRANT_PHANTOM_MEAT = register(Keys.VIBRANT_PHANTOM_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VIBRANT_PHANTOM_MEAT).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_PHANTOM_MEAT, AerialHellFoods.Consumables.VIBRANT_PHANTOM_MEAT)));
    public static final Item FROZEN_PHANTOM_MEAT = register(Keys.FROZEN_PHANTOM_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_PHANTOM_MEAT).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_PHANTOM_MEAT, AerialHellFoods.Consumables.FROZEN_PHANTOM_MEAT)));
    public static final Item COOKED_PHANTOM_MEAT = register(Keys.COOKED_PHANTOM_MEAT.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.COOKED_PHANTOM_MEAT).food(AerialHellFoods.Properties.COOKED_PHANTOM_MEAT)));
    public static final Item TURTLE_MEAT = register(Keys.TURTLE_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.TURTLE_MEAT).food(FoodComponents.BEEF)));
    public static final Item VIBRANT_TURTLE_MEAT = register(Keys.VIBRANT_TURTLE_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VIBRANT_TURTLE_MEAT).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_TURTLE_MEAT, AerialHellFoods.Consumables.VIBRANT_TURTLE_MEAT)));
    public static final Item FROZEN_TURTLE_MEAT = register(Keys.FROZEN_TURTLE_MEAT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.FROZEN_TURTLE_MEAT).rarity(AerialHellRarities.FROZEN).food(AerialHellFoods.Properties.FROZEN_TURTLE_MEAT, AerialHellFoods.Consumables.FROZEN_TURTLE_MEAT)));
    public static final Item COOKED_TURTLE_MEAT = register(Keys.COOKED_TURTLE_MEAT.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.COOKED_TURTLE_MEAT).food(FoodComponents.COOKED_BEEF)));
    public static final Item GODS_VOLUCITE_AERIAL_BERRY = register(Keys.GODS_VOLUCITE_AERIAL_BERRY.getValue().getPath(), new FoilItem(new Item.Settings().registryKey(Keys.GODS_VOLUCITE_AERIAL_BERRY).rarity(AerialHellRarities.MYTHICAL).food(AerialHellFoods.Properties.GODS_VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.GODS_VOLUCITE_AERIAL_BERRY)));
    public static final Item COPPER_PINE_CONE = register(Keys.COPPER_PINE_CONE.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.COPPER_PINE_CONE).food(AerialHellFoods.Properties.COPPER_PINE_CONE)));
    public static final Item AZURITE_COPPER_PINE_CONE = register(Keys.AZURITE_COPPER_PINE_CONE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_COPPER_PINE_CONE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.AZURITE_COPPER_PINE_CONE, AerialHellFoods.Consumables.AZURITE_COPPER_PINE_CONE)));
    public static final Item PHOENIX_FEATHER = register(Keys.PHOENIX_FEATHER.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.PHOENIX_FEATHER).rarity(AerialHellRarities.LEGENDARY).food(AerialHellFoods.Properties.PHOENIX_FEATHER, AerialHellFoods.Consumables.PHOENIX_FEATHER)));
    public static final Item SKY_CACTUS_FIBER = register(Keys.SKY_CACTUS_FIBER.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.SKY_CACTUS_FIBER).food(AerialHellFoods.Properties.SKY_CACTUS_FIBER, AerialHellFoods.Consumables.SKY_CACTUS_FIBER)));
    public static final Item VIBRANT_SKY_CACTUS_FIBER = register(Keys.VIBRANT_SKY_CACTUS_FIBER.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.VIBRANT_SKY_CACTUS_FIBER).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_SKY_CACTUS_FIBER, AerialHellFoods.Consumables.VIBRANT_SKY_CACTUS_FIBER)));
    public static final Item WHITE_SOLID_ETHER_FRAGMENT = register(Keys.WHITE_SOLID_ETHER_FRAGMENT.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.WHITE_SOLID_ETHER_FRAGMENT).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT)));
    public static final Item BLUE_SOLID_ETHER_FRAGMENT = register(Keys.BLUE_SOLID_ETHER_FRAGMENT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.BLUE_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.BLUE_SOLID_ETHER_FRAGMENT)));
    public static final Item GOLDEN_SOLID_ETHER_FRAGMENT = register(Keys.GOLDEN_SOLID_ETHER_FRAGMENT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.GOLDEN_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GOLDEN_SOLID_ETHER_FRAGMENT)));
    public static final Item GREEN_SOLID_ETHER_FRAGMENT = register(Keys.GREEN_SOLID_ETHER_FRAGMENT.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.GREEN_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GREEN_SOLID_ETHER_FRAGMENT)));
    public static final Item PURPLE_SOLID_ETHER_FRAGMENT = register(Keys.PURPLE_SOLID_ETHER_FRAGMENT.getValue().getPath(),new Item(new Item.Settings().registryKey(Keys.PURPLE_SOLID_ETHER_FRAGMENT).rarity(AerialHellRarities.CORRUPTED).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.PURPLE_SOLID_ETHER_FRAGMENT)));
    public static final Item GOLDEN_NETHER_MEAT_PIECE = register(Keys.GOLDEN_NETHER_MEAT_PIECE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.GOLDEN_NETHER_MEAT_PIECE).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_MEAT_PIECE, AerialHellFoods.Consumables.GOLDEN_NETHER_MEAT_PIECE)));
    public static final Item GOLDEN_NETHER_STEAK = register(Keys.GOLDEN_NETHER_STEAK.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.GOLDEN_NETHER_STEAK).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.GOLDEN_NETHER_STEAK)));
    public static final Item VIBRANT_GOLDEN_NETHER_STEAK = register(Keys.VIBRANT_GOLDEN_NETHER_STEAK.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VIBRANT_GOLDEN_NETHER_STEAK).rarity(AerialHellRarities.VIBRANT).food(AerialHellFoods.Properties.VIBRANT_GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.VIBRANT_GOLDEN_NETHER_STEAK)));

    //buckets
    public static final Item IRON_LIQUID_OF_GODS_BUCKET = register(Keys.IRON_LIQUID_OF_GODS_BUCKET.getValue().getPath(), new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, (new Item.Settings().registryKey(Keys.IRON_LIQUID_OF_GODS_BUCKET)).maxCount(1)));
    public static final Item RUBY_LIQUID_OF_GODS_BUCKET = register(Keys.RUBY_LIQUID_OF_GODS_BUCKET.getValue().getPath(), new RubyLiquidOfGodsBucketItem(new Item.Settings().registryKey(Keys.RUBY_LIQUID_OF_GODS_BUCKET).maxCount(1)));
    public static final Item RUBY_BUCKET = register(Keys.RUBY_BUCKET.getValue().getPath(), new RubyBucketItem(new Item.Settings().registryKey(Keys.RUBY_BUCKET).maxCount(16)));
    public static final Item RUBY_WATER_BUCKET = register(Keys.RUBY_WATER_BUCKET.getValue().getPath(), new RubyWaterBucketItem(new Item.Settings().registryKey(Keys.RUBY_WATER_BUCKET).maxCount(1)));
    public static final Item RUBY_MILK_BUCKET = register(Keys.RUBY_MILK_BUCKET.getValue().getPath(), new Item(new Item.Settings().registryKey((Keys.RUBY_MILK_BUCKET)).recipeRemainder(RUBY_BUCKET).component(DataComponentTypes.CONSUMABLE, ConsumableComponents.MILK_BUCKET).useRemainder(RUBY_BUCKET).maxCount(1)));

    //arrows & bows
    public static final Item RUBY_BLOWPIPE_ARROW = register(Keys.RUBY_BLOWPIPE_ARROW.getValue().getPath(), new AerialArrowItem(new Item.Settings().registryKey(Keys.RUBY_BLOWPIPE_ARROW)));
    public static final Item VOLUCITE_BLOWPIPE_ARROW = register(Keys.VOLUCITE_BLOWPIPE_ARROW.getValue().getPath(), new AerialArrowItem(new Item.Settings().registryKey(Keys.VOLUCITE_BLOWPIPE_ARROW).rarity(AerialHellRarities.VIBRANT)));

    public static final Item RUBY_BLOWPIPE = register(Keys.RUBY_BLOWPIPE.getValue().getPath(), new BlowpipeItem(new Item.Settings().registryKey(Keys.RUBY_BLOWPIPE).maxCount(1).maxDamage(200), 1.7F));
    public static final Item VOLUCITE_BLOWPIPE = register(Keys.VOLUCITE_BLOWPIPE.getValue().getPath(), new BlowpipeItem(new Item.Settings().registryKey(Keys.VOLUCITE_BLOWPIPE).rarity(AerialHellRarities.VIBRANT).maxCount(1).maxDamage(400), 2.4F));

    //music discs
    public static final Item MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = register(Keys.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP).maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.AERIAL_HELL_THEME_TOMMAUP)));
    public static final Item MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = register(Keys.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER).maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.SWEDEN_ANDREAS_ZOELLER)));
    public static final Item MUSIC_DISC_ENTHUSIAST_TOURS = register(Keys.MUSIC_DISC_ENTHUSIAST_TOURS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MUSIC_DISC_ENTHUSIAST_TOURS).maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.ENTHUSIAST_TOURS)));
    public static final Item MUSIC_DISC_BMINOR_ARULO = register(Keys.MUSIC_DISC_BMINOR_ARULO.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MUSIC_DISC_BMINOR_ARULO).maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.BMINOR_ARULO)));
    public static final Item MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = register(Keys.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP).maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.RETRO_EXPLORATION_TOMMAUP)));

    //tools
    public static final Item SKY_WOOD_PICKAXE = register(Keys.SKY_WOOD_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.SKY_WOOD_PICKAXE).pickaxe(ToolMaterials.SKY_WOOD, 1.0F, -2.8F)));
    public static final Item STELLAR_STONE_PICKAXE = register(Keys.STELLAR_STONE_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.STELLAR_STONE_PICKAXE).pickaxe(ToolMaterials.STELLAR_STONE, 1.0F, -2.8F)));
    public static final Item RUBY_PICKAXE = register(Keys.RUBY_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_PICKAXE).pickaxe(ToolMaterials.RUBY, 1.0F, -2.8F)));
    public static final Item AZURITE_PICKAXE = register(Keys.AZURITE_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_PICKAXE).pickaxe(ToolMaterials.AZURITE, 1.0F, -2.8F)));
    public static final Item MAGMATIC_GEL_PICKAXE = register(Keys.MAGMATIC_GEL_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_PICKAXE).pickaxe(ToolMaterials.MAGMATIC_GEL, 1.0F, -2.8F)));
    public static final Item OBSIDIAN_PICKAXE = register(Keys.OBSIDIAN_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_PICKAXE).pickaxe(ToolMaterials.OBSIDIAN, 1.0F, -2.8F).rarity(Rarity.EPIC)));
    public static final Item VOLUCITE_PICKAXE = register(Keys.VOLUCITE_PICKAXE.getValue().getPath(), new EffectPickaxeItem(ToolMaterials.VOLUCITE, 1.0F, -2.8F, new Item.Settings().registryKey(Keys.VOLUCITE_PICKAXE).rarity(AerialHellRarities.VIBRANT)));
    public static final Item LUNATIC_PICKAXE = register(Keys.LUNATIC_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_PICKAXE).pickaxe(ToolMaterials.LUNATIC, 1.0F, -2.8F).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item ARSONIST_PICKAXE = register(Keys.ARSONIST_PICKAXE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_PICKAXE).pickaxe(ToolMaterials.ARSONIST, 1.0F, -2.8F).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final Item MAGMA_CUBE_PICKAXE = register(Keys.MAGMA_CUBE_PICKAXE.getValue().getPath(), new EffectPickaxeItem(ToolMaterials.OBSIDIAN, 1.0F, -2.8F, new Item.Settings().registryKey(Keys.MAGMA_CUBE_PICKAXE).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item STELLAR_STONE_BREAKER = register(Keys.STELLAR_STONE_BREAKER.getValue().getPath(), new AerialHellPickaxeItem(ToolMaterials.BREAKER, 1.0F, -2.8F, new Item.Settings().registryKey(Keys.STELLAR_STONE_BREAKER).rarity(Rarity.EPIC)));

    public static final ShovelItem SKY_WOOD_SHOVEL = register(Keys.SKY_WOOD_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.SKY_WOOD, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.SKY_WOOD_SHOVEL)));
    public static final ShovelItem STELLAR_STONE_SHOVEL = register(Keys.STELLAR_STONE_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.STELLAR_STONE, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.STELLAR_STONE_SHOVEL)));
    public static final ShovelItem RUBY_SHOVEL = register(Keys.RUBY_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.RUBY, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.RUBY_SHOVEL)));
    public static final ShovelItem AZURITE_SHOVEL = register(Keys.AZURITE_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.AZURITE, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.AZURITE_SHOVEL)));
    public static final ShovelItem MAGMATIC_GEL_SHOVEL = register(Keys.MAGMATIC_GEL_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.MAGMATIC_GEL, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_SHOVEL)));
    public static final ShovelItem OBSIDIAN_SHOVEL = register(Keys.OBSIDIAN_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.OBSIDIAN_SHOVEL).rarity(Rarity.EPIC)));
    public static final ShovelItem VOLUCITE_SHOVEL = register(Keys.VOLUCITE_SHOVEL.getValue().getPath(), new EffectShovelItem(ToolMaterials.VOLUCITE, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.VOLUCITE_SHOVEL).rarity(AerialHellRarities.VIBRANT)));
    public static final ShovelItem LUNATIC_SHOVEL = register(Keys.LUNATIC_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.LUNATIC, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.LUNATIC_SHOVEL).rarity(AerialHellRarities.LEGENDARY)));
    public static final ShovelItem ARSONIST_SHOVEL = register(Keys.ARSONIST_SHOVEL.getValue().getPath(), new ShovelItem(ToolMaterials.ARSONIST, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.ARSONIST_SHOVEL).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final ShovelItem MAGMA_CUBE_SHOVEL = register(Keys.MAGMA_CUBE_SHOVEL.getValue().getPath(), new EffectShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Settings().registryKey(Keys.MAGMA_CUBE_SHOVEL).rarity(AerialHellRarities.LEGENDARY)));

    public static final AxeItem SKY_WOOD_AXE = register(Keys.SKY_WOOD_AXE.getValue().getPath(), new AxeItem(ToolMaterials.SKY_WOOD, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.SKY_WOOD_AXE)));
    public static final AxeItem STELLAR_STONE_AXE = register(Keys.STELLAR_STONE_AXE.getValue().getPath(), new AxeItem(ToolMaterials.STELLAR_STONE, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.STELLAR_STONE_AXE)));
    public static final AxeItem RUBY_AXE = register(Keys.RUBY_AXE.getValue().getPath(), new AxeItem(ToolMaterials.RUBY, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.RUBY_AXE)));
    public static final AxeItem AZURITE_AXE = register(Keys.AZURITE_AXE.getValue().getPath(), new AxeItem(ToolMaterials.AZURITE, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.AZURITE_AXE)));
    public static final AxeItem MAGMATIC_GEL_AXE = register(Keys.MAGMATIC_GEL_AXE.getValue().getPath(), new AxeItem(ToolMaterials.MAGMATIC_GEL, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_AXE)));
    public static final AxeItem OBSIDIAN_AXE = register(Keys.OBSIDIAN_AXE.getValue().getPath(), new AxeItem(ToolMaterials.OBSIDIAN, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.OBSIDIAN_AXE).rarity(Rarity.EPIC)));
    public static final AxeItem VOLUCITE_AXE = register(Keys.VOLUCITE_AXE.getValue().getPath(), new EffectAxeItem(ToolMaterials.VOLUCITE, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.VOLUCITE_AXE).rarity(AerialHellRarities.VIBRANT)));
    public static final AxeItem LUNATIC_AXE = register(Keys.LUNATIC_AXE.getValue().getPath(), new AxeItem(ToolMaterials.LUNATIC, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.LUNATIC_AXE).rarity(AerialHellRarities.LEGENDARY)));
    public static final AxeItem ARSONIST_AXE = register(Keys.ARSONIST_AXE.getValue().getPath(), new AxeItem(ToolMaterials.ARSONIST, 6.0F, -3.1F, new Item.Settings().registryKey(Keys.ARSONIST_AXE).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final AxeItem HEAVY_AXE = register(Keys.HEAVY_AXE.getValue().getPath(), new AerialHellAxeItem(ToolMaterials.HEAVY, 6.0F, -3.5F, -0.30F, 0.0F, new Item.Settings().registryKey(Keys.HEAVY_AXE).rarity(Rarity.EPIC)));
    public static final AxeItem AXE_OF_LIGHT = register(Keys.AXE_OF_LIGHT.getValue().getPath(), new EffectAxeItem(ToolMaterials.LUNATIC, 5.0F, -3.1F, new Item.Settings().registryKey(Keys.AXE_OF_LIGHT).rarity(AerialHellRarities.LEGENDARY)));
    public static final AxeItem CURSED_AXE = register(Keys.CURSED_AXE.getValue().getPath(), new AerialHellAxeItem(ToolMaterials.SHADOW, 2.0F, -3.2F, new Item.Settings().registryKey(Keys.CURSED_AXE).rarity(AerialHellRarities.CORRUPTED)));
    public static final AxeItem BERSERK_AXE = register(Keys.BERSERK_AXE.getValue().getPath(), new BerserkAxeItem(ToolMaterials.ARSONIST, 4.0F, -2.5F, new Item.Settings().registryKey(Keys.BERSERK_AXE).rarity(AerialHellRarities.MYTHICAL)));

    public static final HoeItem SKY_WOOD_HOE = register(Keys.SKY_WOOD_HOE.getValue().getPath(), new HoeItem(ToolMaterials.SKY_WOOD, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.SKY_WOOD_HOE)));
    public static final HoeItem STELLAR_STONE_HOE = register(Keys.STELLAR_STONE_HOE.getValue().getPath(), new HoeItem(ToolMaterials.STELLAR_STONE, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.STELLAR_STONE_HOE)));
    public static final HoeItem RUBY_HOE = register(Keys.RUBY_HOE.getValue().getPath(), new HoeItem(ToolMaterials.RUBY, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.RUBY_HOE)));
    public static final HoeItem AZURITE_HOE = register(Keys.AZURITE_HOE.getValue().getPath(), new HoeItem(ToolMaterials.AZURITE, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.AZURITE_HOE)));
    public static final HoeItem MAGMATIC_GEL_HOE = register(Keys.MAGMATIC_GEL_HOE.getValue().getPath(), new HoeItem(ToolMaterials.MAGMATIC_GEL, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.MAGMATIC_GEL_HOE)));
    public static final HoeItem OBSIDIAN_HOE = register(Keys.OBSIDIAN_HOE.getValue().getPath(), new HoeItem(ToolMaterials.OBSIDIAN, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.OBSIDIAN_HOE).rarity(Rarity.EPIC)));
    public static final HoeItem VOLUCITE_HOE = register(Keys.VOLUCITE_HOE.getValue().getPath(), new EffectHoeItem(ToolMaterials.VOLUCITE, 0.0F, -3.0F, new Item.Settings().registryKey(Keys.VOLUCITE_HOE).rarity(AerialHellRarities.VIBRANT)));
    public static final HoeItem LUNATIC_HOE = register(Keys.LUNATIC_HOE.getValue().getPath(), new HoeItem(ToolMaterials.LUNATIC, 0.0F, -1.0F, new Item.Settings().registryKey(Keys.LUNATIC_HOE).rarity(AerialHellRarities.LEGENDARY)));
    public static final HoeItem ARSONIST_HOE = register(Keys.ARSONIST_HOE.getValue().getPath(), new HoeItem(ToolMaterials.ARSONIST, 0.0F, -1.0F, new Item.Settings().registryKey(Keys.ARSONIST_HOE).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final HoeItem REAPER_SCYTHE = register(Keys.REAPER_SCYTHE.getValue().getPath(), new EffectHoeItem(ToolMaterials.SHADOW, -2.8F, 4.0F, new Item.Settings().registryKey(Keys.REAPER_SCYTHE).rarity(AerialHellRarities.MYTHICAL)));

    //weapons
    public static final Item SKY_WOOD_SWORD = register(Keys.SKY_WOOD_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.SKY_WOOD_SWORD).sword(ToolMaterials.SKY_WOOD, 3, -2.4F)));
    public static final Item STELLAR_STONE_SWORD = register(Keys.STELLAR_STONE_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.STELLAR_STONE_SWORD).sword(ToolMaterials.STELLAR_STONE, 3, -2.4F)));
    public static final Item RUBY_SWORD = register(Keys.RUBY_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_SWORD).sword(ToolMaterials.RUBY, 3, -2.4F)));
    public static final Item AZURITE_SWORD = register(Keys.AZURITE_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_SWORD).sword(ToolMaterials.AZURITE, 3, -2.4F)));
    public static final Item MAGMATIC_GEL_SWORD = register(Keys.MAGMATIC_GEL_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_SWORD).sword(ToolMaterials.MAGMATIC_GEL, 3, -2.4F)));
    public static final Item OBSIDIAN_SWORD = register(Keys.OBSIDIAN_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_SWORD).sword(ToolMaterials.OBSIDIAN, 3, -2.4F).rarity(Rarity.EPIC)));
    public static final Item VOLUCITE_SWORD = register(Keys.VOLUCITE_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.VOLUCITE, 3, -2.4F, new Item.Settings().registryKey(Keys.VOLUCITE_SWORD).rarity(AerialHellRarities.VIBRANT)));
    public static final Item LUNATIC_SWORD = register(Keys.LUNATIC_SWORD.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_SWORD).sword(ToolMaterials.LUNATIC, 3, -2.4F).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item ARSONIST_SWORD = register(Keys.ARSONIST_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Settings().registryKey(Keys.ARSONIST_SWORD).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final Item HEAVY_SWORD = register(Keys.HEAVY_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.HEAVY, 3, -2.7F, -0.30F, 0.0F, new Item.Settings().registryKey(Keys.HEAVY_SWORD).fireproof().rarity(Rarity.EPIC)));
    public static final Item HEALTH_BOOST_SWORD = register(Keys.HEALTH_BOOST_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.LUNATIC, 3, -2.4F, 0.0F, 4.0F, new Item.Settings().registryKey(Keys.HEALTH_BOOST_SWORD).fireproof().rarity(Rarity.EPIC)));
    public static final Item NINJA_SWORD = register(Keys.NINJA_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.OBSIDIAN, 2, -1.6F, 0.15F, 0.0F, new Item.Settings().registryKey(Keys.NINJA_SWORD).fireproof().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item NINJA_MASTER_SWORD = register(Keys.NINJA_MASTER_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.OBSIDIAN, 4, -2.4F, 0.15F, 0.0F, new Item.Settings().registryKey(Keys.NINJA_MASTER_SWORD).fireproof().rarity(AerialHellRarities.MYTHICAL)));
    public static final Item GLOUTON_SWORD = register(Keys.GLOUTON_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.RUBY, 3, -2.4F, new Item.Settings().registryKey(Keys.GLOUTON_SWORD).fireproof().fireproof().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item RANDOM_SWORD = register(Keys.RANDOM_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.RUBY, 2, -2.4F, new Item.Settings().registryKey(Keys.RANDOM_SWORD).fireproof().rarity(Rarity.EPIC)));
    public static final Item DISLOYAL_SWORD = register(Keys.DISLOYAL_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Settings().registryKey(Keys.DISLOYAL_SWORD).fireproof().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item CURSED_SWORD = register(Keys.CURSED_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.SHADOW, 1, -2.5F, new Item.Settings().registryKey(Keys.CURSED_SWORD).fireproof().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item ABSOLUTE_ZERO_SWORD = register(Keys.ABSOLUTE_ZERO_SWORD.getValue().getPath(), new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Settings().registryKey(Keys.ABSOLUTE_ZERO_SWORD).fireproof().rarity(AerialHellRarities.MYTHICAL)));
    public static final Item SWORD_OF_LIGHT = register(Keys.SWORD_OF_LIGHT.getValue().getPath(), new EffectSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Settings().registryKey(Keys.SWORD_OF_LIGHT).fireproof().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item ANTIDOTE_SWORD = register(Keys.ANTIDOTE_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.RUBY, 4, -2.4F, new Item.Settings().registryKey(Keys.ANTIDOTE_SWORD).fireproof().rarity(Rarity.EPIC)));
    public static final Item NETHERIAN_KING_SWORD = register(Keys.NETHERIAN_KING_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.OBSIDIAN, 1, -2.4F, new Item.Settings().registryKey(Keys.NETHERIAN_KING_SWORD).fireproof().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item GLASS_CANON_SWORD = register(Keys.GLASS_CANON_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.ARSONIST, 7, -1.6F, new Item.Settings().registryKey(Keys.GLASS_CANON_SWORD).fireproof().rarity(AerialHellRarities.MYTHICAL)));
    public static final Item GOD_SWORD = register(Keys.GOD_SWORD.getValue().getPath(), new EffectSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Settings().registryKey(Keys.GOD_SWORD).fireproof().rarity(AerialHellRarities.MYTHICAL)));

    public static final Item FORGOTTEN_BATTLE_TRIDENT = register(Keys.FORGOTTEN_BATTLE_TRIDENT.getValue().getPath(), new ForgottenBattleTridentItem(ToolMaterials.VOLUCITE, 3, -2.9F, 0.2F, 0.0F, new Item.Settings().registryKey(Keys.FORGOTTEN_BATTLE_TRIDENT).maxDamage(1000).rarity(AerialHellRarities.LEGENDARY)));

    //armor
    public static final Item RUBY_HELMET = register(Keys.RUBY_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_HELMET).armor(AerialHellArmorMaterials.RUBY, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(15))));
    public static final Item RUBY_CHESTPLATE = register(Keys.RUBY_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_CHESTPLATE).armor(AerialHellArmorMaterials.RUBY, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(15))));
    public static final Item RUBY_LEGGINGS = register(Keys.RUBY_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_LEGGINGS).armor(AerialHellArmorMaterials.RUBY, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(15))));
    public static final Item RUBY_BOOTS = register(Keys.RUBY_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.RUBY_BOOTS).armor(AerialHellArmorMaterials.RUBY, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(15))));

    public static final Item AZURITE_HELMET = register(Keys.AZURITE_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_HELMET).armor(AerialHellArmorMaterials.AZURITE, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(10))));
    public static final Item AZURITE_CHESTPLATE = register(Keys.AZURITE_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_CHESTPLATE).armor(AerialHellArmorMaterials.AZURITE, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(10))));
    public static final Item AZURITE_LEGGINGS = register(Keys.AZURITE_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_LEGGINGS).armor(AerialHellArmorMaterials.AZURITE, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(10))));
    public static final Item AZURITE_BOOTS = register(Keys.AZURITE_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.AZURITE_BOOTS).armor(AerialHellArmorMaterials.AZURITE, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(10))));

    public static final Item OBSIDIAN_HELMET = register(Keys.OBSIDIAN_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_HELMET).armor(AerialHellArmorMaterials.OBSIDIAN, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(37)).rarity(Rarity.EPIC)));
    public static final Item OBSIDIAN_CHESTPLATE = register(Keys.OBSIDIAN_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_CHESTPLATE).armor(AerialHellArmorMaterials.OBSIDIAN, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(37)).rarity(Rarity.EPIC)));
    public static final Item OBSIDIAN_LEGGINGS = register(Keys.OBSIDIAN_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_LEGGINGS).armor(AerialHellArmorMaterials.OBSIDIAN, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(37)).rarity(Rarity.EPIC)));
    public static final Item OBSIDIAN_BOOTS = register(Keys.OBSIDIAN_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.OBSIDIAN_BOOTS).armor(AerialHellArmorMaterials.OBSIDIAN, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(37)).rarity(Rarity.EPIC)));

    public static final Item VOLUCITE_HELMET = register(Keys.VOLUCITE_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VOLUCITE_HELMET).armor(AerialHellArmorMaterials.VOLUCITE, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(35)).rarity(AerialHellRarities.VIBRANT)));
    public static final Item VOLUCITE_CHESTPLATE = register(Keys.VOLUCITE_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VOLUCITE_CHESTPLATE).armor(AerialHellArmorMaterials.VOLUCITE, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(35)).rarity(AerialHellRarities.VIBRANT)));
    public static final Item VOLUCITE_LEGGINGS = register(Keys.VOLUCITE_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VOLUCITE_LEGGINGS).armor(AerialHellArmorMaterials.VOLUCITE, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(35)).rarity(AerialHellRarities.VIBRANT)));
    public static final Item VOLUCITE_BOOTS = register(Keys.VOLUCITE_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.VOLUCITE_BOOTS).armor(AerialHellArmorMaterials.VOLUCITE, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(35)).rarity(AerialHellRarities.VIBRANT)));

    public static final Item MAGMATIC_GEL_HELMET = register(Keys.MAGMATIC_GEL_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_HELMET).armor(AerialHellArmorMaterials.MAGMATIC_GEL, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(7))));
    public static final Item MAGMATIC_GEL_CHESTPLATE = register(Keys.MAGMATIC_GEL_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_CHESTPLATE).armor(AerialHellArmorMaterials.MAGMATIC_GEL, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(7))));
    public static final Item MAGMATIC_GEL_LEGGINGS = register(Keys.MAGMATIC_GEL_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_LEGGINGS).armor(AerialHellArmorMaterials.MAGMATIC_GEL, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(7))));
    public static final Item MAGMATIC_GEL_BOOTS = register(Keys.MAGMATIC_GEL_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.MAGMATIC_GEL_BOOTS).armor(AerialHellArmorMaterials.MAGMATIC_GEL, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(7))));

    public static final Item LUNATIC_HELMET = register(Keys.LUNATIC_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_HELMET).armor(AerialHellArmorMaterials.LUNATIC, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(33)).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item LUNATIC_CHESTPLATE = register(Keys.LUNATIC_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_CHESTPLATE).armor(AerialHellArmorMaterials.LUNATIC, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(33)).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item LUNATIC_LEGGINGS = register(Keys.LUNATIC_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_LEGGINGS).armor(AerialHellArmorMaterials.LUNATIC, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(33)).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item LUNATIC_BOOTS = register(Keys.LUNATIC_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.LUNATIC_BOOTS).armor(AerialHellArmorMaterials.LUNATIC, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(33)).rarity(AerialHellRarities.LEGENDARY)));

    public static final Item ARSONIST_HELMET = register(Keys.ARSONIST_HELMET.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_HELMET).armor(AerialHellArmorMaterials.ARSONIST, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(37)).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final Item ARSONIST_CHESTPLATE = register(Keys.ARSONIST_CHESTPLATE.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_CHESTPLATE).armor(AerialHellArmorMaterials.ARSONIST, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(37)).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final Item ARSONIST_LEGGINGS = register(Keys.ARSONIST_LEGGINGS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_LEGGINGS).armor(AerialHellArmorMaterials.ARSONIST, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(37)).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final Item ARSONIST_BOOTS = register(Keys.ARSONIST_BOOTS.getValue().getPath(), new Item(new Item.Settings().registryKey(Keys.ARSONIST_BOOTS).armor(AerialHellArmorMaterials.ARSONIST, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(37)).rarity(AerialHellRarities.MYTHICAL).fireproof()));

    public static final Item SHADOW_HELMET = register(Keys.SHADOW_HELMET.getValue().getPath(), new ShadowArmorItem(new Item.Settings().registryKey(Keys.SHADOW_HELMET).armor(AerialHellArmorMaterials.SHADOW, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(25)).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_CHESTPLATE = register(Keys.SHADOW_CHESTPLATE.getValue().getPath(), new ShadowArmorItem(new Item.Settings().registryKey(Keys.SHADOW_CHESTPLATE).armor(AerialHellArmorMaterials.SHADOW, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(25)).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_LEGGINGS = register(Keys.SHADOW_LEGGINGS.getValue().getPath(), new ShadowArmorItem(new Item.Settings().registryKey(Keys.SHADOW_LEGGINGS).armor(AerialHellArmorMaterials.SHADOW, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(25)).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_BOOTS = register(Keys.SHADOW_BOOTS.getValue().getPath(), new ShadowArmorItem(new Item.Settings().registryKey(Keys.SHADOW_BOOTS).armor(AerialHellArmorMaterials.SHADOW, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(25)).rarity(AerialHellRarities.CORRUPTED)));

    //effect totems
    public static final Item REGENERATION_TOTEM = register(Keys.REGENERATION_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.REGENERATION_TOTEM).maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item SPEED_TOTEM = register(Keys.SPEED_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.SPEED_TOTEM).maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item SPEED_II_TOTEM = register(Keys.SPEED_II_TOTEM.getValue().getPath(), new EnchantedEffectTotemItem(new Item.Settings().registryKey(Keys.SPEED_II_TOTEM).maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item NIGHT_VISION_TOTEM = register(Keys.NIGHT_VISION_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.NIGHT_VISION_TOTEM).maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item AGILITY_TOTEM = register(Keys.AGILITY_TOTEM.getValue().getPath(), new EnchantedEffectTotemItem(new Item.Settings().registryKey(Keys.AGILITY_TOTEM).maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item HERO_TOTEM = register(Keys.HERO_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.HERO_TOTEM).maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item HEAD_IN_THE_CLOUDS_TOTEM = register(Keys.HEAD_IN_THE_CLOUDS_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.HEAD_IN_THE_CLOUDS_TOTEM).maxCount(1).rarity(AerialHellRarities.VIBRANT)));
    public static final Item GOD_TOTEM = register(Keys.GOD_TOTEM.getValue().getPath(), new EnchantedEffectTotemItem(new Item.Settings().registryKey(Keys.GOD_TOTEM).maxCount(1).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final Item CURSED_TOTEM = register(Keys.CURSED_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.CURSED_TOTEM).maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item SHADOW_TOTEM = register(Keys.SHADOW_TOTEM.getValue().getPath(), new EffectTotemItem(new Item.Settings().registryKey(Keys.SHADOW_TOTEM).maxCount(1).rarity(AerialHellRarities.CORRUPTED)));

    //spawn eggs
    public static final Item STELLAR_STONE_AUTOMATON_SPAWN_EGG = register(Keys.STELLAR_STONE_AUTOMATON_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.STELLAR_STONE_AUTOMATON_SPAWN_EGG).spawnEgg(AerialHellEntities.STELLAR_STONE_AUTOMATON).rarity(AerialHellRarities.VIBRANT)));
    public static final Item EVIL_COW_SPAWN_EGG = register(Keys.EVIL_COW_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.EVIL_COW_SPAWN_EGG).spawnEgg(AerialHellEntities.EVIL_COW).rarity(Rarity.COMMON)));
    public static final Item CORTINARIUS_COW_SPAWN_EGG = register(Keys.CORTINARIUS_COW_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.CORTINARIUS_COW_SPAWN_EGG).spawnEgg(AerialHellEntities.CORTINARIUS_COW).rarity(Rarity.COMMON)));
    public static final Item STELLAR_ENT_SPAWN_EGG = register(Keys.STELLAR_ENT_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.STELLAR_ENT_SPAWN_EGG).spawnEgg(AerialHellEntities.STELLAR_ENT).rarity(Rarity.COMMON)));
    public static final Item VENOMOUS_SNAKE_SPAWN_EGG = register(Keys.VENOMOUS_SNAKE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.VENOMOUS_SNAKE_SPAWN_EGG).spawnEgg(AerialHellEntities.VENOMOUS_SNAKE).rarity(Rarity.COMMON)));
    public static final Item WORM_SPAWN_EGG = register(Keys.WORM_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.WORM_SPAWN_EGG).spawnEgg(AerialHellEntities.WORM).rarity(Rarity.COMMON)));
    public static final Item STELLAR_CHICKEN_SPAWN_EGG = register(Keys.STELLAR_CHICKEN_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.STELLAR_CHICKEN_SPAWN_EGG).spawnEgg(AerialHellEntities.STELLAR_CHICKEN).rarity(Rarity.COMMON)));
    public static final Item STELLAR_BOAR_SPAWN_EGG = register(Keys.STELLAR_BOAR_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.STELLAR_BOAR_SPAWN_EGG).spawnEgg(AerialHellEntities.STELLAR_BOAR).rarity(Rarity.COMMON)));
    public static final Item SHROOMBOOM_SPAWN_EGG = register(Keys.SHROOMBOOM_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SHROOMBOOM_SPAWN_EGG).spawnEgg(AerialHellEntities.SHROOMBOOM).rarity(Rarity.COMMON)));
    public static final Item VERDIGRIS_ZOMBIE_SPAWN_EGG = register(Keys.VERDIGRIS_ZOMBIE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.VERDIGRIS_ZOMBIE_SPAWN_EGG).spawnEgg(AerialHellEntities.VERDIGRIS_ZOMBIE).rarity(Rarity.COMMON)));
    public static final Item MUMMY_SPAWN_EGG = register(Keys.MUMMY_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.MUMMY_SPAWN_EGG).spawnEgg(AerialHellEntities.MUMMY).rarity(Rarity.COMMON)));
    public static final Item SLIME_PIRATE_SPAWN_EGG = register(Keys.SLIME_PIRATE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SLIME_PIRATE_SPAWN_EGG).spawnEgg(AerialHellEntities.SLIME_PIRATE).rarity(Rarity.COMMON)));
    public static final Item SLIME_NINJA_PIRATE_SPAWN_EGG = register(Keys.SLIME_NINJA_PIRATE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SLIME_NINJA_PIRATE_SPAWN_EGG).spawnEgg(AerialHellEntities.SLIME_NINJA_PIRATE).rarity(Rarity.COMMON)));
    public static final Item GHOST_SLIME_PIRATE_SPAWN_EGG = register(Keys.GHOST_SLIME_PIRATE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.GHOST_SLIME_PIRATE_SPAWN_EGG).spawnEgg(AerialHellEntities.GHOST_SLIME_PIRATE).rarity(Rarity.COMMON)));
    public static final Item GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = register(Keys.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG).spawnEgg(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE).rarity(Rarity.COMMON)));
    public static final Item SANDY_SHEEP_SPAWN_EGG = register(Keys.SANDY_SHEEP_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SANDY_SHEEP_SPAWN_EGG).spawnEgg(AerialHellEntities.SANDY_SHEEP).rarity(Rarity.COMMON)));
    public static final Item GLIDING_TURTLE_SPAWN_EGG = register(Keys.GLIDING_TURTLE_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.GLIDING_TURTLE_SPAWN_EGG).spawnEgg(AerialHellEntities.GLIDING_TURTLE).rarity(Rarity.COMMON)));
    public static final Item FAT_PHANTOM_SPAWN_EGG = register(Keys.FAT_PHANTOM_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.FAT_PHANTOM_SPAWN_EGG).spawnEgg(AerialHellEntities.FAT_PHANTOM).rarity(Rarity.COMMON)));
    public static final Item KODAMA_SPAWN_EGG = register(Keys.KODAMA_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.KODAMA_SPAWN_EGG).spawnEgg(AerialHellEntities.KODAMA).rarity(Rarity.COMMON)));
    public static final Item MUD_GOLEM_SPAWN_EGG = register(Keys.MUD_GOLEM_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.MUD_GOLEM_SPAWN_EGG).spawnEgg(AerialHellEntities.MUD_GOLEM).rarity(Rarity.UNCOMMON)));
    public static final Item MUD_SOLDIER_SPAWN_EGG = register(Keys.MUD_SOLDIER_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.MUD_SOLDIER_SPAWN_EGG).spawnEgg(AerialHellEntities.MUD_SOLDIER).rarity(Rarity.COMMON)));
    public static final Item MUD_CYCLE_MAGE_SPAWN_EGG = register(Keys.MUD_CYCLE_MAGE_SPAWN_EGG.getValue().getPath(), new BossSpawnEggItem(new Item.Settings().registryKey(Keys.MUD_CYCLE_MAGE_SPAWN_EGG).spawnEgg(AerialHellEntities.MUD_CYCLE_MAGE).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item HELL_SPIDER_SPAWN_EGG = register(Keys.HELL_SPIDER_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.HELL_SPIDER_SPAWN_EGG).spawnEgg(AerialHellEntities.HELL_SPIDER).rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_GOLEM_SPAWN_EGG = register(Keys.CRYSTAL_GOLEM_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.CRYSTAL_GOLEM_SPAWN_EGG).spawnEgg(AerialHellEntities.CRYSTAL_GOLEM).rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_SLIME_SPAWN_EGG = register(Keys.CRYSTAL_SLIME_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.CRYSTAL_SLIME_SPAWN_EGG).spawnEgg(AerialHellEntities.CRYSTAL_SLIME).rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_SPIDER_SPAWN_EGG = register(Keys.CRYSTAL_SPIDER_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.CRYSTAL_SPIDER_SPAWN_EGG).spawnEgg(AerialHellEntities.CRYSTAL_SPIDER).rarity(Rarity.UNCOMMON)));
    public static final Item LUNATIC_PRIEST_SPAWN_EGG = register(Keys.LUNATIC_PRIEST_SPAWN_EGG.getValue().getPath(), new BossSpawnEggItem(new Item.Settings().registryKey(Keys.LUNATIC_PRIEST_SPAWN_EGG).spawnEgg(AerialHellEntities.LUNATIC_PRIEST).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item CRYSTAL_CATERPILLAR_SPAWN_EGG = register(Keys.CRYSTAL_CATERPILLAR_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.CRYSTAL_CATERPILLAR_SPAWN_EGG).spawnEgg(AerialHellEntities.CRYSTAL_CATERPILLAR).rarity(Rarity.COMMON)));
    public static final Item FOREST_CATERPILLAR_SPAWN_EGG = register(Keys.FOREST_CATERPILLAR_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.FOREST_CATERPILLAR_SPAWN_EGG).spawnEgg(AerialHellEntities.FOREST_CATERPILLAR).rarity(Rarity.COMMON)));
    public static final Item TORN_SPIRIT_SPAWN_EGG = register(Keys.TORN_SPIRIT_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.TORN_SPIRIT_SPAWN_EGG).spawnEgg(AerialHellEntities.TORN_SPIRIT).rarity(Rarity.EPIC)));
    public static final Item CHAINED_GOD_SPAWN_EGG = register(Keys.CHAINED_GOD_SPAWN_EGG.getValue().getPath(), new BossSpawnEggItem(new Item.Settings().registryKey(Keys.CHAINED_GOD_SPAWN_EGG).spawnEgg(AerialHellEntities.CHAINED_GOD).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item ICE_SPIRIT_SPAWN_EGG = register(Keys.ICE_SPIRIT_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.ICE_SPIRIT_SPAWN_EGG).spawnEgg(AerialHellEntities.ICE_SPIRIT).rarity(Rarity.UNCOMMON)));
    public static final Item FIRE_SPIRIT_SPAWN_EGG = register(Keys.FIRE_SPIRIT_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.FIRE_SPIRIT_SPAWN_EGG).spawnEgg(AerialHellEntities.FIRE_SPIRIT).rarity(Rarity.UNCOMMON)));
    public static final Item ELECTRO_SPIRIT_SPAWN_EGG = register(Keys.ELECTRO_SPIRIT_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.ELECTRO_SPIRIT_SPAWN_EGG).spawnEgg(AerialHellEntities.ELECTRO_SPIRIT).rarity(Rarity.UNCOMMON)));
    public static final Item FLYING_JELLYFISH_SPAWN_EGG = register(Keys.FLYING_JELLYFISH_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.FLYING_JELLYFISH_SPAWN_EGG).spawnEgg(AerialHellEntities.FLYING_JELLYFISH).rarity(Rarity.COMMON)));
    public static final Item SHADOW_FLYING_SKULL_SPAWN_EGG = register(Keys.SHADOW_FLYING_SKULL_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SHADOW_FLYING_SKULL_SPAWN_EGG).spawnEgg(AerialHellEntities.SHADOW_FLYING_SKULL).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_TROLL_SPAWN_EGG = register(Keys.SHADOW_TROLL_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SHADOW_TROLL_SPAWN_EGG).spawnEgg(AerialHellEntities.SHADOW_TROLL).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_AUTOMATON_SPAWN_EGG = register(Keys.SHADOW_AUTOMATON_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SHADOW_AUTOMATON_SPAWN_EGG).spawnEgg(AerialHellEntities.SHADOW_AUTOMATON).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_SPIDER_SPAWN_EGG = register(Keys.SHADOW_SPIDER_SPAWN_EGG.getValue().getPath(), new SpawnEggItem(new Item.Settings().registryKey(Keys.SHADOW_SPIDER_SPAWN_EGG).spawnEgg(AerialHellEntities.SHADOW_SPIDER).rarity(AerialHellRarities.CORRUPTED)));
    public static final Item LILITH_SPAWN_EGG = register(Keys.LILITH_SPAWN_EGG.getValue().getPath(), new BossSpawnEggItem(new Item.Settings().registryKey(Keys.LILITH_SPAWN_EGG).spawnEgg(AerialHellEntities.LILITH).rarity(AerialHellRarities.CORRUPTED)));

    public static final Item AERIAL_HELL_PAINTING = register(Keys.AERIAL_HELL_PAINTING.getValue().getPath(), new AerialHellHangingEntityItem(AerialHellEntities.AERIAL_HELL_PAINTING, new Item.Settings().registryKey(Keys.AERIAL_HELL_PAINTING)));

    //build items
    public static final Item BLOCK_UPDATER = register(Keys.BLOCK_UPDATER.getValue().getPath(), new BlockUpdaterItem(new Item.Settings().registryKey(Keys.BLOCK_UPDATER)));
    public static final Item BLOCK_CRACKER = register(Keys.BLOCK_CRACKER.getValue().getPath(), new BlockCrackerItem(new Item.Settings().registryKey(Keys.BLOCK_CRACKER)));
    public static final Item STRUCTURE_VOID_PLACER = register(Keys.STRUCTURE_VOID_PLACER.getValue().getPath(), new StructureVoidPlacerItem(new Item.Settings().registryKey(Keys.STRUCTURE_VOID_PLACER)));

    public static class Keys
    {
        //portal
        public static final RegistryKey<Item> STELLAR_PORTAL_FRAME_BLOCK = createKey("stellar_portal_frame_block");
        public static final RegistryKey<Item> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
        public static final RegistryKey<Item> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = createKey("deepslate_stellar_portal_frame_ore");
        public static final RegistryKey<Item> STELLAR_PORTAL_FRAME_BRICK = createKey("stellar_portal_frame_brick");
        public static final RegistryKey<Item> STELLAR_LIGHTER = createKey("stellar_lighter");

        //torch
        public static final RegistryKey<Item> CRYSTALLIZED_TORCH = createKey("crystallized_torch");
        public static final RegistryKey<Item> FLUORITE_TORCH = createKey("fluorite_torch");
        public static final RegistryKey<Item> VOLUCITE_TORCH = createKey("volucite_torch");
        public static final RegistryKey<Item> SHADOW_TORCH = createKey("shadow_torch");

        //lanterns
        public static final RegistryKey<Item> CRYSTALLIZED_LANTERN = createKey("crystallized_lantern");
        public static final RegistryKey<Item> FLUORITE_LANTERN = createKey("fluorite_lantern");
        public static final RegistryKey<Item> RUBY_LANTERN = createKey("ruby_lantern");
        public static final RegistryKey<Item> RUBY_CRYSTALLIZED_LANTERN = createKey("ruby_crystallized_lantern");
        public static final RegistryKey<Item> RUBY_FLUORITE_LANTERN = createKey("ruby_fluorite_lantern");
        public static final RegistryKey<Item> VOLUCITE_LANTERN = createKey("volucite_lantern");
        public static final RegistryKey<Item> VOLUCITE_CRYSTALLIZED_LANTERN = createKey("volucite_crystallized_lantern");
        public static final RegistryKey<Item> VOLUCITE_FLUORITE_LANTERN = createKey("volucite_fluorite_lantern");
        public static final RegistryKey<Item> LUNATIC_LANTERN = createKey("lunatic_lantern");
        public static final RegistryKey<Item> SHADOW_LANTERN = createKey("shadow_lantern");

        //chains
        public static final RegistryKey<Item> RUBY_CHAIN = createKey("ruby_chain");
        public static final RegistryKey<Item> VOLUCITE_CHAIN = createKey("volucite_chain");
        public static final RegistryKey<Item> LUNATIC_CHAIN = createKey("lunatic_chain");
        public static final RegistryKey<Item> SHADOW_CHAIN = createKey("shadow_chain");

        //grass & dirt
        public static final RegistryKey<Item> STELLAR_GRASS_BLOCK = createKey("stellar_grass_block");
        public static final RegistryKey<Item> CHISELED_STELLAR_GRASS_BLOCK = createKey("chiseled_stellar_grass_block");
        public static final RegistryKey<Item> STELLAR_DIRT = createKey("stellar_dirt");
        public static final RegistryKey<Item> STELLAR_COARSE_DIRT = createKey("stellar_coarse_dirt");
        public static final RegistryKey<Item> STELLAR_FARMLAND = createKey("stellar_farmland");
        public static final RegistryKey<Item> STELLAR_DIRT_PATH = createKey("stellar_dirt_path");
        public static final RegistryKey<Item> STELLAR_PODZOL = createKey("stellar_podzol");
        public static final RegistryKey<Item> STELLAR_CRYSTAL_PODZOL = createKey("stellar_crystal_podzol");
        public static final RegistryKey<Item> CHISELED_STELLAR_DIRT = createKey("chiseled_stellar_dirt");
        public static final RegistryKey<Item> SHADOW_GRASS_BLOCK = createKey("shadow_grass_block");

        //slippery sand
        public static final RegistryKey<Item> SLIPPERY_SAND = createKey("slippery_sand");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE = createKey("slippery_sand_stone");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS = createKey("slippery_sand_stone_bricks");
        public static final RegistryKey<Item> CUT_SLIPPERY_SAND_STONE = createKey("cut_slippery_sand_stone");
        public static final RegistryKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS = createKey("cracked_slippery_sand_stone_bricks");

        //giant root
        public static final RegistryKey<Item> GIANT_ROOT = createKey("giant_root");

        //aerial_tree
        public static final RegistryKey<Item> AERIAL_TREE_LOG = createKey("aerial_tree_log");
        public static final RegistryKey<Item> STRIPPED_AERIAL_TREE_LOG = createKey("stripped_aerial_tree_log");
        public static final RegistryKey<Item> AERIAL_TREE_WOOD = createKey("aerial_tree_wood");
        public static final RegistryKey<Item> STRIPPED_AERIAL_TREE_WOOD = createKey("stripped_aerial_tree_wood");
        public static final RegistryKey<Item> AERIAL_TREE_LEAVES = createKey("aerial_tree_leaves");
        public static final RegistryKey<Item> AERIAL_TREE_PLANKS = createKey("aerial_tree_planks");
        public static final RegistryKey<Item> CHISELED_AERIAL_TREE_PLANKS = createKey("chiseled_aerial_tree_planks");
        public static final RegistryKey<Item> AERIAL_TREE_BOOKSHELF = createKey("aerial_tree_bookshelf");
        public static final RegistryKey<Item> AERIAL_TREE_SAPLING = createKey("aerial_tree_sapling");

        //petrified aerial tree
        public static final RegistryKey<Item> PETRIFIED_AERIAL_TREE_LOG = createKey("petrified_aerial_tree_log");

        //golden beech
        public static final RegistryKey<Item> GOLDEN_BEECH_LOG = createKey("golden_beech_log");
        public static final RegistryKey<Item> STRIPPED_GOLDEN_BEECH_LOG = createKey("stripped_golden_beech_log");
        public static final RegistryKey<Item> GOLDEN_BEECH_WOOD = createKey("golden_beech_wood");
        public static final RegistryKey<Item> STRIPPED_GOLDEN_BEECH_WOOD = createKey("stripped_golden_beech_wood");
        public static final RegistryKey<Item> GOLDEN_BEECH_PLANKS = createKey("golden_beech_planks");
        public static final RegistryKey<Item> CHISELED_GOLDEN_BEECH_PLANKS = createKey("chiseled_golden_beech_planks");
        public static final RegistryKey<Item> GOLDEN_BEECH_LEAVES = createKey("golden_beech_leaves");
        public static final RegistryKey<Item> GOLDEN_BEECH_BOOKSHELF = createKey("golden_beech_bookshelf");
        public static final RegistryKey<Item> GOLDEN_BEECH_SAPLING = createKey("golden_beech_sapling");

        //cropper pine
        public static final RegistryKey<Item> COPPER_PINE_LOG = createKey("copper_pine_log");
        public static final RegistryKey<Item> STRIPPED_COPPER_PINE_LOG = createKey("stripped_copper_pine_log");
        public static final RegistryKey<Item> COPPER_PINE_WOOD = createKey("copper_pine_wood");
        public static final RegistryKey<Item> STRIPPED_COPPER_PINE_WOOD = createKey("stripped_copper_pine_wood");
        public static final RegistryKey<Item> COPPER_PINE_PLANKS = createKey("copper_pine_planks");
        public static final RegistryKey<Item> COPPER_PINE_LEAVES = createKey("copper_pine_leaves");
        public static final RegistryKey<Item> COPPER_PINE_BOOKSHELF = createKey("copper_pine_bookshelf");
        public static final RegistryKey<Item> COPPER_PINE_SAPLING = createKey("copper_pine_sapling");

        //lapis robinia
        public static final RegistryKey<Item> LAPIS_ROBINIA_LOG = createKey("lapis_robinia_log");
        public static final RegistryKey<Item> ENCHANTED_LAPIS_ROBINIA_LOG = createKey("enchanted_lapis_robinia_log");
        public static final RegistryKey<Item> STRIPPED_LAPIS_ROBINIA_LOG = createKey("stripped_lapis_robinia_log");
        public static final RegistryKey<Item> LAPIS_ROBINIA_WOOD = createKey("lapis_robinia_wood");
        public static final RegistryKey<Item> STRIPPED_LAPIS_ROBINIA_WOOD = createKey("stripped_lapis_robinia_wood");
        public static final RegistryKey<Item> LAPIS_ROBINIA_LEAVES = createKey("lapis_robinia_leaves");
        public static final RegistryKey<Item> LAPIS_ROBINIA_PLANKS = createKey("lapis_robinia_planks");
        public static final RegistryKey<Item> LAPIS_ROBINIA_BOOKSHELF = createKey("lapis_robinia_bookshelf");
        public static final RegistryKey<Item> LAPIS_ROBINIA_SAPLING = createKey("lapis_robinia_sapling");

        //shadow_pine
        public static final RegistryKey<Item> SHADOW_PINE_LOG = createKey("shadow_pine_log");
        public static final RegistryKey<Item> EYE_SHADOW_PINE_LOG = createKey("eye_shadow_pine_log");
        public static final RegistryKey<Item> STRIPPED_SHADOW_PINE_LOG = createKey("stripped_shadow_pine_log");
        public static final RegistryKey<Item> SHADOW_PINE_WOOD = createKey("shadow_pine_wood");
        public static final RegistryKey<Item> STRIPPED_SHADOW_PINE_WOOD = createKey("stripped_shadow_pine_wood");
        public static final RegistryKey<Item> SHADOW_PINE_LEAVES = createKey("shadow_pine_leaves");
        public static final RegistryKey<Item> PURPLE_SHADOW_PINE_LEAVES = createKey("purple_shadow_pine_leaves");
        public static final RegistryKey<Item> SHADOW_PINE_PLANKS = createKey("shadow_pine_planks");
        public static final RegistryKey<Item> SHADOW_PINE_BOOKSHELF = createKey("shadow_pine_bookshelf");
        public static final RegistryKey<Item> SHADOW_PINE_SAPLING = createKey("shadow_pine_sapling");
        public static final RegistryKey<Item> PURPLE_SHADOW_PINE_SAPLING = createKey("purple_shadow_pine_sapling");

        //stellar jungle tree
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_LOG = createKey("stellar_jungle_tree_log");
        public static final RegistryKey<Item> STRIPPED_STELLAR_JUNGLE_TREE_LOG = createKey("stripped_stellar_jungle_tree_log");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_WOOD = createKey("stellar_jungle_tree_wood");
        public static final RegistryKey<Item> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = createKey("stripped_stellar_jungle_tree_wood");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_LEAVES = createKey("stellar_jungle_tree_leaves");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_PLANKS = createKey("stellar_jungle_tree_planks");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_BOOKSHELF = createKey("stellar_jungle_tree_bookshelf");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_SAPLING = createKey("stellar_jungle_tree_sapling");
        public static final RegistryKey<Item> DEAD_STELLAR_JUNGLE_TREE_LOG = createKey("dead_stellar_jungle_tree_log");

        //shroom
        public static final RegistryKey<Item> GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("giant_cortinarius_violaceus_stem");
        public static final RegistryKey<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("stripped_giant_cortinarius_violaceus_stem");
        public static final RegistryKey<Item> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("giant_cortinarius_violaceus_bark_stem");
        public static final RegistryKey<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("stripped_giant_cortinarius_violaceus_bark_stem");
        public static final RegistryKey<Item> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = createKey("giant_cortinarius_violaceus_cap_block");
        public static final RegistryKey<Item> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = createKey("giant_cortinarius_violaceus_light");
        public static final RegistryKey<Item> CORTINARIUS_VIOLACEUS = createKey("cortinarius_violaceus");
        public static final RegistryKey<Item> GLOWING_BOLETUS = createKey("glowing_boletus");
        public static final RegistryKey<Item> TALL_GLOWING_BOLETUS = createKey("tall_glowing_boletus");
        public static final RegistryKey<Item> BLUE_MEANIE_CLUSTER = createKey("blue_meanie_cluster");
        public static final RegistryKey<Item> GIANT_ROOT_SHROOM = createKey("giant_root_shroom");

        public static final RegistryKey<Item> GIANT_VERDIGRIS_AGARIC_STEM = createKey("giant_verdigris_agaric_stem");
        public static final RegistryKey<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = createKey("stripped_giant_verdigris_agaric_stem");
        public static final RegistryKey<Item> GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("giant_verdigris_agaric_bark_stem");
        public static final RegistryKey<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("stripped_giant_verdigris_agaric_bark_stem");
        public static final RegistryKey<Item> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = createKey("giant_verdigris_agaric_cap_block");
        public static final RegistryKey<Item> VERDIGRIS_AGARIC = createKey("verdigris_agaric");

        public static final RegistryKey<Item> GIANT_GANODERMA_APPLANATUM_BLOCK = createKey("giant_ganoderma_applanatum_block");

        public static final RegistryKey<Item> GRAY_SHROOM_PLANKS = createKey("gray_shroom_planks");
        public static final RegistryKey<Item> GRAY_SHROOM_BOOKSHELF = createKey("gray_shroom_bookshelf");

        //shadow corrupted / uncorrupted variants
        public static final RegistryKey<Item> SHADOW_AERIAL_TREE_LOG = createKey("shadow_aerial_tree_log");
        public static final RegistryKey<Item> SHADOW_GOLDEN_BEECH_LOG = createKey("shadow_golden_beech_log");
        public static final RegistryKey<Item> SHADOW_COPPER_PINE_LOG = createKey("shadow_copper_pine_log");
        public static final RegistryKey<Item> SHADOW_LAPIS_ROBINIA_LOG = createKey("shadow_lapis_robinia_log");
        public static final RegistryKey<Item> SHADOW_STELLAR_JUNGLE_TREE_LOG = createKey("shadow_stellar_jungle_tree_log");
        public static final RegistryKey<Item> HOLLOW_SHADOW_PINE_LOG = createKey("hollow_shadow_pine_log");
        public static final RegistryKey<Item> SHADOW_AERIAL_TREE_LEAVES = createKey("shadow_aerial_tree_leaves");
        public static final RegistryKey<Item> SHADOW_GOLDEN_BEECH_LEAVES = createKey("shadow_golden_beech_leaves");
        public static final RegistryKey<Item> SHADOW_COPPER_PINE_LEAVES = createKey("shadow_copper_pine_leaves");
        public static final RegistryKey<Item> SHADOW_LAPIS_ROBINIA_LEAVES = createKey("shadow_lapis_robinia_leaves");
        public static final RegistryKey<Item> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = createKey("shadow_stellar_jungle_tree_leaves");
        public static final RegistryKey<Item> HOLLOW_SHADOW_PINE_LEAVES = createKey("hollow_shadow_pine_leaves");
        public static final RegistryKey<Item> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = createKey("hollow_purple_shadow_pine_leaves");

        //ladder
        public static final RegistryKey<Item> SKY_LADDER = createKey("sky_ladder");

        //natural blocks and items
        public static final RegistryKey<Item> STELLAR_STONE = createKey("stellar_stone");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE = createKey("stellar_cobblestone");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS = createKey("stellar_stone_bricks");
        public static final RegistryKey<Item> MOSSY_STELLAR_STONE = createKey("mossy_stellar_stone");
        public static final RegistryKey<Item> MOSSY_STELLAR_COBBLESTONE = createKey("mossy_stellar_cobblestone");
        public static final RegistryKey<Item> STELLAR_CLAY = createKey("stellar_clay");
        public static final RegistryKey<Item> GLAUCOPHANITE = createKey("glaucophanite");
        public static final RegistryKey<Item> POLISHED_GLAUCOPHANITE = createKey("polished_glaucophanite");
        public static final RegistryKey<Item> SHADOW_STONE = createKey("shadow_stone");

        //crystal
        public static final RegistryKey<Item> CRYSTAL_BLOCK = createKey("crystal_block");
        public static final RegistryKey<Item> CRYSTAL_BRICKS = createKey("crystal_bricks");
        public static final RegistryKey<Item> CRYSTAL_BRICKS_SLAB = createKey("crystal_bricks_slab");
        public static final RegistryKey<Item> CRYSTAL_BRICKS_STAIRS = createKey("crystal_bricks_stairs");
        public static final RegistryKey<Item> CRYSTAL_BRICKS_WALL = createKey("crystal_bricks_wall");
        public static final RegistryKey<Item> STELLAR_STONE_CRYSTAL_BLOCK = createKey("stellar_stone_crystal_block");
        public static final RegistryKey<Item> SHADOW_CRYSTAL_BLOCK = createKey("shadow_crystal_block");
        public static final RegistryKey<Item> CRYSTALLIZED_LEAVES = createKey("crystallized_leaves");
        public static final RegistryKey<Item> CRYSTAL = createKey("crystal");
        public static final RegistryKey<Item> STELLAR_STONE_CRYSTAL = createKey("stellar_stone_crystal");
        public static final RegistryKey<Item> SHADOW_CRYSTAL = createKey("shadow_crystal");

        //glass and glass pane
        public static final RegistryKey<Item> SLIPPERY_SAND_GLASS = createKey("slippery_sand_glass");
        public static final RegistryKey<Item> RED_SLIPPERY_SAND_GLASS = createKey("red_slippery_sand_glass");
        public static final RegistryKey<Item> BLACK_SLIPPERY_SAND_GLASS = createKey("black_slippery_sand_glass");
        public static final RegistryKey<Item> BLUE_SLIPPERY_SAND_GLASS = createKey("blue_slippery_sand_glass");
        public static final RegistryKey<Item> GREEN_SLIPPERY_SAND_GLASS = createKey("green_slippery_sand_glass");
        public static final RegistryKey<Item> SLIPPERY_SAND_GLASS_PANE = createKey("slippery_sand_glass_pane");
        public static final RegistryKey<Item> RED_SLIPPERY_SAND_GLASS_PANE = createKey("red_slippery_sand_glass_pane");
        public static final RegistryKey<Item> BLACK_SLIPPERY_SAND_GLASS_PANE = createKey("black_slippery_sand_glass_pane");
        public static final RegistryKey<Item> BLUE_SLIPPERY_SAND_GLASS_PANE = createKey("blue_slippery_sand_glass_pane");
        public static final RegistryKey<Item> GREEN_SLIPPERY_SAND_GLASS_PANE = createKey("green_slippery_sand_glass_pane");

        //ghost boat
        public static final RegistryKey<Item> GHOST_BOAT_PLANKS = createKey("ghost_boat_planks");
        public static final RegistryKey<Item> GHOST_BOAT_LOG = createKey("ghost_boat_log");
        public static final RegistryKey<Item> GHOST_BOAT_WOOD = createKey("ghost_boat_wood");
        public static final RegistryKey<Item> GHOST_BOAT_SLAB = createKey("ghost_boat_slab");
        public static final RegistryKey<Item> GHOST_BOAT_STAIRS = createKey("ghost_boat_stairs");
        public static final RegistryKey<Item> GHOST_BOAT_FENCE = createKey("ghost_boat_fence");
        public static final RegistryKey<Item> GHOST_BOAT_GATE = createKey("ghost_boat_gate");
        public static final RegistryKey<Item> GHOST_BOAT_DOOR = createKey("ghost_boat_door");
        public static final RegistryKey<Item> GHOST_BOAT_TRAPDOOR = createKey("ghost_boat_trapdoor");
        public static final RegistryKey<Item> GHOST_BOAT_CHEST = createKey("ghost_boat_chest");
        public static final RegistryKey<Item> GHOST_BOAT_WOOL = createKey("ghost_boat_wool");
        public static final RegistryKey<Item> GHOST_STELLAR_COBBLESTONE = createKey("ghost_stellar_cobblestone");
        public static final RegistryKey<Item> GHOST_RUBY_BLOCK = createKey("ghost_ruby_block");
        public static final RegistryKey<Item> GHOST_FLUORITE_BLOCK = createKey("ghost_fluorite_block");
        public static final RegistryKey<Item> GHOST_AZURITE_BLOCK = createKey("ghost_azurite_block");
        public static final RegistryKey<Item> GHOST_GOLD_BLOCK = createKey("ghost_gold_block");
        public static final RegistryKey<Item> GHOST_BOAT_BARREL = createKey("ghost_boat_barrel");
        public static final RegistryKey<Item> GHOST_BOAT_CRAFTING_TABLE = createKey("ghost_boat_crafting_table");
        public static final RegistryKey<Item> GHOST_BOAT_VINE_ROPE_SPOOL = createKey("ghost_boat_vine_rope_spool");
        public static final RegistryKey<Item> GHOST_LANTERN = createKey("ghost_lantern");

        //reactors
        public static final RegistryKey<Item> WEAK_LIGHT_REACTOR = createKey("weak_light_reactor");
        public static final RegistryKey<Item> HIGH_POWER_LIGHT_REACTOR = createKey("high_power_light_reactor");
        public static final RegistryKey<Item> WEAK_SHADOW_REACTOR = createKey("weak_shadow_reactor");
        public static final RegistryKey<Item> HIGH_POWER_SHADOW_REACTOR = createKey("high_power_shadow_reactor");

        public static final RegistryKey<Item> BROKEN_WEAK_LIGHT_REACTOR = createKey("broken_weak_light_reactor");
        public static final RegistryKey<Item> BROKEN_HIGH_POWER_LIGHT_REACTOR = createKey("broken_high_power_light_reactor");
        public static final RegistryKey<Item> BROKEN_WEAK_SHADOW_REACTOR = createKey("broken_weak_shadow_reactor");
        public static final RegistryKey<Item> BROKEN_HIGH_POWER_SHADOW_REACTOR = createKey("broken_high_power_shadow_reactor");

        //solid_ethers
        public static final RegistryKey<Item> WHITE_SOLID_ETHER = createKey("white_solid_ether");
        public static final RegistryKey<Item> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
        public static final RegistryKey<Item> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
        public static final RegistryKey<Item> GREEN_SOLID_ETHER = createKey("green_solid_ether");
        public static final RegistryKey<Item> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

        //dungeons blocks
        public static final RegistryKey<Item> MUD_BRICKS = createKey("mud_bricks");
        public static final RegistryKey<Item> CRACKED_MUD_BRICKS = createKey("cracked_mud_bricks");
        public static final RegistryKey<Item> MOSSY_MUD_BRICKS = createKey("mossy_mud_bricks");
        public static final RegistryKey<Item> CHISELED_MUD_BRICKS = createKey("chiseled_mud_bricks");
        public static final RegistryKey<Item> LIGHT_MUD_BRICKS = createKey("light_mud_bricks");
        public static final RegistryKey<Item> CRACKED_LIGHT_MUD_BRICKS = createKey("cracked_light_mud_bricks");
        public static final RegistryKey<Item> LUNATIC_STONE = createKey("lunatic_stone");
        public static final RegistryKey<Item> DARK_LUNATIC_STONE = createKey("dark_lunatic_stone");
        public static final RegistryKey<Item> ROOF_LUNATIC_STONE = createKey("roof_lunatic_stone");
        public static final RegistryKey<Item> CRACKED_LUNATIC_STONE = createKey("cracked_lunatic_stone");
        public static final RegistryKey<Item> CHISELED_LUNATIC_STONE = createKey("chiseled_lunatic_stone");
        public static final RegistryKey<Item> LIGHT_LUNATIC_STONE = createKey("light_lunatic_stone");
        public static final RegistryKey<Item> ROOF_LIGHT_LUNATIC_STONE = createKey("roof_light_lunatic_stone");
        public static final RegistryKey<Item> CRACKED_LIGHT_LUNATIC_STONE = createKey("cracked_light_lunatic_stone");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_BRICKS = createKey("shadow_catacombs_bricks");
        public static final RegistryKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS = createKey("cracked_shadow_catacombs_bricks");
        public static final RegistryKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS = createKey("mossy_shadow_catacombs_bricks");
        public static final RegistryKey<Item> CHISELED_SHADOW_CATACOMBS_BRICKS = createKey("chiseled_shadow_catacombs_bricks");
        public static final RegistryKey<Item> BONE_SHADOW_CATACOMBS_BRICKS = createKey("bone_shadow_catacombs_bricks");
        public static final RegistryKey<Item> SKULL_SHADOW_CATACOMBS_BRICKS = createKey("skull_shadow_catacombs_bricks");
        public static final RegistryKey<Item> LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("light_shadow_catacombs_bricks");
        public static final RegistryKey<Item> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("cracked_light_shadow_catacombs_bricks");
        public static final RegistryKey<Item> GOLDEN_NETHER_BRICKS  = createKey("golden_nether_bricks");
        public static final RegistryKey<Item> CRACKED_GOLDEN_NETHER_BRICKS  = createKey("cracked_golden_nether_bricks");
        public static final RegistryKey<Item> CHISELED_GOLDEN_NETHER_BRICKS  = createKey("chiseled_golden_nether_bricks");
        public static final RegistryKey<Item> LIGHT_GOLDEN_NETHER_BRICKS = createKey("light_golden_nether_bricks");
        public static final RegistryKey<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("cracked_light_golden_nether_bricks");
        public static final RegistryKey<Item> LUNATIC_PILLAR = createKey("lunatic_pillar");
        public static final RegistryKey<Item> LUNATIC_PILLAR_TOP = createKey("lunatic_pillar_top");
        public static final RegistryKey<Item> VOLUCITE_STONE = createKey("volucite_stone");
        public static final RegistryKey<Item> CRACKED_VOLUCITE_STONE = createKey("cracked_volucite_stone");
        public static final RegistryKey<Item> CHISELED_VOLUCITE_STONE = createKey("chiseled_volucite_stone");
        public static final RegistryKey<Item> LIGHT_VOLUCITE_STONE = createKey("light_volucite_stone");
        public static final RegistryKey<Item> CRACKED_LIGHT_VOLUCITE_STONE = createKey("cracked_light_volucite_stone");

        //dungeon cores
        public static final RegistryKey<Item> MUD_DUNGEON_CORE = createKey("mud_dungeon_core");
        public static final RegistryKey<Item> LUNATIC_DUNGEON_CORE = createKey("lunatic_dungeon_core");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_DUNGEON_CORE = createKey("shadow_catacombs_dungeon_core");
        public static final RegistryKey<Item> GOLDEN_NETHER_DUNGEON_CORE = createKey("golden_nether_dungeon_core");
        public static final RegistryKey<Item> VOLUCITE_DUNGEON_CORE = createKey("volucite_dungeon_core");

        //dungeons slabs, stairs & walls
        public static final RegistryKey<Item> MUD_BRICKS_SLAB = createKey("mud_bricks_slab");
        public static final RegistryKey<Item> MUD_BRICKS_STAIRS = createKey("mud_bricks_stairs");
        public static final RegistryKey<Item> MUD_BRICKS_WALL = createKey("mud_bricks_wall");
        public static final RegistryKey<Item> CRACKED_MUD_BRICKS_SLAB = createKey("cracked_mud_bricks_slab");
        public static final RegistryKey<Item> CRACKED_MUD_BRICKS_STAIRS = createKey("cracked_mud_bricks_stairs");
        public static final RegistryKey<Item> CRACKED_MUD_BRICKS_WALL = createKey("cracked_mud_bricks_wall");
        public static final RegistryKey<Item> MOSSY_MUD_BRICKS_SLAB = createKey("mossy_mud_bricks_slab");
        public static final RegistryKey<Item> MOSSY_MUD_BRICKS_STAIRS = createKey("mossy_mud_bricks_stairs");
        public static final RegistryKey<Item> MOSSY_MUD_BRICKS_WALL = createKey("mossy_mud_bricks_wall");
        public static final RegistryKey<Item> VOLUCITE_STONE_SLAB = createKey("volucite_stone_slab");
        public static final RegistryKey<Item> VOLUCITE_STONE_STAIRS = createKey("volucite_stone_stairs");
        public static final RegistryKey<Item> VOLUCITE_STONE_WALL = createKey("volucite_stone_wall");
        public static final RegistryKey<Item> CRACKED_VOLUCITE_STONE_SLAB = createKey("cracked_volucite_stone_slab");
        public static final RegistryKey<Item> CRACKED_VOLUCITE_STONE_STAIRS = createKey("cracked_volucite_stone_stairs");
        public static final RegistryKey<Item> CRACKED_VOLUCITE_STONE_WALL = createKey("cracked_volucite_stone_wall");
        public static final RegistryKey<Item> LUNATIC_STONE_SLAB = createKey("lunatic_stone_slab");
        public static final RegistryKey<Item> LUNATIC_STONE_STAIRS = createKey("lunatic_stone_stairs");
        public static final RegistryKey<Item> LUNATIC_STONE_WALL = createKey("lunatic_stone_wall");
        public static final RegistryKey<Item> DARK_LUNATIC_STONE_SLAB = createKey("dark_lunatic_stone_slab");
        public static final RegistryKey<Item> DARK_LUNATIC_STONE_STAIRS = createKey("dark_lunatic_stone_stairs");
        public static final RegistryKey<Item> DARK_LUNATIC_STONE_WALL = createKey("dark_lunatic_stone_wall");
        public static final RegistryKey<Item> CRACKED_LUNATIC_STONE_SLAB = createKey("cracked_lunatic_stone_slab");
        public static final RegistryKey<Item> CRACKED_LUNATIC_STONE_STAIRS = createKey("cracked_lunatic_stone_stairs");
        public static final RegistryKey<Item> CRACKED_LUNATIC_STONE_WALL = createKey("cracked_lunatic_stone_wall");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_BRICKS_SLAB = createKey("shadow_catacombs_bricks_slab");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_BRICKS_WALL = createKey("shadow_catacombs_bricks_wall");
        public static final RegistryKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("cracked_shadow_catacombs_bricks_slab");
        public static final RegistryKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("cracked_shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = createKey("cracked_shadow_catacombs_bricks_wall");
        public static final RegistryKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("mossy_shadow_catacombs_bricks_slab");
        public static final RegistryKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("mossy_shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = createKey("mossy_shadow_catacombs_bricks_wall");
        public static final RegistryKey<Item> SHADOW_BARS = createKey("shadow_bars");
        public static final RegistryKey<Item> GOLDEN_NETHER_BRICKS_SLAB = createKey("golden_nether_bricks_slab");
        public static final RegistryKey<Item> GOLDEN_NETHER_BRICKS_STAIRS = createKey("golden_nether_bricks_stairs");
        public static final RegistryKey<Item> GOLDEN_NETHER_BRICKS_WALL = createKey("golden_nether_bricks_wall");
        public static final RegistryKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = createKey("cracked_golden_nether_bricks_slab");
        public static final RegistryKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = createKey("cracked_golden_nether_bricks_stairs");
        public static final RegistryKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL = createKey("cracked_golden_nether_bricks_wall");

        //smoky quartz
        public static final RegistryKey<Item> SMOKY_QUARTZ = createKey("smoky_quartz");
        public static final RegistryKey<Item> SMOKY_QUARTZ_BLOCK = createKey("smoky_quartz_block");
        public static final RegistryKey<Item> SMOOTH_SMOKY_QUARTZ = createKey("smooth_smoky_quartz");
        public static final RegistryKey<Item> CHISELED_SMOKY_QUARTZ_BLOCK = createKey("chiseled_smoky_quartz_block");
        public static final RegistryKey<Item> SMOKY_QUARTZ_BRICKS = createKey("smoky_quartz_bricks");
        public static final RegistryKey<Item> SMOKY_QUARTZ_PILLAR = createKey("smoky_quartz_pillar");
        public static final RegistryKey<Item> SMOKY_QUARTZ_SLAB = createKey("smoky_quartz_slab");
        public static final RegistryKey<Item> SMOOTH_SMOKY_QUARTZ_SLAB = createKey("smooth_smoky_quartz_slab");
        public static final RegistryKey<Item> SMOKY_QUARTZ_STAIRS = createKey("smoky_quartz_stairs");
        public static final RegistryKey<Item> SMOOTH_SMOKY_QUARTZ_STAIRS = createKey("smooth_smoky_quartz_stairs");

        //dungeon trapped blocks
        public static final RegistryKey<Item> TRAPPED_MUD_BRICKS = createKey("trapped_mud_bricks");
        public static final RegistryKey<Item> TRAPPED_LIGHT_MUD_BRICKS = createKey("trapped_light_mud_bricks");
        public static final RegistryKey<Item> TRAPPED_LUNATIC_STONE = createKey("trapped_lunatic_stone");
        public static final RegistryKey<Item> TRAPPED_LIGHT_LUNATIC_STONE = createKey("trapped_light_lunatic_stone");
        public static final RegistryKey<Item> TRAPPED_GOLDEN_NETHER_BRICKS = createKey("trapped_golden_nether_bricks");
        public static final RegistryKey<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("trapped_light_golden_nether_bricks");

        //dungeon other blocks, loots
        public static final RegistryKey<Item> MUD_BONE_BLOCK = createKey("mud_bone_block");
        public static final RegistryKey<Item> MUD_BONE_PILE_BLOCK = createKey("mud_bone_pile_block");
        public static final RegistryKey<Item> MUD_BONE = createKey("mud_bone");
        public static final RegistryKey<Item> THORNY_COBWEB = createKey("thorny_cobweb");
        public static final RegistryKey<Item> AERIAL_NETHERRACK = createKey("aerial_netherrack");
        public static final RegistryKey<Item> AERIAL_NETHERRACK_SLAB = createKey("aerial_netherrack_slab");
        public static final RegistryKey<Item> AERIAL_NETHERRACK_STAIRS = createKey("aerial_netherrack_stairs");
        public static final RegistryKey<Item> AERIAL_NETHERRACK_WALL = createKey("aerial_netherrack_wall");

        //dungeon bookshelfs
        public static final RegistryKey<Item> MUD_BOOKSHELF = createKey("mud_bookshelf");
        public static final RegistryKey<Item> LUNATIC_BOOKSHELF = createKey("lunatic_bookshelf");
        public static final RegistryKey<Item> GOLDEN_NETHER_BOOKSHELF = createKey("golden_nether_bookshelf");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_BOOKSHELF = createKey("shadow_catacombs_bookshelf");
        public static final RegistryKey<Item> VOLUCITE_BOOKSHELF = createKey("volucite_bookshelf");

        //glyph blocks
        public static final RegistryKey<Item> MUD_GLYPH_BLOCK = createKey("mud_glyph_block");
        public static final RegistryKey<Item> LUNATIC_GLYPH_BLOCK = createKey("lunatic_glyph_block");
        public static final RegistryKey<Item> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = createKey("golden_nether_prison_glyph_block");
        public static final RegistryKey<Item> VOLUCITE_GLYPH_BLOCK = createKey("volucite_glyph_block");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_GLYPH_BLOCK = createKey("shadow_catacombs_glyph_block");

        //trophies
        public static final RegistryKey<Item> MUD_CYCLE_MAGE_TROPHY = createKey("mud_cycle_mage_trophy");
        public static final RegistryKey<Item> LUNAR_PRIEST_TROPHY = createKey("lunar_priest_trophy");
        public static final RegistryKey<Item> LILITH_TROPHY = createKey("lilith_trophy");
        public static final RegistryKey<Item> CHAINED_GOD_TROPHY = createKey("chained_god_trophy");

        //ores
        public static final RegistryKey<Item> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
        public static final RegistryKey<Item> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
        public static final RegistryKey<Item> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
        public static final RegistryKey<Item> FLUORITE_ORE = createKey("fluorite_ore");
        public static final RegistryKey<Item> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
        public static final RegistryKey<Item> RUBY_ORE = createKey("ruby_ore");
        public static final RegistryKey<Item> AZURITE_ORE = createKey("azurite_ore");
        public static final RegistryKey<Item> VOLUCITE_ORE = createKey("volucite_ore");
        public static final RegistryKey<Item> OBSIDIAN_ORE = createKey("obsidian_ore");
        public static final RegistryKey<Item> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");

        public static final RegistryKey<Item> RAW_RUBY_BLOCK = createKey("raw_ruby_block");
        public static final RegistryKey<Item> RAW_AZURITE_BLOCK = createKey("raw_azurite_crystal_block");
        public static final RegistryKey<Item> RAW_VOLUCITE_BLOCK = createKey("raw_volucite_block");

        public static final RegistryKey<Item> RAW_RUBY  = createKey("raw_ruby");
        public static final RegistryKey<Item> RAW_AZURITE  = createKey("raw_azurite_crystal");
        public static final RegistryKey<Item> RAW_VOLUCITE  = createKey("raw_volucite");

        public static final RegistryKey<Item> FLUORITE_BLOCK = createKey("fluorite_block");
        public static final RegistryKey<Item> MAGMATIC_GEL_BLOCK = createKey("magmatic_gel_block");
        public static final RegistryKey<Item> RUBY_BLOCK = createKey("ruby_block");
        public static final RegistryKey<Item> AZURITE_BLOCK = createKey("azurite_block");
        public static final RegistryKey<Item> VOLUCITE_BLOCK = createKey("volucite_block");

        public static final RegistryKey<Item> FLUORITE = createKey("fluorite");
        public static final RegistryKey<Item> MAGMATIC_GEL = createKey("magmatic_gel");
        public static final RegistryKey<Item> RUBY = createKey("ruby");
        public static final RegistryKey<Item> AZURITE_CRYSTAL = createKey("azurite_crystal");
        public static final RegistryKey<Item> VOLUCITE_VIBRANT = createKey("volucite_vibrant");

        public static final RegistryKey<Item> OVERHEATED_RUBY = createKey("overheated_ruby");
        public static final RegistryKey<Item> OVERHEATED_VOLUCITE = createKey("overheated_volucite");

        //legendary ores
        public static final RegistryKey<Item> ARSONIST_INGOT = createKey("arsonist_ingot");
        public static final RegistryKey<Item> LUNATIC_CRYSTAL = createKey("lunatic_crystal");
        public static final RegistryKey<Item> OBSIDIAN_SHARD = createKey("obsidian_shard");
        public static final RegistryKey<Item> CURSED_CRYSAL = createKey("cursed_crystal");

        public static final RegistryKey<Item> ARSONIST_BLOCK = createKey("arsonist_block");
        public static final RegistryKey<Item> LUNATIC_CRYSTAL_BLOCK = createKey("lunatic_crystal_block");
        public static final RegistryKey<Item> CURSED_CRYSAL_BLOCK = createKey("cursed_crystal_block");

        //cactus
        public static final RegistryKey<Item> SKY_CACTUS = createKey("sky_cactus");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_PLANKS = createKey("sky_cactus_fiber_planks");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_BOOKSHELF = createKey("sky_cactus_fiber_bookshelf");
        public static final RegistryKey<Item> VIBRANT_SKY_CACTUS = createKey("vibrant_sky_cactus");
        public static final RegistryKey<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN = createKey("vibrant_sky_cactus_fiber_lantern");

        //bushes
        public static final RegistryKey<Item> AERIAL_BERRY_SEEDS = createKey("aerial_berry_seeds");
        public static final RegistryKey<Item> VIBRANT_AERIAL_BERRY_SEEDS = createKey("vibrant_aerial_berry_seeds");

        //crops
        public static final RegistryKey<Item> STELLAR_WHEAT_SEEDS = createKey("stellar_wheat_seeds");
        public static final RegistryKey<Item> BLUE_MEANIE_SPORES = createKey("blue_meanie_spores");
        public static final RegistryKey<Item> STELLAR_WHEAT = createKey("stellar_wheat");
        public static final RegistryKey<Item> BLUE_MEANIE_CAP = createKey("blue_meanie_cap");

        //vertical growing plants
        public static final RegistryKey<Item> CLIMBING_VINE = createKey("climbing_vine");
        public static final RegistryKey<Item> STELLAR_SUGAR_CANE = createKey("stellar_sugar_cane");

        //chorus like
        public static final RegistryKey<Item> FULL_MOON_FLOWER = createKey("full_moon_flower");

        //vines
        public static final RegistryKey<Item> VINE_BLOSSOM  = createKey("vine_blossom");
        public static final RegistryKey<Item> LAZULI_ROOTS = createKey("lazuli_roots");
        public static final RegistryKey<Item> STELLAR_ROOTS = createKey("stellar_roots");
        public static final RegistryKey<Item> DEAD_ROOTS = createKey("dead_roots");
        public static final RegistryKey<Item> GLOWING_ROOTS = createKey("glowing_roots");
        public static final RegistryKey<Item> SHADOW_GLOWING_ROOTS = createKey("shadow_glowing_roots");

        //grass
        public static final RegistryKey<Item> STELLAR_GRASS = createKey("stellar_grass");
        public static final RegistryKey<Item> STELLAR_GRASS_BALL = createKey("stellar_grass_ball");
        public static final RegistryKey<Item> STELLAR_FERN = createKey("stellar_fern");
        public static final RegistryKey<Item> STELLAR_TALL_GRASS = createKey("stellar_tall_grass");
        public static final RegistryKey<Item> STELLAR_TALL_FERN = createKey("stellar_tall_fern");
        public static final RegistryKey<Item> STELLAR_VERY_TALL_GRASS = createKey("stellar_very_tall_grass");
        public static final RegistryKey<Item> BLUISH_FERN = createKey("bluish_fern");
        public static final RegistryKey<Item> TALL_BLUISH_FERN = createKey("tall_bluish_fern");
        public static final RegistryKey<Item> POLYCHROME_FERN = createKey("polychrome_fern");
        public static final RegistryKey<Item> TALL_POLYCHROME_FERN = createKey("tall_polychrome_fern");
        public static final RegistryKey<Item> STELLAR_DEAD_BUSH = createKey("stellar_dead_bush");
        public static final RegistryKey<Item> BRAMBLES = createKey("brambles");
        public static final RegistryKey<Item> SHADOW_BRAMBLES = createKey("shadow_brambles");
        public static final RegistryKey<Item> SHADOW_GRASS = createKey("shadow_grass");
        public static final RegistryKey<Item> SHADOW_GRASS_BALL = createKey("shadow_grass_ball");
        public static final RegistryKey<Item> PURPLISH_STELLAR_GRASS = createKey("purplish_stellar_grass");
        public static final RegistryKey<Item> STELLAR_CLOVERS = createKey("stellar_clovers");
        public static final RegistryKey<Item> GLOWING_STELLAR_GRASS = createKey("glowing_stellar_grass");

        //flowers
        public static final RegistryKey<Item> BLUE_FLOWER = createKey("blue_flower");
        public static final RegistryKey<Item> BLACK_ROSE = createKey("black_rose");
        public static final RegistryKey<Item> BELLFLOWER = createKey("bellflower");

        //with gui
        public static final RegistryKey<Item> OSCILLATOR = createKey("oscillator");
        public static final RegistryKey<Item> FREEZER = createKey("freezer");
        public static final RegistryKey<Item> STELLAR_FURNACE = createKey("stellar_furnace");
        public static final RegistryKey<Item> GHOST_STELLAR_FURNACE = createKey("ghost_stellar_furnace");

        //chests
        public static final RegistryKey<Item> AERIAL_TREE_CHEST = createKey("aerial_tree_chest");
        public static final RegistryKey<Item> GOLDEN_BEECH_CHEST = createKey("golden_beech_chest");
        public static final RegistryKey<Item> COPPER_PINE_CHEST = createKey("copper_pine_chest");
        public static final RegistryKey<Item> LAPIS_ROBINIA_CHEST = createKey("lapis_robinia_chest");
        public static final RegistryKey<Item> SHADOW_PINE_CHEST = createKey("shadow_pine_chest");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_CHEST = createKey("stellar_jungle_tree_chest");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_CHEST = createKey("sky_cactus_fiber_chest");
        public static final RegistryKey<Item> GRAY_SHROOM_CHEST = createKey("gray_shroom_chest");
        public static final RegistryKey<Item> MUD_CHEST = createKey("mud_chest");
        public static final RegistryKey<Item> LUNATIC_CHEST = createKey("lunatic_chest");
        public static final RegistryKey<Item> VOLUCITE_CHEST = createKey("volucite_chest");
        public static final RegistryKey<Item> SHADOW_CATACOMBS_CHEST = createKey("shadow_catacombs_chest");
        public static final RegistryKey<Item> GOLDEN_NETHER_CHEST = createKey("golden_nether_chest");

        //chest mimics
        public static final RegistryKey<Item> AERIAL_TREE_CHEST_MIMIC = createKey("aerial_tree_chest_mimic");
        public static final RegistryKey<Item> GOLDEN_BEECH_CHEST_MIMIC = createKey("golden_beech_chest_mimic");
        public static final RegistryKey<Item> COPPER_PINE_CHEST_MIMIC = createKey("copper_pine_chest_mimic");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_CHEST_MIMIC = createKey("sky_cactus_fiber_chest_mimic");

        //barrel mimics
        public static final RegistryKey<Item> SHADOW_PINE_BARREL_MIMIC = createKey("shadow_pine_barrel_mimic");

        //fences, bars or walls
        public static final RegistryKey<Item> AERIAL_TREE_FENCE = createKey("aerial_tree_fence");
        public static final RegistryKey<Item> GOLDEN_BEECH_FENCE = createKey("golden_beech_fence");
        public static final RegistryKey<Item> COPPER_PINE_FENCE = createKey("copper_pine_fence");
        public static final RegistryKey<Item> LAPIS_ROBINIA_FENCE = createKey("lapis_robinia_fence");
        public static final RegistryKey<Item> SHADOW_PINE_FENCE = createKey("shadow_pine_fence");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_FENCE = createKey("stellar_jungle_tree_fence");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_FENCE = createKey("sky_cactus_fiber_fence");
        public static final RegistryKey<Item> GRAY_SHROOM_FENCE = createKey("gray_shroom_fence");
        public static final RegistryKey<Item> RUBY_BARS = createKey("ruby_bars");
        public static final RegistryKey<Item> STELLAR_STONE_WALL = createKey("stellar_stone_wall");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE_WALL = createKey("stellar_cobblestone_wall");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS_WALL = createKey("stellar_stone_bricks_wall");
        public static final RegistryKey<Item> MOSSY_STELLAR_STONE_WALL = createKey("mossy_stellar_stone_wall");
        public static final RegistryKey<Item> MOSSY_STELLAR_COBBLESTONE_WALL = createKey("mossy_stellar_cobblestone_wall");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_WALL = createKey("slippery_sand_stone_wall");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("slippery_sand_stone_bricks_wall");
        public static final RegistryKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("cracked_slippery_sand_stone_bricks_wall");
        public static final RegistryKey<Item> GLAUCOPHANITE_WALL = createKey("glaucophanite_wall");
        public static final RegistryKey<Item> POLISHED_GLAUCOPHANITE_WALL = createKey("polished_glaucophanite_wall");
        public static final RegistryKey<Item> MAGMATIC_GEL_WALL = createKey("magmatic_gel_wall");

        //gates
        public static final RegistryKey<Item> AERIAL_TREE_GATE = createKey("aerial_tree_gate");
        public static final RegistryKey<Item> GOLDEN_BEECH_GATE = createKey("golden_beech_gate");
        public static final RegistryKey<Item> COPPER_PINE_GATE = createKey("copper_pine_gate");
        public static final RegistryKey<Item> LAPIS_ROBINIA_GATE = createKey("lapis_robinia_gate");
        public static final RegistryKey<Item> SHADOW_PINE_GATE = createKey("shadow_pine_gate");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_GATE = createKey("stellar_jungle_tree_gate");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_GATE = createKey("sky_cactus_fiber_gate");
        public static final RegistryKey<Item> GRAY_SHROOM_GATE = createKey("gray_shroom_gate");

        //doors
        public static final RegistryKey<Item> AERIAL_TREE_DOOR = createKey("aerial_tree_door");
        public static final RegistryKey<Item> GOLDEN_BEECH_DOOR = createKey("golden_beech_door");
        public static final RegistryKey<Item> COPPER_PINE_DOOR = createKey("copper_pine_door");
        public static final RegistryKey<Item> LAPIS_ROBINIA_DOOR = createKey("lapis_robinia_door");
        public static final RegistryKey<Item> SHADOW_PINE_DOOR = createKey("shadow_pine_door");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_DOOR = createKey("stellar_jungle_tree_door");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_DOOR = createKey("sky_cactus_fiber_door");
        public static final RegistryKey<Item> GRAY_SHROOM_DOOR = createKey("gray_shroom_door");
        public static final RegistryKey<Item> RUBY_DOOR = createKey("ruby_door");

        //trapdoors
        public static final RegistryKey<Item> AERIAL_TREE_TRAPDOOR = createKey("aerial_tree_trapdoor");
        public static final RegistryKey<Item> GOLDEN_BEECH_TRAPDOOR = createKey("golden_beech_trapdoor");
        public static final RegistryKey<Item> COPPER_PINE_TRAPDOOR = createKey("copper_pine_trapdoor");
        public static final RegistryKey<Item> LAPIS_ROBINIA_TRAPDOOR = createKey("lapis_robinia_trapdoor");
        public static final RegistryKey<Item> SHADOW_PINE_TRAPDOOR = createKey("shadow_pine_trapdoor");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_TRAPDOOR = createKey("stellar_jungle_tree_trapdoor");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_TRAPDOOR = createKey("sky_cactus_fiber_trapdoor");
        public static final RegistryKey<Item> GRAY_SHROOM_TRAPDOOR = createKey("gray_shroom_trapdoor");
        public static final RegistryKey<Item> RUBY_TRAPDOOR = createKey("ruby_trapdoor");

        //buttons
        public static final RegistryKey<Item> STELLAR_STONE_BUTTON = createKey("stellar_stone_button");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE_BUTTON = createKey("stellar_cobblestone_button");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS_BUTTON = createKey("stellar_stone_bricks_button");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BUTTON = createKey("slippery_sand_stone_button");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON = createKey("slippery_sand_stone_bricks_button");
        public static final RegistryKey<Item> AERIAL_TREE_BUTTON = createKey("aerial_tree_button");
        public static final RegistryKey<Item> GOLDEN_BEECH_BUTTON = createKey("golden_beech_button");
        public static final RegistryKey<Item> COPPER_PINE_BUTTON = createKey("copper_pine_button");
        public static final RegistryKey<Item> LAPIS_ROBINIA_BUTTON = createKey("lapis_robinia_button");
        public static final RegistryKey<Item> SHADOW_PINE_BUTTON = createKey("shadow_pine_button");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_BUTTON = createKey("stellar_jungle_tree_button");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_BUTTON = createKey("sky_cactus_fiber_button");
        public static final RegistryKey<Item> GRAY_SHROOM_BUTTON = createKey("gray_shroom_button");
        public static final RegistryKey<Item> GLAUCOPHANITE_BUTTON = createKey("glaucophanite_button");

        //pressure plates
        public static final RegistryKey<Item> STELLAR_STONE_PRESSURE_PLATE = createKey("stellar_stone_pressure_plate");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE = createKey("stellar_cobblestone_pressure_plate");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE = createKey("stellar_stone_bricks_pressure_plate");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE = createKey("slippery_sand_stone_pressure_plate");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = createKey("slippery_sand_stone_bricks_pressure_plate");
        public static final RegistryKey<Item> AERIAL_TREE_PRESSURE_PLATE = createKey("aerial_tree_pressure_plate");
        public static final RegistryKey<Item> GOLDEN_BEECH_PRESSURE_PLATE = createKey("golden_beech_pressure_plate");
        public static final RegistryKey<Item> COPPER_PINE_PRESSURE_PLATE = createKey("copper_pine_pressure_plate");
        public static final RegistryKey<Item> LAPIS_ROBINIA_PRESSURE_PLATE = createKey("lapis_robinia_pressure_plate");
        public static final RegistryKey<Item> SHADOW_PINE_PRESSURE_PLATE = createKey("shadow_pine_pressure_plate");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = createKey("stellar_jungle_tree_pressure_plate");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE = createKey("sky_cactus_fiber_pressure_plate");
        public static final RegistryKey<Item> GRAY_SHROOM_PRESSURE_PLATE = createKey("gray_shroom_pressure_plate");
        public static final RegistryKey<Item> GLAUCOPHANITE_PRESSURE_PLATE = createKey("glaucophanite_pressure_plate");

        //slabs
        public static final RegistryKey<Item> AERIAL_TREE_SLAB = createKey("aerial_tree_slab");
        public static final RegistryKey<Item> GOLDEN_BEECH_SLAB = createKey("golden_beech_slab");
        public static final RegistryKey<Item> COPPER_PINE_SLAB = createKey("copper_pine_slab");
        public static final RegistryKey<Item> LAPIS_ROBINIA_SLAB = createKey("lapis_robinia_slab");
        public static final RegistryKey<Item> SHADOW_PINE_SLAB = createKey("shadow_pine_slab");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_SLAB = createKey("stellar_jungle_tree_slab");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_SLAB = createKey("sky_cactus_fiber_slab");
        public static final RegistryKey<Item> GRAY_SHROOM_SLAB = createKey("gray_shroom_slab");
        public static final RegistryKey<Item> STELLAR_STONE_SLAB = createKey("stellar_stone_slab");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE_SLAB = createKey("stellar_cobblestone_slab");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS_SLAB = createKey("stellar_stone_bricks_slab");
        public static final RegistryKey<Item> MOSSY_STELLAR_STONE_SLAB = createKey("mossy_stellar_stone_slab");
        public static final RegistryKey<Item> MOSSY_STELLAR_COBBLESTONE_SLAB = createKey("mossy_stellar_cobblestone_slab");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_SLAB = createKey("slippery_sand_stone_slab");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("slippery_sand_stone_bricks_slab");
        public static final RegistryKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("cracked_slippery_sand_stone_bricks_slab");
        public static final RegistryKey<Item> POLISHED_GLAUCOPHANITE_SLAB = createKey("polished_glaucophanite_slab");
        public static final RegistryKey<Item> MAGMATIC_GEL_SLAB = createKey("magmatic_gel_slab");

        //stairs
        public static final RegistryKey<Item> AERIAL_TREE_STAIRS = createKey("aerial_tree_stairs");
        public static final RegistryKey<Item> GOLDEN_BEECH_STAIRS = createKey("golden_beech_stairs");
        public static final RegistryKey<Item> COPPER_PINE_STAIRS = createKey("copper_pine_stairs");
        public static final RegistryKey<Item> LAPIS_ROBINIA_STAIRS = createKey("lapis_robinia_stairs");
        public static final RegistryKey<Item> SHADOW_PINE_STAIRS = createKey("shadow_pine_stairs");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_STAIRS = createKey("stellar_jungle_tree_stairs");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_STAIRS = createKey("sky_cactus_fiber_stairs");
        public static final RegistryKey<Item> GRAY_SHROOM_STAIRS = createKey("gray_shroom_stairs");
        public static final RegistryKey<Item> STELLAR_STONE_STAIRS = createKey("stellar_stone_stairs");
        public static final RegistryKey<Item> STELLAR_COBBLESTONE_STAIRS = createKey("stellar_cobblestone_stairs");
        public static final RegistryKey<Item> STELLAR_STONE_BRICKS_STAIRS = createKey("stellar_stone_bricks_stairs");
        public static final RegistryKey<Item> MOSSY_STELLAR_STONE_STAIRS = createKey("mossy_stellar_stone_stairs");
        public static final RegistryKey<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS = createKey("mossy_stellar_cobblestone_stairs");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_STAIRS = createKey("slippery_sand_stone_stairs");
        public static final RegistryKey<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("slippery_sand_stone_bricks_stairs");
        public static final RegistryKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("cracked_slippery_sand_stone_bricks_stairs");
        public static final RegistryKey<Item> POLISHED_GLAUCOPHANITE_STAIRS = createKey("polished_glaucophanite_stairs");
        public static final RegistryKey<Item> MAGMATIC_GEL_STAIRS = createKey("magmatic_gel_stairs");

        //signs
        public static final RegistryKey<Item> AERIAL_TREE_SIGN = createKey("aerial_tree_sign");
        public static final RegistryKey<Item> GOLDEN_BEECH_SIGN = createKey("golden_beech_sign");
        public static final RegistryKey<Item> COPPER_PINE_SIGN = createKey("copper_pine_sign");
        public static final RegistryKey<Item> LAPIS_ROBINIA_SIGN = createKey("lapis_robinia_sign");
        public static final RegistryKey<Item> SHADOW_PINE_SIGN = createKey("shadow_pine_sign");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_SIGN = createKey("stellar_jungle_tree_sign");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_SIGN = createKey("sky_cactus_fiber_sign");
        public static final RegistryKey<Item> GRAY_SHROOM_SIGN = createKey("gray_shroom_sign");

        //hanging signs
        public static final RegistryKey<Item> AERIAL_TREE_HANGING_SIGN = createKey("aerial_tree_hanging_sign");
        public static final RegistryKey<Item> GOLDEN_BEECH_HANGING_SIGN = createKey("golden_beech_hanging_sign");
        public static final RegistryKey<Item> COPPER_PINE_HANGING_SIGN = createKey("copper_pine_hanging_sign");
        public static final RegistryKey<Item> LAPIS_ROBINIA_HANGING_SIGN = createKey("lapis_robinia_hanging_sign");
        public static final RegistryKey<Item> SHADOW_PINE_HANGING_SIGN = createKey("shadow_pine_hanging_sign");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_HANGING_SIGN = createKey("stellar_jungle_tree_hanging_sign");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_HANGING_SIGN = createKey("sky_cactus_fiber_hanging_sign");
        public static final RegistryKey<Item> GRAY_SHROOM_HANGING_SIGN = createKey("gray_shroom_hanging_sign");

        //crafting tables
        public static final RegistryKey<Item> AERIAL_TREE_CRAFTING_TABLE = createKey("aerial_tree_crafting_table");
        public static final RegistryKey<Item> GOLDEN_BEECH_CRAFTING_TABLE = createKey("golden_beech_crafting_table");
        public static final RegistryKey<Item> COPPER_PINE_CRAFTING_TABLE = createKey("copper_pine_crafting_table");
        public static final RegistryKey<Item> LAPIS_ROBINIA_CRAFTING_TABLE = createKey("lapis_robinia_crafting_table");
        public static final RegistryKey<Item> SHADOW_PINE_CRAFTING_TABLE = createKey("shadow_pine_crafting_table");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = createKey("stellar_jungle_tree_crafting_table");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE = createKey("sky_cactus_fiber_crafting_table");
        public static final RegistryKey<Item> GRAY_SHROOM_CRAFTING_TABLE = createKey("gray_shroom_crafting_table");

        //barrels
        public static final RegistryKey<Item> AERIAL_TREE_BARREL = createKey("aerial_tree_barrel");
        public static final RegistryKey<Item> GOLDEN_BEECH_BARREL = createKey("golden_beech_barrel");
        public static final RegistryKey<Item> COPPER_PINE_BARREL = createKey("copper_pine_barrel");
        public static final RegistryKey<Item> LAPIS_ROBINIA_BARREL = createKey("lapis_robinia_barrel");
        public static final RegistryKey<Item> SHADOW_PINE_BARREL = createKey("shadow_pine_barrel");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_BARREL = createKey("stellar_jungle_tree_barrel");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_BARREL = createKey("sky_cactus_fiber_barrel");
        public static final RegistryKey<Item> GRAY_SHROOM_BARREL = createKey("gray_shroom_barrel");

        //composters
        public static final RegistryKey<Item> AERIAL_TREE_COMPOSTER = createKey("aerial_tree_composter");
        public static final RegistryKey<Item> GOLDEN_BEECH_COMPOSTER = createKey("golden_beech_composter");
        public static final RegistryKey<Item> COPPER_PINE_COMPOSTER = createKey("copper_pine_composter");
        public static final RegistryKey<Item> LAPIS_ROBINIA_COMPOSTER = createKey("lapis_robinia_composter");
        public static final RegistryKey<Item> SHADOW_PINE_COMPOSTER = createKey("shadow_pine_composter");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_COMPOSTER = createKey("stellar_jungle_tree_composter");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_COMPOSTER = createKey("sky_cactus_fiber_composter");
        public static final RegistryKey<Item> GRAY_SHROOM_COMPOSTER = createKey("gray_shroom_composter");

        //decorative
        public static final RegistryKey<Item> AERIAL_TREE_VINE_ROPE_SPOOL = createKey("aerial_tree_vine_rope_spool");
        public static final RegistryKey<Item> GOLDEN_BEECH_VINE_ROPE_SPOOL = createKey("golden_beech_vine_rope_spool");
        public static final RegistryKey<Item> COPPER_PINE_VINE_ROPE_SPOOL = createKey("copper_pine_vine_rope_spool");
        public static final RegistryKey<Item> LAPIS_ROBINIA_VINE_ROPE_SPOOL = createKey("lapis_robinia_vine_rope_spool");
        public static final RegistryKey<Item> SHADOW_PINE_VINE_ROPE_SPOOL = createKey("shadow_pine_vine_rope_spool");
        public static final RegistryKey<Item> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = createKey("stellar_jungle_tree_vine_rope_spool");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = createKey("sky_cactus_fiber_vine_rope_spool");
        public static final RegistryKey<Item> GRAY_SHROOM_VINE_ROPE_SPOOL = createKey("gray_shroom_vine_rope_spool");

        //item for crafts
        public static final RegistryKey<Item> SKY_STICK = createKey("sky_stick");
        public static final RegistryKey<Item> SKY_BOWL = createKey("sky_bowl");
        public static final RegistryKey<Item> SHADOW_SHARD = createKey("shadow_shard");
        public static final RegistryKey<Item> ROTTEN_LEATHER = createKey("rotten_leather");
        public static final RegistryKey<Item> VENOMOUS_SNAKE_SKIN = createKey("venomous_snake_skin");
        public static final RegistryKey<Item> ARSONIST_UPGRADE_SMITHING_TEMPLATE = createKey("arsonist_upgrade_smithing_template");

        //projectile item
        public static final RegistryKey<Item> STELLAR_EGG = createKey("stellar_egg");
        public static final RegistryKey<Item> DIMENSION_SHATTERER_PROJECTILE = createKey("dimension_shatterer_projectile");

        //shurikens
        public static final RegistryKey<Item> IRON_SHURIKEN = createKey("iron_shuriken");
        public static final RegistryKey<Item> GOLD_SHURIKEN = createKey("gold_shuriken");
        public static final RegistryKey<Item> DIAMOND_SHURIKEN = createKey("diamond_shuriken");
        public static final RegistryKey<Item> NETHERITE_SHURIKEN = createKey("netherite_shuriken");
        public static final RegistryKey<Item> RUBY_SHURIKEN = createKey("ruby_shuriken");
        public static final RegistryKey<Item> AZURITE_SHURIKEN = createKey("azurite_shuriken");
        public static final RegistryKey<Item> MAGMATIC_GEL_SHURIKEN = createKey("magmatic_gel_shuriken");
        public static final RegistryKey<Item> VOLUCITE_SHURIKEN = createKey("volucite_shuriken");
        public static final RegistryKey<Item> OBSIDIAN_SHURIKEN = createKey("obsidian_shuriken");
        public static final RegistryKey<Item> LUNATIC_CRYSTAL_SHURIKEN = createKey("lunatic_crystal_shuriken");
        public static final RegistryKey<Item> ARSONIST_SHURIKEN = createKey("arsonist_shuriken");
        public static final RegistryKey<Item> LIGHTNING_SHURIKEN = createKey("lightning_shuriken");

        //food
        public static final RegistryKey<Item> AERIAL_BERRY = createKey("aerial_berry");
        public static final RegistryKey<Item> ROASTED_AERIAL_BERRY = createKey("roasted_aerial_berry");
        public static final RegistryKey<Item> VIBRANT_AERIAL_BERRY = createKey("vibrant_aerial_berry");
        public static final RegistryKey<Item> FROZEN_AERIAL_BERRY = createKey("frozen_aerial_berry");
        public static final RegistryKey<Item> STELLAR_BREAD = createKey("stellar_bread");
        public static final RegistryKey<Item> FROZEN_MUTTON = createKey("frozen_mutton");
        public static final RegistryKey<Item> VIBRANT_CHICKEN = createKey("vibrant_chicken");
        public static final RegistryKey<Item> FROZEN_CHICKEN = createKey("frozen_chicken");
        public static final RegistryKey<Item> RUBY_AERIAL_BERRY = createKey("ruby_aerial_berry");
        public static final RegistryKey<Item> VOLUCITE_AERIAL_BERRY = createKey("volucite_aerial_berry");
        public static final RegistryKey<Item> GLOWING_STICK_FRUIT = createKey("glowing_stick_fruit");
        public static final RegistryKey<Item> VIBRANT_GLOWING_STICK_FRUIT = createKey("vibrant_glowing_stick_fruit");
        public static final RegistryKey<Item> FROZEN_GLOWING_STICK_FRUIT = createKey("frozen_glowing_stick_fruit");
        public static final RegistryKey<Item> CORTINARIUS_VIOLACEUS_PIECE = createKey("cortinarius_violaceus_piece");
        public static final RegistryKey<Item> GANODERMA_APPLANATUM_PIECE = createKey("ganoderma_applanatum_piece");
        public static final RegistryKey<Item> DARK_SHADOW_FRUIT = createKey("dark_shadow_fruit");
        public static final RegistryKey<Item> PURPLE_SHADOW_FRUIT = createKey("purple_shadow_fruit");
        public static final RegistryKey<Item> SHADOW_FRUIT_STEW = createKey("shadow_fruit_stew");
        public static final RegistryKey<Item> SOLID_ETHER_SOUP = createKey("solid_ether_soup");
        public static final RegistryKey<Item> VIBRANT_SOLID_ETHER_SOUP = createKey("vibrant_solid_ether_soup");
        public static final RegistryKey<Item> FROZEN_SOLID_ETHER_SOUP = createKey("frozen_solid_ether_soup");
        public static final RegistryKey<Item> SHADOW_SPIDER_EYE = createKey("shadow_spider_eye");
        public static final RegistryKey<Item> PHANTOM_MEAT = createKey("phantom_meat");
        public static final RegistryKey<Item> VIBRANT_PHANTOM_MEAT = createKey("vibrant_phantom_meat");
        public static final RegistryKey<Item> FROZEN_PHANTOM_MEAT = createKey("frozen_phantom_meat");
        public static final RegistryKey<Item> COOKED_PHANTOM_MEAT = createKey("cooked_phantom_meat");
        public static final RegistryKey<Item> TURTLE_MEAT = createKey("turtle_meat");
        public static final RegistryKey<Item> VIBRANT_TURTLE_MEAT = createKey("vibrant_turtle_meat");
        public static final RegistryKey<Item> FROZEN_TURTLE_MEAT = createKey("frozen_turtle_meat");
        public static final RegistryKey<Item> COOKED_TURTLE_MEAT = createKey("cooked_turtle_meat");
        public static final RegistryKey<Item> GODS_VOLUCITE_AERIAL_BERRY = createKey("gods_volucite_aerial_berry");
        public static final RegistryKey<Item> COPPER_PINE_CONE = createKey("copper_pine_cone");
        public static final RegistryKey<Item> AZURITE_COPPER_PINE_CONE = createKey("azurite_copper_pine_cone");
        public static final RegistryKey<Item> PHOENIX_FEATHER = createKey("phoenix_feather");
        public static final RegistryKey<Item> SKY_CACTUS_FIBER = createKey("sky_cactus_fiber");
        public static final RegistryKey<Item> VIBRANT_SKY_CACTUS_FIBER = createKey("vibrant_sky_cactus_fiber");
        public static final RegistryKey<Item> WHITE_SOLID_ETHER_FRAGMENT = createKey("white_solid_ether_fragment");
        public static final RegistryKey<Item> BLUE_SOLID_ETHER_FRAGMENT = createKey("blue_solid_ether_fragment");
        public static final RegistryKey<Item> GOLDEN_SOLID_ETHER_FRAGMENT = createKey("golden_solid_ether_fragment");
        public static final RegistryKey<Item> GREEN_SOLID_ETHER_FRAGMENT = createKey("green_solid_ether_fragment");
        public static final RegistryKey<Item> PURPLE_SOLID_ETHER_FRAGMENT = createKey("purple_solid_ether_fragment");
        public static final RegistryKey<Item> GOLDEN_NETHER_MEAT_PIECE = createKey("golden_nether_meat_piece");
        public static final RegistryKey<Item> GOLDEN_NETHER_STEAK = createKey("golden_nether_steak");
        public static final RegistryKey<Item> VIBRANT_GOLDEN_NETHER_STEAK = createKey("vibrant_golden_nether_steak");

        //buckets
        public static final RegistryKey<Item> IRON_LIQUID_OF_GODS_BUCKET = createKey("iron_liquid_of_gods_bucket");
        public static final RegistryKey<Item> RUBY_LIQUID_OF_GODS_BUCKET = createKey("ruby_liquid_of_gods_bucket");
        public static final RegistryKey<Item> RUBY_BUCKET = createKey("ruby_bucket");
        public static final RegistryKey<Item> RUBY_WATER_BUCKET = createKey("ruby_water_bucket");
        public static final RegistryKey<Item> RUBY_MILK_BUCKET = createKey("ruby_milk_bucket");

        //arrows & bows
        public static final RegistryKey<Item> RUBY_BLOWPIPE_ARROW = createKey("ruby_blowpipe_arrow");
        public static final RegistryKey<Item> VOLUCITE_BLOWPIPE_ARROW = createKey("volucite_blowpipe_arrow");

        public static final RegistryKey<Item> RUBY_BLOWPIPE = createKey("ruby_blowpipe");
        public static final RegistryKey<Item> VOLUCITE_BLOWPIPE = createKey("volucite_blowpipe");

        //music discs
        public static final RegistryKey<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = createKey("music_disc_aerial_hell_theme_tommaup");
        public static final RegistryKey<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = createKey("music_disc_sweden_andreas_zoeller");
        public static final RegistryKey<Item> MUSIC_DISC_ENTHUSIAST_TOURS = createKey("music_disc_enthusiast_tours");
        public static final RegistryKey<Item> MUSIC_DISC_BMINOR_ARULO = createKey("music_disc_bminor_arulo");
        public static final RegistryKey<Item> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = createKey("music_disc_retro_exploration_tommaup");

        //tools
        public static final RegistryKey<Item> SKY_WOOD_PICKAXE = createKey("sky_wood_pickaxe");
        public static final RegistryKey<Item> STELLAR_STONE_PICKAXE = createKey("stellar_stone_pickaxe");
        public static final RegistryKey<Item> RUBY_PICKAXE = createKey("ruby_pickaxe");
        public static final RegistryKey<Item> AZURITE_PICKAXE = createKey("azurite_pickaxe");
        public static final RegistryKey<Item> MAGMATIC_GEL_PICKAXE = createKey("magmatic_gel_pickaxe");
        public static final RegistryKey<Item> OBSIDIAN_PICKAXE = createKey("obsidian_pickaxe");
        public static final RegistryKey<Item> VOLUCITE_PICKAXE = createKey("volucite_pickaxe");
        public static final RegistryKey<Item> LUNATIC_PICKAXE = createKey("lunatic_pickaxe");
        public static final RegistryKey<Item> ARSONIST_PICKAXE = createKey("arsonist_pickaxe");

        public static final RegistryKey<Item> MAGMA_CUBE_PICKAXE = createKey("magma_cube_pickaxe");
        public static final RegistryKey<Item> STELLAR_STONE_BREAKER = createKey("stellar_stone_breaker");

        public static final RegistryKey<Item> SKY_WOOD_SHOVEL = createKey("sky_wood_shovel");
        public static final RegistryKey<Item> STELLAR_STONE_SHOVEL = createKey("stellar_stone_shovel");
        public static final RegistryKey<Item> RUBY_SHOVEL = createKey("ruby_shovel");
        public static final RegistryKey<Item> AZURITE_SHOVEL = createKey("azurite_shovel");
        public static final RegistryKey<Item> MAGMATIC_GEL_SHOVEL = createKey("magmatic_gel_shovel");
        public static final RegistryKey<Item> OBSIDIAN_SHOVEL = createKey("obsidian_shovel");
        public static final RegistryKey<Item> VOLUCITE_SHOVEL = createKey("volucite_shovel");
        public static final RegistryKey<Item> LUNATIC_SHOVEL = createKey("lunatic_shovel");
        public static final RegistryKey<Item> ARSONIST_SHOVEL = createKey("arsonist_shovel");

        public static final RegistryKey<Item> MAGMA_CUBE_SHOVEL = createKey("magma_cube_shovel");

        public static final RegistryKey<Item> SKY_WOOD_AXE = createKey("sky_wood_axe");
        public static final RegistryKey<Item> STELLAR_STONE_AXE = createKey("stellar_stone_axe");
        public static final RegistryKey<Item> RUBY_AXE = createKey("ruby_axe");
        public static final RegistryKey<Item> AZURITE_AXE = createKey("azurite_axe");
        public static final RegistryKey<Item> MAGMATIC_GEL_AXE = createKey("magmatic_gel_axe");
        public static final RegistryKey<Item> OBSIDIAN_AXE = createKey("obsidian_axe");
        public static final RegistryKey<Item> VOLUCITE_AXE = createKey("volucite_axe");
        public static final RegistryKey<Item> LUNATIC_AXE = createKey("lunatic_axe");
        public static final RegistryKey<Item> ARSONIST_AXE = createKey("arsonist_axe");

        public static final RegistryKey<Item> HEAVY_AXE = createKey("heavy_axe");
        public static final RegistryKey<Item> AXE_OF_LIGHT = createKey("axe_of_light");
        public static final RegistryKey<Item> CURSED_AXE = createKey("cursed_axe");
        public static final RegistryKey<Item> BERSERK_AXE = createKey("berserk_axe");

        public static final RegistryKey<Item> SKY_WOOD_HOE = createKey("sky_wood_hoe");
        public static final RegistryKey<Item> STELLAR_STONE_HOE = createKey("stellar_stone_hoe");
        public static final RegistryKey<Item> RUBY_HOE = createKey("ruby_hoe");
        public static final RegistryKey<Item> AZURITE_HOE = createKey("azurite_hoe");
        public static final RegistryKey<Item> MAGMATIC_GEL_HOE = createKey("magmatic_gel_hoe");
        public static final RegistryKey<Item> OBSIDIAN_HOE = createKey("obsidian_hoe");
        public static final RegistryKey<Item> VOLUCITE_HOE = createKey("volucite_hoe");
        public static final RegistryKey<Item> LUNATIC_HOE = createKey("lunatic_hoe");
        public static final RegistryKey<Item> ARSONIST_HOE = createKey("arsonist_hoe");

        public static final RegistryKey<Item> REAPER_SCYTHE = createKey("reaper_scythe");

        //weapons
        public static final RegistryKey<Item> SKY_WOOD_SWORD = createKey("sky_wood_sword");
        public static final RegistryKey<Item> STELLAR_STONE_SWORD = createKey("stellar_stone_sword");
        public static final RegistryKey<Item> RUBY_SWORD = createKey("ruby_sword");
        public static final RegistryKey<Item> AZURITE_SWORD = createKey("azurite_sword");
        public static final RegistryKey<Item> MAGMATIC_GEL_SWORD = createKey("magmatic_gel_sword");
        public static final RegistryKey<Item> OBSIDIAN_SWORD = createKey("obsidian_sword");
        public static final RegistryKey<Item> VOLUCITE_SWORD = createKey("volucite_sword");
        public static final RegistryKey<Item> LUNATIC_SWORD = createKey("lunatic_sword");
        public static final RegistryKey<Item> ARSONIST_SWORD = createKey("arsonist_sword");

        public static final RegistryKey<Item> HEAVY_SWORD = createKey("heavy_sword");
        public static final RegistryKey<Item> HEALTH_BOOST_SWORD = createKey("health_boost_sword");
        public static final RegistryKey<Item> NINJA_SWORD = createKey("ninja_sword");
        public static final RegistryKey<Item> NINJA_MASTER_SWORD = createKey("ninja_master_sword");
        public static final RegistryKey<Item> GLOUTON_SWORD = createKey("glouton_sword");
        public static final RegistryKey<Item> RANDOM_SWORD = createKey("random_sword");
        public static final RegistryKey<Item> DISLOYAL_SWORD = createKey("disloyal_sword");
        public static final RegistryKey<Item> CURSED_SWORD = createKey("cursed_sword");
        public static final RegistryKey<Item> ABSOLUTE_ZERO_SWORD = createKey("absolute_zero_sword");
        public static final RegistryKey<Item> SWORD_OF_LIGHT = createKey("sword_of_light");
        public static final RegistryKey<Item> ANTIDOTE_SWORD = createKey("antidote_sword");
        public static final RegistryKey<Item> NETHERIAN_KING_SWORD = createKey("netherian_king_sword");
        public static final RegistryKey<Item> GLASS_CANON_SWORD = createKey("glass_canon_sword");
        public static final RegistryKey<Item> GOD_SWORD = createKey("god_sword");

        public static final RegistryKey<Item> FORGOTTEN_BATTLE_TRIDENT = createKey("forgotten_battle_trident");

        //armor
        public static final RegistryKey<Item> RUBY_HELMET = createKey("ruby_helmet");
        public static final RegistryKey<Item> RUBY_CHESTPLATE = createKey("ruby_chestplate");
        public static final RegistryKey<Item> RUBY_LEGGINGS = createKey("ruby_leggings");
        public static final RegistryKey<Item> RUBY_BOOTS = createKey("ruby_boots");

        public static final RegistryKey<Item> AZURITE_HELMET = createKey("azurite_helmet");
        public static final RegistryKey<Item> AZURITE_CHESTPLATE = createKey("azurite_chestplate");
        public static final RegistryKey<Item> AZURITE_LEGGINGS = createKey("azurite_leggings");
        public static final RegistryKey<Item> AZURITE_BOOTS = createKey("azurite_boots");

        public static final RegistryKey<Item> OBSIDIAN_HELMET = createKey("obsidian_helmet");
        public static final RegistryKey<Item> OBSIDIAN_CHESTPLATE = createKey("obsidian_chestplate");
        public static final RegistryKey<Item> OBSIDIAN_LEGGINGS = createKey("obsidian_leggings");
        public static final RegistryKey<Item> OBSIDIAN_BOOTS = createKey("obsidian_boots");

        public static final RegistryKey<Item> VOLUCITE_HELMET = createKey("volucite_helmet");
        public static final RegistryKey<Item> VOLUCITE_CHESTPLATE = createKey("volucite_chestplate");
        public static final RegistryKey<Item> VOLUCITE_LEGGINGS = createKey("volucite_leggings");
        public static final RegistryKey<Item> VOLUCITE_BOOTS = createKey("volucite_boots");

        public static final RegistryKey<Item> MAGMATIC_GEL_HELMET = createKey("magmatic_gel_helmet");
        public static final RegistryKey<Item> MAGMATIC_GEL_CHESTPLATE = createKey("magmatic_gel_chestplate");
        public static final RegistryKey<Item> MAGMATIC_GEL_LEGGINGS = createKey("magmatic_gel_leggings");
        public static final RegistryKey<Item> MAGMATIC_GEL_BOOTS = createKey("magmatic_gel_boots");

        public static final RegistryKey<Item> LUNATIC_HELMET = createKey("lunatic_helmet");
        public static final RegistryKey<Item> LUNATIC_CHESTPLATE = createKey("lunatic_chestplate");
        public static final RegistryKey<Item> LUNATIC_LEGGINGS = createKey("lunatic_leggings");
        public static final RegistryKey<Item> LUNATIC_BOOTS = createKey("lunatic_boots");

        public static final RegistryKey<Item> ARSONIST_HELMET = createKey("arsonist_helmet");
        public static final RegistryKey<Item> ARSONIST_CHESTPLATE = createKey("arsonist_chestplate");
        public static final RegistryKey<Item> ARSONIST_LEGGINGS = createKey("arsonist_leggings");
        public static final RegistryKey<Item> ARSONIST_BOOTS = createKey("arsonist_boots");

        public static final RegistryKey<Item> SHADOW_HELMET = createKey("shadow_helmet");
        public static final RegistryKey<Item> SHADOW_CHESTPLATE = createKey("shadow_chestplate");
        public static final RegistryKey<Item> SHADOW_LEGGINGS = createKey("shadow_leggings");
        public static final RegistryKey<Item> SHADOW_BOOTS = createKey("shadow_boots");

        //effect totems
        public static final RegistryKey<Item> REGENERATION_TOTEM = createKey("regeneration_totem");
        public static final RegistryKey<Item> SPEED_TOTEM = createKey("speed_totem");
        public static final RegistryKey<Item> SPEED_II_TOTEM = createKey("speed_ii_totem");
        public static final RegistryKey<Item> NIGHT_VISION_TOTEM = createKey("night_vision_totem");
        public static final RegistryKey<Item> AGILITY_TOTEM = createKey("agility_totem");
        public static final RegistryKey<Item> HERO_TOTEM = createKey("hero_totem");
        public static final RegistryKey<Item> HEAD_IN_THE_CLOUDS_TOTEM = createKey("head_in_the_clouds_totem");
        public static final RegistryKey<Item> GOD_TOTEM = createKey("god_totem");
        public static final RegistryKey<Item> CURSED_TOTEM = createKey("cursed_totem");
        public static final RegistryKey<Item> SHADOW_TOTEM = createKey("shadow_totem");

        //spawn eggs
        public static final RegistryKey<Item> STELLAR_STONE_AUTOMATON_SPAWN_EGG = createKey("stellar_stone_automaton_spawn_egg");
        public static final RegistryKey<Item> EVIL_COW_SPAWN_EGG = createKey("evil_cow_spawn_egg");
        public static final RegistryKey<Item> CORTINARIUS_COW_SPAWN_EGG = createKey("cortinarius_cow_spawn_egg");
        public static final RegistryKey<Item> STELLAR_ENT_SPAWN_EGG = createKey("stellar_ent_spawn_egg");
        public static final RegistryKey<Item> VENOMOUS_SNAKE_SPAWN_EGG = createKey("venomous_snake_spawn_egg");
        public static final RegistryKey<Item> WORM_SPAWN_EGG = createKey("worm_spawn_egg");
        public static final RegistryKey<Item> STELLAR_CHICKEN_SPAWN_EGG = createKey("stellar_chicken_spawn_egg");
        public static final RegistryKey<Item> STELLAR_BOAR_SPAWN_EGG = createKey("stellar_boar_spawn_egg");
        public static final RegistryKey<Item> SHROOMBOOM_SPAWN_EGG = createKey("shroomboom_spawn_egg");
        public static final RegistryKey<Item> VERDIGRIS_ZOMBIE_SPAWN_EGG = createKey("verdigris_zombie_spawn_egg");
        public static final RegistryKey<Item> MUMMY_SPAWN_EGG = createKey("mummy_spawn_egg");
        public static final RegistryKey<Item> SLIME_PIRATE_SPAWN_EGG = createKey("slime_pirate_spawn_egg");
        public static final RegistryKey<Item> SLIME_NINJA_PIRATE_SPAWN_EGG = createKey("slime_ninja_pirate_spawn_egg");
        public static final RegistryKey<Item> GHOST_SLIME_PIRATE_SPAWN_EGG = createKey("ghost_slime_pirate_spawn_egg");
        public static final RegistryKey<Item> GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = createKey("ghost_slime_ninja_pirate_spawn_egg");
        public static final RegistryKey<Item> SANDY_SHEEP_SPAWN_EGG = createKey("sandy_sheep_spawn_egg");
        public static final RegistryKey<Item> GLIDING_TURTLE_SPAWN_EGG = createKey("gliding_turtle_spawn_egg");
        public static final RegistryKey<Item> FAT_PHANTOM_SPAWN_EGG = createKey("fat_phantom_spawn_egg");
        public static final RegistryKey<Item> KODAMA_SPAWN_EGG = createKey("kodama_spawn_egg");
        public static final RegistryKey<Item> MUD_GOLEM_SPAWN_EGG = createKey("mud_golem_spawn_egg");
        public static final RegistryKey<Item> MUD_SOLDIER_SPAWN_EGG = createKey("mud_soldier_spawn_egg");
        public static final RegistryKey<Item> MUD_CYCLE_MAGE_SPAWN_EGG = createKey("mud_cycle_mage_spawn_egg");
        public static final RegistryKey<Item> HELL_SPIDER_SPAWN_EGG = createKey("hell_spider_spawn_egg");
        public static final RegistryKey<Item> CRYSTAL_GOLEM_SPAWN_EGG = createKey("crystal_golem_spawn_egg");
        public static final RegistryKey<Item> CRYSTAL_SLIME_SPAWN_EGG = createKey("crystal_slime_spawn_egg");
        public static final RegistryKey<Item> CRYSTAL_SPIDER_SPAWN_EGG = createKey("crystal_spider_spawn_egg");
        public static final RegistryKey<Item> LUNATIC_PRIEST_SPAWN_EGG = createKey("lunatic_priest_spawn_egg");
        public static final RegistryKey<Item> CRYSTAL_CATERPILLAR_SPAWN_EGG = createKey("crystal_caterpillar_spawn_egg");
        public static final RegistryKey<Item> FOREST_CATERPILLAR_SPAWN_EGG = createKey("forest_caterpillar_spawn_egg");
        public static final RegistryKey<Item> TORN_SPIRIT_SPAWN_EGG = createKey("torn_spirit_spawn_egg");
        public static final RegistryKey<Item> CHAINED_GOD_SPAWN_EGG = createKey("chained_god_spawn_egg");
        public static final RegistryKey<Item> ICE_SPIRIT_SPAWN_EGG = createKey("ice_spirit_spawn_egg");
        public static final RegistryKey<Item> FIRE_SPIRIT_SPAWN_EGG = createKey("fire_spirit_spawn_egg");
        public static final RegistryKey<Item> ELECTRO_SPIRIT_SPAWN_EGG = createKey("electro_spirit_spawn_egg");
        public static final RegistryKey<Item> FLYING_JELLYFISH_SPAWN_EGG = createKey("flying_jellyfish_spawn_egg");
        public static final RegistryKey<Item> SHADOW_FLYING_SKULL_SPAWN_EGG = createKey("shadow_flying_skull_spawn_egg");
        public static final RegistryKey<Item> SHADOW_TROLL_SPAWN_EGG = createKey("shadow_troll_spawn_egg");
        public static final RegistryKey<Item> SHADOW_AUTOMATON_SPAWN_EGG = createKey("shadow_automaton_spawn_egg");
        public static final RegistryKey<Item> SHADOW_SPIDER_SPAWN_EGG = createKey("shadow_spider_spawn_egg");
        public static final RegistryKey<Item> LILITH_SPAWN_EGG = createKey("lilith_spawn_egg");

        public static final RegistryKey<Item> AERIAL_HELL_PAINTING = createKey("aerial_hell_painting");

        //build items
        public static final RegistryKey<Item> BLOCK_UPDATER = createKey("block_updater");
        public static final RegistryKey<Item> BLOCK_CRACKER = createKey("block_cracker");
        public static final RegistryKey<Item> STRUCTURE_VOID_PLACER = createKey("structure_void_placer");

        private static RegistryKey<Item> createKey(String name)
        {
            return RegistryKey.of(RegistryKeys.ITEM, AerialHell.id(name));
        }
    }

    public static <T extends Item> T register(String name, T item) {return Registry.register(Registries.ITEM, AerialHell.id(name), item);}

    public static void load() {}
}
