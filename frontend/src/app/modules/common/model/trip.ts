import { Product } from "./product";

export interface Trip extends Product
{
    destination: string;
}