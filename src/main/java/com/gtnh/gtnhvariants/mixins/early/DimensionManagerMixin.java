package com.gtnh.gtnhvariants.mixins.early;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraftforge.common.DimensionManager;

@Mixin(DimensionManager.class) 
public class DimensionManagerMixin {
    
    @ModifyReturnValue(method = "getStaticDimensionIDs", at = @At("RETURN"), remap = false)
    private static Integer[] gtnhvariants$getStaticDimensionIDs(Integer[] original) {
        Arrays.sort(original);
        return original;
    }
}

