# Diablo

>만약 내가 힘을 통제하지 못하게 되면?

<img src="https://user-images.githubusercontent.com/33388801/161443084-d5967597-820c-41ae-9639-d815d6111ba8.png" width=250/>


---
## Seminar

| Week  |            Contents            |   Date   |                    Summary                    | Check |
| :---: | :----------------------------: | :------: | :-------------------------------------------: | :---: |
| 1주차 |    안드로이드 기초와 View/ViewGroup     | 22/04/02 |[1st Seminar](#seminar1)|  ✅   |
| 2주차 |    Fragment와 Recycler View     | 22/04/09 |[2nd Seminar](#seminar2)|✅   |
| 3주차 |    ViewPager2와 앱 내 디자인 적용     | 22/04/23 |[3rd Seminar](#seminar3)|✅   |
| 4주차 |    Retrofit2로 서버와 통신하기     | 22/04/23 |[4th Seminar](#seminar4)|✅   |
---

## seminar1
### 구현화면

| 로그인 | 회원가입 | 스크롤뷰 |
| :---: | :---: | :---: |
|<img width="100%" src="https://user-images.githubusercontent.com/71129059/162563940-2526fc1a-27a6-4d91-b6fb-d9d8df264fa4.gif">|<img width="100%" src="https://user-images.githubusercontent.com/71129059/162563943-612853ff-0426-4cc2-b281-bdbfc828c599.gif">|<img width="100%" src="https://user-images.githubusercontent.com/71129059/162564039-001b6836-4d70-434c-9496-bafbcd1682e5.gif">|
### LEVEL1.

- 아이디,비밀번호 모두 입력이 되어있을때만 로그인 버튼을 눌렀을 때 `HomeActivity`로 이동
    - `isNotEmpty()` 함수 활용
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

    [ SingUpActivity (보내는 쪽) ]
    - `putExtra`로 data 넘겨주기
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

    [ LoginActivity (받는 쪽) ]
    - `registerForActivityResult`로 정보 받아오기
         - `registerForActivityResult`에서는 `launch`를 사용해준다.
         - `registerForActivityResult`의 콜백함수에 데이터를 받아 할 일들을 적어준다.
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
    ```kotlin
    // initView 함수 내
    binding.btnSingup.setOnClickListener {
        val intent = Intent(this, SignUpActivity::class.java)
        signUpActivityLauncher.launch(intent)
    }
    ```

- 사진의 비율을 1:1로 만들어주세요.
    - `constraintDimensionRatio` 활용
    ```xml
    <ImageView
        android:id="@+id/iv_profile"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginTop="30dp"
        android:src="@drawable/ic_launcher_background"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintWidth_percent="0.3" />
    ```

- 자기소개에 스크롤뷰 적용
    - `NestedScrollView` 활용
        - ScrollView의 parent는 ConstraintLayout이므로, 제약 필수
        - TextView의 parent는 ScrollView이므로 제약 없어도 됨
    ```xml
    <androidx.core.widget.NestedScrollView
        android:id="@+id/scroll_introduction"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginHorizontal="30dp"
        android:layout_marginTop="15dp"
        android:layout_marginBottom="30dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_mbti">
        
        <TextView
            android:id="@+id/tv_introduction"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="블라블라~"
            android:textColor="@color/dark_navy"
            android:textSize="17sp" />
        
    </androidx.core.widget.NestedScrollView>
    
    ```



---
## seminar2
### 구현화면

| Fragment 전환 | 디테일뷰 |
| :---: | :---: |
|<img width="50%" src="https://user-images.githubusercontent.com/71129059/164742749-0bc2eded-49d1-4a7b-99c1-4750597f74be.gif">|<img width="50%" src="https://user-images.githubusercontent.com/71129059/164742733-09595e12-fa47-42ed-9bb1-c68b824b3c41.gif">|

### LEVEL1.

- 자기소개 페이지를 만든 HomeActivity 하단에 FollowerRecyclerView, RepositoryRecyclerView 만들기
- HomeActivity 하단에 NestedScrollView로 FragmentContainerView 지정
- FollowerListFragment와 RepoListFragment 사용
    ```xml
    <androidx.core.widget.NestedScrollView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginVertical="20dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="@id/btn_repo"
        app:layout_constraintStart_toStartOf="@id/btn_follower"
        app:layout_constraintTop_toBottomOf="@id/btn_follower">

        <androidx.fragment.app.FragmentContainerView
            android:id="@+id/container_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </androidx.core.widget.NestedScrollView>
    ```
- 버튼 클릭시 FollowerListFragment와 RepoListFragment로 이동하도록 설정
    ```kotlin

    private fun initTransactionEvent() {
        val followerListFragment = FollowerListFragment()
        val repositoryListFragment = RepoListFragment()
        supportFragmentManager.beginTransaction().add(R.id.container_list, followerListFragment).commit()

        binding.apply {
            btnFollower.setOnClickListener {
                if (position == REPO_POSITION) {
                    fragmentManage(followerListFragment, FOLLOWER_POSITION)
                }
            }
            btnRepo.setOnClickListener{
                if (position == FOLLOWER_POSITION) {
                    fragmentManage(repositoryListFragment, REPO_POSITION)
                }
            }
        }
    }

    private fun fragmentManage(fragment: Fragment, pos: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.container_list, fragment).commit()
        position = pos
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
    ```


### LEVEL2.

- 아이템 클릭시 상세설명을 보여주는 Activity로 이동하기
    ```kotlin
    // FollowerAdapter.kt
        interface ItemClickListener {
        fun onClick(data: UserData)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
    ```
    ```kotlin
    // FollowerListFragment.kt
    private fun listClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.ItemClickListener {
            override fun onClick(data: UserData) {
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("profile", data.profile)
                    putExtra("name", data.name)
                    putExtra("introduction", data.introduction)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        })
    }
    ```
    ```kotlin
    // DetailActivity.kt
    private fun initData() {
        binding.ivProfile.setImageResource(intent.getIntExtra("name", R.drawable.ic_launcher_background))
        binding.tvNameContent.text = intent.getStringExtra("name")
        binding.tvIntroductionContent.text = intent.getStringExtra("introduction")
    }
    ```

- itemDecoration을 통해 리스트 간 간격 주기

    ```kotlin
    class GridSpaceDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when(parent.getChildAdapterPosition(view) % 2) {
            0 -> {
                outRect.right = Math.ceil(margin.toDouble() / 2).toInt()
            }
            1 -> {
                outRect.left = Math.floor(margin.toDouble() / 2).toInt()
            }
        }
        outRect.bottom = margin
        }
    }
    ```


---
## seminar3
### 구현화면

| 회원가입 및 로그인 디자인 적용 | ViewPager2 및 TabLayout 적용 |
| :---: | :---: |
|<img width="50%" src="https://user-images.githubusercontent.com/71129059/167178771-fba90f5e-5360-4a14-bac6-55763d47c5f4.gif">|<img width="50%" src="https://user-images.githubusercontent.com/71129059/167179291-a581b7bc-1fd5-4a99-a326-a6f37b0b4927.gif">|

### LEVEL1.
- Selector를 통한 버튼 구현
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_selected="true" android:drawable="@drawable/shape_bg_sopt_button_selected"/>
    <item android:state_selected="false" android:drawable="@drawable/shape_bg_sopt_button_notselected"/>
</selector>
```

- ProfileFragment에서 버튼에 isSelected 설정
```kotlin
// ProfileFragment.kt
private fun initTransactionEvent() {
    val followerFragment = ProfileFollowerFragment()
    val repoFragment = ProfileRepoFragment()
    childFragmentManager.beginTransaction().add(R.id.container_list, followerFragment).commit()

    with(binding) {
        btnFollower.isSelected = true
        btnRepo.isSelected = false
        btnFollower.setOnClickListener {
            if (position == REPO_POSITION) {
                fragmentManage(followerFragment, FOLLOWER_POSITION)
            }
        }
        btnRepo.setOnClickListener{
            if (position == FOLLOWER_POSITION) {
                fragmentManage(repoFragment, REPO_POSITION)
            }
        }
    }
}

private fun fragmentManage(fragment: Fragment, pos: Int) {
    childFragmentManager.beginTransaction().replace(R.id.container_list, fragment).commit()
    with(binding) {
        btnFollower.isSelected = !btnFollower.isSelected
        btnRepo.isSelected = !btnRepo.isSelected
    }
    position = pos
}
```

### LEVEL2.
- ViewPager2의 중첩 스크롤 문제 해결 : `NestedScrollableHost` Util
- Inner ViewPager2를 `NestedScrollabeHost`로 감싸준다

```xml
<org.sopt.diablo.util.NestedScrollableHost
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tab_follow">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_follow"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</org.sopt.diablo.util.NestedScrollableHost>
```

---
## seminar4
### 구현화면

| 회원가입 서버통신 | 로그인 및 깃허브 서버통신 |
| :---: | :---: |
|<img width="50%" src="https://user-images.githubusercontent.com/71129059/168304813-3a951147-ae35-47ef-887a-005de7a45c56.gif">|<img width="50%" src="https://user-images.githubusercontent.com/71129059/168304819-e8407870-933f-4bf2-b783-9710b80440d8.gif">|

### LEVEL1.
- 로그인/회원가입 API 연동
- 확장함수 사용
```kotlin
// ContextExtension.kt
fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest", "error:$t")
        }

    })
}
```

```kotlin
// SignUpActivity.kt
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
                Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
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
```

### LEVEL2.
- Github API 연동
- SharedPreference 사용 (로그인된 깃허브 아이디로 서버통신을 하기 위해서)
```kotlin
// PreferenceUtil

class PreferenceUtil(context: Context) {
    private val ACCOUNT : String = "account"

    private val accountPrefs: SharedPreferences =
        context.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE)

    fun getId(): String = accountPrefs.getString("id", "").toString()
    fun setId(str: String) = accountPrefs.edit().putString("id", str).apply()

    fun getPw(): String = accountPrefs.getString("password", "").toString()
    fun setPw(str: String) = accountPrefs.edit().putString("password", str).apply()

    fun getName(): String = accountPrefs.getString("name", "").toString()
    fun setName(str: String) = accountPrefs.edit().putString("name", str).apply()

}
```

- 로그인시 id, name, password를 SharedPreference로 저장 
```kotlin
// LoginActivity.kt
with(MyApplication.prefs) {
    setId(id)
    setName(name)
    setPw(password)
}
```

- ProfileFragment에서 SharedPreference에 저장된 id와 name을 불러온 후, 깃허브 서버통신
```kotlin
// ProfileFragment.kt
private fun initView() {
    val name = MyApplication.prefs.getName()
    val id = MyApplication.prefs.getId()
    with(binding) {
        tvName.text = name
        tvId.text = id
    }
    with(ServiceCreator.githubService.getProfile(id)) {
        enqueueUtil(
            onSuccess = {
                Glide.with(this@ProfileFragment).load(it.avatar_url).into(binding.ivProfile)
                binding.tvIntroduction.text = it.bio
            }
        )
    }
}
```

- ProfileFollowerFragment에서 follower를 불러오는 github 서버통신 후 어댑터 초기화
```kotlin
// ProfileFollowerFragment.kt
private fun initAdapter() {
    followerAdapter = FollowerAdapter()
    val id = MyApplication.prefs.getId()
    ServiceCreator.githubService.getFollowers(id).apply {
        enqueueUtil(
            onSuccess = {
                followerAdapter.setItems(it)
            }
        )
    }
    binding.rvFollowerList.adapter = followerAdapter
}
```

- Response 공통된 부분 BaseResponse로 묶어주기
```kotlin
// BaseResponse.kt
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)
```

```kotlin
// SoptService.kt
interface SoptService {
    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<BaseResponse<ResponseSignUp>>

    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignIn
    ): Call<BaseResponse<ResponseSignIn>>
}
```