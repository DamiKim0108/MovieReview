package lilcode.aop.p3.c04.bookreview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lilcode.aop.p3.c04.bookreview.databinding.ItemBookBinding
import lilcode.aop.p3.c04.bookreview.model.Book
import lilcode.aop.p3.c04.bookreview.model.Movie

class MovieAdapter(private val itemClickedListener: (Movie)->Unit) : ListAdapter<Movie, MovieAdapter.BookItemViewHolder>(diffUtil) {

    // 뷰 바인딩 (item_movie.xml)
    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(bookModel: Movie){
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description

            binding.root.setOnClickListener {
                itemClickedListener(bookModel)
            }

            // Glide 사용 하기
            Glide
                .with(binding.coverImageView.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.coverImageView)
        }
    }

    // 미리 만들어진 뷰 홀더가 없을 경우 생성하는 함수.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 실제 뷰 홀더가 뷰에 그려지게 됬을 때 데이터를 바인드하게 되는 함수.
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        // 같은 값이 있으면 할당 해줄 필요 없다
        val diffUtil = object: DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}