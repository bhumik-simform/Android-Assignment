package com.example.assignmentchapter3androidd.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentchapter3androidd.R
import com.example.assignmentchapter3androidd.viewmodel.MessageViewModel

class ViewMessageFragment : Fragment(R.layout.fragment_view_message) {

    lateinit var messageViewModel: MessageViewModel

    lateinit var messageTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageViewModel = ViewModelProvider(requireActivity())[MessageViewModel::class.java]

        messageTextView = view.findViewById(R.id.tv_show_message)

        messageViewModel.message.observe(viewLifecycleOwner) { newMessage ->
            messageTextView.text = newMessage
        }

    }
}