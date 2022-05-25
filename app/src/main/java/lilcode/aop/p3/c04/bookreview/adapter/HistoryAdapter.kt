package lilcode.aop.p3.c04.bookreview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lilcode.aop.p3.c04.bookreview.MainActivity
import lilcode.aop.p3.c04.bookreview.databinding.ItemHistoryBinding
import lilcode.aop.p3.c04.bookreview.model.History


class HistoryAdapter(val historyDeleteClickListener: (String) -> Unit, val mainActivity: MainActivity) :
    ListAdapter<History, HistoryAdapter.HistoryViewHolder>(diffUtil) {



    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(historyModel: History) {
            binding.historyKeywordTextView.text = historyModel.keyword
            binding.historyKeywordDeleteButton.setOnClickListener {
                historyDeleteClickListener(historyModel.keyword.orEmpty())
            }

            binding.root.setOnClickListener {
                mainActivity.bookServiceSearchBook(historyModel.keyword.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areContentsTheSame(oldItem: History, newItem: History) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: History, newItem: History) =
                oldItem.keyword == newItem.keyword
        }
    }

}

//class HistoryAdapter(val historyDeleteClickedListener: (String) -> Unit) : ListAdapter<History, HistoryAdapter.historyViewHolder>(diffUtil) {
//
//
//    inner class historyViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
//
//        fun bind(history: History){
//            binding.searchHistoryTextView.text = history.keyword
//            binding.historyKeywordDeleteButton.setOnClickListener {
//                historyDeleteClickedListener(history.keyword.orEmpty())
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.historyViewHolder {
//        return historyViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//    }
//
//    override fun onBindViewHolder(holder: HistoryAdapter.historyViewHolder, position: Int) {
//        holder.bind(currentList[position])
//    }
//
//    companion object{
//        // 같은 값이 있으면 할당 해줄 필요 없다
//        val diffUtil = object: DiffUtil.ItemCallback<History>(){
//            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
//                return oldItem.keyword == newItem.keyword
//            }
//
//        }
//    }
//
//
//}