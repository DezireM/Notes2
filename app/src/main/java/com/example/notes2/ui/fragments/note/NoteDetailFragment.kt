package com.example.notes2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes2.R
import com.example.notes2.data.extension.setBackStackData
import com.example.notes2.databinding.FragmentNoteDetailBinding




class NoteDetailFragment : Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnAdd.setOnClickListener{
            val et = binding.etAdd.text.toString()
            setBackStackData("key", et, true)
        }
    }
}