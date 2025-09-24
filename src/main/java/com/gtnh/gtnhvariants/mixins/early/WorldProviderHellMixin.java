package com.gtnh.gtnhvariants.mixins.early;

import net.minecraft.world.WorldProviderHell;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

@Mixin(WorldProviderHell.class)
public class WorldProviderHellMixin {

    @Overwrite()
    public boolean isSurfaceWorld() {
        return GTNHWorldConfig.instance()
            .IsNetherOnly();
    }

    @Overwrite()
    public boolean canRespawnHere() {
        return GTNHWorldConfig.instance()
            .IsNetherOnly();
    }
}
