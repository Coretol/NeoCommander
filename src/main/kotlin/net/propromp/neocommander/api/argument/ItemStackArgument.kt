package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.arguments.item.ItemInput
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class ItemStackArgument(name: String) : NeoArgument<Any, ItemStack>(name) {
    override fun asBrigadier() = net.minecraft.world.item.ItemStack.CODEC as ArgumentType<out Any>

    override fun parse(context: NeoCommandContext, t: Any): ItemStack {
        val itemStack = (t as ItemInput).createItemStack(1, false)

        return CraftItemStack.asBukkitCopy(itemStack)
    }
}