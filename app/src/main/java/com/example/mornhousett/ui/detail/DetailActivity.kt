package com.example.mornhousett.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.mornhousett.R
import com.example.mornhousett.databinding.ActivityDetailBinding
import com.example.mornhousett.other.collectLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

const val EXTRA_FACT_ID = "EXTRA_FACT_ID"

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getFactById(intent.getIntExtra(EXTRA_FACT_ID, 0))

        collectLifecycleFlow(viewModel.fact) {
            it?.let {
                binding.tvNumber.text = it.number.toString()
                binding.tvText.text = it.text
            }
        }
    }
}