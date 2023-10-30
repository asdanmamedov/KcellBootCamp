package kz.kcell.kcellbootcamp.presentation.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.kcell.kcellbootcamp.BuildConfig
import kz.kcell.kcellbootcamp.data.entities.Movie
import kz.kcell.kcellbootcamp.databinding.MovieItemBinding
import kz.kcell.kcellbootcamp.utils.loadImage
import kz.kcell.kcellbootcamp.utils.orZero

class MoviesAdapter(
    private val onClick: (item: Movie) -> Unit
) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MovieViewHolder(
        onClick,
        MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(
        private val onClick: (item: Movie) -> Unit,
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) = with(binding) {
            root.setOnClickListener {
                onClick.invoke(item)
            }
            movieItemTitle.text = item.title
            movieItemRelease.text = formatDate(item.releaseDate)
            movieItemRatingbar.rating = item.voteAverage.toFloat().orZero()
            movieItemPoster.loadImage(
                BuildConfig.IMAGE_URL + item.posterPath,
                progressBar
            )
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

}

fun getMonthNameFromDigit(monthDigit: String): String {
    return when (monthDigit.toIntOrNull()) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "Aug"
        9 -> "Sep"
        10 -> "Oct"
        11 -> "Nov"
        12 -> "Dec"
        else -> "Invalid Month"
    }
}

fun formatDate(dateString: String): String {
    val day = dateString.slice(8..9)
    val month = getMonthNameFromDigit(dateString.slice(5..6))
    val year = dateString.slice(0..3)

    return "$day $month $year"
}
