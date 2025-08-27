package com.gtnh.gtnhvariants.mixins.early;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import cpw.mods.fml.common.FMLCommonHandler;

@Mixin(FMLCommonHandler.class) 
public abstract class FMLCommonHandlerMixin {
    @Inject(method = "handleWorldDataSave", at = @At("TAIL"), remap = false)
    private void gtnhvariants$handleWorldDataSave(SaveHandler handler, WorldInfo worldInfo, NBTTagCompound tagCompound, CallbackInfo ci) {
        GTNHWorldConfig.instance().SaveToNBT(tagCompound);
    }

    @Inject(method = "handleWorldDataLoad", at = @At("TAIL"), remap = false)
    private void gtnhvariants$handleWorldDataLoad(SaveHandler handler, WorldInfo worldInfo, NBTTagCompound tagCompound, CallbackInfo ci) {
        GTNHWorldConfig.instance().LoadFromNBT(tagCompound);
    }
}