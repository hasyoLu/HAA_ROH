package com.example.haa_roh.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.R
import com.example.haa_roh.bean.CountChange
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


/**
匹配是否是手机号
 */
fun isValidPhoneNumber(phoneNumber: String?): Boolean {
    return if (phoneNumber != null && phoneNumber.isNotEmpty()) {
        Pattern.matches("^1[3-9]\\d{9}$", phoneNumber)
    } else false
}
/**
 * 匹配是否是手机验证码【6位数字】
 */
fun isValidPassword(password : String?) : Boolean{
    return if(password != null && password.isNotEmpty()){
        Pattern.matches("\\d{6}",password)
    }else false
}

/**
 * 验证码：一分钟倒计时
 * 使用Rxjava实现
 */
fun oneMinuteCountdown(loginCountNumber : MutableLiveData<CountChange?>) {
    val count = 60
    Observable.interval(0,1, TimeUnit.SECONDS)
        .take(60)
        .map {
            CountChange("${count-it}秒后从新发送", R.color.authCodeAfter,false)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<CountChange> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: CountChange) {
                loginCountNumber.value = t
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                val countChange = CountChange("重新获取验证码", R.color.authCode,true)
                loginCountNumber.value = countChange
            }
        })
}

/**
 * Base64编码
 */
fun base64Encode(bitmap : Bitmap) : String{
    val baos = ByteArrayOutputStream()
    //读取图片到ByteArrayOutputStream
    //读取图片到ByteArrayOutputStream
    bitmap.compress(Bitmap.CompressFormat.PNG, 40, baos) //参数如果为100那么就不压缩

    val bytes : ByteArray = baos.toByteArray()
    val encode : String = Base64.encodeToString(bytes,Base64.DEFAULT)
    return encode
}

/**
 * Base64解码
 */
fun base64Decode(photoString : String) : Bitmap{
    val input = Base64.decode(photoString,Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(input,0,input.size)
}