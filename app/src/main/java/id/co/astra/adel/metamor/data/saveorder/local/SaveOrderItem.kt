package id.co.astra.adel.metamor.data.saveorder.local

data class SaveOrderItem(
    var idItem: Int = 0,
    var nameItem: String?,
    var priceItem: Double?,
    var quantityItem: Int?,
    var discountItem: Int?
)
