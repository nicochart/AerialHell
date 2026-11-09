package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.world.item.Tier;

//old tool materials class, replaced by AerialHellToolMaterials
//kept for compatibility with other mods compiled against it (L2 Weaponry Aerial Hell compat reads ToolMaterials.arsonist, lunatic, ruby & volucite)
//fields must keep their old names and their Tier type
@Deprecated
public class ToolMaterials
{
	public static final Tier sky_wood = AerialHellToolMaterials.SKY_WOOD.vanillaMaterial();
	public static final Tier stellar_stone = AerialHellToolMaterials.STELLAR_STONE.vanillaMaterial();
	public static final Tier ruby = AerialHellToolMaterials.RUBY.vanillaMaterial();
	public static final Tier azurite = AerialHellToolMaterials.AZURITE.vanillaMaterial();
	public static final Tier magmatic_gel = AerialHellToolMaterials.MAGMATIC_GEL.vanillaMaterial();
	public static final Tier obsidian = AerialHellToolMaterials.OBSIDIAN.vanillaMaterial();
	public static final Tier shadow = AerialHellToolMaterials.SHADOW.vanillaMaterial();
	public static final Tier volucite = AerialHellToolMaterials.VOLUCITE.vanillaMaterial();
	public static final Tier heavy = AerialHellToolMaterials.HEAVY.vanillaMaterial();
	public static final Tier lunatic = AerialHellToolMaterials.LUNATIC.vanillaMaterial();
	public static final Tier breaker = AerialHellToolMaterials.BREAKER.vanillaMaterial();
	public static final Tier arsonist = AerialHellToolMaterials.ARSONIST.vanillaMaterial();
}
