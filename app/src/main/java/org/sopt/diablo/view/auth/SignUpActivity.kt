package org.sopt.diablo.view.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.data.request.RequestSignUp
import org.sopt.diablo.databinding.ActivitySignUpBinding
import org.sopt.diablo.util.enqueueUtil
import org.sopt.diablo.util.showToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initEvent()
    }

    private fun initEvent() {
        binding.btnSignup.setOnClickListener {
            if (isSignUpFormValid) signUpNetwork()
            else showToast("입력되지 않은 정보가 있습니다")
        }
    }

    private val isSignUpFormValid: Boolean
        get() = binding.etName.text.isNotEmpty() && binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()

    private fun signUpNetwork() {
        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        ServiceCreator.soptService.postSignUp(requestSignUp).apply {
            enqueueUtil(
                onSuccess = {
                    showToast("회원가입 성공")
                    Intent().apply {
                        putExtra("id", binding.etId.text.toString())
                        putExtra("pw", binding.etPw.text.toString())
                        setResult(Activity.RESULT_OK, this)
                    }
                    finish()
                },
                onError = {
                    showToast("회원가입에 실패하셨습니다.")
                }
            )
        }
    }
}