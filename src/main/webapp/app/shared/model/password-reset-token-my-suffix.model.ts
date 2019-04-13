import { Moment } from 'moment';

export interface IPasswordResetTokenMySuffix {
    id?: number;
    token?: string;
    expiryDate?: Moment;
}

export class PasswordResetTokenMySuffix implements IPasswordResetTokenMySuffix {
    constructor(public id?: number, public token?: string, public expiryDate?: Moment) {}
}
