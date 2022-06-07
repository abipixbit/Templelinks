package com.example.templelinks.ui.fragment.pujabookingfragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.databinding.FragmentPujaBookingBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.ui.adapter.PoojaBookingDeitiesAdapter
import com.example.templelinks.ui.adapter.PujasAdapter
import java.util.*


class PujaBookingFragment : Fragment() {

    private lateinit var binding : FragmentPujaBookingBinding
    private val arguments : PujaBookingFragmentArgs by navArgs()
    private val viewModel : PujaBookingViewModel by viewModels()
    private lateinit var deitiesAdapter : PoojaBookingDeitiesAdapter
    private lateinit var pujasAdapter: PujasAdapter
    private lateinit var builder : AlertDialog.Builder
    private var pujaTempData : Map<Int?, List<Pujas>> = mutableMapOf()
    private val calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPujaBookingBinding.inflate(layoutInflater, container, false)
        binding.toolBarPujaBooking.tvToolBar.text = getString(R.string.pooja_booking)
        binding.toolBarPujaBooking.toolBar.setNavigationIcon(R.drawable.ic_back)
        builder = AlertDialog.Builder(requireContext())
        updateUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBarPujaBooking.toolBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                PujaBookingFragmentDirections.actionPujaBookingFragmentToDetailFragment(arguments.currentTemple)
            )
        }

        deitiesAdapter = PoojaBookingDeitiesAdapter { deitiesId ->

            if (pujaTempData.contains(deitiesId)) {
                pujasAdapter.submitList(pujaTempData.getValue(deitiesId))
                Log.d("MapIf", pujaTempData.toString())
            } else {
                viewModel.loadPujas(arguments.currentTemple.id, deitiesId)
                loadPujas()
                Log.d("MapElse", pujaTempData.toString())
            }
        }

        val datePickerDialog = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, date)
            updateDate(calendar)
        }

        binding.includePoojaBooking.ivCalendar.setOnClickListener {
          val datePickerDialogue = DatePickerDialog(
                requireContext(),
                datePickerDialog,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialogue.datePicker.minDate = System.currentTimeMillis()
            datePickerDialogue.show()
        }

        pujasAdapter = PujasAdapter({ pujas ->
            builder.setTitle(pujas.translation.pujaName)
                .setMessage(pujas.translation.pujaDescription)
                .setCancelable(false)
                .setPositiveButton("Close") { _, _ -> }
                .show()},
            { pujaId ->
                val dialogue = FamilyMemberDialogueFragment{
                    viewModel.pujaBook(pujaId, viewModel.familyMember)
                    viewModel.removeFam()
                }
                dialogue.show(childFragmentManager, "customDialogue")
            }
        , viewModel.check)
    }

    private fun updateDate(cal: Calendar) {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        binding.includePoojaBooking.tvBookingDate.text = sdf.format(cal.time)
    }

    private fun updateUI() {
        binding.tvTempleNamePujaBooking.text = arguments.currentTemple.locale?.name
        binding.tvTempleAddressPujaBooking.text = arguments.currentTemple.locale?.address
        viewModel.loadDeities(arguments.currentTemple.id)
        loadDeities()
        updateDate(calendar)
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
            when(apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> {
                    binding.rvPujas.adapter = pujasAdapter
                    apiResponse.data?.let { pujas ->

                        pujaTempData = pujaTempData.plus(pujas[0].deityId to pujas)
                        Log.d("tempData", pujaTempData.toString())

                        pujasAdapter.submitList(pujas)
                        Log.d("LoadPujaSuc", pujas.toString())
                    }
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    Log.d("LoadPujaFail", message.toString())
                }
                else -> {}
            }
        }
    }
}