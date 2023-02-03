package com.ivanvegagonzalez.campeoanto.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.FragmentMainBinding
import com.ivanvegagonzalez.campeoanto.ui.detail.DetailFragment
import com.ivanvegagonzalez.campeoanto.ui.detail.DetailFragmentPiloto

class PilotoFragment : Fragment(R.layout.fragment_piloto) {
    private val viewModel: MainViewModelPiloto by viewModels()

    private lateinit var binding: FragmentMainBinding
    private val adapter = PilotosAdapter(){ piloto -> viewModel.navigateTo(piloto)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) View.VISIBLE else View.GONE

            state.pilotos?.let {
                adapter.pilotos = state.pilotos
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    //R.id.action_mainFragment_to_detailFragment,
                    R.id.action_pilotoFragment_to_detailFragmentPiloto,
                    bundleOf(DetailFragmentPiloto.EXTRA_PILOTO to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                      //  R.id.action_mainFragment_to_createEscuderiaFragment,
                        R.id.action_pilotoFragment_to_createPilotoFragment,
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
                    findNavController().navigate(
                        // R.id.action_mainFragment_to_pilotoFragment,
                            R.id.action_pilotoFragment_to_mainFragment,
                    )
                    true
                }
                R.id.listaPiloto ->{
                    Log.d("PILOTO","Lista piloto")
                    true
                }
                else->false
            }
        }
    }
}
