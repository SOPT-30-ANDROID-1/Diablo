package org.sopt.diablo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
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
        with(binding) {
            Glide.with(this@DetailActivity).load(intent.getStringExtra("profile")).circleCrop().into(binding.ivProfile)
            tvNameContent.text = intent.getStringExtra("name")
            tvIntroductionContent.text = intent.getStringExtra("introduction")
        }
    }
}