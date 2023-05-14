package fr.factionbedrock.aerialhell.Inventory.Menu;

import fr.factionbedrock.aerialhell.Block.AerialHellCraftingTableBlock;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class AerialHellCraftingMenu extends CraftingMenu
{
    private final AerialHellCraftingTableBlock craftingTableBlock;

    public AerialHellCraftingMenu(int id, Inventory inventory, ContainerLevelAccess access, AerialHellCraftingTableBlock block) {super(id, inventory, access); this.craftingTableBlock = block;}

    @Override
    public boolean stillValid(Player player) {return stillValid(this.access, player, this.craftingTableBlock);}
}
