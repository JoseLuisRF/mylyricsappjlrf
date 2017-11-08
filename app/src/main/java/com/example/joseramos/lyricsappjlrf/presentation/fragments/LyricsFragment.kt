package com.example.joseramos.lyricsappjlrf.presentation.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.FragmentLyricsScreenBinding
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener
import com.example.joseramos.lyricsappjlrf.presentation.presenters.LyricsPresenter
import com.example.joseramos.lyricsappjlrf.presentation.views.LyricsView
import javax.inject.Inject

const val BUNDLE_TRACK_ID = "track_id"

class LyricsFragment : LyricsView,  OnBackPressListener , BaseFragment() {

    private var binding: FragmentLyricsScreenBinding? = null

    @Inject
    lateinit var presenter: LyricsPresenter

    companion object {

        @JvmStatic
        fun newInstance(trackId: Int) : Fragment {
            val fragment = LyricsFragment()
            val arguments = Bundle()
            arguments.putInt(BUNDLE_TRACK_ID, trackId)
            fragment.arguments =  arguments
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentLyricsScreenBinding>(inflater, R.layout.fragment_lyrics_screen, container, false)
        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent?.inject(this)
        presenter.setView(this)
        if (arguments.getInt(BUNDLE_TRACK_ID, 0) != 0 ){
            presenter.loadLyrics(arguments.getInt(BUNDLE_TRACK_ID))
        }
    }
    override fun onBackPressed() {
        navigator.navigateToMainActivity()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(throwable: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadLyricsSuccessful(model: LyricsModel) {
        binding?.model = model
        Log.d("JLRF", model.lyricsBody)
    }
}