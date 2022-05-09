package org.sopt.diablo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.data.request.RequestSignIn
import org.sopt.diablo.data.response.BaseResponse
import org.sopt.diablo.data.response.ResponseSignIn
import org.sopt.diablo.databinding.ActivityLoginBinding
import org.sopt.diablo.util.enqueueUtil
import retrofit2.Call

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initEvent()
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener {
            if (isLoginFormsValid) loginNetwork()
            else Toast.makeText(this, "아이디/비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    private val isLoginFormsValid: Boolean
        get() = binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.let {
                with(binding) {
                    etId.setText(it.getStringExtra("id"))
                    etPw.setText(it.getStringExtra("pw"))
                }
            }
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        ServiceCreator.soptService.postSignIn(requestSignIn).apply {
            enqueueUtil(
                onSuccess = {
                    val data = it?.data
                    Toast.makeText(this@LoginActivity, "${data?.name}님 반갑습니다!", Toast.LENGTH_SHORT)
                        .show()

                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        putExtra("id", data?.id)
                        putExtra("name", data?.name)
                        startActivity(this)
                    }
                    finish()
                },
                onError = {
                    Toast.makeText(this@LoginActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}