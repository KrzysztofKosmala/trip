import { Image } from "../../admin-image/model/Image";

export interface AdminProductUpdate{
    id: number,
    name: string,
    desc: string,
    destination: string,
    basePrice: number,
    currency: string,
    slug: string
    fullDesc: string
    slopNearby: boolean,
    apartment: boolean,
    house: boolean,
    wifi: boolean,
    food: boolean,
    spa: boolean,
    startDate: Date,
    endDate: Date,
    images: Image[]
}