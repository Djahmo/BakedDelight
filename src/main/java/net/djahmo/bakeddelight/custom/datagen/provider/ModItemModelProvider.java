package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.registry.ModBlocks;
import net.djahmo.bakeddelight.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BakedDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BACON_BITS);
        simpleItem(ModItems.BACON_CREAM);
        simpleItem(ModItems.BOLOGNESE);
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.COOKED_BACON_BITS);
        simpleItem(ModItems.COOKED_GROUND_MEAT);
        simpleItem(ModItems.EGGPLANT);
        simpleItem(ModItems.EGGPLANT_SEED);
        simpleItem(ModItems.ZUCCHINI);
        simpleItem(ModItems.ZUCCHINI_SEED);
        simpleItem(ModItems.GROUND_MEAT);
        simpleItem(ModItems.LASAGNA_PASTA);
        simpleItem(ModItems.SLICED_EGGPLANT);
        simpleItem(ModItems.SLICED_POTATO);
        simpleItem(ModItems.SLICED_TOMATO);
        simpleItem(ModItems.SLICED_ZUCCHINI);

        simpleItem(ModItems.LASAGNA_SLICE);
        simpleItem(ModItems.GRATIN_SLICE);
        simpleItem(ModItems.MOUSSAKA_SLICE);
        simpleItem(ModItems.TIAN_SLICE);

        simpleItem(ModItems.ROLLING_PIN);

        simpleItem(ModItems.CHEFS_HAT);
        simpleItem(ModItems.CHEFS_JACKET);
        simpleItem(ModItems.CHEFS_PANTS);
        simpleItem(ModItems.CHEFS_SHOES);

        dishItem(ModBlocks.BAKING_DISH);
        dishItem(ModBlocks.UNCOOKED_LASAGNA_DISH);
        dishItem(ModBlocks.COOKED_LASAGNA_DISH);
        dishItem(ModBlocks.UNCOOKED_GRATIN_DISH);
        dishItem(ModBlocks.COOKED_GRATIN_DISH);
        dishItem(ModBlocks.UNCOOKED_MOUSSAKA_DISH);
        dishItem(ModBlocks.COOKED_MOUSSAKA_DISH);
        dishItem(ModBlocks.UNCOOKED_TIAN_DISH);
        dishItem(ModBlocks.COOKED_TIAN_DISH);
    }


    private void simpleItem(RegistryObject<? extends Item> item) {
        withExistingParent(item.getId().getPath(),
            new ResourceLocation("item/generated")).texture("layer0",
            new ResourceLocation(BakedDelight.MODID,"item/" + item.getId().getPath()));
    }

    private void dishItem(RegistryObject<? extends Block> block) {
        withExistingParent(block.getId().getPath(), new ResourceLocation(BakedDelight.MODID, "block/" + block.getId().getPath()));
    }
}
