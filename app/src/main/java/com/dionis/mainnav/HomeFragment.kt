package com.dionis.mainnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dionis.mainnav.databinding.FragmentHomeBinding
import com.dionis.mainnav.util.navTo


class HomeFragment : Fragment() {

    companion object {
        const val REPLY = "REPLY"
    }

    private lateinit var binding: FragmentHomeBinding
    private var result: Float? = null
    var weightError = false
    var heightError = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setUpClicks()
    }

    override fun onResume() {
        super.onResume()
        returnScreen()
    }

    private fun setUpClicks() {
        binding.btCalcular.setOnClickListener {
            checkHeight()
            checkWeight()

            if (!weightError && !heightError) {
                calculateImc()
                nextScreen()
            }
        }
    }

    private fun returnScreen() {
        binding.altura.setText(R.string.empty)
        binding.peso.setText(R.string.empty)
    }

    private fun nextScreen() {
        val args = Bundle()
        binding.btCalcular.text = result.toString()
        args.putString(SecondScreenFragment.SEND, binding.btCalcular.text.toString())
        navTo(R.id.action_homeFragment_to_secondScreenFragment, args)
    }

    private fun checkWeight() {
        if (binding.peso.text.isNullOrEmpty()) {
            binding.peso.error = "digite seu peso"
            Toast.makeText(requireContext(), "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            weightError = true
        } else {
            weightError = false
        }
    }

    private fun checkHeight() {
        if (binding.altura.text.isNullOrEmpty()) {
            binding.altura.error = "digite sua altura"
            Toast.makeText(requireContext(), "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            heightError = true
        } else {
            heightError = false
        }
    }

    private fun calculateImc() {
        val peso = binding.peso.text.toString().toFloat()
        val alt = binding.altura.text.toString().toFloat()
        result = peso / (alt * alt)
    }
}