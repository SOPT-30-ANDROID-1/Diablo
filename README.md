# Diablo

>만약 내가 힘을 통제하지 못하게 되면?

<img src="https://user-images.githubusercontent.com/33388801/161443084-d5967597-820c-41ae-9639-d815d6111ba8.png" width=250/>


---
## Seminar

| Week  |            Contents            |   Date   |                    Summary                    | Check |
| :---: | :----------------------------: | :------: | :-------------------------------------------: | :---: |
| 1주차 |    안드로이드 기초와 View/ViewGroup     | 22/04/02 | [1st Seminar](#seminar1) |  ✅   |
| 2주차 |    Fragment와 Recycler View     | 22/04/09 |  |     |
---

## seminar1
### LEVEL1.

- 아이디,비밀번호 모두 입력이 되어있을때만 로그인 버튼을 눌렀을 때 `HomeActivity`로 이동
- 회원가입 버튼을 클릭시 `SignUpActivity`로 이동

    ```kotlin
    private fun initView() {
        binding.btnLogin.setOnClickListener {
            if (checkInput()) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                Intent(this, HomeActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            } else {
                Toast.makeText(this, "아이디/비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSingup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    private fun checkInput(): Boolean {
        return binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()
    }

    ```

### LEVEL2.

- 회원가입(SignUpActivity)이 성공한다면, 이전 로그인 화면으로 돌아옵니다.
- 이 때, 회원가입에서 입력했던 아이디와 비밀번호가 입력되어 있어야 합니다.
    [ SingUpActivity (정보를 주는 쪽) ]
    ```kotlin
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
    ```

    [ LoginActivity (정보를 받는 쪽) ]
    ```kotlin
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.let {
                binding.apply {
                    etId.setText(it.getStringExtra("id"))
                    etPw.setText(it.getStringExtra("pw"))
                }
            }
        }
    }
    ```