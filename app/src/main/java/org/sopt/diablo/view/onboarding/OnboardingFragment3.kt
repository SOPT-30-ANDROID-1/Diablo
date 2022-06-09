package org.sopt.diablo.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.diablo.R
import org.sopt.diablo.databinding.FragmentOnboarding3Binding
import org.sopt.diablo.util.MyApplication

class OnboardingFragment3 : Fragment() {
    private var _binding: FragmentOnboarding3Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnItemClickListener()
    }

    private fun setOnItemClickListener() {
        binding.btnStart.setOnClickListener {
            MyApplication.prefs.setOnboardingActivated()
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}