package fr.djahmo.bakeddelight.utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties GROUND_BEEF, GROUND_PORKCHOP, GROUND_BEEF_PORKCHOP, COOKED_GROUND_BEEF, COOKED_GROUND_PORKCHOP, COOKED_GROUND_BEEF_PORKCHOP, LASAGNA_DOUGH, CHEESE, LASAGNA_SLICE;

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
        GROUND_BEEF = setProperties(3, 0.3F,true);
        GROUND_PORKCHOP = setProperties(3, 0.3F,true);
        GROUND_BEEF_PORKCHOP = setProperties(4, 0.3F,true);
        COOKED_GROUND_BEEF = setProperties(8, 0.7F,true);
        COOKED_GROUND_PORKCHOP = setProperties(8, 0.7F,true);
        COOKED_GROUND_BEEF_PORKCHOP = setProperties(10, 0.8F,true);
        LASAGNA_DOUGH = setProperties(2, 0.3F, false, new MobEffectInstance(MobEffects.HUNGER, 300));
        CHEESE = setProperties(4, 0.5F, false);
        LASAGNA_SLICE = setProperties(10, 1.0F, true);
    }
}
