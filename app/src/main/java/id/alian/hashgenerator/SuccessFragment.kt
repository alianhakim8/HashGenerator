package id.alian.hashgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import id.alian.hashgenerator.databinding.FragmentSuccessBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuccessFragment : Fragment() {

    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!

    private val args: SuccessFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.hashTextView.text = args.hash

        binding.buttonCopy.setOnClickListener {
            onCopyClicked()
        }

        return binding.root
    }

    private fun onCopyClicked() {
        copyToClipboard(args.hash)
        lifecycleScope.launch {
            applyAnimation()
        }
        Toast.makeText(requireContext(), "copied!", Toast.LENGTH_SHORT).show()
    }

    private fun copyToClipboard(hash: String) {
        val clipBoardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted Text", hash)
        clipBoardManager.setPrimaryClip(clipData)

    }

    private suspend fun applyAnimation() {
        binding.include.messageBackground.animate().translationY(80f).duration = 200L
        binding.include.messageTextView.animate().translationY(80f).duration = 200L

        delay(2000L)

        binding.include.messageBackground.animate().translationY(-80f).duration = 500L
        binding.include.messageTextView.animate().translationY(-80f).duration = 500L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}