package id.co.astra.adel.metamor.presentasion.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.astra.adel.metamor.databinding.ItemListMenuBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.utils.convertCurrency

class ListMenuAdapter(
    private val context: Context,
    private val data: MutableList<AddItem>,
    private val onClickListener: (AddItem) -> Unit
) : RecyclerView.Adapter<ListMenuAdapter.MenuViewHolder>() {


    fun setListMenu(addItemAddList: List<AddItem>) {
        data.clear()
        data.addAll(addItemAddList)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemListMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MenuViewHolder(private val binding: ItemListMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: AddItem
        ) {
            with(binding) {
                tvName.text = data.nameItem
                tvPricing.text = convertCurrency(data.priceItem)
                Glide
                    .with(context)
                    .load(data.imageItem)
                    .centerCrop()
                    .into(imgPoster)
                itemView.setOnClickListener { onClickListener(data) }
            }
        }

    }

}