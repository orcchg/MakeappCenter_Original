package com.orcchg.makeappcenter.app.common.widget.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orcchg.makeappcenter.app.R

class ImageHolder(private val context: Context) {

    @BindView(R.id.fl_widget_image) lateinit var rootView: ViewGroup
    @BindView(R.id.iv_cover) lateinit var cover: ImageView
    @BindView(R.id.iv_placeholder) lateinit var coverPlaceholder: ImageView
    @BindView(R.id.progressbar) lateinit var progressBar: View

    fun init(rootView: View) {
        ButterKnife.bind(this, rootView)
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun setCover(url: String) {
        progressBar.visibility = View.VISIBLE
        Glide.with(context).load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.INVISIBLE
                        cover.visibility = View.VISIBLE
                        coverPlaceholder.visibility = View.INVISIBLE
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.INVISIBLE
                        cover.visibility = View.INVISIBLE
                        coverPlaceholder.visibility = View.VISIBLE
                        return true
                    }
                })
                .into(cover)
    }

    /* Listener */
    // --------------------------------------------------------------------------------------------
    fun setOnClickListener(l: View.OnClickListener) {
        rootView.setOnClickListener(l)
    }
}
