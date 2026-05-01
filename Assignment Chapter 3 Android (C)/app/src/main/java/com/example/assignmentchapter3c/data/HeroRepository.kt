package com.example.assignmentchapter3c.data

import android.content.Context
import com.example.assignmentchapter3c.model.HeroModel
import org.json.JSONArray
import kotlin.String

object HeroRepository {

    fun getHeroes(context: Context): List<HeroModel> {
        val heroList = mutableListOf<HeroModel>()

        val jsonString = context.assets.open("marvel.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)

            val hero = HeroModel(
                name = obj.getString("name"),
                realName = obj.getString("realName"),
                team = obj.getString("team"),
                firstAppearance = obj.getString("firstAppearance"),
                createdBy = obj.getString("createdBy"),
                publisher = obj.getString("publisher"),
                imageURL = obj.getString("imageURL"),
                bio = obj.getString("bio")
            )

            heroList.add(hero)
        }

        return heroList
    }
}