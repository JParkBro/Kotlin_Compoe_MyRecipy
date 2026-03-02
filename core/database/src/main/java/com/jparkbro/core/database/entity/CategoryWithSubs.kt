package com.jparkbro.core.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithSubs(
    @Embedded val parent: CategoryEntity,
    @Relation(parentColumn = "id", entityColumn = "parentId")
    val children: List<CategoryEntity>,
)
