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
import fr.factionbedrock.aerialhell.Registry.TrimMaterials.AerialHellTrimMaterials;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellItems
{
    public static void registerCompostableItems()
    {
        ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_TALL_GRASS.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_BALL.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_DEAD_BUSH.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(BLUE_FLOWER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(BLACK_ROSE.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(BELLFLOWER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY.get().asItem(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(FROZEN_AERIAL_BERRY.get().asItem(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY_SEEDS.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY_SEEDS.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_CONE.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CORTINARIUS_VIOLACEUS.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_FIBER.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(SKY_CACTUS.get().asItem(), 0.4F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_FIBER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS.get().asItem(), 0.8F);
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //portal
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_BLOCK = ITEMS.register(Keys.STELLAR_PORTAL_FRAME_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get(), new Item.Properties().setId(Keys.STELLAR_PORTAL_FRAME_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_ORE = ITEMS.register(Keys.STELLAR_PORTAL_FRAME_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties().setId(Keys.STELLAR_PORTAL_FRAME_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = ITEMS.register(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties().setId(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_BRICK = ITEMS.register(Keys.STELLAR_PORTAL_FRAME_BRICK.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.STELLAR_PORTAL_FRAME_BRICK)));
    public static final DeferredItem<Item> STELLAR_LIGHTER = ITEMS.register(Keys.STELLAR_LIGHTER.identifier().getPath(), () -> new StellarLighterItem(new Item.Properties().setId(Keys.STELLAR_LIGHTER).stacksTo(1).durability(4)));

    //torch
    public static final DeferredItem<Item> CRYSTALLIZED_TORCH = ITEMS.register(Keys.CRYSTALLIZED_TORCH.identifier().getPath(), () -> new StandingAndWallBlockItem(AerialHellBlocks.CRYSTALLIZED_TORCH.get(), AerialHellBlocks.CRYSTALLIZED_WALL_TORCH.get(), Direction.DOWN, new Item.Properties().setId(Keys.CRYSTALLIZED_TORCH).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> FLUORITE_TORCH = ITEMS.register(Keys.FLUORITE_TORCH.identifier().getPath(), () -> new StandingAndWallBlockItem(AerialHellBlocks.FLUORITE_TORCH.get(), AerialHellBlocks.FLUORITE_WALL_TORCH.get(), Direction.DOWN, new Item.Properties().setId(Keys.FLUORITE_TORCH).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_TORCH = ITEMS.register(Keys.VOLUCITE_TORCH.identifier().getPath(), () -> new StandingAndWallBlockItem(AerialHellBlocks.VOLUCITE_TORCH.get(), AerialHellBlocks.VOLUCITE_WALL_TORCH.get(), Direction.DOWN, new Item.Properties().setId(Keys.VOLUCITE_TORCH).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_TORCH = ITEMS.register(Keys.SHADOW_TORCH.identifier().getPath(), () -> new StandingAndWallBlockItem(AerialHellBlocks.SHADOW_TORCH.get(), AerialHellBlocks.SHADOW_WALL_TORCH.get(), Direction.DOWN, new Item.Properties().setId(Keys.SHADOW_TORCH).useBlockDescriptionPrefix()));

    //lanterns
    public static final DeferredItem<Item> CRYSTALLIZED_LANTERN = ITEMS.register(Keys.CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTALLIZED_LANTERN.get(), new Item.Properties().setId(Keys.CRYSTALLIZED_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> FLUORITE_LANTERN = ITEMS.register(Keys.FLUORITE_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.FLUORITE_LANTERN.get(), new Item.Properties().setId(Keys.FLUORITE_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_LANTERN = ITEMS.register(Keys.RUBY_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_LANTERN.get(), new Item.Properties().setId(Keys.RUBY_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_CRYSTALLIZED_LANTERN = ITEMS.register(Keys.RUBY_CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_CRYSTALLIZED_LANTERN.get(), new Item.Properties().setId(Keys.RUBY_CRYSTALLIZED_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_FLUORITE_LANTERN = ITEMS.register(Keys.RUBY_FLUORITE_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_FLUORITE_LANTERN.get(), new Item.Properties().setId(Keys.RUBY_FLUORITE_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_LANTERN = ITEMS.register(Keys.VOLUCITE_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_LANTERN.get(), new Item.Properties().setId(Keys.VOLUCITE_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_CRYSTALLIZED_LANTERN = ITEMS.register(Keys.VOLUCITE_CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_CRYSTALLIZED_LANTERN.get(), new Item.Properties().setId(Keys.VOLUCITE_CRYSTALLIZED_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_FLUORITE_LANTERN = ITEMS.register(Keys.VOLUCITE_FLUORITE_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_FLUORITE_LANTERN.get(), new Item.Properties().setId(Keys.VOLUCITE_FLUORITE_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_LANTERN = ITEMS.register(Keys.LUNATIC_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_LANTERN.get(), new Item.Properties().setId(Keys.LUNATIC_LANTERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_LANTERN = ITEMS.register(Keys.SHADOW_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_LANTERN.get(), new Item.Properties().setId(Keys.SHADOW_LANTERN).useBlockDescriptionPrefix()));

    //chains
    public static final DeferredItem<Item> RUBY_CHAIN = ITEMS.register(Keys.RUBY_CHAIN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_CHAIN.get(), new Item.Properties().setId(Keys.RUBY_CHAIN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_CHAIN = ITEMS.register(Keys.VOLUCITE_CHAIN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_CHAIN.get(), new Item.Properties().setId(Keys.VOLUCITE_CHAIN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_CHAIN = ITEMS.register(Keys.LUNATIC_CHAIN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_CHAIN.get(), new Item.Properties().setId(Keys.LUNATIC_CHAIN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CHAIN = ITEMS.register(Keys.SHADOW_CHAIN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CHAIN.get(), new Item.Properties().setId(Keys.SHADOW_CHAIN).useBlockDescriptionPrefix()));

    //grass & dirt
    public static final DeferredItem<Item> STELLAR_GRASS_BLOCK = ITEMS.register(Keys.STELLAR_GRASS_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS_BLOCK.get(), new Item.Properties().setId(Keys.STELLAR_GRASS_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_STELLAR_GRASS_BLOCK = ITEMS.register(Keys.CHISELED_STELLAR_GRASS_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get(), new Item.Properties().setId(Keys.CHISELED_STELLAR_GRASS_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_DIRT = ITEMS.register(Keys.STELLAR_DIRT.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_DIRT.get(), new Item.Properties().setId(Keys.STELLAR_DIRT).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COARSE_DIRT = ITEMS.register(Keys.STELLAR_COARSE_DIRT.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COARSE_DIRT.get(), new Item.Properties().setId(Keys.STELLAR_COARSE_DIRT).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_FARMLAND = ITEMS.register(Keys.STELLAR_FARMLAND.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_FARMLAND.get(), new Item.Properties().setId(Keys.STELLAR_FARMLAND).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_DIRT_PATH = ITEMS.register(Keys.STELLAR_DIRT_PATH.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_DIRT_PATH.get(), new Item.Properties().setId(Keys.STELLAR_DIRT_PATH).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_PODZOL = ITEMS.register(Keys.STELLAR_PODZOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_PODZOL.get(), new Item.Properties().setId(Keys.STELLAR_PODZOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_CRYSTAL_PODZOL = ITEMS.register(Keys.STELLAR_CRYSTAL_PODZOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_CRYSTAL_PODZOL.get(), new Item.Properties().setId(Keys.STELLAR_CRYSTAL_PODZOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_STELLAR_DIRT = ITEMS.register(Keys.CHISELED_STELLAR_DIRT.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_STELLAR_DIRT.get(), new Item.Properties().setId(Keys.CHISELED_STELLAR_DIRT).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_GRASS_BLOCK = ITEMS.register(Keys.SHADOW_GRASS_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS_BLOCK.get(), new Item.Properties().setId(Keys.SHADOW_GRASS_BLOCK).useBlockDescriptionPrefix()));

    //slippery sand
    public static final DeferredItem<Item> SLIPPERY_SAND = ITEMS.register(Keys.SLIPPERY_SAND.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE = ITEMS.register(Keys.SLIPPERY_SAND_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CUT_SLIPPERY_SAND_STONE = ITEMS.register(Keys.CUT_SLIPPERY_SAND_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CUT_SLIPPERY_SAND_STONE.get(), new Item.Properties().setId(Keys.CUT_SLIPPERY_SAND_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS = ITEMS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS).useBlockDescriptionPrefix()));

    //giant root
    public static final DeferredItem<Item> GIANT_ROOT = ITEMS.register(Keys.GIANT_ROOT.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GIANT_ROOT.get(), new Item.Properties().setId(Keys.GIANT_ROOT).useBlockDescriptionPrefix(), 300));

    //aerial_tree
    public static final DeferredItem<Item> AERIAL_TREE_LOG = ITEMS.register(Keys.AERIAL_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_LOG.get(), new Item.Properties().setId(Keys.AERIAL_TREE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_AERIAL_TREE_LOG = ITEMS.register(Keys.STRIPPED_AERIAL_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_AERIAL_TREE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> AERIAL_TREE_WOOD = ITEMS.register(Keys.AERIAL_TREE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_WOOD.get(), new Item.Properties().setId(Keys.AERIAL_TREE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> STRIPPED_AERIAL_TREE_WOOD = ITEMS.register(Keys.STRIPPED_AERIAL_TREE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_AERIAL_TREE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> AERIAL_TREE_LEAVES = ITEMS.register(Keys.AERIAL_TREE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_LEAVES.get(), new Item.Properties().setId(Keys.AERIAL_TREE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_TREE_PLANKS = ITEMS.register(Keys.AERIAL_TREE_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PLANKS.get(), new Item.Properties().setId(Keys.AERIAL_TREE_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> CHISELED_AERIAL_TREE_PLANKS = ITEMS.register(Keys.CHISELED_AERIAL_TREE_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS.get(), new Item.Properties().setId(Keys.CHISELED_AERIAL_TREE_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> AERIAL_TREE_BOOKSHELF = ITEMS.register(Keys.AERIAL_TREE_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_BOOKSHELF.get(), new Item.Properties().setId(Keys.AERIAL_TREE_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_TREE_SAPLING = ITEMS.register(Keys.AERIAL_TREE_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_SAPLING.get(), new Item.Properties().setId(Keys.AERIAL_TREE_SAPLING).useBlockDescriptionPrefix()));

    //petrified aerial tree
    public static final DeferredItem<Item> PETRIFIED_AERIAL_TREE_LOG = ITEMS.register(Keys.PETRIFIED_AERIAL_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.PETRIFIED_AERIAL_TREE_LOG.get(), new Item.Properties().setId(Keys.PETRIFIED_AERIAL_TREE_LOG).useBlockDescriptionPrefix(), 600));

    //golden beech
    public static final DeferredItem<Item> GOLDEN_BEECH_LOG = ITEMS.register(Keys.GOLDEN_BEECH_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_LOG.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_GOLDEN_BEECH_LOG = ITEMS.register(Keys.STRIPPED_GOLDEN_BEECH_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_GOLDEN_BEECH_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_WOOD = ITEMS.register(Keys.GOLDEN_BEECH_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_WOOD.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> STRIPPED_GOLDEN_BEECH_WOOD = ITEMS.register(Keys.STRIPPED_GOLDEN_BEECH_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_GOLDEN_BEECH_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> GOLDEN_BEECH_PLANKS = ITEMS.register(Keys.GOLDEN_BEECH_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PLANKS.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> CHISELED_GOLDEN_BEECH_PLANKS = ITEMS.register(Keys.CHISELED_GOLDEN_BEECH_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS.get(), new Item.Properties().setId(Keys.CHISELED_GOLDEN_BEECH_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_LEAVES = ITEMS.register(Keys.GOLDEN_BEECH_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_LEAVES.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_BOOKSHELF = ITEMS.register(Keys.GOLDEN_BEECH_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_BOOKSHELF.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_SAPLING = ITEMS.register(Keys.GOLDEN_BEECH_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_SAPLING.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_SAPLING).useBlockDescriptionPrefix()));

    //cropper pine
    public static final DeferredItem<Item> COPPER_PINE_LOG = ITEMS.register(Keys.COPPER_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_LOG.get(), new Item.Properties().setId(Keys.COPPER_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_COPPER_PINE_LOG = ITEMS.register(Keys.STRIPPED_COPPER_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_COPPER_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_WOOD = ITEMS.register(Keys.COPPER_PINE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_WOOD.get(), new Item.Properties().setId(Keys.COPPER_PINE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> STRIPPED_COPPER_PINE_WOOD = ITEMS.register(Keys.STRIPPED_COPPER_PINE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_COPPER_PINE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> COPPER_PINE_PLANKS = ITEMS.register(Keys.COPPER_PINE_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PLANKS.get(), new Item.Properties().setId(Keys.COPPER_PINE_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_LEAVES = ITEMS.register(Keys.COPPER_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_LEAVES.get(), new Item.Properties().setId(Keys.COPPER_PINE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_BOOKSHELF = ITEMS.register(Keys.COPPER_PINE_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_BOOKSHELF.get(), new Item.Properties().setId(Keys.COPPER_PINE_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_SAPLING = ITEMS.register(Keys.COPPER_PINE_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_SAPLING.get(), new Item.Properties().setId(Keys.COPPER_PINE_SAPLING).useBlockDescriptionPrefix()));

    //lapis robinia
    public static final DeferredItem<Item> LAPIS_ROBINIA_LOG = ITEMS.register(Keys.LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_LOG.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_LOG).useBlockDescriptionPrefix(), 400));
    public static final DeferredItem<Item> ENCHANTED_LAPIS_ROBINIA_LOG = ITEMS.register(Keys.ENCHANTED_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG.get(), new Item.Properties().setId(Keys.ENCHANTED_LAPIS_ROBINIA_LOG).useBlockDescriptionPrefix(), 400));
    public static final DeferredItem<Item> STRIPPED_LAPIS_ROBINIA_LOG = ITEMS.register(Keys.STRIPPED_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_LAPIS_ROBINIA_LOG).useBlockDescriptionPrefix(), 400));
    public static final DeferredItem<Item> LAPIS_ROBINIA_WOOD = ITEMS.register(Keys.LAPIS_ROBINIA_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_WOOD.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_WOOD).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_LAPIS_ROBINIA_WOOD = ITEMS.register(Keys.STRIPPED_LAPIS_ROBINIA_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_LAPIS_ROBINIA_WOOD).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_LEAVES = ITEMS.register(Keys.LAPIS_ROBINIA_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_PLANKS = ITEMS.register(Keys.LAPIS_ROBINIA_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PLANKS.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BOOKSHELF = ITEMS.register(Keys.LAPIS_ROBINIA_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SAPLING = ITEMS.register(Keys.LAPIS_ROBINIA_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_SAPLING.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_SAPLING).useBlockDescriptionPrefix()));

    //shadow_pine
    public static final DeferredItem<Item> SHADOW_PINE_LOG = ITEMS.register(Keys.SHADOW_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_LOG.get(), new Item.Properties().setId(Keys.SHADOW_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> EYE_SHADOW_PINE_LOG = ITEMS.register(Keys.EYE_SHADOW_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get(), new Item.Properties().setId(Keys.EYE_SHADOW_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_SHADOW_PINE_LOG = ITEMS.register(Keys.STRIPPED_SHADOW_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_SHADOW_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_WOOD = ITEMS.register(Keys.SHADOW_PINE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_WOOD.get(), new Item.Properties().setId(Keys.SHADOW_PINE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> STRIPPED_SHADOW_PINE_WOOD = ITEMS.register(Keys.STRIPPED_SHADOW_PINE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_SHADOW_PINE_WOOD).useBlockDescriptionPrefix(), 210));
    public static final DeferredItem<Item> SHADOW_PINE_LEAVES = ITEMS.register(Keys.SHADOW_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_PINE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> PURPLE_SHADOW_PINE_LEAVES = ITEMS.register(Keys.PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get(), new Item.Properties().setId(Keys.PURPLE_SHADOW_PINE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_PLANKS = ITEMS.register(Keys.SHADOW_PINE_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PLANKS.get(), new Item.Properties().setId(Keys.SHADOW_PINE_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_BOOKSHELF = ITEMS.register(Keys.SHADOW_PINE_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_BOOKSHELF.get(), new Item.Properties().setId(Keys.SHADOW_PINE_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_SAPLING = ITEMS.register(Keys.SHADOW_PINE_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_SAPLING.get(), new Item.Properties().setId(Keys.SHADOW_PINE_SAPLING).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> PURPLE_SHADOW_PINE_SAPLING = ITEMS.register(Keys.PURPLE_SHADOW_PINE_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_SAPLING.get(), new Item.Properties().setId(Keys.PURPLE_SHADOW_PINE_SAPLING).useBlockDescriptionPrefix()));

    //stellar jungle tree
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_LOG = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_LOG).useBlockDescriptionPrefix(), 400));
    public static final DeferredItem<Item> STRIPPED_STELLAR_JUNGLE_TREE_LOG = ITEMS.register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties().setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG).useBlockDescriptionPrefix(), 400));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_WOOD = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_WOOD).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = ITEMS.register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties().setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_LEAVES = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_PLANKS = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PLANKS.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_PLANKS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BOOKSHELF = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BOOKSHELF.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SAPLING = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_SAPLING.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_SAPLING).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DEAD_STELLAR_JUNGLE_TREE_LOG = ITEMS.register(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.DEAD_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties().setId(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG).useBlockDescriptionPrefix(), 300));

    //shroom
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_STEM = ITEMS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = ITEMS.register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties().setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = ITEMS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = ITEMS.register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties().setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = ITEMS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get(), new Item.Properties().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = ITEMS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get(), new Item.Properties().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CORTINARIUS_VIOLACEUS = ITEMS.register(Keys.CORTINARIUS_VIOLACEUS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CORTINARIUS_VIOLACEUS.get(), new Item.Properties().setId(Keys.CORTINARIUS_VIOLACEUS).useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_STEM = ITEMS.register(Keys.GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties().setId(Keys.GIANT_VERDIGRIS_AGARIC_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = ITEMS.register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties().setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_BARK_STEM = ITEMS.register(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties().setId(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM).useBlockDescriptionPrefix(), 75));
    public static final DeferredItem<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = ITEMS.register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties().setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM).useBlockDescriptionPrefix(), 75));
    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = ITEMS.register(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.get(), new Item.Properties().setId(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VERDIGRIS_AGARIC = ITEMS.register(Keys.VERDIGRIS_AGARIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VERDIGRIS_AGARIC.get(), new Item.Properties().setId(Keys.VERDIGRIS_AGARIC).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GLOWING_BOLETUS = ITEMS.register(Keys.GLOWING_BOLETUS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLOWING_BOLETUS.get(), new Item.Properties().setId(Keys.GLOWING_BOLETUS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TALL_GLOWING_BOLETUS = ITEMS.register(Keys.TALL_GLOWING_BOLETUS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TALL_GLOWING_BOLETUS.get(), new Item.Properties().setId(Keys.TALL_GLOWING_BOLETUS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLUE_MEANIE_CLUSTER = ITEMS.register(Keys.BLUE_MEANIE_CLUSTER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUE_MEANIE_CLUSTER.get(), new Item.Properties().setId(Keys.BLUE_MEANIE_CLUSTER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GIANT_ROOT_SHROOM = ITEMS.register(Keys.GIANT_ROOT_SHROOM.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GIANT_ROOT_SHROOM.get(), new Item.Properties().setId(Keys.GIANT_ROOT_SHROOM).useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> GIANT_GANODERMA_APPLANATUM_BLOCK = ITEMS.register(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK.get(), new Item.Properties().setId(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK).useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> GRAY_SHROOM_PLANKS = ITEMS.register(Keys.GRAY_SHROOM_PLANKS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PLANKS.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_PLANKS).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GRAY_SHROOM_BOOKSHELF = ITEMS.register(Keys.GRAY_SHROOM_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_BOOKSHELF.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_BOOKSHELF).useBlockDescriptionPrefix()));

    //shadow corrupted / uncorrupted variants
    public static final DeferredItem<Item> SHADOW_AERIAL_TREE_LOG = ITEMS.register(Keys.SHADOW_AERIAL_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG.get(), new Item.Properties().setId(Keys.SHADOW_AERIAL_TREE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_GOLDEN_BEECH_LOG = ITEMS.register(Keys.SHADOW_GOLDEN_BEECH_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG.get(), new Item.Properties().setId(Keys.SHADOW_GOLDEN_BEECH_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_COPPER_PINE_LOG = ITEMS.register(Keys.SHADOW_COPPER_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LOG.get(), new Item.Properties().setId(Keys.SHADOW_COPPER_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_LAPIS_ROBINIA_LOG = ITEMS.register(Keys.SHADOW_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), new Item.Properties().setId(Keys.SHADOW_LAPIS_ROBINIA_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_STELLAR_JUNGLE_TREE_LOG = ITEMS.register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties().setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> HOLLOW_SHADOW_PINE_LOG = ITEMS.register(Keys.HOLLOW_SHADOW_PINE_LOG.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), new Item.Properties().setId(Keys.HOLLOW_SHADOW_PINE_LOG).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_AERIAL_TREE_LEAVES = ITEMS.register(Keys.SHADOW_AERIAL_TREE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_AERIAL_TREE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_GOLDEN_BEECH_LEAVES = ITEMS.register(Keys.SHADOW_GOLDEN_BEECH_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_GOLDEN_BEECH_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_COPPER_PINE_LEAVES = ITEMS.register(Keys.SHADOW_COPPER_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_COPPER_PINE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_LAPIS_ROBINIA_LEAVES = ITEMS.register(Keys.SHADOW_LAPIS_ROBINIA_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_LAPIS_ROBINIA_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = ITEMS.register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(), new Item.Properties().setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> HOLLOW_SHADOW_PINE_LEAVES = ITEMS.register(Keys.HOLLOW_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES.get(), new Item.Properties().setId(Keys.HOLLOW_SHADOW_PINE_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = ITEMS.register(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get(), new Item.Properties().setId(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES).useBlockDescriptionPrefix()));

    //ladder
    public static final DeferredItem<Item> SKY_LADDER = ITEMS.register(Keys.SKY_LADDER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_LADDER.get(), new Item.Properties().setId(Keys.SKY_LADDER).useBlockDescriptionPrefix()));

    //natural blocks and items
    public static final DeferredItem<Item> STELLAR_STONE = ITEMS.register(Keys.STELLAR_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE.get(), new Item.Properties().setId(Keys.STELLAR_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE = ITEMS.register(Keys.STELLAR_COBBLESTONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE = ITEMS.register(Keys.MOSSY_STELLAR_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE = ITEMS.register(Keys.MOSSY_STELLAR_COBBLESTONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_COBBLESTONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_CLAY = ITEMS.register(Keys.STELLAR_CLAY.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_CLAY.get(), new Item.Properties().setId(Keys.STELLAR_CLAY).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS = ITEMS.register(Keys.STELLAR_STONE_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GLAUCOPHANITE = ITEMS.register(Keys.GLAUCOPHANITE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE.get(), new Item.Properties().setId(Keys.GLAUCOPHANITE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE = ITEMS.register(Keys.POLISHED_GLAUCOPHANITE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE.get(), new Item.Properties().setId(Keys.POLISHED_GLAUCOPHANITE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_STONE = ITEMS.register(Keys.SHADOW_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_STONE.get(), new Item.Properties().setId(Keys.SHADOW_STONE).useBlockDescriptionPrefix()));

    //crystal
    public static final DeferredItem<Item> CRYSTAL_BLOCK = ITEMS.register(Keys.CRYSTAL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTAL_BLOCK.get(), new Item.Properties().setId(Keys.CRYSTAL_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS = ITEMS.register(Keys.CRYSTAL_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS.get(), new Item.Properties().setId(Keys.CRYSTAL_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_SLAB = ITEMS.register(Keys.CRYSTAL_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.CRYSTAL_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_STAIRS = ITEMS.register(Keys.CRYSTAL_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.CRYSTAL_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_WALL = ITEMS.register(Keys.CRYSTAL_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_WALL.get(), new Item.Properties().setId(Keys.CRYSTAL_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_CRYSTAL_BLOCK = ITEMS.register(Keys.STELLAR_STONE_CRYSTAL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.get(), new Item.Properties().setId(Keys.STELLAR_STONE_CRYSTAL_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CRYSTAL_BLOCK = ITEMS.register(Keys.SHADOW_CRYSTAL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get(), new Item.Properties().setId(Keys.SHADOW_CRYSTAL_BLOCK).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> CRYSTALLIZED_LEAVES = ITEMS.register(Keys.CRYSTALLIZED_LEAVES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRYSTALLIZED_LEAVES.get(), new Item.Properties().setId(Keys.CRYSTALLIZED_LEAVES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRYSTAL = ITEMS.register(Keys.CRYSTAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.CRYSTAL)));
    public static final DeferredItem<Item> STELLAR_STONE_CRYSTAL = ITEMS.register(Keys.STELLAR_STONE_CRYSTAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.STELLAR_STONE_CRYSTAL)));
    public static final DeferredItem<Item> SHADOW_CRYSTAL = ITEMS.register(Keys.SHADOW_CRYSTAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.SHADOW_CRYSTAL).rarity(AerialHellRarities.CORRUPTED.getValue())));

    //glass and glass pane
    public static final DeferredItem<Item> SLIPPERY_SAND_GLASS = ITEMS.register(Keys.SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_GLASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RED_SLIPPERY_SAND_GLASS = ITEMS.register(Keys.RED_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.get(), new Item.Properties().setId(Keys.RED_SLIPPERY_SAND_GLASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLACK_SLIPPERY_SAND_GLASS = ITEMS.register(Keys.BLACK_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS.get(), new Item.Properties().setId(Keys.BLACK_SLIPPERY_SAND_GLASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLUE_SLIPPERY_SAND_GLASS = ITEMS.register(Keys.BLUE_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS.get(), new Item.Properties().setId(Keys.BLUE_SLIPPERY_SAND_GLASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GREEN_SLIPPERY_SAND_GLASS = ITEMS.register(Keys.GREEN_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS.get(), new Item.Properties().setId(Keys.GREEN_SLIPPERY_SAND_GLASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_GLASS_PANE = ITEMS.register(Keys.SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_GLASS_PANE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RED_SLIPPERY_SAND_GLASS_PANE = ITEMS.register(Keys.RED_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().setId(Keys.RED_SLIPPERY_SAND_GLASS_PANE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLACK_SLIPPERY_SAND_GLASS_PANE = ITEMS.register(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().setId(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLUE_SLIPPERY_SAND_GLASS_PANE = ITEMS.register(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().setId(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GREEN_SLIPPERY_SAND_GLASS_PANE = ITEMS.register(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().setId(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE).useBlockDescriptionPrefix()));

    //ghost boat
    public static final DeferredItem<Item> GHOST_BOAT_PLANKS = ITEMS.register(Keys.GHOST_BOAT_PLANKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_PLANKS.get(), new Item.Properties().setId(Keys.GHOST_BOAT_PLANKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_LOG = ITEMS.register(Keys.GHOST_BOAT_LOG.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_LOG.get(), new Item.Properties().setId(Keys.GHOST_BOAT_LOG).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_WOOD = ITEMS.register(Keys.GHOST_BOAT_WOOD.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOD.get(), new Item.Properties().setId(Keys.GHOST_BOAT_WOOD).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_SLAB = ITEMS.register(Keys.GHOST_BOAT_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_SLAB.get(), new Item.Properties().setId(Keys.GHOST_BOAT_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_STAIRS = ITEMS.register(Keys.GHOST_BOAT_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_STAIRS.get(), new Item.Properties().setId(Keys.GHOST_BOAT_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_FENCE = ITEMS.register(Keys.GHOST_BOAT_FENCE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_FENCE.get(), new Item.Properties().setId(Keys.GHOST_BOAT_FENCE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_GATE = ITEMS.register(Keys.GHOST_BOAT_GATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_GATE.get(), new Item.Properties().setId(Keys.GHOST_BOAT_GATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_DOOR = ITEMS.register(Keys.GHOST_BOAT_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_DOOR.get(), new Item.Properties().setId(Keys.GHOST_BOAT_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_TRAPDOOR = ITEMS.register(Keys.GHOST_BOAT_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_TRAPDOOR.get(), new Item.Properties().setId(Keys.GHOST_BOAT_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_CHEST = ITEMS.register(Keys.GHOST_BOAT_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_CHEST.get(), new Item.Properties().setId(Keys.GHOST_BOAT_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_WOOL = ITEMS.register(Keys.GHOST_BOAT_WOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOL.get(), new Item.Properties().setId(Keys.GHOST_BOAT_WOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_STELLAR_COBBLESTONE = ITEMS.register(Keys.GHOST_STELLAR_COBBLESTONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE.get(), new Item.Properties().setId(Keys.GHOST_STELLAR_COBBLESTONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_RUBY_BLOCK = ITEMS.register(Keys.GHOST_RUBY_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_RUBY_BLOCK.get(), new Item.Properties().setId(Keys.GHOST_RUBY_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_FLUORITE_BLOCK = ITEMS.register(Keys.GHOST_FLUORITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_FLUORITE_BLOCK.get(), new Item.Properties().setId(Keys.GHOST_FLUORITE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_AZURITE_BLOCK = ITEMS.register(Keys.GHOST_AZURITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_AZURITE_BLOCK.get(), new Item.Properties().setId(Keys.GHOST_AZURITE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_GOLD_BLOCK = ITEMS.register(Keys.GHOST_GOLD_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_GOLD_BLOCK.get(), new Item.Properties().setId(Keys.GHOST_GOLD_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_BARREL = ITEMS.register(Keys.GHOST_BOAT_BARREL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_BARREL.get(), new Item.Properties().setId(Keys.GHOST_BOAT_BARREL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_CRAFTING_TABLE = ITEMS.register(Keys.GHOST_BOAT_CRAFTING_TABLE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.GHOST_BOAT_CRAFTING_TABLE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_BOAT_VINE_ROPE_SPOOL = ITEMS.register(Keys.GHOST_BOAT_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.GHOST_BOAT_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_LANTERN = ITEMS.register(Keys.GHOST_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_LANTERN.get(), new Item.Properties().setId(Keys.GHOST_LANTERN).useBlockDescriptionPrefix()));

    //reactors
    public static final DeferredItem<Item> WEAK_LIGHT_REACTOR = ITEMS.register(Keys.WEAK_LIGHT_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.WEAK_LIGHT_REACTOR.get(), new Item.Properties().setId(Keys.WEAK_LIGHT_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> HIGH_POWER_LIGHT_REACTOR = ITEMS.register(Keys.HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR.get(), new Item.Properties().setId(Keys.HIGH_POWER_LIGHT_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> WEAK_SHADOW_REACTOR = ITEMS.register(Keys.WEAK_SHADOW_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.WEAK_SHADOW_REACTOR.get(), new Item.Properties().setId(Keys.WEAK_SHADOW_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> HIGH_POWER_SHADOW_REACTOR = ITEMS.register(Keys.HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR.get(), new Item.Properties().setId(Keys.HIGH_POWER_SHADOW_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));

    public static final DeferredItem<Item> BROKEN_WEAK_LIGHT_REACTOR = ITEMS.register(Keys.BROKEN_WEAK_LIGHT_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR.get(), new Item.Properties().setId(Keys.BROKEN_WEAK_LIGHT_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> BROKEN_HIGH_POWER_LIGHT_REACTOR = ITEMS.register(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR.get(), new Item.Properties().setId(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> BROKEN_WEAK_SHADOW_REACTOR = ITEMS.register(Keys.BROKEN_WEAK_SHADOW_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR.get(), new Item.Properties().setId(Keys.BROKEN_WEAK_SHADOW_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> BROKEN_HIGH_POWER_SHADOW_REACTOR = ITEMS.register(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR.get(), new Item.Properties().setId(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));

    //solid_ethers
    public static final DeferredItem<Item> WHITE_SOLID_ETHER = ITEMS.register(Keys.WHITE_SOLID_ETHER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.WHITE_SOLID_ETHER.get(), new Item.Properties().setId(Keys.WHITE_SOLID_ETHER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLUE_SOLID_ETHER = ITEMS.register(Keys.BLUE_SOLID_ETHER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUE_SOLID_ETHER.get(), new Item.Properties().setId(Keys.BLUE_SOLID_ETHER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_SOLID_ETHER = ITEMS.register(Keys.GOLDEN_SOLID_ETHER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_SOLID_ETHER.get(), new Item.Properties().setId(Keys.GOLDEN_SOLID_ETHER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GREEN_SOLID_ETHER = ITEMS.register(Keys.GREEN_SOLID_ETHER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GREEN_SOLID_ETHER.get(), new Item.Properties().setId(Keys.GREEN_SOLID_ETHER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> PURPLE_SOLID_ETHER = ITEMS.register(Keys.PURPLE_SOLID_ETHER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.PURPLE_SOLID_ETHER.get(), new Item.Properties().setId(Keys.PURPLE_SOLID_ETHER).useBlockDescriptionPrefix()));

    //dungeons blocks
    public static final DeferredItem<Item> MUD_BRICKS = ITEMS.register(Keys.MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BRICKS.get(), new Item.Properties().setId(Keys.MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS = ITEMS.register(Keys.CRACKED_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS = ITEMS.register(Keys.MOSSY_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS.get(), new Item.Properties().setId(Keys.MOSSY_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_MUD_BRICKS = ITEMS.register(Keys.CHISELED_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_MUD_BRICKS.get(), new Item.Properties().setId(Keys.CHISELED_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LIGHT_MUD_BRICKS = ITEMS.register(Keys.LIGHT_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LIGHT_MUD_BRICKS.get(), new Item.Properties().setId(Keys.LIGHT_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LIGHT_MUD_BRICKS = ITEMS.register(Keys.CRACKED_LIGHT_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_LIGHT_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_STONE = ITEMS.register(Keys.LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE.get(), new Item.Properties().setId(Keys.LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE = ITEMS.register(Keys.DARK_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.DARK_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> ROOF_LUNATIC_STONE = ITEMS.register(Keys.ROOF_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.ROOF_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.ROOF_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE = ITEMS.register(Keys.CRACKED_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.CRACKED_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_LUNATIC_STONE = ITEMS.register(Keys.CHISELED_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.CHISELED_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LIGHT_LUNATIC_STONE = ITEMS.register(Keys.LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LIGHT_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.LIGHT_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> ROOF_LIGHT_LUNATIC_STONE = ITEMS.register(Keys.ROOF_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.ROOF_LIGHT_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.ROOF_LIGHT_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LIGHT_LUNATIC_STONE = ITEMS.register(Keys.CRACKED_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.CRACKED_LIGHT_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BONE_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.BONE_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BONE_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.BONE_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKULL_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.SKULL_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKULL_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.SKULL_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LIGHT_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = ITEMS.register(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.CHISELED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.CHISELED_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.LIGHT_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_PILLAR = ITEMS.register(Keys.LUNATIC_PILLAR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_PILLAR.get(), new Item.Properties().setId(Keys.LUNATIC_PILLAR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_PILLAR_TOP = ITEMS.register(Keys.LUNATIC_PILLAR_TOP.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_PILLAR_TOP.get(), new Item.Properties().setId(Keys.LUNATIC_PILLAR_TOP).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_STONE = ITEMS.register(Keys.VOLUCITE_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE.get(), new Item.Properties().setId(Keys.VOLUCITE_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE = ITEMS.register(Keys.CRACKED_VOLUCITE_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE.get(), new Item.Properties().setId(Keys.CRACKED_VOLUCITE_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_VOLUCITE_STONE = ITEMS.register(Keys.CHISELED_VOLUCITE_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_VOLUCITE_STONE.get(), new Item.Properties().setId(Keys.CHISELED_VOLUCITE_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LIGHT_VOLUCITE_STONE = ITEMS.register(Keys.LIGHT_VOLUCITE_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LIGHT_VOLUCITE_STONE.get(), new Item.Properties().setId(Keys.LIGHT_VOLUCITE_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LIGHT_VOLUCITE_STONE = ITEMS.register(Keys.CRACKED_LIGHT_VOLUCITE_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE.get(), new Item.Properties().setId(Keys.CRACKED_LIGHT_VOLUCITE_STONE).useBlockDescriptionPrefix()));

    //dungeon cores
    public static final DeferredItem<Item> MUD_DUNGEON_CORE = ITEMS.register(Keys.MUD_DUNGEON_CORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_DUNGEON_CORE.get(), new Item.Properties().setId(Keys.MUD_DUNGEON_CORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_DUNGEON_CORE = ITEMS.register(Keys.LUNATIC_DUNGEON_CORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_DUNGEON_CORE.get(), new Item.Properties().setId(Keys.LUNATIC_DUNGEON_CORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_DUNGEON_CORE = ITEMS.register(Keys.SHADOW_CATACOMBS_DUNGEON_CORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_DUNGEON_CORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_DUNGEON_CORE = ITEMS.register(Keys.GOLDEN_NETHER_DUNGEON_CORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_DUNGEON_CORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_DUNGEON_CORE = ITEMS.register(Keys.VOLUCITE_DUNGEON_CORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_DUNGEON_CORE.get(), new Item.Properties().setId(Keys.VOLUCITE_DUNGEON_CORE).useBlockDescriptionPrefix()));

    //dungeons slabs, stairs & walls
    public static final DeferredItem<Item> MUD_BRICKS_SLAB = ITEMS.register(Keys.MUD_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.MUD_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MUD_BRICKS_STAIRS = ITEMS.register(Keys.MUD_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.MUD_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MUD_BRICKS_WALL = ITEMS.register(Keys.MUD_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_WALL.get(), new Item.Properties().setId(Keys.MUD_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_SLAB = ITEMS.register(Keys.CRACKED_MUD_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_MUD_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_STAIRS = ITEMS.register(Keys.CRACKED_MUD_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_MUD_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_WALL = ITEMS.register(Keys.CRACKED_MUD_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_WALL.get(), new Item.Properties().setId(Keys.CRACKED_MUD_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_SLAB = ITEMS.register(Keys.MOSSY_MUD_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.MOSSY_MUD_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_STAIRS = ITEMS.register(Keys.MOSSY_MUD_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.MOSSY_MUD_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_WALL = ITEMS.register(Keys.MOSSY_MUD_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL.get(), new Item.Properties().setId(Keys.MOSSY_MUD_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_STONE_SLAB = ITEMS.register(Keys.VOLUCITE_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_SLAB.get(), new Item.Properties().setId(Keys.VOLUCITE_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_STONE_STAIRS = ITEMS.register(Keys.VOLUCITE_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_STAIRS.get(), new Item.Properties().setId(Keys.VOLUCITE_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_STONE_WALL = ITEMS.register(Keys.VOLUCITE_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_WALL.get(), new Item.Properties().setId(Keys.VOLUCITE_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_SLAB = ITEMS.register(Keys.CRACKED_VOLUCITE_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_VOLUCITE_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_STAIRS = ITEMS.register(Keys.CRACKED_VOLUCITE_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_VOLUCITE_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_WALL = ITEMS.register(Keys.CRACKED_VOLUCITE_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL.get(), new Item.Properties().setId(Keys.CRACKED_VOLUCITE_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_STONE_SLAB = ITEMS.register(Keys.LUNATIC_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_SLAB.get(), new Item.Properties().setId(Keys.LUNATIC_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_STONE_STAIRS = ITEMS.register(Keys.LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_STAIRS.get(), new Item.Properties().setId(Keys.LUNATIC_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_STONE_WALL = ITEMS.register(Keys.LUNATIC_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_WALL.get(), new Item.Properties().setId(Keys.LUNATIC_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_SLAB = ITEMS.register(Keys.DARK_LUNATIC_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_SLAB.get(), new Item.Properties().setId(Keys.DARK_LUNATIC_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_STAIRS = ITEMS.register(Keys.DARK_LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_STAIRS.get(), new Item.Properties().setId(Keys.DARK_LUNATIC_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_WALL = ITEMS.register(Keys.DARK_LUNATIC_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_WALL.get(), new Item.Properties().setId(Keys.DARK_LUNATIC_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_SLAB = ITEMS.register(Keys.CRACKED_LUNATIC_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_LUNATIC_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_STAIRS = ITEMS.register(Keys.CRACKED_LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_LUNATIC_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_WALL = ITEMS.register(Keys.CRACKED_LUNATIC_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL.get(), new Item.Properties().setId(Keys.CRACKED_LUNATIC_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register(Keys.SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register(Keys.SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties().setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties().setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_BARS = ITEMS.register(Keys.SHADOW_BARS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_BARS.get(), new Item.Properties().setId(Keys.SHADOW_BARS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_SLAB = ITEMS.register(Keys.GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_STAIRS = ITEMS.register(Keys.GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_WALL = ITEMS.register(Keys.GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = ITEMS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = ITEMS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL = ITEMS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL).useBlockDescriptionPrefix()));

    //smoky quartz
    public static final DeferredItem<Item> SMOKY_QUARTZ = ITEMS.register(Keys.SMOKY_QUARTZ.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.SMOKY_QUARTZ)));
    public static final DeferredItem<Item> SMOKY_QUARTZ_BLOCK = ITEMS.register(Keys.SMOKY_QUARTZ_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BLOCK.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ = ITEMS.register(Keys.SMOOTH_SMOKY_QUARTZ.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ.get(), new Item.Properties().setId(Keys.SMOOTH_SMOKY_QUARTZ).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHISELED_SMOKY_QUARTZ_BLOCK = ITEMS.register(Keys.CHISELED_SMOKY_QUARTZ_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.get(), new Item.Properties().setId(Keys.CHISELED_SMOKY_QUARTZ_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_BRICKS = ITEMS.register(Keys.SMOKY_QUARTZ_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BRICKS.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_PILLAR = ITEMS.register(Keys.SMOKY_QUARTZ_PILLAR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_PILLAR.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_PILLAR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_SLAB = ITEMS.register(Keys.SMOKY_QUARTZ_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_SLAB.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ_SLAB = ITEMS.register(Keys.SMOOTH_SMOKY_QUARTZ_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB.get(), new Item.Properties().setId(Keys.SMOOTH_SMOKY_QUARTZ_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_STAIRS = ITEMS.register(Keys.SMOKY_QUARTZ_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_STAIRS.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ_STAIRS = ITEMS.register(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS.get(), new Item.Properties().setId(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS).useBlockDescriptionPrefix()));

    //dungeon trapped blocks
    public static final DeferredItem<Item> TRAPPED_MUD_BRICKS = ITEMS.register(Keys.TRAPPED_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_MUD_BRICKS.get(), new Item.Properties().setId(Keys.TRAPPED_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_MUD_BRICKS = ITEMS.register(Keys.TRAPPED_LIGHT_MUD_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS.get(), new Item.Properties().setId(Keys.TRAPPED_LIGHT_MUD_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TRAPPED_LUNATIC_STONE = ITEMS.register(Keys.TRAPPED_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.TRAPPED_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_LUNATIC_STONE = ITEMS.register(Keys.TRAPPED_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE.get(), new Item.Properties().setId(Keys.TRAPPED_LIGHT_LUNATIC_STONE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TRAPPED_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.TRAPPED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.TRAPPED_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().setId(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS).useBlockDescriptionPrefix()));

    //dungeon other blocks, loots
    public static final DeferredItem<Item> MUD_BONE_BLOCK = ITEMS.register(Keys.MUD_BONE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BONE_BLOCK.get(), new Item.Properties().setId(Keys.MUD_BONE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MUD_BONE_PILE_BLOCK = ITEMS.register(Keys.MUD_BONE_PILE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BONE_PILE_BLOCK.get(), new Item.Properties().setId(Keys.MUD_BONE_PILE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MUD_BONE = ITEMS.register(Keys.MUD_BONE.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.MUD_BONE)));
    public static final DeferredItem<Item> THORNY_COBWEB = ITEMS.register(Keys.THORNY_COBWEB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.THORNY_COBWEB.get(), new Item.Properties().setId(Keys.THORNY_COBWEB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK = ITEMS.register(Keys.AERIAL_NETHERRACK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK.get(), new Item.Properties().setId(Keys.AERIAL_NETHERRACK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_SLAB = ITEMS.register(Keys.AERIAL_NETHERRACK_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_SLAB.get(), new Item.Properties().setId(Keys.AERIAL_NETHERRACK_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_STAIRS = ITEMS.register(Keys.AERIAL_NETHERRACK_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_STAIRS.get(), new Item.Properties().setId(Keys.AERIAL_NETHERRACK_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_WALL = ITEMS.register(Keys.AERIAL_NETHERRACK_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_WALL.get(), new Item.Properties().setId(Keys.AERIAL_NETHERRACK_WALL).useBlockDescriptionPrefix()));

    //dungeon bookshelfs
    public static final DeferredItem<Item> MUD_BOOKSHELF = ITEMS.register(Keys.MUD_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_BOOKSHELF.get(), new Item.Properties().setId(Keys.MUD_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_BOOKSHELF = ITEMS.register(Keys.LUNATIC_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_BOOKSHELF.get(), new Item.Properties().setId(Keys.LUNATIC_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BOOKSHELF = ITEMS.register(Keys.GOLDEN_NETHER_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BOOKSHELF.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BOOKSHELF = ITEMS.register(Keys.SHADOW_CATACOMBS_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BOOKSHELF.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_BOOKSHELF = ITEMS.register(Keys.VOLUCITE_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_BOOKSHELF.get(), new Item.Properties().setId(Keys.VOLUCITE_BOOKSHELF).useBlockDescriptionPrefix()));

    //glyph blocks
    public static final DeferredItem<Item> MUD_GLYPH_BLOCK = ITEMS.register(Keys.MUD_GLYPH_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_GLYPH_BLOCK.get(), new Item.Properties().setId(Keys.MUD_GLYPH_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_GLYPH_BLOCK = ITEMS.register(Keys.LUNATIC_GLYPH_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_GLYPH_BLOCK.get(), new Item.Properties().setId(Keys.LUNATIC_GLYPH_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = ITEMS.register(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_GLYPH_BLOCK = ITEMS.register(Keys.VOLUCITE_GLYPH_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_GLYPH_BLOCK.get(), new Item.Properties().setId(Keys.VOLUCITE_GLYPH_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_GLYPH_BLOCK = ITEMS.register(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK).useBlockDescriptionPrefix()));

    //trophies
    public static final DeferredItem<Item> MUD_CYCLE_MAGE_TROPHY = ITEMS.register(Keys.MUD_CYCLE_MAGE_TROPHY.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_CYCLE_MAGE_TROPHY.get(), new Item.Properties().setId(Keys.MUD_CYCLE_MAGE_TROPHY).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNAR_PRIEST_TROPHY = ITEMS.register(Keys.LUNAR_PRIEST_TROPHY.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNAR_PRIEST_TROPHY.get(), new Item.Properties().setId(Keys.LUNAR_PRIEST_TROPHY).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LILITH_TROPHY = ITEMS.register(Keys.LILITH_TROPHY.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LILITH_TROPHY.get(), new Item.Properties().setId(Keys.LILITH_TROPHY).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CHAINED_GOD_TROPHY = ITEMS.register(Keys.CHAINED_GOD_TROPHY.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CHAINED_GOD_TROPHY.get(), new Item.Properties().setId(Keys.CHAINED_GOD_TROPHY).useBlockDescriptionPrefix()));

    //ores
    public static final DeferredItem<Item> IRON_STELLAR_ORE = ITEMS.register(Keys.IRON_STELLAR_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.IRON_STELLAR_ORE.get(), new Item.Properties().setId(Keys.IRON_STELLAR_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLD_STELLAR_ORE = ITEMS.register(Keys.GOLD_STELLAR_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLD_STELLAR_ORE.get(), new Item.Properties().setId(Keys.GOLD_STELLAR_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DIAMOND_STELLAR_ORE = ITEMS.register(Keys.DIAMOND_STELLAR_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DIAMOND_STELLAR_ORE.get(), new Item.Properties().setId(Keys.DIAMOND_STELLAR_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> FLUORITE_ORE = ITEMS.register(Keys.FLUORITE_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.FLUORITE_ORE.get(), new Item.Properties().setId(Keys.FLUORITE_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAGMATIC_GEL_ORE = ITEMS.register(Keys.MAGMATIC_GEL_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_ORE.get(), new Item.Properties().setId(Keys.MAGMATIC_GEL_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_ORE = ITEMS.register(Keys.RUBY_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_ORE.get(), new Item.Properties().setId(Keys.RUBY_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AZURITE_ORE = ITEMS.register(Keys.AZURITE_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AZURITE_ORE.get(), new Item.Properties().setId(Keys.AZURITE_ORE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_ORE = ITEMS.register(Keys.VOLUCITE_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_ORE.get(), new Item.Properties().setId(Keys.VOLUCITE_ORE).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> OBSIDIAN_ORE = ITEMS.register(Keys.OBSIDIAN_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.OBSIDIAN_ORE.get(), new Item.Properties().setId(Keys.OBSIDIAN_ORE).useBlockDescriptionPrefix().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SMOKY_QUARTZ_ORE = ITEMS.register(Keys.SMOKY_QUARTZ_ORE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_ORE.get(), new Item.Properties().setId(Keys.SMOKY_QUARTZ_ORE).useBlockDescriptionPrefix().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> RAW_RUBY_BLOCK = ITEMS.register(Keys.RAW_RUBY_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RAW_RUBY_BLOCK.get(), new Item.Properties().setId(Keys.RAW_RUBY_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RAW_AZURITE_BLOCK = ITEMS.register(Keys.RAW_AZURITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RAW_AZURITE_BLOCK.get(), new Item.Properties().setId(Keys.RAW_AZURITE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RAW_VOLUCITE_BLOCK = ITEMS.register(Keys.RAW_VOLUCITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RAW_VOLUCITE_BLOCK.get(), new Item.Properties().setId(Keys.RAW_VOLUCITE_BLOCK).useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> RAW_RUBY = ITEMS.register(Keys.RAW_RUBY.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RAW_RUBY)));
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.register(Keys.RAW_AZURITE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RAW_AZURITE)));
    public static final DeferredItem<Item> RAW_VOLUCITE = ITEMS.register(Keys.RAW_VOLUCITE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RAW_VOLUCITE)));

    public static final DeferredItem<Item> FLUORITE_BLOCK = ITEMS.register(Keys.FLUORITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.FLUORITE_BLOCK.get(), new Item.Properties().setId(Keys.FLUORITE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAGMATIC_GEL_BLOCK = ITEMS.register(Keys.MAGMATIC_GEL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_BLOCK.get(), new Item.Properties().setId(Keys.MAGMATIC_GEL_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_BLOCK = ITEMS.register(Keys.RUBY_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_BLOCK.get(), new Item.Properties().setId(Keys.RUBY_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AZURITE_BLOCK = ITEMS.register(Keys.AZURITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AZURITE_BLOCK.get(), new Item.Properties().setId(Keys.AZURITE_BLOCK).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_BLOCK = ITEMS.register(Keys.VOLUCITE_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_BLOCK.get(), new Item.Properties().setId(Keys.VOLUCITE_BLOCK).useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> FLUORITE = ITEMS.register(Keys.FLUORITE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FLUORITE).trimMaterial(AerialHellTrimMaterials.Keys.FLUORITE)));
    public static final DeferredItem<Item> MAGMATIC_GEL = ITEMS.register(Keys.MAGMATIC_GEL.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL).trimMaterial(AerialHellTrimMaterials.Keys.MAGMATIC_GEL)));
    public static final DeferredItem<Item> RUBY = ITEMS.register(Keys.RUBY.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY).trimMaterial(AerialHellTrimMaterials.Keys.RUBY)));
    public static final DeferredItem<Item> AZURITE_CRYSTAL = ITEMS.register(Keys.AZURITE_CRYSTAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_CRYSTAL)));
    public static final DeferredItem<Item> VOLUCITE_VIBRANT = ITEMS.register(Keys.VOLUCITE_VIBRANT.identifier().getPath(), () -> new VoluciteVibrantItem(new Item.Properties().setId(Keys.VOLUCITE_VIBRANT).trimMaterial(AerialHellTrimMaterials.Keys.VOLUCITE_VIBRANT).rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<Item> OVERHEATED_RUBY = ITEMS.register(Keys.OVERHEATED_RUBY.identifier().getPath(), () -> new WithInformationItem(new Item.Properties().setId(Keys.OVERHEATED_RUBY)));
    public static final DeferredItem<Item> OVERHEATED_VOLUCITE = ITEMS.register(Keys.OVERHEATED_VOLUCITE.identifier().getPath(), () -> new WithInformationItem(new Item.Properties().setId(Keys.OVERHEATED_VOLUCITE)));

    //legendary ores
    public static final DeferredItem<Item> ARSONIST_INGOT = ITEMS.register(Keys.ARSONIST_INGOT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_INGOT).trimMaterial(AerialHellTrimMaterials.Keys.ARSONIST_INGOT).rarity(AerialHellRarities.LEGENDARY.getValue()).fireResistant()));
    public static final DeferredItem<Item> LUNATIC_CRYSTAL = ITEMS.register(Keys.LUNATIC_CRYSTAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_CRYSTAL).trimMaterial(AerialHellTrimMaterials.Keys.LUNATIC_CRYSTAL).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.register(Keys.OBSIDIAN_SHARD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_SHARD).trimMaterial(AerialHellTrimMaterials.Keys.OBSIDIAN_SHARD).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> CURSED_CRYSAL = ITEMS.register(Keys.CURSED_CRYSAL.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.CURSED_CRYSAL).trimMaterial(AerialHellTrimMaterials.Keys.CURSED_CRYSTAL).rarity(AerialHellRarities.CORRUPTED.getValue())));

    public static final DeferredItem<Item> ARSONIST_BLOCK = ITEMS.register(Keys.ARSONIST_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.ARSONIST_BLOCK.get(), new Item.Properties().setId(Keys.ARSONIST_BLOCK).useBlockDescriptionPrefix().rarity(AerialHellRarities.LEGENDARY.getValue()).fireResistant()));
    public static final DeferredItem<Item> LUNATIC_CRYSTAL_BLOCK = ITEMS.register(Keys.LUNATIC_CRYSTAL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_CRYSTAL_BLOCK.get(), new Item.Properties().setId(Keys.LUNATIC_CRYSTAL_BLOCK).useBlockDescriptionPrefix().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> CURSED_CRYSAL_BLOCK = ITEMS.register(Keys.CURSED_CRYSAL_BLOCK.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CURSED_CRYSAL_BLOCK.get(), new Item.Properties().setId(Keys.CURSED_CRYSAL_BLOCK).useBlockDescriptionPrefix().rarity(AerialHellRarities.CORRUPTED.getValue())));

    //cactus
    public static final DeferredItem<Item> SKY_CACTUS = ITEMS.register(Keys.SKY_CACTUS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS.get(), new Item.Properties().setId(Keys.SKY_CACTUS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_PLANKS = ITEMS.register(Keys.SKY_CACTUS_FIBER_PLANKS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PLANKS.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_PLANKS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BOOKSHELF = ITEMS.register(Keys.SKY_CACTUS_FIBER_BOOKSHELF.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BOOKSHELF.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_BOOKSHELF).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS = ITEMS.register(Keys.VIBRANT_SKY_CACTUS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS.get(), new Item.Properties().setId(Keys.VIBRANT_SKY_CACTUS).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN = ITEMS.register(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get(), new Item.Properties().setId(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN).useBlockDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));

    //bushes
    public static final DeferredItem<Item> AERIAL_BERRY_SEEDS = ITEMS.register(Keys.AERIAL_BERRY_SEEDS.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.AERIAL_BERRY_BUSH.get(), new Item.Properties().setId(Keys.AERIAL_BERRY_SEEDS).useItemDescriptionPrefix()));
    public static final DeferredItem<Item> VIBRANT_AERIAL_BERRY_SEEDS = ITEMS.register(Keys.VIBRANT_AERIAL_BERRY_SEEDS.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.VIBRANT_AERIAL_BERRY_BUSH.get(), new Item.Properties().setId(Keys.VIBRANT_AERIAL_BERRY_SEEDS).useItemDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));

    //crops
    public static final DeferredItem<Item> STELLAR_WHEAT_SEEDS = ITEMS.register(Keys.STELLAR_WHEAT_SEEDS.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.STELLAR_WHEAT.get(), new Item.Properties().setId(Keys.STELLAR_WHEAT_SEEDS).useItemDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_WHEAT = ITEMS.register(Keys.STELLAR_WHEAT.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.STELLAR_WHEAT)));
    public static final DeferredItem<Item> BLUE_MEANIE_SPORES = ITEMS.register(Keys.BLUE_MEANIE_SPORES.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.BLUE_MEANIE_CROP.get(), new Item.Properties().setId(Keys.BLUE_MEANIE_SPORES).useItemDescriptionPrefix()));
    public static final DeferredItem<Item> BLUE_MEANIE_CAP = ITEMS.register(Keys.BLUE_MEANIE_CAP.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.BLUE_MEANIE_CAP)));

    //vertical growing plants
    public static final DeferredItem<Item> CLIMBING_VINE = ITEMS.register(Keys.CLIMBING_VINE.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.CLIMBING_VINE.get(), new Item.Properties().setId(Keys.CLIMBING_VINE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_SUGAR_CANE = ITEMS.register(Keys.STELLAR_SUGAR_CANE.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.STELLAR_SUGAR_CANE.get(), new Item.Properties().setId(Keys.STELLAR_SUGAR_CANE).useBlockDescriptionPrefix()));

    //chorus like
    public static final DeferredItem<Item> FULL_MOON_FLOWER = ITEMS.register(Keys.FULL_MOON_FLOWER.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.FULL_MOON_FLOWER.get(), new Item.Properties().setId(Keys.FULL_MOON_FLOWER).useBlockDescriptionPrefix()));

    //vines
    public static final DeferredItem<Item> VINE_BLOSSOM = ITEMS.register(Keys.VINE_BLOSSOM.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.BLOSSOMING_VINES.get(), new Item.Properties().setId(Keys.VINE_BLOSSOM).useItemDescriptionPrefix()));
    public static final DeferredItem<Item> LAZULI_ROOTS = ITEMS.register(Keys.LAZULI_ROOTS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAZULI_ROOTS.get(), new Item.Properties().setId(Keys.LAZULI_ROOTS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_ROOTS = ITEMS.register(Keys.STELLAR_ROOTS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_ROOTS.get(), new Item.Properties().setId(Keys.STELLAR_ROOTS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> DEAD_ROOTS = ITEMS.register(Keys.DEAD_ROOTS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.DEAD_ROOTS.get(), new Item.Properties().setId(Keys.DEAD_ROOTS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GLOWING_ROOTS = ITEMS.register(Keys.GLOWING_ROOTS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLOWING_ROOTS.get(), new Item.Properties().setId(Keys.GLOWING_ROOTS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_GLOWING_ROOTS = ITEMS.register(Keys.SHADOW_GLOWING_ROOTS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_GLOWING_ROOTS.get(), new Item.Properties().setId(Keys.SHADOW_GLOWING_ROOTS).useBlockDescriptionPrefix()));

    //grass
    public static final DeferredItem<Item> STELLAR_GRASS = ITEMS.register(Keys.STELLAR_GRASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS.get(), new Item.Properties().setId(Keys.STELLAR_GRASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_GRASS_BALL = ITEMS.register(Keys.STELLAR_GRASS_BALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS_BALL.get(), new Item.Properties().setId(Keys.STELLAR_GRASS_BALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_FERN = ITEMS.register(Keys.STELLAR_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_FERN.get(), new Item.Properties().setId(Keys.STELLAR_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_TALL_GRASS = ITEMS.register(Keys.STELLAR_TALL_GRASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_TALL_GRASS.get(), new Item.Properties().setId(Keys.STELLAR_TALL_GRASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_TALL_FERN = ITEMS.register(Keys.STELLAR_TALL_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_TALL_FERN.get(), new Item.Properties().setId(Keys.STELLAR_TALL_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_VERY_TALL_GRASS = ITEMS.register(Keys.STELLAR_VERY_TALL_GRASS.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.STELLAR_VERY_TALL_GRASS.get(), new Item.Properties().setId(Keys.STELLAR_VERY_TALL_GRASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLUISH_FERN = ITEMS.register(Keys.BLUISH_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUISH_FERN.get(), new Item.Properties().setId(Keys.BLUISH_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TALL_BLUISH_FERN = ITEMS.register(Keys.TALL_BLUISH_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TALL_BLUISH_FERN.get(), new Item.Properties().setId(Keys.TALL_BLUISH_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> POLYCHROME_FERN = ITEMS.register(Keys.POLYCHROME_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.POLYCHROME_FERN.get(), new Item.Properties().setId(Keys.POLYCHROME_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> TALL_POLYCHROME_FERN = ITEMS.register(Keys.TALL_POLYCHROME_FERN.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.TALL_POLYCHROME_FERN.get(), new Item.Properties().setId(Keys.TALL_POLYCHROME_FERN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_DEAD_BUSH = ITEMS.register(Keys.STELLAR_DEAD_BUSH.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_DEAD_BUSH.get(), new Item.Properties().setId(Keys.STELLAR_DEAD_BUSH).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BRAMBLES = ITEMS.register(Keys.BRAMBLES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BRAMBLES.get(), new Item.Properties().setId(Keys.BRAMBLES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_BRAMBLES = ITEMS.register(Keys.SHADOW_BRAMBLES.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_BRAMBLES.get(), new Item.Properties().setId(Keys.SHADOW_BRAMBLES).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_GRASS = ITEMS.register(Keys.SHADOW_GRASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS.get(), new Item.Properties().setId(Keys.SHADOW_GRASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_GRASS_BALL = ITEMS.register(Keys.SHADOW_GRASS_BALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS_BALL.get(), new Item.Properties().setId(Keys.SHADOW_GRASS_BALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> PURPLISH_STELLAR_GRASS = ITEMS.register(Keys.PURPLISH_STELLAR_GRASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.PURPLISH_STELLAR_GRASS.get(), new Item.Properties().setId(Keys.PURPLISH_STELLAR_GRASS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_CLOVERS = ITEMS.register(Keys.STELLAR_CLOVERS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_CLOVERS.get(), new Item.Properties().setId(Keys.STELLAR_CLOVERS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GLOWING_STELLAR_GRASS = ITEMS.register(Keys.GLOWING_STELLAR_GRASS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLOWING_STELLAR_GRASS.get(), new Item.Properties().setId(Keys.GLOWING_STELLAR_GRASS).useBlockDescriptionPrefix()));

    //flowers
    public static final DeferredItem<Item> BLUE_FLOWER = ITEMS.register(Keys.BLUE_FLOWER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLUE_FLOWER.get(), new Item.Properties().setId(Keys.BLUE_FLOWER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BLACK_ROSE = ITEMS.register(Keys.BLACK_ROSE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BLACK_ROSE.get(), new Item.Properties().setId(Keys.BLACK_ROSE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> BELLFLOWER = ITEMS.register(Keys.BELLFLOWER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.BELLFLOWER.get(), new Item.Properties().setId(Keys.BELLFLOWER).useBlockDescriptionPrefix()));

    //with gui
    public static final DeferredItem<Item> OSCILLATOR = ITEMS.register(Keys.OSCILLATOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.OSCILLATOR.get(), new Item.Properties().setId(Keys.OSCILLATOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> FREEZER = ITEMS.register(Keys.FREEZER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.FREEZER.get(), new Item.Properties().setId(Keys.FREEZER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_FURNACE = ITEMS.register(Keys.STELLAR_FURNACE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_FURNACE.get(), new Item.Properties().setId(Keys.STELLAR_FURNACE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GHOST_STELLAR_FURNACE = ITEMS.register(Keys.GHOST_STELLAR_FURNACE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GHOST_STELLAR_FURNACE.get(), new Item.Properties().setId(Keys.GHOST_STELLAR_FURNACE).useBlockDescriptionPrefix()));

    //chests
    public static final DeferredItem<Item> AERIAL_TREE_CHEST = ITEMS.register(Keys.AERIAL_TREE_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST.get(), new Item.Properties().setId(Keys.AERIAL_TREE_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_CHEST = ITEMS.register(Keys.GOLDEN_BEECH_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_CHEST = ITEMS.register(Keys.COPPER_PINE_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST.get(), new Item.Properties().setId(Keys.COPPER_PINE_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_CHEST = ITEMS.register(Keys.LAPIS_ROBINIA_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_CHEST.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_CHEST = ITEMS.register(Keys.SHADOW_PINE_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_CHEST.get(), new Item.Properties().setId(Keys.SHADOW_PINE_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_CHEST = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CHEST = ITEMS.register(Keys.SKY_CACTUS_FIBER_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_CHEST = ITEMS.register(Keys.GRAY_SHROOM_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_CHEST.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MUD_CHEST = ITEMS.register(Keys.MUD_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MUD_CHEST.get(), new Item.Properties().setId(Keys.MUD_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LUNATIC_CHEST = ITEMS.register(Keys.LUNATIC_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LUNATIC_CHEST.get(), new Item.Properties().setId(Keys.LUNATIC_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> VOLUCITE_CHEST = ITEMS.register(Keys.VOLUCITE_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.VOLUCITE_CHEST.get(), new Item.Properties().setId(Keys.VOLUCITE_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_CHEST = ITEMS.register(Keys.SHADOW_CATACOMBS_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_CHEST.get(), new Item.Properties().setId(Keys.SHADOW_CATACOMBS_CHEST).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_NETHER_CHEST = ITEMS.register(Keys.GOLDEN_NETHER_CHEST.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_CHEST.get(), new Item.Properties().setId(Keys.GOLDEN_NETHER_CHEST).useBlockDescriptionPrefix()));

    //chest mimics
    public static final DeferredItem<Item> AERIAL_TREE_CHEST_MIMIC = ITEMS.register(Keys.AERIAL_TREE_CHEST_MIMIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get(), new Item.Properties().setId(Keys.AERIAL_TREE_CHEST_MIMIC).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_CHEST_MIMIC = ITEMS.register(Keys.GOLDEN_BEECH_CHEST_MIMIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_CHEST_MIMIC).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_CHEST_MIMIC = ITEMS.register(Keys.COPPER_PINE_CHEST_MIMIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get(), new Item.Properties().setId(Keys.COPPER_PINE_CHEST_MIMIC).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CHEST_MIMIC = ITEMS.register(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC).useBlockDescriptionPrefix()));

    //barrel mimics
    public static final DeferredItem<Item> SHADOW_PINE_BARREL_MIMIC = ITEMS.register(Keys.SHADOW_PINE_BARREL_MIMIC.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_BARREL_MIMIC.get(), new Item.Properties().setId(Keys.SHADOW_PINE_BARREL_MIMIC).useBlockDescriptionPrefix()));

    //fences, bars or walls
    public static final DeferredItem<Item> AERIAL_TREE_FENCE = ITEMS.register(Keys.AERIAL_TREE_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_FENCE.get(), new Item.Properties().setId(Keys.AERIAL_TREE_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_FENCE = ITEMS.register(Keys.GOLDEN_BEECH_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_FENCE.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_FENCE = ITEMS.register(Keys.COPPER_PINE_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_FENCE.get(), new Item.Properties().setId(Keys.COPPER_PINE_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_FENCE = ITEMS.register(Keys.LAPIS_ROBINIA_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_FENCE.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_FENCE = ITEMS.register(Keys.SHADOW_PINE_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_FENCE.get(), new Item.Properties().setId(Keys.SHADOW_PINE_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_FENCE = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_FENCE.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_FENCE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_FENCE = ITEMS.register(Keys.SKY_CACTUS_FIBER_FENCE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_FENCE.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_FENCE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_FENCE = ITEMS.register(Keys.GRAY_SHROOM_FENCE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_FENCE.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_FENCE).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> RUBY_BARS = ITEMS.register(Keys.RUBY_BARS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_BARS.get(), new Item.Properties().setId(Keys.RUBY_BARS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_WALL = ITEMS.register(Keys.STELLAR_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_WALL.get(), new Item.Properties().setId(Keys.STELLAR_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_WALL = ITEMS.register(Keys.STELLAR_COBBLESTONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_WALL.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_WALL = ITEMS.register(Keys.STELLAR_STONE_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_WALL.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_WALL = ITEMS.register(Keys.MOSSY_STELLAR_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_WALL = ITEMS.register(Keys.MOSSY_STELLAR_COBBLESTONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_COBBLESTONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_WALL = ITEMS.register(Keys.SLIPPERY_SAND_STONE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_WALL.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_WALL = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = ITEMS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties().setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GLAUCOPHANITE_WALL = ITEMS.register(Keys.GLAUCOPHANITE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_WALL.get(), new Item.Properties().setId(Keys.GLAUCOPHANITE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_WALL = ITEMS.register(Keys.POLISHED_GLAUCOPHANITE_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_WALL.get(), new Item.Properties().setId(Keys.POLISHED_GLAUCOPHANITE_WALL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAGMATIC_GEL_WALL = ITEMS.register(Keys.MAGMATIC_GEL_WALL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_WALL.get(), new Item.Properties().setId(Keys.MAGMATIC_GEL_WALL).useBlockDescriptionPrefix()));

    //gates
    public static final DeferredItem<Item> AERIAL_TREE_GATE = ITEMS.register(Keys.AERIAL_TREE_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_GATE.get(), new Item.Properties().setId(Keys.AERIAL_TREE_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_GATE = ITEMS.register(Keys.GOLDEN_BEECH_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_GATE.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_GATE = ITEMS.register(Keys.COPPER_PINE_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_GATE.get(), new Item.Properties().setId(Keys.COPPER_PINE_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_GATE = ITEMS.register(Keys.LAPIS_ROBINIA_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_GATE.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_GATE = ITEMS.register(Keys.SHADOW_PINE_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_GATE.get(), new Item.Properties().setId(Keys.SHADOW_PINE_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_GATE = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_GATE.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_GATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_GATE = ITEMS.register(Keys.SKY_CACTUS_FIBER_GATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_GATE.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_GATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_GATE = ITEMS.register(Keys.GRAY_SHROOM_GATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_GATE.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_GATE).useBlockDescriptionPrefix(), 100));

    //doors
    public static final DeferredItem<Item> AERIAL_TREE_DOOR = ITEMS.register(Keys.AERIAL_TREE_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_DOOR.get(), new Item.Properties().setId(Keys.AERIAL_TREE_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_DOOR = ITEMS.register(Keys.GOLDEN_BEECH_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_DOOR.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_DOOR = ITEMS.register(Keys.COPPER_PINE_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_DOOR.get(), new Item.Properties().setId(Keys.COPPER_PINE_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_DOOR = ITEMS.register(Keys.LAPIS_ROBINIA_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_DOOR.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_DOOR = ITEMS.register(Keys.SHADOW_PINE_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_DOOR.get(), new Item.Properties().setId(Keys.SHADOW_PINE_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_DOOR = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_DOOR.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_DOOR = ITEMS.register(Keys.SKY_CACTUS_FIBER_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_DOOR.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_DOOR = ITEMS.register(Keys.GRAY_SHROOM_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_DOOR.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_DOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_DOOR = ITEMS.register(Keys.RUBY_DOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_DOOR.get(), new Item.Properties().setId(Keys.RUBY_DOOR).useBlockDescriptionPrefix()));

    //trapdoors
    public static final DeferredItem<Item> AERIAL_TREE_TRAPDOOR = ITEMS.register(Keys.AERIAL_TREE_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_TRAPDOOR.get(), new Item.Properties().setId(Keys.AERIAL_TREE_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_TRAPDOOR = ITEMS.register(Keys.GOLDEN_BEECH_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_TRAPDOOR.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_TRAPDOOR = ITEMS.register(Keys.COPPER_PINE_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_TRAPDOOR.get(), new Item.Properties().setId(Keys.COPPER_PINE_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_TRAPDOOR = ITEMS.register(Keys.LAPIS_ROBINIA_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_TRAPDOOR = ITEMS.register(Keys.SHADOW_PINE_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_TRAPDOOR.get(), new Item.Properties().setId(Keys.SHADOW_PINE_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_TRAPDOOR = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_TRAPDOOR.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_TRAPDOOR = ITEMS.register(Keys.SKY_CACTUS_FIBER_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks. SKY_CACTUS_FIBER_TRAPDOOR.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_TRAPDOOR = ITEMS.register(Keys.GRAY_SHROOM_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_TRAPDOOR.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_TRAPDOOR).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> RUBY_TRAPDOOR = ITEMS.register(Keys.RUBY_TRAPDOOR.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.RUBY_TRAPDOOR.get(), new Item.Properties().setId(Keys.RUBY_TRAPDOOR).useBlockDescriptionPrefix()));

    //buttons
    public static final DeferredItem<Item> STELLAR_STONE_BUTTON = ITEMS.register(Keys.STELLAR_STONE_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BUTTON.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_BUTTON = ITEMS.register(Keys.STELLAR_COBBLESTONE_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_BUTTON.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_BUTTON = ITEMS.register(Keys.STELLAR_STONE_BRICKS_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_BUTTON.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BUTTON = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_TREE_BUTTON = ITEMS.register(Keys.AERIAL_TREE_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BUTTON.get(), new Item.Properties().setId(Keys.AERIAL_TREE_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GOLDEN_BEECH_BUTTON = ITEMS.register(Keys.GOLDEN_BEECH_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BUTTON.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> COPPER_PINE_BUTTON = ITEMS.register(Keys.COPPER_PINE_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BUTTON.get(), new Item.Properties().setId(Keys.COPPER_PINE_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BUTTON = ITEMS.register(Keys.LAPIS_ROBINIA_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BUTTON.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> SHADOW_PINE_BUTTON = ITEMS.register(Keys.SHADOW_PINE_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BUTTON.get(), new Item.Properties().setId(Keys.SHADOW_PINE_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BUTTON = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BUTTON.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_BUTTON).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BUTTON = ITEMS.register(Keys.SKY_CACTUS_FIBER_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BUTTON.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_BUTTON).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_BUTTON = ITEMS.register(Keys.GRAY_SHROOM_BUTTON.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BUTTON.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_BUTTON).useBlockDescriptionPrefix(), 30));
    public static final DeferredItem<Item> GLAUCOPHANITE_BUTTON = ITEMS.register(Keys.GLAUCOPHANITE_BUTTON.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_BUTTON.get(), new Item.Properties().setId(Keys.GLAUCOPHANITE_BUTTON).useBlockDescriptionPrefix()));

    //pressure plates
    public static final DeferredItem<Item> STELLAR_STONE_PRESSURE_PLATE = ITEMS.register(Keys.STELLAR_STONE_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.STELLAR_STONE_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE = ITEMS.register(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE = ITEMS.register(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE = ITEMS.register(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> AERIAL_TREE_PRESSURE_PLATE = ITEMS.register(Keys.AERIAL_TREE_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.AERIAL_TREE_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_PRESSURE_PLATE = ITEMS.register(Keys.GOLDEN_BEECH_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_PRESSURE_PLATE = ITEMS.register(Keys.COPPER_PINE_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.COPPER_PINE_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_PRESSURE_PLATE = ITEMS.register(Keys.LAPIS_ROBINIA_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_PRESSURE_PLATE = ITEMS.register(Keys.SHADOW_PINE_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.SHADOW_PINE_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE = ITEMS.register(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_PRESSURE_PLATE = ITEMS.register(Keys.GRAY_SHROOM_PRESSURE_PLATE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_PRESSURE_PLATE).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> GLAUCOPHANITE_PRESSURE_PLATE = ITEMS.register(Keys.GLAUCOPHANITE_PRESSURE_PLATE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_PRESSURE_PLATE.get(), new Item.Properties().setId(Keys.GLAUCOPHANITE_PRESSURE_PLATE).useBlockDescriptionPrefix()));

    //slabs
    public static final DeferredItem<Item> AERIAL_TREE_SLAB = ITEMS.register(Keys.AERIAL_TREE_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_SLAB.get(), new Item.Properties().setId(Keys.AERIAL_TREE_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> GOLDEN_BEECH_SLAB = ITEMS.register(Keys.GOLDEN_BEECH_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_SLAB.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> COPPER_PINE_SLAB = ITEMS.register(Keys.COPPER_PINE_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_SLAB.get(), new Item.Properties().setId(Keys.COPPER_PINE_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SLAB = ITEMS.register(Keys.LAPIS_ROBINIA_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_SLAB.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> SHADOW_PINE_SLAB = ITEMS.register(Keys.SHADOW_PINE_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_SLAB.get(), new Item.Properties().setId(Keys.SHADOW_PINE_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SLAB = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SLAB.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_SLAB).useBlockDescriptionPrefix(), 150));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_SLAB = ITEMS.register(Keys.SKY_CACTUS_FIBER_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_SLAB.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_SLAB = ITEMS.register(Keys.GRAY_SHROOM_SLAB.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_SLAB.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_SLAB).useBlockDescriptionPrefix(), 50));
    public static final DeferredItem<Item> STELLAR_STONE_SLAB = ITEMS.register(Keys.STELLAR_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_SLAB.get(), new Item.Properties().setId(Keys.STELLAR_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_SLAB = ITEMS.register(Keys.STELLAR_COBBLESTONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_SLAB = ITEMS.register(Keys.STELLAR_STONE_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_SLAB = ITEMS.register(Keys.MOSSY_STELLAR_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_SLAB = ITEMS.register(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_SLAB = ITEMS.register(Keys.SLIPPERY_SAND_STONE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = ITEMS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties().setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_SLAB = ITEMS.register(Keys.POLISHED_GLAUCOPHANITE_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_SLAB.get(), new Item.Properties().setId(Keys.POLISHED_GLAUCOPHANITE_SLAB).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAGMATIC_GEL_SLAB = ITEMS.register(Keys.MAGMATIC_GEL_SLAB.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_SLAB.get(), new Item.Properties().setId(Keys.MAGMATIC_GEL_SLAB).useBlockDescriptionPrefix()));

    //stairs
    public static final DeferredItem<Item> AERIAL_TREE_STAIRS = ITEMS.register(Keys.AERIAL_TREE_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_STAIRS.get(), new Item.Properties().setId(Keys.AERIAL_TREE_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_STAIRS = ITEMS.register(Keys.GOLDEN_BEECH_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_STAIRS.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_STAIRS = ITEMS.register(Keys.COPPER_PINE_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_STAIRS.get(), new Item.Properties().setId(Keys.COPPER_PINE_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_STAIRS = ITEMS.register(Keys.LAPIS_ROBINIA_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_STAIRS.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_STAIRS = ITEMS.register(Keys.SHADOW_PINE_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_STAIRS.get(), new Item.Properties().setId(Keys.SHADOW_PINE_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_STAIRS = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STAIRS.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_STAIRS).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_STAIRS = ITEMS.register(Keys.SKY_CACTUS_FIBER_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_STAIRS.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_STAIRS = ITEMS.register(Keys.GRAY_SHROOM_STAIRS.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_STAIRS.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_STAIRS).useBlockDescriptionPrefix(), 100));
    public static final DeferredItem<Item> STELLAR_STONE_STAIRS = ITEMS.register(Keys.STELLAR_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_STAIRS.get(), new Item.Properties().setId(Keys.STELLAR_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_STAIRS = ITEMS.register(Keys.STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties().setId(Keys.STELLAR_COBBLESTONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_STAIRS = ITEMS.register(Keys.STELLAR_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.STELLAR_STONE_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_STAIRS = ITEMS.register(Keys.MOSSY_STELLAR_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS = ITEMS.register(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties().setId(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_STAIRS = ITEMS.register(Keys.SLIPPERY_SAND_STONE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS = ITEMS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = ITEMS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties().setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_STAIRS = ITEMS.register(Keys.POLISHED_GLAUCOPHANITE_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_STAIRS.get(), new Item.Properties().setId(Keys.POLISHED_GLAUCOPHANITE_STAIRS).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAGMATIC_GEL_STAIRS = ITEMS.register(Keys.MAGMATIC_GEL_STAIRS.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_STAIRS.get(), new Item.Properties().setId(Keys.MAGMATIC_GEL_STAIRS).useBlockDescriptionPrefix()));

    //signs
    public static final DeferredItem<Item> AERIAL_TREE_SIGN = ITEMS.register(Keys.AERIAL_TREE_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.AERIAL_TREE_STANDING_SIGN.get(), AerialHellBlocks.AERIAL_TREE_WALL_SIGN.get(), new Item.Properties().setId(Keys.AERIAL_TREE_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_SIGN = ITEMS.register(Keys.GOLDEN_BEECH_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN.get(), AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_SIGN = ITEMS.register(Keys.COPPER_PINE_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.COPPER_PINE_STANDING_SIGN.get(), AerialHellBlocks.COPPER_PINE_WALL_SIGN.get(), new Item.Properties().setId(Keys.COPPER_PINE_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SIGN = ITEMS.register(Keys.LAPIS_ROBINIA_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN.get(), AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_SIGN = ITEMS.register(Keys.SHADOW_PINE_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.SHADOW_PINE_STANDING_SIGN.get(), AerialHellBlocks.SHADOW_PINE_WALL_SIGN.get(), new Item.Properties().setId(Keys.SHADOW_PINE_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SIGN = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN.get(), AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_SIGN = ITEMS.register(Keys.SKY_CACTUS_FIBER_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN.get(), AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_SIGN).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_SIGN = ITEMS.register(Keys.GRAY_SHROOM_SIGN.identifier().getPath(), () -> new SignItem(AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN.get(), AerialHellBlocks.GRAY_SHROOM_WALL_SIGN.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_SIGN).useBlockDescriptionPrefix()));

    //hanging signs
    public static final DeferredItem<Item> AERIAL_TREE_HANGING_SIGN = ITEMS.register(Keys.AERIAL_TREE_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.AERIAL_TREE_HANGING_SIGN.get(), AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.AERIAL_TREE_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_BEECH_HANGING_SIGN = ITEMS.register(Keys.GOLDEN_BEECH_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN.get(), AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> COPPER_PINE_HANGING_SIGN = ITEMS.register(Keys.COPPER_PINE_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.COPPER_PINE_HANGING_SIGN.get(), AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.COPPER_PINE_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> LAPIS_ROBINIA_HANGING_SIGN = ITEMS.register(Keys.LAPIS_ROBINIA_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN.get(), AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> SHADOW_PINE_HANGING_SIGN = ITEMS.register(Keys.SHADOW_PINE_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.SHADOW_PINE_HANGING_SIGN.get(), AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.SHADOW_PINE_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_HANGING_SIGN = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN.get(), AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_HANGING_SIGN = ITEMS.register(Keys.SKY_CACTUS_FIBER_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN.get(), AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));
    public static final DeferredItem<Item> GRAY_SHROOM_HANGING_SIGN = ITEMS.register(Keys.GRAY_SHROOM_HANGING_SIGN.identifier().getPath(), () -> new HangingSignItem(AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN.get(), AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_HANGING_SIGN).useBlockDescriptionPrefix().stacksTo(16)));

    //crafting tables
    public static final DeferredItem<Item> AERIAL_TREE_CRAFTING_TABLE = ITEMS.register(Keys.AERIAL_TREE_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.AERIAL_TREE_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_CRAFTING_TABLE = ITEMS.register(Keys.GOLDEN_BEECH_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_CRAFTING_TABLE = ITEMS.register(Keys.COPPER_PINE_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.COPPER_PINE_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_CRAFTING_TABLE = ITEMS.register(Keys.LAPIS_ROBINIA_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_CRAFTING_TABLE = ITEMS.register(Keys.SHADOW_PINE_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.SHADOW_PINE_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE = ITEMS.register(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_CRAFTING_TABLE = ITEMS.register(Keys.GRAY_SHROOM_CRAFTING_TABLE.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_CRAFTING_TABLE).useBlockDescriptionPrefix(), 100));

    //barrels
    public static final DeferredItem<Item> AERIAL_TREE_BARREL = ITEMS.register(Keys.AERIAL_TREE_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BARREL.get(), new Item.Properties().setId(Keys.AERIAL_TREE_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_BARREL = ITEMS.register(Keys.GOLDEN_BEECH_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BARREL.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_BARREL = ITEMS.register(Keys.COPPER_PINE_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BARREL.get(), new Item.Properties().setId(Keys.COPPER_PINE_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BARREL = ITEMS.register(Keys.LAPIS_ROBINIA_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BARREL.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_BARREL = ITEMS.register(Keys.SHADOW_PINE_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BARREL.get(), new Item.Properties().setId(Keys.SHADOW_PINE_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BARREL = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_BARREL).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BARREL = ITEMS.register(Keys.SKY_CACTUS_FIBER_BARREL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BARREL.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_BARREL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_BARREL = ITEMS.register(Keys.GRAY_SHROOM_BARREL.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BARREL.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_BARREL).useBlockDescriptionPrefix(), 100));

    //composters
    public static final DeferredItem<Item> AERIAL_TREE_COMPOSTER = ITEMS.register(Keys.AERIAL_TREE_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_COMPOSTER.get(), new Item.Properties().setId(Keys.AERIAL_TREE_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_COMPOSTER = ITEMS.register(Keys.GOLDEN_BEECH_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_COMPOSTER.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> COPPER_PINE_COMPOSTER = ITEMS.register(Keys.COPPER_PINE_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_COMPOSTER.get(), new Item.Properties().setId(Keys.COPPER_PINE_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_COMPOSTER = ITEMS.register(Keys.LAPIS_ROBINIA_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_COMPOSTER = ITEMS.register(Keys.SHADOW_PINE_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_COMPOSTER.get(), new Item.Properties().setId(Keys.SHADOW_PINE_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_COMPOSTER = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_COMPOSTER.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_COMPOSTER).useBlockDescriptionPrefix(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_COMPOSTER = ITEMS.register(Keys.SKY_CACTUS_FIBER_COMPOSTER.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_COMPOSTER.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_COMPOSTER).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_COMPOSTER = ITEMS.register(Keys.GRAY_SHROOM_COMPOSTER.identifier().getPath(), () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_COMPOSTER.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_COMPOSTER).useBlockDescriptionPrefix(), 100));

    //decorative
    public static final DeferredItem<Item> AERIAL_TREE_VINE_ROPE_SPOOL = ITEMS.register(Keys.AERIAL_TREE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.AERIAL_TREE_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GOLDEN_BEECH_VINE_ROPE_SPOOL = ITEMS.register(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> COPPER_PINE_VINE_ROPE_SPOOL = ITEMS.register(Keys.COPPER_PINE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.COPPER_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.COPPER_PINE_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_VINE_ROPE_SPOOL = ITEMS.register(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SHADOW_PINE_VINE_ROPE_SPOOL = ITEMS.register(Keys.SHADOW_PINE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.SHADOW_PINE_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = ITEMS.register(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = ITEMS.register(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> GRAY_SHROOM_VINE_ROPE_SPOOL = ITEMS.register(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL.identifier().getPath(), () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_VINE_ROPE_SPOOL.get(), new Item.Properties().setId(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL).useBlockDescriptionPrefix()));

    //item for crafts
    public static final DeferredItem<Item> SKY_STICK = ITEMS.register(Keys.SKY_STICK.identifier().getPath(),() -> new BurnableItem(new Item.Properties().setId(Keys.SKY_STICK), 100));
    public static final DeferredItem<Item> SKY_BOWL = ITEMS.register(Keys.SKY_BOWL.identifier().getPath(),() -> new BurnableItem(new Item.Properties().setId(Keys.SKY_BOWL), 200));
    public static final DeferredItem<Item> SHADOW_SHARD = ITEMS.register(Keys.SHADOW_SHARD.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.SHADOW_SHARD).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> ROTTEN_LEATHER = ITEMS.register(Keys.ROTTEN_LEATHER.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.ROTTEN_LEATHER)));
    public static final DeferredItem<Item> VENOMOUS_SNAKE_SKIN = ITEMS.register(Keys.VENOMOUS_SNAKE_SKIN.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.VENOMOUS_SNAKE_SKIN)));
    public static final DeferredItem<Item> ARSONIST_UPGRADE_SMITHING_TEMPLATE = ITEMS.register(Keys.ARSONIST_UPGRADE_SMITHING_TEMPLATE.identifier().getPath(), () -> ItemHelper.SmithingTemplate.createUpgradeTemplate("arsonist", new Item.Properties().setId(Keys.ARSONIST_UPGRADE_SMITHING_TEMPLATE)));

    //projectile item
    public static final DeferredItem<Item> STELLAR_EGG = ITEMS.register(Keys.STELLAR_EGG.identifier().getPath(),() -> new StellarEggItem(new Item.Properties().setId(Keys.STELLAR_EGG).stacksTo(16)));
    public static final DeferredItem<Item> DIMENSION_SHATTERER_PROJECTILE = ITEMS.register(Keys.DIMENSION_SHATTERER_PROJECTILE.identifier().getPath(),() -> new DimensionShattererProjectileItem(new Item.Properties().setId(Keys.DIMENSION_SHATTERER_PROJECTILE).stacksTo(16)));

    //shurikens
    public static final DeferredItem<Item> IRON_SHURIKEN = ITEMS.register(Keys.IRON_SHURIKEN.identifier().getPath(), () -> new IronShurikenItem(new Item.Properties().rarity(Rarity.COMMON).setId(Keys.IRON_SHURIKEN)));
    public static final DeferredItem<Item> GOLD_SHURIKEN = ITEMS.register(Keys.GOLD_SHURIKEN.identifier().getPath(), () -> new GoldShurikenItem(new Item.Properties().rarity(Rarity.COMMON).setId(Keys.GOLD_SHURIKEN)));
    public static final DeferredItem<Item> DIAMOND_SHURIKEN = ITEMS.register(Keys.DIAMOND_SHURIKEN.identifier().getPath(), () -> new DiamondShurikenItem(new Item.Properties().rarity(Rarity.COMMON).setId(Keys.DIAMOND_SHURIKEN)));
    public static final DeferredItem<Item> NETHERITE_SHURIKEN = ITEMS.register(Keys.NETHERITE_SHURIKEN.identifier().getPath(), () -> new NetheriteShurikenItem(new Item.Properties().rarity(Rarity.UNCOMMON).setId(Keys.NETHERITE_SHURIKEN)));
    public static final DeferredItem<Item> RUBY_SHURIKEN = ITEMS.register(Keys.RUBY_SHURIKEN.identifier().getPath(), () -> new RubyShurikenItem(new Item.Properties().rarity(Rarity.COMMON).setId(Keys.RUBY_SHURIKEN)));
    public static final DeferredItem<Item> AZURITE_SHURIKEN = ITEMS.register(Keys.AZURITE_SHURIKEN.identifier().getPath(), () -> new AzuriteShurikenItem(new Item.Properties().rarity(Rarity.COMMON).setId(Keys.AZURITE_SHURIKEN)));
    public static final DeferredItem<Item> MAGMATIC_GEL_SHURIKEN = ITEMS.register(Keys.MAGMATIC_GEL_SHURIKEN.identifier().getPath(), () -> new MagmaticGelShurikenItem(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).setId(Keys.MAGMATIC_GEL_SHURIKEN)));
    public static final DeferredItem<Item> VOLUCITE_SHURIKEN = ITEMS.register(Keys.VOLUCITE_SHURIKEN.identifier().getPath(), () -> new VoluciteShurikenItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).setId(Keys.VOLUCITE_SHURIKEN)));
    public static final DeferredItem<Item> OBSIDIAN_SHURIKEN = ITEMS.register(Keys.OBSIDIAN_SHURIKEN.identifier().getPath(), () -> new ObsidianShurikenItem(new Item.Properties().rarity(Rarity.EPIC).setId(Keys.OBSIDIAN_SHURIKEN)));
    public static final DeferredItem<Item> LUNATIC_CRYSTAL_SHURIKEN = ITEMS.register(Keys.LUNATIC_CRYSTAL_SHURIKEN.identifier().getPath(), () -> new LunaticCrystalShurikenItem(new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()).setId(Keys.LUNATIC_CRYSTAL_SHURIKEN)));
    public static final DeferredItem<Item> ARSONIST_SHURIKEN = ITEMS.register(Keys.ARSONIST_SHURIKEN.identifier().getPath(), () -> new ArsonistShurikenItem(new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue()).setId(Keys.ARSONIST_SHURIKEN)));
    public static final DeferredItem<Item> LIGHTNING_SHURIKEN = ITEMS.register(Keys.LIGHTNING_SHURIKEN.identifier().getPath(), () -> new LightningShurikenItem(new Item.Properties().rarity(Rarity.UNCOMMON).setId(Keys.LIGHTNING_SHURIKEN)));

    //food
    public static final DeferredItem<Item> AERIAL_BERRY = ITEMS.register(Keys.AERIAL_BERRY.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.AERIAL_BERRY).food(AerialHellFoods.Properties.AERIAL_BERRY)));
    public static final DeferredItem<Item> ROASTED_AERIAL_BERRY = ITEMS.register(Keys.ROASTED_AERIAL_BERRY.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.ROASTED_AERIAL_BERRY).food(AerialHellFoods.Properties.ROASTED_AERIAL_BERRY)));
    public static final DeferredItem<Item> VIBRANT_AERIAL_BERRY = ITEMS.register(Keys.VIBRANT_AERIAL_BERRY.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.VIBRANT_AERIAL_BERRY).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_AERIAL_BERRY)));
    public static final DeferredItem<Item> FROZEN_AERIAL_BERRY = ITEMS.register(Keys.FROZEN_AERIAL_BERRY.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_AERIAL_BERRY).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_AERIAL_BERRY, AerialHellFoods.Consumables.FROZEN_AERIAL_BERRY)));
    public static final DeferredItem<Item> STELLAR_BREAD = ITEMS.register(Keys.STELLAR_BREAD.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.STELLAR_BREAD).food(AerialHellFoods.Properties.STELLAR_BREAD)));
    public static final DeferredItem<Item> FROZEN_MUTTON = ITEMS.register(Keys.FROZEN_MUTTON.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_MUTTON).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_MUTTON, AerialHellFoods.Consumables.FROZEN_MUTTON)));
    public static final DeferredItem<Item> VIBRANT_CHICKEN = ITEMS.register(Keys.VIBRANT_CHICKEN.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VIBRANT_CHICKEN).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_CHICKEN, AerialHellFoods.Consumables.VIBRANT_CHICKEN)));
    public static final DeferredItem<Item> FROZEN_CHICKEN = ITEMS.register(Keys.FROZEN_CHICKEN.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_CHICKEN).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_CHICKEN, AerialHellFoods.Consumables.FROZEN_CHICKEN)));
    public static final DeferredItem<Item> RUBY_AERIAL_BERRY = ITEMS.register(Keys.RUBY_AERIAL_BERRY.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_AERIAL_BERRY).rarity(Rarity.RARE).food(AerialHellFoods.Properties.RUBY_AERIAL_BERRY, AerialHellFoods.Consumables.RUBY_AERIAL_BERRY)));
    public static final DeferredItem<Item> VOLUCITE_AERIAL_BERRY = ITEMS.register(Keys.VOLUCITE_AERIAL_BERRY.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VOLUCITE_AERIAL_BERRY).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.VOLUCITE_AERIAL_BERRY)));
    public static final DeferredItem<Item> GLOWING_STICK_FRUIT = ITEMS.register(Keys.GLOWING_STICK_FRUIT.identifier().getPath(),() -> new BlockItem(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES.get(), new Item.Properties().setId(Keys.GLOWING_STICK_FRUIT).food(AerialHellFoods.Properties.GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> VIBRANT_GLOWING_STICK_FRUIT = ITEMS.register(Keys.VIBRANT_GLOWING_STICK_FRUIT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VIBRANT_GLOWING_STICK_FRUIT).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> FROZEN_GLOWING_STICK_FRUIT = ITEMS.register(Keys.FROZEN_GLOWING_STICK_FRUIT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_GLOWING_STICK_FRUIT).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_GLOWING_STICK_FRUIT, AerialHellFoods.Consumables.FROZEN_GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> CORTINARIUS_VIOLACEUS_PIECE = ITEMS.register(Keys.CORTINARIUS_VIOLACEUS_PIECE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.CORTINARIUS_VIOLACEUS_PIECE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.CORTINARIUS_VIOLACEUS_PIECE, AerialHellFoods.Consumables.CORTINARIUS_VIOLACEUS_PIECE)));
    public static final DeferredItem<Item> GANODERMA_APPLANATUM_PIECE = ITEMS.register(Keys.GANODERMA_APPLANATUM_PIECE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.GANODERMA_APPLANATUM_PIECE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.GANODERMA_APPLANATUM_PIECE, AerialHellFoods.Consumables.GANODERMA_APPLANATUM_PIECE)));
    public static final DeferredItem<Item> DARK_SHADOW_FRUIT = ITEMS.register(Keys.DARK_SHADOW_FRUIT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.DARK_SHADOW_FRUIT).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.DARK_SHADOW_FRUIT, AerialHellFoods.Consumables.DARK_SHADOW_FRUIT)));
    public static final DeferredItem<Item> PURPLE_SHADOW_FRUIT = ITEMS.register(Keys.PURPLE_SHADOW_FRUIT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.PURPLE_SHADOW_FRUIT).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.PURPLE_SHADOW_FRUIT, AerialHellFoods.Consumables.PURPLE_SHADOW_FRUIT)));
    public static final DeferredItem<Item> SHADOW_FRUIT_STEW = ITEMS.register(Keys.SHADOW_FRUIT_STEW.identifier().getPath(), () -> new SkySoupItem(new Item.Properties().setId(Keys.SHADOW_FRUIT_STEW).rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SHADOW_FRUIT_STEW, AerialHellFoods.Consumables.SHADOW_FRUIT_STEW)));
    public static final DeferredItem<Item> SOLID_ETHER_SOUP = ITEMS.register(Keys.SOLID_ETHER_SOUP.identifier().getPath(), () -> new SkySoupItem(new Item.Properties().setId(Keys.SOLID_ETHER_SOUP).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.SOLID_ETHER_SOUP, AerialHellFoods.Consumables.SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> VIBRANT_SOLID_ETHER_SOUP = ITEMS.register(Keys.VIBRANT_SOLID_ETHER_SOUP.identifier().getPath(), () -> new SkySoupItem(new Item.Properties().setId(Keys.VIBRANT_SOLID_ETHER_SOUP).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.VIBRANT_SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> FROZEN_SOLID_ETHER_SOUP = ITEMS.register(Keys.FROZEN_SOLID_ETHER_SOUP.identifier().getPath(), () -> new SkySoupItem(new Item.Properties().setId(Keys.FROZEN_SOLID_ETHER_SOUP).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.FROZEN_SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> SHADOW_SPIDER_EYE = ITEMS.register(Keys.SHADOW_SPIDER_EYE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.SHADOW_SPIDER_EYE).rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SHADOW_SPIDER_EYE, AerialHellFoods.Consumables.SHADOW_SPIDER_EYE)));
    public static final DeferredItem<Item> PHANTOM_MEAT = ITEMS.register(Keys.PHANTOM_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.PHANTOM_MEAT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.PHANTOM_MEAT, AerialHellFoods.Consumables.PHANTOM_MEAT)));
    public static final DeferredItem<Item> VIBRANT_PHANTOM_MEAT = ITEMS.register(Keys.VIBRANT_PHANTOM_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VIBRANT_PHANTOM_MEAT).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_PHANTOM_MEAT, AerialHellFoods.Consumables.VIBRANT_PHANTOM_MEAT)));
    public static final DeferredItem<Item> FROZEN_PHANTOM_MEAT = ITEMS.register(Keys.FROZEN_PHANTOM_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_PHANTOM_MEAT).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_PHANTOM_MEAT, AerialHellFoods.Consumables.FROZEN_PHANTOM_MEAT)));
    public static final DeferredItem<Item> COOKED_PHANTOM_MEAT = ITEMS.register(Keys.COOKED_PHANTOM_MEAT.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.COOKED_PHANTOM_MEAT).food(AerialHellFoods.Properties.COOKED_PHANTOM_MEAT)));
    public static final DeferredItem<Item> TURTLE_MEAT = ITEMS.register(Keys.TURTLE_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.TURTLE_MEAT).food(Foods.BEEF)));
    public static final DeferredItem<Item> VIBRANT_TURTLE_MEAT = ITEMS.register(Keys.VIBRANT_TURTLE_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VIBRANT_TURTLE_MEAT).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_TURTLE_MEAT, AerialHellFoods.Consumables.VIBRANT_TURTLE_MEAT)));
    public static final DeferredItem<Item> FROZEN_TURTLE_MEAT = ITEMS.register(Keys.FROZEN_TURTLE_MEAT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.FROZEN_TURTLE_MEAT).rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_TURTLE_MEAT, AerialHellFoods.Consumables.FROZEN_TURTLE_MEAT)));
    public static final DeferredItem<Item> COOKED_TURTLE_MEAT = ITEMS.register(Keys.COOKED_TURTLE_MEAT.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.COOKED_TURTLE_MEAT).food(Foods.COOKED_BEEF)));
    public static final DeferredItem<Item> GODS_VOLUCITE_AERIAL_BERRY = ITEMS.register(Keys.GODS_VOLUCITE_AERIAL_BERRY.identifier().getPath(), () -> new FoilItem(new Item.Properties().setId(Keys.GODS_VOLUCITE_AERIAL_BERRY).rarity(AerialHellRarities.MYTHICAL.getValue()).food(AerialHellFoods.Properties.GODS_VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.GODS_VOLUCITE_AERIAL_BERRY)));
    public static final DeferredItem<Item> COPPER_PINE_CONE = ITEMS.register(Keys.COPPER_PINE_CONE.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.COPPER_PINE_CONE).food(AerialHellFoods.Properties.COPPER_PINE_CONE)));
    public static final DeferredItem<Item> AZURITE_COPPER_PINE_CONE = ITEMS.register(Keys.AZURITE_COPPER_PINE_CONE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_COPPER_PINE_CONE).rarity(Rarity.COMMON).food(AerialHellFoods.Properties.AZURITE_COPPER_PINE_CONE, AerialHellFoods.Consumables.AZURITE_COPPER_PINE_CONE)));
    public static final DeferredItem<Item> PHOENIX_FEATHER = ITEMS.register(Keys.PHOENIX_FEATHER.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.PHOENIX_FEATHER).rarity(AerialHellRarities.LEGENDARY.getValue()).food(AerialHellFoods.Properties.PHOENIX_FEATHER, AerialHellFoods.Consumables.PHOENIX_FEATHER)));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER = ITEMS.register(Keys.SKY_CACTUS_FIBER.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.SKY_CACTUS_FIBER).food(AerialHellFoods.Properties.SKY_CACTUS_FIBER, AerialHellFoods.Consumables.SKY_CACTUS_FIBER)));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS_FIBER = ITEMS.register(Keys.VIBRANT_SKY_CACTUS_FIBER.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.VIBRANT_SKY_CACTUS_FIBER).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_SKY_CACTUS_FIBER, AerialHellFoods.Consumables.VIBRANT_SKY_CACTUS_FIBER)));
    public static final DeferredItem<Item> WHITE_SOLID_ETHER_FRAGMENT = ITEMS.register(Keys.WHITE_SOLID_ETHER_FRAGMENT.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.WHITE_SOLID_ETHER_FRAGMENT).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> BLUE_SOLID_ETHER_FRAGMENT = ITEMS.register(Keys.BLUE_SOLID_ETHER_FRAGMENT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.BLUE_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.BLUE_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GOLDEN_SOLID_ETHER_FRAGMENT = ITEMS.register(Keys.GOLDEN_SOLID_ETHER_FRAGMENT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.GOLDEN_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GOLDEN_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GREEN_SOLID_ETHER_FRAGMENT = ITEMS.register(Keys.GREEN_SOLID_ETHER_FRAGMENT.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.GREEN_SOLID_ETHER_FRAGMENT).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GREEN_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> PURPLE_SOLID_ETHER_FRAGMENT = ITEMS.register(Keys.PURPLE_SOLID_ETHER_FRAGMENT.identifier().getPath(),() -> new Item(new Item.Properties().setId(Keys.PURPLE_SOLID_ETHER_FRAGMENT).rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.PURPLE_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GOLDEN_NETHER_MEAT_PIECE = ITEMS.register(Keys.GOLDEN_NETHER_MEAT_PIECE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.GOLDEN_NETHER_MEAT_PIECE).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_MEAT_PIECE, AerialHellFoods.Consumables.GOLDEN_NETHER_MEAT_PIECE)));
    public static final DeferredItem<Item> GOLDEN_NETHER_STEAK = ITEMS.register(Keys.GOLDEN_NETHER_STEAK.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.GOLDEN_NETHER_STEAK).rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.GOLDEN_NETHER_STEAK)));
    public static final DeferredItem<Item> VIBRANT_GOLDEN_NETHER_STEAK = ITEMS.register(Keys.VIBRANT_GOLDEN_NETHER_STEAK.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VIBRANT_GOLDEN_NETHER_STEAK).rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.VIBRANT_GOLDEN_NETHER_STEAK)));

    //buckets
    public static final DeferredItem<Item> IRON_LIQUID_OF_GODS_BUCKET = ITEMS.register(Keys.IRON_LIQUID_OF_GODS_BUCKET.identifier().getPath(), () -> new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get(), (new Item.Properties().setId(Keys.IRON_LIQUID_OF_GODS_BUCKET)).stacksTo(1)));
    public static final DeferredItem<Item> RUBY_LIQUID_OF_GODS_BUCKET = ITEMS.register(Keys.RUBY_LIQUID_OF_GODS_BUCKET.identifier().getPath(), () -> new RubyLiquidOfGodsBucketItem(new Item.Properties().setId(Keys.RUBY_LIQUID_OF_GODS_BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> RUBY_BUCKET = ITEMS.register(Keys.RUBY_BUCKET.identifier().getPath(), () -> new RubyBucketItem(new Item.Properties().setId(Keys.RUBY_BUCKET).stacksTo(16)));
    public static final DeferredItem<Item> RUBY_WATER_BUCKET = ITEMS.register(Keys.RUBY_WATER_BUCKET.identifier().getPath(), () -> new RubyWaterBucketItem(new Item.Properties().setId(Keys.RUBY_WATER_BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> RUBY_MILK_BUCKET = ITEMS.register(Keys.RUBY_MILK_BUCKET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_MILK_BUCKET).craftRemainder(RUBY_BUCKET.get()).component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET).usingConvertsTo(RUBY_BUCKET.get()).stacksTo(1)));

    //arrows & bows
    public static final DeferredItem<Item> RUBY_BLOWPIPE_ARROW = ITEMS.register(Keys.RUBY_BLOWPIPE_ARROW.identifier().getPath(), () -> new AerialArrowItem(new Item.Properties().setId(Keys.RUBY_BLOWPIPE_ARROW)));
    public static final DeferredItem<Item> VOLUCITE_BLOWPIPE_ARROW = ITEMS.register(Keys.VOLUCITE_BLOWPIPE_ARROW.identifier().getPath(), () -> new AerialArrowItem(new Item.Properties().setId(Keys.VOLUCITE_BLOWPIPE_ARROW).rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<Item> RUBY_BLOWPIPE = ITEMS.register(Keys.RUBY_BLOWPIPE.identifier().getPath(), () -> new BlowpipeItem(new Item.Properties().setId(Keys.RUBY_BLOWPIPE).stacksTo(1).durability(200), 1.7F));
    public static final DeferredItem<Item> VOLUCITE_BLOWPIPE = ITEMS.register(Keys.VOLUCITE_BLOWPIPE.identifier().getPath(), () -> new BlowpipeItem(new Item.Properties().setId(Keys.VOLUCITE_BLOWPIPE).rarity(AerialHellRarities.VIBRANT.getValue()).stacksTo(1).durability(400), 2.4F));

    //music discs
    public static final DeferredItem<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = ITEMS.register(Keys.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.AERIAL_HELL_THEME_TOMMAUP)));
    public static final DeferredItem<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = ITEMS.register(Keys.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.SWEDEN_ANDREAS_ZOELLER)));
    public static final DeferredItem<Item> MUSIC_DISC_ENTHUSIAST_TOURS = ITEMS.register(Keys.MUSIC_DISC_ENTHUSIAST_TOURS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MUSIC_DISC_ENTHUSIAST_TOURS).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.ENTHUSIAST_TOURS)));
    public static final DeferredItem<Item> MUSIC_DISC_BMINOR_ARULO = ITEMS.register(Keys.MUSIC_DISC_BMINOR_ARULO.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MUSIC_DISC_BMINOR_ARULO).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.BMINOR_ARULO)));
    public static final DeferredItem<Item> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = ITEMS.register(Keys.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.RETRO_EXPLORATION_TOMMAUP)));

    //tools
    public static final DeferredItem<Item> SKY_WOOD_PICKAXE = ITEMS.register(Keys.SKY_WOOD_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.SKY_WOOD_PICKAXE).pickaxe(ToolMaterials.SKY_WOOD, 1.0F, -2.8F)));
    public static final DeferredItem<Item> STELLAR_STONE_PICKAXE = ITEMS.register(Keys.STELLAR_STONE_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.STELLAR_STONE_PICKAXE).pickaxe(ToolMaterials.STELLAR_STONE, 1.0F, -2.8F)));
    public static final DeferredItem<Item> RUBY_PICKAXE = ITEMS.register(Keys.RUBY_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_PICKAXE).pickaxe(ToolMaterials.RUBY, 1.0F, -2.8F)));
    public static final DeferredItem<Item> AZURITE_PICKAXE = ITEMS.register(Keys.AZURITE_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_PICKAXE).pickaxe(ToolMaterials.AZURITE, 1.0F, -2.8F)));
    public static final DeferredItem<Item> MAGMATIC_GEL_PICKAXE = ITEMS.register(Keys.MAGMATIC_GEL_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_PICKAXE).pickaxe(ToolMaterials.MAGMATIC_GEL, 1.0F, -2.8F)));
    public static final DeferredItem<Item> OBSIDIAN_PICKAXE = ITEMS.register(Keys.OBSIDIAN_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_PICKAXE).pickaxe(ToolMaterials.OBSIDIAN, 1.0F, -2.8F).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> VOLUCITE_PICKAXE = ITEMS.register(Keys.VOLUCITE_PICKAXE.identifier().getPath(), () -> new EffectPickaxeItem(ToolMaterials.VOLUCITE, 1.0F, -2.8F, new Item.Properties().setId(Keys.VOLUCITE_PICKAXE).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> LUNATIC_PICKAXE = ITEMS.register(Keys.LUNATIC_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_PICKAXE).pickaxe(ToolMaterials.LUNATIC, 1.0F, -2.8F).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> ARSONIST_PICKAXE = ITEMS.register(Keys.ARSONIST_PICKAXE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_PICKAXE).fireResistant().pickaxe(ToolMaterials.ARSONIST, 1.0F, -2.8F).rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<Item> MAGMA_CUBE_PICKAXE = ITEMS.register(Keys.MAGMA_CUBE_PICKAXE.identifier().getPath(), () -> new EffectPickaxeItem(ToolMaterials.OBSIDIAN, 1.0F, -2.8F, new Item.Properties().setId(Keys.MAGMA_CUBE_PICKAXE).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> STELLAR_STONE_BREAKER = ITEMS.register(Keys.STELLAR_STONE_BREAKER.identifier().getPath(), () -> new AerialHellPickaxeItem(ToolMaterials.BREAKER, 1.0F, -2.8F, new Item.Properties().setId(Keys.STELLAR_STONE_BREAKER).rarity(Rarity.EPIC)));

    public static final DeferredItem<ShovelItem> SKY_WOOD_SHOVEL = ITEMS.register(Keys.SKY_WOOD_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.SKY_WOOD, 1.5F, -3.0F, new Item.Properties().setId(Keys.SKY_WOOD_SHOVEL)));
    public static final DeferredItem<ShovelItem> STELLAR_STONE_SHOVEL = ITEMS.register(Keys.STELLAR_STONE_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.STELLAR_STONE, 1.5F, -3.0F, new Item.Properties().setId(Keys.STELLAR_STONE_SHOVEL)));
    public static final DeferredItem<ShovelItem> RUBY_SHOVEL = ITEMS.register(Keys.RUBY_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.RUBY, 1.5F, -3.0F, new Item.Properties().setId(Keys.RUBY_SHOVEL)));
    public static final DeferredItem<ShovelItem> AZURITE_SHOVEL = ITEMS.register(Keys.AZURITE_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.AZURITE, 1.5F, -3.0F, new Item.Properties().setId(Keys.AZURITE_SHOVEL)));
    public static final DeferredItem<ShovelItem> MAGMATIC_GEL_SHOVEL = ITEMS.register(Keys.MAGMATIC_GEL_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.MAGMATIC_GEL, 1.5F, -3.0F, new Item.Properties().setId(Keys.MAGMATIC_GEL_SHOVEL)));
    public static final DeferredItem<ShovelItem> OBSIDIAN_SHOVEL = ITEMS.register(Keys.OBSIDIAN_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Properties().setId(Keys.OBSIDIAN_SHOVEL).rarity(Rarity.EPIC)));
    public static final DeferredItem<ShovelItem> VOLUCITE_SHOVEL = ITEMS.register(Keys.VOLUCITE_SHOVEL.identifier().getPath(), () -> new EffectShovelItem(ToolMaterials.VOLUCITE, 1.5F, -3.0F, new Item.Properties().setId(Keys.VOLUCITE_SHOVEL).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<ShovelItem> LUNATIC_SHOVEL = ITEMS.register(Keys.LUNATIC_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.LUNATIC, 1.5F, -3.0F, new Item.Properties().setId(Keys.LUNATIC_SHOVEL).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<ShovelItem> ARSONIST_SHOVEL = ITEMS.register(Keys.ARSONIST_SHOVEL.identifier().getPath(), () -> new ShovelItem(ToolMaterials.ARSONIST, 1.5F, -3.0F, new Item.Properties().setId(Keys.ARSONIST_SHOVEL).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<ShovelItem> MAGMA_CUBE_SHOVEL = ITEMS.register(Keys.MAGMA_CUBE_SHOVEL.identifier().getPath(), () -> new EffectShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Properties().setId(Keys.MAGMA_CUBE_SHOVEL).rarity(AerialHellRarities.LEGENDARY.getValue())));

    public static final DeferredItem<AxeItem> SKY_WOOD_AXE = ITEMS.register(Keys.SKY_WOOD_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.SKY_WOOD, 6.0F, -3.1F, new Item.Properties().setId(Keys.SKY_WOOD_AXE)));
    public static final DeferredItem<AxeItem> STELLAR_STONE_AXE = ITEMS.register(Keys.STELLAR_STONE_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.STELLAR_STONE, 6.0F, -3.1F, new Item.Properties().setId(Keys.STELLAR_STONE_AXE)));
    public static final DeferredItem<AxeItem> RUBY_AXE = ITEMS.register(Keys.RUBY_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.RUBY, 6.0F, -3.1F, new Item.Properties().setId(Keys.RUBY_AXE)));
    public static final DeferredItem<AxeItem> AZURITE_AXE = ITEMS.register(Keys.AZURITE_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.AZURITE, 6.0F, -3.1F, new Item.Properties().setId(Keys.AZURITE_AXE)));
    public static final DeferredItem<AxeItem> MAGMATIC_GEL_AXE = ITEMS.register(Keys.MAGMATIC_GEL_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.MAGMATIC_GEL, 6.0F, -3.1F, new Item.Properties().setId(Keys.MAGMATIC_GEL_AXE)));
    public static final DeferredItem<AxeItem> OBSIDIAN_AXE = ITEMS.register(Keys.OBSIDIAN_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.OBSIDIAN, 6.0F, -3.1F, new Item.Properties().setId(Keys.OBSIDIAN_AXE).rarity(Rarity.EPIC)));
    public static final DeferredItem<AxeItem> VOLUCITE_AXE = ITEMS.register(Keys.VOLUCITE_AXE.identifier().getPath(), () -> new EffectAxeItem(ToolMaterials.VOLUCITE, 6.0F, -3.1F, new Item.Properties().setId(Keys.VOLUCITE_AXE).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<AxeItem> LUNATIC_AXE = ITEMS.register(Keys.LUNATIC_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.LUNATIC, 6.0F, -3.1F, new Item.Properties().setId(Keys.LUNATIC_AXE).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<AxeItem> ARSONIST_AXE = ITEMS.register(Keys.ARSONIST_AXE.identifier().getPath(), () -> new AxeItem(ToolMaterials.ARSONIST, 6.0F, -3.1F, new Item.Properties().setId(Keys.ARSONIST_AXE).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<AxeItem> HEAVY_AXE = ITEMS.register(Keys.HEAVY_AXE.identifier().getPath(), () -> new AerialHellAxeItem(ToolMaterials.HEAVY, 6.0F, -3.5F, -0.30F, 0.0F, new Item.Properties().setId(Keys.HEAVY_AXE).rarity(Rarity.EPIC)));
    public static final DeferredItem<AxeItem> AXE_OF_LIGHT = ITEMS.register(Keys.AXE_OF_LIGHT.identifier().getPath(), () -> new EffectAxeItem(ToolMaterials.LUNATIC, 5.0F, -3.1F, new Item.Properties().setId(Keys.AXE_OF_LIGHT).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<AxeItem> CURSED_AXE = ITEMS.register(Keys.CURSED_AXE.identifier().getPath(), () -> new AerialHellAxeItem(ToolMaterials.SHADOW, 2.0F, -3.2F, new Item.Properties().setId(Keys.CURSED_AXE).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<AxeItem> BERSERK_AXE = ITEMS.register(Keys.BERSERK_AXE.identifier().getPath(), () -> new BerserkAxeItem(ToolMaterials.ARSONIST, 4.0F, -2.5F, new Item.Properties().setId(Keys.BERSERK_AXE).rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<HoeItem> SKY_WOOD_HOE = ITEMS.register(Keys.SKY_WOOD_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.SKY_WOOD, 0.0F, -3.0F, new Item.Properties().setId(Keys.SKY_WOOD_HOE)));
    public static final DeferredItem<HoeItem> STELLAR_STONE_HOE = ITEMS.register(Keys.STELLAR_STONE_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.STELLAR_STONE, 0.0F, -3.0F, new Item.Properties().setId(Keys.STELLAR_STONE_HOE)));
    public static final DeferredItem<HoeItem> RUBY_HOE = ITEMS.register(Keys.RUBY_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.RUBY, 0.0F, -3.0F, new Item.Properties().setId(Keys.RUBY_HOE)));
    public static final DeferredItem<HoeItem> AZURITE_HOE = ITEMS.register(Keys.AZURITE_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.AZURITE, 0.0F, -3.0F, new Item.Properties().setId(Keys.AZURITE_HOE)));
    public static final DeferredItem<HoeItem> MAGMATIC_GEL_HOE = ITEMS.register(Keys.MAGMATIC_GEL_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.MAGMATIC_GEL, 0.0F, -3.0F, new Item.Properties().setId(Keys.MAGMATIC_GEL_HOE)));
    public static final DeferredItem<HoeItem> OBSIDIAN_HOE = ITEMS.register(Keys.OBSIDIAN_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.OBSIDIAN, 0.0F, -3.0F, new Item.Properties().setId(Keys.OBSIDIAN_HOE).rarity(Rarity.EPIC)));
    public static final DeferredItem<HoeItem> VOLUCITE_HOE = ITEMS.register(Keys.VOLUCITE_HOE.identifier().getPath(), () -> new EffectHoeItem(ToolMaterials.VOLUCITE, 0.0F, -3.0F, new Item.Properties().setId(Keys.VOLUCITE_HOE).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<HoeItem> LUNATIC_HOE = ITEMS.register(Keys.LUNATIC_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.LUNATIC, 0.0F, -1.0F, new Item.Properties().setId(Keys.LUNATIC_HOE).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<HoeItem> ARSONIST_HOE = ITEMS.register(Keys.ARSONIST_HOE.identifier().getPath(), () -> new HoeItem(ToolMaterials.ARSONIST, 0.0F, -1.0F, new Item.Properties().setId(Keys.ARSONIST_HOE).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<HoeItem> REAPER_SCYTHE = ITEMS.register(Keys.REAPER_SCYTHE.identifier().getPath(), () -> new EffectHoeItem(ToolMaterials.SHADOW, -2.8F, 4.0F, new Item.Properties().setId(Keys.REAPER_SCYTHE).rarity(AerialHellRarities.MYTHICAL.getValue())));

    //weapons
    public static final DeferredItem<Item> SKY_WOOD_SWORD = ITEMS.register(Keys.SKY_WOOD_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.SKY_WOOD_SWORD).sword(ToolMaterials.SKY_WOOD, 3, -2.4F)));
    public static final DeferredItem<Item> STELLAR_STONE_SWORD = ITEMS.register(Keys.STELLAR_STONE_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.STELLAR_STONE_SWORD).sword(ToolMaterials.STELLAR_STONE, 3, -2.4F)));
    public static final DeferredItem<Item> RUBY_SWORD = ITEMS.register(Keys.RUBY_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_SWORD).sword(ToolMaterials.RUBY, 3, -2.4F)));
    public static final DeferredItem<Item> AZURITE_SWORD = ITEMS.register(Keys.AZURITE_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_SWORD).sword(ToolMaterials.AZURITE, 3, -2.4F)));
    public static final DeferredItem<Item> MAGMATIC_GEL_SWORD = ITEMS.register(Keys.MAGMATIC_GEL_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_SWORD).sword(ToolMaterials.MAGMATIC_GEL, 3, -2.4F)));
    public static final DeferredItem<Item> OBSIDIAN_SWORD = ITEMS.register(Keys.OBSIDIAN_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_SWORD).sword(ToolMaterials.OBSIDIAN, 3, -2.4F).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> VOLUCITE_SWORD = ITEMS.register(Keys.VOLUCITE_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.VOLUCITE, 3, -2.4F, new Item.Properties().setId(Keys.VOLUCITE_SWORD).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> LUNATIC_SWORD = ITEMS.register(Keys.LUNATIC_SWORD.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_SWORD).sword(ToolMaterials.LUNATIC, 3, -2.4F).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> ARSONIST_SWORD = ITEMS.register(Keys.ARSONIST_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Properties().setId(Keys.ARSONIST_SWORD).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<Item> HEAVY_SWORD = ITEMS.register(Keys.HEAVY_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.HEAVY, 3, -2.7F, -0.30F, 0.0F, new Item.Properties().setId(Keys.HEAVY_SWORD).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> HEALTH_BOOST_SWORD = ITEMS.register(Keys.HEALTH_BOOST_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 3, -2.4F, 0.0F, 4.0F, new Item.Properties().setId(Keys.HEALTH_BOOST_SWORD).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> NINJA_SWORD = ITEMS.register(Keys.NINJA_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 2, -1.6F, 0.15F, 0.0F, new Item.Properties().setId(Keys.NINJA_SWORD).fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> NINJA_MASTER_SWORD = ITEMS.register(Keys.NINJA_MASTER_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 4, -2.4F, 0.15F, 0.0F, new Item.Properties().setId(Keys.NINJA_MASTER_SWORD).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> GLOUTON_SWORD = ITEMS.register(Keys.GLOUTON_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.RUBY, 3, -2.4F, new Item.Properties().setId(Keys.GLOUTON_SWORD).fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> RANDOM_SWORD = ITEMS.register(Keys.RANDOM_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.RUBY, 2, -2.4F, new Item.Properties().setId(Keys.RANDOM_SWORD).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> DISLOYAL_SWORD = ITEMS.register(Keys.DISLOYAL_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setId(Keys.DISLOYAL_SWORD).fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> CURSED_SWORD = ITEMS.register(Keys.CURSED_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.SHADOW, 1, -2.5F, new Item.Properties().setId(Keys.CURSED_SWORD).fireResistant().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> ABSOLUTE_ZERO_SWORD = ITEMS.register(Keys.ABSOLUTE_ZERO_SWORD.identifier().getPath(), () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setId(Keys.ABSOLUTE_ZERO_SWORD).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> SWORD_OF_LIGHT = ITEMS.register(Keys.SWORD_OF_LIGHT.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setId(Keys.SWORD_OF_LIGHT).fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> ANTIDOTE_SWORD = ITEMS.register(Keys.ANTIDOTE_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.RUBY, 4, -2.4F, new Item.Properties().setId(Keys.ANTIDOTE_SWORD).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> NETHERIAN_KING_SWORD = ITEMS.register(Keys.NETHERIAN_KING_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 1, -2.4F, new Item.Properties().setId(Keys.NETHERIAN_KING_SWORD).fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> GLASS_CANON_SWORD = ITEMS.register(Keys.GLASS_CANON_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.ARSONIST, 7, -1.6F, new Item.Properties().setId(Keys.GLASS_CANON_SWORD).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> GOD_SWORD = ITEMS.register(Keys.GOD_SWORD.identifier().getPath(), () -> new EffectSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Properties().setId(Keys.GOD_SWORD).fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<Item> FORGOTTEN_BATTLE_TRIDENT = ITEMS.register(Keys.FORGOTTEN_BATTLE_TRIDENT.identifier().getPath(), () -> new ForgottenBattleTridentItem(ToolMaterials.VOLUCITE, 3, -2.9F, 0.2F, 0.0F, new Item.Properties().setId(Keys.FORGOTTEN_BATTLE_TRIDENT).durability(1000).rarity(AerialHellRarities.LEGENDARY.getValue())));

    //armor
    public static final DeferredItem<Item> RUBY_HELMET = ITEMS.register(Keys.RUBY_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_HELMET).humanoidArmor(AerialHellArmorMaterials.RUBY, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(15))));
    public static final DeferredItem<Item> RUBY_CHESTPLATE = ITEMS.register(Keys.RUBY_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.RUBY, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(15))));
    public static final DeferredItem<Item> RUBY_LEGGINGS = ITEMS.register(Keys.RUBY_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.RUBY, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(15))));
    public static final DeferredItem<Item> RUBY_BOOTS = ITEMS.register(Keys.RUBY_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.RUBY_BOOTS).humanoidArmor(AerialHellArmorMaterials.RUBY, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(15))));

    public static final DeferredItem<Item> AZURITE_HELMET = ITEMS.register(Keys.AZURITE_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_HELMET).humanoidArmor(AerialHellArmorMaterials.AZURITE, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(10))));
    public static final DeferredItem<Item> AZURITE_CHESTPLATE = ITEMS.register(Keys.AZURITE_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.AZURITE, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(10))));
    public static final DeferredItem<Item> AZURITE_LEGGINGS = ITEMS.register(Keys.AZURITE_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.AZURITE, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(10))));
    public static final DeferredItem<Item> AZURITE_BOOTS = ITEMS.register(Keys.AZURITE_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.AZURITE_BOOTS).humanoidArmor(AerialHellArmorMaterials.AZURITE, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(10))));

    public static final DeferredItem<Item> OBSIDIAN_HELMET = ITEMS.register(Keys.OBSIDIAN_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_HELMET).humanoidArmor(AerialHellArmorMaterials.OBSIDIAN, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(37)).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> OBSIDIAN_CHESTPLATE = ITEMS.register(Keys.OBSIDIAN_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.OBSIDIAN, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(37)).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> OBSIDIAN_LEGGINGS = ITEMS.register(Keys.OBSIDIAN_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.OBSIDIAN, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(37)).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> OBSIDIAN_BOOTS = ITEMS.register(Keys.OBSIDIAN_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.OBSIDIAN_BOOTS).humanoidArmor(AerialHellArmorMaterials.OBSIDIAN, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(37)).rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> VOLUCITE_HELMET = ITEMS.register(Keys.VOLUCITE_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VOLUCITE_HELMET).humanoidArmor(AerialHellArmorMaterials.VOLUCITE, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(35)).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> VOLUCITE_CHESTPLATE = ITEMS.register(Keys.VOLUCITE_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VOLUCITE_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.VOLUCITE, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(35)).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> VOLUCITE_LEGGINGS = ITEMS.register(Keys.VOLUCITE_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VOLUCITE_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.VOLUCITE, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(35)).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> VOLUCITE_BOOTS = ITEMS.register(Keys.VOLUCITE_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.VOLUCITE_BOOTS).humanoidArmor(AerialHellArmorMaterials.VOLUCITE, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(35)).rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<Item> MAGMATIC_GEL_HELMET = ITEMS.register(Keys.MAGMATIC_GEL_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_HELMET).humanoidArmor(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(7))));
    public static final DeferredItem<Item> MAGMATIC_GEL_CHESTPLATE = ITEMS.register(Keys.MAGMATIC_GEL_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(7))));
    public static final DeferredItem<Item> MAGMATIC_GEL_LEGGINGS = ITEMS.register(Keys.MAGMATIC_GEL_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(7))));
    public static final DeferredItem<Item> MAGMATIC_GEL_BOOTS = ITEMS.register(Keys.MAGMATIC_GEL_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.MAGMATIC_GEL_BOOTS).humanoidArmor(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(7))));

    public static final DeferredItem<Item> LUNATIC_HELMET = ITEMS.register(Keys.LUNATIC_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_HELMET).humanoidArmor(AerialHellArmorMaterials.LUNATIC, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(33)).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> LUNATIC_CHESTPLATE = ITEMS.register(Keys.LUNATIC_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.LUNATIC, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(33)).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> LUNATIC_LEGGINGS = ITEMS.register(Keys.LUNATIC_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.LUNATIC, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(33)).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> LUNATIC_BOOTS = ITEMS.register(Keys.LUNATIC_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.LUNATIC_BOOTS).humanoidArmor(AerialHellArmorMaterials.LUNATIC, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(33)).rarity(AerialHellRarities.LEGENDARY.getValue())));

    public static final DeferredItem<Item> ARSONIST_HELMET = ITEMS.register(Keys.ARSONIST_HELMET.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_HELMET).humanoidArmor(AerialHellArmorMaterials.ARSONIST, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(37)).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<Item> ARSONIST_CHESTPLATE = ITEMS.register(Keys.ARSONIST_CHESTPLATE.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.ARSONIST, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(37)).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<Item> ARSONIST_LEGGINGS = ITEMS.register(Keys.ARSONIST_LEGGINGS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.ARSONIST, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(37)).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<Item> ARSONIST_BOOTS = ITEMS.register(Keys.ARSONIST_BOOTS.identifier().getPath(), () -> new Item(new Item.Properties().setId(Keys.ARSONIST_BOOTS).humanoidArmor(AerialHellArmorMaterials.ARSONIST, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(37)).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));

    public static final DeferredItem<Item> SHADOW_HELMET = ITEMS.register(Keys.SHADOW_HELMET.identifier().getPath(), () -> new ShadowArmorItem(new Item.Properties().setId(Keys.SHADOW_HELMET).humanoidArmor(AerialHellArmorMaterials.SHADOW, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(25)).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> SHADOW_CHESTPLATE = ITEMS.register(Keys.SHADOW_CHESTPLATE.identifier().getPath(), () -> new ShadowArmorItem(new Item.Properties().setId(Keys.SHADOW_CHESTPLATE).humanoidArmor(AerialHellArmorMaterials.SHADOW, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(25)).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> SHADOW_LEGGINGS = ITEMS.register(Keys.SHADOW_LEGGINGS.identifier().getPath(), () -> new ShadowArmorItem(new Item.Properties().setId(Keys.SHADOW_LEGGINGS).humanoidArmor(AerialHellArmorMaterials.SHADOW, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(25)).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> SHADOW_BOOTS = ITEMS.register(Keys.SHADOW_BOOTS.identifier().getPath(), () -> new ShadowArmorItem(new Item.Properties().setId(Keys.SHADOW_BOOTS).humanoidArmor(AerialHellArmorMaterials.SHADOW, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(25)).rarity(AerialHellRarities.CORRUPTED.getValue())));

    //effect totems
    public static final DeferredItem<Item> REGENERATION_TOTEM = ITEMS.register(Keys.REGENERATION_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.REGENERATION_TOTEM).stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> SPEED_TOTEM = ITEMS.register(Keys.SPEED_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.SPEED_TOTEM).stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> SPEED_II_TOTEM = ITEMS.register(Keys.SPEED_II_TOTEM.identifier().getPath(), () -> new EnchantedEffectTotemItem(new Item.Properties().setId(Keys.SPEED_II_TOTEM).stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> NIGHT_VISION_TOTEM = ITEMS.register(Keys.NIGHT_VISION_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.NIGHT_VISION_TOTEM).stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> AGILITY_TOTEM = ITEMS.register(Keys.AGILITY_TOTEM.identifier().getPath(), () -> new EnchantedEffectTotemItem(new Item.Properties().setId(Keys.AGILITY_TOTEM).stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> HERO_TOTEM = ITEMS.register(Keys.HERO_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.HERO_TOTEM).stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> HEAD_IN_THE_CLOUDS_TOTEM = ITEMS.register(Keys.HEAD_IN_THE_CLOUDS_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.HEAD_IN_THE_CLOUDS_TOTEM).stacksTo(1).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> GOD_TOTEM = ITEMS.register(Keys.GOD_TOTEM.identifier().getPath(), () -> new EnchantedEffectTotemItem(new Item.Properties().setId(Keys.GOD_TOTEM).stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<Item> CURSED_TOTEM = ITEMS.register(Keys.CURSED_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.CURSED_TOTEM).stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> SHADOW_TOTEM = ITEMS.register(Keys.SHADOW_TOTEM.identifier().getPath(), () -> new EffectTotemItem(new Item.Properties().setId(Keys.SHADOW_TOTEM).stacksTo(1).rarity(AerialHellRarities.CORRUPTED.getValue())));

    //spawn eggs
    public static final DeferredItem<Item> STELLAR_STONE_AUTOMATON_SPAWN_EGG = ITEMS.register(Keys.STELLAR_STONE_AUTOMATON_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), new Item.Properties().setId(Keys.STELLAR_STONE_AUTOMATON_SPAWN_EGG).rarity(AerialHellRarities.VIBRANT.getValue()), false));
    public static final DeferredItem<Item> EVIL_COW_SPAWN_EGG = ITEMS.register(Keys.EVIL_COW_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.EVIL_COW.get(), new Item.Properties().setId(Keys.EVIL_COW_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CORTINARIUS_COW_SPAWN_EGG = ITEMS.register(Keys.CORTINARIUS_COW_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CORTINARIUS_COW.get(), new Item.Properties().setId(Keys.CORTINARIUS_COW_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_ENT_SPAWN_EGG = ITEMS.register(Keys.STELLAR_ENT_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_ENT.get(), new Item.Properties().setId(Keys.STELLAR_ENT_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> VENOMOUS_SNAKE_SPAWN_EGG = ITEMS.register(Keys.VENOMOUS_SNAKE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.VENOMOUS_SNAKE.get(), new Item.Properties().setId(Keys.VENOMOUS_SNAKE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> WORM_SPAWN_EGG = ITEMS.register(Keys.WORM_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.WORM.get(), new Item.Properties().setId(Keys.WORM_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_CHICKEN_SPAWN_EGG = ITEMS.register(Keys.STELLAR_CHICKEN_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_CHICKEN.get(), new Item.Properties().setId(Keys.STELLAR_CHICKEN_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_BOAR_SPAWN_EGG = ITEMS.register(Keys.STELLAR_BOAR_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_BOAR.get(), new Item.Properties().setId(Keys.STELLAR_BOAR_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SHROOMBOOM_SPAWN_EGG = ITEMS.register(Keys.SHROOMBOOM_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHROOMBOOM.get(), new Item.Properties().setId(Keys.SHROOMBOOM_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> VERDIGRIS_ZOMBIE_SPAWN_EGG = ITEMS.register(Keys.VERDIGRIS_ZOMBIE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.VERDIGRIS_ZOMBIE.get(), new Item.Properties().setId(Keys.VERDIGRIS_ZOMBIE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUMMY_SPAWN_EGG = ITEMS.register(Keys.MUMMY_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUMMY.get(), new Item.Properties().setId(Keys.MUMMY_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SLIME_PIRATE_SPAWN_EGG = ITEMS.register(Keys.SLIME_PIRATE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SLIME_PIRATE.get(), new Item.Properties().setId(Keys.SLIME_PIRATE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register(Keys.SLIME_NINJA_PIRATE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SLIME_NINJA_PIRATE.get(), new Item.Properties().setId(Keys.SLIME_NINJA_PIRATE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GHOST_SLIME_PIRATE_SPAWN_EGG = ITEMS.register(Keys.GHOST_SLIME_PIRATE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GHOST_SLIME_PIRATE.get(), new Item.Properties().setId(Keys.GHOST_SLIME_PIRATE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register(Keys.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get(), new Item.Properties().setId(Keys.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SANDY_SHEEP_SPAWN_EGG = ITEMS.register(Keys.SANDY_SHEEP_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SANDY_SHEEP.get(), new Item.Properties().setId(Keys.SANDY_SHEEP_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GLIDING_TURTLE_SPAWN_EGG = ITEMS.register(Keys.GLIDING_TURTLE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GLIDING_TURTLE.get(), new Item.Properties().setId(Keys.GLIDING_TURTLE_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> FAT_PHANTOM_SPAWN_EGG = ITEMS.register(Keys.FAT_PHANTOM_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FAT_PHANTOM.get(), new Item.Properties().setId(Keys.FAT_PHANTOM_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> KODAMA_SPAWN_EGG = ITEMS.register(Keys.KODAMA_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.KODAMA.get(), new Item.Properties().setId(Keys.KODAMA_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUD_GOLEM_SPAWN_EGG = ITEMS.register(Keys.MUD_GOLEM_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_GOLEM.get(), new Item.Properties().setId(Keys.MUD_GOLEM_SPAWN_EGG).rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> MUD_SOLDIER_SPAWN_EGG = ITEMS.register(Keys.MUD_SOLDIER_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_SOLDIER.get(), new Item.Properties().setId(Keys.MUD_SOLDIER_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUD_CYCLE_MAGE_SPAWN_EGG = ITEMS.register(Keys.MUD_CYCLE_MAGE_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_CYCLE_MAGE.get(), new Item.Properties().setId(Keys.MUD_CYCLE_MAGE_SPAWN_EGG).rarity(AerialHellRarities.LEGENDARY.getValue()), true));
    public static final DeferredItem<Item> HELL_SPIDER_SPAWN_EGG = ITEMS.register(Keys.HELL_SPIDER_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.HELL_SPIDER.get(), new Item.Properties().setId(Keys.HELL_SPIDER_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_GOLEM_SPAWN_EGG = ITEMS.register(Keys.CRYSTAL_GOLEM_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_GOLEM.get(), new Item.Properties().setId(Keys.CRYSTAL_GOLEM_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_SLIME_SPAWN_EGG = ITEMS.register(Keys.CRYSTAL_SLIME_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_SLIME.get(), new Item.Properties().setId(Keys.CRYSTAL_SLIME_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_SPIDER_SPAWN_EGG = ITEMS.register(Keys.CRYSTAL_SPIDER_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_SPIDER.get(), new Item.Properties().setId(Keys.CRYSTAL_SPIDER_SPAWN_EGG).rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> LUNATIC_PRIEST_SPAWN_EGG = ITEMS.register(Keys.LUNATIC_PRIEST_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.LUNATIC_PRIEST.get(), new Item.Properties().setId(Keys.LUNATIC_PRIEST_SPAWN_EGG).rarity(AerialHellRarities.MYTHICAL.getValue()), true));
    public static final DeferredItem<Item> CRYSTAL_CATERPILLAR_SPAWN_EGG = ITEMS.register(Keys.CRYSTAL_CATERPILLAR_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_CATERPILLAR.get(), new Item.Properties().setId(Keys.CRYSTAL_CATERPILLAR_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> FOREST_CATERPILLAR_SPAWN_EGG = ITEMS.register(Keys.FOREST_CATERPILLAR_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FOREST_CATERPILLAR.get(), new Item.Properties().setId(Keys.FOREST_CATERPILLAR_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> TORN_SPIRIT_SPAWN_EGG = ITEMS.register(Keys.TORN_SPIRIT_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.TORN_SPIRIT.get(), new Item.Properties().setId(Keys.TORN_SPIRIT_SPAWN_EGG).rarity(Rarity.EPIC), false));
    public static final DeferredItem<Item> CHAINED_GOD_SPAWN_EGG = ITEMS.register(Keys.CHAINED_GOD_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CHAINED_GOD.get(), new Item.Properties().setId(Keys.CHAINED_GOD_SPAWN_EGG).rarity(AerialHellRarities.MYTHICAL.getValue()), true));
    public static final DeferredItem<Item> ICE_SPIRIT_SPAWN_EGG = ITEMS.register(Keys.ICE_SPIRIT_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.ICE_SPIRIT.get(), new Item.Properties().setId(Keys.ICE_SPIRIT_SPAWN_EGG).rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> FIRE_SPIRIT_SPAWN_EGG = ITEMS.register(Keys.FIRE_SPIRIT_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FIRE_SPIRIT.get(), new Item.Properties().setId(Keys.FIRE_SPIRIT_SPAWN_EGG).rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> ELECTRO_SPIRIT_SPAWN_EGG = ITEMS.register(Keys.ELECTRO_SPIRIT_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.ELECTRO_SPIRIT.get(), new Item.Properties().setId(Keys.ELECTRO_SPIRIT_SPAWN_EGG).rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> FLYING_JELLYFISH_SPAWN_EGG = ITEMS.register(Keys.FLYING_JELLYFISH_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FLYING_JELLYFISH.get(), new Item.Properties().setId(Keys.FLYING_JELLYFISH_SPAWN_EGG).rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SHADOW_FLYING_SKULL_SPAWN_EGG = ITEMS.register(Keys.SHADOW_FLYING_SKULL_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_FLYING_SKULL.get(), new Item.Properties().setId(Keys.SHADOW_FLYING_SKULL_SPAWN_EGG).rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_TROLL_SPAWN_EGG = ITEMS.register(Keys.SHADOW_TROLL_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_TROLL.get(), new Item.Properties().setId(Keys.SHADOW_TROLL_SPAWN_EGG).rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_AUTOMATON_SPAWN_EGG = ITEMS.register(Keys.SHADOW_AUTOMATON_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_AUTOMATON.get(), new Item.Properties().setId(Keys.SHADOW_AUTOMATON_SPAWN_EGG).rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_SPIDER_SPAWN_EGG = ITEMS.register(Keys.SHADOW_SPIDER_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_SPIDER.get(), new Item.Properties().setId(Keys.SHADOW_SPIDER_SPAWN_EGG).rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> LILITH_SPAWN_EGG = ITEMS.register(Keys.LILITH_SPAWN_EGG.identifier().getPath(), () -> new AerialHellSpawnEgg(() -> AerialHellEntities.LILITH.get(), new Item.Properties().setId(Keys.LILITH_SPAWN_EGG).rarity(AerialHellRarities.CORRUPTED.getValue()), true));

    public static final DeferredItem<Item> AERIAL_HELL_PAINTING = ITEMS.register(Keys.AERIAL_HELL_PAINTING.identifier().getPath(), () -> new AerialHellHangingEntityItem(() -> AerialHellEntities.AERIAL_HELL_PAINTING.get(), new Item.Properties().setId(Keys.AERIAL_HELL_PAINTING)));

    //build items
    public static final DeferredItem<Item> BLOCK_UPDATER = ITEMS.register(Keys.BLOCK_UPDATER.identifier().getPath(), () -> new BlockUpdaterItem(new Item.Properties().setId(Keys.BLOCK_UPDATER)));
    public static final DeferredItem<Item> BLOCK_CRACKER = ITEMS.register(Keys.BLOCK_CRACKER.identifier().getPath(), () -> new BlockCrackerItem(new Item.Properties().setId(Keys.BLOCK_CRACKER)));
    public static final DeferredItem<Item> STRUCTURE_VOID_PLACER = ITEMS.register(Keys.STRUCTURE_VOID_PLACER.identifier().getPath(), () -> new StructureVoidPlacerItem(new Item.Properties().setId(Keys.STRUCTURE_VOID_PLACER)));

    public static class Keys
    {
        //portal
        public static final ResourceKey<Item> STELLAR_PORTAL_FRAME_BLOCK = createKey("stellar_portal_frame_block");
        public static final ResourceKey<Item> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
        public static final ResourceKey<Item> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = createKey("deepslate_stellar_portal_frame_ore");
        public static final ResourceKey<Item> STELLAR_PORTAL_FRAME_BRICK = createKey("stellar_portal_frame_brick");
        public static final ResourceKey<Item> STELLAR_LIGHTER = createKey("stellar_lighter");

        //torch
        public static final ResourceKey<Item> CRYSTALLIZED_TORCH = createKey("crystallized_torch");
        public static final ResourceKey<Item> FLUORITE_TORCH = createKey("fluorite_torch");
        public static final ResourceKey<Item> VOLUCITE_TORCH = createKey("volucite_torch");
        public static final ResourceKey<Item> SHADOW_TORCH = createKey("shadow_torch");

        //lanterns
        public static final ResourceKey<Item> CRYSTALLIZED_LANTERN = createKey("crystallized_lantern");
        public static final ResourceKey<Item> FLUORITE_LANTERN = createKey("fluorite_lantern");
        public static final ResourceKey<Item> RUBY_LANTERN = createKey("ruby_lantern");
        public static final ResourceKey<Item> RUBY_CRYSTALLIZED_LANTERN = createKey("ruby_crystallized_lantern");
        public static final ResourceKey<Item> RUBY_FLUORITE_LANTERN = createKey("ruby_fluorite_lantern");
        public static final ResourceKey<Item> VOLUCITE_LANTERN = createKey("volucite_lantern");
        public static final ResourceKey<Item> VOLUCITE_CRYSTALLIZED_LANTERN = createKey("volucite_crystallized_lantern");
        public static final ResourceKey<Item> VOLUCITE_FLUORITE_LANTERN = createKey("volucite_fluorite_lantern");
        public static final ResourceKey<Item> LUNATIC_LANTERN = createKey("lunatic_lantern");
        public static final ResourceKey<Item> SHADOW_LANTERN = createKey("shadow_lantern");

        //chains
        public static final ResourceKey<Item> RUBY_CHAIN = createKey("ruby_chain");
        public static final ResourceKey<Item> VOLUCITE_CHAIN = createKey("volucite_chain");
        public static final ResourceKey<Item> LUNATIC_CHAIN = createKey("lunatic_chain");
        public static final ResourceKey<Item> SHADOW_CHAIN = createKey("shadow_chain");

        //grass & dirt
        public static final ResourceKey<Item> STELLAR_GRASS_BLOCK = createKey("stellar_grass_block");
        public static final ResourceKey<Item> CHISELED_STELLAR_GRASS_BLOCK = createKey("chiseled_stellar_grass_block");
        public static final ResourceKey<Item> STELLAR_DIRT = createKey("stellar_dirt");
        public static final ResourceKey<Item> STELLAR_COARSE_DIRT = createKey("stellar_coarse_dirt");
        public static final ResourceKey<Item> STELLAR_FARMLAND = createKey("stellar_farmland");
        public static final ResourceKey<Item> STELLAR_DIRT_PATH = createKey("stellar_dirt_path");
        public static final ResourceKey<Item> STELLAR_PODZOL = createKey("stellar_podzol");
        public static final ResourceKey<Item> STELLAR_CRYSTAL_PODZOL = createKey("stellar_crystal_podzol");
        public static final ResourceKey<Item> CHISELED_STELLAR_DIRT = createKey("chiseled_stellar_dirt");
        public static final ResourceKey<Item> SHADOW_GRASS_BLOCK = createKey("shadow_grass_block");

        //slippery sand
        public static final ResourceKey<Item> SLIPPERY_SAND = createKey("slippery_sand");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE = createKey("slippery_sand_stone");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS = createKey("slippery_sand_stone_bricks");
        public static final ResourceKey<Item> CUT_SLIPPERY_SAND_STONE = createKey("cut_slippery_sand_stone");
        public static final ResourceKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS = createKey("cracked_slippery_sand_stone_bricks");

        //giant root
        public static final ResourceKey<Item> GIANT_ROOT = createKey("giant_root");

        //aerial_tree
        public static final ResourceKey<Item> AERIAL_TREE_LOG = createKey("aerial_tree_log");
        public static final ResourceKey<Item> STRIPPED_AERIAL_TREE_LOG = createKey("stripped_aerial_tree_log");
        public static final ResourceKey<Item> AERIAL_TREE_WOOD = createKey("aerial_tree_wood");
        public static final ResourceKey<Item> STRIPPED_AERIAL_TREE_WOOD = createKey("stripped_aerial_tree_wood");
        public static final ResourceKey<Item> AERIAL_TREE_LEAVES = createKey("aerial_tree_leaves");
        public static final ResourceKey<Item> AERIAL_TREE_PLANKS = createKey("aerial_tree_planks");
        public static final ResourceKey<Item> CHISELED_AERIAL_TREE_PLANKS = createKey("chiseled_aerial_tree_planks");
        public static final ResourceKey<Item> AERIAL_TREE_BOOKSHELF = createKey("aerial_tree_bookshelf");
        public static final ResourceKey<Item> AERIAL_TREE_SAPLING = createKey("aerial_tree_sapling");

        //petrified aerial tree
        public static final ResourceKey<Item> PETRIFIED_AERIAL_TREE_LOG = createKey("petrified_aerial_tree_log");

        //golden beech
        public static final ResourceKey<Item> GOLDEN_BEECH_LOG = createKey("golden_beech_log");
        public static final ResourceKey<Item> STRIPPED_GOLDEN_BEECH_LOG = createKey("stripped_golden_beech_log");
        public static final ResourceKey<Item> GOLDEN_BEECH_WOOD = createKey("golden_beech_wood");
        public static final ResourceKey<Item> STRIPPED_GOLDEN_BEECH_WOOD = createKey("stripped_golden_beech_wood");
        public static final ResourceKey<Item> GOLDEN_BEECH_PLANKS = createKey("golden_beech_planks");
        public static final ResourceKey<Item> CHISELED_GOLDEN_BEECH_PLANKS = createKey("chiseled_golden_beech_planks");
        public static final ResourceKey<Item> GOLDEN_BEECH_LEAVES = createKey("golden_beech_leaves");
        public static final ResourceKey<Item> GOLDEN_BEECH_BOOKSHELF = createKey("golden_beech_bookshelf");
        public static final ResourceKey<Item> GOLDEN_BEECH_SAPLING = createKey("golden_beech_sapling");

        //cropper pine
        public static final ResourceKey<Item> COPPER_PINE_LOG = createKey("copper_pine_log");
        public static final ResourceKey<Item> STRIPPED_COPPER_PINE_LOG = createKey("stripped_copper_pine_log");
        public static final ResourceKey<Item> COPPER_PINE_WOOD = createKey("copper_pine_wood");
        public static final ResourceKey<Item> STRIPPED_COPPER_PINE_WOOD = createKey("stripped_copper_pine_wood");
        public static final ResourceKey<Item> COPPER_PINE_PLANKS = createKey("copper_pine_planks");
        public static final ResourceKey<Item> COPPER_PINE_LEAVES = createKey("copper_pine_leaves");
        public static final ResourceKey<Item> COPPER_PINE_BOOKSHELF = createKey("copper_pine_bookshelf");
        public static final ResourceKey<Item> COPPER_PINE_SAPLING = createKey("copper_pine_sapling");

        //lapis robinia
        public static final ResourceKey<Item> LAPIS_ROBINIA_LOG = createKey("lapis_robinia_log");
        public static final ResourceKey<Item> ENCHANTED_LAPIS_ROBINIA_LOG = createKey("enchanted_lapis_robinia_log");
        public static final ResourceKey<Item> STRIPPED_LAPIS_ROBINIA_LOG = createKey("stripped_lapis_robinia_log");
        public static final ResourceKey<Item> LAPIS_ROBINIA_WOOD = createKey("lapis_robinia_wood");
        public static final ResourceKey<Item> STRIPPED_LAPIS_ROBINIA_WOOD = createKey("stripped_lapis_robinia_wood");
        public static final ResourceKey<Item> LAPIS_ROBINIA_LEAVES = createKey("lapis_robinia_leaves");
        public static final ResourceKey<Item> LAPIS_ROBINIA_PLANKS = createKey("lapis_robinia_planks");
        public static final ResourceKey<Item> LAPIS_ROBINIA_BOOKSHELF = createKey("lapis_robinia_bookshelf");
        public static final ResourceKey<Item> LAPIS_ROBINIA_SAPLING = createKey("lapis_robinia_sapling");

        //shadow_pine
        public static final ResourceKey<Item> SHADOW_PINE_LOG = createKey("shadow_pine_log");
        public static final ResourceKey<Item> EYE_SHADOW_PINE_LOG = createKey("eye_shadow_pine_log");
        public static final ResourceKey<Item> STRIPPED_SHADOW_PINE_LOG = createKey("stripped_shadow_pine_log");
        public static final ResourceKey<Item> SHADOW_PINE_WOOD = createKey("shadow_pine_wood");
        public static final ResourceKey<Item> STRIPPED_SHADOW_PINE_WOOD = createKey("stripped_shadow_pine_wood");
        public static final ResourceKey<Item> SHADOW_PINE_LEAVES = createKey("shadow_pine_leaves");
        public static final ResourceKey<Item> PURPLE_SHADOW_PINE_LEAVES = createKey("purple_shadow_pine_leaves");
        public static final ResourceKey<Item> SHADOW_PINE_PLANKS = createKey("shadow_pine_planks");
        public static final ResourceKey<Item> SHADOW_PINE_BOOKSHELF = createKey("shadow_pine_bookshelf");
        public static final ResourceKey<Item> SHADOW_PINE_SAPLING = createKey("shadow_pine_sapling");
        public static final ResourceKey<Item> PURPLE_SHADOW_PINE_SAPLING = createKey("purple_shadow_pine_sapling");

        //stellar jungle tree
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_LOG = createKey("stellar_jungle_tree_log");
        public static final ResourceKey<Item> STRIPPED_STELLAR_JUNGLE_TREE_LOG = createKey("stripped_stellar_jungle_tree_log");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_WOOD = createKey("stellar_jungle_tree_wood");
        public static final ResourceKey<Item> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = createKey("stripped_stellar_jungle_tree_wood");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_LEAVES = createKey("stellar_jungle_tree_leaves");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_PLANKS = createKey("stellar_jungle_tree_planks");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_BOOKSHELF = createKey("stellar_jungle_tree_bookshelf");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_SAPLING = createKey("stellar_jungle_tree_sapling");
        public static final ResourceKey<Item> DEAD_STELLAR_JUNGLE_TREE_LOG = createKey("dead_stellar_jungle_tree_log");

        //shroom
        public static final ResourceKey<Item> GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("giant_cortinarius_violaceus_stem");
        public static final ResourceKey<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("stripped_giant_cortinarius_violaceus_stem");
        public static final ResourceKey<Item> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("giant_cortinarius_violaceus_bark_stem");
        public static final ResourceKey<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("stripped_giant_cortinarius_violaceus_bark_stem");
        public static final ResourceKey<Item> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = createKey("giant_cortinarius_violaceus_cap_block");
        public static final ResourceKey<Item> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = createKey("giant_cortinarius_violaceus_light");
        public static final ResourceKey<Item> CORTINARIUS_VIOLACEUS = createKey("cortinarius_violaceus");
        public static final ResourceKey<Item> GLOWING_BOLETUS = createKey("glowing_boletus");
        public static final ResourceKey<Item> TALL_GLOWING_BOLETUS = createKey("tall_glowing_boletus");
        public static final ResourceKey<Item> BLUE_MEANIE_CLUSTER = createKey("blue_meanie_cluster");
        public static final ResourceKey<Item> GIANT_ROOT_SHROOM = createKey("giant_root_shroom");

        public static final ResourceKey<Item> GIANT_VERDIGRIS_AGARIC_STEM = createKey("giant_verdigris_agaric_stem");
        public static final ResourceKey<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = createKey("stripped_giant_verdigris_agaric_stem");
        public static final ResourceKey<Item> GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("giant_verdigris_agaric_bark_stem");
        public static final ResourceKey<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("stripped_giant_verdigris_agaric_bark_stem");
        public static final ResourceKey<Item> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = createKey("giant_verdigris_agaric_cap_block");
        public static final ResourceKey<Item> VERDIGRIS_AGARIC = createKey("verdigris_agaric");

        public static final ResourceKey<Item> GIANT_GANODERMA_APPLANATUM_BLOCK = createKey("giant_ganoderma_applanatum_block");

        public static final ResourceKey<Item> GRAY_SHROOM_PLANKS = createKey("gray_shroom_planks");
        public static final ResourceKey<Item> GRAY_SHROOM_BOOKSHELF = createKey("gray_shroom_bookshelf");

        //shadow corrupted / uncorrupted variants
        public static final ResourceKey<Item> SHADOW_AERIAL_TREE_LOG = createKey("shadow_aerial_tree_log");
        public static final ResourceKey<Item> SHADOW_GOLDEN_BEECH_LOG = createKey("shadow_golden_beech_log");
        public static final ResourceKey<Item> SHADOW_COPPER_PINE_LOG = createKey("shadow_copper_pine_log");
        public static final ResourceKey<Item> SHADOW_LAPIS_ROBINIA_LOG = createKey("shadow_lapis_robinia_log");
        public static final ResourceKey<Item> SHADOW_STELLAR_JUNGLE_TREE_LOG = createKey("shadow_stellar_jungle_tree_log");
        public static final ResourceKey<Item> HOLLOW_SHADOW_PINE_LOG = createKey("hollow_shadow_pine_log");
        public static final ResourceKey<Item> SHADOW_AERIAL_TREE_LEAVES = createKey("shadow_aerial_tree_leaves");
        public static final ResourceKey<Item> SHADOW_GOLDEN_BEECH_LEAVES = createKey("shadow_golden_beech_leaves");
        public static final ResourceKey<Item> SHADOW_COPPER_PINE_LEAVES = createKey("shadow_copper_pine_leaves");
        public static final ResourceKey<Item> SHADOW_LAPIS_ROBINIA_LEAVES = createKey("shadow_lapis_robinia_leaves");
        public static final ResourceKey<Item> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = createKey("shadow_stellar_jungle_tree_leaves");
        public static final ResourceKey<Item> HOLLOW_SHADOW_PINE_LEAVES = createKey("hollow_shadow_pine_leaves");
        public static final ResourceKey<Item> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = createKey("hollow_purple_shadow_pine_leaves");

        //ladder
        public static final ResourceKey<Item> SKY_LADDER = createKey("sky_ladder");

        //natural blocks and items
        public static final ResourceKey<Item> STELLAR_STONE = createKey("stellar_stone");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE = createKey("stellar_cobblestone");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS = createKey("stellar_stone_bricks");
        public static final ResourceKey<Item> MOSSY_STELLAR_STONE = createKey("mossy_stellar_stone");
        public static final ResourceKey<Item> MOSSY_STELLAR_COBBLESTONE = createKey("mossy_stellar_cobblestone");
        public static final ResourceKey<Item> STELLAR_CLAY = createKey("stellar_clay");
        public static final ResourceKey<Item> GLAUCOPHANITE = createKey("glaucophanite");
        public static final ResourceKey<Item> POLISHED_GLAUCOPHANITE = createKey("polished_glaucophanite");
        public static final ResourceKey<Item> SHADOW_STONE = createKey("shadow_stone");

        //crystal
        public static final ResourceKey<Item> CRYSTAL_BLOCK = createKey("crystal_block");
        public static final ResourceKey<Item> CRYSTAL_BRICKS = createKey("crystal_bricks");
        public static final ResourceKey<Item> CRYSTAL_BRICKS_SLAB = createKey("crystal_bricks_slab");
        public static final ResourceKey<Item> CRYSTAL_BRICKS_STAIRS = createKey("crystal_bricks_stairs");
        public static final ResourceKey<Item> CRYSTAL_BRICKS_WALL = createKey("crystal_bricks_wall");
        public static final ResourceKey<Item> STELLAR_STONE_CRYSTAL_BLOCK = createKey("stellar_stone_crystal_block");
        public static final ResourceKey<Item> SHADOW_CRYSTAL_BLOCK = createKey("shadow_crystal_block");
        public static final ResourceKey<Item> CRYSTALLIZED_LEAVES = createKey("crystallized_leaves");
        public static final ResourceKey<Item> CRYSTAL = createKey("crystal");
        public static final ResourceKey<Item> STELLAR_STONE_CRYSTAL = createKey("stellar_stone_crystal");
        public static final ResourceKey<Item> SHADOW_CRYSTAL = createKey("shadow_crystal");

        //glass and glass pane
        public static final ResourceKey<Item> SLIPPERY_SAND_GLASS = createKey("slippery_sand_glass");
        public static final ResourceKey<Item> RED_SLIPPERY_SAND_GLASS = createKey("red_slippery_sand_glass");
        public static final ResourceKey<Item> BLACK_SLIPPERY_SAND_GLASS = createKey("black_slippery_sand_glass");
        public static final ResourceKey<Item> BLUE_SLIPPERY_SAND_GLASS = createKey("blue_slippery_sand_glass");
        public static final ResourceKey<Item> GREEN_SLIPPERY_SAND_GLASS = createKey("green_slippery_sand_glass");
        public static final ResourceKey<Item> SLIPPERY_SAND_GLASS_PANE = createKey("slippery_sand_glass_pane");
        public static final ResourceKey<Item> RED_SLIPPERY_SAND_GLASS_PANE = createKey("red_slippery_sand_glass_pane");
        public static final ResourceKey<Item> BLACK_SLIPPERY_SAND_GLASS_PANE = createKey("black_slippery_sand_glass_pane");
        public static final ResourceKey<Item> BLUE_SLIPPERY_SAND_GLASS_PANE = createKey("blue_slippery_sand_glass_pane");
        public static final ResourceKey<Item> GREEN_SLIPPERY_SAND_GLASS_PANE = createKey("green_slippery_sand_glass_pane");

        //ghost boat
        public static final ResourceKey<Item> GHOST_BOAT_PLANKS = createKey("ghost_boat_planks");
        public static final ResourceKey<Item> GHOST_BOAT_LOG = createKey("ghost_boat_log");
        public static final ResourceKey<Item> GHOST_BOAT_WOOD = createKey("ghost_boat_wood");
        public static final ResourceKey<Item> GHOST_BOAT_SLAB = createKey("ghost_boat_slab");
        public static final ResourceKey<Item> GHOST_BOAT_STAIRS = createKey("ghost_boat_stairs");
        public static final ResourceKey<Item> GHOST_BOAT_FENCE = createKey("ghost_boat_fence");
        public static final ResourceKey<Item> GHOST_BOAT_GATE = createKey("ghost_boat_gate");
        public static final ResourceKey<Item> GHOST_BOAT_DOOR = createKey("ghost_boat_door");
        public static final ResourceKey<Item> GHOST_BOAT_TRAPDOOR = createKey("ghost_boat_trapdoor");
        public static final ResourceKey<Item> GHOST_BOAT_CHEST = createKey("ghost_boat_chest");
        public static final ResourceKey<Item> GHOST_BOAT_WOOL = createKey("ghost_boat_wool");
        public static final ResourceKey<Item> GHOST_STELLAR_COBBLESTONE = createKey("ghost_stellar_cobblestone");
        public static final ResourceKey<Item> GHOST_RUBY_BLOCK = createKey("ghost_ruby_block");
        public static final ResourceKey<Item> GHOST_FLUORITE_BLOCK = createKey("ghost_fluorite_block");
        public static final ResourceKey<Item> GHOST_AZURITE_BLOCK = createKey("ghost_azurite_block");
        public static final ResourceKey<Item> GHOST_GOLD_BLOCK = createKey("ghost_gold_block");
        public static final ResourceKey<Item> GHOST_BOAT_BARREL = createKey("ghost_boat_barrel");
        public static final ResourceKey<Item> GHOST_BOAT_CRAFTING_TABLE = createKey("ghost_boat_crafting_table");
        public static final ResourceKey<Item> GHOST_BOAT_VINE_ROPE_SPOOL = createKey("ghost_boat_vine_rope_spool");
        public static final ResourceKey<Item> GHOST_LANTERN = createKey("ghost_lantern");

        //reactors
        public static final ResourceKey<Item> WEAK_LIGHT_REACTOR = createKey("weak_light_reactor");
        public static final ResourceKey<Item> HIGH_POWER_LIGHT_REACTOR = createKey("high_power_light_reactor");
        public static final ResourceKey<Item> WEAK_SHADOW_REACTOR = createKey("weak_shadow_reactor");
        public static final ResourceKey<Item> HIGH_POWER_SHADOW_REACTOR = createKey("high_power_shadow_reactor");

        public static final ResourceKey<Item> BROKEN_WEAK_LIGHT_REACTOR = createKey("broken_weak_light_reactor");
        public static final ResourceKey<Item> BROKEN_HIGH_POWER_LIGHT_REACTOR = createKey("broken_high_power_light_reactor");
        public static final ResourceKey<Item> BROKEN_WEAK_SHADOW_REACTOR = createKey("broken_weak_shadow_reactor");
        public static final ResourceKey<Item> BROKEN_HIGH_POWER_SHADOW_REACTOR = createKey("broken_high_power_shadow_reactor");

        //solid_ethers
        public static final ResourceKey<Item> WHITE_SOLID_ETHER = createKey("white_solid_ether");
        public static final ResourceKey<Item> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
        public static final ResourceKey<Item> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
        public static final ResourceKey<Item> GREEN_SOLID_ETHER = createKey("green_solid_ether");
        public static final ResourceKey<Item> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

        //dungeons blocks
        public static final ResourceKey<Item> MUD_BRICKS = createKey("mud_bricks");
        public static final ResourceKey<Item> CRACKED_MUD_BRICKS = createKey("cracked_mud_bricks");
        public static final ResourceKey<Item> MOSSY_MUD_BRICKS = createKey("mossy_mud_bricks");
        public static final ResourceKey<Item> CHISELED_MUD_BRICKS = createKey("chiseled_mud_bricks");
        public static final ResourceKey<Item> LIGHT_MUD_BRICKS = createKey("light_mud_bricks");
        public static final ResourceKey<Item> CRACKED_LIGHT_MUD_BRICKS = createKey("cracked_light_mud_bricks");
        public static final ResourceKey<Item> LUNATIC_STONE = createKey("lunatic_stone");
        public static final ResourceKey<Item> DARK_LUNATIC_STONE = createKey("dark_lunatic_stone");
        public static final ResourceKey<Item> ROOF_LUNATIC_STONE = createKey("roof_lunatic_stone");
        public static final ResourceKey<Item> CRACKED_LUNATIC_STONE = createKey("cracked_lunatic_stone");
        public static final ResourceKey<Item> CHISELED_LUNATIC_STONE = createKey("chiseled_lunatic_stone");
        public static final ResourceKey<Item> LIGHT_LUNATIC_STONE = createKey("light_lunatic_stone");
        public static final ResourceKey<Item> ROOF_LIGHT_LUNATIC_STONE = createKey("roof_light_lunatic_stone");
        public static final ResourceKey<Item> CRACKED_LIGHT_LUNATIC_STONE = createKey("cracked_light_lunatic_stone");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_BRICKS = createKey("shadow_catacombs_bricks");
        public static final ResourceKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS = createKey("cracked_shadow_catacombs_bricks");
        public static final ResourceKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS = createKey("mossy_shadow_catacombs_bricks");
        public static final ResourceKey<Item> CHISELED_SHADOW_CATACOMBS_BRICKS = createKey("chiseled_shadow_catacombs_bricks");
        public static final ResourceKey<Item> BONE_SHADOW_CATACOMBS_BRICKS = createKey("bone_shadow_catacombs_bricks");
        public static final ResourceKey<Item> SKULL_SHADOW_CATACOMBS_BRICKS = createKey("skull_shadow_catacombs_bricks");
        public static final ResourceKey<Item> LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("light_shadow_catacombs_bricks");
        public static final ResourceKey<Item> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("cracked_light_shadow_catacombs_bricks");
        public static final ResourceKey<Item> GOLDEN_NETHER_BRICKS  = createKey("golden_nether_bricks");
        public static final ResourceKey<Item> CRACKED_GOLDEN_NETHER_BRICKS  = createKey("cracked_golden_nether_bricks");
        public static final ResourceKey<Item> CHISELED_GOLDEN_NETHER_BRICKS  = createKey("chiseled_golden_nether_bricks");
        public static final ResourceKey<Item> LIGHT_GOLDEN_NETHER_BRICKS = createKey("light_golden_nether_bricks");
        public static final ResourceKey<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("cracked_light_golden_nether_bricks");
        public static final ResourceKey<Item> LUNATIC_PILLAR = createKey("lunatic_pillar");
        public static final ResourceKey<Item> LUNATIC_PILLAR_TOP = createKey("lunatic_pillar_top");
        public static final ResourceKey<Item> VOLUCITE_STONE = createKey("volucite_stone");
        public static final ResourceKey<Item> CRACKED_VOLUCITE_STONE = createKey("cracked_volucite_stone");
        public static final ResourceKey<Item> CHISELED_VOLUCITE_STONE = createKey("chiseled_volucite_stone");
        public static final ResourceKey<Item> LIGHT_VOLUCITE_STONE = createKey("light_volucite_stone");
        public static final ResourceKey<Item> CRACKED_LIGHT_VOLUCITE_STONE = createKey("cracked_light_volucite_stone");

        //dungeon cores
        public static final ResourceKey<Item> MUD_DUNGEON_CORE = createKey("mud_dungeon_core");
        public static final ResourceKey<Item> LUNATIC_DUNGEON_CORE = createKey("lunatic_dungeon_core");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_DUNGEON_CORE = createKey("shadow_catacombs_dungeon_core");
        public static final ResourceKey<Item> GOLDEN_NETHER_DUNGEON_CORE = createKey("golden_nether_dungeon_core");
        public static final ResourceKey<Item> VOLUCITE_DUNGEON_CORE = createKey("volucite_dungeon_core");

        //dungeons slabs, stairs & walls
        public static final ResourceKey<Item> MUD_BRICKS_SLAB = createKey("mud_bricks_slab");
        public static final ResourceKey<Item> MUD_BRICKS_STAIRS = createKey("mud_bricks_stairs");
        public static final ResourceKey<Item> MUD_BRICKS_WALL = createKey("mud_bricks_wall");
        public static final ResourceKey<Item> CRACKED_MUD_BRICKS_SLAB = createKey("cracked_mud_bricks_slab");
        public static final ResourceKey<Item> CRACKED_MUD_BRICKS_STAIRS = createKey("cracked_mud_bricks_stairs");
        public static final ResourceKey<Item> CRACKED_MUD_BRICKS_WALL = createKey("cracked_mud_bricks_wall");
        public static final ResourceKey<Item> MOSSY_MUD_BRICKS_SLAB = createKey("mossy_mud_bricks_slab");
        public static final ResourceKey<Item> MOSSY_MUD_BRICKS_STAIRS = createKey("mossy_mud_bricks_stairs");
        public static final ResourceKey<Item> MOSSY_MUD_BRICKS_WALL = createKey("mossy_mud_bricks_wall");
        public static final ResourceKey<Item> VOLUCITE_STONE_SLAB = createKey("volucite_stone_slab");
        public static final ResourceKey<Item> VOLUCITE_STONE_STAIRS = createKey("volucite_stone_stairs");
        public static final ResourceKey<Item> VOLUCITE_STONE_WALL = createKey("volucite_stone_wall");
        public static final ResourceKey<Item> CRACKED_VOLUCITE_STONE_SLAB = createKey("cracked_volucite_stone_slab");
        public static final ResourceKey<Item> CRACKED_VOLUCITE_STONE_STAIRS = createKey("cracked_volucite_stone_stairs");
        public static final ResourceKey<Item> CRACKED_VOLUCITE_STONE_WALL = createKey("cracked_volucite_stone_wall");
        public static final ResourceKey<Item> LUNATIC_STONE_SLAB = createKey("lunatic_stone_slab");
        public static final ResourceKey<Item> LUNATIC_STONE_STAIRS = createKey("lunatic_stone_stairs");
        public static final ResourceKey<Item> LUNATIC_STONE_WALL = createKey("lunatic_stone_wall");
        public static final ResourceKey<Item> DARK_LUNATIC_STONE_SLAB = createKey("dark_lunatic_stone_slab");
        public static final ResourceKey<Item> DARK_LUNATIC_STONE_STAIRS = createKey("dark_lunatic_stone_stairs");
        public static final ResourceKey<Item> DARK_LUNATIC_STONE_WALL = createKey("dark_lunatic_stone_wall");
        public static final ResourceKey<Item> CRACKED_LUNATIC_STONE_SLAB = createKey("cracked_lunatic_stone_slab");
        public static final ResourceKey<Item> CRACKED_LUNATIC_STONE_STAIRS = createKey("cracked_lunatic_stone_stairs");
        public static final ResourceKey<Item> CRACKED_LUNATIC_STONE_WALL = createKey("cracked_lunatic_stone_wall");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_BRICKS_SLAB = createKey("shadow_catacombs_bricks_slab");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("shadow_catacombs_bricks_stairs");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_BRICKS_WALL = createKey("shadow_catacombs_bricks_wall");
        public static final ResourceKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("cracked_shadow_catacombs_bricks_slab");
        public static final ResourceKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("cracked_shadow_catacombs_bricks_stairs");
        public static final ResourceKey<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = createKey("cracked_shadow_catacombs_bricks_wall");
        public static final ResourceKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("mossy_shadow_catacombs_bricks_slab");
        public static final ResourceKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("mossy_shadow_catacombs_bricks_stairs");
        public static final ResourceKey<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = createKey("mossy_shadow_catacombs_bricks_wall");
        public static final ResourceKey<Item> SHADOW_BARS = createKey("shadow_bars");
        public static final ResourceKey<Item> GOLDEN_NETHER_BRICKS_SLAB = createKey("golden_nether_bricks_slab");
        public static final ResourceKey<Item> GOLDEN_NETHER_BRICKS_STAIRS = createKey("golden_nether_bricks_stairs");
        public static final ResourceKey<Item> GOLDEN_NETHER_BRICKS_WALL = createKey("golden_nether_bricks_wall");
        public static final ResourceKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = createKey("cracked_golden_nether_bricks_slab");
        public static final ResourceKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = createKey("cracked_golden_nether_bricks_stairs");
        public static final ResourceKey<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL = createKey("cracked_golden_nether_bricks_wall");

        //smoky quartz
        public static final ResourceKey<Item> SMOKY_QUARTZ = createKey("smoky_quartz");
        public static final ResourceKey<Item> SMOKY_QUARTZ_BLOCK = createKey("smoky_quartz_block");
        public static final ResourceKey<Item> SMOOTH_SMOKY_QUARTZ = createKey("smooth_smoky_quartz");
        public static final ResourceKey<Item> CHISELED_SMOKY_QUARTZ_BLOCK = createKey("chiseled_smoky_quartz_block");
        public static final ResourceKey<Item> SMOKY_QUARTZ_BRICKS = createKey("smoky_quartz_bricks");
        public static final ResourceKey<Item> SMOKY_QUARTZ_PILLAR = createKey("smoky_quartz_pillar");
        public static final ResourceKey<Item> SMOKY_QUARTZ_SLAB = createKey("smoky_quartz_slab");
        public static final ResourceKey<Item> SMOOTH_SMOKY_QUARTZ_SLAB = createKey("smooth_smoky_quartz_slab");
        public static final ResourceKey<Item> SMOKY_QUARTZ_STAIRS = createKey("smoky_quartz_stairs");
        public static final ResourceKey<Item> SMOOTH_SMOKY_QUARTZ_STAIRS = createKey("smooth_smoky_quartz_stairs");

        //dungeon trapped blocks
        public static final ResourceKey<Item> TRAPPED_MUD_BRICKS = createKey("trapped_mud_bricks");
        public static final ResourceKey<Item> TRAPPED_LIGHT_MUD_BRICKS = createKey("trapped_light_mud_bricks");
        public static final ResourceKey<Item> TRAPPED_LUNATIC_STONE = createKey("trapped_lunatic_stone");
        public static final ResourceKey<Item> TRAPPED_LIGHT_LUNATIC_STONE = createKey("trapped_light_lunatic_stone");
        public static final ResourceKey<Item> TRAPPED_GOLDEN_NETHER_BRICKS = createKey("trapped_golden_nether_bricks");
        public static final ResourceKey<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("trapped_light_golden_nether_bricks");

        //dungeon other blocks, loots
        public static final ResourceKey<Item> MUD_BONE_BLOCK = createKey("mud_bone_block");
        public static final ResourceKey<Item> MUD_BONE_PILE_BLOCK = createKey("mud_bone_pile_block");
        public static final ResourceKey<Item> MUD_BONE = createKey("mud_bone");
        public static final ResourceKey<Item> THORNY_COBWEB = createKey("thorny_cobweb");
        public static final ResourceKey<Item> AERIAL_NETHERRACK = createKey("aerial_netherrack");
        public static final ResourceKey<Item> AERIAL_NETHERRACK_SLAB = createKey("aerial_netherrack_slab");
        public static final ResourceKey<Item> AERIAL_NETHERRACK_STAIRS = createKey("aerial_netherrack_stairs");
        public static final ResourceKey<Item> AERIAL_NETHERRACK_WALL = createKey("aerial_netherrack_wall");

        //dungeon bookshelfs
        public static final ResourceKey<Item> MUD_BOOKSHELF = createKey("mud_bookshelf");
        public static final ResourceKey<Item> LUNATIC_BOOKSHELF = createKey("lunatic_bookshelf");
        public static final ResourceKey<Item> GOLDEN_NETHER_BOOKSHELF = createKey("golden_nether_bookshelf");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_BOOKSHELF = createKey("shadow_catacombs_bookshelf");
        public static final ResourceKey<Item> VOLUCITE_BOOKSHELF = createKey("volucite_bookshelf");

        //glyph blocks
        public static final ResourceKey<Item> MUD_GLYPH_BLOCK = createKey("mud_glyph_block");
        public static final ResourceKey<Item> LUNATIC_GLYPH_BLOCK = createKey("lunatic_glyph_block");
        public static final ResourceKey<Item> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = createKey("golden_nether_prison_glyph_block");
        public static final ResourceKey<Item> VOLUCITE_GLYPH_BLOCK = createKey("volucite_glyph_block");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_GLYPH_BLOCK = createKey("shadow_catacombs_glyph_block");

        //trophies
        public static final ResourceKey<Item> MUD_CYCLE_MAGE_TROPHY = createKey("mud_cycle_mage_trophy");
        public static final ResourceKey<Item> LUNAR_PRIEST_TROPHY = createKey("lunar_priest_trophy");
        public static final ResourceKey<Item> LILITH_TROPHY = createKey("lilith_trophy");
        public static final ResourceKey<Item> CHAINED_GOD_TROPHY = createKey("chained_god_trophy");

        //ores
        public static final ResourceKey<Item> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
        public static final ResourceKey<Item> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
        public static final ResourceKey<Item> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
        public static final ResourceKey<Item> FLUORITE_ORE = createKey("fluorite_ore");
        public static final ResourceKey<Item> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
        public static final ResourceKey<Item> RUBY_ORE = createKey("ruby_ore");
        public static final ResourceKey<Item> AZURITE_ORE = createKey("azurite_ore");
        public static final ResourceKey<Item> VOLUCITE_ORE = createKey("volucite_ore");
        public static final ResourceKey<Item> OBSIDIAN_ORE = createKey("obsidian_ore");
        public static final ResourceKey<Item> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");

        public static final ResourceKey<Item> RAW_RUBY_BLOCK = createKey("raw_ruby_block");
        public static final ResourceKey<Item> RAW_AZURITE_BLOCK = createKey("raw_azurite_crystal_block");
        public static final ResourceKey<Item> RAW_VOLUCITE_BLOCK = createKey("raw_volucite_block");

        public static final ResourceKey<Item> RAW_RUBY  = createKey("raw_ruby");
        public static final ResourceKey<Item> RAW_AZURITE  = createKey("raw_azurite_crystal");
        public static final ResourceKey<Item> RAW_VOLUCITE  = createKey("raw_volucite");

        public static final ResourceKey<Item> FLUORITE_BLOCK = createKey("fluorite_block");
        public static final ResourceKey<Item> MAGMATIC_GEL_BLOCK = createKey("magmatic_gel_block");
        public static final ResourceKey<Item> RUBY_BLOCK = createKey("ruby_block");
        public static final ResourceKey<Item> AZURITE_BLOCK = createKey("azurite_block");
        public static final ResourceKey<Item> VOLUCITE_BLOCK = createKey("volucite_block");

        public static final ResourceKey<Item> FLUORITE = createKey("fluorite");
        public static final ResourceKey<Item> MAGMATIC_GEL = createKey("magmatic_gel");
        public static final ResourceKey<Item> RUBY = createKey("ruby");
        public static final ResourceKey<Item> AZURITE_CRYSTAL = createKey("azurite_crystal");
        public static final ResourceKey<Item> VOLUCITE_VIBRANT = createKey("volucite_vibrant");

        public static final ResourceKey<Item> OVERHEATED_RUBY = createKey("overheated_ruby");
        public static final ResourceKey<Item> OVERHEATED_VOLUCITE = createKey("overheated_volucite");

        //legendary ores
        public static final ResourceKey<Item> ARSONIST_INGOT = createKey("arsonist_ingot");
        public static final ResourceKey<Item> LUNATIC_CRYSTAL = createKey("lunatic_crystal");
        public static final ResourceKey<Item> OBSIDIAN_SHARD = createKey("obsidian_shard");
        public static final ResourceKey<Item> CURSED_CRYSAL = createKey("cursed_crystal");

        public static final ResourceKey<Item> ARSONIST_BLOCK = createKey("arsonist_block");
        public static final ResourceKey<Item> LUNATIC_CRYSTAL_BLOCK = createKey("lunatic_crystal_block");
        public static final ResourceKey<Item> CURSED_CRYSAL_BLOCK = createKey("cursed_crystal_block");

        //cactus
        public static final ResourceKey<Item> SKY_CACTUS = createKey("sky_cactus");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_PLANKS = createKey("sky_cactus_fiber_planks");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_BOOKSHELF = createKey("sky_cactus_fiber_bookshelf");
        public static final ResourceKey<Item> VIBRANT_SKY_CACTUS = createKey("vibrant_sky_cactus");
        public static final ResourceKey<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN = createKey("vibrant_sky_cactus_fiber_lantern");

        //bushes
        public static final ResourceKey<Item> AERIAL_BERRY_SEEDS = createKey("aerial_berry_seeds");
        public static final ResourceKey<Item> VIBRANT_AERIAL_BERRY_SEEDS = createKey("vibrant_aerial_berry_seeds");

        //crops
        public static final ResourceKey<Item> STELLAR_WHEAT_SEEDS = createKey("stellar_wheat_seeds");
        public static final ResourceKey<Item> BLUE_MEANIE_SPORES = createKey("blue_meanie_spores");
        public static final ResourceKey<Item> STELLAR_WHEAT = createKey("stellar_wheat");
        public static final ResourceKey<Item> BLUE_MEANIE_CAP = createKey("blue_meanie_cap");

        //vertical growing plants
        public static final ResourceKey<Item> CLIMBING_VINE = createKey("climbing_vine");
        public static final ResourceKey<Item> STELLAR_SUGAR_CANE = createKey("stellar_sugar_cane");

        //chorus like
        public static final ResourceKey<Item> FULL_MOON_FLOWER = createKey("full_moon_flower");

        //vines
        public static final ResourceKey<Item> VINE_BLOSSOM  = createKey("vine_blossom");
        public static final ResourceKey<Item> LAZULI_ROOTS = createKey("lazuli_roots");
        public static final ResourceKey<Item> STELLAR_ROOTS = createKey("stellar_roots");
        public static final ResourceKey<Item> DEAD_ROOTS = createKey("dead_roots");
        public static final ResourceKey<Item> GLOWING_ROOTS = createKey("glowing_roots");
        public static final ResourceKey<Item> SHADOW_GLOWING_ROOTS = createKey("shadow_glowing_roots");

        //grass
        public static final ResourceKey<Item> STELLAR_GRASS = createKey("stellar_grass");
        public static final ResourceKey<Item> STELLAR_GRASS_BALL = createKey("stellar_grass_ball");
        public static final ResourceKey<Item> STELLAR_FERN = createKey("stellar_fern");
        public static final ResourceKey<Item> STELLAR_TALL_GRASS = createKey("stellar_tall_grass");
        public static final ResourceKey<Item> STELLAR_TALL_FERN = createKey("stellar_tall_fern");
        public static final ResourceKey<Item> STELLAR_VERY_TALL_GRASS = createKey("stellar_very_tall_grass");
        public static final ResourceKey<Item> BLUISH_FERN = createKey("bluish_fern");
        public static final ResourceKey<Item> TALL_BLUISH_FERN = createKey("tall_bluish_fern");
        public static final ResourceKey<Item> POLYCHROME_FERN = createKey("polychrome_fern");
        public static final ResourceKey<Item> TALL_POLYCHROME_FERN = createKey("tall_polychrome_fern");
        public static final ResourceKey<Item> STELLAR_DEAD_BUSH = createKey("stellar_dead_bush");
        public static final ResourceKey<Item> BRAMBLES = createKey("brambles");
        public static final ResourceKey<Item> SHADOW_BRAMBLES = createKey("shadow_brambles");
        public static final ResourceKey<Item> SHADOW_GRASS = createKey("shadow_grass");
        public static final ResourceKey<Item> SHADOW_GRASS_BALL = createKey("shadow_grass_ball");
        public static final ResourceKey<Item> PURPLISH_STELLAR_GRASS = createKey("purplish_stellar_grass");
        public static final ResourceKey<Item> STELLAR_CLOVERS = createKey("stellar_clovers");
        public static final ResourceKey<Item> GLOWING_STELLAR_GRASS = createKey("glowing_stellar_grass");

        //flowers
        public static final ResourceKey<Item> BLUE_FLOWER = createKey("blue_flower");
        public static final ResourceKey<Item> BLACK_ROSE = createKey("black_rose");
        public static final ResourceKey<Item> BELLFLOWER = createKey("bellflower");

        //with gui
        public static final ResourceKey<Item> OSCILLATOR = createKey("oscillator");
        public static final ResourceKey<Item> FREEZER = createKey("freezer");
        public static final ResourceKey<Item> STELLAR_FURNACE = createKey("stellar_furnace");
        public static final ResourceKey<Item> GHOST_STELLAR_FURNACE = createKey("ghost_stellar_furnace");

        //chests
        public static final ResourceKey<Item> AERIAL_TREE_CHEST = createKey("aerial_tree_chest");
        public static final ResourceKey<Item> GOLDEN_BEECH_CHEST = createKey("golden_beech_chest");
        public static final ResourceKey<Item> COPPER_PINE_CHEST = createKey("copper_pine_chest");
        public static final ResourceKey<Item> LAPIS_ROBINIA_CHEST = createKey("lapis_robinia_chest");
        public static final ResourceKey<Item> SHADOW_PINE_CHEST = createKey("shadow_pine_chest");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_CHEST = createKey("stellar_jungle_tree_chest");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_CHEST = createKey("sky_cactus_fiber_chest");
        public static final ResourceKey<Item> GRAY_SHROOM_CHEST = createKey("gray_shroom_chest");
        public static final ResourceKey<Item> MUD_CHEST = createKey("mud_chest");
        public static final ResourceKey<Item> LUNATIC_CHEST = createKey("lunatic_chest");
        public static final ResourceKey<Item> VOLUCITE_CHEST = createKey("volucite_chest");
        public static final ResourceKey<Item> SHADOW_CATACOMBS_CHEST = createKey("shadow_catacombs_chest");
        public static final ResourceKey<Item> GOLDEN_NETHER_CHEST = createKey("golden_nether_chest");

        //chest mimics
        public static final ResourceKey<Item> AERIAL_TREE_CHEST_MIMIC = createKey("aerial_tree_chest_mimic");
        public static final ResourceKey<Item> GOLDEN_BEECH_CHEST_MIMIC = createKey("golden_beech_chest_mimic");
        public static final ResourceKey<Item> COPPER_PINE_CHEST_MIMIC = createKey("copper_pine_chest_mimic");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_CHEST_MIMIC = createKey("sky_cactus_fiber_chest_mimic");

        //barrel mimics
        public static final ResourceKey<Item> SHADOW_PINE_BARREL_MIMIC = createKey("shadow_pine_barrel_mimic");

        //fences, bars or walls
        public static final ResourceKey<Item> AERIAL_TREE_FENCE = createKey("aerial_tree_fence");
        public static final ResourceKey<Item> GOLDEN_BEECH_FENCE = createKey("golden_beech_fence");
        public static final ResourceKey<Item> COPPER_PINE_FENCE = createKey("copper_pine_fence");
        public static final ResourceKey<Item> LAPIS_ROBINIA_FENCE = createKey("lapis_robinia_fence");
        public static final ResourceKey<Item> SHADOW_PINE_FENCE = createKey("shadow_pine_fence");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_FENCE = createKey("stellar_jungle_tree_fence");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_FENCE = createKey("sky_cactus_fiber_fence");
        public static final ResourceKey<Item> GRAY_SHROOM_FENCE = createKey("gray_shroom_fence");
        public static final ResourceKey<Item> RUBY_BARS = createKey("ruby_bars");
        public static final ResourceKey<Item> STELLAR_STONE_WALL = createKey("stellar_stone_wall");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE_WALL = createKey("stellar_cobblestone_wall");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS_WALL = createKey("stellar_stone_bricks_wall");
        public static final ResourceKey<Item> MOSSY_STELLAR_STONE_WALL = createKey("mossy_stellar_stone_wall");
        public static final ResourceKey<Item> MOSSY_STELLAR_COBBLESTONE_WALL = createKey("mossy_stellar_cobblestone_wall");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_WALL = createKey("slippery_sand_stone_wall");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("slippery_sand_stone_bricks_wall");
        public static final ResourceKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("cracked_slippery_sand_stone_bricks_wall");
        public static final ResourceKey<Item> GLAUCOPHANITE_WALL = createKey("glaucophanite_wall");
        public static final ResourceKey<Item> POLISHED_GLAUCOPHANITE_WALL = createKey("polished_glaucophanite_wall");
        public static final ResourceKey<Item> MAGMATIC_GEL_WALL = createKey("magmatic_gel_wall");

        //gates
        public static final ResourceKey<Item> AERIAL_TREE_GATE = createKey("aerial_tree_gate");
        public static final ResourceKey<Item> GOLDEN_BEECH_GATE = createKey("golden_beech_gate");
        public static final ResourceKey<Item> COPPER_PINE_GATE = createKey("copper_pine_gate");
        public static final ResourceKey<Item> LAPIS_ROBINIA_GATE = createKey("lapis_robinia_gate");
        public static final ResourceKey<Item> SHADOW_PINE_GATE = createKey("shadow_pine_gate");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_GATE = createKey("stellar_jungle_tree_gate");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_GATE = createKey("sky_cactus_fiber_gate");
        public static final ResourceKey<Item> GRAY_SHROOM_GATE = createKey("gray_shroom_gate");

        //doors
        public static final ResourceKey<Item> AERIAL_TREE_DOOR = createKey("aerial_tree_door");
        public static final ResourceKey<Item> GOLDEN_BEECH_DOOR = createKey("golden_beech_door");
        public static final ResourceKey<Item> COPPER_PINE_DOOR = createKey("copper_pine_door");
        public static final ResourceKey<Item> LAPIS_ROBINIA_DOOR = createKey("lapis_robinia_door");
        public static final ResourceKey<Item> SHADOW_PINE_DOOR = createKey("shadow_pine_door");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_DOOR = createKey("stellar_jungle_tree_door");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_DOOR = createKey("sky_cactus_fiber_door");
        public static final ResourceKey<Item> GRAY_SHROOM_DOOR = createKey("gray_shroom_door");
        public static final ResourceKey<Item> RUBY_DOOR = createKey("ruby_door");

        //trapdoors
        public static final ResourceKey<Item> AERIAL_TREE_TRAPDOOR = createKey("aerial_tree_trapdoor");
        public static final ResourceKey<Item> GOLDEN_BEECH_TRAPDOOR = createKey("golden_beech_trapdoor");
        public static final ResourceKey<Item> COPPER_PINE_TRAPDOOR = createKey("copper_pine_trapdoor");
        public static final ResourceKey<Item> LAPIS_ROBINIA_TRAPDOOR = createKey("lapis_robinia_trapdoor");
        public static final ResourceKey<Item> SHADOW_PINE_TRAPDOOR = createKey("shadow_pine_trapdoor");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_TRAPDOOR = createKey("stellar_jungle_tree_trapdoor");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_TRAPDOOR = createKey("sky_cactus_fiber_trapdoor");
        public static final ResourceKey<Item> GRAY_SHROOM_TRAPDOOR = createKey("gray_shroom_trapdoor");
        public static final ResourceKey<Item> RUBY_TRAPDOOR = createKey("ruby_trapdoor");

        //buttons
        public static final ResourceKey<Item> STELLAR_STONE_BUTTON = createKey("stellar_stone_button");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE_BUTTON = createKey("stellar_cobblestone_button");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS_BUTTON = createKey("stellar_stone_bricks_button");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BUTTON = createKey("slippery_sand_stone_button");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON = createKey("slippery_sand_stone_bricks_button");
        public static final ResourceKey<Item> AERIAL_TREE_BUTTON = createKey("aerial_tree_button");
        public static final ResourceKey<Item> GOLDEN_BEECH_BUTTON = createKey("golden_beech_button");
        public static final ResourceKey<Item> COPPER_PINE_BUTTON = createKey("copper_pine_button");
        public static final ResourceKey<Item> LAPIS_ROBINIA_BUTTON = createKey("lapis_robinia_button");
        public static final ResourceKey<Item> SHADOW_PINE_BUTTON = createKey("shadow_pine_button");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_BUTTON = createKey("stellar_jungle_tree_button");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_BUTTON = createKey("sky_cactus_fiber_button");
        public static final ResourceKey<Item> GRAY_SHROOM_BUTTON = createKey("gray_shroom_button");
        public static final ResourceKey<Item> GLAUCOPHANITE_BUTTON = createKey("glaucophanite_button");

        //pressure plates
        public static final ResourceKey<Item> STELLAR_STONE_PRESSURE_PLATE = createKey("stellar_stone_pressure_plate");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE = createKey("stellar_cobblestone_pressure_plate");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE = createKey("stellar_stone_bricks_pressure_plate");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE = createKey("slippery_sand_stone_pressure_plate");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = createKey("slippery_sand_stone_bricks_pressure_plate");
        public static final ResourceKey<Item> AERIAL_TREE_PRESSURE_PLATE = createKey("aerial_tree_pressure_plate");
        public static final ResourceKey<Item> GOLDEN_BEECH_PRESSURE_PLATE = createKey("golden_beech_pressure_plate");
        public static final ResourceKey<Item> COPPER_PINE_PRESSURE_PLATE = createKey("copper_pine_pressure_plate");
        public static final ResourceKey<Item> LAPIS_ROBINIA_PRESSURE_PLATE = createKey("lapis_robinia_pressure_plate");
        public static final ResourceKey<Item> SHADOW_PINE_PRESSURE_PLATE = createKey("shadow_pine_pressure_plate");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = createKey("stellar_jungle_tree_pressure_plate");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE = createKey("sky_cactus_fiber_pressure_plate");
        public static final ResourceKey<Item> GRAY_SHROOM_PRESSURE_PLATE = createKey("gray_shroom_pressure_plate");
        public static final ResourceKey<Item> GLAUCOPHANITE_PRESSURE_PLATE = createKey("glaucophanite_pressure_plate");

        //slabs
        public static final ResourceKey<Item> AERIAL_TREE_SLAB = createKey("aerial_tree_slab");
        public static final ResourceKey<Item> GOLDEN_BEECH_SLAB = createKey("golden_beech_slab");
        public static final ResourceKey<Item> COPPER_PINE_SLAB = createKey("copper_pine_slab");
        public static final ResourceKey<Item> LAPIS_ROBINIA_SLAB = createKey("lapis_robinia_slab");
        public static final ResourceKey<Item> SHADOW_PINE_SLAB = createKey("shadow_pine_slab");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_SLAB = createKey("stellar_jungle_tree_slab");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_SLAB = createKey("sky_cactus_fiber_slab");
        public static final ResourceKey<Item> GRAY_SHROOM_SLAB = createKey("gray_shroom_slab");
        public static final ResourceKey<Item> STELLAR_STONE_SLAB = createKey("stellar_stone_slab");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE_SLAB = createKey("stellar_cobblestone_slab");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS_SLAB = createKey("stellar_stone_bricks_slab");
        public static final ResourceKey<Item> MOSSY_STELLAR_STONE_SLAB = createKey("mossy_stellar_stone_slab");
        public static final ResourceKey<Item> MOSSY_STELLAR_COBBLESTONE_SLAB = createKey("mossy_stellar_cobblestone_slab");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_SLAB = createKey("slippery_sand_stone_slab");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("slippery_sand_stone_bricks_slab");
        public static final ResourceKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("cracked_slippery_sand_stone_bricks_slab");
        public static final ResourceKey<Item> POLISHED_GLAUCOPHANITE_SLAB = createKey("polished_glaucophanite_slab");
        public static final ResourceKey<Item> MAGMATIC_GEL_SLAB = createKey("magmatic_gel_slab");

        //stairs
        public static final ResourceKey<Item> AERIAL_TREE_STAIRS = createKey("aerial_tree_stairs");
        public static final ResourceKey<Item> GOLDEN_BEECH_STAIRS = createKey("golden_beech_stairs");
        public static final ResourceKey<Item> COPPER_PINE_STAIRS = createKey("copper_pine_stairs");
        public static final ResourceKey<Item> LAPIS_ROBINIA_STAIRS = createKey("lapis_robinia_stairs");
        public static final ResourceKey<Item> SHADOW_PINE_STAIRS = createKey("shadow_pine_stairs");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_STAIRS = createKey("stellar_jungle_tree_stairs");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_STAIRS = createKey("sky_cactus_fiber_stairs");
        public static final ResourceKey<Item> GRAY_SHROOM_STAIRS = createKey("gray_shroom_stairs");
        public static final ResourceKey<Item> STELLAR_STONE_STAIRS = createKey("stellar_stone_stairs");
        public static final ResourceKey<Item> STELLAR_COBBLESTONE_STAIRS = createKey("stellar_cobblestone_stairs");
        public static final ResourceKey<Item> STELLAR_STONE_BRICKS_STAIRS = createKey("stellar_stone_bricks_stairs");
        public static final ResourceKey<Item> MOSSY_STELLAR_STONE_STAIRS = createKey("mossy_stellar_stone_stairs");
        public static final ResourceKey<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS = createKey("mossy_stellar_cobblestone_stairs");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_STAIRS = createKey("slippery_sand_stone_stairs");
        public static final ResourceKey<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("slippery_sand_stone_bricks_stairs");
        public static final ResourceKey<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("cracked_slippery_sand_stone_bricks_stairs");
        public static final ResourceKey<Item> POLISHED_GLAUCOPHANITE_STAIRS = createKey("polished_glaucophanite_stairs");
        public static final ResourceKey<Item> MAGMATIC_GEL_STAIRS = createKey("magmatic_gel_stairs");

        //signs
        public static final ResourceKey<Item> AERIAL_TREE_SIGN = createKey("aerial_tree_sign");
        public static final ResourceKey<Item> GOLDEN_BEECH_SIGN = createKey("golden_beech_sign");
        public static final ResourceKey<Item> COPPER_PINE_SIGN = createKey("copper_pine_sign");
        public static final ResourceKey<Item> LAPIS_ROBINIA_SIGN = createKey("lapis_robinia_sign");
        public static final ResourceKey<Item> SHADOW_PINE_SIGN = createKey("shadow_pine_sign");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_SIGN = createKey("stellar_jungle_tree_sign");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_SIGN = createKey("sky_cactus_fiber_sign");
        public static final ResourceKey<Item> GRAY_SHROOM_SIGN = createKey("gray_shroom_sign");

        //hanging signs
        public static final ResourceKey<Item> AERIAL_TREE_HANGING_SIGN = createKey("aerial_tree_hanging_sign");
        public static final ResourceKey<Item> GOLDEN_BEECH_HANGING_SIGN = createKey("golden_beech_hanging_sign");
        public static final ResourceKey<Item> COPPER_PINE_HANGING_SIGN = createKey("copper_pine_hanging_sign");
        public static final ResourceKey<Item> LAPIS_ROBINIA_HANGING_SIGN = createKey("lapis_robinia_hanging_sign");
        public static final ResourceKey<Item> SHADOW_PINE_HANGING_SIGN = createKey("shadow_pine_hanging_sign");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_HANGING_SIGN = createKey("stellar_jungle_tree_hanging_sign");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_HANGING_SIGN = createKey("sky_cactus_fiber_hanging_sign");
        public static final ResourceKey<Item> GRAY_SHROOM_HANGING_SIGN = createKey("gray_shroom_hanging_sign");

        //crafting tables
        public static final ResourceKey<Item> AERIAL_TREE_CRAFTING_TABLE = createKey("aerial_tree_crafting_table");
        public static final ResourceKey<Item> GOLDEN_BEECH_CRAFTING_TABLE = createKey("golden_beech_crafting_table");
        public static final ResourceKey<Item> COPPER_PINE_CRAFTING_TABLE = createKey("copper_pine_crafting_table");
        public static final ResourceKey<Item> LAPIS_ROBINIA_CRAFTING_TABLE = createKey("lapis_robinia_crafting_table");
        public static final ResourceKey<Item> SHADOW_PINE_CRAFTING_TABLE = createKey("shadow_pine_crafting_table");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = createKey("stellar_jungle_tree_crafting_table");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE = createKey("sky_cactus_fiber_crafting_table");
        public static final ResourceKey<Item> GRAY_SHROOM_CRAFTING_TABLE = createKey("gray_shroom_crafting_table");

        //barrels
        public static final ResourceKey<Item> AERIAL_TREE_BARREL = createKey("aerial_tree_barrel");
        public static final ResourceKey<Item> GOLDEN_BEECH_BARREL = createKey("golden_beech_barrel");
        public static final ResourceKey<Item> COPPER_PINE_BARREL = createKey("copper_pine_barrel");
        public static final ResourceKey<Item> LAPIS_ROBINIA_BARREL = createKey("lapis_robinia_barrel");
        public static final ResourceKey<Item> SHADOW_PINE_BARREL = createKey("shadow_pine_barrel");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_BARREL = createKey("stellar_jungle_tree_barrel");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_BARREL = createKey("sky_cactus_fiber_barrel");
        public static final ResourceKey<Item> GRAY_SHROOM_BARREL = createKey("gray_shroom_barrel");

        //composters
        public static final ResourceKey<Item> AERIAL_TREE_COMPOSTER = createKey("aerial_tree_composter");
        public static final ResourceKey<Item> GOLDEN_BEECH_COMPOSTER = createKey("golden_beech_composter");
        public static final ResourceKey<Item> COPPER_PINE_COMPOSTER = createKey("copper_pine_composter");
        public static final ResourceKey<Item> LAPIS_ROBINIA_COMPOSTER = createKey("lapis_robinia_composter");
        public static final ResourceKey<Item> SHADOW_PINE_COMPOSTER = createKey("shadow_pine_composter");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_COMPOSTER = createKey("stellar_jungle_tree_composter");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_COMPOSTER = createKey("sky_cactus_fiber_composter");
        public static final ResourceKey<Item> GRAY_SHROOM_COMPOSTER = createKey("gray_shroom_composter");

        //decorative
        public static final ResourceKey<Item> AERIAL_TREE_VINE_ROPE_SPOOL = createKey("aerial_tree_vine_rope_spool");
        public static final ResourceKey<Item> GOLDEN_BEECH_VINE_ROPE_SPOOL = createKey("golden_beech_vine_rope_spool");
        public static final ResourceKey<Item> COPPER_PINE_VINE_ROPE_SPOOL = createKey("copper_pine_vine_rope_spool");
        public static final ResourceKey<Item> LAPIS_ROBINIA_VINE_ROPE_SPOOL = createKey("lapis_robinia_vine_rope_spool");
        public static final ResourceKey<Item> SHADOW_PINE_VINE_ROPE_SPOOL = createKey("shadow_pine_vine_rope_spool");
        public static final ResourceKey<Item> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = createKey("stellar_jungle_tree_vine_rope_spool");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = createKey("sky_cactus_fiber_vine_rope_spool");
        public static final ResourceKey<Item> GRAY_SHROOM_VINE_ROPE_SPOOL = createKey("gray_shroom_vine_rope_spool");

        //item for crafts
        public static final ResourceKey<Item> SKY_STICK = createKey("sky_stick");
        public static final ResourceKey<Item> SKY_BOWL = createKey("sky_bowl");
        public static final ResourceKey<Item> SHADOW_SHARD = createKey("shadow_shard");
        public static final ResourceKey<Item> ROTTEN_LEATHER = createKey("rotten_leather");
        public static final ResourceKey<Item> VENOMOUS_SNAKE_SKIN = createKey("venomous_snake_skin");
        public static final ResourceKey<Item> ARSONIST_UPGRADE_SMITHING_TEMPLATE = createKey("arsonist_upgrade_smithing_template");

        //projectile item
        public static final ResourceKey<Item> STELLAR_EGG = createKey("stellar_egg");
        public static final ResourceKey<Item> DIMENSION_SHATTERER_PROJECTILE = createKey("dimension_shatterer_projectile");

        //shurikens
        public static final ResourceKey<Item> IRON_SHURIKEN = createKey("iron_shuriken");
        public static final ResourceKey<Item> GOLD_SHURIKEN = createKey("gold_shuriken");
        public static final ResourceKey<Item> DIAMOND_SHURIKEN = createKey("diamond_shuriken");
        public static final ResourceKey<Item> NETHERITE_SHURIKEN = createKey("netherite_shuriken");
        public static final ResourceKey<Item> RUBY_SHURIKEN = createKey("ruby_shuriken");
        public static final ResourceKey<Item> AZURITE_SHURIKEN = createKey("azurite_shuriken");
        public static final ResourceKey<Item> MAGMATIC_GEL_SHURIKEN = createKey("magmatic_gel_shuriken");
        public static final ResourceKey<Item> VOLUCITE_SHURIKEN = createKey("volucite_shuriken");
        public static final ResourceKey<Item> OBSIDIAN_SHURIKEN = createKey("obsidian_shuriken");
        public static final ResourceKey<Item> LUNATIC_CRYSTAL_SHURIKEN = createKey("lunatic_crystal_shuriken");
        public static final ResourceKey<Item> ARSONIST_SHURIKEN = createKey("arsonist_shuriken");
        public static final ResourceKey<Item> LIGHTNING_SHURIKEN = createKey("lightning_shuriken");

        //food
        public static final ResourceKey<Item> AERIAL_BERRY = createKey("aerial_berry");
        public static final ResourceKey<Item> ROASTED_AERIAL_BERRY = createKey("roasted_aerial_berry");
        public static final ResourceKey<Item> VIBRANT_AERIAL_BERRY = createKey("vibrant_aerial_berry");
        public static final ResourceKey<Item> FROZEN_AERIAL_BERRY = createKey("frozen_aerial_berry");
        public static final ResourceKey<Item> STELLAR_BREAD = createKey("stellar_bread");
        public static final ResourceKey<Item> FROZEN_MUTTON = createKey("frozen_mutton");
        public static final ResourceKey<Item> VIBRANT_CHICKEN = createKey("vibrant_chicken");
        public static final ResourceKey<Item> FROZEN_CHICKEN = createKey("frozen_chicken");
        public static final ResourceKey<Item> RUBY_AERIAL_BERRY = createKey("ruby_aerial_berry");
        public static final ResourceKey<Item> VOLUCITE_AERIAL_BERRY = createKey("volucite_aerial_berry");
        public static final ResourceKey<Item> GLOWING_STICK_FRUIT = createKey("glowing_stick_fruit");
        public static final ResourceKey<Item> VIBRANT_GLOWING_STICK_FRUIT = createKey("vibrant_glowing_stick_fruit");
        public static final ResourceKey<Item> FROZEN_GLOWING_STICK_FRUIT = createKey("frozen_glowing_stick_fruit");
        public static final ResourceKey<Item> CORTINARIUS_VIOLACEUS_PIECE = createKey("cortinarius_violaceus_piece");
        public static final ResourceKey<Item> GANODERMA_APPLANATUM_PIECE = createKey("ganoderma_applanatum_piece");
        public static final ResourceKey<Item> DARK_SHADOW_FRUIT = createKey("dark_shadow_fruit");
        public static final ResourceKey<Item> PURPLE_SHADOW_FRUIT = createKey("purple_shadow_fruit");
        public static final ResourceKey<Item> SHADOW_FRUIT_STEW = createKey("shadow_fruit_stew");
        public static final ResourceKey<Item> SOLID_ETHER_SOUP = createKey("solid_ether_soup");
        public static final ResourceKey<Item> VIBRANT_SOLID_ETHER_SOUP = createKey("vibrant_solid_ether_soup");
        public static final ResourceKey<Item> FROZEN_SOLID_ETHER_SOUP = createKey("frozen_solid_ether_soup");
        public static final ResourceKey<Item> SHADOW_SPIDER_EYE = createKey("shadow_spider_eye");
        public static final ResourceKey<Item> PHANTOM_MEAT = createKey("phantom_meat");
        public static final ResourceKey<Item> VIBRANT_PHANTOM_MEAT = createKey("vibrant_phantom_meat");
        public static final ResourceKey<Item> FROZEN_PHANTOM_MEAT = createKey("frozen_phantom_meat");
        public static final ResourceKey<Item> COOKED_PHANTOM_MEAT = createKey("cooked_phantom_meat");
        public static final ResourceKey<Item> TURTLE_MEAT = createKey("turtle_meat");
        public static final ResourceKey<Item> VIBRANT_TURTLE_MEAT = createKey("vibrant_turtle_meat");
        public static final ResourceKey<Item> FROZEN_TURTLE_MEAT = createKey("frozen_turtle_meat");
        public static final ResourceKey<Item> COOKED_TURTLE_MEAT = createKey("cooked_turtle_meat");
        public static final ResourceKey<Item> GODS_VOLUCITE_AERIAL_BERRY = createKey("gods_volucite_aerial_berry");
        public static final ResourceKey<Item> COPPER_PINE_CONE = createKey("copper_pine_cone");
        public static final ResourceKey<Item> AZURITE_COPPER_PINE_CONE = createKey("azurite_copper_pine_cone");
        public static final ResourceKey<Item> PHOENIX_FEATHER = createKey("phoenix_feather");
        public static final ResourceKey<Item> SKY_CACTUS_FIBER = createKey("sky_cactus_fiber");
        public static final ResourceKey<Item> VIBRANT_SKY_CACTUS_FIBER = createKey("vibrant_sky_cactus_fiber");
        public static final ResourceKey<Item> WHITE_SOLID_ETHER_FRAGMENT = createKey("white_solid_ether_fragment");
        public static final ResourceKey<Item> BLUE_SOLID_ETHER_FRAGMENT = createKey("blue_solid_ether_fragment");
        public static final ResourceKey<Item> GOLDEN_SOLID_ETHER_FRAGMENT = createKey("golden_solid_ether_fragment");
        public static final ResourceKey<Item> GREEN_SOLID_ETHER_FRAGMENT = createKey("green_solid_ether_fragment");
        public static final ResourceKey<Item> PURPLE_SOLID_ETHER_FRAGMENT = createKey("purple_solid_ether_fragment");
        public static final ResourceKey<Item> GOLDEN_NETHER_MEAT_PIECE = createKey("golden_nether_meat_piece");
        public static final ResourceKey<Item> GOLDEN_NETHER_STEAK = createKey("golden_nether_steak");
        public static final ResourceKey<Item> VIBRANT_GOLDEN_NETHER_STEAK = createKey("vibrant_golden_nether_steak");

        //buckets
        public static final ResourceKey<Item> IRON_LIQUID_OF_GODS_BUCKET = createKey("iron_liquid_of_gods_bucket");
        public static final ResourceKey<Item> RUBY_LIQUID_OF_GODS_BUCKET = createKey("ruby_liquid_of_gods_bucket");
        public static final ResourceKey<Item> RUBY_BUCKET = createKey("ruby_bucket");
        public static final ResourceKey<Item> RUBY_WATER_BUCKET = createKey("ruby_water_bucket");
        public static final ResourceKey<Item> RUBY_MILK_BUCKET = createKey("ruby_milk_bucket");

        //arrows & bows
        public static final ResourceKey<Item> RUBY_BLOWPIPE_ARROW = createKey("ruby_blowpipe_arrow");
        public static final ResourceKey<Item> VOLUCITE_BLOWPIPE_ARROW = createKey("volucite_blowpipe_arrow");

        public static final ResourceKey<Item> RUBY_BLOWPIPE = createKey("ruby_blowpipe");
        public static final ResourceKey<Item> VOLUCITE_BLOWPIPE = createKey("volucite_blowpipe");

        //music discs
        public static final ResourceKey<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = createKey("music_disc_aerial_hell_theme_tommaup");
        public static final ResourceKey<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = createKey("music_disc_sweden_andreas_zoeller");
        public static final ResourceKey<Item> MUSIC_DISC_ENTHUSIAST_TOURS = createKey("music_disc_enthusiast_tours");
        public static final ResourceKey<Item> MUSIC_DISC_BMINOR_ARULO = createKey("music_disc_bminor_arulo");
        public static final ResourceKey<Item> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = createKey("music_disc_retro_exploration_tommaup");

        //tools
        public static final ResourceKey<Item> SKY_WOOD_PICKAXE = createKey("sky_wood_pickaxe");
        public static final ResourceKey<Item> STELLAR_STONE_PICKAXE = createKey("stellar_stone_pickaxe");
        public static final ResourceKey<Item> RUBY_PICKAXE = createKey("ruby_pickaxe");
        public static final ResourceKey<Item> AZURITE_PICKAXE = createKey("azurite_pickaxe");
        public static final ResourceKey<Item> MAGMATIC_GEL_PICKAXE = createKey("magmatic_gel_pickaxe");
        public static final ResourceKey<Item> OBSIDIAN_PICKAXE = createKey("obsidian_pickaxe");
        public static final ResourceKey<Item> VOLUCITE_PICKAXE = createKey("volucite_pickaxe");
        public static final ResourceKey<Item> LUNATIC_PICKAXE = createKey("lunatic_pickaxe");
        public static final ResourceKey<Item> ARSONIST_PICKAXE = createKey("arsonist_pickaxe");

        public static final ResourceKey<Item> MAGMA_CUBE_PICKAXE = createKey("magma_cube_pickaxe");
        public static final ResourceKey<Item> STELLAR_STONE_BREAKER = createKey("stellar_stone_breaker");

        public static final ResourceKey<Item> SKY_WOOD_SHOVEL = createKey("sky_wood_shovel");
        public static final ResourceKey<Item> STELLAR_STONE_SHOVEL = createKey("stellar_stone_shovel");
        public static final ResourceKey<Item> RUBY_SHOVEL = createKey("ruby_shovel");
        public static final ResourceKey<Item> AZURITE_SHOVEL = createKey("azurite_shovel");
        public static final ResourceKey<Item> MAGMATIC_GEL_SHOVEL = createKey("magmatic_gel_shovel");
        public static final ResourceKey<Item> OBSIDIAN_SHOVEL = createKey("obsidian_shovel");
        public static final ResourceKey<Item> VOLUCITE_SHOVEL = createKey("volucite_shovel");
        public static final ResourceKey<Item> LUNATIC_SHOVEL = createKey("lunatic_shovel");
        public static final ResourceKey<Item> ARSONIST_SHOVEL = createKey("arsonist_shovel");

        public static final ResourceKey<Item> MAGMA_CUBE_SHOVEL = createKey("magma_cube_shovel");

        public static final ResourceKey<Item> SKY_WOOD_AXE = createKey("sky_wood_axe");
        public static final ResourceKey<Item> STELLAR_STONE_AXE = createKey("stellar_stone_axe");
        public static final ResourceKey<Item> RUBY_AXE = createKey("ruby_axe");
        public static final ResourceKey<Item> AZURITE_AXE = createKey("azurite_axe");
        public static final ResourceKey<Item> MAGMATIC_GEL_AXE = createKey("magmatic_gel_axe");
        public static final ResourceKey<Item> OBSIDIAN_AXE = createKey("obsidian_axe");
        public static final ResourceKey<Item> VOLUCITE_AXE = createKey("volucite_axe");
        public static final ResourceKey<Item> LUNATIC_AXE = createKey("lunatic_axe");
        public static final ResourceKey<Item> ARSONIST_AXE = createKey("arsonist_axe");

        public static final ResourceKey<Item> HEAVY_AXE = createKey("heavy_axe");
        public static final ResourceKey<Item> AXE_OF_LIGHT = createKey("axe_of_light");
        public static final ResourceKey<Item> CURSED_AXE = createKey("cursed_axe");
        public static final ResourceKey<Item> BERSERK_AXE = createKey("berserk_axe");

        public static final ResourceKey<Item> SKY_WOOD_HOE = createKey("sky_wood_hoe");
        public static final ResourceKey<Item> STELLAR_STONE_HOE = createKey("stellar_stone_hoe");
        public static final ResourceKey<Item> RUBY_HOE = createKey("ruby_hoe");
        public static final ResourceKey<Item> AZURITE_HOE = createKey("azurite_hoe");
        public static final ResourceKey<Item> MAGMATIC_GEL_HOE = createKey("magmatic_gel_hoe");
        public static final ResourceKey<Item> OBSIDIAN_HOE = createKey("obsidian_hoe");
        public static final ResourceKey<Item> VOLUCITE_HOE = createKey("volucite_hoe");
        public static final ResourceKey<Item> LUNATIC_HOE = createKey("lunatic_hoe");
        public static final ResourceKey<Item> ARSONIST_HOE = createKey("arsonist_hoe");

        public static final ResourceKey<Item> REAPER_SCYTHE = createKey("reaper_scythe");

        //weapons
        public static final ResourceKey<Item> SKY_WOOD_SWORD = createKey("sky_wood_sword");
        public static final ResourceKey<Item> STELLAR_STONE_SWORD = createKey("stellar_stone_sword");
        public static final ResourceKey<Item> RUBY_SWORD = createKey("ruby_sword");
        public static final ResourceKey<Item> AZURITE_SWORD = createKey("azurite_sword");
        public static final ResourceKey<Item> MAGMATIC_GEL_SWORD = createKey("magmatic_gel_sword");
        public static final ResourceKey<Item> OBSIDIAN_SWORD = createKey("obsidian_sword");
        public static final ResourceKey<Item> VOLUCITE_SWORD = createKey("volucite_sword");
        public static final ResourceKey<Item> LUNATIC_SWORD = createKey("lunatic_sword");
        public static final ResourceKey<Item> ARSONIST_SWORD = createKey("arsonist_sword");

        public static final ResourceKey<Item> HEAVY_SWORD = createKey("heavy_sword");
        public static final ResourceKey<Item> HEALTH_BOOST_SWORD = createKey("health_boost_sword");
        public static final ResourceKey<Item> NINJA_SWORD = createKey("ninja_sword");
        public static final ResourceKey<Item> NINJA_MASTER_SWORD = createKey("ninja_master_sword");
        public static final ResourceKey<Item> GLOUTON_SWORD = createKey("glouton_sword");
        public static final ResourceKey<Item> RANDOM_SWORD = createKey("random_sword");
        public static final ResourceKey<Item> DISLOYAL_SWORD = createKey("disloyal_sword");
        public static final ResourceKey<Item> CURSED_SWORD = createKey("cursed_sword");
        public static final ResourceKey<Item> ABSOLUTE_ZERO_SWORD = createKey("absolute_zero_sword");
        public static final ResourceKey<Item> SWORD_OF_LIGHT = createKey("sword_of_light");
        public static final ResourceKey<Item> ANTIDOTE_SWORD = createKey("antidote_sword");
        public static final ResourceKey<Item> NETHERIAN_KING_SWORD = createKey("netherian_king_sword");
        public static final ResourceKey<Item> GLASS_CANON_SWORD = createKey("glass_canon_sword");
        public static final ResourceKey<Item> GOD_SWORD = createKey("god_sword");

        public static final ResourceKey<Item> FORGOTTEN_BATTLE_TRIDENT = createKey("forgotten_battle_trident");

        //armor
        public static final ResourceKey<Item> RUBY_HELMET = createKey("ruby_helmet");
        public static final ResourceKey<Item> RUBY_CHESTPLATE = createKey("ruby_chestplate");
        public static final ResourceKey<Item> RUBY_LEGGINGS = createKey("ruby_leggings");
        public static final ResourceKey<Item> RUBY_BOOTS = createKey("ruby_boots");

        public static final ResourceKey<Item> AZURITE_HELMET = createKey("azurite_helmet");
        public static final ResourceKey<Item> AZURITE_CHESTPLATE = createKey("azurite_chestplate");
        public static final ResourceKey<Item> AZURITE_LEGGINGS = createKey("azurite_leggings");
        public static final ResourceKey<Item> AZURITE_BOOTS = createKey("azurite_boots");

        public static final ResourceKey<Item> OBSIDIAN_HELMET = createKey("obsidian_helmet");
        public static final ResourceKey<Item> OBSIDIAN_CHESTPLATE = createKey("obsidian_chestplate");
        public static final ResourceKey<Item> OBSIDIAN_LEGGINGS = createKey("obsidian_leggings");
        public static final ResourceKey<Item> OBSIDIAN_BOOTS = createKey("obsidian_boots");

        public static final ResourceKey<Item> VOLUCITE_HELMET = createKey("volucite_helmet");
        public static final ResourceKey<Item> VOLUCITE_CHESTPLATE = createKey("volucite_chestplate");
        public static final ResourceKey<Item> VOLUCITE_LEGGINGS = createKey("volucite_leggings");
        public static final ResourceKey<Item> VOLUCITE_BOOTS = createKey("volucite_boots");

        public static final ResourceKey<Item> MAGMATIC_GEL_HELMET = createKey("magmatic_gel_helmet");
        public static final ResourceKey<Item> MAGMATIC_GEL_CHESTPLATE = createKey("magmatic_gel_chestplate");
        public static final ResourceKey<Item> MAGMATIC_GEL_LEGGINGS = createKey("magmatic_gel_leggings");
        public static final ResourceKey<Item> MAGMATIC_GEL_BOOTS = createKey("magmatic_gel_boots");

        public static final ResourceKey<Item> LUNATIC_HELMET = createKey("lunatic_helmet");
        public static final ResourceKey<Item> LUNATIC_CHESTPLATE = createKey("lunatic_chestplate");
        public static final ResourceKey<Item> LUNATIC_LEGGINGS = createKey("lunatic_leggings");
        public static final ResourceKey<Item> LUNATIC_BOOTS = createKey("lunatic_boots");

        public static final ResourceKey<Item> ARSONIST_HELMET = createKey("arsonist_helmet");
        public static final ResourceKey<Item> ARSONIST_CHESTPLATE = createKey("arsonist_chestplate");
        public static final ResourceKey<Item> ARSONIST_LEGGINGS = createKey("arsonist_leggings");
        public static final ResourceKey<Item> ARSONIST_BOOTS = createKey("arsonist_boots");

        public static final ResourceKey<Item> SHADOW_HELMET = createKey("shadow_helmet");
        public static final ResourceKey<Item> SHADOW_CHESTPLATE = createKey("shadow_chestplate");
        public static final ResourceKey<Item> SHADOW_LEGGINGS = createKey("shadow_leggings");
        public static final ResourceKey<Item> SHADOW_BOOTS = createKey("shadow_boots");

        //effect totems
        public static final ResourceKey<Item> REGENERATION_TOTEM = createKey("regeneration_totem");
        public static final ResourceKey<Item> SPEED_TOTEM = createKey("speed_totem");
        public static final ResourceKey<Item> SPEED_II_TOTEM = createKey("speed_ii_totem");
        public static final ResourceKey<Item> NIGHT_VISION_TOTEM = createKey("night_vision_totem");
        public static final ResourceKey<Item> AGILITY_TOTEM = createKey("agility_totem");
        public static final ResourceKey<Item> HERO_TOTEM = createKey("hero_totem");
        public static final ResourceKey<Item> HEAD_IN_THE_CLOUDS_TOTEM = createKey("head_in_the_clouds_totem");
        public static final ResourceKey<Item> GOD_TOTEM = createKey("god_totem");
        public static final ResourceKey<Item> CURSED_TOTEM = createKey("cursed_totem");
        public static final ResourceKey<Item> SHADOW_TOTEM = createKey("shadow_totem");

        //spawn eggs
        public static final ResourceKey<Item> STELLAR_STONE_AUTOMATON_SPAWN_EGG = createKey("stellar_stone_automaton_spawn_egg");
        public static final ResourceKey<Item> EVIL_COW_SPAWN_EGG = createKey("evil_cow_spawn_egg");
        public static final ResourceKey<Item> CORTINARIUS_COW_SPAWN_EGG = createKey("cortinarius_cow_spawn_egg");
        public static final ResourceKey<Item> STELLAR_ENT_SPAWN_EGG = createKey("stellar_ent_spawn_egg");
        public static final ResourceKey<Item> VENOMOUS_SNAKE_SPAWN_EGG = createKey("venomous_snake_spawn_egg");
        public static final ResourceKey<Item> WORM_SPAWN_EGG = createKey("worm_spawn_egg");
        public static final ResourceKey<Item> STELLAR_CHICKEN_SPAWN_EGG = createKey("stellar_chicken_spawn_egg");
        public static final ResourceKey<Item> STELLAR_BOAR_SPAWN_EGG = createKey("stellar_boar_spawn_egg");
        public static final ResourceKey<Item> SHROOMBOOM_SPAWN_EGG = createKey("shroomboom_spawn_egg");
        public static final ResourceKey<Item> VERDIGRIS_ZOMBIE_SPAWN_EGG = createKey("verdigris_zombie_spawn_egg");
        public static final ResourceKey<Item> MUMMY_SPAWN_EGG = createKey("mummy_spawn_egg");
        public static final ResourceKey<Item> SLIME_PIRATE_SPAWN_EGG = createKey("slime_pirate_spawn_egg");
        public static final ResourceKey<Item> SLIME_NINJA_PIRATE_SPAWN_EGG = createKey("slime_ninja_pirate_spawn_egg");
        public static final ResourceKey<Item> GHOST_SLIME_PIRATE_SPAWN_EGG = createKey("ghost_slime_pirate_spawn_egg");
        public static final ResourceKey<Item> GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = createKey("ghost_slime_ninja_pirate_spawn_egg");
        public static final ResourceKey<Item> SANDY_SHEEP_SPAWN_EGG = createKey("sandy_sheep_spawn_egg");
        public static final ResourceKey<Item> GLIDING_TURTLE_SPAWN_EGG = createKey("gliding_turtle_spawn_egg");
        public static final ResourceKey<Item> FAT_PHANTOM_SPAWN_EGG = createKey("fat_phantom_spawn_egg");
        public static final ResourceKey<Item> KODAMA_SPAWN_EGG = createKey("kodama_spawn_egg");
        public static final ResourceKey<Item> MUD_GOLEM_SPAWN_EGG = createKey("mud_golem_spawn_egg");
        public static final ResourceKey<Item> MUD_SOLDIER_SPAWN_EGG = createKey("mud_soldier_spawn_egg");
        public static final ResourceKey<Item> MUD_CYCLE_MAGE_SPAWN_EGG = createKey("mud_cycle_mage_spawn_egg");
        public static final ResourceKey<Item> HELL_SPIDER_SPAWN_EGG = createKey("hell_spider_spawn_egg");
        public static final ResourceKey<Item> CRYSTAL_GOLEM_SPAWN_EGG = createKey("crystal_golem_spawn_egg");
        public static final ResourceKey<Item> CRYSTAL_SLIME_SPAWN_EGG = createKey("crystal_slime_spawn_egg");
        public static final ResourceKey<Item> CRYSTAL_SPIDER_SPAWN_EGG = createKey("crystal_spider_spawn_egg");
        public static final ResourceKey<Item> LUNATIC_PRIEST_SPAWN_EGG = createKey("lunatic_priest_spawn_egg");
        public static final ResourceKey<Item> CRYSTAL_CATERPILLAR_SPAWN_EGG = createKey("crystal_caterpillar_spawn_egg");
        public static final ResourceKey<Item> FOREST_CATERPILLAR_SPAWN_EGG = createKey("forest_caterpillar_spawn_egg");
        public static final ResourceKey<Item> TORN_SPIRIT_SPAWN_EGG = createKey("torn_spirit_spawn_egg");
        public static final ResourceKey<Item> CHAINED_GOD_SPAWN_EGG = createKey("chained_god_spawn_egg");
        public static final ResourceKey<Item> ICE_SPIRIT_SPAWN_EGG = createKey("ice_spirit_spawn_egg");
        public static final ResourceKey<Item> FIRE_SPIRIT_SPAWN_EGG = createKey("fire_spirit_spawn_egg");
        public static final ResourceKey<Item> ELECTRO_SPIRIT_SPAWN_EGG = createKey("electro_spirit_spawn_egg");
        public static final ResourceKey<Item> FLYING_JELLYFISH_SPAWN_EGG = createKey("flying_jellyfish_spawn_egg");
        public static final ResourceKey<Item> SHADOW_FLYING_SKULL_SPAWN_EGG = createKey("shadow_flying_skull_spawn_egg");
        public static final ResourceKey<Item> SHADOW_TROLL_SPAWN_EGG = createKey("shadow_troll_spawn_egg");
        public static final ResourceKey<Item> SHADOW_AUTOMATON_SPAWN_EGG = createKey("shadow_automaton_spawn_egg");
        public static final ResourceKey<Item> SHADOW_SPIDER_SPAWN_EGG = createKey("shadow_spider_spawn_egg");
        public static final ResourceKey<Item> LILITH_SPAWN_EGG = createKey("lilith_spawn_egg");

        public static final ResourceKey<Item> AERIAL_HELL_PAINTING = createKey("aerial_hell_painting");

        //build items
        public static final ResourceKey<Item> BLOCK_UPDATER = createKey("block_updater");
        public static final ResourceKey<Item> BLOCK_CRACKER = createKey("block_cracker");
        public static final ResourceKey<Item> STRUCTURE_VOID_PLACER = createKey("structure_void_placer");

        private static ResourceKey<Item> createKey(String name)
        {
            return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));
        }
    }
}
