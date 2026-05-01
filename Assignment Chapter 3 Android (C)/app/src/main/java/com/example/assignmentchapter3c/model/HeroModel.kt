package com.example.assignmentchapter3c.model

data class HeroModel(
    val name: String,
    val realName: String,
    val team: String,
    val firstAppearance: String,
    val createdBy: String,
    val publisher: String,
    val imageURL: String,
    val bio: String,
    var isExpanded: Boolean = false
)
