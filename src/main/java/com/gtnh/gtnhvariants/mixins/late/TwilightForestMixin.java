package com.gtnh.gtnhvariants.mixins.late;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import twilightforest.block.BlockTFPortal;

@Mixin(BlockTFPortal.class)
public class TwilightForestMixin {

    @Inject(method = "tryToCreatePortal", at = @At("HEAD"), cancellable = true, remap = false)
    public void gtnhvariants$tryToCreatePortal(World world, int dx, int dy, int dz,
        CallbackInfoReturnable<Boolean> ci) {
        if (GTNHWorldConfig.instance()
            .IsPortalsDisabled()) {
            for (Object playerObj : world.playerEntities) {
                EntityPlayer player = (EntityPlayer) playerObj;
                double distance = player.getDistance(dx + 0.5, dy + 0.5, dz + 0.5);
                if (distance <= 10.0) {
                    player.addChatMessage(
                        new net.minecraft.util.ChatComponentText(
                            "Twilight Forest portals are disabled due to the GTNH world configuration."));
                }
            }
            ci.setReturnValue(true);
            ci.cancel();
        }
    }
}
