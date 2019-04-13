import { Moment } from 'moment';

export interface IDeviceMetadataMySuffix {
    id?: number;
    userIdDevice?: number;
    deviceDetails?: string;
    location?: string;
    lastLoggedIn?: Moment;
}

export class DeviceMetadataMySuffix implements IDeviceMetadataMySuffix {
    constructor(
        public id?: number,
        public userIdDevice?: number,
        public deviceDetails?: string,
        public location?: string,
        public lastLoggedIn?: Moment
    ) {}
}
