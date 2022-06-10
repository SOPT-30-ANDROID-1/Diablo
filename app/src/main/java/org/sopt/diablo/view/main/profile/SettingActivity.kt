package org.sopt.diablo.view.main.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.diablo.databinding.ActivitySettingBinding
import org.sopt.diablo.util.MyApplication

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initEvent()
    }

    private fun initEvent() {
        binding.switchAutologin.isChecked = isAutoLoginValid

        with(binding.switchAutologin) {
            setOnClickListener {
                this.isChecked != this.isChecked
                if(this.isChecked) {
                    MyApplication.prefs.setAutoLogin()
                } else {
                    MyApplication.prefs.disableAutoLogin()
                }
            }
        }
    }

    private val isAutoLoginValid: Boolean
        get() = MyApplication.prefs.getAutoLogin()
}