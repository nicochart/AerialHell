package fr.factionbedrock.aerialhell.Entity.MultipartEntity;

//status of a child part, stored on both sides : master side in PartInfo (saved by the master), part side in MasterPartInfo (synced & saved by the part)
//master's status is the reference : if both are inconsistent, the part status is corrected by the master
public enum PartStatus
{
    ATTACHED, //positioned by the master, damage redirected to the master. if missing, the master reacts (see MasterInvalidSituationBehavior)
    SEMI_DETACHED, //independent (own health & AI) but still linked to its master : a fraction of the damage is applied to the master (never lethal). dies if the master dies, if too far from it, or if the master is not found for too long
    FULLY_DETACHED, //fully independent : master and part forget each other. damage is not dealt to the master, master's death has no effect on the part
    DEAD; //killed by the master, or dead while semi detached. no longer expected by the master

    public static PartStatus byName(String name)
    {
        for (PartStatus status : values())
        {
            if (status.name().equals(name)) {return status;}
        }
        return ATTACHED;
    }

    public static PartStatus byId(int id) {return id >= 0 && id < values().length ? values()[id] : ATTACHED;}
}
