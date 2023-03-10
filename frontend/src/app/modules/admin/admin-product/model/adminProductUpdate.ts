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
    spa: boolean
}