package de.hglabor.plugins.kitapi.implementation

import de.hglabor.plugins.kitapi.cooldown.CooldownProperties
import de.hglabor.plugins.kitapi.cooldown.applyCooldown
import de.hglabor.plugins.kitapi.kit.Kit
import net.axay.kspigot.extensions.events.isRightClick
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class TobleroneProperties : CooldownProperties(150) {
    val effectDuration by int(60)
    val min by int(0)
    val max by int(3)
}

fun rand(min: Int, max: Int): Int {
    if (min >= max) println("${ChatColor.RED}THE MIN=$min IS BIGGER THAN THE MAX=$max")
    return (min..max).random()
}

val Toblerone = Kit("Toblerone", ::TobleroneProperties) {
    displayItem = ItemStack(Material.INK_SACK, 1, 3)


    clickableItem(ItemStack(Material.INK_SACK, 1, 3)) {
        it.isCancelled = true
        if (!it.action.isRightClick) return@clickableItem

        applyCooldown(it) {
            it.player.playSound(it.player.location, Sound.EAT, 2f, 1f)
            it.player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SPEED,
                    kit.properties.effectDuration * 20,
                    rand(kit.properties.min, kit.properties.max)
                )
            )
            it.player.addPotionEffect(
                PotionEffect(
                    PotionEffectType.JUMP,
                    kit.properties.effectDuration * 20,
                    rand(kit.properties.min, kit.properties.max)
                )
            )
        }
    }
}

