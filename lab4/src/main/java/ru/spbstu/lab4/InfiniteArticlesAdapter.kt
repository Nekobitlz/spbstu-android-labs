package ru.spbstu.lab4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import name.ank.lab4.BibDatabase
import name.ank.lab4.Types

class InfiniteArticlesAdapter(private val database: BibDatabase) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (Types.values()[viewType]) {
        Types.MISC -> MiscViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_misc, parent, false),
            viewType
        )
        Types.INPROCEEDINGS -> InproceedingsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_inproceeding, parent, false),
            viewType
        )
        else -> BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false),
            viewType
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = database.getEntry(getRealPosition(position))
        holder.bind(item)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun getItemViewType(position: Int): Int {
        val realPosition = getRealPosition(position)
        return database.getEntry(realPosition).type.ordinal
    }

    private fun getRealPosition(position: Int) = position % database.size()
}