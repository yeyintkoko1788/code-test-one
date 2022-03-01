package com.yeyint.datamanagement.presentation.createAccountView

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeyint.datamanagement.helper.LiveDataValidator
import com.yeyint.datamanagement.helper.LiveDataValidatorResolver
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CreateAccountViewModel : ViewModel() {

    val isFormValidMediator = MediatorLiveData<Boolean>()

    val firstNameLD : MutableLiveData<String> = MutableLiveData()
    val firstNameValidator = LiveDataValidator(firstNameLD).apply {
        addRule("First name require!") {it.isNullOrEmpty()}
    }
    val lastNameLD : MutableLiveData<String> = MutableLiveData()
    val lastNameValidator = LiveDataValidator(lastNameLD).apply {
        addRule("Last name require!") {it.isNullOrEmpty()}
    }
    val emailAddressLD : MutableLiveData<String> = MutableLiveData()
    val emailAddressValidator = LiveDataValidator(emailAddressLD).apply {
        addRule("Email address require!") {it.isNullOrEmpty()}
        addRule("Invalid email address!") {it.isValidEmail()}
    }
    val dobLD : MutableLiveData<String> = MutableLiveData()
    val dobValidator = LiveDataValidator(dobLD).apply {
        addRule("Date of Birth require!") {it.isNullOrEmpty()}
        addRule("Invalid date format") {!isValidDateFormat(it!!)}
    }
    val genderLD : MutableLiveData<GenderType> = MutableLiveData(GenderType.FEMALE)
    val nationalityLD : MutableLiveData<String> = MutableLiveData()
    val nationalityValidator = LiveDataValidator(nationalityLD).apply {
        addRule("Nationality require!") {it.isNullOrEmpty()}
    }
    val countryOfResidentLD : MutableLiveData<String> = MutableLiveData()
    val countryOfResidentValidator = LiveDataValidator(countryOfResidentLD).apply {
        addRule("Country of Resident require!") {it.isNullOrEmpty()}
    }
    val phoneNoLD : MutableLiveData<String> = MutableLiveData()
    val phoneValidator = LiveDataValidator(phoneNoLD).apply {
        addRule("Phone no require!") {it.isNullOrEmpty()}
        addRule("Invlaid Phone Number"){!isValidMyanmarPhoneNumber(it)}
    }

    fun CharSequence?.isValidEmail() = !Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun isValidMyanmarPhoneNumber(phoneNumber: String?): Boolean {

        val myanmarPhoneNumberRegex = Regex("^(09|\\+?950?9|\\+?95950?9)\\d{7,9}\$")

        return myanmarPhoneNumberRegex.matches(phoneNumber!!)
    }

    init {
        isFormValidMediator.value = false
        isFormValidMediator.addSource(firstNameLD) { validateForm() }
        isFormValidMediator.addSource(lastNameLD) { validateForm() }
        isFormValidMediator.addSource(emailAddressLD) { validateForm() }
        isFormValidMediator.addSource(dobLD) { validateForm() }
        isFormValidMediator.addSource(nationalityLD) { validateForm() }
        isFormValidMediator.addSource(countryOfResidentLD) { validateForm() }
        isFormValidMediator.addSource(phoneNoLD) { validateForm() }
    }

    fun validateForm(){
        val validators = listOf(firstNameValidator, lastNameValidator,emailAddressValidator,dobValidator,nationalityValidator,countryOfResidentValidator,phoneValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        isFormValidMediator.value = validatorResolver.isValid()
    }

    fun isFormValid() : Boolean{
        isFormValidMediator.value = false
        val validators = listOf(firstNameValidator, lastNameValidator,emailAddressValidator,dobValidator,nationalityValidator,countryOfResidentValidator,phoneValidator)
        val validatorResolver = LiveDataValidatorResolver(validators)
        isFormValidMediator.value = validatorResolver.isValid()

        return isFormValidMediator.value!!
    }

    @SuppressLint("SimpleDateFormat")
    fun isValidDateFormat(value: String): Boolean {
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            date = sdf.parse(value)
            if (value != sdf.format(date)) {
                date = null
            }
        } catch (ex: ParseException) {
            ex.printStackTrace()
        }
        return date != null
    }

    fun setGender(type : GenderType){
        genderLD.value = type
    }

    enum class GenderType{
        FEMALE,
        MALE
    }
}