package com.jcdelacueva.ernitakehome.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcdelacueva.ernitakehome.data.model.User
import com.jcdelacueva.ernitakehome.databinding.ActivityMainBinding
import com.jcdelacueva.ernitakehome.isNetworkAvailable
import com.jcdelacueva.ernitakehome.view.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setUpObservers()
        loadCheckNetworkThenLoadUsers()
    }

    private fun setUpView() {
        adapter = UserAdapter()
        adapter.onItemClickListener = this
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
    }

    private fun setUpObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                setProgress(state)

                when (state) {
                    is MainState.LoadUsers -> {
                        adapter.submitList(state.users)
                    }
                    is MainState.ShowError -> {
                        showMessage(state.error.message ?: "")
                    }
                    else -> {}
                }
            }
        }
    }

    private fun loadCheckNetworkThenLoadUsers() {
        if (isNetworkAvailable(this)) {
            viewModel.downloadUsers()
        } else {
            binding.tvNetworkMessage.visibility = View.VISIBLE
            showMessage("No Network Available :(")
        }
    }

    override fun onClick(user: User) {
        DetailsActivity.start(this, user)
    }

    private fun setProgress(state: MainState) {
        binding.apply {
            progress.visibility = if (state == MainState.ShowLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}