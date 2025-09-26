package com.gtnh.gtnhvariants.mixins.early;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {

    @Redirect(
        method = "sleepInBedAt",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;isSurfaceWorld()Z"))
    private boolean gtnhvariants$allowNetherSleeping(WorldProvider worldProvider) {
        if (GTNHWorldConfig.instance()
            .IsNetherOnly()) {
            if (worldProvider instanceof WorldProviderHell) {
                return true;
            }
        }
        return worldProvider.isSurfaceWorld();
    }
}
