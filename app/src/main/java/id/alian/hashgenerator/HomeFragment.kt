package id.alian.hashgenerator

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import id.alian.hashgenerator.databinding.FragmentHomeBinding
import id.alian.hashgenerator.viewmodel.HashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel

        onGenerateClicked()
        return binding.root
    }

    private fun onGenerateClicked() {
        binding.generateButton.setOnClickListener {
            if (binding.plainText.text.toString().trim().isEmpty()) {
                showSnackBar("Field empty")
            } else {
                lifecycleScope.launch {
                    playAnimation()
                    navigateToSuccess(getHashData())
                }
            }
        }
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

    private fun navigateToSuccess(hash: String) {
        val directions = HomeFragmentDirections.actionHomeFragmentToSuccessFragment(hash)
        findNavController().navigate(directions)
    }

    private fun getHashData(): String {
        val algorithm = binding.autoCompleteTextView.text.toString()
        val plainText = binding.plainText.text.toString().trim()
        return viewModel.getHash(plainText, algorithm)
    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(binding.rootLayout, message, Snackbar.LENGTH_SHORT)
        snackBar.setAction("OK") { snackBar.dismiss() }
        snackBar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clear_menu) {
            binding.plainText.text.clear()
            showSnackBar("cleared.")
            return true
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithms)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}