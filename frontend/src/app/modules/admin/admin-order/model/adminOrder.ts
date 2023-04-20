import { Product } from "src/app/modules/common/model/product"
import { AdminOrderLog } from "./adminOrderLog"
import { AdminPayment } from "./adminPayment"

export interface AdminOrder{
    id: number,
    placeDate: Date,
    orderStatus: string,
    product: Product,
    grossValue: number,
    firstname: string,
    lastname: string,
    street: string,
    zipcode: string,
    city: string,
    email: string,
    phone: string,
    payment: AdminPayment
    orderLogs: Array<AdminOrderLog>
}