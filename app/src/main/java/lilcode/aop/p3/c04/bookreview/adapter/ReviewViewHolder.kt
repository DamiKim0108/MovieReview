package lilcode.aop.p3.c04.bookreview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ReviewViewHolder(v: View):RecyclerView.ViewHolder(v) {

    var view : View = v

    fun updateView(item:String){
        view.textView.text = item
    }
}