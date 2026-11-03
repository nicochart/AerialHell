package fr.factionbedrock.aerialhell.Entity.Util;

import net.minecraft.world.entity.Entity;

import java.util.Set;

//implemented on Level by LevelLargeEntitiesMixin (both sides)
//entity lookups (Level.getEntities : collisions, attack and projectile targeting...) only check entity sections near the searched area : 2 blocks around it horizontally, 4 blocks below and 0 above
//an entity larger than this margin around its position (its section is the one of its position) is not found when the searched area is too far from its position, while touching its bounding box
//registered large entities are also found by their bounding box, like vanilla dragon parts
//registered while tracked by the level, like dragon parts (see ServerLevelEntityCallbacksMixin and ClientLevelEntityCallbacksMixin)
public interface LargeEntityLevel
{
    float MAX_WIDTH = 4.0F, MAX_HEIGHT = 4.0F; //largest size always found by the entity section lookup (2 blocks margin on each side horizontally, 4 blocks below)

    Set<Entity> aerialhell$getLargeEntities();

    static boolean isLarge(Entity entity) {return entity.getBbWidth() > MAX_WIDTH || entity.getBbHeight() > MAX_HEIGHT;}

    static void register(Entity entity) {if (isLarge(entity) && entity.level() instanceof LargeEntityLevel level) {level.aerialhell$getLargeEntities().add(entity);}} //call when level tracking starts
    static void unregister(Entity entity) {if (entity.level() instanceof LargeEntityLevel level) {level.aerialhell$getLargeEntities().remove(entity);}} //call when level tracking ends
}
