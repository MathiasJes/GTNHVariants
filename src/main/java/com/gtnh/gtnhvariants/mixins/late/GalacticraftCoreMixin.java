package com.gtnh.gtnhvariants.mixins.late;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntityTieredRocket;

@Mixin(EntityTieredRocket.class)
public class GalacticraftCoreMixin {

    @Inject(method = "interactFirst", at = @At("HEAD"), cancellable = true)
    public void gtnhvariants$interactFirst(net.minecraft.entity.player.EntityPlayer player,
        CallbackInfoReturnable<Boolean> ci) {
        if (GTNHWorldConfig.instance()
            .IsRocketsDisabled()) {
            player.addChatMessage(
                new net.minecraft.util.ChatComponentText(
                    "You feel so earth bound that you can't use rockets in this world. (GTNHVariants configured to diable use of rockets)."));
            ci.setReturnValue(false);
            ci.cancel();
        }
    }
}
