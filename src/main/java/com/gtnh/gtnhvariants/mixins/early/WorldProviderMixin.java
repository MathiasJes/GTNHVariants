package com.gtnh.gtnhvariants.mixins.early;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.WorldType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

@Mixin(WorldProvider.class) 
public class WorldProviderMixin {

    @Shadow
    public World worldObj;
    
    @Overwrite(remap = false)
    public int getRespawnDimension(EntityPlayerMP player) {
        return GTNHWorldConfig.instance().GetPrimaryDimension();
    }

    @Inject(
        method = "getRandomizedSpawnPoint",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    public void getRandomizedSpawnPoint(CallbackInfoReturnable<ChunkCoordinates> cir)
    {
        if (GTNHWorldConfig.instance().IsVoidWorld()) {
            ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

            chunkcoordinates.posY = this.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
            cir.setReturnValue(chunkcoordinates);
            cir.cancel();
        }
    }
}