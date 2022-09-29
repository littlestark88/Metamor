package id.co.astra.adel.metamor.data.customer.local

import id.co.astra.adel.metamor.domain.customer.model.Customer

fun Customer.mapToEntity() = CustomerEntity(
    idCustomer = idCustomer,
    nameCustomer = nameCustomer,
    numberPhone = numberPhone,
    email = email
)