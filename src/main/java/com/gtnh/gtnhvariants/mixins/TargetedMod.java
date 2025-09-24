package com.gtnh.gtnhvariants.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetedMod implements ITargetMod {

    GALACTICRAFTCORE("core.asm.GCLoadingPlugin", "GalacticraftCore"),
    TWILIGHTFOREST(null, "TwilightForest");

    private final TargetModBuilder builder;

    TargetedMod(String coreModClass, String modId) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass)
            .setModId(modId);
    }

    @Override
    @Nonnull
    public TargetModBuilder getBuilder() {
        return builder;
    }
}
