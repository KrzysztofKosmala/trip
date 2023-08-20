import { Payment } from "./Payment";
import { Transport } from "./transport";

export interface InitData
{
    payments: Array<Payment>
    transports: Array<Transport>
}