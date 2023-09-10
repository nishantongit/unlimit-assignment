package com.example.unlimitassignment.jokes.presentation.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.unlimitassignment.databinding.FragmentJokeListBinding
import com.example.unlimitassignment.jokes.presentation.di.JokesComponent
import com.example.unlimitassignment.jokes.presentation.ui.adapter.JokesListAdapter
import com.example.unlimitassignment.jokes.presentation.ui.viewmodel.JokesViewModel
import com.example.unlimitassignment.jokes.presentation.ui.viewmodel.factory.JokesViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JokeListFragment : Fragment() {

    private var binding: FragmentJokeListBinding? = null

    private lateinit var viewModel: JokesViewModel

    @Inject
    lateinit var viewModelFactory: JokesViewModelFactory

    private var adapter = JokesListAdapter()


    companion object {
        fun newInstance() = JokeListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        JokesComponent.Initializer.init(requireContext()).inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[JokesViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeListBinding.inflate(inflater, container, false)
        binding?.vm = viewModel
        binding?.jokesRv?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onLoad()

        viewModel.jokeLive.observe(viewLifecycleOwner) {
            adapter.swapData(it)
            binding?.jokesRv?.post { binding?.jokesRv?.smoothScrollToPosition(0) }
        }

        startJobs()
    }

    private fun startJobs(){



        val job = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.loadFromLocal()
                }

                launch {
                    try {
                        viewModel.startRepeatingJob(10000)
                    }catch (e:java.lang.Exception){
                        Log.e("stack", e.stackTraceToString())
                        withContext(Dispatchers.Main){
                            // to handle this create retrofit call adapter and generic request response
                            Toast.makeText(activity, "Check internet", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onResume() {
        super.onResume()
        isNetworkAvailable()
    }


    private fun isNetworkAvailable(): Boolean {
        val cm: ConnectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cap: NetworkCapabilities =
                cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks: Array<Network> = cm.allNetworks
            for (n in networks) {
                val nInfo: NetworkInfo? = cm.getNetworkInfo(n)
                if (nInfo != null && nInfo.isConnected) return true
            }
        } else {
            val networks: Array<NetworkInfo> = cm.allNetworkInfo
            for (nInfo in networks) {
                if (nInfo != null && nInfo.isConnected) return true
            }
        }
        return false
    }
}