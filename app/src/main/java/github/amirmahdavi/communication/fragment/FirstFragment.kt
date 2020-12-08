package github.amirmahdavi.communication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import github.amirmahdavi.communication.SharedViewModel
import github.amirmahdavi.communication.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        // Observe value from ViewModel and set in TextView
        sharedViewModel.getData.observe(viewLifecycleOwner, Observer {
            binding.tvData.text = it.toString()
        })
        // Change ViewModel value
        binding.btnSendData.setOnClickListener {
            sharedViewModel.setData("Text from First Fragment")
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
