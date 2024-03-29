import { Image } from "../../admin/admin-image/model/Image";

export interface Product 
{
    name: string,
    destination: string,
    desc: string,
    basePrice: number,
    salePrice: number,
    currency: string,
    slug: string,
    images: Image[]
}