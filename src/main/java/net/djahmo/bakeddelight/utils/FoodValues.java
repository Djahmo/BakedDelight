package net.djahmo.bakeddelight.utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties GROUND_MEAT, COOKED_GROUND_MEAT, LASAGNA_PASTA, SLICED_POTATO, CHEESE, BACON_BITS, COOKED_BACON_BITS, BOLOGNESE, BACON_CREAM, LASAGNA_SLICE, GRATIN_SLICE, MOUSSAKA_SLICE, EGGPLANT;

    private static FoodProperties.Builder setBasicProps(int nutrition, float saturation, boolean isMeat) {
        FoodProperties.Builder temp = new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation);
        if(isMeat)
            temp.meat();
        return temp;
    }
    private static FoodProperties setProperties(int nutrition, float saturation, boolean isMeat) {
        return setBasicProps(nutrition, saturation, isMeat).build();
    }

    private static FoodProperties setProperties(int nutrition, float saturation, boolean isMeat, MobEffectInstance effect) {
        return setBasicProps(nutrition, saturation, isMeat).effect(() -> effect, 0.3F).build();
    }

    static {

        GROUND_MEAT = setProperties(4, 0.3F,true);
        COOKED_GROUND_MEAT = setProperties(9, 0.8F,true);
        LASAGNA_PASTA = setProperties(1, 0.2F, false, new MobEffectInstance(MobEffects.HUNGER, 300));
        SLICED_POTATO = setProperties(2, 0.2F, false, new MobEffectInstance(MobEffects.HUNGER, 300));
        CHEESE = setProperties(4, 0.5F, false);
        BACON_BITS = setProperties(2, 0.3F,true);
        COOKED_BACON_BITS = setProperties(4, 0.4F,true);
        BOLOGNESE = setProperties(10, 0.8F, true);
        BACON_CREAM = setProperties(6, 0.6F, false);
        LASAGNA_SLICE = setProperties(10, 1.0F, true);
        GRATIN_SLICE = setProperties(8, 0.9F, true);
        MOUSSAKA_SLICE = setProperties(11, 0.8F, true);
        EGGPLANT = setProperties(2, 0.3F, false, new MobEffectInstance(MobEffects.HUNGER, 300));
    }
}
