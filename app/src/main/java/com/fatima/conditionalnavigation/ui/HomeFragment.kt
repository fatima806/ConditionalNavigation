package com.fatima.conditionalnavigation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.fatima.conditionalnavigation.R
import com.fatima.conditionalnavigation.ui.viewmodel.HomeViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.lang.Integer.max


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.flexWrap = FlexWrap.WRAP
        recyclerView.layoutManager =layoutManager
        val adapter = ButtonsAdapter()
        adapter.dataSource = viewModel.getAndroidTopics()
        recyclerView.adapter = adapter

    }

    inner class ButtonsAdapter : RecyclerView.Adapter<ButtonsAdapter.ButtonViewHolder>() {
        var dataSource: List<String>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
            return ButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
            val buttonText = dataSource?.get(position) ?: ""
            holder.button.text = buttonText
            // Calculate the width of the button based on its content
            val textWidth = holder.button.paint.measureText(buttonText)
            val layoutParams = holder.itemView.layoutParams
            layoutParams.width = textWidth.toInt()
            holder.itemView.layoutParams = layoutParams
        }

        override fun getItemCount(): Int {
            return dataSource?.size ?: 0
        }

        inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var button: Button

            init {
                button = itemView.findViewById(R.id.button)
            }
        }
    }

    class DynamicWidthLayoutManager : RecyclerView.LayoutManager() {

        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
            return RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
            if (itemCount == 0) {
                removeAndRecycleAllViews(recycler!!)
                return
            }

            var xOffset = paddingLeft
            var yOffset = paddingTop
            var maxChildHeight = 0

            for (i in 0 until itemCount) {
                val view = recycler!!.getViewForPosition(i)
                addView(view)
                measureChildWithMargins(view, 0, 0)

                val width = getDecoratedMeasuredWidth(view)
                val height = getDecoratedMeasuredHeight(view)

                if (xOffset + width <= width - paddingRight) {
                    layoutDecorated(view, xOffset, yOffset, xOffset + width, yOffset + height)
                    xOffset += width
                    maxChildHeight = max(maxChildHeight, height)
                } else {
                    xOffset = paddingLeft
                    yOffset += maxChildHeight
                    layoutDecorated(view, xOffset, yOffset, xOffset + width, yOffset + height)
                    xOffset += width
                    maxChildHeight = height
                }
            }
        }
    }



}