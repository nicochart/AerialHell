package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Registry.AerialHellItemAbilities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//since 26.3, first person item rendering moved from ItemInHandRenderer to FirstPersonHandsAndItemsRenderer, using render states
@Mixin(FirstPersonHandsAndItemsRenderer.class)
public class UseItemInHandRendererMixin
{
    //modifies the arms position when using aerial hell ranged weapon
    @Inject(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;"), cancellable = true)
    private void modifyItemInHandRender(PlayerRenderState playerState, FirstPersonHandsAndItemsRenderState state, float partialTicks, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo callbackInfo)
    {
        int itemUseDuration = 20;
        if (itemStack.is(AerialHellItems.RUBY_RESONATOR) || itemStack.is(AerialHellItems.VOLUCITE_RESONATOR))
        {
            itemUseDuration = AerialHellItemAbilities.RESONATOR_USE_TICKS;
        }
        else {return;} //if the item is none of the Aerial Hell ranged weapons, return (and execute vanilla code)

        AvatarRenderState avatarRenderState = playerState.avatarRenderState;
        //copy of BOW part, only editing charge time (itemUseDuration)
        if (!state.isScoping && avatarRenderState != null)
        {
            boolean isMainHand = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = isMainHand ? avatarRenderState.mainArm : avatarRenderState.mainArm.getOpposite();
            int useDuration = isMainHand ? state.mainHandUseDuration : state.offHandUseDuration;
            poseStack.pushPose();

            boolean isRightArm = arm == HumanoidArm.RIGHT;
            int invert = isRightArm ? 1 : -1;
            if (avatarRenderState.isUsingItem && state.useItemRemainingTicks > 0 && avatarRenderState.useItemHand == hand)
            {
                if (!itemStack.getUseAnimation().hasCustomArmTransform()) {this.doApplyItemArmTransform(poseStack, arm, inverseArmHeight);}

                if (itemStack.getUseAnimation() == ItemUseAnimation.BOW)
                {
                    poseStack.translate(invert * -0.2785682F, 0.18344387F, 0.15731531F);
                    poseStack.rotate(Axis.XP.rotationDegrees(-13.935F));
                    poseStack.rotate(Axis.YP.rotationDegrees(invert * 35.3F));
                    poseStack.rotate(Axis.ZP.rotationDegrees(invert * -9.785F));
                    float timeHeld = useDuration - (state.useItemRemainingTicks - partialTicks + 1.0F);
                    float power = timeHeld / itemUseDuration;
                    power = (power * power + power * 2.0F) / 3.0F;
                    if (power > 1.0F) {
                        power = 1.0F;
                    }

                    if (power > 0.1F) {
                        float shakeOffset = Mth.sin((timeHeld - 0.1F) * 1.3F);
                        float shakeIntensity = power - 0.1F;
                        float shake = shakeOffset * shakeIntensity;
                        poseStack.translate(shake * 0.0F, shake * 0.004F, shake * 0.0F);
                    }

                    poseStack.translate(power * 0.0F, power * 0.0F, power * 0.04F);
                    poseStack.scale(1.0F, 1.0F, 1.0F + power * 0.2F);
                    poseStack.rotate(Axis.YN.rotationDegrees(invert * 45.0F));
                }
            }

            (isMainHand ? state.mainHandRenderState : state.offHandRenderState).submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
            callbackInfo.cancel();
        }
    }

    //copy of net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer method "applyItemArmTransform"
    private void doApplyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float inverseArmHeight)
    {
        int invert = arm == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)invert * 0.56F, -0.52F + inverseArmHeight * -0.6F, -0.72F);
    }
}
