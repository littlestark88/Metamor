package id.co.astra.adel.metamor.presentasion.saveorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ItemListSaveOrderBinding
import id.co.astra.adel.metamor.domain.saveorder.model.SaveOrder
import id.co.astra.adel.metamor.utils.convertCurrency

class SaveOrderAdapter(
    private val context: Context,
    private val data: MutableList<SaveOrder>,
    private val onClickListener: (SaveOrder) -> Unit
): RecyclerView.Adapter<SaveOrderAdapter.SaveOrderViewHolder>() {

    fun setListSaveOrder(saveOrderList: List<SaveOrder>) {
        data.clear()
        data.addAll(saveOrderList)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SaveOrderViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_save_order, parent, false)
        )

    override fun onBindViewHolder(holder: SaveOrderViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    inner class SaveOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSaveOrderBinding.bind(itemView)
        fun bind(
            data: SaveOrder,
            onClickListener: (SaveOrder) -> Unit
        ) {
            with(binding) {
                tvNameValue.text = data.nameCustomer
                tvPhoneNumberValue.text = data.numberPhone.toString()
            }
        }
    }
}