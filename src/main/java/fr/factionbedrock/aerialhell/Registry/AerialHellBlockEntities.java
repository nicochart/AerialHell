package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.BlockEntity.*;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;

import com.google.common.collect.Sets;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellBlockEntities
{
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AerialHell.MODID);

	public static final RegistryObject<BlockEntityType<OscillatorBlockEntity>> OSCILLATOR = BLOCK_ENTITY_TYPES.register("oscillator", () ->
			new BlockEntityType<>(OscillatorBlockEntity::new, Sets.newHashSet(AerialHellBlocksAndItems.OSCILLATOR.get()), null));
	
	public static final RegistryObject<BlockEntityType<FreezerBlockEntity>> FREEZER = BLOCK_ENTITY_TYPES.register("freezer", () ->
			new BlockEntityType<>(FreezerBlockEntity::new, Sets.newHashSet(AerialHellBlocksAndItems.FREEZER.get()), null));
	
	public static final RegistryObject<BlockEntityType<StellarFurnaceBlockEntity>> STELLAR_FURNACE = BLOCK_ENTITY_TYPES.register("stellar_furnace", () ->
			new BlockEntityType<>(StellarFurnaceBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.STELLAR_FURNACE.get(),
							AerialHellBlocksAndItems.GHOST_STELLAR_FURNACE.get()
					), null));
	
	public static final RegistryObject<BlockEntityType<AerialHellSignBlockEntity>> SIGN = BLOCK_ENTITY_TYPES.register("sign", () ->
			new BlockEntityType<>(AerialHellSignBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.AERIAL_TREE_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.AERIAL_TREE_WALL_SIGN.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_WALL_SIGN.get(),
							AerialHellBlocksAndItems.COPPER_PINE_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.COPPER_PINE_WALL_SIGN.get(),
							AerialHellBlocksAndItems.LAPIS_ROBINIA_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.LAPIS_ROBINIA_WALL_SIGN.get(),
							AerialHellBlocksAndItems.SHADOW_PINE_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.SHADOW_PINE_WALL_SIGN.get(),
							AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_WALL_SIGN.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_WALL_SIGN.get(),
							AerialHellBlocksAndItems.GRAY_SHROOM_STANDING_SIGN.get(),
							AerialHellBlocksAndItems.GRAY_SHROOM_WALL_SIGN.get()
					), null));

	public static final RegistryObject<BlockEntityType<AerialHellHangingSignBlockEntity>> HANGING_SIGN = BLOCK_ENTITY_TYPES.register("hanging_sign", () ->
			new BlockEntityType<>(AerialHellHangingSignBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.AERIAL_TREE_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.AERIAL_TREE_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.COPPER_PINE_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.COPPER_PINE_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.LAPIS_ROBINIA_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.LAPIS_ROBINIA_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.SHADOW_PINE_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.SHADOW_PINE_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.GRAY_SHROOM_HANGING_SIGN.get(),
							AerialHellBlocksAndItems.GRAY_SHROOM_WALL_HANGING_SIGN.get()
					), null));
	
	public static final RegistryObject<BlockEntityType<AerialHellBarrelBlockEntity>> BARREL = BLOCK_ENTITY_TYPES.register("barrel", () ->
	new BlockEntityType<>(AerialHellBarrelBlockEntity::new, Sets.newHashSet
			(
					AerialHellBlocksAndItems.AERIAL_TREE_BARREL.get(),
					AerialHellBlocksAndItems.GOLDEN_BEECH_BARREL.get(),
					AerialHellBlocksAndItems.COPPER_PINE_BARREL.get(),
					AerialHellBlocksAndItems.LAPIS_ROBINIA_BARREL.get(),
					AerialHellBlocksAndItems.SHADOW_PINE_BARREL.get(),
					AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_BARREL.get(),
					AerialHellBlocksAndItems.SKY_CACTUS_FIBER_BARREL.get(),
					AerialHellBlocksAndItems.GRAY_SHROOM_BARREL.get()
			), null));
	
	public static final RegistryObject<BlockEntityType<AerialHellChestBlockEntity>> CHEST = BLOCK_ENTITY_TYPES.register("chest", () ->
	        new BlockEntityType<>(AerialHellChestBlockEntity::new, Sets.newHashSet
	        		(
	        				AerialHellBlocksAndItems.AERIAL_TREE_CHEST.get(),
	        				AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST.get(),
	        				AerialHellBlocksAndItems.COPPER_PINE_CHEST.get(),
	        				AerialHellBlocksAndItems.LAPIS_ROBINIA_CHEST.get(),
	        				AerialHellBlocksAndItems.SHADOW_PINE_CHEST.get(),
	        				AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_CHEST.get(),
	        				AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST.get(),
	        				AerialHellBlocksAndItems.GRAY_SHROOM_CHEST.get(),
	        				AerialHellBlocksAndItems.GHOST_BOAT_CHEST.get(),
	        				AerialHellBlocksAndItems.MUD_CHEST.get(),
	        				AerialHellBlocksAndItems.LUNATIC_CHEST.get(),
	        				AerialHellBlocksAndItems.SHADOW_CATACOMBS_CHEST.get(),
	        				AerialHellBlocksAndItems.VOLUCITE_CHEST.get(),
	        				AerialHellBlocksAndItems.GOLDEN_NETHER_CHEST.get()
	        		), null));
	
	public static final RegistryObject<BlockEntityType<ChestMimicBlockEntity>> CHEST_MIMIC = BLOCK_ENTITY_TYPES.register("chest_mimic", () ->
			new BlockEntityType<>(ChestMimicBlockEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get()
					), null));
}
