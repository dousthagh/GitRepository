package dousthagh.software.git.ui.fragments.search_result

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dousthagh.software.git.R
import dousthagh.software.git.data.model.search.result.RepositoryItem
import dousthagh.software.git.databinding.ItemRepositoryResultBinding
import dousthagh.software.git.ui.fragments.search_result.adapter.IRepositoryItemListener

class SearchResultAdapter() :
    RecyclerView.Adapter<MyViewHolder>() {


    private val items = ArrayList<RepositoryItem>()
    private lateinit var _listener: IRepositoryItemListener
    fun setItems(items: ArrayList<RepositoryItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemListener(listener: IRepositoryItemListener){
        _listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemRepositoryResultBinding =
            ItemRepositoryResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, _listener, parent.context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(items[position])
}

class MyViewHolder(
    private val itemBinding: ItemRepositoryResultBinding,
    private val listener: IRepositoryItemListener,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var item: RepositoryItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: RepositoryItem) {
        this.item = item
        if (layoutPosition % 2 == 0) {
            itemBinding.cvContainer.setCardBackgroundColor(context.resources.getColor(R.color.light_gray))
        }else{
            itemBinding.cvContainer.setCardBackgroundColor(context.resources.getColor(R.color.white))
        }
        itemBinding.tvRepositoryName.text = item.name
        itemBinding.tvRepositoryScore.text = item.stargazers_count.toString()
        itemBinding.tvAuthorFullName.text = item.owner.login

        Glide.with(itemBinding.root)
            .load(item.owner.avatar_url)
            .transform(CircleCrop())
            .placeholder(R.drawable.ic_baseline_person_gray)
            .into(itemBinding.imgAvatar)
    }

    override fun onClick(v: View?) {
        listener.onClicked(item.id)
    }
}

