package com.example.haa_roh.bean

import com.example.haa_roh.R
import com.example.haa_roh.bean.Bmob.Users
import com.example.haa_roh.bean.room.PersonalInformation

data class CountChange(var textCountNumber : String? = null,
                       var textColor : Int = (R.color.authCodeAfter),
                       var isEnable : Boolean = false )


data class LoginAutoCode(
    var error : String? = null,
    var success : Int? = null,
)


data class LoginResult(
    val success: Boolean = false,
    val error: String? = null
)

/**
 * author: haa-zzz
 * time: 2021-7-27
 * LiveData中用来判断登录状态的bean
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isUserNameValid: Boolean = false,
    val isPasswordValid : Boolean = false
)

data class CreateUserResult(
    val success: Boolean = false,
    val error: String? = null,
    val personalInformation : PersonalInformation? = null
)