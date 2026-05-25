package com.example.assignmentchapter3androidd.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentchapter3androidd.R
import com.example.assignmentchapter3androidd.viewmodel.MessageViewModel
import com.google.android.material.textfield.TextInputEditText

class AddMessageFragment : Fragment(R.layout.fragment_add_message) {
    private lateinit var messageViewModel: MessageViewModel

    private lateinit var messageEditTxt: TextInputEditText
    private lateinit var saveBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageViewModel = ViewModelProvider(requireActivity())[MessageViewModel::class.java]

        messageEditTxt = view.findViewById(R.id.edit_txt_add_message)
        saveBtn = view.findViewById(R.id.btn_save)

        setUpBtn()
    }

    private fun setUpBtn() {
            saveBtn.setOnClickListener {
                    val newMessage = messageEditTxt.text.toString()
                    messageViewModel.saveMessage(newMessage)
                    messageEditTxt.setText("")
                    Toast.makeText(requireActivity(),"Message Saved!", Toast.LENGTH_SHORT).show()
            }
    }
}