package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.StellarFurnaceMenu;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class StellarFurnaceBlockEntity extends AbstractFurnaceBlockEntity
{
	protected StellarFurnaceBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeType) {super(blockEntity, pos, state, recipeType);}

	public StellarFurnaceBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.STELLAR_FURNACE.get(), pos, state, RecipeType.SMELTING);}

	@Override
	protected Component getDefaultName()
	{
		return Component.translatable("container." + AerialHell.MODID + ".stellar_furnace");
	}

	@Override protected AbstractContainerMenu createMenu(int id, Inventory inv) {return new StellarFurnaceMenu(id, inv, this, this.dataAccess);}
}
