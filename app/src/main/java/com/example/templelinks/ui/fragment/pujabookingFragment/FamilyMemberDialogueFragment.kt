package com.example.templelinks.ui.fragment.pujabookingFragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModelProvider
import com.example.templelinks.R
import com.example.templelinks.data.model.Families
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.data.model.response.ApiResponse
import com.example.templelinks.databinding.FragmentFamilyMemberDialogueBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.glide
import com.example.templelinks.extensions.setFullScreen
import com.example.templelinks.ui.adapter.FamilyAdapter


class FamilyMemberDialogueFragment(private val pujas: Pujas, val selectedPujas : (MutableList<Pujas>)->Unit) : DialogFragment() {

    private lateinit var binding : FragmentFamilyMemberDialogueBinding
    private lateinit var familyAdapter : FamilyAdapter
    private val viewModel : FamilyMemberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDialogueBinding.inflate(layoutInflater, container, false)
        Log.d("pujasDialogFrag", pujas.selectedFamilies.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        familyAdapter = FamilyAdapter { family->

                if (family.isSelected == true) {
                    viewModel.addSelectedFamily(family)
                }
                else {
                viewModel.deleteSelectedFamily(family)
                }

        }

        binding.apply {
            ivExit.setOnClickListener {
                dismiss()
            }

            btnSelect.setOnClickListener {

                    if (pujas.isSelected) {
                        pujas.selectedFamilies = pujas.selectedFamilies?.plus(viewModel.selectedFamily)
                    }
                    else {
                        pujas.isSelected = true
                        pujas.selectedFamilies = viewModel.selectedFamily
                    }
                    selectedPujas(mutableListOf(pujas))
                    dismiss()
            }
        }
    }


    private fun setupUI() {
        requireView().glide("file:///android_asset/loading.gif", binding.ivLoading)
        setFullScreen()
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.time_schedule_dropdown, resources.getStringArray(R.array.time_schedule_dropdown))
        binding.etTimeSchedule.setAdapter(arrayAdapter)

        loadFamilies()
    }

    private fun loadFamilies() {
        viewModel.families.observe(viewLifecycleOwner) { apiResponse ->
            binding.ivLoading.isVisible = apiResponse.apiStatus == ApiStatus.LOADING
            when(apiResponse.apiStatus) {
                    ApiStatus.SUCCESS -> {
                    binding.rvFamilyMembers.adapter = familyAdapter
                    apiResponse.data.let { families ->

                     val selectedFamilyId = pujas.selectedFamilies?.map {
                            it.id
                        }

                        families?.forEach {
                            if (selectedFamilyId?.contains(it.id) == true)
                                it.isSelected = true
                        }

                        familyAdapter.submitList(families)
                    }
                }
                ApiStatus.ERROR -> Log.d("FamiliesError", apiResponse.message.toString())
                else -> { }
            }
        }
    }

}