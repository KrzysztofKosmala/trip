import { Payment } from "./Payment"

export interface OrderSummary
{
    id: number,
    placeDate: Date,
    status: string,
    grossValue: number,
    payment: Payment,
    friendEmails: string[]
}