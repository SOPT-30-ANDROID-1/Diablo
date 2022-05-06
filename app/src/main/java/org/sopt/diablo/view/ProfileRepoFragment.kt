package org.sopt.diablo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.diablo.adapter.RepoAdapter
import org.sopt.diablo.data.RepoData
import org.sopt.diablo.databinding.FragmentProfileRepoBinding

class ProfileRepoFragment : Fragment() {
    private lateinit var repoAdapter: RepoAdapter
    private var _binding: FragmentProfileRepoBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileRepoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter().apply {
            repoList.addAll(
                listOf(
                    RepoData("레포1", "과제레포입니다"),
                    RepoData("레포2", "과제레포입니다"),
                    RepoData("레포3", "과제레포입니다"),
                    RepoData("레포4", "과제레포입니다"),
                    RepoData("레포5", "과제레포입니다"),
                    RepoData("레포6", "과제레포입니다"),
                    RepoData("레포7", "과제레포입니다"),
                    RepoData("레포8", "과제레포입니다"),
                    RepoData("레포9", "과제레포입니다"),
                    RepoData("레포10", "과제레포입니다"),
                    RepoData("레포11", "과제레포입니다"),
                )
            )
            notifyDataSetChanged()
        }
        binding.rvRepoList.adapter = repoAdapter
    }

}