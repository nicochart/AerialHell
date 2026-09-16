package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayList;
import java.util.List;

public class VoluciteWardenMasterRenderState extends LivingEntityRenderState
{
    public final List<Vec3> leftArmPositions = new ArrayList<>(); //nullable Vec3
    public final List<Vec3> rightArmPositions = new ArrayList<>(); //nullable Vec3
}