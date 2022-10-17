package id.co.astra.adel.metamor.presentasion.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.databinding.ItemListOrderBinding
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.utils.CallbackInterface
import id.co.astra.adel.metamor.utils.convertCurrency

class OrderListAdapter(
    private val data: MutableList<Order>,
    private val onClickListener: (Order) -> Unit
): RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    var callbackInterface: CallbackInterface? = null
    var total = 0.0


    fun setListOrder(orderList: List<Order>) {
        data.clear()
        data.addAll(orderList)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemListOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    inner class OrderViewHolder(private val binding: ItemListOrderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: Order,
            onClickListener: (Order) -> Unit
        ) {
            val totalPrice = data.priceItem * data.quantityItem
            var totalQuantity = data.quantityItem
            with(binding) {
                tvNameItem.text = data.nameItem
                tvPriceItem.text = convertCurrency(data.priceItem)
                tvQuantity.text = data.quantityItem.toString()
                tvTotalPrice.text = convertCurrency(totalPrice)
                imgDelete.setOnClickListener {
                    onClickListener(data)
                }
                total += totalPrice
                totalQuantity += data.quantityItem
                callbackInterface?.passResultCallback(total.toString(), totalQuantity)
            }
        }

    }

}