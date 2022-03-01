package com.yeyint.datamanagement.presentation.createAccountView

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.yeyint.datamanagement.BaseApplication
import com.yeyint.datamanagement.databinding.ActivityCreateNewAccountBinding
import com.yeyint.datamanagement.presentation.BaseActivity

class CreateAccountActivity : BaseActivity() {
    private lateinit var binding : ActivityCreateNewAccountBinding
    val viewModel : CreateAccountViewModel by viewModels()

    companion object {
        fun getIntent() : Intent {
            return Intent(BaseApplication.getInstance(), CreateAccountActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar(binding.toolbar,true)
        supportActionBar?.title = ""

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.crateActivity = this
    }

    fun onFormSubmit(){
        if(viewModel.isFormValid()){
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }
    }
}