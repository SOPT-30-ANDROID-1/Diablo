package org.sopt.diablo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.diablo.adapter.RepoAdapter
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.databinding.FragmentProfileRepoBinding
import org.sopt.diablo.util.MyApplication
import org.sopt.diablo.util.enqueueUtil

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
        repoAdapter = RepoAdapter()
        val id = MyApplication.prefs.getId()
        ServiceCreator.githubService.getRepos(id).apply {
            enqueueUtil(
                onSuccess = {
                    repoAdapter.setItems(it)
                }
            )
        }
        binding.rvRepoList.adapter = repoAdapter
    }
}