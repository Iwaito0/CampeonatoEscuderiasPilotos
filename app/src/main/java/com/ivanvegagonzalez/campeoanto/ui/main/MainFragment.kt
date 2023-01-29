package com.ivanvegagonzalez.campeoanto.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentMainBinding
import com.ivanvegagonzalez.campeoanto.ui.detail.DetailFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding
    private val adapter = EscuderiasAdapter(){ escuderia -> viewModel.navigateTo(escuderia)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.escuderias?.let {
                adapter.escuderias = state.escuderias
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    bundleOf(DetailFragment.EXTRA_ESCUDERIA to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                          R.id.action_mainFragment_to_createEscuderiaFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.fab.setOnClickListener {
            viewModel.navigateToCreate()
        }

        binding.menuItemListas.setOnItemSelectedListener{
            when(it.itemId){
                R.id.listaEscuderia ->{
                    Log.d("ESCUDERIA","Lista escuderia")
                    true
                }
                R.id.listaPiloto ->{
                    Log.d("PILOTO","Lista piloto")
                    findNavController().navigate(
                        R.id.action_mainFragment_to_pilotoFragment,
                    )
                    true
                }
                else->false
            }
        }
    }
}
