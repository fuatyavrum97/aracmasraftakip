package com.fuat.aracmasraftakip.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuat.aracmasraftakip.database.entity.VehicleEntity
import com.fuat.aracmasraftakip.databinding.ItemVehicleBinding

class VehicleAdapter(
    private val vehicleList: List<VehicleEntity>,
    private val onClick: (VehicleEntity) -> Unit = {}
) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    inner class VehicleViewHolder(
        private val binding: ItemVehicleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(vehicle: VehicleEntity) = with(binding) {
            tvPlate.text = vehicle.plate?.takeIf { it.isNotBlank() } ?: "Plaka yok"

            val yearPart = vehicle.year?.let { " ($it)" } ?: ""
            tvBrandModel.text = "${vehicle.brand} ${vehicle.model}$yearPart"

            root.setOnClickListener { onClick(vehicle) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVehicleBinding.inflate(inflater, parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicleList[position])
    }

    override fun getItemCount(): Int = vehicleList.size
}

