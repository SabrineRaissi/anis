export interface IPrivilegeMySuffix {
    id?: number;
    name?: string;
}

export class PrivilegeMySuffix implements IPrivilegeMySuffix {
    constructor(public id?: number, public name?: string) {}
}
