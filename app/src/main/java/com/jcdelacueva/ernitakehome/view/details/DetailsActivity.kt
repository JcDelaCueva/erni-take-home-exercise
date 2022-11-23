package com.jcdelacueva.ernitakehome.view.details

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.jcdelacueva.ernitakehome.data.model.User
import com.jcdelacueva.ernitakehome.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(USER, User::class.java)
        } else {
            intent.getParcelableExtra(USER) as? User
        }

        setUpView()
        setObserver()
        user?.let {
            loadData(user)
        }
    }

    private fun setUpView() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setObserver() {
        viewModel.user.observe(this) { user ->
            showUserInfo(user)
        }
    }

    private fun showUserInfo(user: User) {
        binding.apply {
            tvName.text = user.name
            Glide.with(binding.root).load(user.imageUrl).into(imgAvatar)
        }
    }

    private fun loadData(user: User) {
        viewModel.setUserInfo(user)
    }

    companion object {

        private const val USER = "USER"

        fun start(context: Context, user: User) {
            context.startActivity(
                Intent(context, DetailsActivity::class.java).apply {
                    putExtra(USER, user)
                }
            )
        }
    }
}