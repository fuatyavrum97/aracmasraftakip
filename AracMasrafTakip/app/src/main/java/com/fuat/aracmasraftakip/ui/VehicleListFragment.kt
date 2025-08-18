package com.fuat.aracmasraftakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fuat.aracmasraftakip.R
import com.fuat.aracmasraftakip.database.viewmodel.VehicleViewModel
import com.fuat.aracmasraftakip.databinding.FragmentVehicleListBinding
import com.fuat.aracmasraftakip.ui.adapter.VehicleAdapter

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

        // RecyclerView ayarları
        binding.vehicleRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Verileri gözlemleme ve adapter'e bağlama
        vehicleViewModel.allVehicles.observe(viewLifecycleOwner) { vehicleList ->
            adapter = VehicleAdapter(vehicleList)
            binding.vehicleRecyclerView.adapter = adapter
        }


        // Yeni araç ekleme butonu için listener
        binding.addVehicleButton.setOnClickListener {
            findNavController().navigate(R.id.action_vehicleListFragment_to_addVehicleFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}