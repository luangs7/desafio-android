package com.picpay.desafio.userlist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.picpay.desafio.userlist.databinding.ActivityMainBinding
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.presentation.adapter.UserListAdapter
import com.picpay.desafio.common.base.ViewState
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.userListResponse.observe(this, {
            handleResponse(it)
        })
    }

    private fun handleResponse(response: ViewState<List<User>>) {
        binding.userListProgressBar.isVisible = response.isLoading
        binding.cvEmptyView.isVisible = response.isEmpty
        binding.cvErrorView.isVisible = response.error != null
        adapter.users = response.result ?: listOf()
    }
}
