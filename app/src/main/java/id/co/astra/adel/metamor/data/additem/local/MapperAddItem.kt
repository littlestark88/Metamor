package id.co.astra.adel.metamor.data.additem.local

import id.co.astra.adel.metamor.domain.additem.model.AddItem


fun AddItem.mapToEntity() = AddItemEntity(
    idItem = idItem,
    nameItem = nameItem,
    priceItem = priceItem,
    imageItem = imageItem
)