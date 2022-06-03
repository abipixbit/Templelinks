package com.example.templelinks.ui.fragment.pujabookingfragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.templelinks.R
import com.example.templelinks.data.model.Pujas
import com.example.templelinks.databinding.FragmentPujaBookingBinding
import com.example.templelinks.enums.ApiStatus
import com.example.templelinks.ui.adapter.PoojaBookingDeitiesAdapter
import com.example.templelinks.ui.adapter.PujasAdapter


class PujaBookingFragment : Fragment() {

    private lateinit var binding : FragmentPujaBookingBinding
    private val arguments : PujaBookingFragmentArgs by navArgs()
    private val viewModel : PujaBookingViewModel by viewModels()
    private lateinit var deitiesAdapter : PoojaBookingDeitiesAdapter
    private lateinit var pujasAdapter: PujasAdapter
    private lateinit var builder : AlertDialog.Builder
    private var pujasTempData : Map<Int, List<Pujas>> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPujaBookingBinding.inflate(layoutInflater, container, false)
        binding.toolBarPujaBooking.tvToolBar.text = getString(R.string.pooja_booking)
        binding.toolBarPujaBooking.toolBar.setNavigationIcon(R.drawable.ic_back)
        builder = AlertDialog.Builder(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        binding.toolBarPujaBooking.toolBar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).navigate(PujaBookingFragmentDirections.actionPujaBookingFragmentToDetailFragment(arguments.currentTemple))
        }

            deitiesAdapter = PoojaBookingDeitiesAdapter { deitiesId ->
            viewModel.loadPujas(arguments.currentTemple.id, deitiesId)
            loadPujas()
        }

        pujasAdapter = PujasAdapter { pujas ->
            builder .setTitle(pujas.translation.pujaName)
                    .setMessage(pujas.translation.pujaDescription)
                    .setCancelable(false)
                    .setPositiveButton("Close") { _,_ -> }
                    .show()
        }
    }

    private fun updateUI() {
        binding.tvTempleNamePujaBooking.text = arguments.currentTemple.locale?.name
        binding.tvTempleAddressPujaBooking.text = arguments.currentTemple.locale?.address
        viewModel.loadDeities(arguments.currentTemple.id)
        loadDeities()
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
                    apiResponse.data.let { pujas ->
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