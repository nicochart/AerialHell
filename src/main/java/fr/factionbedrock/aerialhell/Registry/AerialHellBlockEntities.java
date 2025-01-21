package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.BlockEntity.*;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;

import com.google.common.collect.Sets;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellBlockEntities
{
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AerialHell.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OscillatorBlockEntity>> OSCILLATOR = BLOCK_ENTITY_TYPES.register("oscillator", () ->
			new BlockEntityType<>(OscillatorBlockEntity::new, Sets.newHashSet(AerialHellBlocks.OSCILLATOR.get())));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FreezerBlockEntity>> FREEZER = BLOCK_ENTITY_TYPES.register("freezer", () ->
			new BlockEntityType<>(FreezerBlockEntity::new, Sets.newHashSet(AerialHellBlocks.FREEZER.get())));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IntangibleTemporaryBlockEntity>> INTANGIBLE_TEMPORARY_BLOCK = BLOCK_ENTITY_TYPES.register("intangible_temporary_block", () ->
			new BlockEntityType<>(IntangibleTemporaryBlockEntity::new, Sets.newHashSet(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK.get())));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BiomeShifterBlockEntity>> BIOME_SHIFTER = BLOCK_ENTITY_TYPES.register("biome_shifter", () ->
			new BlockEntityType<>((pos, blockState) -> new BiomeShifterBlockEntity(pos, blockState, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null), Sets.newHashSet
					(
							AerialHellBlocks.FLUORITE_ORE.get(),
							AerialHellBlocks.FLUORITE_BLOCK.get()
					)));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReactorBlockEntity>> REACTOR = BLOCK_ENTITY_TYPES.register("reactor", () ->
			new BlockEntityType<>((pos, blockState) -> new ReactorBlockEntity(pos, blockState, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null), Sets.newHashSet
					(
							AerialHellBlocks.WEAK_LIGHT_REACTOR.get(),
							AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR.get(),
							AerialHellBlocks.WEAK_SHADOW_REACTOR.get(),
							AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR.get()
					)));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StellarFurnaceBlockEntity>> STELLAR_FURNACE = BLOCK_ENTITY_TYPES.register("stellar_furnace", () ->
			new BlockEntityType<>(StellarFurnaceBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocks.STELLAR_FURNACE.get(),
							AerialHellBlocks.GHOST_STELLAR_FURNACE.get()
					)));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AerialHellSignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () ->
			new BlockEntityType<>(AerialHellSignBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocks.AERIAL_TREE_STANDING_SIGN.get(),
							AerialHellBlocks.AERIAL_TREE_WALL_SIGN.get(),
							AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN.get(),
							AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN.get(),
							AerialHellBlocks.COPPER_PINE_STANDING_SIGN.get(),
							AerialHellBlocks.COPPER_PINE_WALL_SIGN.get(),
							AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN.get(),
							AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN.get(),
							AerialHellBlocks.SHADOW_PINE_STANDING_SIGN.get(),
							AerialHellBlocks.SHADOW_PINE_WALL_SIGN.get(),
							AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN.get(),
							AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN.get(),
							AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN.get(),
							AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN.get(),
							AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN.get(),
							AerialHellBlocks.GRAY_SHROOM_WALL_SIGN.get()
					)));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AerialHellHangingSignBlockEntity>> HANGING_SIGN = BLOCK_ENTITY_TYPES.register("hanging_sign", () ->
			new BlockEntityType<>(AerialHellHangingSignBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocks.AERIAL_TREE_HANGING_SIGN.get(),
							AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN.get(),
							AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.COPPER_PINE_HANGING_SIGN.get(),
							AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN.get(),
							AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.SHADOW_PINE_HANGING_SIGN.get(),
							AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN.get(),
							AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN.get(),
							AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.get(),
							AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN.get(),
							AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN.get()
					)));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AerialHellBarrelBlockEntity>> BARREL = BLOCK_ENTITY_TYPES.register("barrel", () ->
	new BlockEntityType<>(AerialHellBarrelBlockEntity::new, Sets.newHashSet
			(
					AerialHellBlocks.AERIAL_TREE_BARREL.get(),
					AerialHellBlocks.GOLDEN_BEECH_BARREL.get(),
					AerialHellBlocks.COPPER_PINE_BARREL.get(),
					AerialHellBlocks.LAPIS_ROBINIA_BARREL.get(),
					AerialHellBlocks.SHADOW_PINE_BARREL.get(),
					AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL.get(),
					AerialHellBlocks.SKY_CACTUS_FIBER_BARREL.get(),
					AerialHellBlocks.GRAY_SHROOM_BARREL.get(),
					AerialHellBlocks.GHOST_BOAT_BARREL.get()
			)));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AerialHellChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest", () ->
	        new BlockEntityType<>(AerialHellChestBlockEntity::new, Sets.newHashSet
	        		(
	        				AerialHellBlocks.AERIAL_TREE_CHEST.get(),
	        				AerialHellBlocks.GOLDEN_BEECH_CHEST.get(),
	        				AerialHellBlocks.COPPER_PINE_CHEST.get(),
	        				AerialHellBlocks.LAPIS_ROBINIA_CHEST.get(),
	        				AerialHellBlocks.SHADOW_PINE_CHEST.get(),
	        				AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST.get(),
	        				AerialHellBlocks.SKY_CACTUS_FIBER_CHEST.get(),
	        				AerialHellBlocks.GRAY_SHROOM_CHEST.get(),
	        				AerialHellBlocks.GHOST_BOAT_CHEST.get(),
	        				AerialHellBlocks.MUD_CHEST.get(),
	        				AerialHellBlocks.LUNATIC_CHEST.get(),
	        				AerialHellBlocks.SHADOW_CATACOMBS_CHEST.get(),
	        				AerialHellBlocks.VOLUCITE_CHEST.get(),
	        				AerialHellBlocks.GOLDEN_NETHER_CHEST.get()
	        		)));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChestMimicBlockEntity>> CHEST_MIMIC = BLOCK_ENTITY_TYPES.register("chest_mimic", () ->
			new BlockEntityType<>(ChestMimicBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get(),
							AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get(),
							AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get(),
							AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC.get()
					)));
}
