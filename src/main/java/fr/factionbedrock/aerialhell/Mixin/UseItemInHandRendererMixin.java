package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Registry.AerialHellItemAbilities;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class UseItemInHandRendererMixin
{
    //modifies the arms position when using aerial hell ranged weapon
    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/ItemUseAnimation;"), cancellable = true)
    private void modifyItemInHandRender(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo callbackInfo)
    {
        ItemInHandRenderer itemInHandRenderer = (ItemInHandRenderer) (Object) this;

        int itemUseDuration = 20;
        if (itemStack.is(AerialHellItems.RUBY_RESONATOR) || itemStack.is(AerialHellItems.VOLUCITE_RESONATOR))
        {
            itemUseDuration = AerialHellItemAbilities.RESONATOR_USE_TICKS;
        }
        else {return;} //if the item is none of the Aerial Hell ranged weapons, return (and execute vanilla code)

        //copy of BOW part, only editing charge time (itemUseDuration)
        if (!player.isScoping())
        {
            boolean isMainHand = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = isMainHand ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();

            boolean isRightArm = arm == HumanoidArm.RIGHT;
            int invert = isRightArm ? 1 : -1;
            if (player.isUsingItem() && player.getUseItemRemainingTicks() > 0 && player.getUsedItemHand() == hand)
            {
                if (!itemStack.getUseAnimation().hasCustomArmTransform()) {this.applyItemArmTransform(poseStack, arm, inverseArmHeight);}

                if (itemStack.getUseAnimation() == ItemUseAnimation.BOW)
                {
                    poseStack.translate(invert * -0.2785682F, 0.18344387F, 0.15731531F);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-13.935F));
                    poseStack.mulPose(Axis.YP.rotationDegrees(invert * 35.3F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(invert * -9.785F));
                    float timeHeld = itemStack.getUseDuration(player) - (player.getUseItemRemainingTicks() - frameInterp + 1.0F);
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
                    poseStack.mulPose(Axis.YN.rotationDegrees(invert * 45.0F));
                }
            }

            itemInHandRenderer.renderItem(player, itemStack, isRightArm ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, poseStack, submitNodeCollector, lightCoords);

            poseStack.popPose();
            callbackInfo.cancel();
        }
    }

    //copy of net.minecraft.client.renderer.ItemInHandRenderer method of same name
    private void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm, float inverseArmHeight)
    {
        int invert = arm == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)invert * 0.56F, -0.52F + inverseArmHeight * -0.6F, -0.72F);
    }
}
