package com.fuat.aracmasraftakip.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuat.aracmasraftakip.database.entity.VehicleEntity
import com.fuat.aracmasraftakip.databinding.ItemVehicleBinding

class VehicleAdapter(private val vehicleList: List<VehicleEntity>) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    class VehicleViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(vehicle: VehicleEntity) {
            binding.vehicleBrand.text = "Marka: ${vehicle.brand}"
            binding.vehicleModel.text = "Model: ${vehicle.model}"
            binding.vehiclePlate.text = "Plaka: ${vehicle.plate}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVehicleBinding.inflate(inflater, parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        holder.bind(vehicle)
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }
}
