package id.alian.hashgenerator

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import id.alian.hashgenerator.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithms)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.generateButton.setOnClickListener {
            lifecycleScope.launch {
                playAnimation()
                navigateToSuccess()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    private suspend fun playAnimation() {
        binding.generateButton.isClickable = false
        binding.titleTextView.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
        binding.textInputLayout.animate().alpha(0f).duration = 400L
        binding.plainText.animate().alpha(0f).duration = 400L
        delay(300)
        binding.sucessBackground.animate().alpha(1f).duration = 600L
        delay(500)
        binding.sucessImageView.animate().alpha(1f).duration = 1000L
        delay(1500L)
    }

    private fun navigateToSuccess() {
        findNavController().navigate(R.id.action_homeFragment_to_successFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}