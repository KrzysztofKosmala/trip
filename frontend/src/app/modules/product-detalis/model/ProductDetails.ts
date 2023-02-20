import { Review } from "./review";

export interface ProductDetails
{
    name: string,
    category: string,
    desc: string,
    basePrice: number,
    currency: string,
    slug: string,
    destination: string,
    slopNearby: boolean;
    apartment: boolean;
    house: boolean;
    wifi: boolean;
    food: boolean;
    spa: boolean;
    reviews: Array<Review>
}