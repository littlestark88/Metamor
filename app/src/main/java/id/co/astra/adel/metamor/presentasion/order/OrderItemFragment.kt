package id.co.astra.adel.metamor.presentasion.order

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import id.co.astra.adel.metamor.databinding.FragmentOrderItemBinding
import id.co.astra.adel.metamor.domain.additem.model.AddItem
import id.co.astra.adel.metamor.domain.order.model.Order
import id.co.astra.adel.metamor.utils.Constants.ITEM_DATA
import id.co.astra.adel.metamor.utils.convertCurrency
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderItemFragment : DialogFragment() {

    private var _binding: FragmentOrderItemBinding? = null
    private val binding get() = _binding!!
    private var valueQuantity = 1
    private val orderViewModel: OrderViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOrderItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataItem = arguments?.getParcelable<AddItem>(ITEM_DATA)
        val priceDiscount = convertCurrency(dataItem?.priceItem)
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnSave.setOnClickListener {
                lifecycleScope.launch {
                    orderViewModel.insertOrder(
                        Order(
                            0,
                            dataItem?.nameItem.toString(),
                            dataItem?.priceItem ?: 0.0,
                            valueQuantity,
                            0
                        )
                    )
                }
                dismiss()
            }

            switchDiscount.setOnClickListener {
                if (switchDiscount.isChecked) {
                    edtDiscount.isEnabled = true
                    edtDiscount.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
//                            val discounts = edtDiscount.text.toString().trim()
//                            if (discounts.isEmpty()) {
//                                edtDiscount.hint = "0"
//                            } else {
//                                val discount = p0.toString().toInt()
//                                priceDiscount =
//                                    dataItem.priceItem - (dataItem.priceItem / 100 * discount)
//                            }
                        }
                    })

                } else {
                    edtDiscount.isEnabled = false
//                    priceDiscount = dataItem.priceItem
                }

            }
            btnPlus.setOnClickListener {
                val valuePlus = valueQuantity++
                tvQuantityValue.text = valuePlus.toString()
            }
            btnMinus.setOnClickListener {
                val valueMinus = valueQuantity--
                tvQuantityValue.text = valueMinus.toString()
            }
            tvNameItem.text = dataItem?.nameItem
            tvPrice.text = convertCurrency(dataItem?.priceItem)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }
}