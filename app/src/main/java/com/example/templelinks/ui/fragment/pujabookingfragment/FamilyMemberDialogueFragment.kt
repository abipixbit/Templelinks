package com.example.templelinks.ui.fragment.pujabookingfragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.fragment.app.DialogFragment
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFamilyMemberDialogueBinding


class FamilyMemberDialogueFragment : DialogFragment() {

    private lateinit var binding : FragmentFamilyMemberDialogueBinding
    val dialogue = dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDialogueBinding.inflate(layoutInflater, container, false)

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.time_schedule_dropdown, resources.getStringArray(R.array.time_schedule_dropdown))
        binding.etTimeSchedule.setAdapter(arrayAdapter)

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.full_screen_dialog
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}