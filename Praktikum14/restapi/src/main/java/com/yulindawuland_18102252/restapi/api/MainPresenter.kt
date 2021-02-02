package com.yulindawuland_18102252.restapi.api

import android.telecom.Call
import com.yulindawuland_18102252.restapi.`interface`.MainView
import com.yulindawuland_18102252.restapi.model.Login
import com.yulindawuland_18102252.restapi.model.Message
import com.yulindawuland_18102252.restapi.model.QuoteResponse
import com.yulindawuland_18102252.restapi.ui.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainPresenter(private val view: MainView, private val context:
CoroutineContextProvider = CoroutineContextProvider()) {
    fun getMyQuotes(token:String?) {
        GlobalScope.launch (context.main){
            try {
                ApiMain().services.getMyQuotes("Bearer "+token).enqueue(object :
                    Callback<QuoteResponse> {
                    override fun onResponse(call: Call<QuoteResponse>, response:
                    Response<QuoteResponse>
                    ) {
                        if(response.code() == 200) {
                            response.body()?.quotes?.let {
                                view.resultQuote(it)
                            }
                        }
                    }
                    override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                        view.showMessage("Koneksi Terputus")
                    }
                })
            }
            catch (e:Exception){
            }
        }
    }
}

fun getClassQuotes(token:String?) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.getClassQuotes("Bearer "+token).enqueue(object :
                Callback<QuoteResponse> {
                override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                    if(response.code() == 200) {
                        response.body()?.quotes?.let {
                            view.resultQuote(it)
                        }
                    }
                }
                override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}
fun getAllQuotes(token: String) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.getAllQuotes("Bearer "+token).enqueue(object :
                Callback<QuoteResponse> {
                override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                    if(response.code() == 200) {
                        response.body()?.quotes?.let {
                            view.resultQuote(it)
                        }
                    }
                }
                override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}
fun addQuote(token:String, title:String, description: String) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.addQuote("Bearer "+token,title,description).enqueue(object :
                Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    if(response.code() == 200) {
                        response.body()?.message?.let {
                            view.showMessage(it)
                        }
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}
fun updateQuote(token:String, quote_id:String, title:String, description: String) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.updateQuote("Bearer "+token,quote_id,title,description).enqueue(object :
                Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    if(response.code() == 200) {
                        response.body()?.message?.let {
                            view.showMessage(it)
                        }
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}
fun deleteQuote(token: String, quote_id: String) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.deleteQuote("Bearer "+token,quote_id).enqueue(object :
                Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    if(response.code() == 200) {
                        response.body()?.message?.let {
                            view.showMessage(it)
                        }
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}
fun login(nim:String, password:String) {
    GlobalScope.launch (context.main){
        try {
            ApiMain().services.login(nim,password).enqueue(object :
                Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if(response.code()==200){
                        response.body()?.let { it1 -> view.resultLogin(it1) }
                        view.showMessage("Login Berhasil")
                    }
                    else{
                        view.showMessage("Login Gagal")
                    }

                }
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    view.showMessage("Koneksi Terputus")
                }
            })
        }
        catch (e:Exception){
        }
    }
}

}