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
    var selectedFamily = mutableListOf<Families>()
    private val viewModel : FamilyMemberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFamilyMemberDialogueBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    init {
        Log.d("Dialog","Created, ${selectedFamily.toString()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Dialog","Destroy ${selectedFamily.toString()}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFullScreen()
        requireView().glide("file:///android_asset/loading.gif", binding.ivLoading)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.time_schedule_dropdown, resources.getStringArray(R.array.time_schedule_dropdown))
        binding.etTimeSchedule.setAdapter(arrayAdapter)

        loadFamilies()

        familyAdapter = FamilyAdapter({ familiesAdd->
                pujas.isSelected = true
                selectedFamily.add(familiesAdd)
                pujas.selectedFamilies = selectedFamily
                Log.d("FamilyAdd", selectedFamily.toString())

        }, { familyRemove ->
            selectedFamily.remove(familyRemove)
            pujas.selectedFamilies = selectedFamily
            Log.d("FamilyRemove", selectedFamily.toString())
        })

        binding.apply {
            ivExit.setOnClickListener {
                dismiss()
            }

            btnSelect.setOnClickListener {
                    if (pujas.isSelected == true) {
                    selectedPujas(mutableListOf(pujas))
                    dismiss() }

                    else { dismiss() }
            }

        }
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
                        } ?: emptyList()

                        families?.forEach {
                            if (selectedFamilyId.contains(it.id))
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