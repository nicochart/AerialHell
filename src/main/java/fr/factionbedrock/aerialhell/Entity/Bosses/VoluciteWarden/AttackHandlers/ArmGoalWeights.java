package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

//weighted probabilities of each arm goal. stored in ArmsGoalHandler, can be edited depending on boss phase
public class ArmGoalWeights
{
    private final EnumMap<ArmGoalType, Integer> weights = new EnumMap<>(ArmGoalType.class);

    public ArmGoalWeights set(ArmGoalType goalType, int weight)
    {
        this.weights.put(goalType, Math.max(0, weight));
        return this;
    }

    public int get(ArmGoalType goalType) {return this.weights.getOrDefault(goalType, 0);}

    public ArmGoalWeights clear() {this.weights.clear(); return this;}

    //replaces all weights by the given ones
    public ArmGoalWeights copyFrom(ArmGoalWeights other)
    {
        this.weights.clear();
        this.weights.putAll(other.weights);
        return this;
    }

    //picks a random goal among available ones (goals with weight <= 0 or not available are ignored). returns null if no goal can be picked
    @Nullable public ArmGoalType pick(RandomSource random, Predicate<ArmGoalType> isAvailable)
    {
        int totalWeight = 0;
        for (Map.Entry<ArmGoalType, Integer> entry : this.weights.entrySet())
        {
            if (entry.getValue() > 0 && isAvailable.test(entry.getKey())) {totalWeight += entry.getValue();}
        }
        if (totalWeight <= 0) {return null;}

        int roll = random.nextInt(totalWeight);
        for (Map.Entry<ArmGoalType, Integer> entry : this.weights.entrySet())
        {
            if (entry.getValue() > 0 && isAvailable.test(entry.getKey()))
            {
                roll -= entry.getValue();
                if (roll < 0) {return entry.getKey();}
            }
        }
        return null;
    }
}
