package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.BlockEntity.*;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellBlockEntities
{
	public static final BlockEntityType<OscillatorBlockEntity> OSCILLATOR = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("oscillator"),
			FabricBlockEntityTypeBuilder.create(OscillatorBlockEntity::new, AerialHellBlocks.OSCILLATOR).build());

	public static final BlockEntityType<FreezerBlockEntity> FREEZER = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("freezer"),
			FabricBlockEntityTypeBuilder.create(FreezerBlockEntity::new,AerialHellBlocks.FREEZER).build());

	public static final BlockEntityType<IntangibleTemporaryBlockEntity> INTANGIBLE_TEMPORARY_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("intangible_temporary_block"),
			FabricBlockEntityTypeBuilder.create(IntangibleTemporaryBlockEntity::new,AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK).build());

	public static final BlockEntityType<BiomeShifterBlockEntity> BIOME_SHIFTER = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("biome_shifter"),
			FabricBlockEntityTypeBuilder.create((pos, blockState) -> new BiomeShifterBlockEntity(pos, blockState, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null),
				AerialHellBlocks.FLUORITE_ORE,
				AerialHellBlocks.FLUORITE_BLOCK
			).build());

	public static final BlockEntityType<ReactorBlockEntity> REACTOR = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("reactor"),
			FabricBlockEntityTypeBuilder.create((pos, blockState) -> new ReactorBlockEntity(pos, blockState, BiomeShifter.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null),
				AerialHellBlocks.WEAK_LIGHT_REACTOR,
				AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR,
				AerialHellBlocks.WEAK_SHADOW_REACTOR,
				AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR
			).build());

	public static final BlockEntityType<StellarFurnaceBlockEntity> STELLAR_FURNACE = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("stellar_furnace"),
			FabricBlockEntityTypeBuilder.create(StellarFurnaceBlockEntity::new,
					AerialHellBlocks.STELLAR_FURNACE,
					AerialHellBlocks.GHOST_STELLAR_FURNACE
			).build());

	public static final BlockEntityType<AerialHellSignBlockEntity> SIGN = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("sign"),
			FabricBlockEntityTypeBuilder.create(AerialHellSignBlockEntity::new,
					AerialHellBlocks.AERIAL_TREE_STANDING_SIGN,
					AerialHellBlocks.AERIAL_TREE_WALL_SIGN,
					AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN,
					AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN,
					AerialHellBlocks.COPPER_PINE_STANDING_SIGN,
					AerialHellBlocks.COPPER_PINE_WALL_SIGN,
					AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN,
					AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN,
					AerialHellBlocks.SHADOW_PINE_STANDING_SIGN,
					AerialHellBlocks.SHADOW_PINE_WALL_SIGN,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN,
					AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN,
					AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN,
					AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN,
					AerialHellBlocks.GRAY_SHROOM_WALL_SIGN
			).build());

	public static final BlockEntityType<AerialHellHangingSignBlockEntity> HANGING_SIGN = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("hanging_sign"),
			FabricBlockEntityTypeBuilder.create(AerialHellHangingSignBlockEntity::new,
					AerialHellBlocks.AERIAL_TREE_HANGING_SIGN,
					AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN,
					AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN,
					AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN,
					AerialHellBlocks.COPPER_PINE_HANGING_SIGN,
					AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN,
					AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN,
					AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN,
					AerialHellBlocks.SHADOW_PINE_HANGING_SIGN,
					AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN,
					AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN,
					AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN,
					AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN,
					AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN
			).build());

	public static final BlockEntityType<AerialHellBarrelBlockEntity> BARREL = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("barrel"),
			FabricBlockEntityTypeBuilder.create(AerialHellBarrelBlockEntity::new,
					AerialHellBlocks.AERIAL_TREE_BARREL,
					AerialHellBlocks.GOLDEN_BEECH_BARREL,
					AerialHellBlocks.COPPER_PINE_BARREL,
					AerialHellBlocks.LAPIS_ROBINIA_BARREL,
					AerialHellBlocks.SHADOW_PINE_BARREL,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL,
					AerialHellBlocks.SKY_CACTUS_FIBER_BARREL,
					AerialHellBlocks.GRAY_SHROOM_BARREL,
					AerialHellBlocks.GHOST_BOAT_BARREL
			).build());

	public static final BlockEntityType<AerialHellChestBlockEntity> CHEST = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("chest"),
			FabricBlockEntityTypeBuilder.create(AerialHellChestBlockEntity::new,
					AerialHellBlocks.AERIAL_TREE_CHEST,
					AerialHellBlocks.GOLDEN_BEECH_CHEST,
					AerialHellBlocks.COPPER_PINE_CHEST,
					AerialHellBlocks.LAPIS_ROBINIA_CHEST,
					AerialHellBlocks.SHADOW_PINE_CHEST,
					AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST,
					AerialHellBlocks.SKY_CACTUS_FIBER_CHEST,
					AerialHellBlocks.GRAY_SHROOM_CHEST,
					AerialHellBlocks.GHOST_BOAT_CHEST,
					AerialHellBlocks.MUD_CHEST,
					AerialHellBlocks.LUNATIC_CHEST,
					AerialHellBlocks.SHADOW_CATACOMBS_CHEST,
					AerialHellBlocks.VOLUCITE_CHEST,
					AerialHellBlocks.GOLDEN_NETHER_CHEST
			).build());

	public static final BlockEntityType<ChestMimicBlockEntity> CHEST_MIMIC = Registry.register(Registries.BLOCK_ENTITY_TYPE, AerialHell.id("chest_mimic"),
			FabricBlockEntityTypeBuilder.create(ChestMimicBlockEntity::new,
					AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC,
					AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC,
					AerialHellBlocks.COPPER_PINE_CHEST_MIMIC,
					AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC
			).build());

	public static void load() {}
}
