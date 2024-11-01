package dev.cakend.growthaccelerator.item.items;

import dev.cakend.growthaccelerator.GrowthAccelerator;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrowthAccelerator.MODID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("item", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
