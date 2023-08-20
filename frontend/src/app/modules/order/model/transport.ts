import { TransportType } from "./TransportType";

export interface Transport{
    id: number,
    name: string,
    type: TransportType,
    defaultTransport: boolean,
    note: string 
}