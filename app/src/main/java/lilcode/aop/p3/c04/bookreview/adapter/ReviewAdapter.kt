package lilcode.aop.p3.c04.bookreview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lilcode.aop.p3.c04.bookreview.R
import lilcode.aop.p3.c04.bookreview.dao.ReviewDao
import lilcode.aop.p3.c04.bookreview.model.Movie
import lilcode.aop.p3.c04.bookreview.model.Review
import java.security.AccessControlContext

class ReviewAdapter(val listData:ArrayList<Review>):
RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reviewData = listData.get(postion)
        holder.setReview(reviewData)
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val txtName: TextView = itemView.findViewById(R.id.movieTitle)

        fun bind(item: Review){
            txtName.text = item.id.toString()
        }

        fun setReview(reviewData: Review){
            itemView.titleTextView.text = Review.id.toString()

        }
    }
}



