package com.gtnh.gtnhvariants.mixins.early;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import net.minecraft.server.management.ServerConfigurationManager;

@Mixin(ServerConfigurationManager.class)
public abstract class ServerConfigurationManagerMixin {
    @ModifyArg(
        method = "createPlayerForUser",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/MinecraftServer;worldServerForDimension(I)Lnet/minecraft/world/WorldServer;"
        ),
        index = 0
    )
    private int gtnhvariants$createPlayerForUser(int dimension) {
        return GTNHWorldConfig.instance().GetPrimaryDimension();
    }
}
