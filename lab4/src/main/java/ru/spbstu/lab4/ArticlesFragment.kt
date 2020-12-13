package ru.spbstu.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticlesFragment : Fragment() {

    private lateinit var rvArticles: RecyclerView

    private val database by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().application as ArticlesApp).database
    }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        InfiniteArticlesAdapter(database)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.frg_articles, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvArticles = view.findViewById(R.id.rv_articles)
        rvArticles.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = this@ArticlesFragment.adapter
        }
    }
}
