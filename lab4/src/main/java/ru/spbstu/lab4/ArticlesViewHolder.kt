package ru.spbstu.lab4

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import name.ank.lab4.BibEntry
import name.ank.lab4.Keys
import name.ank.lab4.Types

open class BaseViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView
    private val tvAuthor: TextView
    private val tvJournal: TextView
    private val tvUrl: TextView
    private val tvYear: TextView

    init {
        with(itemView) {
            tvTitle = findViewById(R.id.tv_title)
            tvAuthor = findViewById(R.id.tv_author)
            tvJournal = findViewById(R.id.tv_journal)
            tvUrl = findViewById(R.id.tv_url)
            tvYear = findViewById(R.id.tv_year)
            setBackgroundColor(ContextCompat.getColor(itemView.context, pickColor(viewType)))
        }
    }

    open fun bind(item: BibEntry) {
        tvTitle.setTextOrHide(item.getField(Keys.TITLE))
        tvAuthor.setTextOrHide(item.getField(Keys.AUTHOR))
        tvJournal.setTextOrHide(item.getField(Keys.JOURNAL))
        tvUrl.setTextOrHide(item.getField(Keys.URL))
        tvYear.setTextOrHide(item.getField(Keys.YEAR))
    }

    @ColorRes
    private fun pickColor(type: Int): Int = when (Types.values()[type]) {
        Types.ARTICLE -> android.R.color.holo_blue_bright
        Types.BOOK -> android.R.color.holo_blue_dark
        Types.BOOKLET -> android.R.color.holo_purple
        Types.CONFERENCE -> android.R.color.holo_orange_light
        Types.INBOOK -> android.R.color.holo_orange_dark
        Types.INCOLLECTION -> android.R.color.holo_red_light
        Types.INPROCEEDINGS -> android.R.color.holo_red_dark
        Types.MANUAL -> android.R.color.holo_green_light
        Types.MASTERSTHESIS -> android.R.color.holo_green_dark
        Types.MISC -> android.R.color.darker_gray
        Types.PHDTHESIS -> R.color.colorAccent
        Types.PROCEEDINGS -> R.color.colorPrimary
        Types.TECHREPORT -> R.color.colorPrimaryDark
        Types.UNPUBLISHED -> android.R.color.black
    }
}

class InproceedingsViewHolder(itemView: View, viewType: Int) : BaseViewHolder(itemView, viewType) {

    private val tvAddress: TextView = itemView.findViewById(R.id.tv_address)

    override fun bind(item: BibEntry) {
        super.bind(item)
        tvAddress.setTextOrHide(item.getField(Keys.ADDRESS))
    }
}

class MiscViewHolder(itemView: View, viewType: Int) : BaseViewHolder(itemView, viewType) {

    private val tvBookTitle: TextView = itemView.findViewById(R.id.tv_book_title)

    override fun bind(item: BibEntry) {
        super.bind(item)
        tvBookTitle.setTextOrHide(item.getField(Keys.BOOKTITLE))
    }
}

fun TextView.setTextOrHide(text: String?) {
    if (text.isNullOrEmpty()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        setText(text)
    }
}