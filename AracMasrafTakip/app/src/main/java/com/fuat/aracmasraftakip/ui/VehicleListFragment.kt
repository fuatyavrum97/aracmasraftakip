package com.fuat.aracmasraftakip.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuat.aracmasraftakip.R
import com.fuat.aracmasraftakip.database.viewmodel.VehicleViewModel
import com.fuat.aracmasraftakip.databinding.FragmentVehicleListBinding
import com.fuat.aracmasraftakip.ui.adapter.VehicleAdapter
import com.fuat.aracmasraftakip.utils.LogWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null
    private val binding get() = _binding!!

    private val vehicleViewModel: VehicleViewModel by viewModels()
    private lateinit var adapter: VehicleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vehicleRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        vehicleViewModel.allVehicles.observe(viewLifecycleOwner) { vehicleList ->
            adapter = VehicleAdapter(vehicleList)
            binding.vehicleRecyclerView.adapter = adapter
        }


        //
        binding.fabAddVehicle.setOnClickListener {
            println("asd123**")
            LogWrapper.d("VehicleListFragment_AddVehicleButton12", "fabAddVehicle Button clicked.")
            Toast.makeText(
                requireContext(),
                "Yeni Araç Ekleme Ekranına Yönlendiriliyorsunuz. Lütfen Bekleyiniz...",
                Toast.LENGTH_SHORT
            ).show()
            viewLifecycleOwner.lifecycleScope.launch {
                delay(1500) // 1.5 saniye
                findNavController().navigate(R.id.action_vehicleListFragment_to_addVehicleFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}