package com.example.assignmentchapter3c.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.adapter.HeroAdapter
import com.example.assignmentchapter3c.data.HeroRepository

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroRecyclerView = view.findViewById<RecyclerView>(R.id.rv_heroes)

        val heroList = HeroRepository.getHeroes(requireContext())
        heroRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        heroRecyclerView.adapter = HeroAdapter(requireContext(), heroList)
    }
}