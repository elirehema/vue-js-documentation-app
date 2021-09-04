package com.eli.nationalid.adapters

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.eli.nationalid.R
import com.eli.nationalid.databinding.DataItemBinding
import com.eli.nationalid.util.GlideApp
import com.eli.nationalid.BR;
import com.eli.nationalid.room.entities.DataListEntity
import java.util.*

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 4/25/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 */
class RecyclerAdapter(
        private val context: Context,
        private val data: List<DataListEntity>?

) : RecyclerView.Adapter<RecyclerAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: DataItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                R.layout.data_item,
                        parent,
                false);

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = data?.get(position)
        if (data != null) {
            holder.bind(data)
        }

        GlideApp.with(context)
                .load(Uri.parse(data?.dIcon))
                .apply(RequestOptions().circleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.vue_logo)
                .placeholder(R.drawable.vue_logo)

                .into(holder.binding.dataImage)

    }

    override fun getItemCount(): Int {
            return data!!.size

    }

    inner class ListViewHolder(val binding: DataItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, OnLongClickListener {
        fun bind(data: DataListEntity) {
            binding.setVariable(BR.dataItems, data)
            binding.executePendingBindings()
        }

        override fun onClick(v: View) {
            startCustomTabIntent(data?.get(adapterPosition)?.dLink.toString())
        }
        override fun onLongClick(v: View): Boolean {
            return false
        }

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v: View -> onClick(v) }
        }
    }


    private fun startCustomTabIntent(link: String) {
        val uri = String.format(Locale.ENGLISH, link)
        val PACKAGE_NAME = "com.android.chrome"
        val builder = CustomTabsIntent.Builder()
        //builder.setToolbarColor(context.resources.getColor(R.color.colorPrimary,null))
        builder.setShowTitle(true)
        val customTabsIntent = builder.build()
        val resolveInfoList = context.packageManager.queryIntentActivities(customTabsIntent.intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resolveInfoList) {
            val packageName = resolveInfo.activityInfo.packageName

            if (TextUtils.equals(packageName, PACKAGE_NAME)) {
                customTabsIntent.intent.setPackage(PACKAGE_NAME)
            }
        }
        customTabsIntent.launchUrl(context, Uri.parse(uri))
    }

}