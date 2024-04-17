package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BakedDelight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
