package com.dionis.mainnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import com.dionis.mainnav.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : Fragment() {

    companion object {
        const val SEND = "SEND"
    }

    private lateinit var binding: FragmentSecondScreenBinding
    private var sent: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)


        // obtem a mensagem enviada pelo fragment
        arguments?.let {
            sent = it.getString(SEND, null)
        }

        // exibe a mensagem obtida
        sent.let { value ->
            binding.resultado.text = value.format("%.2f")
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        definition()
    }

    private fun setUpClicks() {
        binding.btRecalcular.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun definition() {
        val grau: Double = sent.toDouble()

        when (grau) {
            in grau..15.9 -> {
                binding.definicao.text = "desnutrição nível III,\n Procure orientação médica"
                binding.resultScreen.background = requireContext().getDrawable(R.color.warning)
            }

            in grau..16.9 -> {
                binding.definicao.text = "desnutrição nível II,\n Procure orientação médica"
                binding.resultScreen.background = requireContext().getDrawable(R.color.preWarning)
            }

            in grau..18.4 -> {
                binding.definicao.text = "desnutrição nível I,\n Alimente-se bem"
                binding.resultScreen.background = requireContext().getDrawable(R.color.preWarning)
            }

            in grau..24.9 -> {
                binding.definicao.text = "seu peso está normal"
                binding.resultScreen.background = requireContext().getDrawable(R.color.good)
            }

            in grau..29.9 -> {
                binding.definicao.text = "pré obesidade,\n cuide da sua alimentação"
                binding.resultScreen.background = requireContext().getDrawable(R.color.preWarning)
            }

            in grau..34.5 -> {
                binding.definicao.text = "OBESIDADE GRAU I,\n cuide da sua alimentação"
                binding.resultScreen.background = requireContext().getDrawable(R.color.preWarning)
            }

            in grau..39.9 -> {
                binding.definicao.text = "OBESIDADE GRAU II,\n Procure orientação médica"
                binding.resultScreen.background = requireContext().getDrawable(R.color.warning)
            }

            else -> {
                binding.definicao.text = "OBESIDADE GRAU III,\n Procure orientação médica"
                binding.resultScreen.background = requireContext().getDrawable(R.color.warning)
            }
        }
    }


}