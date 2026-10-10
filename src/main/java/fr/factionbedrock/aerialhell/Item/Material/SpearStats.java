package fr.factionbedrock.aerialhell.Item.Material;

public record SpearStats(float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold)
{
    //spear vanilla stats presets, copied from vanilla spears (see Items.WOODEN_SPEAR, ...)
    public static final SpearStats WOOD = new SpearStats(0.65F, 0.7F, 0.75F, 5.0F, 14.0F, 10.0F, 5.1F, 15.0F, 4.6F);
    public static final SpearStats STONE = new SpearStats(0.75F, 0.82F, 0.7F, 4.5F, 13.0F, 9.0F, 5.1F, 13.75F, 4.6F);
    public static final SpearStats COPPER = new SpearStats(0.85F, 0.82F, 0.65F, 4.0F, 12.0F, 8.25F, 5.1F, 12.5F, 4.6F);
    public static final SpearStats IRON = new SpearStats(0.95F, 0.95F, 0.6F, 2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F);
    public static final SpearStats GOLD = new SpearStats(0.95F, 0.7F, 0.7F, 3.5F, 13.0F, 8.5F, 5.1F, 13.75F, 4.6F);
    public static final SpearStats DIAMOND = new SpearStats(1.05F, 1.075F, 0.5F, 3.0F, 10.0F, 6.5F, 5.1F, 10.0F, 4.6F);
    public static final SpearStats NETHERITE = new SpearStats(1.15F, 1.2F, 0.4F, 2.5F, 9.0F, 5.5F, 5.1F, 8.75F, 4.6F);

    //additional presets
    public static final SpearStats VOLUCITE = new SpearStats(1.25F, 1.2F, 0.4F, 2.5F, 9.0F, 5.25F, 5.1F, 8.5F, 4.6F);
    public static final SpearStats ARSONIST = new SpearStats(1.20F, 1.22F, 0.4F, 2.5F, 8.75F, 5.25F, 5.1F, 8.5F, 4.6F);
}