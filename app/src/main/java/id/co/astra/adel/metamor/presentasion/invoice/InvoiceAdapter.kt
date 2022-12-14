package id.co.astra.adel.metamor.presentasion.invoice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.astra.adel.metamor.R
import id.co.astra.adel.metamor.databinding.ItemListInvoiceBinding
import id.co.astra.adel.metamor.domain.checkout.model.Checkout
import id.co.astra.adel.metamor.utils.convertCurrency
import id.co.astra.adel.metamor.utils.convertPatternDate

class InvoiceAdapter(
    private val context: Context,
    private val data: MutableList<Checkout>,
    private val onClickListener: (Checkout) -> Unit
): RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>() {

    fun setListInvoice(checkoutEntity: List<Checkout>) {
        data.clear()
        data.addAll(checkoutEntity)
        notifyItemChanged(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            InvoiceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_invoice, parent, false))

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class InvoiceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListInvoiceBinding.bind(itemView)
        fun bind(
            data: Checkout
        ) {
            with(binding) {
                tvDate.text = convertPatternDate(data.date)
                tvNameValue.text = data.nameCustomer
                tvTotalValue.text = convertCurrency(data.total)
                itemView.setOnClickListener {
                    onClickListener(data)
                }
            }
        }
    }
}