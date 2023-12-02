package com.saitawngpha.koinretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.saitawngpha.koinretrofit.R
import com.saitawngpha.koinretrofit.adapter.PhotoAdapter
import com.saitawngpha.koinretrofit.databinding.ActivityMainBinding
import com.saitawngpha.koinretrofit.utils.DataStatus
import com.saitawngpha.koinretrofit.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel : MainViewModel by inject()
    private val photoAdapter by lazy { PhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.getPhoto("beach with blue sky")
            viewModel.photoList.observe(this@MainActivity, Observer {
                when(it.status) {
                    DataStatus.Status.LOADING -> {
                        showProgressBar(isShown = false)
                    }
                    DataStatus.Status.SUCCESS -> {
                        showProgressBar(isShown = true)
                        photoAdapter.differ.submitList(it.data)
                    }
                    DataStatus.Status.ERROR -> {
                        showProgressBar(isShown = false)
                        Toast.makeText(this@MainActivity, "This is something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun setupRecyclerView() {
        binding!!.rvPhoto.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = photoAdapter
        }
    }

    private fun showProgressBar(isShown: Boolean) {
        binding!!.apply {
            if (isShown){
                rvPhoto.visibility = View.VISIBLE
                pBarLoading.visibility = View.GONE
            }else{
                rvPhoto.visibility = View.GONE
                pBarLoading.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}