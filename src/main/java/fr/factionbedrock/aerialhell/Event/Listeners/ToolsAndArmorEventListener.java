package fr.factionbedrock.aerialhell.Event.Listeners;

public class ToolsAndArmorEventListener
{
	/*
	 *  Everything related to Projectile Impact, Entity damage or player break/interaction Events is done in Mixins :
	 *
	 *  Projectile impact : ImpactFromThrownEntityMixin, ImpactFromPersistentProjectileEntityMixin
	 *
	 *  Living Entity damage : LivingDamageMixin
	 *
	 *  Player harvest (mining speed) : BlockBreakingSpeedMixin
	 *
	 *  Break reach : TODO
	 *
	 */

	/*public static void addReach(PlayerInteractEvent.LeftClickBlock event) TODO
	{
		Item item = event.getItemStack().getItem();
		if ((item == AerialHellBlocksAndItems.REAPER_SCYTHE.get() || item == AerialHellBlocksAndItems.FORGOTTEN_BATTLE_TRIDENT.get()) && event.getHand() == InteractionHand.MAIN_HAND)
		{
			PlayerEntity player = event.getEntity();
			UUID modifierUUID = UUID.fromString("6127DB5B-1AE8-4030-940E-512C1F160890");
			AttributeInstance reachDistance = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
			if (reachDistance != null)
			{
				AttributeModifier modifier = reachDistance.getModifier(modifierUUID);
				double amount = 0;
				if (modifier != null) {amount = modifier.amount(); reachDistance.removeModifier(modifier);}
				AttributeModifier newModifier = new AttributeModifier(modifierUUID, "Tool modifier",amount + 2.0, AttributeModifier.Operation.ADD_VALUE);
			}
		}
	}*/


}
