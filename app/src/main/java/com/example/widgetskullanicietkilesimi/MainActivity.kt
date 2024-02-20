package com.example.widgetskullanicietkilesimi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.example.widgetskullanicietkilesimi.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var control = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonresim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.resim1)
        }

        binding.buttonresim2.setOnClickListener {
            binding.imageView.setImageResource(
                resources.getIdentifier(
                    "resim2",
                    "drawable",
                    packageName
                )
            )

        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.e("Sonuç", "On")
            } else {
                Log.e("Sonuç", "Off")
            }

        }

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE

        }

        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewSonuc.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")
        ulkeler.add("İngiltere")

        val arrayAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, ulkeler)

        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            control =isChecked
            if (control){
                val secilenButton=findViewById<Button>(checkedId)
                val buttonYazi =secilenButton.text.toString()
                Log.e("Sonuç", "Kategori : $buttonYazi")

            }
        }

        binding.buttonSaat.setOnClickListener {
            val tp= MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            tp.show(supportFragmentManager,"")

            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour} : ${tp.minute}")
            }
        }
        binding.buttonTarih.setOnClickListener {
        val dp=MaterialDatePicker.Builder.datePicker()
            .setTitleText("Tarih seç")
            .build()

            dp.show(supportFragmentManager,"")
            dp.addOnPositiveButtonClickListener {
                val df = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
                val tarih= df.format(it)                                    // Tarih bilgisi milisaniye türünde olur. o yüzden long

                binding.editTextTarih.setText(tarih)

            }

        }


        binding.buttonToast.setOnClickListener {
            Toast.makeText(this,"Merhaba", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSnackBar.setOnClickListener {

            Snackbar.make(it, "Silmek ister misiniz?", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){


                    Snackbar.make(it, "Silindi.", Snackbar.LENGTH_SHORT).show()
                       // .setBackgroundTint(Color.WHITE)
                        //.setActionTextColor(Color.RED)
                        // .show()


                }
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLUE)
                .setActionTextColor(Color.RED)
                .show()
        }




        binding.buttonAlert.setOnClickListener {
        MaterialAlertDialogBuilder(this)
            .setTitle("Başlık")
            .setMessage("İçerik")
            .setPositiveButton("Tamam") { d,i ->
                Toast.makeText(this,"Tamam seçildi.", Toast.LENGTH_SHORT).show()



            }
            .setNegativeButton("İptal") { d,i ->
                Toast.makeText(this,"İptal Seçildi.", Toast.LENGTH_SHORT).show()



            }
            .show()
        }











        binding.buttonGoster.setOnClickListener {
            Log.e("Sonuç", "Switch durum : ${binding.switch1.isChecked}")
            Log.e("Sonuç", "Slider durum : ${binding.slider.progress}")
            Log.e("Sonuç", "Ülke durum   : ${binding.autoCompleteTextView.text}")
            if (control){
                val secilenButton=findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi =secilenButton.text.toString()
                Log.e("Sonuç", "Kategori Durum: $buttonYazi")

            }
        }


    }
}