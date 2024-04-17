package net.djahmo.bakeddelight.custom.datagen.provider;

import net.djahmo.bakeddelight.BakedDelight;
import net.djahmo.bakeddelight.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
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
        simpleItem(ModItems.GROUND_MEAT);
        simpleItem(ModItems.LASAGNA_PASTA);
        simpleItem(ModItems.SLICED_EGGPLANT);
        simpleItem(ModItems.SLICED_POTATO);
        simpleItem(ModItems.LASAGNA_SLICE);

        simpleItem(ModItems.GRATIN_SLICE);
        simpleItem(ModItems.MOUSSAKA_SLICE);
        simpleItem(ModItems.ROLLING_PIN);

        simpleItem(ModItems.CHEFS_HAT);
        simpleItem(ModItems.CHEFS_JACKET);
        simpleItem(ModItems.CHEFS_PANTS);
        simpleItem(ModItems.CHEFS_SHOES);

    }


    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BakedDelight.MODID,"item/" + item.getId().getPath()));
    }
}
