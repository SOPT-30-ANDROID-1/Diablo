package org.sopt.diablo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.diablo.adapter.FeedAdapter
import org.sopt.diablo.adapter.FollowerAdapter
import org.sopt.diablo.data.FeedData
import org.sopt.diablo.data.FeedImageData
import org.sopt.diablo.databinding.FragmentCameraBinding

class CameraFragment: Fragment() {
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
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
//        val customDecoration = GridSpaceDecoration(10)
        val imagesThree = listOf(
            FeedImageData("https://avatars.githubusercontent.com/u/105535772?s=200&v=4", false, false),
            FeedImageData("https://avatars.githubusercontent.com/u/105535772?s=200&v=4", false, false),
            FeedImageData("https://avatars.githubusercontent.com/u/105535772?s=200&v=4", false, false)
        )
        val imagesTwo = listOf(
            FeedImageData("https://avatars.githubusercontent.com/u/105535772?s=200&v=4", false, false),
            FeedImageData("https://avatars.githubusercontent.com/u/105535772?s=200&v=4", false, false),
        )
        val feedAdapter = FeedAdapter().apply {
            feedList.addAll(
                listOf(
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesThree ),
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesTwo ),
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesThree ),
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesThree ),
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesTwo ),
                    FeedData("title", "https://avatars.githubusercontent.com/u/105535772?s=200&v=4","description", imagesThree )
                )
            )
            notifyDataSetChanged()
        }
        binding.rvFeedList.apply {
//            addItemDecoration(customDecoration)
            adapter = feedAdapter
        }
    }
}