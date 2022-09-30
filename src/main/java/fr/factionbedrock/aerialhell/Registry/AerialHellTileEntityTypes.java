package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.TileEntity.VibratorTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.ChestMimicTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.FreezerTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.StellarFurnaceTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.AerialHellBarrelTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.AerialHellChestTileEntity;
import fr.factionbedrock.aerialhell.TileEntity.AerialHellSignTileEntity;

import com.google.common.collect.Sets;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellTileEntityTypes
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AerialHell.MODID);

	public static final RegistryObject<TileEntityType<VibratorTileEntity>> VIBRATOR = TILE_ENTITIES.register("vibrator", () ->
			new TileEntityType<>(VibratorTileEntity::new, Sets.newHashSet(AerialHellBlocksAndItems.VIBRATOR.get()), null));
	
	public static final RegistryObject<TileEntityType<FreezerTileEntity>> FREEZER = TILE_ENTITIES.register("freezer", () ->
			new TileEntityType<>(FreezerTileEntity::new, Sets.newHashSet(AerialHellBlocksAndItems.FREEZER.get()), null));
	
	public static final RegistryObject<TileEntityType<StellarFurnaceTileEntity>> STELLAR_FURNACE = TILE_ENTITIES.register("stellar_furnace", () ->
			new TileEntityType<>(StellarFurnaceTileEntity::new, Sets.newHashSet(AerialHellBlocksAndItems.STELLAR_FURNACE.get()), null));
	
	public static final RegistryObject<TileEntityType<AerialHellSignTileEntity>> SIGN = TILE_ENTITIES.register("sign", () ->
			new TileEntityType<>(AerialHellSignTileEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.AERIAL_TREE_SIGN.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_SIGN.get(),
							AerialHellBlocksAndItems.COPPER_PINE_SIGN.get(),
							AerialHellBlocksAndItems.LAPIS_ROBINIA_SIGN.get(),
							AerialHellBlocksAndItems.SHADOW_PINE_SIGN.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_SIGN.get(),
							AerialHellBlocksAndItems.GRAY_SHROOM_SIGN.get()
					), null));
	
	public static final RegistryObject<TileEntityType<AerialHellBarrelTileEntity>> BARREL = TILE_ENTITIES.register("barrel", () ->
	new TileEntityType<>(AerialHellBarrelTileEntity::new, Sets.newHashSet
			(
					AerialHellBlocksAndItems.AERIAL_TREE_BARREL.get(),
					AerialHellBlocksAndItems.GOLDEN_BEECH_BARREL.get(),
					AerialHellBlocksAndItems.COPPER_PINE_BARREL.get(),
					AerialHellBlocksAndItems.LAPIS_ROBINIA_BARREL.get(),
					AerialHellBlocksAndItems.SHADOW_PINE_BARREL.get(),
					AerialHellBlocksAndItems.SKY_CACTUS_FIBER_BARREL.get(),
					AerialHellBlocksAndItems.GRAY_SHROOM_BARREL.get()
			), null));
	
	public static final RegistryObject<TileEntityType<AerialHellChestTileEntity>> CHEST = TILE_ENTITIES.register("chest", () ->
	        new TileEntityType<>(AerialHellChestTileEntity::new, Sets.newHashSet
	        		(
	        				AerialHellBlocksAndItems.AERIAL_TREE_CHEST.get(),
	        				AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST.get(),
	        				AerialHellBlocksAndItems.COPPER_PINE_CHEST.get(),
	        				AerialHellBlocksAndItems.LAPIS_ROBINIA_CHEST.get(),
	        				AerialHellBlocksAndItems.SHADOW_PINE_CHEST.get(),
	        				AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST.get(),
	        				AerialHellBlocksAndItems.GRAY_SHROOM_CHEST.get(),
	        				AerialHellBlocksAndItems.MUD_CHEST.get(),
	        				AerialHellBlocksAndItems.LUNATIC_CHEST.get(),
	        				AerialHellBlocksAndItems.VOLUCITE_CHEST.get(),
	        				AerialHellBlocksAndItems.GOLDEN_NETHER_CHEST.get()
	        		), null));
	
	public static final RegistryObject<TileEntityType<ChestMimicTileEntity>> CHEST_MIMIC = TILE_ENTITIES.register("chest_mimic", () ->
			new TileEntityType<>(ChestMimicTileEntity::new, Sets.newHashSet
					(
							AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC.get(),
							AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get()
					), null));
}
