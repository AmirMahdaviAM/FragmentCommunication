package github.amirmahdavi.communication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.amirmahdavi.communication.SharedViewModel
import github.amirmahdavi.communication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        // Observe value from ViewModel and set in TextView
        sharedViewModel.secondData.observe(viewLifecycleOwner, Observer {
            binding.tvData.text = it.toString()
        })
        // Change ViewModel value
        var firstTime = true
        binding.btnSendData.setOnClickListener {
            if (firstTime) {
                sharedViewModel.secondData.value = "Changed Value"
                firstTime = false
            } else {
                sharedViewModel.secondData.value = "Initial Value"
                firstTime = true
            }
        }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }
}
