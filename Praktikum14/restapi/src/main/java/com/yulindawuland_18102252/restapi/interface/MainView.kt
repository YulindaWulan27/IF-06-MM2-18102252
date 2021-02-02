package com.yulindawuland_18102252.restapi.`interface`

import com.yulindawuland_18102252.restapi.model.Login
import com.yulindawuland_18102252.restapi.model.Quote

interface MainView {
    fun showMessage(messsage : String)
    fun resultQuote(data: ArrayList<Quote>)
    fun resultLogin(data: Login)
}