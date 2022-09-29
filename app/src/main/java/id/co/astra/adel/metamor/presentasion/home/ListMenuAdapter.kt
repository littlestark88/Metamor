package id.co.astra.adel.metamor.presentasion.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.co.astra.adel.metamor.R
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_menu, parent, false))

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMenuBinding.bind(itemView)
        fun bind(
            data: AddItem
        ) {
            with(binding) {
                tvName.text = data.nameItem
                tvPricing.text = convertCurrency(data.priceItem)
                imgPoster.load(data.imageItem)
                itemView.setOnClickListener { onClickListener(data) }
            }
        }

    }

}