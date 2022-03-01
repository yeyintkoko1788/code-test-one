package com.yeyint.datamanagement.presentation.welcomeView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.yeyint.datamanagement.BaseApplication
import com.yeyint.datamanagement.R
import com.yeyint.datamanagement.databinding.ActivityMainBinding
import com.yeyint.datamanagement.presentation.BaseActivity
import com.yeyint.datamanagement.presentation.createAccountView.CreateAccountActivity

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.apply {
            statusBarColor = ContextCompat.getColor(
                BaseApplication.getInstance(),
                R.color.color_transparent
            )
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.mainActivity = this
    }

    fun onTapCreateAccount(){
        startActivity(CreateAccountActivity.getIntent())
    }
}