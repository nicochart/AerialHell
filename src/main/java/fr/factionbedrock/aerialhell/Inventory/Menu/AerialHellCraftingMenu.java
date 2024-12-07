package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Block.AerialHellCraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class AerialHellCraftingMenu extends CraftingScreenHandler
{
    private final AerialHellCraftingTableBlock craftingTableBlock;

    public AerialHellCraftingMenu(int id, PlayerInventory playerInventory, ScreenHandlerContext context, AerialHellCraftingTableBlock block) {super(id, playerInventory, context); this.craftingTableBlock = block;}

    @Override
    public boolean canUse(PlayerEntity player) {return canUse(this.context, player, this.craftingTableBlock);}
}
