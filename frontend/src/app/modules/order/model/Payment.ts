import { PaymentType } from "./PaymentType";

export interface Payment{
    id: number,
    name: string,
    type: PaymentType,
    defaultPayment: boolean,
    note: string 
}