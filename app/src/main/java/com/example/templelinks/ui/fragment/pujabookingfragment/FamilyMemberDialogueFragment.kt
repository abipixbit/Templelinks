package com.example.templelinks.ui.fragment.pujabookingfragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.templelinks.R
import com.example.templelinks.databinding.FragmentFamilyMemberDialogueBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.setFullScreen
import com.example.templelinks.ui.adapter.FamilyAdapter


class FamilyMemberDialogueFragment(val famMemberAdd : ()->Unit) : DialogFragment() {

    private lateinit var binding : FragmentFamilyMemberDialogueBinding
//    private val viewModel : PujaBookingViewModel by viewModels()
    private lateinit var familyAdapter : FamilyAdapter

    private lateinit var viewModel : PujaBookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDialogueBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(requireParentFragment()).get(PujaBookingViewModel::class.java)


        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.time_schedule_dropdown, resources.getStringArray(R.array.time_schedule_dropdown))
        binding.etTimeSchedule.setAdapter(arrayAdapter)
        familyAdapter = FamilyAdapter({ familyIdAdd->
            viewModel.addFamMember(familyIdAdd)
        }, { familyIdRemove ->
            viewModel.removeFamMember(familyIdRemove)
        }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFullScreen()
        loadFamilies()

        binding.ivExit.setOnClickListener {
            dismiss()
        }

        binding.btnSelect.setOnClickListener {
            famMemberAdd()
            dismiss()
        }

    }

    private fun loadFamilies() {
        viewModel.families.observe(viewLifecycleOwner) { apiResponse ->
            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.rvFamilyMembers.adapter = familyAdapter
                    apiResponse.data.let { families ->
                        familyAdapter.submitList(families)
                    }
                }
                ApiStatus.ERROR -> Log.d("FamiliesError", apiResponse.message.toString())
                else -> {}
            }
        }
    }

}