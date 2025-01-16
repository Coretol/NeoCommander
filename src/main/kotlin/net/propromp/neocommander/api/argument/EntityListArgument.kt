package net.propromp.neocommander.api.argument

import com.mojang.brigadier.arguments.ArgumentType
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.selector.EntitySelector
import net.propromp.neocommander.api.NeoCommandContext
import org.bukkit.entity.Entity

class EntityListArgument(name: String) :
    AbstractEntityArgument<List<Entity>>(name) {

    override fun asBrigadier(): ArgumentType<out Any> {
        return EntityArgument.entities()
    }

    override fun parse(context: NeoCommandContext, t: Any): List<Entity> {
        return (t as EntitySelector).findEntities(context.context.source as CommandSourceStack)
            .map { entity -> entity.bukkitEntity }
    }
}