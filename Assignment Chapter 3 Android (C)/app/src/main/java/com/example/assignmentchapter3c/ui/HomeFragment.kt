package com.example.assignmentchapter3c.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.btnListview.setOnClickListener {
            startActivity(
                Intent(requireContext(),
                ListViewActivity::class.java)
            )
        }

        binding.btnRecyclerview.setOnClickListener {
            startActivity(
                Intent(requireContext(),
                    RecyclerViewActivity::class.java
                )
            )
        }

        binding.btnGridview.setOnClickListener {
            startActivity(
                Intent(requireContext(),
                    GridViewActivity::class.java
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}