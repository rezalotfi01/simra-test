package simra.androidtest.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import simra.androidtest.home.R
import simra.androidtest.home.databinding.ItemMovieBinding
import simra.androidtest.home.viewmodel.HomeViewModel
import simra.androidtest.model.Movie

class MovieAdapter(private val viewModel: HomeViewModel): RecyclerView.Adapter<MovieAdapter.HomeViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount(): Int
            = movies.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int)
            = holder.bindTo(movies[position], viewModel)

    class HomeViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

        private val binding = ItemMovieBinding.bind(parent)

        fun bindTo(movie: Movie, viewModel: HomeViewModel) {
            binding.movie = movie
            binding.viewmodel = viewModel
        }
    }

    fun updateData(items: List<Movie>) {
        val diffCallback = MovieItemDiffCallBack(movies, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movies.clear()
        movies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}