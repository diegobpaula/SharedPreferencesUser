package com.diegobpaula.sharedpreferences

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.diegobpaula.sharedpreferences.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var cor = ""

    companion object {
        const val ARQUIVO_PREFERENCIAS = "ArquivoPreferencias"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()

        binding.cor1.setOnClickListener {
            cor = "#FF03DAC5"
            salvar(cor)
        }

        binding.cor2.setOnClickListener {
            cor = "#FFBB86FC"
            salvar(cor)

        }

        binding.cor3.setOnClickListener {
            cor = "#3F51B5"
            salvar(cor)
        }

        binding.cor4.setOnClickListener {
            cor = "#F44336"
            salvar(cor)
        }
    }

    private fun salvar(cor: String){

        binding.layouPrincipal.setBackgroundColor(Color.parseColor(cor))

        binding.btTrocarCor.setOnClickListener {view ->

            val preferencias  = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("cor", cor)
            editor.apply()

            snackbar(view)
        }
    }

    private fun snackbar(view: View){
        val snackbar = Snackbar.make(view, "Cor de fundo alterada com sucesso!", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK"){

        }
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()

        val preferencias  = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE)
        val cor = preferencias.getString("cor", "")

        if (cor!!.isNotEmpty()){
            binding.layouPrincipal.setBackgroundColor(Color.parseColor(cor))
        }
    }
}