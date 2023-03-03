package com.example.mornhousett.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mornhousett.R
import com.example.mornhousett.databinding.ActivityMainBinding
import com.example.mornhousett.other.collectLifecycleFlow
import com.example.mornhousett.ui.detail.DetailActivity
import com.example.mornhousett.ui.detail.EXTRA_FACT_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: HistoryRVAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        collectLifecycleFlow(viewModel.facts) {
            rvAdapter.differ.submitList(it)
        }

        binding.btnGetFact.setOnClickListener {
            if(binding.etNumber.text.isNotEmpty()) {
                viewModel.getFactByNumber(binding.etNumber.text.toString().toInt())
                binding.etNumber.setText("")
            }
        }

        binding.btnGetRandomFact.setOnClickListener {
            viewModel.getRandomFact()
        }
    }

    private fun setupRecyclerView() {
        rvAdapter = HistoryRVAdapter()
        binding.rvHistory.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        rvAdapter.setOnItemClickListener {
            Intent(this, DetailActivity::class.java).apply {
                putExtra(EXTRA_FACT_ID, it.id)
                startActivity(this)
            }
        }
    }
}