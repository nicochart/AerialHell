package fr.factionbedrock.aerialhell.Client.Util;

import java.util.ArrayList;

//saves a copy of the list to reinitialise if needed
//also has a custom "get and remove" method
public class ModelRotationList<E> extends ArrayList<E>
{
    private int nextIndex;
    public ModelRotationList() {super(); this.nextIndex = 0;}

    public ModelRotationList(ModelRotationList<E> list) {super(list); this.nextIndex = 0;}

    public E getNext()
    {
        E element = this.get(nextIndex);
        nextIndex++;
        if (nextIndex >= this.size()) {nextIndex = 0;}
        return element;
    }
}
