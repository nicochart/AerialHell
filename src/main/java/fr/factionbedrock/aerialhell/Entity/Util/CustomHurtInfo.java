package fr.factionbedrock.aerialhell.Entity.Util;

import org.jetbrains.annotations.Nullable;

public class CustomHurtInfo
{
    private final float amount;
    @Nullable
    private final float kbStrength;
    private final boolean playSound, applyKb;

    public CustomHurtInfo(float amount) {this(amount, 0.4F);}
    public CustomHurtInfo(float amount, float kbStrength) {this(amount, kbStrength, true, true);}

    public CustomHurtInfo(float amount, float kbStrength, boolean playSound, boolean applyKb)
    {
        this.amount = amount;
        this.kbStrength = kbStrength;
        this.playSound = playSound;
        this.applyKb = applyKb;
    }

    public float amount() {return amount;}
    @Nullable public float kbStrength() {return kbStrength;}
    public boolean playSound() {return playSound;}
    public boolean applyKb() {return applyKb;}
}
