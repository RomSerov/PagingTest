package com.example.pagingtest

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pagingtest.adapter.RickMortyPagedAdapter
import com.example.pagingtest.databinding.ActivityMainBinding
import com.example.pagingtest.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rmAdapter: RickMortyPagedAdapter
    private val viewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                rmAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        rmAdapter = RickMortyPagedAdapter()
        binding.recyclerView.apply {
            adapter = rmAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
    }
}