package org.sopt.diablo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.diablo.R
import org.sopt.diablo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initData()
    }

    private fun initData() {
        binding.ivProfile.setImageResource(intent.getIntExtra("name", R.drawable.ic_launcher_background))
        binding.tvNameContent.text = intent.getStringExtra("name")
        binding.tvIntroductionContent.text = intent.getStringExtra("introduction")
    }
}