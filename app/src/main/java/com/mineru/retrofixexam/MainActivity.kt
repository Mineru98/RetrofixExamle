package com.mineru.retrofixexam

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*


@Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val _retrofit: RetrofitService = retrofit.service

        btnSignin.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_pw.text.toString()
            val uuid = UUID.randomUUID().toString()
            val apiKey = this.resources.getString(R.string.authorization_key)

            val body = HashMap<String, Any>()
            body.put("Email", email)
            body.put("Password", password)
            body.put("uuid", uuid)
            Log.i("Mineru:", apiKey)

            _retrofit.signin(apiKey, body)
                .enqueue(object : Callback<UserData> {
                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        Log.i("SingInAPI::", "Failed API call with call: " + call +
                                " + exception: " + t)
                    }

                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        Log.i("Response:: ", response.body().toString())
                    }
                })
        }

        btnSignup.setOnClickListener {
            val name = "게스트".toString()
            val email = et_email.text.toString()
            val password = et_pw.text.toString()
            val uuid = UUID.randomUUID().toString()
            val apiKey = this.resources.getString(R.string.authorization_key)

            val body = HashMap<String, Any>()
            body.put("Name", name)
            body.put("Email", email)
            body.put("Password", password)
            body.put("uuid", uuid)
            Log.i("Mineru:", apiKey)

            _retrofit.signup(apiKey, body).enqueue(object: Callback<UserData> {
                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        Log.i("SingUpAPI::", "Failed API call with call: " + call +
                                " + exception: " + t)
                    }

                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        Log.i("Response:: ", response.body().toString())
                    }
                })
        }
    }

    object retrofit {
        val service : RetrofitService by lazy {
            // 디버깅 코드
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            val client = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build()

            //Retrofit 객체 생성
            val BASE_URL = "http://your.server.com"
            val retrofit = Retrofit.Builder()
//                .client(client) // 디버깅 코드
                .client(OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            //retrofit 객체를 통해 인터페이스 생성
            return@lazy retrofit.create(RetrofitService::class.java)
        }
    }
}
