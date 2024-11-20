package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.*;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.*;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.*;
import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Block.Furnaces.*;
import fr.factionbedrock.aerialhell.Block.Mimics.*;
import fr.factionbedrock.aerialhell.Block.Ores.*;
import fr.factionbedrock.aerialhell.Block.Plants.*;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.*;
import fr.factionbedrock.aerialhell.Block.Plants.Vines.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.*;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.*;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.*;
import fr.factionbedrock.aerialhell.Block.Trophies.BottomSlabLikeTrophyBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Item.*;
import fr.factionbedrock.aerialhell.Item.Armor.ShadowArmorItem;
import fr.factionbedrock.aerialhell.Item.Bucket.*;
import fr.factionbedrock.aerialhell.Item.Material.*;
import fr.factionbedrock.aerialhell.Item.Shuriken.*;
import fr.factionbedrock.aerialhell.Item.Tools.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellJukeboxSongs;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellBlocksAndItems
{
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

	public static void registerCompostableItems() {
		ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_ITEM.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_TALL_GRASS_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_BALL_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_DEAD_BUSH_ITEM.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(BLUE_FLOWER_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(BLACK_ROSE_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(BELLFLOWER_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY.get().asItem(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY.get().asItem(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(FROZEN_AERIAL_BERRY.get().asItem(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY_SEEDS.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY_SEEDS.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_CONE.get().asItem(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(CORTINARIUS_VIOLACEUS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_FIBER.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_ITEM.get().asItem(), 0.4F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_FIBER.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_ITEM.get().asItem(), 0.8F);
	}

	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

		pot.addPlant(BLUE_FLOWER.getId(), POTTED_BLUE_FLOWER);
		pot.addPlant(BLACK_ROSE.getId(), POTTED_BLACK_ROSE);
		pot.addPlant(BELLFLOWER.getId(), POTTED_BELLFLOWER);
		pot.addPlant(STELLAR_FERN.getId(), POTTED_STELLAR_FERN);
		pot.addPlant(STELLAR_DEAD_BUSH.getId(), POTTED_STELLAR_DEAD_BUSH);
		pot.addPlant(SKY_CACTUS.getId(), POTTED_SKY_CACTUS);
		pot.addPlant(VIBRANT_SKY_CACTUS.getId(), POTTED_VIBRANT_SKY_CACTUS);
		pot.addPlant(AERIAL_TREE_SAPLING.getId(), POTTED_AERIAL_TREE_SAPLING);
		pot.addPlant(GOLDEN_BEECH_SAPLING.getId(), POTTED_GOLDEN_BEECH_SAPLING);
		pot.addPlant(COPPER_PINE_SAPLING.getId(), POTTED_COPPER_PINE_SAPLING);
		pot.addPlant(LAPIS_ROBINIA_SAPLING.getId(), POTTED_LAPIS_ROBINIA_SAPLING);
		pot.addPlant(SHADOW_PINE_SAPLING.getId(), POTTED_SHADOW_PINE_SAPLING);
		pot.addPlant(PURPLE_SHADOW_PINE_SAPLING.getId(), POTTED_PURPLE_SHADOW_PINE_SAPLING);
		pot.addPlant(STELLAR_JUNGLE_TREE_SAPLING.getId(), POTTED_STELLAR_JUNGLE_TREE_SAPLING);
		pot.addPlant(CORTINARIUS_VIOLACEUS.getId(), POTTED_CORTINARIUS_VIOLACEUS);
		pot.addPlant(VERDIGRIS_AGARIC.getId(), POTTED_VERDIGRIS_AGARIC);
		pot.addPlant(BLOSSOMING_VINES.getId(), POTTED_VINE_BLOSSOM);
		pot.addPlant(GLOWING_BOLETUS.getId(), POTTED_GLOWING_BOLETUS);
	}

	public static void registerAxeStrippingBlocks() {
		AxeItem.STRIPPABLES = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.STRIPPABLES)
				.put(AERIAL_TREE_LOG.get(), STRIPPED_AERIAL_TREE_LOG.get())
				.put(AERIAL_TREE_WOOD.get(), STRIPPED_AERIAL_TREE_WOOD.get())
				.put(GOLDEN_BEECH_LOG.get(), STRIPPED_GOLDEN_BEECH_LOG.get())
				.put(GOLDEN_BEECH_WOOD.get(), STRIPPED_GOLDEN_BEECH_WOOD.get())
				.put(COPPER_PINE_LOG.get(), STRIPPED_COPPER_PINE_LOG.get())
				.put(COPPER_PINE_WOOD.get(), STRIPPED_COPPER_PINE_WOOD.get())
				.put(LAPIS_ROBINIA_LOG.get(), STRIPPED_LAPIS_ROBINIA_LOG.get())
				.put(LAPIS_ROBINIA_WOOD.get(), STRIPPED_LAPIS_ROBINIA_WOOD.get())
				.put(SHADOW_PINE_LOG.get(), STRIPPED_SHADOW_PINE_LOG.get())
				.put(SHADOW_PINE_WOOD.get(), STRIPPED_SHADOW_PINE_WOOD.get())
				.put(STELLAR_JUNGLE_TREE_LOG.get(), STRIPPED_STELLAR_JUNGLE_TREE_LOG.get())
				.put(STELLAR_JUNGLE_TREE_WOOD.get(), STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get())
				.put(GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get())
				.put(GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get())
				.put(GIANT_VERDIGRIS_AGARIC_STEM.get(), STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get())
				.put(GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get())
				.build();
	}
}
