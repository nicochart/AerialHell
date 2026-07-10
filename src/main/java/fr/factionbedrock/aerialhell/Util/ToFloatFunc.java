package fr.factionbedrock.aerialhell.Util;

//backport of new vanilla ToFloatFunction
@FunctionalInterface
public interface ToFloatFunc<T>
{
    float applyAsFloat(T var1);
}
