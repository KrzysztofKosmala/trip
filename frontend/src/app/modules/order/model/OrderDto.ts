export interface OrderDto
{
    firstname: string,
    lastname: string,
    street: string,
    postal: string,
    city: string,
    email: string,
    phone: string,
    transport: string,
    departureCity: string,
    productslug: string,
    paymentId: number,
    friendEmails: string[] 
}