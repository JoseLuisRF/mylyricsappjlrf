package com.example.joseramos.lyricsappjlrf.presentation.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.FragmentTopSongsBinding
import com.example.joseramos.lyricsappjlrf.presentation.adapters.TracksAdapter
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment
import com.example.joseramos.lyricsappjlrf.presentation.models.TrackUIModel
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener
import com.example.joseramos.lyricsappjlrf.presentation.viewmodel.TopSongsViewModel
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView
import javax.inject.Inject


class TopSongsFragment : BaseFragment(), TopSongsView, OnBackPressListener {

    lateinit var viewModel: TopSongsViewModel
    lateinit var binding: FragmentTopSongsBinding
    private var adapter: TracksAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val trackModelTracksAdapterListener = TracksAdapter.TracksAdapterListener<TrackUIModel> { view, position, (trackId) -> navigator.navigateToSecondLevelActivity(trackId) }

    override fun onAttach(context: Context?) {
        //TODO: Implement [AndroidSupportInjection] to avoid injection in the class
//        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_top_songs, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //FIXME: This Fragment should be injected by [AndroidSupportInjection]
        userComponent.inject(this)

        binding.rvTopSongs.layoutManager =  LinearLayoutManager(activity)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopSongsViewModel::class.java)

        viewModel.loadTopSongs().observe(this, Observer { response ->
            adapter = TracksAdapter(response?.topSongs)
            binding.rvTopSongs.adapter = adapter
            adapter!!.setTrackAdapterListener(trackModelTracksAdapterListener)
         })
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onSuccessSongsLoaded(trackModels: List<TrackUIModel>) {
        adapter = TracksAdapter(trackModels)
        binding.rvTopSongs.adapter = adapter
        adapter!!.setTrackAdapterListener(trackModelTracksAdapterListener)

    }

    override fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        Snackbar.make(binding.root, throwable.message as CharSequence, Snackbar.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        activity.finish()
    }

    companion object {

        fun newInstance(): TopSongsFragment {
            return TopSongsFragment()
        }
    }
}
