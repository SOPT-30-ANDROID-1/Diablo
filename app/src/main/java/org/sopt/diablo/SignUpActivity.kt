package org.sopt.diablo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.diablo.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.btnSignup.setOnClickListener {
            if (checkInput()) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    putExtra("id", binding.etId.text.toString())
                    putExtra("pw", binding.etPw.text.toString())
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkInput(): Boolean {
        return binding.etName.text.isNotEmpty() && binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()
    }

}