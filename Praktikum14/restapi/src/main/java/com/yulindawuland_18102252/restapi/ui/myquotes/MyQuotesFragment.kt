package com.yulindawuland_18102252.restapi.ui.myquotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yulindawuland_18102252.restapi.QuoteAdapter
import com.yulindawuland_18102252.restapi.R
import com.yulindawuland_18102252.restapi.TokenPref
import com.yulindawuland_18102252.restapi.`interface`.MainView
import com.yulindawuland_18102252.restapi.api.MainPresenter
import com.yulindawuland_18102252.restapi.model.Quote
import com.yulindawuland_18102252.restapi.model.Token
import com.yulindawuland_18102252.restapi.ui.CoroutineContextProvider

class MyQuotesFragment : Fragment() {

    class MyQuotesFragment : Fragment(), MainView {
        private lateinit var presenter: MainPresenter
        private var quotes: MutableList<Quote> = mutableListOf()
        private lateinit var adapter: QuoteAdapter
        private lateinit var tokenPref: TokenPref
        private lateinit var token: Token
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
            Bundle?
        ): View? = inflater.inflate(R.layout.fragment_my_quotes, container, false)
    }
}
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentMyQuotesBinding.bind(view)
    binding.recyclerviewMyQuotes.layoutManager = LinearLayoutManager(activity)
    tokenPref = TokenPref(requireActivity())
    token = tokenPref.getToken()
    adapter = QuoteAdapter(requireActivity())
    binding.recyclerviewMyQuotes.adapter = adapter
    presenter =
        MainPresenter(this, CoroutineContextProvider())
    progressbar.visibility = View.VISIBLE
    presenter.getMyQuotes(token.token)
    swiperefresh.onRefresh {
        progressbar.visibility = View.INVISIBLE
        presenter.getMyQuotes(token.token)
    }
}
override fun onResume() {
    super.onResume()
    presenter.getMyQuotes(token.token)
}
override fun showMessage(messsage: String) {
    Toast.makeText(requireActivity(),messsage, Toast.LENGTH_SHORT).show()
}
override fun resultQuote(data: ArrayList<Quote>) {
    quotes.clear()
    adapter.listQuotes = data
    quotes.addAll(data)
    adapter.notifyDataSetChanged()
    progressbar.visibility = View.INVISIBLE
    swiperefresh.isRefreshing = false
}
override fun resultLogin(data: Login) {
}