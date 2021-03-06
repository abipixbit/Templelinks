package com.example.templelinks.ui.fragment.pujabookingFragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.data.model.Families
import com.example.templelinks.databinding.FragmentPujaBookingBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.extensions.glide
import com.example.templelinks.ui.adapter.PoojaBookingDeitiesAdapter
import com.example.templelinks.ui.adapter.PujasAdapter
import com.example.templelinks.ui.fragment.pujabookingFragment.familyMemberDialogueFragment.FamilyMemberDialogueFragment
import java.util.*


class PujaBookingFragment : Fragment() {

    private lateinit var binding : FragmentPujaBookingBinding
    private val arguments : PujaBookingFragmentArgs by navArgs()
    private val viewModel : PujaBookingViewModel by viewModels()
    private lateinit var deitiesAdapter : PoojaBookingDeitiesAdapter
    private lateinit var pujasAdapter: PujasAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPujaBookingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupUI()
        }


    }

    private fun updateDate(cal: Calendar) {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        binding.includePoojaBooking.tvBookingDate.text = sdf.format(cal.time)
    }

    private fun setupUI() {

        val calendar = viewModel.calendar

        deitiesAdapter = PoojaBookingDeitiesAdapter { deitiesId ->
            viewModel.loadPujas(arguments.currentTemple.id, deitiesId)
            loadPujas()
        }

        val datePickerDialog = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, date)
            }
            updateDate(calendar)
        }

        binding.apply {
            toolBarPujaBooking.toolBar.setNavigationOnClickListener {
                Navigation.findNavController(requireView()).navigate(
                    PujaBookingFragmentDirections.actionPujaBookingFragmentToDetailFragment(arguments.currentTemple)
                )
            }

            btnBook.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.pujaBookingFragment) {
                    findNavController().navigate(PujaBookingFragmentDirections.actionPujaBookingFragmentToFinalBokingFragment
                        (viewModel.selectedPoojas.toTypedArray(),
                        arguments.currentTemple,
                        includePoojaBooking.tvBookingDate.text.toString())
                    )
                }
            }

            includePoojaBooking.ivCalendar.setOnClickListener {
                val datePickerDialogue = DatePickerDialog(
                    requireContext(),
                    datePickerDialog,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

                datePickerDialogue.apply {
                    datePicker.minDate = System.currentTimeMillis()
                    show()
                }
            }
        }

        pujasAdapter = PujasAdapter({ pujas ->
            AlertDialog .Builder(requireContext())
                .setTitle(pujas.translation.pujaName)
                .setMessage(pujas.translation.pujaDescription)
                .setCancelable(false)
                .setPositiveButton("Close") { _, _ -> }
                .show()}
        ) { clickedPujas, position ->

            val dialogue = FamilyMemberDialogueFragment(clickedPujas) { selectedPujas ->
                viewModel.addSelectedPoojas(selectedPujas)
                pujasAdapter.notifyItemChanged(position)
            }
            dialogue.show(childFragmentManager, "customDialogue")
        }

        requireView().glide("file:///android_asset/loading.gif", binding.ivLoading)

        val currentTemple = arguments.currentTemple

        binding.apply {
            toolBarPujaBooking.apply {
                tvToolBar.text = getString(R.string.pooja_booking)
                toolBar.setNavigationIcon(R.drawable.ic_back)
            }

            tvTempleNamePujaBooking.text = currentTemple.locale?.name
            tvTempleAddressPujaBooking.text = currentTemple.locale?.address
        }

        viewModel.loadDeities(currentTemple.id)
        loadDeities()
        updateDate(viewModel.calendar)
    }

    private fun loadDeities() {

        viewModel.deities.observe(viewLifecycleOwner) { apiResponse->
            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.rvDeitiesButton.adapter = deitiesAdapter
                    apiResponse.data.let { deities ->
                        deitiesAdapter.submitList(deities)
                        Log.d("PujaBookDeitiesSuc", deities.toString())
                    }
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    Log.d("PujaBookDeitiesFail", message.toString())
                }
                else -> {}
            }
        }
    }

    private fun loadPujas() {

        viewModel.pujas.observe(viewLifecycleOwner) { apiResponse->

            binding.ivLoading.isVisible = apiResponse.apiStatus == ApiStatus.LOADING

            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.rvPujas.adapter = pujasAdapter
                    apiResponse.data?.let { pujas ->

                        val selectedPujaId =  viewModel.selectedPoojas.map {
                        it.translation.pujaId
                    }
                        viewModel.selectedPoojas.forEach {
                            viewModel.addtofamMap(it.translation.pujaId, it.selectedFamilies)
                        }

                        pujas.forEach {
                            if (selectedPujaId.contains(it.translation.pujaId))
                                it.isSelected = true
                                it.selectedFamilies = viewModel.famMap[it.translation.pujaId]
                        }

                    pujasAdapter.submitList(pujas)
                    Log.d("LoadPujaSuc", pujas.toString())
                    }
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    Log.d("LoadPujaFail", message.toString())
                }
                else -> { }
            }
        }
    }
}