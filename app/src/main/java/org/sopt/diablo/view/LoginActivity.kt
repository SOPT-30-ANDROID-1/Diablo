package org.sopt.diablo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.data.request.RequestSignIn
import org.sopt.diablo.databinding.ActivityLoginBinding
import org.sopt.diablo.util.MyApplication
import org.sopt.diablo.util.PreferenceUtil
import org.sopt.diablo.util.enqueueUtil
import org.sopt.diablo.util.showToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initEvent()
    }

    private fun initEvent() {
        if (isAutoLoginValid) {
            showToast("${MyApplication.prefs.getName()}님 자동 로그인 되었습니다")
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
        else {
            binding.btnLogin.setOnClickListener {
                if (isLoginFormsValid) loginNetwork()
                else showToast("아이디/비밀번호를 입력해주세요")
            }

            binding.btnSignup.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                signUpActivityLauncher.launch(intent)
            }
        }
    }

    private val isAutoLoginValid: Boolean
        get() = !MyApplication.prefs.getId().isNullOrBlank() && !MyApplication.prefs.getName().isNullOrBlank()

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
        val id = binding.etId.text.toString()
        val password = binding.etPw.text.toString()
        val requestSignIn = RequestSignIn(
            id = id,
            password = password
        )

        ServiceCreator.soptService.postSignIn(requestSignIn).apply {
            enqueueUtil(
                onSuccess = {
                    val name = it?.data?.name.toString()
                    showToast("${name}님 반갑습니다!")
                    with(MyApplication.prefs) {
                        setId(id)
                        setName(name)
                        setPw(password)
                    }
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                        startActivity(it)
                    }
                    finish()
                },
                onError = {
                    showToast("로그인에 실패하셨습니다.")
                }
            )
        }
    }
}