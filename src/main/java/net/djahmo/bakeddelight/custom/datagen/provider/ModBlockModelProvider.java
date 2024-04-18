package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.custom.block.BackingDishTypeCollection;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.djahmo.bakeddelight.utils.Function;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BakedDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        cropBlock(ModBlocks.EGGPLANT_CROP);
        cropBlock(ModBlocks.ZUCCHINI_CROP);

        uncookedDishBlock(ModBlocks.UNCOOKED_LASAGNA_DISH);
        cookedDishBlock(ModBlocks.COOKED_LASAGNA_DISH);
        uncookedDishBlock(ModBlocks.UNCOOKED_GRATIN_DISH);
        cookedDishBlock(ModBlocks.COOKED_GRATIN_DISH);
        uncookedDishBlock(ModBlocks.UNCOOKED_MOUSSAKA_DISH);
        cookedDishBlock(ModBlocks.COOKED_MOUSSAKA_DISH);
        uncookedDishBlock(ModBlocks.UNCOOKED_TIAN_DISH);
        cookedDishBlock(ModBlocks.COOKED_TIAN_DISH);
    }

    private void cookedDishBlock(RegistryObject<? extends Block> block) {
        String blockName = block.getId().getPath();
        for(int slice = 0; slice <= 5; slice++){
            withExistingParent(blockName + (slice != 0 ? "_" + slice + "slice" : ""),
                new ResourceLocation(BakedDelight.MODID, "block/" + (slice == 0 ? "baking_full_dish" : "baking_cooked_dish_"+ slice + "slice")))
                    .texture("top", new ResourceLocation(BakedDelight.MODID, "block/" + blockName.replace("dish", "top")))
                    .texture("inner", new ResourceLocation(BakedDelight.MODID, "block/" + blockName.replace("dish", "inner")));

        }
    }

    private void uncookedDishBlock(RegistryObject<? extends Block> block) {
        String blockName = block.getId().getPath();
        for(int slice = 0; slice <= 5; slice++){
            withExistingParent(blockName + (slice != 0 ? "_" + slice + "slice" : ""),
                    new ResourceLocation(BakedDelight.MODID, "block/" + (slice == 0 ? "baking_full_dish" : "baking_uncooked_dish_"+ slice + "slice")))
                    .texture("top", new ResourceLocation(BakedDelight.MODID, "block/" + getRightTexture(block, slice)));

        }
    }

    private void cropBlock(RegistryObject<? extends Block> block) {
        String cropName = block.getId().getPath().split("_")[0];
        for(int stage = 0; stage <= 5; stage++) {
            withExistingParent( cropName + "_stage" + stage,
                "minecraft:block/stem_growth7")
                .renderType("minecraft:cutout")
                .texture("stem", new ResourceLocation(BakedDelight.MODID, "block/" + cropName + "_stage" + stage));
        }
    }
    private String getRightTexture(RegistryObject<? extends Block> block, int slice) {
        if(slice == 0) {
            return block.getId().getPath().replace("dish", "top");
        }
        else {
            return BackingDishTypeCollection.getDishRecipeIngredient(Function.getItemName(block.get().asItem()), slice);
        }
    }
}
