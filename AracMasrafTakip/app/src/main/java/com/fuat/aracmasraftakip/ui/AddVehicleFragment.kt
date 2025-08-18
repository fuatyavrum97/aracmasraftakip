package com.fuat.aracmasraftakip.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fuat.aracmasraftakip.database.entity.VehicleEntity
import com.fuat.aracmasraftakip.database.viewmodel.VehicleViewModel
import com.fuat.aracmasraftakip.databinding.FragmentAddVehicleBinding
import com.fuat.aracmasraftakip.utils.LogWrapper
import kotlinx.coroutines.launch

class AddVehicleFragment : Fragment() {

    private var _binding: FragmentAddVehicleBinding? = null
    private val binding get() = _binding!!

    private val vehicleViewModel: VehicleViewModel by viewModels()

    // Marka ve model listesi
    private val brandModelMap = mapOf(
        "Renault" to listOf("Arkana", "Austral", "Captur", "Clio", "Duster", "Espace", "Fluence", "Grand Scenic", "Kadjar", "Kiger", "Koleos", "Kwid", "Laguna", "Master", "Megane", "Scenic", "Symbol", "Talisman", "Triber", "Zoe"),
        "Hyundai" to listOf("Accent", "Azera", "Bayon", "Creta", "Elantra", "Genesis", "Ioniq 5", "Ioniq 6", "Kona", "Nexo", "Palisade", "Santa Fe", "Sonata", "Staria", "Terracan", "Tucson", "Venue", "Verna", "i10", "i20", "i30"),
        "Toyota" to listOf("Aygo", "Avensis", "Camry", "CH-R", "Corolla", "Fortuner", "Highlander", "Hilux", "Land Cruiser", "Mirai", "Prius", "RAV4", "Sequoia", "Sienna", "Supra", "Tacoma", "Tundra", "Urban Cruiser", "Venza", "Yaris"),
        "BMW" to listOf("1 Series", "2 Series", "3 Series", "4 Series", "5 Series", "7 Series", "M3", "M4", "M5", "X1", "X2", "X3", "X4", "X5", "X6", "X7", "Z4", "i3", "i4", "i7"),
        "Mercedes-Benz" to listOf("A-Class", "AMG GT", "B-Class", "C-Class", "CLA", "CLS", "E-Class", "EQS", "EQC", "EQE", "G-Class", "GLA", "GLB", "GLC", "GLE", "GLS", "S-Class", "SL", "Sprinter", "Vito"),
        "Ford" to listOf("Bronco", "Escape", "Everest", "Explorer", "Fiesta", "Focus", "F-150", "Galaxy", "Kuga", "Mach-E", "Maverick", "Mondeo", "Mustang", "Puma", "Ranger", "S-Max", "Transit"),
        "Volkswagen" to listOf("Amarok", "Arteon", "Atlas", "Beetle", "Caddy", "Golf", "ID.3", "ID.4", "Jetta", "Multivan", "Passat", "Polo", "Scirocco", "Sharan", "T-Cross", "T-Roc", "Taos", "Tiguan", "Touareg", "Vento"),
        "Honda" to listOf("Accord", "Acty", "City", "Clarity", "Civic", "CR-V", "Crosstour", "Element", "Fit", "HR-V", "Insight", "Integra", "Jazz", "Legend", "NSX", "Odyssey", "Passport", "Pilot", "Prelude", "Ridgeline"),
        "Chevrolet" to listOf("Aveo", "Blazer", "Bolt", "Camaro", "Captiva", "Colorado", "Corvette", "Cruze", "Equinox", "Express", "Impala", "Malibu", "Silverado", "Sonic", "Spark", "Suburban", "Tahoe", "Trailblazer", "Traverse", "Trax"),
        "Audi" to listOf("A1", "A3", "A4", "A5", "A6", "A7", "A8", "Q2", "Q3", "Q4 e-tron", "Q5", "Q7", "Q8", "R8", "RS3", "RS5", "S4", "S8", "TT", "e-tron GT"),
        "Nissan" to listOf("Ariya", "Altima", "Juke", "Kicks", "Leaf", "Micra", "Navara", "Patrol", "Qashqai", "Rogue", "Sentra", "Terra", "X-Trail"),
        "Peugeot" to listOf("2008", "208", "3008", "308", "5008", "508", "Expert", "Partner", "Rifter", "Traveller"),
        "Kia" to listOf("Carnival", "Ceed", "EV6", "Optima", "Picanto", "Rio", "Seltos", "Sorento", "Sportage", "Stonic"),
        "Mazda" to listOf("BT-50", "CX-3", "CX-30", "CX-5", "CX-9", "MX-5", "Mazda2", "Mazda3", "Mazda6"),
        "Subaru" to listOf("Ascent", "BRZ", "Crosstrek", "Forester", "Impreza", "Legacy", "Outback", "WRX"),
        "Volvo" to listOf("C40 Recharge", "S60", "S90", "V60", "V90", "XC40", "XC60", "XC90"),
        "Tesla" to listOf("Cybertruck", "Model 3", "Model S", "Model X", "Model Y", "Roadster"),
        "Fiat" to listOf("500", "500L", "500X", "Doblo", "Ducato", "Egea", "Fiorino", "Panda", "Strada", "Tipo"),
        "Jeep" to listOf("Compass", "Gladiator", "Grand Cherokee", "Renegade", "Wagoneer", "Wrangler"),
        "Land Rover" to listOf("Defender", "Discovery", "Discovery Sport", "Range Rover", "Range Rover Evoque", "Range Rover Sport"),
        "Mitsubishi" to listOf("ASX", "Eclipse Cross", "Lancer", "Mirage", "Outlander", "Pajero", "Triton")
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Marka Spinner'ı için adaptör
        val brandList = brandModelMap.keys.toList()
        val brandAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            brandList
        )
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBrand.adapter = brandAdapter

        // Spinner prompt olarak başlık ekleme
        binding.spinnerBrand.prompt = "Markayı Seçiniz"

        // Model Spinner'ı için değiştirilebilir başlangıç listesi
        val modelList = mutableListOf<String>()
        val modelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            modelList
        )
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerModel.adapter = modelAdapter

        // Spinner prompt olarak başlık ekleme
        binding.spinnerModel.prompt = "Modeli Seçiniz"

        // Marka seçildiğinde modele özel listeyi güncelle
        binding.spinnerBrand.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedBrand = parent?.getItemAtPosition(position).toString()
                val models = brandModelMap[selectedBrand] ?: emptyList()
                modelList.clear() // Model listesini temizle
                modelList.addAll(models) // Yeni modelleri ekle
                modelAdapter.notifyDataSetChanged() // Adaptörü güncelle
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        }

        // Plaka girişine TextWatcher ekle
        binding.inputPlate.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (isUpdating) return

                isUpdating = true

                val input = editable.toString().replace(" ", "").uppercase() // Boşlukları kaldır ve büyük harfe çevir
                val formattedInput = StringBuilder()

                var numbersWritten = 0 // Sol taraftaki sayıların sayısı
                var lettersWritten = 0 // Ortadaki harflerin sayısı
                var digitsWritten = 0 // Sağ taraftaki sayıların sayısı
                var hasAddedDigitSpace = false // Harflerden sonra boşluk eklenip eklenmediğini kontrol et

                for (char in input) {
                    when {
                        // İlk iki karakter sayı olacak
                        numbersWritten < 2 && char.isDigit() -> {
                            formattedInput.append(char)
                            numbersWritten++
                            if (numbersWritten == 2) formattedInput.append(" ") // İlk iki sayıdan sonra boşluk
                        }
                        // Ortadaki harfler en fazla 3 karakter olacak
                        numbersWritten == 2 && lettersWritten < 3 && char.isLetter() -> {
                            formattedInput.append(char)
                            lettersWritten++
                            hasAddedDigitSpace = false // Harflerden sonra boşluk için izin ver
                        }
                        // Sağ taraftaki sayılar en fazla 5 karakter olacak
                        lettersWritten > 0 && digitsWritten < 5 && char.isDigit() -> {
                            if (!hasAddedDigitSpace) {
                                formattedInput.append(" ") // Harflerden sonra sayı geliyorsa boşluk ekle
                                hasAddedDigitSpace = true // Bir kez boşluk eklediğini işaretle
                            }
                            formattedInput.append(char)
                            digitsWritten++
                        }
                        else -> {
                            // Hatalı giriş
                            Toast.makeText(binding.root.context, "Plakayı yanlış girdiniz!", Toast.LENGTH_SHORT).show()
                            isUpdating = false
                            return
                        }
                    }
                }

                editable?.replace(0, editable.length, formattedInput.toString().trimEnd()) // Düzenlenmiş metni uygula
                isUpdating = false
            }
        })

        binding.addVehicleButton.setOnClickListener{
            val selectedBrand = binding.spinnerBrand.selectedItem?.toString()
            val selectedModel = binding.spinnerModel.selectedItem?.toString()
            val year = binding.inputYear.text.toString()
            val plate = binding.inputPlate.text.toString()

            if(selectedBrand.isNullOrEmpty() || selectedModel.isNullOrEmpty() || year.isNullOrEmpty() || plate.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurunuz.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val vehicle = VehicleEntity(
                    brand = selectedBrand,
                    model = selectedModel,
                    year = year.toInt(),
                    plate = plate
                )
                lifecycleScope.launch {
                    vehicleViewModel.insertVehicle(vehicle)
                    Toast.makeText(requireContext(), "Araç başarıyla eklendi!", Toast.LENGTH_SHORT).show()
                    LogWrapper.d("AddVehicleFragment_addVehicle", "$vehicle bilgilerine sahip araç kaydı başarıyla veritabanına eklendi.")

                }
            }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
