package com.metacontent.cobblenav.utils

import com.cobblemon.mod.common.api.conditional.RegistryLikeCondition
import com.cobblemon.mod.common.api.conditional.RegistryLikeIdentifierCondition
import com.cobblemon.mod.common.api.conditional.RegistryLikeTagCondition
import com.metacontent.cobblenav.CobbleNav
import net.minecraft.resources.ResourceLocation

fun cobblenavResource(name: String, namespace: String = CobbleNav.MOD_ID): ResourceLocation =
	ResourceLocation.fromNamespaceAndPath(namespace, name)

fun RegistryLikeCondition<*>.toResourceLocation(): ResourceLocation? {
	if (this is RegistryLikeIdentifierCondition) {
		return this.identifier
	}
	if (this is RegistryLikeTagCondition) {
		return this.tag.location
	}
	return null
}

private val PREDEFINED_BUCKET_ORDER = listOf("common", "uncommon", "rare", "ultra-rare")

fun List<String>.sortBuckets(): List<String> = this.sortedWith(
	compareBy<String> {
		val index = PREDEFINED_BUCKET_ORDER.indexOf(it)
		if (index == -1) Int.MAX_VALUE else index
	}.thenBy { it },
)
