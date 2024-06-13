package fr.factionbedrock.aerialhell.Entity.Util;

public class SnakeCustomHurtInfo extends CustomHurtInfo
{
    private final boolean shouldSendToOthers;

    public SnakeCustomHurtInfo(float amount) {super(amount); this.shouldSendToOthers = false;}

    public SnakeCustomHurtInfo(float amount, float kbStrength, boolean playSound, boolean applyKb, boolean shouldSendToOthers)
    {
        super(amount, kbStrength, playSound, applyKb);
        this.shouldSendToOthers = shouldSendToOthers;
    }

    public boolean shouldSendToOthers() {return this.shouldSendToOthers;}
}
